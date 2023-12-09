-- Generate Asset Code
drop function generate_asset_code;

CREATE OR REPLACE FUNCTION generate_asset_code(p_asset_category VARCHAR)
RETURNS VARCHAR AS $$
DECLARE
    v_prefix VARCHAR;
    v_next_number INT;
    v_new_asset_code VARCHAR;
BEGIN
    CASE p_asset_category
        WHEN 'Laptop' THEN
            v_prefix := 'MCL';
        WHEN 'Charger' THEN
            v_prefix := 'MCC';
        WHEN 'Desktop' THEN
            v_prefix := 'MCD';
        WHEN 'Storage' THEN
            v_prefix := 'MCS';
        WHEN 'Wifi' THEN
            v_prefix := 'MCW';
        ELSE
            RAISE EXCEPTION 'Unknown asset category: %', p_asset_category;
    END CASE;

    SELECT COALESCE(MAX(SUBSTRING(asset_code FROM 4)::INT), 0) + 1 INTO v_next_number
    FROM m_assets
    WHERE asset_category = p_asset_category;

    v_new_asset_code := v_prefix || LPAD(v_next_number::TEXT, 3, '0');

    RETURN v_new_asset_code;
END;
$$ LANGUAGE plpgsql;


-- Generate Display Name
drop function generate_display_name;

CREATE OR REPLACE FUNCTION generate_display_name(
    p_manufacturer VARCHAR,
    p_product VARCHAR,
    p_model VARCHAR
)
RETURNS VARCHAR AS $$
DECLARE
    v_display_name VARCHAR(100);
BEGIN
    v_display_name := p_manufacturer || ' ' || p_product || ' ' || p_model;
    RETURN v_display_name;
END;
$$ LANGUAGE plpgsql;