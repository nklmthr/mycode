package com.practice.sap.work;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HARParser {

	static String dirPath = "C:\\Users\\nklmt\\OneDrive\\Desktop\\";

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
		Map<String, String> onlyLOAUpdateHAR = parseHAR(dirPath + "hcm44preview.sapsf.com1.har", "acme");
		Map<String, String> onlySuccessUpdateHAR = parseHAR(dirPath + "hcm44preview.sapsf.com2.har", "acme");
		System.out.println(onlyLOAUpdateHAR.size());
		System.out.println(onlySuccessUpdateHAR.size());
		compareMap(onlyLOAUpdateHAR, onlySuccessUpdateHAR);
	}

	private static void compareMap(Map<String, String> onlyLOAUpdateHAR, Map<String, String> onlySuccessUpdateHAR) {
		for (String key : onlyLOAUpdateHAR.keySet()) {
			if (!onlyLOAUpdateHAR.get(key).equals(onlySuccessUpdateHAR.get(key))) {
				System.out.println(
						"Key:" + key + " ->" + onlyLOAUpdateHAR.get(key) + " != " + onlySuccessUpdateHAR.get(key));
			}
		}
	}

	private static Map<String, String> parseHAR(String fileName, String requestPath)
			throws FileNotFoundException, IOException, ParseException {
		System.out.println("*****************************************************");
		Map<String, String> paramMap = new HashMap<String, String>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(fileName));
		JSONObject jsonObject = (JSONObject) obj;
		JSONObject logObject = (JSONObject) jsonObject.get("log");
		JSONArray entriesArray = (JSONArray) logObject.get("entries");
		// System.out.println(entriesArray.toString());
		for (int i = 0; i < entriesArray.size(); i++) {
			JSONObject enteriesObject = (JSONObject) entriesArray.get(i);
			JSONObject requestObject = (JSONObject) enteriesObject.get("request");
			if (requestObject.get("url").toString().contains(requestPath)) {
				JSONObject postDataObject = (JSONObject) requestObject.get("postData");
				JSONArray paramArray = (JSONArray) postDataObject.get("params");
				for (int j = 0; j < paramArray.size(); j++) {
					JSONObject paramObject = (JSONObject) paramArray.get(j);
					String name = (String) paramObject.get("name");
					String value = (String) paramObject.get("value");
					//System.out.println(paramMap.size() + " -> " + i + "," + j + ":" + name + "=" + value);
					paramMap.put(name, value);
				}
			}
		}
		return paramMap;
	}

}
