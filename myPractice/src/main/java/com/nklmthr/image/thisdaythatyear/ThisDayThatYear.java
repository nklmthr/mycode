package com.nklmthr.image.thisdaythatyear;

import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectory;

public class ThisDayThatYear {
	public static void main(String[] args) throws MetadataException, ImageProcessingException {
		walk("/Users/i344377/Dropbox/nmathur/allway/hemanta/NikSrish");
	}

	public static void walk(String path) throws MetadataException, ImageProcessingException {
		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				// System.out.println(f.getPath());
				walk(f.getAbsolutePath());
			} else {
				// System.out.println(f.getName());
				checkThisDayThatYear(f.getAbsolutePath());
			}
		}
	}

	static void checkThisDayThatYear(String imagePath) {
		try {
			File jpgFile = new File(imagePath);
			Metadata metadata = ImageMetadataReader.readMetadata(jpgFile);
			//System.out.print("file=" + imagePath);
			// Read Exif Data
			Directory directory = metadata.getDirectory(ExifDirectory.class);
			if (directory != null) {
				// Read the date
				if (directory.containsTag(ExifDirectory.TAG_DATETIME)) {
					Date date = directory.getDate(ExifDirectory.TAG_DATETIME);
					DateFormat df = DateFormat.getDateInstance();
					df.format(date);
					int year = df.getCalendar().get(Calendar.YEAR);
					int month = df.getCalendar().get(Calendar.MONTH) + 1;
					int day = df.getCalendar().get(Calendar.DAY_OF_MONTH);
					Calendar today = Calendar.getInstance();
					int monthToday = today.get(Calendar.MONTH) + 1;
					int dayToday = today.get(Calendar.DAY_OF_MONTH);
					if (monthToday == month && dayToday == day) {
						System.out.println("month=" + month + ", day=" + day + ", year=" + year);
					}
				}else{
					System.out.println();
				}
			}else{
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
