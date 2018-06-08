package com.nklmthr.system.utils;

import java.io.File;

public class FileCheckWritableFiles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean setReadOnlyToWriteableFiles = false;
		boolean listDirectories = true, listFiles = false;
		// boolean listDirectorySizes = true;
		walk("/SAPDevelop/AN/src/ariba/ond/AN/mtrunk", setReadOnlyToWriteableFiles, listDirectories, listFiles);
	}

	public static void walk(String path, boolean setReadOnlyToWriteableFiles, boolean listDirectories,
			boolean listFiles) {
		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				if (listDirectories) {
					System.out.print("\nDir:" + f.getAbsoluteFile());
				}
				walk(f.getAbsolutePath(), setReadOnlyToWriteableFiles, listDirectories, listFiles);
			} else {
				if (setReadOnlyToWriteableFiles && f.canWrite()) {
					System.out.println(f.getAbsolutePath());
					f.setReadOnly();
				}
				if (listFiles) {
					System.out.println("File:" + f.getAbsoluteFile());
				}
			}
		}
	}
}
