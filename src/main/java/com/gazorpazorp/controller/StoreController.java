package com.gazorpazorp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gazorpazorp.model.Store;
import com.gazorpazorp.service.StoreService;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/locate")
	public ResponseEntity getSampleById (@RequestParam("lat")double latitude, @RequestParam("long")double longitude) throws Exception {
		return Optional.ofNullable(storeService.locateClosestStoreToCoords(latitude, longitude))
				.map(s -> new ResponseEntity<Store>(s, HttpStatus.OK))
				.orElseThrow(() -> new Exception("There are no store near the coordinates provided."));
	}

	
	
}
