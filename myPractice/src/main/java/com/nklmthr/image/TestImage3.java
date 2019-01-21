package com.nklmthr.image;

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

public class TestImage3 {
	public static void main(String[] args) throws MetadataException, ImageProcessingException {
		File jpgFile = new File("/Users/i344377/Desktop/2017-09-23 18.28.31.jpg");
		Metadata metadata = ImageMetadataReader.readMetadata(jpgFile);

		// Read Exif Data
		Directory directory = metadata.getDirectory(ExifDirectory.class);
		if (directory != null) {
			// Read the date
			Date date = directory.getDate(ExifDirectory.TAG_DATETIME);
			DateFormat df = DateFormat.getDateInstance();
			df.format(date);
			int year = df.getCalendar().get(Calendar.YEAR);
			int month = df.getCalendar().get(Calendar.MONTH) + 1;

			System.out.println("Year: " + year + ", Month: " + month);

			System.out.println("Date: " + date);

			System.out.println("Tags");
			for (Iterator i = directory.getTagIterator(); i.hasNext();) {
				Tag tag = (Tag) i.next();
				System.out.println("\t" + tag.getTagName() + " = " + tag.getDescription());

			}
		}
	}
}
