package com.mindcraft.in.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindcraft.in.pojos.AddAssetReqView;
import com.mindcraft.in.pojos.AssetRequest;
import com.mindcraft.in.services.AddAssetReqService;

@RestController
public class AddAssetReqController {

    private final AddAssetReqService addAssetReqService;

    public AddAssetReqController(AddAssetReqService addAssetReqService) {
        this.addAssetReqService = addAssetReqService;
    }

    @RequestMapping(value = "/insertAssetReq", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> insertAssetReq(@RequestBody AddAssetReqView addAssetReqView) {
        Map<String, Object> response = (Map<String, Object>) addAssetReqService.insertAssetReq(addAssetReqView);
        System.out.println(response);
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @RequestMapping(value = "/checkComments/{assetrequestId}/{approvalId}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> checkComments(
            @PathVariable Long assetrequestId,
            @PathVariable Long approvalId,
            @RequestBody AssetRequest assetRequest) {
        Map<String, Object> response = (Map<String, Object>) addAssetReqService.checkComments(approvalId,assetRequest);
        System.out.println(response);
        System.out.println("Approval id at backend: " + approvalId);
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @RequestMapping(value = "/rejectComments/{assetrequestId}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> rejectComments(
            @PathVariable Long assetrequestId,
            @RequestBody AssetRequest assetRequest) {
        Map<String, Object> response = (Map<String, Object>) addAssetReqService.rejectComments(assetRequest);
        System.out.println(response);
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @RequestMapping(value = "/assignAsset/{assetrequestId}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> assignAsset(
            @PathVariable Long assetrequestId,
            @RequestBody AssetRequest assetRequest) {
        Map<String, Object> response = (Map<String, Object>) addAssetReqService.assignAsset(assetRequest);
        System.out.println(response);
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @RequestMapping(value = "/adminComment/{assetrequestId}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> adminComment(
            @PathVariable Long assetrequestId,
            @RequestBody AssetRequest assetRequest) {
        Map<String, Object> response = (Map<String, Object>) addAssetReqService.adminComment(assetRequest);
        System.out.println(response);
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @RequestMapping(value = "/getpendingDelivery", method = RequestMethod.GET)
    public List<Map<String, Object>> getpendingDelivery() {
        List<Map<String, Object>> pendingDelivery = addAssetReqService.getpendingDelivery();
        Map<String, Object> response = new HashMap<>();

        if (pendingDelivery.isEmpty()){
            response.put("status", "error");
            response.put("message", "no pending delivery found");
        }
        else{
            response.put("status", "success");
            response.put("message", "all pending delivery displayed");
        }
        return pendingDelivery;
    }

    @RequestMapping(value = "/getcompletedDelivery", method = RequestMethod.GET)
    public List<Map<String, Object>> getcompletedDelivery() {
        List<Map<String, Object>> completedDelivery = addAssetReqService.getcompletedDelivery();
        Map<String, Object> response = new HashMap<>();

        if (completedDelivery.isEmpty()){
            response.put("status", "error");
            response.put("message", "no completed delivery found");
        }
        else{
            response.put("status", "success");
            response.put("message", "all completed delivery displayed");
        }
        return completedDelivery;
    }

}
