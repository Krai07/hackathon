package com.mindcraft.in.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindcraft.in.services.AssetApprovalManagerService;

@RestController
public class AssetApprovalManagerController {
    
    private final AssetApprovalManagerService assetApprovalManagerService;

    public AssetApprovalManagerController(AssetApprovalManagerService assetApprovalManagerService){
        this.assetApprovalManagerService = assetApprovalManagerService;
    }

    @RequestMapping(value = "/getAssetsApproval", method = RequestMethod.GET)
    public List<Map<String, Object>> getAssetApproval() {
        List<Map<String, Object>> approvals = assetApprovalManagerService.getAssetApproval();
        Map<String, Object> response = new HashMap<>();

        if (approvals.isEmpty()){
            response.put("status", "error");
            response.put("message", "no approvals found");
        }
        else{
            response.put("status", "success");
            response.put("message", "all asset approvals displayed");
        }
        return approvals;
    }
}
