package com.gazorpazorp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gazorpazorp.model.Dataset;
import com.gazorpazorp.model.DatasetResult;
import com.gazorpazorp.model.Product;
import com.gazorpazorp.model.Store;
import com.gazorpazorp.repository.ProductRestRepository;
import com.gazorpazorp.repository.StoreRepository;

@Service
public class ProductRepositoryCreationService extends Thread {
	Logger logger = LoggerFactory.getLogger(ProductRepositoryCreationService.class);

	@Autowired
	ProductRestRepository productRepo;
	@Autowired
	StoreRepository storeRepo;

	@Autowired
	ProductService productService;
	@Autowired
	StoreService storeService;

	String key = "MDo1NDQwN2RjYy0wMDhkLTExZTctYWEwNy0yMzI4NjgxOTRjOWU6V2hSaDdoOXBVbjFjTU80cUtBZlpxRkI4UlJDVWcxRWlBUWZZ";
	String initalDatasetId = "2358";
	
	@Autowired
	DatasetUpdateMgr updateMgr;
//	int latestDatasetUpdate = 0;

	@Override
	public void run() {
		// get the dataset
		RestTemplate datasetTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set(headers.AUTHORIZATION, "Token " + key);
		HttpEntity entity = new HttpEntity(headers);

		Dataset initialSet = datasetTemplate
				.exchange("https://www.lcboapi.com/datasets/"+initalDatasetId, HttpMethod.GET, entity, DatasetResult.class)
				.getBody().getResult();

		logger.info("Starting the initial update to latest");
		updateFromDataset(initialSet);

//		logger.info("Creating Repository");
//		Dataset initialSet = datasetTemplate
//				.exchange("https://www.lcboapi.com/datasets/latest", HttpMethod.GET, entity, DatasetResult.class)
//				.getBody().getResult();
//
//		logger.info("Starting the initial update to latest");
//		parseAndCreateRepoFromDataset(initialSet);
	}
	
	private void updateFromDataset(Dataset initialDataset) {
		RestTemplate datasetTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set(headers.AUTHORIZATION, "Token " + key);
		HttpEntity entity = new HttpEntity(headers);

		Dataset latestDataset = datasetTemplate
				.exchange("https://www.lcboapi.com/datasets/latest", HttpMethod.GET, entity, DatasetResult.class)
				.getBody().getResult();
		
		//Get all updates up to the latest dataset
		for (int x = initialDataset.getId()+1; x<latestDataset.getId(); x++) {
			
			Dataset dataset = datasetTemplate
					.exchange("https://www.lcboapi.com/datasets/"+x, HttpMethod.GET, entity, DatasetResult.class)
					.getBody().getResult();
			logger.info("Updating to dataset" + dataset.getId());
			addProducts(dataset);
			removeProducts(dataset);
		}
		//Update from the latest dataset
		logger.info("Updating to latest dataset");
		addProducts(latestDataset);
		removeProducts(latestDataset);
		addStores(latestDataset);
		removeStores(latestDataset);
		updateMgr.setLatestUpdate(latestDataset.getId());
		logger.info("Finished initial update. Latest ID updated is " + updateMgr.getLatestUpdate());
	}
	
	//Manage products
	private void addProducts(Dataset dataset) {
		List<Product> products = productService.getLCBOProductsById(dataset.getAddedProductIds().stream().map(Object::toString).collect(Collectors.joining(",")));
		products.forEach(p -> productService.replaceSadCharactersOnProduct(p));
		productRepo.saveAll(products);	
	}
	private void removeProducts(Dataset dataset) {
		dataset.getRemovedProductIds()
			.forEach(id -> {
				try {
					productRepo.deleteById(id);
				} catch(Exception e) {
					e.printStackTrace();
				}
			});
	}
	//Manage stores
	private void addStores(Dataset dataset) {
		List<Store> stores = storeService.getStoresById(dataset.getAddedStoreIds().stream().map(Object::toString).collect(Collectors.joining(",")));
		stores.forEach(s -> storeService.replaceSadCharactersOnStore(s));
		storeRepo.saveAll(stores);
	}
	private void removeStores(Dataset dataset) {
		dataset.getRemovedStoreIds()
			.forEach(id -> {
				try {
					storeRepo.deleteById(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}
	

	private void parseAndCreateRepoFromDataset(Dataset dataset) {

		for (long x = 0; x < dataset.getTotalProducts(); x++) {
			Long productId = dataset.getProductIds().get((int) x);
			try {
					productRepo.save(productService.getProductById(productId));
			} catch (Exception e) {
				//e.printStackTrace();
				logger.error("Failed to persist product with id: " + productId + ". " + e.getMessage());
				e.printStackTrace();
			}
			if (x % 100 == 0) 
				productRepo.flush();
		}
		for (long x = 0; x < dataset.getTotalStores(); x++) {
			Long storeId = dataset.getStoreIds().get((int) x);
			try {
				storeRepo.save(storeService.getStoreById(storeId));
			} catch (Exception e) {
				//e.printStackTrace();
				logger.error("Failed to persist store with id: " + storeId + ". " + e.getMessage());
				e.printStackTrace();
			}
			if (x % 100 == 0) 
				storeRepo.flush();			
		}
		logger.info("IMPORT COMPLETE");
	}
}
