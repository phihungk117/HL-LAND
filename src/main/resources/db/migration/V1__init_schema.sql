-- =============================================
-- MODULE 1: SYSTEM - Quản lý hệ thống
-- =============================================

CREATE TABLE sys_users (--Tạo bảng sys_users (Người dùng)
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    email       VARCHAR(150) UNIQUE,
    phone       VARCHAR(20),
    full_name   VARCHAR(200),
    role        VARCHAR(50) NOT NULL DEFAULT 'STAFF',
    status      VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP DEFAULT NOW(),
    updated_at  TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(100),
    CONSTRAINT chk_role CHECK (role IN ('SUPER_ADMIN','ADMIN','MANAGER','STAFF','RESIDENT')),
    CONSTRAINT chk_status CHECK (status IN ('ACTIVE','INACTIVE','LOCKED'))
);

CREATE TABLE sys_building_config (--Tạo bảng sys_building_config (Cấu hình tòa nhà)
    id              BIGSERIAL PRIMARY KEY,
    project_name    VARCHAR(300) NOT NULL,
    address         TEXT,
    hotline         VARCHAR(50),
    mgmt_fee_rate   NUMERIC(10,4),  -- Đơn giá phí quản lý
    vat_rate        NUMERIC(5,4) DEFAULT 0.1,
    bank_account    VARCHAR(100),
    bank_name       VARCHAR(200),
    payment_note    TEXT,
    logo_url        VARCHAR(500),
    created_at      TIMESTAMP DEFAULT NOW(),
    updated_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE sys_refresh_tokens (--Tạo bảng sys_refresh_tokens (Token làm mới)
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL REFERENCES sys_users(id),
    token       TEXT NOT NULL UNIQUE,
    expires_at  TIMESTAMP NOT NULL,
    revoked     BOOLEAN DEFAULT FALSE, --đánh dấu token còn hiệu lực hay đã bị thu hồi
    created_at  TIMESTAMP DEFAULT NOW()
);

-- =============================================
-- MODULE 2: ADMIN - Hành chính
-- =============================================

CREATE TABLE adm_staff (--Tạo bảng adm_staff (Nhân viên hành chính)
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT REFERENCES sys_users(id),
    employee_code   VARCHAR(50) UNIQUE,
    full_name       VARCHAR(200) NOT NULL,
    date_of_birth   DATE,
    gender          VARCHAR(10),
    id_card         VARCHAR(20) UNIQUE,
    phone           VARCHAR(20),
    email           VARCHAR(150),
    address         TEXT,
    department      VARCHAR(100),
    position        VARCHAR(100),
    hire_date       DATE,  --ngày tuyển dụng
    status          VARCHAR(20) DEFAULT 'ACTIVE',
    created_at      TIMESTAMP DEFAULT NOW(),
    updated_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE adm_staff_family ( --Tạo bảng adm_staff_family (Thân nhân nhân viên)
    id          BIGSERIAL PRIMARY KEY,
    staff_id    BIGINT NOT NULL REFERENCES adm_staff(id),
    full_name   VARCHAR(200),
    relation    VARCHAR(50),
    phone       VARCHAR(20),
    id_card     VARCHAR(20)
);
 
CREATE TABLE adm_staff_education (--Tạo bảng adm_staff_education (Học vấn nhân viên)
    id          BIGSERIAL PRIMARY KEY,
    staff_id    BIGINT NOT NULL REFERENCES adm_staff(id),
    degree      VARCHAR(100),
    major       VARCHAR(200),
    school      VARCHAR(300),
    year        INT
);
 
CREATE TABLE adm_staff_experience (--Tạo bảng adm_staff_experience (Kinh nghiệm làm việc của nhân viên)
    id              BIGSERIAL PRIMARY KEY,
    staff_id        BIGINT NOT NULL REFERENCES adm_staff(id),
    company         VARCHAR(300),
    position        VARCHAR(100),
    from_date       DATE,
    to_date         DATE,
    description     TEXT
);

CREATE TABLE adm_documents (
    id              BIGSERIAL PRIMARY KEY,
    title           VARCHAR(500) NOT NULL,
    doc_type        VARCHAR(50),  -- PROCEDURE / FORM / ADMIN
    file_url        VARCHAR(1000),
    file_name       VARCHAR(300),
    file_size       BIGINT,
    description     TEXT,
    created_by      VARCHAR(100),
    created_at      TIMESTAMP DEFAULT NOW(),
    updated_at      TIMESTAMP DEFAULT NOW()
);

-- =============================================
-- MODULE 3: CUSTOMER - Khách hàng & Dịch vụ
-- =============================================

CREATE TABLE cus_customers (
    id              BIGSERIAL PRIMARY KEY,
    customer_code   VARCHAR(50) UNIQUE NOT NULL,
    full_name       VARCHAR(200) NOT NULL,
    id_card         VARCHAR(20) UNIQUE,
    phone           VARCHAR(20),
    email           VARCHAR(150),
    address         TEXT,
    tax_code        VARCHAR(20),-- mã số thuế
    customer_type   VARCHAR(20) DEFAULT 'OWNER', -- OWNER / TENANT
    status          VARCHAR(20) DEFAULT 'ACTIVE',
    user_id         BIGINT REFERENCES sys_users(id),
    created_at      TIMESTAMP DEFAULT NOW(),
    updated_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE cus_apartments ( --Tạo bảng cus_apartments (Căn hộ)
    id              BIGSERIAL PRIMARY KEY,
    apartment_code  VARCHAR(50) UNIQUE NOT NULL,
    block           VARCHAR(20),
    floor           INT,
    apartment_no    VARCHAR(20),
    area            NUMERIC(10,2),  -- m2
    status          VARCHAR(30) DEFAULT 'OCCUPIED', -- OCCUPIED/VACANT/MAINTENANCE
    handover_date   DATE,
    invoice_name    VARCHAR(200),   -- Tên xuất hóa đơn
    invoice_address TEXT,
    invoice_tax     VARCHAR(20),
    created_at      TIMESTAMP DEFAULT NOW(),
    updated_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE cus_apartment_owners (--Lịch sử sở hữu căn hộ
    id              BIGSERIAL PRIMARY KEY,
    apartment_id    BIGINT NOT NULL REFERENCES cus_apartments(id),
    customer_id     BIGINT NOT NULL REFERENCES cus_customers(id),
    ownership_type  VARCHAR(20) DEFAULT 'OWNER', -- OWNER/TENANT
    from_date       DATE NOT NULL,
    to_date         DATE,
    is_current      BOOLEAN DEFAULT TRUE,
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE cus_vehicles (--Tạo bảng cus_vehicles (Phương tiện)
    id              BIGSERIAL PRIMARY KEY,
    apartment_id    BIGINT NOT NULL REFERENCES cus_apartments(id),
    license_plate   VARCHAR(20) NOT NULL,
    vehicle_type    VARCHAR(20), -- MOTORBIKE/CAR/BICYCLE
    brand           VARCHAR(100),
    color           VARCHAR(50),
    status          VARCHAR(20) DEFAULT 'ACTIVE',
    from_date       DATE,
    to_date         DATE,
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE cus_residents (--Tạo bảng cus_residents (Cư dân)
    id              BIGSERIAL PRIMARY KEY,
    apartment_id    BIGINT NOT NULL REFERENCES cus_apartments(id),
    full_name       VARCHAR(200) NOT NULL,
    id_card         VARCHAR(20),
    phone           VARCHAR(20),
    date_of_birth   DATE,
    gender          VARCHAR(10),
    relation        VARCHAR(50),
    move_in_date    DATE,
    move_out_date   DATE,
    is_current      BOOLEAN DEFAULT TRUE,
    created_at      TIMESTAMP DEFAULT NOW()
);

-- =============================================
-- MODULE 4: FINANCE - Tài chính
-- =============================================
CREATE TABLE fin_fee_types ( --Tạo bảng fin_fee_types (Loại phí VD phí quản lý, phí gửi xe, phí điện nước...)
    id              BIGSERIAL PRIMARY KEY,
    fee_code        VARCHAR(50) UNIQUE NOT NULL,
    fee_name        VARCHAR(200) NOT NULL,
    fee_category    VARCHAR(50), -- WATER/ELECTRIC/GAS/VEHICLE/MGMT/OTHER
    unit            VARCHAR(50),
    description     TEXT,
    is_active       BOOLEAN DEFAULT TRUE,
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE fin_fee_configs ( --Bảng cấu hình giá tiền cho từng loại phí theo khoảng sử dụng và thời gian áp dụng
    id              BIGSERIAL PRIMARY KEY,
    fee_type_id     BIGINT NOT NULL REFERENCES fin_fee_types(id),
    min_usage       NUMERIC(10,2),
    max_usage       NUMERIC(10,2),
    unit_price      NUMERIC(15,4) NOT NULL,
    apply_from      DATE NOT NULL,
    apply_to        DATE,
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE fin_billing_periods (--Tạo bảng fin_billing_periods (Kỳ tính phí)
    id              BIGSERIAL PRIMARY KEY,
    period_code     VARCHAR(20) UNIQUE NOT NULL, -- e.g. 2024-01
    year            INT NOT NULL,
    month           INT NOT NULL,
    from_date       DATE,
    to_date         DATE,
    status          VARCHAR(20) DEFAULT 'OPEN', -- OPEN/CLOSED
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE fin_fee_notices ( --Tạo bảng fin_fee_notices (Thông báo phí)
    id              BIGSERIAL PRIMARY KEY,
    notice_no       VARCHAR(50) UNIQUE NOT NULL,
    apartment_id    BIGINT NOT NULL REFERENCES cus_apartments(id),
    period_id       BIGINT NOT NULL REFERENCES fin_billing_periods(id),
    fee_type_id     BIGINT NOT NULL REFERENCES fin_fee_types(id),
    usage_start     NUMERIC(10,2), --Chỉ số cũ
    usage_end       NUMERIC(10,2), --Chỉ số mới
    usage_amount    NUMERIC(10,2), --Số lượng sử dụng (usage_end - usage_start)
    unit_price      NUMERIC(15,4),
    amount          NUMERIC(15,2) NOT NULL,
    vat_amount      NUMERIC(15,2) DEFAULT 0,
    total_amount    NUMERIC(15,2) NOT NULL,
    status          VARCHAR(20) DEFAULT 'UNPAID', -- UNPAID/PAID/PARTIAL/CANCELLED
    due_date        DATE,
    note            TEXT,
    created_at      TIMESTAMP DEFAULT NOW(),
    updated_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE fin_receipts (--Tạo bảng fin_receipts (Phiếu thu/chi)
    id              BIGSERIAL PRIMARY KEY, -- Khóa chính tự động tăng, định danh duy nhất cho mỗi phiếu
    receipt_no      VARCHAR(50) UNIQUE NOT NULL, -- Số/Mã phiếu (VD: PT001, PC002). Bắt buộc nhập và không trùng lặp
    receipt_type    VARCHAR(10) NOT NULL, -- Loại phiếu: 'IN' (Phiếu thu) hoặc 'OUT' (Phiếu chi)
    apartment_id    BIGINT REFERENCES cus_apartments(id), -- ID Căn hộ (Khóa ngoại liên kết bảng cus_apartments)
    customer_id     BIGINT REFERENCES cus_customers(id), -- ID Khách hàng (Khóa ngoại liên kết bảng cus_customers)
    amount          NUMERIC(15,2) NOT NULL, -- Số tiền giao dịch (Tối đa 15 chữ số, 2 số thập phân)
    payment_method  VARCHAR(30), -- Phương thức thanh toán (VD: CASH, BANK_TRANSFER, CARD)
    payment_date    TIMESTAMP NOT NULL, -- Ngày giờ thực hiện thanh toán/giao dịch thực tế
    reason          VARCHAR(500), -- Diễn giải lý do thu hoặc chi tiền
    bank_account    VARCHAR(100), -- Số tài khoản ngân hàng nhận/chuyển (phục vụ đối soát)
    reference_no    VARCHAR(100), -- Mã tham chiếu (Số chứng từ gốc, mã giao dịch ngân hàng...)
    note            TEXT, -- Ghi chú bổ sung
    created_by      VARCHAR(100), -- Tên, mã nhân viên hoặc username của người lập phiếu
    created_at      TIMESTAMP DEFAULT NOW() -- Thời gian tạo phiếu (Tự động lấy ngày giờ hệ thống khi lưu)
);

CREATE TABLE fin_receipt_fee_notices (--Tạo bảng fin_receipt_fee_notices (Liên kết phiếu thu với các thông báo phí đã thanh toán)
    id              BIGSERIAL PRIMARY KEY,
    receipt_id      BIGINT NOT NULL REFERENCES fin_receipts(id),
    fee_notice_id   BIGINT NOT NULL REFERENCES fin_fee_notices(id),
    paid_amount     NUMERIC(15,2) NOT NULL
);

CREATE TABLE fin_accounts (--Tạo bảng fin_accounts (Tài khoản ngân hàng)
    id              BIGSERIAL PRIMARY KEY,
    account_no      VARCHAR(50) NOT NULL,
    bank_name       VARCHAR(200),
    account_name    VARCHAR(200),
    branch          VARCHAR(200),
    is_default      BOOLEAN DEFAULT FALSE,
    created_at      TIMESTAMP DEFAULT NOW()
);

-- =============================================
-- MODULE 5: TECHNICAL - Kỹ thuật
-- =============================================

CREATE TABLE tec_systems (--Tạo bảng tec_systems (Hệ thống kỹ thuật)
    id              BIGSERIAL PRIMARY KEY,
    system_code     VARCHAR(50) UNIQUE NOT NULL,
    system_name     VARCHAR(300) NOT NULL,
    system_type     VARCHAR(100),
    description     TEXT,
    is_active       BOOLEAN DEFAULT TRUE,
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE tec_equipment (--Tạo bảng tec_equipment (Thiết bị kỹ thuật)
    id              BIGSERIAL PRIMARY KEY,
    equipment_code  VARCHAR(50) UNIQUE NOT NULL,
    equipment_name  VARCHAR(300) NOT NULL,
    system_id       BIGINT REFERENCES tec_systems(id),
    location        VARCHAR(300),
    brand           VARCHAR(100),
    model           VARCHAR(100),
    serial_no       VARCHAR(100),
    install_date    DATE,
    warranty_date   DATE,
    status          VARCHAR(20) DEFAULT 'ACTIVE',
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE tec_checkpoints (--Tạo bảng tec_checkpoints (Điểm kiểm tra kỹ thuật)
    id              BIGSERIAL PRIMARY KEY,
    checkpoint_code VARCHAR(50) UNIQUE NOT NULL,
    checkpoint_name VARCHAR(300) NOT NULL,
    location        VARCHAR(300),
    system_id       BIGINT REFERENCES tec_systems(id),
    is_active       BOOLEAN DEFAULT TRUE,
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE tec_checklists (--Tạo bảng tec_checklists (Bảng kiểm tra kỹ thuật)
    id              BIGSERIAL PRIMARY KEY,
    checklist_name  VARCHAR(300) NOT NULL,
    checkpoint_id   BIGINT NOT NULL REFERENCES tec_checkpoints(id),
    description     TEXT,
    frequency       VARCHAR(30), -- DAILY/WEEKLY/MONTHLY
    is_active       BOOLEAN DEFAULT TRUE,
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE tec_inspection_logs (--Tạo bảng tec_inspection_logs (Nhật ký kiểm tra kỹ thuật)
    id              BIGSERIAL PRIMARY KEY,
    checklist_id    BIGINT NOT NULL REFERENCES tec_checklists(id),
    inspector_id    BIGINT REFERENCES adm_staff(id),
    inspect_date    TIMESTAMP NOT NULL,
    result          VARCHAR(20), -- OK/WARNING/FAILED
    note            TEXT,
    image_url       VARCHAR(1000),
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE tec_maintenance_plans (--Tạo bảng tec_maintenance_plans (Kế hoạch bảo trì kỹ thuật)
    id              BIGSERIAL PRIMARY KEY,
    plan_code       VARCHAR(50) UNIQUE NOT NULL,
    plan_name       VARCHAR(300) NOT NULL,
    system_id       BIGINT REFERENCES tec_systems(id),
    tasks           TEXT,
    executor        VARCHAR(200),  -- INTERNAL / contractor name
    scheduled_date  DATE,
    actual_date     DATE,
    status          VARCHAR(20) DEFAULT 'PLANNED', -- PLANNED/IN_PROGRESS/DONE/CANCELLED
    cost            NUMERIC(15,2),
    note            TEXT,
    created_by      VARCHAR(100),
    created_at      TIMESTAMP DEFAULT NOW(),
    updated_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE tec_energy_meters (--Tạo bảng tec_energy_meters (Đồng hồ đo năng lượng)
    id              BIGSERIAL PRIMARY KEY,
    meter_code      VARCHAR(50) UNIQUE NOT NULL,
    meter_name      VARCHAR(200) NOT NULL,
    meter_type      VARCHAR(30), -- ELECTRIC/WATER/GAS
    location        VARCHAR(300),
    unit            VARCHAR(20),
    is_active       BOOLEAN DEFAULT TRUE,
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE tec_energy_readings (--Tạo bảng tec_energy_readings (Chỉ số năng lượng)
    id              BIGSERIAL PRIMARY KEY,
    meter_id        BIGINT NOT NULL REFERENCES tec_energy_meters(id),
    reading_date    DATE NOT NULL,
    reading_value   NUMERIC(15,4) NOT NULL,
    recorded_by     VARCHAR(100),
    note            TEXT,
    created_at      TIMESTAMP DEFAULT NOW(),
    UNIQUE(meter_id, reading_date)
);

-- =============================================
-- MODULE 6: APP - Ứng dụng
-- =============================================
CREATE TABLE app_notifications ( --thông báo
    id                  BIGSERIAL PRIMARY KEY,
    title               VARCHAR(500) NOT NULL,
    content             TEXT NOT NULL,
    notification_type   VARCHAR(50), -- GENERAL/URGENT/FEE/MAINTENANCE
    target_type         VARCHAR(30) DEFAULT 'ALL', -- ALL/APARTMENT/RESIDENT
    target_id           BIGINT,
    is_published        BOOLEAN DEFAULT FALSE,
    published_at        TIMESTAMP,
    expires_at          TIMESTAMP,
    created_by          VARCHAR(100),
    created_at          TIMESTAMP DEFAULT NOW()
);

CREATE TABLE app_feedbacks (
    id              BIGSERIAL PRIMARY KEY,
    feedback_no     VARCHAR(50) UNIQUE NOT NULL,
    apartment_id    BIGINT REFERENCES cus_apartments(id),
    customer_id     BIGINT REFERENCES cus_customers(id),
    feedback_type   VARCHAR(30), -- FEEDBACK/EXCHANGE/SUPPORT
    title           VARCHAR(500),
    content         TEXT NOT NULL,
    status          VARCHAR(20) DEFAULT 'PENDING', -- PENDING/IN_PROGRESS/RESOLVED/CLOSED
    response        TEXT,
    responded_by    VARCHAR(100),
    responded_at    TIMESTAMP,
    created_at      TIMESTAMP DEFAULT NOW(),
    updated_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE app_registrations (
    id              BIGSERIAL PRIMARY KEY,
    reg_no          VARCHAR(50) UNIQUE NOT NULL,
    apartment_id    BIGINT REFERENCES cus_apartments(id),
    customer_id     BIGINT REFERENCES cus_customers(id),
    reg_type        VARCHAR(50), -- GOODS_IN/GOODS_OUT/CONSTRUCTION/RESIDENT/AMENITY
    from_date       TIMESTAMP,
    to_date         TIMESTAMP,
    description     TEXT,
    status          VARCHAR(20) DEFAULT 'PENDING', -- PENDING/APPROVED/REJECTED
    approved_by     VARCHAR(100),
    approved_at     TIMESTAMP,
    reject_reason   TEXT,
    created_at      TIMESTAMP DEFAULT NOW(),
    updated_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE app_surveys (--Tạo bảng app_surveys (Khảo sát ý kiến cư dân)
    id              BIGSERIAL PRIMARY KEY,
    survey_title    VARCHAR(500) NOT NULL,
    description     TEXT,
    from_date       DATE,
    to_date         DATE,
    is_active       BOOLEAN DEFAULT TRUE,
    created_by      VARCHAR(100),
    created_at      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE app_survey_questions (
    id              BIGSERIAL PRIMARY KEY,
    survey_id       BIGINT NOT NULL REFERENCES app_surveys(id),
    question_text   TEXT NOT NULL,
    question_type   VARCHAR(20), -- RATING/TEXT/CHOICE
    options         TEXT, -- JSON array for choices
    sort_order      INT DEFAULT 0
);
 
CREATE TABLE app_survey_responses (
    id              BIGSERIAL PRIMARY KEY,
    survey_id       BIGINT NOT NULL REFERENCES app_surveys(id),
    question_id     BIGINT NOT NULL REFERENCES app_survey_questions(id),
    apartment_id    BIGINT REFERENCES cus_apartments(id),
    answer          TEXT,
    created_at      TIMESTAMP DEFAULT NOW()
);

-- =============================================
-- AUDIT LOG
-- =============================================
CREATE TABLE sys_audit_logs (
    id              BIGSERIAL PRIMARY KEY,
    entity_type     VARCHAR(100),
    entity_id       BIGINT,
    action          VARCHAR(50), -- CREATE/UPDATE/DELETE/VIEW
    actor           VARCHAR(100),
    old_value       TEXT,
    new_value       TEXT,
    ip_address      VARCHAR(50),
    reason          TEXT,
    created_at      TIMESTAMP DEFAULT NOW()
);
 
-- =============================================
-- INDEXES
-- =============================================
CREATE INDEX idx_users_username ON sys_users(username);
CREATE INDEX idx_users_email ON sys_users(email);
CREATE INDEX idx_apartments_code ON cus_apartments(apartment_code);
CREATE INDEX idx_customers_code ON cus_customers(customer_code);
CREATE INDEX idx_fee_notices_apartment ON fin_fee_notices(apartment_id);
CREATE INDEX idx_fee_notices_period ON fin_fee_notices(period_id);
CREATE INDEX idx_fee_notices_status ON fin_fee_notices(status);
CREATE INDEX idx_receipts_date ON fin_receipts(payment_date);
CREATE INDEX idx_audit_logs_entity ON sys_audit_logs(entity_type, entity_id);