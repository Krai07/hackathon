package com.mindcraft.in.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AssetApprovedManagerService {
    
    private final JdbcTemplate jdbcTemplate;

    public AssetApprovedManagerService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Map<String, Object>> getApprovedAssets() {
        String sql = "select * from assets_approved_view where approver_comments IS NOT NULL";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no assets approved");
            
        } else{
            response.put("status", "success");
            response.put("message", "all approved assets displayed");
        }
        System.out.println(response);
        return result;
    }
}
