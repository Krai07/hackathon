package com.mindcraft.in.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindcraft.in.services.AssetApprovedManagerService;

@RestController
public class AssetsApprovedManagerController {
    
    private final AssetApprovedManagerService assetApprovedManagerService;

    public AssetsApprovedManagerController(AssetApprovedManagerService assetApprovedManagerService){
        this.assetApprovedManagerService = assetApprovedManagerService;
    }

    @RequestMapping(value = "/getApprovedAssets", method = RequestMethod.GET)
    public List<Map<String, Object>> getApprovedAssets() {
        List<Map<String, Object>> approvedAssets = assetApprovedManagerService.getApprovedAssets();
        Map<String, Object> response = new HashMap<>();

        if (approvedAssets.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no approved assets found");
        }
        else{
            response.put("status", "success");
            response.put("message", "all approved assets displayed");
        }
        return approvedAssets;

        
    }
}
