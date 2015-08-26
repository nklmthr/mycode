package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.io.Serializable;
import java.util.List;


public class EinsVO implements Serializable {

	private static final long serialVersionUID = 1L;


	protected List<EinVO> eins;

	
	@Override
	public String toString() {
		if (eins != null) {
			return "eins.size()=" + eins.size();
		} else {
			return "eins=null";
		}
	}

	/**
	 * @return the eins
	 */
	public List<EinVO> getEins() {
		return eins;
	}

	/**
	 * @param eins to set
	 */
	public void setEins(List<EinVO> eins) {
		this.eins = eins;
	}

}
