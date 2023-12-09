package com.mindcraft.in.pojos;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "procurement")
public class AssetPurchase {

    @Id
    private int pro_id;

    @Column(nullable = false)
    private String asset_category;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String selected_vendor;

    @Column(nullable = false)
    private String payment_terms;

    @Column(nullable = false)
    private String payment_terms_comments;

    @Column(nullable = false)
    private Date procurement_date;

    @Column(nullable = false)
    private String procurement_desc;

    @Column(nullable = false)
    private Long total_value;

    @Column(nullable = false)
    private int raised_by_id;

    @Column(nullable = false)
    private String raised_comments;

    @Column(nullable = false)
    private Date raised_date;

    @Column(nullable = false)
    private int approved_by_id;

    @Column(nullable = false)
    private String approved_comments;

    @Column(nullable = false)
    private Date approved_date;

    public AssetPurchase(String asset_category, int quantity, String selected_vendor, String payment_terms,
            String payment_terms_comments, Date procurement_date, String procurement_desc, Long total_value,
            int raised_by_id,
            String raised_comments, Date raised_date, int approved_by_id, String approved_comments,
            Date approved_date) {
        this.asset_category = asset_category;
        this.quantity = quantity;
        this.selected_vendor = selected_vendor;
        this.payment_terms = payment_terms;
        this.payment_terms_comments = payment_terms_comments;
        this.procurement_date = procurement_date;
        this.procurement_desc = procurement_desc;
        this.total_value = total_value;
        this.raised_by_id = raised_by_id;
        this.raised_comments = raised_comments;
        this.raised_date = raised_date;
        this.approved_by_id = approved_by_id;
        this.approved_comments = approved_comments;
        this.approved_date = approved_date;
    }

    public AssetPurchase() {
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getProcurement_desc() {
        return procurement_desc;
    }

    public void setProcurement_desc(String procurement_desc) {
        this.procurement_desc = procurement_desc;
    }

    public String getAsset_category() {
        return asset_category;
    }

    public void setAsset_category(String asset_category) {
        this.asset_category = asset_category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSelected_vendor() {
        return selected_vendor;
    }

    public void setSelected_vendor(String selected_vendor) {
        this.selected_vendor = selected_vendor;
    }

    public String getPayment_terms() {
        return payment_terms;
    }

    public void setPayment_terms(String payment_terms) {
        this.payment_terms = payment_terms;
    }

    public String getPayment_terms_comments() {
        return payment_terms_comments;
    }

    public void setPayment_terms_comments(String payment_terms_comments) {
        this.payment_terms_comments = payment_terms_comments;
    }

    public Date getProcurement_date() {
        return procurement_date;
    }

    public void setProcurement_date(Date procurement_date) {
        this.procurement_date = procurement_date;
    }

    public Long getTotal_value() {
        return total_value;
    }

    public void setTotal_value(Long total_value) {
        this.total_value = total_value;
    }

    public int getRaised_by_id() {
        return raised_by_id;
    }

    public void setRaised_by_id(int raised_by_id) {
        this.raised_by_id = raised_by_id;
    }

    public String getRaised_comments() {
        return raised_comments;
    }

    public void setRaised_comments(String raised_comments) {
        this.raised_comments = raised_comments;
    }

    public Date getRaised_date() {
        return raised_date;
    }

    public void setRaised_date(Date raised_date) {
        this.raised_date = raised_date;
    }

    public int getApproved_by_id() {
        return approved_by_id;
    }

    public void setApproved_by_id(int approved_by_id) {
        this.approved_by_id = approved_by_id;
    }

    public String getApproved_comments() {
        return approved_comments;
    }

    public void setApproved_comments(String approved_comments) {
        this.approved_comments = approved_comments;
    }

    public Date getApproved_date() {
        return approved_date;
    }

    public void setApproved_date(Date approved_date) {
        this.approved_date = approved_date;
    }

}
