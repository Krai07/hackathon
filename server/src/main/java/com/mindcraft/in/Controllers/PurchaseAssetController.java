package com.mindcraft.in.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindcraft.in.pojos.AssetPurchase;
import com.mindcraft.in.services.AssetService;

@RestController
public class PurchaseAssetController {

    private final AssetService assetService;

    public PurchaseAssetController(AssetService assetService){
        this.assetService = assetService;
    }

    @RequestMapping(value = "/addPurchaseAsset", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> insertPurchaseAsset(@RequestBody AssetPurchase assetPurchase) {
        Map<String, String> response = (Map<String, String>) assetService.insertAssetPurchaseRecord(assetPurchase);
        System.out.println(response);
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
}
