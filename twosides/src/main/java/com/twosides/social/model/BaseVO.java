package com.twosides.social.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BaseVO {
	private long start = 1;
	private long limit = 20;
	private SORT[] sorts = { SORT.rating };

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

	public SORT[] getSorts() {
		return sorts;
	}

	public void setSorts(SORT[] sorts) {
		this.sorts = sorts;
	}

}

enum SORT {
	created, updated, rating, user
}