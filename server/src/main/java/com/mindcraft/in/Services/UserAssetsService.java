package com.mindcraft.in.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserAssetsService {
    
    private final JdbcTemplate jdbcTemplate;

    public UserAssetsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getUserAsset() {
        String sql = "select * from asset_info_view ";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no assets found");
            
        }else{
            response.put("status", "success");
            response.put("message", "user's assets displayed");
        }
        System.out.println(response);
        return result;
    }

}
