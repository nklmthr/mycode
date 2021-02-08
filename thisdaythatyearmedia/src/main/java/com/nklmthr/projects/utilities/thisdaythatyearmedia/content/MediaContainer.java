package com.nklmthr.projects.utilities.thisdaythatyearmedia.content;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaContainer {
	public MediaContainer(String path) {
		this.path = path;
	}
	private String path;
	private List<MediaContent> contents;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<MediaContent> getContents() {
		return contents;
	}
	public void setContents(List<MediaContent> contents) {
		this.contents = contents;
	}
	
}
