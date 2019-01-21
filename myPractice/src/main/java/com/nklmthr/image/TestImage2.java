package com.nklmthr.image;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

public class TestImage2 {
	public static void main(String[] args) throws IOException {

		File file = new File("/Users/i344377/Desktop/2017-09-23 18.28.31.jpg");
		ImageInputStream iis = ImageIO.createImageInputStream(file);
		Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
		if (readers.hasNext()) {
			// pick the first available ImageReader
			ImageReader reader = readers.next();
			System.out.println(reader.isIgnoringMetadata());
			// attach source to the reader
			reader.setInput(iis, true);
			// read metadata of first image
			IIOMetadata metadata = reader.getImageMetadata(0);
			String[] names = metadata.getMetadataFormatNames();
			int length = names.length;
			for (int i = 0; i < length; i++) {
				System.out.println("Format name: " + names[i]);
				displayMetadata(metadata.getAsTree(names[i]));
			}
		}
	}

	private static void displayMetadata(Node node) {
		StringWriter sw = new StringWriter();
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
			System.out.println("nodeToString Transformer Exception");
		}
		System.out.println(sw.toString());

	}
}
