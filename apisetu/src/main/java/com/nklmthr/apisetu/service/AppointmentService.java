package com.nklmthr.apisetu.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nklmthr.apisetu.model.Appointment;
import com.nklmthr.apisetu.model.Center;
import com.nklmthr.apisetu.model.District;
import com.nklmthr.apisetu.model.Notification;
import com.nklmthr.apisetu.model.Session;

@Service
public class AppointmentService {
	private static final Logger logger = Logger.getLogger(AppointmentService.class);

	@Value("${appointment.useTestdata}")
	private boolean useTestData;

	@Value("${district.useMasterData}")
	private boolean useDistrictMasterData;

	@Value("${appointment.districts}")
	private String csvDistricts;

	@Value("${notification.mailto.list}")
	private String mailToList;

	public void findAppointmentsAvailabilityandNotify(int minAge) throws ParseException, IOException {
		logger.debug("useTestData=" + useTestData);
		logger.debug("minAge=" + minAge);
		// "07-05-2021"
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String startDate = dateFormat.format(new Date());
		List<String> districts = Arrays.asList(csvDistricts.split("\\,"));
		String districtJSON = getDistrictDetails();
		Map<String, String> districtMap = getDistrictMap(districtJSON);
		Appointment apt = new Appointment();

		apt.districts = new ArrayList<District>();

		for (String district : districts) {
			District newDistrict = new District(district, districtMap.get(district));
			newDistrict.centers = new ArrayList<Center>();
			apt.districts.add(newDistrict);
		}
		logger.info("Now searching for " + apt.districts);
		for (District dis : apt.districts) {
			String response = getAppointmentResponse(dis.districtId, startDate);
			logger.debug("resposne=" + response);
			if (StringUtils.isNotBlank(response) && response.startsWith("{")) {
				parseAppointment(apt, response);
				List<Notification> notifications = findAvailability(apt, minAge);
				if (notifications.size() > 0) {
					logger.info("Recieved availability for " + notifications);
					sendMail(notifications);
					writeResponseToFile(response);
				}
			} else {
				logger.warn("Could not recieve availability from API");
			}
		}
	}

	public List<Notification> findAvailability(Appointment apt, int minAge) throws IOException {
		List<Notification> notifications = new ArrayList<Notification>();
		for (District district : apt.districts) {
			for (Center center : district.centers) {
				for (Session session : center.sesions) {
					double capacity = Double.valueOf(session.availCapacity);
					int minimumAge = Integer.parseInt(session.minAge);
					if (capacity > 2.0 && minimumAge <= minAge) {
						Notification not = new Notification();
						not.setMessage("District:" + district.districtName + ", Center: " + center.name + ", "
								+ center.address + " has <b>" + session.availCapacity + "</b> available slot for age "
								+ session.minAge + "+ on date: " + session.date + "<br><br><br>");
						notifications.add(not);
					}
				}
			}
		}
		return notifications;
	}

	private Appointment parseAppointment(Appointment apt, String response) {

		JSONObject obj = new JSONObject(response);
		JSONArray arr = obj.getJSONArray("centers");
		for (int i = 0; i < arr.length(); i++) {
			JSONObject centerJSON = arr.getJSONObject(i);
			Center center = new Center(centerJSON.get("name").toString(), centerJSON.get("address").toString(),
					centerJSON.get("from").toString(), centerJSON.get("to").toString());
			JSONArray sessions = centerJSON.getJSONArray("sessions");
			for (int j = 0; j < sessions.length(); j++) {
				JSONObject sessionJSON = sessions.getJSONObject(j);
				center.sesions = new ArrayList<com.nklmthr.apisetu.model.Session>();
				Session session = new Session(sessionJSON.get("date").toString(),
						sessionJSON.get("available_capacity").toString(), sessionJSON.get("min_age_limit").toString(),
						sessionJSON.get("vaccine").toString());
				center.sesions.add(session);
			}
			apt.districts.get(0).centers.add(center);
		}
		return apt;
	}

