package com.nklmthr.work.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DecryptPassword {

	public static void main(String[] args) {
		BufferedReader reader;
		Map<String, Integer> ids = new HashMap<String, Integer>();
		try {
			reader = new BufferedReader(
					new FileReader("/Users/i344377/OneDrive - SAP SE/Ariba/NP-18352/TWSigningFailures.csv"));
			String line = reader.readLine();
			String base = "\"XMLDocException Exception ariba.network.common.cxml.CXMLDocException\",,,\"Production Verify error for document ";
			String suff = ": Code = SignerInvalid, Description = The signing certificate was expired at the time of validation in Production verifying document ";
			while (line != null) {
				if (line.startsWith(
						base)) {
					String docId = line.substring(line.indexOf(base)+base.length(), line.indexOf(suff));
					System.out.println(docId);
					// read next line
					
				}
				if(ids.containsKey(line)){
					ids.put(line, ids.get(line)+1);
				}
				else{
					ids.put(line, 1);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String id: ids.keySet()){
			if(ids.get(id) > 1){
				//System.out.println(id+" "+ids.get(id));
			}
		}
	}

}
