package com.gazorpazorp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gazorpazorp.model.Dataset;
import com.gazorpazorp.model.DatasetResult;
import com.gazorpazorp.model.Product;
import com.gazorpazorp.model.Store;
import com.gazorpazorp.repository.ProductRestRepository;
import com.gazorpazorp.repository.StoreRepository;

@Service
public class ProductRepositoryUpdateService extends Thread {
	Logger logger = LoggerFactory.getLogger(ProductRepositoryUpdateService.class);

	@Autowired
	DatasetUpdateMgr updateMgr;

	@Autowired
	ProductRestRepository productRepo;
	@Autowired
	StoreRepository storeRepo;

	@Autowired
	ProductService productService;
	@Autowired
	StoreService storeService;

	String key = "MDo1NDQwN2RjYy0wMDhkLTExZTctYWEwNy0yMzI4NjgxOTRjOWU6V2hSaDdoOXBVbjFjTU80cUtBZlpxRkI4UlJDVWcxRWlBUWZZ";
	
	@Scheduled(cron = "0 0 2 * * *")
	public void updateRepo() {
//		try {
//			PRUService.wait(new Long(5000));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		start();
	}

	@Override
	public void run() {
		RestTemplate datasetTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set(headers.AUTHORIZATION, "Token " + key);
		HttpEntity entity = new HttpEntity(headers);

		Dataset latestDataset = datasetTemplate
				.exchange("https://www.lcboapi.com/datasets/latest", HttpMethod.GET, entity, DatasetResult.class)
				.getBody().getResult();

		logger.info("Last Update ID: " + updateMgr.getLatestUpdate() + ", Latest Dataset ID: " + latestDataset.getId());

		if (latestDataset.getId() != updateMgr.getLatestUpdate()) {
			logger.info("Cron job update to latest dataset");
			addProducts(latestDataset);
			removeProducts(latestDataset);
			addStores(latestDataset);
			removeStores(latestDataset);
		}
	}

	private void addProducts(Dataset dataset) {
		List<Product> products = productService.getLCBOProductsById(
				dataset.getAddedProductIds().stream().map(Object::toString).collect(Collectors.joining(",")));
		products.forEach(p -> productService.replaceSadCharactersOnProduct(p));
		productRepo.saveAll(products);
	}

	private void removeProducts(Dataset dataset) {
		dataset.getRemovedProductIds().forEach(id -> productRepo.deleteById(id));
	}

	// Manage stores
	private void addStores(Dataset dataset) {
		List<Store> stores = storeService.getLCBOStoresById(
				dataset.getAddedStoreIds().stream().map(Object::toString).collect(Collectors.joining(",")));
		stores.forEach(s -> storeService.replaceSadCharactersOnStore(s));
		storeRepo.saveAll(stores);
	}

	private void removeStores(Dataset dataset) {
		dataset.getRemovedStoreIds().forEach(id -> {
			try {
				storeRepo.deleteById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
