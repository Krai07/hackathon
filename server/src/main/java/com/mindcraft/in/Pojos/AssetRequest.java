package com.mindcraft.in.pojos;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "asset_request")
public class AssetRequest {
    
    @Id
    @Column(name = "asset_request_id")
    private long asset_request_id;

    @Column(name = "asset_category")
    private String asset_category;

    @Column(name = "emp_id")
    private long emp_id;

    @Column(name = "project_code")
    private String project_code;

    @Column(name = "request_type")
    private String request_type;

    @Column(name = "asset_id")
    private long asset_id;

    @Column(name = "request_status")
    private String request_status;

    @Column(name = "request_comments")
    private String request_comments;

    @Column(name = "delivery_location")
    private String delivery_location;

    @Column(name = "delivery_comments")
    private String delivery_comments;

    @Column(name = "approver_role")
    private String approver_role;

    @Column(name = "approver_id")
    private long approvalId;

    @Column(name = "approver_status")
    private String approver_status;

    @Column(name = "approver_comments")
    private String approver_comments;

    @Column(name = "allocation_type")
    private String allocation_type;

    @Column(name = "approver_days")
    private long approver_days;

    @Column(name = "approver_assigned_on")
    private Date approver_assigned_on;

    @Column(name = "approver_approved_on")
    private Date approver_approved_on;

    @Column(name = "admin_id")
    private long admin_id;

    @Column(name = "admin_status")
    private String admin_status;

    @Column(name = "admin_comments")
    private String admin_comments;

    @Column(name = "admin_assigned_on")
    private Date admin_assigned_on;

    @Column(name = "admin_approved_on")
    private Date admin_approved_on;

    @Column(name = "assigned_asset_id")
    private long assigned_asset_id;   // The Already Assigned Asset

    @Column(name = "sysadmin_id")
    private long sysadmin_id;

    @Column(name = "sysadmin_status")
    private String sysadmin_status;

    @Column(name = "sysadmin_comments")
    private String sysadmin_comments;

    @Column(name = "sysadmin_assigned_on")
    private Date sysadmin_assigned_on;

    @Column(name = "sysadmin_approved_on")
    private Date sysadmin_approved_on;

    @Column(name = "asset_code")
    private String asset_code;

    @Column(name = "active_yn")
    private String active_yn;

    @Column(name = "created_by")
    private String created_by;
    
    @Column(name = "created_on")
    private java.sql.Timestamp created_on;
    
    @Column(name = "updated_by")
    private String updated_by;

    @Column(name = "updated_on")
    private java.sql.Timestamp updated_on;

    public AssetRequest() {
    }

    public AssetRequest(long asset_request_id, String asset_category, long emp_id, String project_code,
            String request_type, long asset_id, String request_status, String request_comments,
            String delivery_location, String delivery_comments, String approver_role, long approvalId,
            String approver_status, String approver_comments, String allocation_type, long approver_days, Date approver_assigned_on, Date approver_approved_on,
            long admin_id, String admin_status, String admin_comments, Date admin_assigned_on, Date admin_approved_on,
            long assigned_asset_id, long sysadmin_id, String sysadmin_status, String sysadmin_comments,
            Date sysadmin_assigned_on, Date sysadmin_approved_on, String asset_code, String active_yn,
            String created_by, java.sql.Timestamp created_on, String updated_by, java.sql.Timestamp updated_on) {
        this.asset_request_id = asset_request_id;
        this.asset_category = asset_category;
        this.emp_id = emp_id;
        this.project_code = project_code;
        this.request_type = request_type;
        this.asset_id = asset_id;
        this.request_status = request_status;
        this.request_comments = request_comments;
        this.delivery_location = delivery_location;
        this.delivery_comments = delivery_comments;
        this.approver_role = approver_role;
        this.approvalId = approvalId;
        this.approver_status = approver_status;
        this.approver_comments = approver_comments;
        this.allocation_type = allocation_type;
        this.approver_days = approver_days;
        this.approver_assigned_on = approver_assigned_on;
        this.approver_approved_on = approver_approved_on;
        this.admin_id = admin_id;
        this.admin_status = admin_status;
        this.admin_comments = admin_comments;
        this.admin_assigned_on = admin_assigned_on;
        this.admin_approved_on = admin_approved_on;
        this.assigned_asset_id = assigned_asset_id;
        this.sysadmin_id = sysadmin_id;
        this.sysadmin_status = sysadmin_status;
        this.sysadmin_comments = sysadmin_comments;
        this.sysadmin_assigned_on = sysadmin_assigned_on;
        this.sysadmin_approved_on = sysadmin_approved_on;
        this.asset_code = asset_code;
        this.active_yn = active_yn;
        this.created_by = created_by;
        this.created_on = created_on;
        this.updated_by = updated_by;
        this.updated_on = updated_on;
    }

