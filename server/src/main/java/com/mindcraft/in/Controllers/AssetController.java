package com.mindcraft.in.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindcraft.in.pojos.AssetInsert;
import com.mindcraft.in.pojos.ProcurementView;
import com.mindcraft.in.services.AssetService;

@RestController
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @RequestMapping(value = "/getAssets", method = RequestMethod.GET)
    public List<Map<String, Object>> getAsset() {
        List<Map<String, Object>> assets = assetService.getAsset();
        Map<String, Object> response = new HashMap<>();
        if (assets.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no assets found");
        } else {
            response.put("status", "success");
            response.put("message", "all assets displayed");
        }
        return assets;
    }

    @RequestMapping(value = "/getAssetCode", method = RequestMethod.GET)
    public List<Map<String, Object>> getAssetCode() {
        List<Map<String, Object>> assets = assetService.getAssetCode();
        // List<String> asset_code = new ArrayList<String>();
        System.out.println(assets.size());

        // for(int i=0; i < assets.size(); i++) {
        //     String assetCode = (String) assets.get(i).get("asset_code"); 
        //     asset_code.add(assetCode);
        // }

        // System.out.println(asset_code);
        Map<String, Object> response = new HashMap<>();
        if (assets.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no assets code found");
        } else {
            response.put("status", "success");
            response.put("message", "all asset codes with filters displayed");
        }
        System.out.println(response);
        return assets;
    }

    @RequestMapping(value = "/insertAsset", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> insertAsset(@RequestBody AssetInsert assetInsert) {
        Map<String, String> response = (Map<String, String>) assetService.insertAssetRecord(assetInsert);
        System.out.println(response);
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @RequestMapping(value = "/getProcurementDetails/{procurementId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getProcurementDetails(@PathVariable Long procurementId) {
        Map<String, Object> response = new HashMap<>();
        try {
            ProcurementView procurementView = assetService.getProcurementDetails(procurementId);
            response.put("status", "success");
            response.put("vendorName", procurementView.getSelected_vendor());
            response.put("procurementDate", procurementView.getProcurement_date());
            response.put("procurementId", procurementView.getProcurement_id());
            response.put("assetCategory", procurementView.getAsset_category());
        } catch (EmptyResultDataAccessException e) {
            response.put("status", "error");
            response.put("message", "No procurement details found for the given ID");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to fetch procurement details");
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/checkAsset/{assetCode}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> checkAsset(@PathVariable String assetCode) {
        Boolean checkAsset = assetService.checkAsset(assetCode);
        Map<String, Object> response = new HashMap<>();
        if (!checkAsset) {
            response.put("status", "error");
            response.put("message", "No Asset Code found");
        } else {
            response.put("status", "success");
            response.put("message", "Asset Code Found");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/getAssetCount", method = RequestMethod.GET)
    public ResponseEntity<Integer> getTotalAssetCount() {
        int totalCount = assetService.getTotalAssetCount();
        return ResponseEntity.ok(totalCount);
    }
    
    @RequestMapping(value = "/getUnassignedCount", method = RequestMethod.GET)
    public ResponseEntity<Integer> getUnassignedAssets() {
        int UnassignedCount = assetService.getUnassignedAssets();
        return ResponseEntity.ok(UnassignedCount);
    }

    @RequestMapping(value = "/getDamagedCount", method = RequestMethod.GET)
    public ResponseEntity<Integer> getDamagedAssets() {
        int DamagedCount = assetService.getDamagedAssets();
        return ResponseEntity.ok(DamagedCount);
    }

    @RequestMapping(value = "/displayAllAssets", method = RequestMethod.GET)
    public List<Map<String, Object>> displayTotalAssets(){
        List<Map<String, Object>> request = assetService.displayTotalAssets();
        Map<String, Object> response = new HashMap<>();

        if (request.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no assets found");
        }
        else{
            response.put("status", "success");
            response.put("message", "Total assets displayed");
        }
        return request;
    }

    @RequestMapping(value = "/displayUnassignedAssets", method = RequestMethod.GET)
    public List<Map<String, Object>> displayUnassignedAssets(){
        List<Map<String, Object>> request = assetService.displayUnassignedAssets();
        Map<String, Object> response = new HashMap<>();

        if (request.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no unassigned assets found");
        }
        else{
            response.put("status", "success");
            response.put("message", "Unassigned assets displayed");
        }
        return request;
    }

    @RequestMapping(value = "/displayDamagedAssets", method = RequestMethod.GET)
    public List<Map<String, Object>> displayDamagedAssets(){
        List<Map<String, Object>> request = assetService.displayDamagedAssets();
        Map<String, Object> response = new HashMap<>();

        if (request.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no damaged assets found");
        }
        else{
            response.put("status", "success");
            response.put("message", "Damaged assets displayed");
        }
        return request;
    }

    @RequestMapping(value = "/getAssignedCount", method = RequestMethod.GET)
    public ResponseEntity<Integer> getAssignedAssetsCount() {
        int AssignedCount = assetService.getAssignedAssetsCount();
        return ResponseEntity.ok(AssignedCount);
    }

    @RequestMapping(value = "/displayAssignedAssets", method = RequestMethod.GET)
    public List<Map<String, Object>> displayAssignedAssets(){
        List<Map<String, Object>> request = assetService.displayAssignedAssets();
        Map<String, Object> response = new HashMap<>();

        if (request.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no assigned assets found");
        }else{
            response.put("status", "success");
            response.put("message", "Assigned assets displayed");
        }
        return request;
    }
}
