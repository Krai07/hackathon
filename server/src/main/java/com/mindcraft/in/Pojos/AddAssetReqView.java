package com.mindcraft.in.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "asset_request")
public class AddAssetReqView {

    @Id
    @Column(name = "asset_category")
    private String asset_category;

    @Column(name = "emp_id")
    private long emp_id;

    @Column(name = "project_code")
    private String project_code;

    @Column(name = "request_type")
    private String request_type;

    @Column(name = "request_comments")
    private String request_comments;

    @Column(name = "delivery_location")
    private String delivery_location;

    @Column(name = "delivery_comments")
    private String delivery_comments;

    public AddAssetReqView(){

    }

    public AddAssetReqView(String asset_category, long emp_id, String project_code, String request_type,
            String request_comments, String delivery_location, String delivery_comments) {
        this.asset_category = asset_category;
        this.emp_id = emp_id;
        this.project_code = project_code;
        this.request_type = request_type;
        this.request_comments = request_comments;
        this.delivery_location = delivery_location;
        this.delivery_comments = delivery_comments;
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

    

    
}