    public String getAllocation_type() {
        return allocation_type;
    }

    public void setAllocation_type(String allocation_type) {
        this.allocation_type = allocation_type;
    }

    public long getApprover_days() {
        return approver_days;
    }

    public void setApprover_days(long approver_days) {
        this.approver_days = approver_days;
    }

    public long getAsset_request_id() {
        return asset_request_id;
    }

    public void setAsset_request_id(long asset_request_id) {
        this.asset_request_id = asset_request_id;
    }

    public String getAsset_category() {
        return asset_category;
    }

    public void setAsset_category(String asset_category) {
        this.asset_category = asset_category;
    }

    public long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(long emp_id) {
        this.emp_id = emp_id;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public long getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(long asset_id) {
        this.asset_id = asset_id;
    }

    public String getRequest_status() {
        return request_status;
    }

    public void setRequest_status(String request_status) {
        this.request_status = request_status;
    }

    public String getRequest_comments() {
        return request_comments;
    }

    public void setRequest_comments(String request_comments) {
        this.request_comments = request_comments;
    }

    public String getDelivery_location() {
        return delivery_location;
    }

    public void setDelivery_location(String delivery_location) {
        this.delivery_location = delivery_location;
    }

    public String getDelivery_comments() {
        return delivery_comments;
    }

    public void setDelivery_comments(String delivery_comments) {
        this.delivery_comments = delivery_comments;
    }

    public String getApprover_role() {
        return approver_role;
    }

    public void setApprover_role(String approver_role) {
        this.approver_role = approver_role;
    }

    public long getApprover_id() {
        return approvalId;
    }

    public void setApprover_id(long approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprover_status() {
        return approver_status;
    }

    public void setApprover_status(String approver_status) {
        this.approver_status = approver_status;
    }

    public String getApprover_comments() {
        return approver_comments;
    }

    public void setApprover_comments(String approver_comments) {
        this.approver_comments = approver_comments;
    }

    

    public Date getApprover_assigned_on() {
        return approver_assigned_on;
    }

    public void setApprover_assigned_on(Date approver_assigned_on) {
        this.approver_assigned_on = approver_assigned_on;
    }

    public Date getApprover_approved_on() {
        return approver_approved_on;
    }

    public void setApprover_approved_on(Date approver_approved_on) {
        this.approver_approved_on = approver_approved_on;
    }

    public long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(long admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_status() {
        return admin_status;
    }

    public void setAdmin_status(String admin_status) {
        this.admin_status = admin_status;
    }

    public String getAdmin_comments() {
        return admin_comments;
    }

    public void setAdmin_comments(String admin_comments) {
        this.admin_comments = admin_comments;
    }

    public Date getAdmin_assigned_on() {
        return admin_assigned_on;
    }

    public void setAdmin_assigned_on(Date admin_assigned_on) {
        this.admin_assigned_on = admin_assigned_on;
    }

    public Date getAdmin_approved_on() {
        return admin_approved_on;
    }

    public void setAdmin_approved_on(Date admin_approved_on) {
        this.admin_approved_on = admin_approved_on;
    }

    public long getAssigned_asset_id() {
        return assigned_asset_id;
    }

    public void setAssigned_asset_id(long assigned_asset_id) {
        this.assigned_asset_id = assigned_asset_id;
    }

    public long getSysadmin_id() {
        return sysadmin_id;
    }

    public void setSysadmin_id(long sysadmin_id) {
        this.sysadmin_id = sysadmin_id;
    }

    public String getSysadmin_status() {
        return sysadmin_status;
    }

    public void setSysadmin_status(String sysadmin_status) {
        this.sysadmin_status = sysadmin_status;
    }

    public String getSysadmin_comments() {
        return sysadmin_comments;
    }

    public void setSysadmin_comments(String sysadmin_comments) {
        this.sysadmin_comments = sysadmin_comments;
    }

    public Date getSysadmin_assigned_on() {
        return sysadmin_assigned_on;
    }

    public void setSysadmin_assigned_on(Date sysadmin_assigned_on) {
        this.sysadmin_assigned_on = sysadmin_assigned_on;
    }

    public Date getSysadmin_approved_on() {
        return sysadmin_approved_on;
    }

    public void setSysadmin_approved_on(Date sysadmin_approved_on) {
        this.sysadmin_approved_on = sysadmin_approved_on;
    }

    public String getAsset_code() {
        return asset_code;
    }

    public void setAsset_code(String asset_code) {
        this.asset_code = asset_code;
    }

    public String getActive_yn() {
        return active_yn;
    }

    public void setActive_yn(String active_yn) {
        this.active_yn = active_yn;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public java.sql.Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(java.sql.Timestamp created_on) {
        this.created_on = created_on;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public java.sql.Timestamp getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(java.sql.Timestamp updated_on) {
        this.updated_on = updated_on;
    }


    
}
