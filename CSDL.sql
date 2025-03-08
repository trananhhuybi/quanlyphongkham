
CREATE DATABASE QLPK1;

-- Sử dụng cơ sở dữ liệu
USE QLPK1;

-- Tạo bảng taiKhoan
CREATE TABLE taiKhoan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tenTK VARCHAR(50) NOT NULL,
    matKhau VARCHAR(20) NOT NULL
);

-- Tạo bảng Thuoc
CREATE TABLE Thuoc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tenThuoc VARCHAR(100) NOT NULL,
    soLuong INT NOT NULL,
    gia decimal NOT NULL,
    donViTinh VARCHAR(20) NOT NULL
);

-- Tạo bảng Dịch vụ
CREATE TABLE dichVu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tenDV VARCHAR(100) NOT NULL,
    giaDV decimal NOT NULL
);

-- Tạo bảng BenhNhan
CREATE TABLE benhNhan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hoTen VARCHAR(100) NOT NULL,
    diaChi VARCHAR(100) NOT NULL,
    gioiTinh VARCHAR(20) NOT NULL,
    sdt VARCHAR(20) NOT NULL,
    namSinh INT NOT NULL,
    tienSu VARCHAR(100) NOT NULL
);

-- Tạo bảng Phiếu khám bệnh
CREATE TABLE khamBenh (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idBN INT NOT NULL,
    idDV INT NOT NULL,
    ngayKham datetime default current_timestamp,
    trieuChung VARCHAR(100) NOT NULL,
    chuanDoan VARCHAR(100) NOT NULL,
    tenDV VARCHAR(100) NOT NULL,
    giaDV decimal NOT NULL,
    FOREIGN KEY (idBN) REFERENCES benhNhan(id),
    FOREIGN KEY (idDV) REFERENCES dichVu(id)
);

-- Tạo bảng CTTT
CREATE TABLE chiTietToaThuoc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idThuoc INT NOT NULL,
    idKB INT NOT NULL,
    tenThuoc VARCHAR(100),
    sang INT,
    trua INT,
    chieu INT,
    gia decimal,
    tongGia decimal,
    FOREIGN KEY (idKB) REFERENCES khamBenh(id),
    FOREIGN KEY (idThuoc) REFERENCES Thuoc(id)
);

CREATE TABLE hoaDon(
	id INT auto_increment primary key,
    idKB int,
    ngayKham datetime default current_timestamp,
    thanhTien decimal,
	FOREIGN KEY (idKB) REFERENCES khamBenh(id)
);


INSERT taiKhoan(tenTK, matKhau) values (N'ad', N'1');

INSERT Thuoc(tenThuoc, soLuong, gia, donViTinh) values (N'PANADOL', 30, 2500, N'Viên');
INSERT Thuoc(tenThuoc, soLuong, gia, donViTinh) values (N'HAPACOL', 20, 3000, N'Viên');

INSERT INTO dichVu (tenDV, giaDV) VALUES ('Khám tai mũi họng', 150000);
INSERT INTO dichVu (tenDV, giaDV) VALUES ('Khám da liễu', 250000);
INSERT INTO dichVu (tenDV, giaDV) VALUES ('Nhập viện', 0);

select * FROM qlpk1.hoadon;
SELECT * FROM qlpk1.Thuoc WHERE id = 1



