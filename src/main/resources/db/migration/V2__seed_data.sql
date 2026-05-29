-- src/main/resources/db/migration/V2__seed_data.sql
 
-- Default admin user (password: Admin@123)
INSERT INTO sys_users (username, password, email, full_name, role, status)
VALUES ('admin', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J1eVSsqZW', 'admin@truongson.com', 'Super Administrator', 'SUPER_ADMIN', 'ACTIVE');
 
-- Building config
INSERT INTO sys_building_config (project_name, address, hotline, mgmt_fee_rate, vat_rate, bank_account, bank_name)
VALUES ('Chung Cư Trường Sơn Plaza', '123 Đường Trường Sơn, Q.Tân Bình, TP.HCM', '028.1234.5678', 18000, 0.10, '0123456789', 'Vietcombank - CN Tân Bình');
 
-- Fee types
INSERT INTO fin_fee_types (fee_code, fee_name, fee_category, unit) VALUES
('FEE-MGMT', 'Phí quản lý', 'MGMT', 'đ/m²/tháng'),
('FEE-WATER', 'Phí nước sinh hoạt', 'WATER', 'đ/m³'),
('FEE-ELEC', 'Phí điện', 'ELECTRIC', 'đ/kWh'),
('FEE-GAS', 'Phí gas', 'GAS', 'đ/kg'),
('FEE-MOTORBIKE', 'Phí gửi xe máy', 'VEHICLE', 'đ/xe/tháng'),
('FEE-CAR', 'Phí gửi ô tô', 'VEHICLE', 'đ/xe/tháng');
 
-- Fee configs (Water tiers)
INSERT INTO fin_fee_configs (fee_type_id, min_usage, max_usage, unit_price, apply_from) VALUES
(2, 0, 10, 5973, '2024-01-01'),
(2, 10, 20, 7052, '2024-01-01'),
(2, 20, 30, 8669, '2024-01-01'),
(2, 30, null, 15929, '2024-01-01');
 
-- Current billing period
INSERT INTO fin_billing_periods (period_code, year, month, from_date, to_date, status)
VALUES ('2024-06', 2024, 6, '2024-06-01', '2024-06-30', 'OPEN');