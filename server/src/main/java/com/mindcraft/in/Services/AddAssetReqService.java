package com.mindcraft.in.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mindcraft.in.pojos.AddAssetReqView;
import com.mindcraft.in.pojos.AssetRequest;

@Service
public class AddAssetReqService {

    public final JdbcTemplate jdbcTemplate;

    public AddAssetReqService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> insertAssetReq(AddAssetReqView addAssetReqView) {
        String sql = "insert into asset_request(asset_category, emp_id, project_code, request_type, request_comments, delivery_location, delivery_comments, request_status, approver_status, active_yn, created_on, updated_on) values(?,?,?,?,?,?,?,'PENDING','PENDING','Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

        int result = jdbcTemplate.update(sql,
                addAssetReqView.getAsset_category(),
                addAssetReqView.getEmp_id(),
                addAssetReqView.getProject_code(),
                addAssetReqView.getRequest_type(),
                addAssetReqView.getRequest_comments(),
                addAssetReqView.getDelivery_location(),
                addAssetReqView.getDelivery_comments());

        System.out.println(result);
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("status", "success");
            response.put("message", "asset request inserted successfuly");
        } else {
            response.put("status", "error");
            response.put("message", "Failed to insert asset request");
        }
        return response;
    }

    public Map<String, Object> checkComments(Long approvalId,AssetRequest assetRequest) {
        String sql = "Update asset_request SET request_status = 'APPROVED' , approver_status = 'APPROVED', allocation_type = ?, approver_days = ?, approver_comments = ?, approver_approved_on=CURRENT_TIMESTAMP, sysadmin_status = 'PENDING', approver_id = ? where asset_request_id = ?";
        int result = jdbcTemplate.update(sql,assetRequest.getAllocation_type(), assetRequest.getApprover_days(), assetRequest.getApprover_comments(),approvalId, assetRequest.getAsset_request_id());
        System.out.println("Approver ID: "+assetRequest.getApprover_id());
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("status", "success");
            response.put("message", "Asset request Updated successfuly");
        } else {
            response.put("status", "error");
            response.put("message", "Failed to update asset request");
        }
        return response;
    }

    public Map<String, Object> rejectComments(AssetRequest assetRequest) {
        String sql = "Update asset_request SET request_status = 'REJECTED', approver_status = 'REJECTED', approver_comments = ?, approver_approved_on=CURRENT_TIMESTAMP where asset_request_id=?";
        int result = jdbcTemplate.update(sql, assetRequest.getApprover_comments(), assetRequest.getAsset_request_id());
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("status", "success");
            response.put("message", "Asset request Rejected successfuly");
        } else {
            response.put("status", "error");
            response.put("message", "Failed to update asset request comments");
        }
        return response;
    }

    public Map<String, Object> assignAsset(AssetRequest assetRequest) {
        String sql = "Update asset_request SET asset_code = ?, sysadmin_approved_on=CURRENT_TIMESTAMP, request_status = 'ASSIGNING', sysadmin_status = 'ASSIGNING', admin_status = 'PENDING' where asset_request_id=?";
        int result = jdbcTemplate.update(sql, assetRequest.getAsset_code(), assetRequest.getAsset_request_id());
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("status", "success");
            response.put("message", "Asset Code Assigned successfully");
        } else {
            response.put("status", "error");
            response.put("message", "Failed to assign asset code");
        }
        return response;
    }

    public Map<String, Object> adminComment(AssetRequest assetRequest) {
        String sql = "Update asset_request SET admin_comments = ?, admin_approved_on=CURRENT_TIMESTAMP, request_status = 'DELIVERING', admin_status = 'DELIVERING'  where asset_request_id=?";
        int result = jdbcTemplate.update(sql, assetRequest.getAdmin_comments(), assetRequest.getAsset_request_id());
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("status", "success");
            response.put("message", "Admin Comment added successfuly");
        } else {
            response.put("status", "error");
            response.put("message", "Failed to add admin comments");
        }
        return response;
    }

    public List<Map<String, Object>> getpendingDelivery() {
        String sql = "select * from delivery_view where asset_code IS NOT NULL and admin_comments IS NULL";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no delivery yet");
        } else {
            response.put("status", "success");
            response.put("message", "All deliveries displayed");
        }
        System.out.println(response);
        return result;
    }

    public List<Map<String, Object>> getcompletedDelivery() {
        String sql = "select * from delivery_view where admin_comments IS NOT NULL";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no comments yet");
        } else {
            response.put("status", "success");
            response.put("message", "All Admin Comments displayed");
        }
        System.out.println(response);
        return result;
    }
}
