package com.nklmthr.an.utils.twsoap;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Base64;
import java.util.Random;

import javax.net.ssl.SSLContext;

import org.apache.axis.AxisFault;
import org.apache.axis.AxisProperties;
import org.apache.axis.client.Stub;
import org.w3c.dom.Document;

import com.nklmthr.an.utils.domain.DomainManager;

import ariba.network.common.wsdsig.SignRequest;
import ariba.network.common.wsdsig.SwitchServiceSoap12Stub;

public class TWSoapUtil {

	public static void main(String[] args) {
		try {
			SwitchServiceSoap12Stub stub = new SwitchServiceSoap12Stub();
			AxisProperties.setProperty("axis.socketSecureFactory","org.apache.axis.components.net.SunFakeTrustSocketFactory");
			stub._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, "https://tseiod-test.trustweaver.com/ts/Svs.asmx");
			AxisProperties.setProperty("keystore", "/Users/i344377/AN/out/local/an_mdev/install/lib/certs/trustweaver/TSEOID-TEST_Client_Auth.p12");
            AxisProperties.setProperty("keystorePassword", "Ff6A3SpFw9Zi");
            AxisProperties.setProperty("keystoreType", "PKCS12");
            SSLContext 	context = SSLContext.getInstance("TLSv1.1");
            context.init(null, null, null);
            stub._setProperty("keystore", "/Users/i344377/AN/out/local/an_mdev/install/lib/certs/trustweaver/TSEOID-TEST_Client_Auth.p12");
            stub._setProperty("keystorePassword", "Ff6A3SpFw9Zi");
            stub._setProperty("keystoreType", "PKCS12");
            
            SignRequest request = new SignRequest();
			request.setInputType("CXML");
			request.setOutputType("XMLSIG");
			request.setSenderTag("DE");
			request.setReceiverTag("DE");
			byte[] document = getCXMLDocument();
			request.setDocument(document);

			stub.sign(request);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static byte[] getCXMLDocument() throws IOException, Exception {
		String newName = new Random().nextInt(1000) + "b4Value";
		Document doc = DomainManager.convertStringToDocument(DomainManager.getResource("invoice.xml"));
		DomainManager.changeNodeAttributeValue(doc, "/cXML", "cXML", "payloadID", newName);
		DomainManager.changeNodeAttributeValue(doc, "/cXML/Request/InvoiceDetailRequest/InvoiceDetailRequestHeader",
				"InvoiceDetailRequestHeader", "invoiceID", newName);
		return Base64.getEncoder().encode(DomainManager.convertDocumentToString(doc).getBytes());
	}

}
