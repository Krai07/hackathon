---------------------------------------------------------------------------------
-- Asset Status View
drop view asset_status_view;

CREATE OR REPLACE VIEW asset_status_view AS
SELECT
    asset_id,
    asset_code,
    asset_category,
    CONCAT(manufacturer, ' ', product, ' ', model) AS display_name,
    serial_number,
    CASE
        WHEN sub_state = 'available' THEN 'INSTOCK'
        WHEN sub_state = 'damaged' THEN 'DAMAGED'
        ELSE 'INUSE'
    END AS state,
    sub_state,
    sub_state1
FROM
    m_assets;

SELECT * FROM asset_status_view;

---------------------------------------------------------------------------------
-- Procurement View
drop view procurement_view

CREATE VIEW procurement_view AS
SELECT 
	procurement_id, 
	asset_category, 
	quantity,
	remaining_quantity, 
	selected_vendor,
	payment_terms, 
	procurement_date, 
	total_value
FROM 
	procurement;

select * from procurement_view;
---------------------------------------------------------------------------------
-- Asset Request View
drop view asset_request_view;

CREATE VIEW asset_request_view AS
SELECT
    asset_request_id,
    asset_category,
    emp_id,
    project_code,
    request_type,
    request_status,
    request_comments,
	approver_role,
    delivery_location,
    delivery_comments
FROM asset_request;

select * from asset_request_view where emp_id = 3644;
select * from asset_request_view where emp_id = 3011;

select * from m_assets;
select * from asset_request;

---------------------------------------------------------------------------------
--Asset Info View
drop view asset_info_view;

CREATE OR REPLACE VIEW asset_info_view AS
SELECT
    m.asset_id,
    m.asset_code,
    m.asset_category,
    m.manufacturer,
    m.product,
    m.model,
    m.serial_number,
    m.watts,
    m.display_name,
    m.end_of_support,
    m.end_of_extended_support,
    m.end_of_life,
    m.state,
    m.sub_state,
    m.procurement_id,
    m.purchase_price,
    m.vendor,
    m.with_os_yn,
    m.with_antivirus_yn,
    m.with_msoffice_yn,
    m.with_charger_yn,
    m.with_laptop_bag_yn,
    m.parent_asset_id,
    m.processor,
    m.ram,
    m.hdd_ssd,
    m.licence_type,
    m.os,
    m.active_yn,
    m.created_by,
    m.created_on,
    m.updated_by,
    m.updated_on,
    ar.asset_request_id,
    ar.emp_id,
	ar.assigned_on,
    ar.asset_code AS ar_asset_code
FROM
    m_assets m
LEFT JOIN
    asset_request ar ON m.asset_code = ar.asset_code;
	
select * from asset_info_view;
select asset_code from asset_info_view where emp_id = 3644;
select * from asset_info_view where emp_id = 3644;


---------------------------------------------------------------------------------
--Approved Assets View

drop view assets_approved_view;

CREATE VIEW assets_approved_view AS
SELECT
    asset_category,
    emp_id,
    project_code,
    request_type,
    request_comments,
    delivery_location,
    approver_comments,
    request_status
FROM
    asset_request;

select * from assets_approved_view;

---------------------------------------------------------------------------------
--Asset Approval Manager View
drop view asset_approval_manager;

CREATE VIEW asset_approval_manager AS
SELECT 
	asset_request_id,
	asset_category,
    emp_id,
    project_code,
    request_type,
    request_comments,
	delivery_location,
    approver_comments
FROM 
    asset_request;

select * from asset_approval_manager;

---------------------------------------------------------------------------------
--Asset Assignment at Asset Requests
drop view assign_asset

CREATE VIEW assign_asset AS
SELECT asset_request_id,
    asset_category,
    emp_id,
    project_code,
    request_type,
    request_comments,
    approver_id,
    approver_comments,
	approver_status,
    asset_code
   FROM asset_request;

select * from assign_asset;
---------------------------------------------------------------------------------
--Delivery (Pending & Completed)
drop view delivery_view;

CREATE VIEW delivery_view AS
SELECT 
	asset_request_id,
	asset_category,
    emp_id,
    project_code,
	asset_code,
	delivery_location,
	admin_status,
	delivery_comments,
    admin_comments
FROM 
    asset_request;

select * from delivery_view;