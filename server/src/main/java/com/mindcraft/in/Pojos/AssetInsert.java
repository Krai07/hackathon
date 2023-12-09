package com.mindcraft.in.pojos;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class AssetInsert {

    @Id
    @Column(name = "asset_id")
    private int asset_id;

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

    @Column(name = "end_of_support")
    private Date end_of_support;

    @Column(name = "end_of_extended_support")
    private Date end_of_extended_support;

    @Column(name = "state")
    private String state;

    @Column(name = "sub_state")
    private String sub_state;

    @Column(name = "procurement_id")
    private long procurement_id;

    @Column(name = "with_antivirus_yn")
    private String with_antivirus_yn;

    @Column(name = "with_msoffice_yn")
    private String with_msoffice_yn;

    @Column(name = "with_charger_yn")
    private String with_charger_yn;

    @Column(name = "with_laptop_bag_yn")
    private String with_laptop_bag_yn;

    @Column(name = "processor")
    private String processor;

    @Column(name = "ram")
    private String ram;

    @Column(name = "hdd_ssd")
    private String hdd_ssd;

    @Column(name = "licence_type")
    private String licence_type;

    @Column(name = "sub_state1")
    private String sub_state1;

    @Column(name = "os")
    private String os;

    @Column(name = "with_os_yn")
    private String with_os;

    public AssetInsert(){
        
    }

    public AssetInsert(String asset_category, String manufacturer, String product,
            String model, String serial_number, String watts,  String ram, String processor, String hdd_ssd,String licence_type, String os,String with_os,Long procurement_id,Date end_of_support, Date end_of_extended_support,String state, String sub_state, String sub_state1,String with_antivirus_yn, String with_msoffice_yn,String with_charger_yn,String with_laptop_bag_yn) {
        this.asset_category = asset_category;
        this.manufacturer = manufacturer;
        this.product = product;
        this.model = model;
        this.serial_number = serial_number;
        this.watts = watts;
        this.end_of_support = end_of_support;
        this.end_of_extended_support = end_of_extended_support;
        this.state = state;
        this.sub_state = sub_state;
        this.procurement_id = procurement_id;
        this.with_antivirus_yn = with_antivirus_yn;
        this.with_msoffice_yn = with_msoffice_yn;
        this.with_charger_yn = with_charger_yn;
        this.with_laptop_bag_yn = with_laptop_bag_yn;
        this.processor = processor;
        this.ram = ram;
        this.hdd_ssd = hdd_ssd;
        this.licence_type = licence_type;
        this.os = os;
        this.with_os = with_os;
        this.sub_state1 = sub_state1;
    }



    public int getAsset_id() {
        return asset_id;
    }


    public void setAsset_id(int asset_id) {
        this.asset_id = asset_id;
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


    public String getSub_state1() {
        return sub_state1;
    }


    public void setSub_state1(String sub_state1) {
        this.sub_state1 = sub_state1;
    }


    public String getOs() {
        return os;
    }


    public void setOs(String os) {
        this.os = os;
    }

    public String getWatts() {
        return watts;
    }

    public void setWatts(String watts) {
        this.watts = watts;
    }

    public String getWith_os() {
        return with_os;
    }

    public void setWith_os(String with_os) {
        this.with_os = with_os;
    }
}
