package com.mindcraft.in.pojos;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AssetView {
    @Id
    @Column(name = "asset_id")
    private int asset_id;

    @Column(name = "asset_code")
    private String asset_code;

    @Column(name = "asset_category")
    private String asset_category;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "product")
    private String product;

    @Column(name = "model")
    private String model;

    @Column(name = "serial_number")
    private String serial_number;

    @Column(name = "watts")
    private String watts;

    @Column(name = "display_name")
    private String display_name;

    @Column(name = "end_of_support")
    private Date end_of_support;

    @Column(name = "end_of_extended_support")
    private Date end_of_extended_support;

    @Column(name = "end_of_life")
    private Date end_of_life;

    @Column(name = "state")
    private String state;

    @Column(name = "sub_state")
    private String sub_state;

    @Column(name = "sub_state1")
    private String sub_state1;

    @Column(name = "procurement_id")
    private long procurement_id;

    @Column(name = "purchase_price")
    private int purchase_price;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "with_os_yn")
    private String with_os_yn;

    @Column(name = "with_antivirus_yn")
    private String with_antivirus_yn;

    @Column(name = "with_msoffice_yn")
    private String with_msoffice_yn;

    @Column(name = "with_charger_yn")
    private String with_charger_yn;

    @Column(name = "with_laptop_bag_yn")
    private String with_laptop_bag_yn;

    @Column(name = "parent_asset_id")
    private long parent_asset_id;

    @Column(name = "processor")
    private String processor;

    @Column(name = "ram")
    private String ram;

    @Column(name = "hdd_ssd")
    private String hdd_ssd;

    @Column(name = "licence_type")
    private String licence_type;

    @Column(name = "os")
    private String os;

    @Column(name = "active_yn")
    private String active_yn;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "created_on")
    private Date created_on;

    @Column(name = "updated_by")
    private String updated_by;

    @Column(name = "updated_on")
    private Date updated_on;

    private AssetView(){

    }

    public AssetView(int asset_id, String asset_code, String asset_category, String manufacturer, String product,
            String model, String serial_number, String watts, String display_name, Date end_of_support, Date end_of_extended_support,
            Date end_of_life, String state, String sub_state, String sub_state1, long procurement_id, int purchase_price, String vendor,
            String with_os_yn, String with_antivirus_yn, String with_msoffice_yn, String with_charger_yn,
            String with_laptop_bag_yn, long parent_asset_id, String processor, String ram, String hdd_ssd,
            String licence_type, String os, String active_yn, String created_by, Date created_on, String updated_by,
            Date updated_on) {
        this.asset_id = asset_id;
        this.asset_code = asset_code;
        this.asset_category = asset_category;
        this.manufacturer = manufacturer;
        this.product = product;
        this.model = model;
        this.serial_number = serial_number;
        this.watts = watts;
        this.display_name = display_name;
        this.end_of_support = end_of_support;
        this.end_of_extended_support = end_of_extended_support;
        this.end_of_life = end_of_life;
        this.state = state;
        this.sub_state = sub_state;
        this.sub_state1 = sub_state1;
        this.procurement_id = procurement_id;
        this.purchase_price = purchase_price;
        this.vendor = vendor;
        this.with_os_yn = with_os_yn;
        this.with_antivirus_yn = with_antivirus_yn;
        this.with_msoffice_yn = with_msoffice_yn;
        this.with_charger_yn = with_charger_yn;
        this.with_laptop_bag_yn = with_laptop_bag_yn;
        this.parent_asset_id = parent_asset_id;
        this.processor = processor;
        this.ram = ram;
        this.hdd_ssd = hdd_ssd;
        this.licence_type = licence_type;
        this.os = os;
        this.active_yn = active_yn;
        this.created_by = created_by;
        this.created_on = created_on;
        this.updated_by = updated_by;
        this.updated_on = updated_on;
    }

    public int getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(int asset_id) {
        this.asset_id = asset_id;
    }

    public String getAsset_code() {
        return asset_code;
    }

    public void setAsset_code(String asset_code) {
        this.asset_code = asset_code;
    }

    public String getAsset_category() {
        return asset_category;
    }

    public void setAsset_category(String asset_category) {
        this.asset_category = asset_category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public Date getEnd_of_support() {
        return end_of_support;
    }

    public void setEnd_of_support(Date end_of_support) {
        this.end_of_support = end_of_support;
    }

    public Date getEnd_of_extended_support() {
        return end_of_extended_support;
    }

    public void setEnd_of_extended_support(Date end_of_extended_support) {
        this.end_of_extended_support = end_of_extended_support;
    }

    public Date getEnd_of_life() {
        return end_of_life;
    }

    public void setEnd_of_life(Date end_of_life) {
        this.end_of_life = end_of_life;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSub_state() {
        return sub_state;
    }

    public void setSub_state(String sub_state) {
        this.sub_state = sub_state;
    }

    public long getProcurement_id() {
        return procurement_id;
    }

    public void setProcurement_id(long procurement_id) {
        this.procurement_id = procurement_id;
    }

    public int getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(int purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getWith_os_yn() {
        return with_os_yn;
    }

    public void setWith_os_yn(String with_os_yn) {
        this.with_os_yn = with_os_yn;
    }

    public String getWith_antivirus_yn() {
        return with_antivirus_yn;
    }

    public void setWith_antivirus_yn(String with_antivirus_yn) {
        this.with_antivirus_yn = with_antivirus_yn;
    }

    public String getWith_msoffice_yn() {
        return with_msoffice_yn;
    }

    public void setWith_msoffice_yn(String with_msoffice_yn) {
        this.with_msoffice_yn = with_msoffice_yn;
    }

    public String getWith_charger_yn() {
        return with_charger_yn;
    }

    public void setWith_charger_yn(String with_charger_yn) {
        this.with_charger_yn = with_charger_yn;
    }

    public String getWith_laptop_bag_yn() {
        return with_laptop_bag_yn;
    }

    public void setWith_laptop_bag_yn(String with_laptop_bag_yn) {
        this.with_laptop_bag_yn = with_laptop_bag_yn;
    }

    public long getParent_asset_id() {
        return parent_asset_id;
    }

    public void setParent_asset_id(long parent_asset_id) {
        this.parent_asset_id = parent_asset_id;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHdd_ssd() {
        return hdd_ssd;
    }

    public void setHdd_ssd(String hdd_ssd) {
        this.hdd_ssd = hdd_ssd;
    }

    public String getLicence_type() {
        return licence_type;
    }

    public void setLicence_type(String licence_type) {
        this.licence_type = licence_type;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
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

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public Date getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Date updated_on) {
        this.updated_on = updated_on;
    }

    public String getWatts() {
        return watts;
    }

    public void setWatts(String watts) {
        this.watts = watts;
    }

    public String getSub_state1() {
        return sub_state1;
    }

    public void setSub_state1(String sub_state1) {
        this.sub_state1 = sub_state1;
    }

    

    
    
}
