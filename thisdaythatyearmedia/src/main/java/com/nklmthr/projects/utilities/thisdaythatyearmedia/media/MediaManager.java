package com.nklmthr.projects.utilities.thisdaythatyearmedia.media;

import java.util.List;

import com.nklmthr.projects.utilities.thisdaythatyearmedia.content.MediaContainer;
import com.nklmthr.projects.utilities.thisdaythatyearmedia.exception.MediaManagerException;

public interface MediaManager {
	List<MediaContainer> getMediaContainers() throws MediaManagerException;
	void filterFilesForTime(MediaContainer container) throws MediaManagerException;
	void porpogateMedia(MediaContainer container) throws MediaManagerException;
	public void sendMail(MediaContainer container) throws MediaManagerException;
}
