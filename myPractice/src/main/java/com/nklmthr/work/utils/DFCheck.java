package com.nklmthr.work.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class DFCheck {
	public static void main(String[] args) {
		boolean a= false, b= false, c= false, d= true;
		
		System.out.println(a&&b || c&&d);
	}
	
	
//	public static void main(String[] args) {
//		File file1 = new File("/SAPDevelop/AN/src/ariba/ond/AN/mrel/network/service/common/sql/2018.06/DF26/NP-13242_requeue_failed_CTe_from_TaxID_16883860000746.sql");
//		File file2 = new File("/SAPDevelop/AN/src/ariba/ond/AN/mrel/network/service/common/sql/2018.06/DF31/NP-13242_requeue_failed_CTe_from_TaxID_16883860000746.sql");
//		HashSet<String> result1 = new HashSet<String>();
//		HashSet<String> result2 = new HashSet<String>();
//		try {
//			List<String> lines1 = check(file1);
//			List<String> lines2 = check(file2);
//			
//			for(String line: lines1){
//				if(line.indexOf("UPDATE document_disp_q set status=1, retry_count=0 where destination0='AN01049029371' and status=4 and retry_count=10 and item in (") >0){
//					Integer occ = lines1.indexOf(line);
//					List<String> res = getDocIdList(line);
//					result1.addAll(res);
//				}
//			}
//			Integer occ = 0;
//			for(String line: lines2){
//				occ++;
//				if(line.indexOf("UPDATE document_disp_q set status=1, retry_count=0 where destination0='AN01049029371' and status=4 and retry_count=10 and item in (") >0){
//					System.out.println(result2.size());
//					//Integer occ = lines2.indexOf(line);
//					List<String> res1 = getDocIdListFromUnwrapped(occ-1, lines2);
//					result2.addAll(res1);
//				}
//			}
//			
//			System.out.println(result1.size());
//			System.out.println(result2.size());
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		  
//		HashSet temp1 = new HashSet(result1);
//		HashSet temp2 = new HashSet(result2);
//		temp1.removeAll(result2);
//		temp2.removeAll(result1);
//		System.out.println(temp1);
//		System.out.println(temp2);
//		
//		
//	}
//
//	private static List<String> getDocIdListFromUnwrapped(Integer occ, List<String> lines2) {
//		Integer line = occ+1;
//		List<String> results = new ArrayList<String>();
//		while(!(lines2.get(line).indexOf(");")>0)){
//			//line;
//			//System.out.println(lines2.get(line));
//			//List<String> results1 = Arrays.asList(lines2.get(line).split("\\,"));
//			List<String> resus2 = (List<String>) Arrays.asList(lines2.get(line).split("\\,")).stream().map(s -> s.trim()).collect(Collectors.toList());
//			//System.out.println(results.size());
//			results.addAll(resus2);
//			line++;
//		}
//		occ = line;
//		return results;
//	}
//
//	private static List<String> getDocIdList(String line) {
//		
//		String sub = line.substring(line.indexOf("UPDATE document_disp_q set status=1, retry_count=0 where destination0='AN01049029371' and status=4 and retry_count=10 and item in (")+"UPDATE document_disp_q set status=1, retry_count=0 where destination0='AN01049029371' and status=4 and retry_count=10 and item in (".length(), line.lastIndexOf(");"));
//		return Arrays.asList(sub.split("\\,"));
//	}
//
//	private static List<String> check(File file) throws Exception {
//		List<String> lines = new ArrayList<String>();
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		String st;	
//		while ((st = br.readLine()) != null){
//			lines.add(st);
//		}
//		return lines;
//	}
}
