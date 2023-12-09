-- 06-12-2023
-- JOEL DSOUZA
    ALTER TABLE USERS
        ADD COLUMN manager_id VARCHAR(25);

    DELETE FROM USERS;

    INSERT INTO users
        VALUES
          (	'3642'	, '3642'	, 'ROLE_SYS_ADMIN'	,   NULL),
          (	'3650'	, '3650'	, 'ROLE_SYS_ADMIN'	,   NULL),
          (	'1000'	, '1000'	, 'ROLE_ADMIN'    	,   NULL),
          (	'0231'	, '0231'	, 'ROLE_DADMIN'   	, '0031'),
          (	'0031'	, '0031'	, 'ROLE_MANAGER'  	,   NULL),
          (	'3011'	, '3011'	, 'ROLE_MANAGER'  	, '0231'),
          (	'2976'	, '2976'	, 'ROLE_MANAGER'  	, '0231')
          (	'3644'	, '3644'	, 'ROLE_USER'     	, '2976'),
          (	'3649'	, '3649'	, 'ROLE_USER'     	, '3011'),
          (	'3712'	, '3712'	, 'ROLE_USER'     	, '2976');

-- 07-12-2023
-- PRERNA SHARMA
   ALTER TABLE m_assets
    ALTER COLUMN state TYPE VARCHAR(25);

   ALTER TABLE m_assets
    ALTER COLUMN sub_state TYPE VARCHAR(25);

   ALTER TABLE m_assets
    ALTER COLUMN sub_state1 TYPE VARCHAR(25);

--Changed the query for asset_status_view. So, drop the view and create again
--added 'assigned_on' column in asset_request table. Drop the table
--updated the asset_info_view (added 'assigned_on' column). Drop the view and create