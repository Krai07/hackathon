package com.mindcraft.in.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindcraft.in.services.UserAssetsService;

@RestController
public class UserAssetsController {
    
    private final UserAssetsService userAssetsService;

    public UserAssetsController(UserAssetsService userAssetsService){
        this.userAssetsService = userAssetsService;
    }

    @RequestMapping(value = "/getUserAssets", method = RequestMethod.GET)
    public List<Map<String, Object>> getUserAsset() {
        List<Map<String, Object>> userassets = userAssetsService.getUserAsset();
        Map<String, Object> response = new HashMap<>();
        
        if (userassets.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no user assets found");
        }
        else{
            response.put("status", "success");
            response.put("message", "all users's assets displayed");
        }
        return userassets;
    }
}
