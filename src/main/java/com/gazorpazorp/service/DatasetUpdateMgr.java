package com.gazorpazorp.service;

import org.springframework.stereotype.Component;

@Component
public class DatasetUpdateMgr {
	private int latestUpdate;

	public int getLatestUpdate() {
		return latestUpdate;
	}

	public void setLatestUpdate(int latestUpdate) {
		this.latestUpdate = latestUpdate;
	}
	
	
}
