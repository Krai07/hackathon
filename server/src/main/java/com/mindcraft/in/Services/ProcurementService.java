package com.mindcraft.in.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProcurementService {
    
    private final JdbcTemplate jdbcTemplate;

    public ProcurementService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Map<String,Object>> getProcurement() {
        String sql = "select * from procurement_view where remaining_quantity > 0";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no orders found");
        } else {
            response.put("status", "success");
            response.put("message","orders displayed successfully");
        }
        System.out.println(response);
        return result;
    }
    
    public List<Map<String,Object>> getAddedProcurement() {
        String sql = "select * from procurement_view";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no orders found");
        } else {
            response.put("status", "success");
            response.put("message","orders displayed successfully");
        }
        System.out.println(response);
        return result;
    }
}
