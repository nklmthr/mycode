package com.twosides.social.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BaseVO {
	private long start = 1;
	private long limit = 20;
	private SortType[] sorts = { SortType.rating };

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public SortType[] getSorts() {
		return sorts;
	}

	public void setSorts(SortType[] sorts) {
		this.sorts = sorts;
	}

}
