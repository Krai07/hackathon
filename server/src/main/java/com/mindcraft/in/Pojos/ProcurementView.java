package com.mindcraft.in.pojos;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProcurementView {
    
    @Id
    @Column(name="procurement_id")
    private Long procurement_id;
    
    @Column(name = "asset_category")
    private String asset_category;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "selected_vendor")
    private String selected_vendor;

    @Column(name = "payment_terms")
    private String payment_terms;

    @Column(name = "procurement_date")
    private Date procurement_date;

    @Column(name = "total_value")
    private int total_value;

    private ProcurementView(){

    }

    public ProcurementView(String asset_category, long quantity, String selected_vendor, String payment_terms,
            Date procurement_date, int total_value) {
        this.asset_category = asset_category;
        this.quantity = quantity;
        this.selected_vendor = selected_vendor;
        this.payment_terms = payment_terms;
        this.procurement_date = procurement_date;
        this.total_value = total_value;
    }

    public String getAsset_category() {
        return asset_category;
    }

    public Long getProcurement_id() {
        return procurement_id;
    }

    public void setProcurement_id(Long procurement_id) {
        this.procurement_id = procurement_id;
    }

    public void setAsset_category(String asset_category) {
        this.asset_category = asset_category;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getSelected_vendor() {
        return selected_vendor;
    }

    public void setSelected_vendor(String selected_vendor) {
        this.selected_vendor = selected_vendor;
    }

    public int getTotal_value() {
        return total_value;
    }

    public void setTotal_value(int total_value) {
        this.total_value = total_value;
    }

    public String getPayment_terms() {
        return payment_terms;
    }

    public void setPayment_terms(String payment_terms) {
        this.payment_terms = payment_terms;
    }

    public Date getProcurement_date() {
        return procurement_date;
    }

    public void setProcurement_date(Date procurement_date) {
        this.procurement_date = procurement_date;
    }
    
    
    
}
