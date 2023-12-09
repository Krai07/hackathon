package com.mindcraft.in.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AssetApprovalManagerService {

    private final JdbcTemplate jdbcTemplate;

    public AssetApprovalManagerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAssetApproval() {
        String sql = "select * from asset_approval_manager where (request_type = 'NEW' OR request_type = 'ONBEHALF') AND approver_comments IS NULL";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no approvals yet");
        } else {
            response.put("status", "success");
            response.put("message", "All approvals displayed");
        }
        System.out.println(response);
        return result;
    }

    
}
