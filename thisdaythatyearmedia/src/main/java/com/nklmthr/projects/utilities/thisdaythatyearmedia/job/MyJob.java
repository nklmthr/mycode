package com.nklmthr.projects.utilities.thisdaythatyearmedia.job;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;

import com.nklmthr.projects.utilities.thisdaythatyearmedia.content.MediaContainer;
import com.nklmthr.projects.utilities.thisdaythatyearmedia.exception.MediaManagerException;
import com.nklmthr.projects.utilities.thisdaythatyearmedia.media.DropBoxMediaManager;

@Controller
public class MyJob extends QuartzJobBean{
	@Autowired
	DropBoxMediaManager manager;
	
	Logger logger = Logger.getLogger(MyJob.class);
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		try {
//			manager.sendMail(null);
			List<MediaContainer> containers = manager.getMediaContainers();
			for(MediaContainer container : containers) {
				manager.filterFilesForTime(container);
				manager.porpogateMedia(container);
				//manager.sendMail(container);
			}
		} catch (MediaManagerException e) {
			logger.error(e, e);
		}
	}

}
