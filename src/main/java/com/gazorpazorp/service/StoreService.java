package com.gazorpazorp.service;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazorpazorp.client.LCBOFeignClient;
import com.gazorpazorp.model.Store;
import com.gazorpazorp.repository.StoreRepository;

@Service
public class StoreService {
	Logger logger = LoggerFactory.getLogger(StoreService.class);

	@Autowired
	LCBOFeignClient lcboClient;
	
	@Autowired
	StoreRepository storeRepo;
	
	public Store locateClosestStoreToCoords(double latitude, double longitude) {
		Store store = /*new Store();*/lcboClient.getStoresNearPoints(latitude, longitude).getResult().get(0);
//		store.setCity("Cambridge");
//		store.setAddress("120 Cedar St.");
//		store.setId(new Long(382));
//		store.setLatitude(43.3526762);
//		store.setLongitude(-80.3319758);
//		store.setPostalCode("N1S1W4");
		if (store!=null)
			store.Incorporate();
		logger.warn("STORE_ID" + store.getId());
		return store;
	}
	
	public Store getStoreById(Long id) {
//		Store store = lcboClient.getStoreById(id).getResult();
//		System.out.println(store);
		return lcboClient.getStoreById(id).getResult();
	}
	
	public List<Store> getStoresById(String storeIds) {
		if (!storeIds.isEmpty())
			return storeRepo.findAllById(Arrays.asList(storeIds.split(",")).stream().map(Long::parseLong).collect(Collectors.toList()));
		return new ArrayList<Store>();
//		return Arrays.asList(storeIds.split(",")).stream().map(id -> getStoreById(Long.parseLong(id))).collect(Collectors.toList());
	}
	
	public List<Store> getLCBOStoresById(String storeIds) {
//		return productRepo.findAllById(Arrays.asList(storeIds.split(",")).stream().map(Long::parseLong).collect(Collectors.toList()));
		return Arrays.asList(storeIds.split(",")).stream().map(id -> lcboClient.getStoreById(Long.parseLong(id)).getResult()).collect(Collectors.toList());
	}
	
	public void replaceSadCharactersOnStore(Store store) {
		replaceSadCharacters(store.getAddress());
		replaceSadCharacters(store.getCity());
	}	
	private void replaceSadCharacters(String str) {
		if (str == null)
			return;
		str.replace("\\", "");    
		str.replace("u005c", ""); 
		str.replace("u00e9", "é");
		str.replace("u000d", "");
		str.replace("u00bd", "½");
		str.replace("u00e2", "â");
		str.replace("u00e7", "ç");
		str.replace("u00e8", "è");
		str.replace("u00fb", "û");
		str.replace("u00f1", "ñ");
		str.replace("u00f4", "ô");
	}
}
