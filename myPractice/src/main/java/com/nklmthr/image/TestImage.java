package com.nklmthr.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

public class TestImage {
	public static void main(String[] args) throws IOException {
		File fi = new File("/Users/i344377/Desktop/WhatsApp Image 2018-09-12 at 9.05.59 AM.jpeg");
		BufferedImage img = ImageIO.read(fi);
		
		BufferedImage temp = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics graphics = temp.getGraphics();
		graphics.drawImage(img, 0, 0, null);
		graphics.setFont(new Font("Arial", Font.PLAIN, 80));
		graphics.setColor(new Color(255, 0, 0, 255));
		String watermark = "NIKHIL";
		graphics.drawString(watermark, img.getWidth() / 5, img.getHeight() / 3);
		graphics.dispose();
		File fo = new File("/Users/i344377/Desktop/output.jpg");
		ImageIO.write(temp, "jpg", fo);
		
		
	
	}
}

