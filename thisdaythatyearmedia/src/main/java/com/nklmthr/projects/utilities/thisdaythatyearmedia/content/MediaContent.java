package com.nklmthr.projects.utilities.thisdaythatyearmedia.content;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MediaContent {
	private String path;
	private String mediaName;
	private String exifName;
	private String mediaPath;
	private Date contentDate;
	private String localPath;
	private String extension;

	public void setPath(String path) {
		this.path = path;
		this.mediaName = path.substring(path.lastIndexOf("/") + 1, path.length());
		this.extension = path.substring(path.lastIndexOf("."), path.length());
		this.mediaPath = path.substring(0, path.lastIndexOf("/") + 1);
	}

	public void setContentDate(Date contentDate) {
		this.contentDate = contentDate;
		setExifName();
	}

	private void setExifName() {
		LocalDateTime mediaDate = getContentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		exifName = mediaDate.getYear() + "-" + String.format("%02d", mediaDate.getMonth().getValue()) + "-"
				+ String.format("%02d", mediaDate.getDayOfMonth()) + " " + String.format("%02d", mediaDate.getHour())
				+ "." + String.format("%02d", mediaDate.getMinute()) + getExtension();
	}

	public void setLocalPath(String localRoot) {
		this.localPath = localRoot + getMediaPath();
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getExifName() {
		return exifName;
	}

	public void setExifName(String exifName) {
		this.exifName = exifName;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getPath() {
		return path;
	}

	public Date getContentDate() {
		return contentDate;
	}

	public String getLocalPath() {
		return localPath;
	}

}