	private String getAppointmentResponse(String district, String startDate) throws ParseException, IOException {
		String response = "";
		if (useTestData) {
			File file = new File("C:\\Users\\I344377\\OneDrive - SAP SE\\Desktop\\calendarByDistrict.json");
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			String st;
			while ((st = br.readLine()) != null)
				sb.append(st);

			response = sb.toString();
		} else {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(
					"https://cdn-api.co-vin.in/api/v2/appointment/sessions/calendarByDistrict?district_id=" + district
							+ "&date=" + startDate);
			httpget.addHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0");
			httpget.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			httpget.addHeader("Accept-Language", "en-US,en;q=0.5");
			httpget.addHeader("Accept-Encoding", "gzip, deflate, br");
			httpget.addHeader("Upgrade-Insecure-Requests", "1");
			HttpResponse httpresponse = httpclient.execute(httpget);
			logger.info("Response recieved from co-vin.in");
			response = EntityUtils.toString(httpresponse.getEntity());
		}
		logger.debug("response:" + response);
		return response;
	}

	private void sendMail(List<Notification> notifications) {
		long starttime = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		for (Notification notification : notifications) {
			sb.append(notification.getMessage());
		}

		// Sender's email ID needs to be mentioned
		String from = "niksrish.sync@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("niksrish.sync@gmail.com", "Coron@1404");
			}
		});

		// Used to debug SMTP issues
		session.setDebug(false);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			String[] mailToArr = mailToList.split("\\,");
			for (String mailTo : mailToArr) {
				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
			}
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH mm ss");
			// Set Subject: header field
			message.setSubject("New Appointments available...Update at " + formatter.format(new Date()));

			// Now set the actual message
			message.setContent(sb.toString(), "text/html");

			logger.debug("sending...");
			// Send message
			Transport.send(message);
			long endTime = System.currentTimeMillis();
			logger.info("Sending mail to " + mailToList + " took " + (endTime - starttime) + "ms");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public void writeResponseToFile(String content) throws IOException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		File file = new File("C:\\Source\\vaccination\\appointmentjsons\\" + formatter.format(new Date()) + ".json");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		AppointmentService srv = new AppointmentService();
		String districtJSON = srv.getDistrictDetails();
		Map<String, String> districtMap = srv.getDistrictMap(districtJSON);
		srv.logger.info(districtMap);
	}

	public String getDistrictDetails() throws ClientProtocolException, IOException {
		String response = "";
		if (useDistrictMasterData) {
			response = "{\"districts\":[{\"district_id\":270,\"district_name\":\"Bagalkot\"},{\"district_id\":276,\"district_name\":\"Bangalore Rural\"},{\"district_id\":265,\"district_name\":\"Bangalore Urban\"},{\"district_id\":294,\"district_name\":\"BBMP\"},{\"district_id\":264,\"district_name\":\"Belgaum\"},{\"district_id\":274,\"district_name\":\"Bellary\"},{\"district_id\":272,\"district_name\":\"Bidar\"},{\"district_id\":271,\"district_name\":\"Chamarajanagar\"},{\"district_id\":273,\"district_name\":\"Chikamagalur\"},{\"district_id\":291,\"district_name\":\"Chikkaballapur\"},{\"district_id\":268,\"district_name\":\"Chitradurga\"},{\"district_id\":269,\"district_name\":\"Dakshina Kannada\"},{\"district_id\":275,\"district_name\":\"Davanagere\"},{\"district_id\":278,\"district_name\":\"Dharwad\"},{\"district_id\":280,\"district_name\":\"Gadag\"},{\"district_id\":267,\"district_name\":\"Gulbarga\"},{\"district_id\":289,\"district_name\":\"Hassan\"},{\"district_id\":279,\"district_name\":\"Haveri\"},{\"district_id\":283,\"district_name\":\"Kodagu\"},{\"district_id\":277,\"district_name\":\"Kolar\"},{\"district_id\":282,\"district_name\":\"Koppal\"},{\"district_id\":290,\"district_name\":\"Mandya\"},{\"district_id\":266,\"district_name\":\"Mysore\"},{\"district_id\":284,\"district_name\":\"Raichur\"},{\"district_id\":292,\"district_name\":\"Ramanagara\"},{\"district_id\":287,\"district_name\":\"Shimoga\"},{\"district_id\":288,\"district_name\":\"Tumkur\"},{\"district_id\":286,\"district_name\":\"Udupi\"},{\"district_id\":281,\"district_name\":\"Uttar Kannada\"},{\"district_id\":293,\"district_name\":\"Vijayapura\"},{\"district_id\":285,\"district_name\":\"Yadgir\"},{\"district_id\":507,\"district_name\":\"Ajmer\"},{\"district_id\":512,\"district_name\":\"Alwar\"},{\"district_id\":519,\"district_name\":\"Banswara\"},{\"district_id\":516,\"district_name\":\"Baran\"},{\"district_id\":528,\"district_name\":\"Barmer\"},{\"district_id\":508,\"district_name\":\"Bharatpur\"},{\"district_id\":523,\"district_name\":\"Bhilwara\"},{\"district_id\":501,\"district_name\":\"Bikaner\"},{\"district_id\":514,\"district_name\":\"Bundi\"},{\"district_id\":521,\"district_name\":\"Chittorgarh\"},{\"district_id\":530,\"district_name\":\"Churu\"},{\"district_id\":511,\"district_name\":\"Dausa\"},{\"district_id\":524,\"district_name\":\"Dholpur\"},{\"district_id\":520,\"district_name\":\"Dungarpur\"},{\"district_id\":517,\"district_name\":\"Hanumangarh\"},{\"district_id\":505,\"district_name\":\"Jaipur I\"},{\"district_id\":506,\"district_name\":\"Jaipur II\"},{\"district_id\":527,\"district_name\":\"Jaisalmer\"},{\"district_id\":533,\"district_name\":\"Jalore\"},{\"district_id\":515,\"district_name\":\"Jhalawar\"},{\"district_id\":510,\"district_name\":\"Jhunjhunu\"},{\"district_id\":502,\"district_name\":\"Jodhpur\"},{\"district_id\":525,\"district_name\":\"Karauli\"},{\"district_id\":503,\"district_name\":\"Kota\"},{\"district_id\":532,\"district_name\":\"Nagaur\"},{\"district_id\":529,\"district_name\":\"Pali\"},{\"district_id\":522,\"district_name\":\"Pratapgarh\"},{\"district_id\":518,\"district_name\":\"Rajsamand\"},{\"district_id\":534,\"district_name\":\"Sawai Madhopur\"},{\"district_id\":513,\"district_name\":\"Sikar\"},{\"district_id\":531,\"district_name\":\"Sirohi\"},{\"district_id\":509,\"district_name\":\"Sri Ganganagar\"},{\"district_id\":526,\"district_name\":\"Tonk\"},{\"district_id\":504,\"district_name\":\"Udaipur\"}],\"ttl\":24}";
		} else {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet("https://cdn-api.co-vin.in/api/v2/admin/location/districts/17");
			httpget.addHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0");
			httpget.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			httpget.addHeader("Accept-Language", "en-US,en;q=0.5");
			httpget.addHeader("Accept-Encoding", "gzip, deflate, br");
			httpget.addHeader("Upgrade-Insecure-Requests", "1");
			HttpResponse httpresponse = httpclient.execute(httpget);
			logger.info("Response recieved from co-vin.in");
			response = EntityUtils.toString(httpresponse.getEntity());
			logger.debug(response);
		}
		return response;
	}

	public Map<String, String> getDistrictMap(String districtsJson) {
		Map<String, String> districtMap = new HashMap<String, String>();
		JSONObject obj = new JSONObject(districtsJson);
		JSONArray arr = obj.getJSONArray("districts");
		for (int i = 0; i < arr.length(); i++) {
			String districtId = arr.getJSONObject(i).get("district_id").toString();
			String districtName = arr.getJSONObject(i).get("district_name").toString();
			districtMap.put(districtId, districtName);
		}
		return districtMap;
	}

}
