package com.mindcraft.in.pojos;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserAssetsView {
    
    @Id
    @Column(name = "asset_code")
    private String asset_code;

    @Column(name = "asset_category")
    private String asset_category;

    @Column(name = "watts")
    private String watts;

    @Column(name = "serial_number")
    private String serial_number;

    @Column(name = "display_name")
    private String display_name;

    @Column(name = "end_of_support")
    private Date end_of_support;

    @Column(name = "end_of_extended_support")
    private Date end_of_extended_support;

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

    @Column(name = "assigned_on")
    private java.sql.Timestamp assigned_on;

    public UserAssetsView(){

    }

    public UserAssetsView(String asset_code, String asset_category, String watts, String serial_number,
            String display_name, Date end_of_support, Date end_of_extended_support, String with_os_yn,
            String with_antivirus_yn, String with_msoffice_yn, String with_charger_yn, String with_laptop_bag_yn,
            String processor, String ram, String hdd_ssd, String licence_type, String os, java.sql.Timestamp assigned_on) {
        this.asset_code = asset_code;
        this.asset_category = asset_category;
        this.watts = watts;
        this.serial_number = serial_number;
        this.display_name = display_name;
        this.end_of_support = end_of_support;
        this.end_of_extended_support = end_of_extended_support;
        this.with_os_yn = with_os_yn;
        this.with_antivirus_yn = with_antivirus_yn;
        this.with_msoffice_yn = with_msoffice_yn;
        this.with_charger_yn = with_charger_yn;
        this.with_laptop_bag_yn = with_laptop_bag_yn;
        this.processor = processor;
        this.ram = ram;
        this.hdd_ssd = hdd_ssd;
        this.licence_type = licence_type;
        this.os = os;
        this.assigned_on = assigned_on;
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

    public String getWatts() {
        return watts;
    }

    public void setWatts(String watts) {
        this.watts = watts;
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

    public java.sql.Timestamp getAssigned_on() {
        return assigned_on;
    }

    public void setAssigned_on(java.sql.Timestamp assigned_on) {
        this.assigned_on = assigned_on;
    }
    


}
