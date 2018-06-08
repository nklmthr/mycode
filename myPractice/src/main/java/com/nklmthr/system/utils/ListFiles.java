package com.nklmthr.system.utils;

import java.io.File;
import java.util.Objects;

public class ListFiles {

	public static void main(String[] args) {
				
		walk("/SAPDevelop/AN/out/local/an_mdev/install/WebObjects/Apps", false, true, false, false);
		//boolean check = test();
	}
	private static boolean test() {	
		Boolean check = null;
		return check;
	}

	public static void walk(String path, boolean setReadOnlyToWriteableFiles, boolean listDirectories, boolean listAbsoultePath,
			boolean listFiles) {
		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				if (listDirectories) {
					if(listAbsoultePath){
						System.out.print(f.getAbsoluteFile()+"\n");
					}
					else{
						System.out.print(f.getName()+"\n");
					}
					
				}
				//walk(f.getAbsolutePath(), setReadOnlyToWriteableFiles, listDirectories, listFiles);
			} else {
				if (setReadOnlyToWriteableFiles && f.canWrite()) {
					System.out.println(f.getAbsolutePath());
					f.setReadOnly();
				}
				if (listFiles) {
					if(listAbsoultePath){
						System.out.print(f.getAbsoluteFile()+"\n");
					}
					else{
						System.out.print(f.getName()+"\n");
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		return "ListFiles [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}



