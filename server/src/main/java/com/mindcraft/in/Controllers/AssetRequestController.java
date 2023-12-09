package com.mindcraft.in.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindcraft.in.services.AssetRequestService;

@RestController
public class AssetRequestController {

    private final AssetRequestService assetRequestService;

    public AssetRequestController(AssetRequestService assetRequestService) {
        this.assetRequestService = assetRequestService;
    }

    @RequestMapping(value = "/getAssetStatus", method = RequestMethod.GET)
    public List<Map<String, Object>> getAssetStatus() {
        List<Map<String, Object>> result = assetRequestService.getAssetStatus();
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Fail to load Asset Request Displayed From Controller.");
        } else {
            response.put("status", "success");
            response.put("message", "All Asset Request Displayed From Controller.");
        }
        return result;
    }

    @RequestMapping(value = "/getDeliveringAssets", method = RequestMethod.GET)
    public List<Map<String, Object>> getDeliveringAssets() {
        List<Map<String, Object>> result = assetRequestService.getDeliveringAssets();
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Fail to load Asset Request status set to DELIVERING");
        } else {
            response.put("status", "success");
            response.put("message", "All Asset Request status set to DELIVERING Displayed");
        }
        return result;
    }

    @RequestMapping(value = "/updateAssetAllocation/{asset_request_id}", method = RequestMethod.GET)
    public Map<String, Object> updateAssetAllocation(@PathVariable Long asset_request_id) {
        Map<String, Object> result = assetRequestService.updateAssetAllocation(asset_request_id);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Fail to load Asset Request status set to DELIVERING");
        } else {
            response.put("status", "success");
            response.put("message", "All Asset Request status set to DELIVERING Displayed");
        }
        return result;
    }

    @RequestMapping(value = "/getRequest", method = RequestMethod.GET)
    public List<Map<String, Object>> getAssetRequest() {
        List<Map<String, Object>> requests = assetRequestService.getAssetRequest();
        Map<String, Object> response = new HashMap<>();

        if (requests.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no requests found");
        } else {
            response.put("status", "success");
            response.put("message", "all requests displayed");
        }
        return requests;
    }

    @RequestMapping(value = "/getLostAsset", method = RequestMethod.GET)
    public ResponseEntity<Integer> getAssetLostCount() {
        int lostAsset = assetRequestService.getAssetLostCount();
        return ResponseEntity.ok(lostAsset);
    }

    @RequestMapping(value = "/getLostAssets", method = RequestMethod.GET)
    public List<Map<String, Object>> getLostAssets() {
        List<Map<String, Object>> requests = assetRequestService.getLostAssets();
        Map<String, Object> response = new HashMap<>();

        if (requests.isEmpty()) {
            response.put("status", "error");
            response.put("message", "lost assets not found");
        } else {
            response.put("status", "success");
            response.put("message", "lost assets displayed");
        }
        return requests;
    }
}
