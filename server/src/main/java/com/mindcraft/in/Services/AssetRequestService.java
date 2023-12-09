package com.mindcraft.in.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AssetRequestService {

    private final JdbcTemplate jdbcTemplate;

    public AssetRequestService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAssetStatus() {
        String sql = "Select * from asset_request";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Fail to load Asset Request Displayed");
        } else {
            response.put("status", "success");
            response.put("message", "All Asset Request Displayed");
        }

        return result;
    }
    
    public List<Map<String, Object>> getDeliveringAssets() {
        String sql = "Select * from asset_request where request_status = 'DELIVERING'";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Fail to load Asset Request Status DELIVERING");
        } else {
            response.put("status", "success");
            response.put("message", "All Asset Request Status DELIVERING Displayed");
        }

        return result;
    }
    
    public Map<String, Object> updateAssetAllocation(Long asset_request_id) {
        String sql = "UPDATE asset_request set request_status = 'ALLOCATED', admin_status = 'DELIVERED', assigned_on=CURRENT_TIMESTAMP where asset_request_id = ?";
        int result = jdbcTemplate.update(sql, asset_request_id);
        Map<String, Object> response = new HashMap<>();

        if (result < 0) {
            response.put("status", "error");
            response.put("message", "Fail to load Asset Request Status DELIVERING");
        } else {
            String getassetcode = "select asset_code from asset_request where asset_request_id = ?";
            String asset_code = jdbcTemplate.queryForObject(getassetcode, String.class, asset_request_id);
            String updateState = "UPDATE m_assets SET state = 'inuse', sub_state = 'assigned', sub_state1 = 'working' where asset_code = ?";
            int updateResult = jdbcTemplate.update(updateState, asset_code);
            if(updateResult > 0) {
                response.put("asset_state", "Updated");
                response.put("message", "Asset State Updated");
                response.put("status", "success");
                response.put("message", "All Asset Request Status DELIVERING Displayed");
            } else {
                response.put("status", "error");
                response.put("message", "Asset State failed to update!");
            }
        }
        return response;
    }

    public List<Map<String, Object>> getAssetRequest() {
        String sql = "select * from assign_asset where (request_type = 'NEW' OR request_type = 'ONBEHALF') AND approver_status = 'APPROVED' AND asset_code IS NULL";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();
        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no requests found");

        } else {
            response.put("status", "success");
            response.put("message", "all asset requests displayed");
        }
        System.out.println(response);
        return result;
    }

    public int getAssetLostCount() {
        String sql = "select COUNT(*) from asset_request where request_type = 'LOST'";
        int lostAsset = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("Total lost asset count: "+lostAsset);
        return lostAsset;
    }

    public List<Map<String, Object>> getLostAssets() {
        String sql = "select * from asset_request where request_type = 'LOST'";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();
        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no lost assets found");
        }
        else{
            response.put("status", "success");
            response.put("message", "lost assets found");
        }
        System.out.println(response);
        return result;
    }
}
