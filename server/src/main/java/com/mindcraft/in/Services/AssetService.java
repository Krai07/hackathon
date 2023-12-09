package com.mindcraft.in.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mindcraft.in.pojos.AssetInsert;
import com.mindcraft.in.pojos.AssetPurchase;
import com.mindcraft.in.pojos.AssetView;
import com.mindcraft.in.pojos.ProcurementView;

@Service
public class AssetService {

    private final JdbcTemplate jdbcTemplate;

    public AssetService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAsset() {
        String sql = "select * from asset_status_view";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no assets found");

        } else {
            response.put("status", "success");
            response.put("message", "All assets displayed");
        }
        System.out.println(response);
        return result;
    }

    public List<Map<String, Object>> getAssetCode() {
        String sql = "select asset_code, processor, ram, hdd_ssd, os from m_assets where state = 'instock' and sub_state = 'available' and sub_state1 = 'working'";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no asset code found.");

        } else {
            response.put("status", "success");
            response.put("message", "All assets Code displayed.");
        }
        System.out.println(response);
        return result;
    }

    public Map<String, String> insertAssetPurchaseRecord(AssetPurchase assetPurchase) {
        String sql = "insert into procurement(asset_category,quantity,remaining_quantity,selected_vendor,payment_terms,payment_terms_comments,procurement_date,procurement_desc,total_value,raised_by_id,raised_comments,raised_date,approved_by_id,approved_comments,approved_date,active_yn,created_by,created_on,updated_by,updated_on) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Y','Admin',CURRENT_TIMESTAMP,'Admin',CURRENT_TIMESTAMP)";
        int result = jdbcTemplate.update(sql,
                assetPurchase.getAsset_category(),
                assetPurchase.getQuantity(),
                assetPurchase.getQuantity(),
                assetPurchase.getSelected_vendor(),
                assetPurchase.getPayment_terms(),
                assetPurchase.getPayment_terms_comments(),
                assetPurchase.getProcurement_date(),
                assetPurchase.getProcurement_desc(),
                assetPurchase.getTotal_value(),
                assetPurchase.getRaised_by_id(),
                assetPurchase.getRaised_comments(),
                assetPurchase.getRaised_date(),
                assetPurchase.getApproved_by_id(),
                assetPurchase.getApproved_comments(),
                assetPurchase.getApproved_date());

        System.out.println(assetPurchase.getAsset_category());

        System.out.println(result);
        Map<String, String> response = new HashMap<>();

        if (result > 0) {
            response.put("status", "success");
            response.put("message", "Procurement Details Inserted Successfully.");
        } else {
            response.put("status", "error");
            response.put("message", "Error while Inserting Procurement Details.");
        }
        return response;
    }

    public Map<String, String> insertAssetRecord(AssetInsert assetInsert) {

        String assetCode = generateAssetCode(assetInsert.getAsset_category());

        String displayName = generateDisplayName(assetInsert.getManufacturer(), assetInsert.getProduct(),
                assetInsert.getModel());

        String sql = "insert into m_assets(asset_code,asset_category,manufacturer,product,model,serial_number,watts,display_name,end_of_support,end_of_extended_support,state,sub_state,procurement_id,with_antivirus_yn,with_msoffice_yn,with_charger_yn,with_laptop_bag_yn,processor,ram,hdd_ssd,licence_type,os,sub_state1,with_os_yn,active_yn,created_by,created_on,updated_by,updated_on) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Y','Admin',CURRENT_TIMESTAMP,'Admin',CURRENT_TIMESTAMP)";
        int result = jdbcTemplate.update(sql, 
                    assetCode,
                    assetInsert.getAsset_category(),
                    assetInsert.getManufacturer(),
                    assetInsert.getProduct(),
                    assetInsert.getModel(),
                    assetInsert.getSerial_number(),
                    assetInsert.getWatts(),
                    displayName,
                    assetInsert.getEnd_of_support(),
                    assetInsert.getEnd_of_extended_support(),
                    assetInsert.getState(),
                    assetInsert.getSub_state(),
                    assetInsert.getProcurement_id(),
                    assetInsert.getWith_antivirus_yn(),
                    assetInsert.getWith_msoffice_yn(),
                    assetInsert.getWith_charger_yn(),
                    assetInsert.getWith_laptop_bag_yn(),
                    assetInsert.getProcessor(),
                    assetInsert.getRam(),
                    assetInsert.getHdd_ssd(),
                    assetInsert.getLicence_type(),
                    assetInsert.getOs(),
                    assetInsert.getSub_state1(),
                    assetInsert.getWith_os());


        System.out.println("Extended support Date : "+assetInsert.getEnd_of_extended_support());
        Map<String, String> response = new HashMap<>();

        System.out.println(assetInsert.getSub_state());
        System.out.println(assetInsert.getSub_state1());

        if (result > 0) {
            String updateQuantity = "UPDATE procurement set remaining_quantity = remaining_quantity - 1 where procurement_id = ?";
            int updateResult = jdbcTemplate.update(updateQuantity, assetInsert.getProcurement_id());
            System.out.println(updateResult);
            if(updateResult > 0) {
                response.put("status", "success");
                response.put("message", "Asset added successfully");
                response.put("Procurement Update", "Quantity Updated!");
            } else {
                response.put("status", "error");
                response.put("message", "Failed to Update Procurement Quantity");
            }
        } else {
            response.put("status", "error");
            response.put("message", "Failed to add asset");
        }
        return response;
    }

    private String generateAssetCode(String assetCategory) {
        // Use jdbcTemplate.queryForObject to call the SQL function generate_asset_code
        return jdbcTemplate.queryForObject(
                "SELECT generate_asset_code(?)",
                String.class,
                assetCategory);
    }

    private String generateDisplayName(String manufacturer, String product, String model) {
        // Use jdbcTemplate.queryForObject to call the SQL function
        // generate_display_name
        return jdbcTemplate.queryForObject(
                "SELECT generate_display_name(?, ?, ?)",
                String.class,
                manufacturer,
                product,
                model);
    }

    public ProcurementView getProcurementDetails(Long procurementId) {
        String sql = "select * from procurement where procurement_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { procurementId }, (rs, rowNum) -> {
            ProcurementView procurementView = new ProcurementView(
                    rs.getString("asset_category"),
                    rs.getLong("quantity"),
                    rs.getString("selected_vendor"),
                    rs.getString("payment_terms"),
                    rs.getDate("procurement_date"),
                    rs.getInt("total_value"));
            procurementView.setProcurement_id(rs.getLong("procurement_id"));
            return procurementView;
        });
    }

    public Boolean checkAsset(String assetCode) {
        String sql = "select Count(*) > 0 from m_assets where asset_code = ? AND state='instock' and sub_state = 'available' and sub_state1 = 'working'";
        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, assetCode);
        Map<String, Object> response = new HashMap<>();
        if (!result) {
            response.put("status", "error");
            response.put("message", "no approvals yet");
        } else {
            response.put("status", "success");
            response.put("message", "All approvals displayed");
        }
        System.out.println(response);
        return jdbcTemplate.queryForObject(sql, Boolean.class, assetCode);
    }

    public int getTotalAssetCount() {
        String sql = "SELECT COUNT(*) FROM asset_status_view";
        int totalCount = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("Total asset count: " + totalCount);
        return totalCount;
    }

    public int getAssignedAssetsCount() {
        String sql = "select COUNT(*) from asset_status_view where state = 'INUSE'";
        int AssignedCount = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("Assigned Assets: "+AssignedCount);
        return AssignedCount;
    }

    public int getUnassignedAssets() {
        String sql = "select COUNT(*) from asset_status_view where state = 'INSTOCK'";
        int UnassignedCount = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("Unassigned assets: "+ UnassignedCount);
        return UnassignedCount;
    }

    public int getDamagedAssets() {
        String sql = "select COUNT(*) from asset_status_view where state = 'DAMAGED'";
        int DamagedCount = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("Damaged assets: "+DamagedCount);
        return DamagedCount;
    }

    public List<Map<String, Object>> displayTotalAssets() {
        String sql = "select * from asset_status_view";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no assets available");
        }
        else{
            response.put("status", "success");
            response.put("message", "total assets displayed");
        }
        System.out.println(response);
        return result;
    }

    public List<Map<String, Object>> displayUnassignedAssets() {
        String sql = "select * from asset_status_view where state = 'INSTOCK'";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no unassigned assets");
        }
        else{
            response.put("status", "success");
            response.put("message", "unassigned assets displayed");
        }
        System.out.println(response);
        return result;
    }

    public List<Map<String, Object>> displayDamagedAssets() {
        String sql = "select * from asset_status_view where state = 'DAMAGED'";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();

        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no damaged assets found/");
            
        }else{
            response.put("status","success");
            response.put("message", "damaged assets displayed");
        }
        System.out.println(response);
        return result;
    }

    public List<Map<String, Object>> displayAssignedAssets() {
        String sql = "select * from asset_status_view where state = 'INUSE'";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        Map<String, Object> response = new HashMap<>();
        if (result.isEmpty()) {
            response.put("status", "error");
            response.put("message", "no assigned assets found");
        }else{
            response.put("status", "success");
            response.put("message", "assigned assets displayed");
        }
        System.out.println(response);
        return result;
    }
}
