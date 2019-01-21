package com.nklmthr.work.utils;

import java.net.URISyntaxException;
import java.util.List;

import com.perforce.p4java.client.IClient;
import com.perforce.p4java.core.file.FileSpecBuilder;
import com.perforce.p4java.core.file.IFileSpec;
import com.perforce.p4java.exception.AccessException;
import com.perforce.p4java.exception.ConfigException;
import com.perforce.p4java.exception.ConnectionException;
import com.perforce.p4java.exception.NoSuchObjectException;
import com.perforce.p4java.exception.RequestException;
import com.perforce.p4java.exception.ResourceException;
import com.perforce.p4java.server.IServer;
import com.perforce.p4java.server.ServerFactory;

public class PerforceUtility {

	public static void main(String[] args) {
		try {
			IServer server = ServerFactory.getServer("p4java://perforce.ariba.com:1666", null);
			// server.addTrust(new TrustOptions().setAutoAccept(true));
			server.connect();
			server.setUserName("nikhil.mathur");
			server.login("P@ssword1312");

			IClient client = server.getClient("nmathur_mac");
			System.out.println(client.getOwnerName());
			List<IFileSpec> fileList = null;

			fileList = server.getDepotFiles(FileSpecBuilder.makeFileSpecList(new String[] { "//ariba/ond/AN/mdev/network/..." }), true);
			List<IFileSpec> result = server.getDirectories(fileList, false, false, false);
			for(IFileSpec file : result){
				System.out.println(file.getOpStatus().name());
			}
			server.disconnect();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

