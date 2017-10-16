package com.gazorpazorp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazorpazorp.client.LCBOFeignClient;
import com.gazorpazorp.model.Store;

@Service
public class StoreService {
	Logger logger = LoggerFactory.getLogger(StoreService.class);

	@Autowired
	LCBOFeignClient lcboClient;
	
	public Store locateClosestStoreToCoords(double latitude, double longitude) {
		Store store = new Store();//lcboClient.getStoresNearPoints(latitude, longitude).getResult().get(0);
		store.setCity("Cambridge");
		store.setAddress("120 Cedar St.");
		store.setId(new Long(382));
		store.setLatitude(43.3526762);
		store.setLongitude(-80.3319758);
		store.setPostalCode("N1S1W4");
		if (store!=null)
			store.Incorporate();
		logger.warn("STORE_ID" + store.getId());
		return store;
	}
}
