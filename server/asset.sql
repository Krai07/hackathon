------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Roles & Users
CREATE TABLE users (
	username varchar(25),
	password varchar(25),
	roles varchar(25)
);

INSERT INTO users values ('1000', '1000', 'ROLE_ADMIN');
INSERT INTO users values ('3644', '3644', 'ROLE_USER');
INSERT INTO users values ('3011', '3011', 'ROLE_MANAGER');
INSERT INTO users values ('3642', '3642', 'ROLE_SYS_ADMIN');

SELECT * FROM USERS;

select roles from users where username = '3642';

drop table users;
---------------------------------------------------------------------------------
-- Procurement
drop sequence procurement_id_sequence;

-- Procurement ID Sequence
drop sequence procurement_id_sequence;

CREATE SEQUENCE procurement_id_sequence;

-- Procurement Table
drop table procurement;

CREATE TABLE procurement (
    procurement_id BIGINT DEFAULT nextval('procurement_id_sequence') PRIMARY KEY,
    asset_category VARCHAR(25),
    quantity BIGINT,
    remaining_quantity BIGINT,
    selected_vendor VARCHAR(50),
    payment_terms VARCHAR(50),
    payment_terms_comments TEXT,
    procurement_date DATE,
	procurement_desc TEXT,
    total_value NUMERIC(12,2),
    raised_by_id BIGINT,
    raised_comments TEXT,
    raised_date DATE,
    approved_by_id BIGINT,
    approved_comments TEXT,
    approved_date DATE,
    active_yn CHAR(1),
    created_by VARCHAR(25),
    created_on TIMESTAMP,
    updated_by VARCHAR(25),
    updated_on TIMESTAMP
);

INSERT INTO procurement (
    asset_category,
    quantity,
    selected_vendor,
    payment_terms,
    payment_terms_comments,
    procurement_date,
	procurement_desc,
    total_value,
    raised_by_id,
    raised_comments,
    raised_date,
    approved_by_id,
    approved_comments,
    approved_date,
    active_yn,
    created_by,
    created_on,
    updated_by,
    updated_on
) VALUES (
    'Office Supplies',
    100,
    'Vendor A',
    '30 DAYS/ADVANCE',
    'Comments for payment terms',
    '2023-11-08',
	'Asus Chargers',
    1500.00,
    101,
    'Comments by requester',
    '2023-11-08',
    201,
    'Comments by approver',
    '2023-11-09',
    'Y',
    'User A',
    CURRENT_TIMESTAMP,
    'User A',
    CURRENT_TIMESTAMP
);

SELECT * FROM procurement;

-- delete from procurement where procurement_id = ?;
------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Procurement Documents Details

-- Procurement Documents Details ID Sequence
drop sequence procurement_documents_id_sequence;

CREATE SEQUENCE procurement_documents_id_sequence;

drop table procurement_documents;

CREATE TABLE procurement_documents (
    fileid BIGINT DEFAULT nextval('procurement_documents_id_sequence') PRIMARY KEY,
    procurement_id BIGINT,
    file_type VARCHAR(25),
    filename VARCHAR(255),
    file_shortname VARCHAR(255),
    file_comments TEXT,
    file_validity VARCHAR(25),
    filepath VARCHAR(255),
    fileblob TEXT,
    active_yn CHAR(1),
    created_by VARCHAR(25),
    created_on TIMESTAMP,
    updated_by VARCHAR(25),
    updated_on TIMESTAMP,
	CONSTRAINT fk_procurement FOREIGN KEY (procurement_id) REFERENCES procurement(procurement_id)
);

INSERT INTO procurement_documents (
    procurement_id,
    file_type,
    filename,
    file_shortname,
    file_comments,
    file_validity,
    filepath,
    fileblob,
    active_yn,
    created_by,
    created_on,
    updated_by,
    updated_on
) VALUES (
    1,  -- Match with an existing procurement_id from the procurement table
    'PDF',
    'Document.pdf',
    'Procurement Document',
    'Comments for the document',
    'Valid',
    '/path/to/file.pdf',
    'Base64 encoded file content',
    'Y',
    'User A',
    CURRENT_TIMESTAMP,
    'User A',
    CURRENT_TIMESTAMP
);

SELECT * FROM procurement_documents;

------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Assets Details
drop sequence asset_id_sequence;

CREATE SEQUENCE asset_id_sequence;

drop table m_assets;

CREATE TABLE m_assets (
    asset_id BIGINT DEFAULT nextval('asset_id_sequence') PRIMARY KEY,
    asset_code VARCHAR(25),
    asset_category VARCHAR(25),
    manufacturer VARCHAR(50),
    product VARCHAR(50),
    model VARCHAR(25),
    serial_number VARCHAR(25),
	watts VARCHAR(20),
    display_name VARCHAR(100),
    end_of_support DATE,
    end_of_extended_support DATE,
    end_of_life DATE,
    state VARCHAR(25),
    sub_state VARCHAR(25),
	sub_state1 VARCHAR(25),
    procurement_id BIGINT,
    purchase_price NUMERIC(12, 2),
    vendor VARCHAR(50),
    with_os_yn CHAR(1),
    with_antivirus_yn CHAR(1),
    with_msoffice_yn CHAR(1),
    with_charger_yn CHAR(1),
    with_laptop_bag_yn CHAR(1),
    parent_asset_id BIGINT,
    processor VARCHAR(25),
    ram VARCHAR(25),
    hdd_ssd VARCHAR(25),
    licence_type VARCHAR(25),
    os VARCHAR(25),
    active_yn CHAR(1),
    created_by VARCHAR(25),
    created_on TIMESTAMP,
    updated_by VARCHAR(25),
    updated_on TIMESTAMP,
	CONSTRAINT fk_procurement FOREIGN KEY (procurement_id) REFERENCES procurement(procurement_id)
);

SELECT * FROM m_assets;

------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Asset Requests

-- Asset Request ID Sequence
drop sequence asset_request_id_sequence;

CREATE SEQUENCE asset_request_id_sequence;

drop table asset_request;

CREATE TABLE asset_request (
    asset_request_id BIGINT DEFAULT nextval('asset_request_id_sequence') PRIMARY KEY,
    asset_category VARCHAR(25),
    emp_id BIGINT,
    project_code VARCHAR(25),
    request_type VARCHAR(25),
    asset_id BIGINT,
    request_status VARCHAR(25),
    request_comments TEXT,
    delivery_location VARCHAR(50),
    delivery_comments TEXT,
    approver_role VARCHAR(25),
    approver_id BIGINT,
    approver_status VARCHAR(50),
    allocation_type VARCHAR(25),
    approver_days BIGINT,
    approver_comments TEXT,
    approver_assigned_on DATE,
    approver_approved_on DATE,
    admin_id BIGINT,
    admin_status VARCHAR(50),
    admin_comments TEXT,
    admin_assigned_on DATE,
    admin_approved_on DATE,
    assigned_asset_id BIGINT,
    sysadmin_id BIGINT,
    sysadmin_status VARCHAR(50),
    sysadmin_comments TEXT,
    sysadmin_assigned_on DATE,
    sysadmin_approved_on DATE,
    asset_code VARCHAR(25),
    assigned_on TIMESTAMP;
    active_yn CHAR(1),
    created_by VARCHAR(25),
    created_on TIMESTAMP,
    updated_by VARCHAR(25),
    updated_on TIMESTAMP
);
 
SELECT * FROM asset_request;
