/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Controllers.BenhNhanController;
import Controllers.ChiTietToaThuocController;
import Controllers.DichVuController;
import Controllers.HoaDonController;
import Controllers.KhamBenhController;
import Controllers.ThuocController;
import Models.BenhNhan;
import Models.ChiTietToaThuoc;
import Models.DichVu;
import Models.HoaDon;
import Models.KhamBenh;
import Models.Thuoc;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public final class frNghiepVu extends javax.swing.JFrame {

    private final BenhNhanController benhNhanController;
    private final KhamBenhController khambenhController;
    private final ChiTietToaThuocController ctttController;
    private final HoaDonController hoadonController;
    private final DefaultTableModel BenhNhanmodel;
    private final DefaultTableModel CTTTmodel;
    private final DefaultTableModel HoaDonmodel;

    public frNghiepVu() {
        khambenhController = new KhamBenhController();
        benhNhanController = new BenhNhanController();
        ctttController = new ChiTietToaThuocController();
        hoadonController = new HoaDonController();
        initComponents();
        BenhNhanmodel = (DefaultTableModel) tbBenhNhan.getModel();
        CTTTmodel = (DefaultTableModel) tbCTTT.getModel();
        HoaDonmodel = (DefaultTableModel) tbHoaDon.getModel();
        tbBenhNhanKB.setModel(BenhNhanmodel);
        tbCTTTTT.setModel(CTTTmodel);
        tbBenhNhan.setDefaultEditor(Object.class, null);
        tbBenhNhanKB.setDefaultEditor(Object.class, null);
        tbCTTT.setDefaultEditor(Object.class, null);
        loadBenhNhan();
        loadCbTenDV();
        loadCbTenThuoc();
        loadHoaDon();
    }

    private void loadCbTenDV() {
        // Khởi tạo controller để lấy danh sách dịch vụ
        DichVuController dichVuController = new DichVuController();

        // Lấy danh sách dịch vụ
        List<DichVu> dichVuList = dichVuController.getALLDichVu();

        // Thêm các dịch vụ vào JComboBox
        for (DichVu dv : dichVuList) {
            cbTenDichVu.addItem(dv);
        }
    }

    private void loadCbTenThuoc() {
        // Khởi tạo controller để lấy danh sách dịch vụ
        ThuocController thuocController = new ThuocController();

        // Lấy danh sách dịch vụ
        List<Thuoc> thuocList = thuocController.getALLThuoc();

        // Thêm các dịch vụ vào JComboBox
        for (Thuoc thuoc : thuocList) {
            cbTenThuoc.addItem(thuoc);
        }
        cbTenThuoc.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Thuoc selectedThuoc = (Thuoc) cbTenThuoc.getSelectedItem();
                if (selectedThuoc != null) {
                    txtIDThuoc.setText(String.valueOf(selectedThuoc.getId()));
                    txtGiaThuoc.setText(String.valueOf(selectedThuoc.getGia())); // hiển thị giá vào JTextField
                }
            }
        });
        if (cbTenThuoc.getSelectedItem() != null) {
            Thuoc selectedThuoc = (Thuoc) cbTenThuoc.getSelectedItem();
            txtIDThuoc.setText(String.valueOf(selectedThuoc.getId()));
            txtGiaThuoc.setText(String.valueOf(selectedThuoc.getGia()));
        }
    }

    private void loadKhamBenh(int idBN) {

        List<KhamBenh> khambenhList = khambenhController.getKhamBenhByIDBN(idBN);

        DefaultTableModel model = (DefaultTableModel) tbKhamBenh.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        for (KhamBenh khambenh : khambenhList) {
            model.addRow(new Object[]{
                khambenh.getId(),
                khambenh.getIdBN(),
                khambenh.getIdDV(),
                khambenh.getTrieuChung(),
                khambenh.getChuanDoan(),
                khambenh.getTenDV(),
                khambenh.getGiaDV()
            });
        }
    }

    private void loadCTTT(int idKB) {

        List<ChiTietToaThuoc> ctttList = ctttController.getCTTTByIDKB(idKB);

        CTTTmodel.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        for (ChiTietToaThuoc cttt : ctttList) {
            CTTTmodel.addRow(new Object[]{
                cttt.getId(),
                cttt.getIdThuoc(),
                cttt.getIdKB(),
                cttt.getTenThuoc(),
                cttt.getSang(),
                cttt.getTrua(),
                cttt.getChieu(),
                cttt.getGia(),
                cttt.getTongGia()
            });
        }
    }

    public void loadBenhNhan() {
        List<BenhNhan> benhNhanList = benhNhanController.getALLBenhNhan();
        BenhNhanmodel.setRowCount(0);
        for (BenhNhan benhNhan : benhNhanList) {
            BenhNhanmodel.addRow(new Object[]{
                benhNhan.getId(),
                benhNhan.getHoTen(),
                benhNhan.getDiaChi(),
                benhNhan.getGioiTinh(),
                benhNhan.getSdt(),
                benhNhan.getNamSinh(),
                benhNhan.getTienSu()
            });
        }

    }

    public void loadHoaDon() {
        List<HoaDon> hoadonList = hoadonController.getAllHoaDon();

        HoaDonmodel.setRowCount(0);
        for (HoaDon hoadon : hoadonList) {
            HoaDonmodel.addRow(new Object[]{
                hoadon.getId(),
                hoadon.getIdKB(),
                hoadon.getNgayKham(),
                hoadon.getThanhTien()
            });
        }

    }

    private void thanhTien() {
        float thanhTien = 0;
        if (tbCTTTTT.getRowCount() > 0) {
            thanhTien = Float.parseFloat(txtGiaDVTT.getText());
            for (int i = 0; i < tbCTTTTT.getRowCount(); i++) {
                thanhTien += (float) tbCTTTTT.getValueAt(i, 8);
            }
        }
        txtThanhTien.setText(String.valueOf(thanhTien));
    }

    public void lamMoiBN() {
        this.txtIDBenhNhan.setText(null);
        this.txtHoTen.setText(null);
        this.txtDiaChi.setText(null);
        this.txtSdt.setText(null);
        this.txtNamSinh.setText(null);
        this.txtTienSu.setText(null);
    }

    private void lamMoiKB() {
        this.txtIDKB.setText(null);
        this.txtTrieuChung.setText(null);
        this.txtChuanDoan.setText(null);

    }

    private boolean checkSDT(String sdt) {
        if (sdt.length() != 10 && sdt.length() != 11) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIDBenhNhan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNamSinh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTienSu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbGioiTinh = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        btnThemBenhNhan = new javax.swing.JButton();
        btnSuaBenhNhan = new javax.swing.JButton();
        btnXoaBenhNhan = new javax.swing.JButton();
        btnXemBenhNhan = new javax.swing.JButton();
        btnLamMoiBenhNhan = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBenhNhan = new javax.swing.JTable();
        txtTimKiemBN = new javax.swing.JTextField();
        btnTimKiemBN = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtHoTenKB = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNamSinhKB = new javax.swing.JTextField();
        cbGioiTinhKB = new javax.swing.JComboBox<>();
        txtTienSuKB = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDiaChiKB = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtIDBN = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtIDKB = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTrieuChung = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtChuanDoan = new javax.swing.JTextField();
        btnThemKB = new javax.swing.JButton();
        btnSuaKB = new javax.swing.JButton();
        btnXoaKB = new javax.swing.JButton();
        btnLamMoiKB = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtIDDV = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cbTenDichVu = new javax.swing.JComboBox<>();
        txtGiaDV = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbBenhNhanKB = new javax.swing.JTable();
        txtTimKiemBNKB = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbKhamBenh = new javax.swing.JTable();
        jToolBar3 = new javax.swing.JToolBar();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtIDKBTT = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTrieuChungTT = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtChuanDoanTT = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtIDThuoc = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtGiaThuoc = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtSang = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTrua = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtChieu = new javax.swing.JTextField();
        btnThemCTTT = new javax.swing.JButton();
        btnSuaCTTT = new javax.swing.JButton();
        btnLamMoiCTTT = new javax.swing.JButton();
        cbTenThuoc = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        txtIDTT = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbCTTT = new javax.swing.JTable();
        jToolBar4 = new javax.swing.JToolBar();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbCTTTTT = new javax.swing.JTable();
        txtThanhTien = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txtTenDVTT = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtGiaDVTT = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NGHIỆP VỤ");

        jToolBar1.setRollover(true);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

        jPanel2.setBackground(new java.awt.Color(102, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin bệnh nhân"));

        jLabel1.setText("ID");

        txtIDBenhNhan.setEnabled(false);

        jLabel2.setText("Họ tên");

        jLabel3.setText("Năm sinh");

        txtNamSinh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamSinhKeyTyped(evt);
            }
        });

        jLabel4.setText("Địa chỉ");

        jLabel5.setText("Tiền sử");

        jLabel6.setText("Giới tính");

        cbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam ", "Nữ" }));

        jPanel4.setBackground(new java.awt.Color(102, 255, 255));

        btnThemBenhNhan.setBackground(new java.awt.Color(0, 255, 0));
        btnThemBenhNhan.setText("Thêm");
        btnThemBenhNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemBenhNhanActionPerformed(evt);
            }
        });

        btnSuaBenhNhan.setBackground(new java.awt.Color(0, 255, 0));
        btnSuaBenhNhan.setText("Sửa");
        btnSuaBenhNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaBenhNhanActionPerformed(evt);
            }
        });

        btnXoaBenhNhan.setBackground(new java.awt.Color(0, 255, 0));
        btnXoaBenhNhan.setText("Xoá");
        btnXoaBenhNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaBenhNhanActionPerformed(evt);
            }
        });

        btnXemBenhNhan.setBackground(new java.awt.Color(0, 255, 0));
        btnXemBenhNhan.setText("Xem");
        btnXemBenhNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemBenhNhanActionPerformed(evt);
            }
        });

        btnLamMoiBenhNhan.setBackground(new java.awt.Color(0, 255, 0));
        btnLamMoiBenhNhan.setText("Làm mới");
        btnLamMoiBenhNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiBenhNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnThemBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnSuaBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnXoaBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btnXemBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnLamMoiBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(206, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jLabel25.setText("Sđt");

        txtSdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSdtKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIDBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienSu, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtIDBenhNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtTienSu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách bệnh nhân"));

        tbBenhNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Họ tên", "Địa chỉ", "Giới tính", "Năm sinh", "Sđt", "Tiền sử"
            }
        ));
        tbBenhNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBenhNhanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBenhNhan);

        btnTimKiemBN.setBackground(new java.awt.Color(255, 153, 51));
        btnTimKiemBN.setText("Tìm kiếm");
        btnTimKiemBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemBNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemBN, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnTimKiemBN, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemBN))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToolBar1.add(jPanel1);

        jTabbedPane1.addTab("Tiếp nhận", jToolBar1);

        jToolBar2.setRollover(true);

        jPanel6.setBackground(new java.awt.Color(102, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin bệnh nhân"));

        jLabel7.setText("IDBN");

        jLabel8.setText("Họ tên");

        txtHoTenKB.setEnabled(false);

        jLabel9.setText("Năm sinh");

        txtNamSinhKB.setEnabled(false);

        cbGioiTinhKB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam ", "Nữ" }));
        cbGioiTinhKB.setEnabled(false);

        txtTienSuKB.setEnabled(false);

        jLabel10.setText("Địa chỉ");

        txtDiaChiKB.setEnabled(false);

        jLabel11.setText("Tiền sử");

        jLabel12.setText("Giới tính");

        txtIDBN.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienSuKB))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiaChiKB))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamSinhKB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbGioiTinhKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoTenKB, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIDBN)))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtIDBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtHoTenKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNamSinhKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGioiTinhKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDiaChiKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTienSuKB, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(40, 40, 40))
        );

        jPanel7.setBackground(new java.awt.Color(102, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khám bệnh"));

        jLabel13.setText("IDKB");

        txtIDKB.setEditable(false);

        jLabel14.setText("Triệu chứng");

        jLabel15.setText("Chuẩn đoán");

        btnThemKB.setBackground(new java.awt.Color(51, 255, 51));
        btnThemKB.setText("Thêm");
        btnThemKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKBActionPerformed(evt);
            }
        });

        btnSuaKB.setBackground(new java.awt.Color(51, 255, 51));
        btnSuaKB.setText("Sửa");
        btnSuaKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKBActionPerformed(evt);
            }
        });

        btnXoaKB.setBackground(new java.awt.Color(51, 255, 51));
        btnXoaKB.setText("Xoá");
        btnXoaKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKBActionPerformed(evt);
            }
        });

        btnLamMoiKB.setBackground(new java.awt.Color(51, 255, 51));
        btnLamMoiKB.setText("Làm mới");
        btnLamMoiKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiKBActionPerformed(evt);
            }
        });

        jLabel26.setText("IDDV");

        txtIDDV.setEditable(false);

        jLabel27.setText("Tên dịch vụ");

        jLabel28.setText("Giá");

        cbTenDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTenDichVuActionPerformed(evt);
            }
        });

        txtGiaDV.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnThemKB, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(btnSuaKB, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtIDKB, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTrieuChung))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtChuanDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaDV))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTenDichVu, 0, 241, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIDDV)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaKB, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnLamMoiKB, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtIDKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txtIDDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(cbTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtTrieuChung, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChuanDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel15)))
                .addGap(12, 12, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaKB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaKB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiKB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(102, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách bệnh nhân"));

        tbBenhNhanKB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ tên", "Địa chỉ", "Giới tính", "Năm sinh", "Sđt", "Tiền sử"
            }
        ));
        tbBenhNhanKB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBenhNhanKBMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbBenhNhanKB);

        jButton6.setBackground(new java.awt.Color(255, 153, 51));
        jButton6.setText("Tìm kiếm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(txtTimKiemBNKB, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemBNKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(102, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khám bệnh"));

        tbKhamBenh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDBN", "IDDV", "Triệu chứng", "Chuẩn đoán", "Tên dịch vụ", "Giá dịch vụ"
            }
        ));
        tbKhamBenh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhamBenhMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbKhamBenh);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jToolBar2.add(jPanel5);

        jTabbedPane1.addTab("Khám bệnh", jToolBar2);

        jToolBar3.setRollover(true);

        jPanel11.setBackground(new java.awt.Color(102, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khám bệnh"));

        jLabel16.setText("IDKB");

        txtIDKBTT.setEnabled(false);

        jLabel17.setText("Triệu chứng");

        txtTrieuChungTT.setEnabled(false);

        jLabel18.setText("Chuẩn đoán");

        txtChuanDoanTT.setEnabled(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDKBTT, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTrieuChungTT))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChuanDoanTT, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtIDKBTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(txtTrieuChungTT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(txtChuanDoanTT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(102, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin toa thuốc"));

        jLabel19.setText("IDThuốc");

        txtIDThuoc.setEnabled(false);

        jLabel20.setText("Tên thuốc");

        jLabel21.setText("Giá ");

        jLabel22.setText("Sáng");

        jLabel23.setText("Trưa");

        jLabel24.setText("Chiều");

        btnThemCTTT.setBackground(new java.awt.Color(0, 255, 0));
        btnThemCTTT.setText("Thêm");
        btnThemCTTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTTTActionPerformed(evt);
            }
        });

        btnSuaCTTT.setBackground(new java.awt.Color(51, 255, 51));
        btnSuaCTTT.setText("Sửa");
        btnSuaCTTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTTTActionPerformed(evt);
            }
        });

        btnLamMoiCTTT.setBackground(new java.awt.Color(51, 255, 51));
        btnLamMoiCTTT.setText("Làm mới");

        jLabel29.setText("IDTT");

        txtIDTT.setEnabled(false);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDTT, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSang, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(btnThemCTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(btnSuaCTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addComponent(btnLamMoiCTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(txtIDThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTrua))
                                    .addComponent(txtGiaThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(cbTenThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIDTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(txtSang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtTrua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(cbTenThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGiaThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaCTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiCTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(102, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách toa thuốc"));

        tbCTTT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID Thuốc", "ID Khám bệnh", "Tên thuốc", "Sáng", "Trưa", "Chiều", "Giá", "Tổng giá "
            }
        ));
        tbCTTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTTTMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbCTTT);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToolBar3.add(jPanel10);

        jTabbedPane1.addTab("Toa thuốc", jToolBar3);

        jToolBar4.setRollover(true);

        jPanel14.setBackground(new java.awt.Color(102, 255, 255));

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Toa thuốc"));

        tbCTTTTT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID Thuốc", "ID KB", "Tên thuốc", "Sáng", "Trưa ", "Chiều", "Giá", "Tổng giá"
            }
        ));
        jScrollPane5.setViewportView(tbCTTTTT);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        txtThanhTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtThanhTien.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtThanhTien.setText("0");

        btnThanhToan.setBackground(new java.awt.Color(255, 51, 51));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel30.setText("Tên dịch vụ");

        txtTenDVTT.setEnabled(false);

        jLabel31.setText("Giá dịch vụ");

        txtGiaDVTT.setEnabled(false);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hoá đơn"));

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDKB", "Ngày khám", "Thành tiền"
            }
        ));
        jScrollPane6.setViewportView(tbHoaDon);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenDVTT, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaDVTT, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtTenDVTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(txtGiaDVTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(45, Short.MAX_VALUE))))
        );

        jToolBar4.add(jPanel14);

        jTabbedPane1.addTab("Thanh toán", jToolBar4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemBenhNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemBenhNhanActionPerformed
        String hoTen = txtHoTen.getText();
        String diaChi = txtDiaChi.getText();
        String gioiTinh = cbGioiTinh.getSelectedItem().toString();
        String sdt = txtSdt.getText();
        int namSinh = Integer.parseInt(txtNamSinh.getText());
        String tienSu = txtTienSu.getText();
        if (hoTen.isEmpty() || sdt.isEmpty() || checkSDT(sdt) == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            BenhNhan benhNhan = new BenhNhan(0, hoTen, diaChi, gioiTinh, sdt, namSinh, tienSu);

            if (benhNhanController.addBenhNhan(benhNhan)) {
                JOptionPane.showMessageDialog(null, "Thêm bệnh nhân thành công!");
                loadBenhNhan();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm bệnh nhân thất bại!");
            }
        }
    }//GEN-LAST:event_btnThemBenhNhanActionPerformed

    private void btnXemBenhNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemBenhNhanActionPerformed
        loadBenhNhan();
    }//GEN-LAST:event_btnXemBenhNhanActionPerformed

    private void btnSuaBenhNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaBenhNhanActionPerformed
        int id = Integer.parseInt(txtIDBenhNhan.getText());
        String hoTen = txtHoTen.getText();
        String diaChi = txtDiaChi.getText();
        String gioiTinh = cbGioiTinh.getSelectedItem().toString();
        String sdt = txtSdt.getText();
        int namSinh = Integer.parseInt(txtNamSinh.getText());
        String tienSu = txtTienSu.getText();

        if (hoTen.isEmpty() || sdt.isEmpty() || checkSDT(sdt) == false) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            BenhNhan benhNhan = new BenhNhan(id, hoTen, diaChi, gioiTinh, sdt, namSinh, tienSu);

            if (benhNhanController.updateBenhNhan(benhNhan)) {
                JOptionPane.showMessageDialog(null, "Cập nhật bệnh nhân thành công!");
                loadBenhNhan();
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật bệnh nhân thất bại!");
            }
        }
    }//GEN-LAST:event_btnSuaBenhNhanActionPerformed

    private void tbBenhNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBenhNhanMouseClicked
        int row = this.tbBenhNhan.getSelectedRow();
        int id = (int) (this.tbBenhNhan.getModel().getValueAt(row, 0));
        BenhNhan rs = benhNhanController.getBenhNhanById(id);//Goi ham lay du lieu theo ma loai
        if (rs != null)//Neu co du lieu
        {
            this.txtIDBenhNhan.setText(String.valueOf(rs.getId()));
            this.txtHoTen.setText(rs.getHoTen());
            this.txtDiaChi.setText(rs.getDiaChi());
            this.cbGioiTinh.setSelectedItem(rs.getGioiTinh());
            this.txtSdt.setText(rs.getSdt());
            this.txtNamSinh.setText(String.valueOf(rs.getNamSinh()));
            this.txtTienSu.setText(rs.getTienSu());

        }
    }//GEN-LAST:event_tbBenhNhanMouseClicked

    private void btnXoaBenhNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaBenhNhanActionPerformed
        int id = Integer.parseInt(txtIDBenhNhan.getText());
        int result = JOptionPane.showConfirmDialog(null, "Bạn có chắn chắc muốn thanh toán", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (benhNhanController.deleteBenhNhan(id)) {
                JOptionPane.showMessageDialog(null, "Xóa bệnh nhân thành công!");
                loadBenhNhan();
                lamMoiBN();
            } else {
                JOptionPane.showMessageDialog(null, "Xóa bệnh nhân thất bại!");
            }
        }
    }//GEN-LAST:event_btnXoaBenhNhanActionPerformed

    private void btnLamMoiBenhNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiBenhNhanActionPerformed
        lamMoiBN();
    }//GEN-LAST:event_btnLamMoiBenhNhanActionPerformed

    private void txtSdtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSdtKeyTyped
        char c = evt.getKeyChar();
        // Kiểm tra nếu ký tự không phải là số
        if (!Character.isDigit(c)) {
            evt.consume(); // Nếu không phải số, loại bỏ ký tự
        }
    }//GEN-LAST:event_txtSdtKeyTyped

    private void txtNamSinhKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamSinhKeyTyped
        char c = evt.getKeyChar();
        // Kiểm tra nếu ký tự không phải là số
        if (!Character.isDigit(c)) {
            evt.consume(); // Nếu không phải số, loại bỏ ký tự
        }
    }//GEN-LAST:event_txtNamSinhKeyTyped

    private void cbTenDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTenDichVuActionPerformed

        DichVu selectedDichVu = (DichVu) cbTenDichVu.getSelectedItem();
        if (selectedDichVu != null) {
            txtIDDV.setText(String.valueOf(selectedDichVu.getID()));
            txtGiaDV.setText(String.valueOf(selectedDichVu.getGiaDV()));
        }
    }//GEN-LAST:event_cbTenDichVuActionPerformed

    private void btnTimKiemBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemBNActionPerformed
        String hoTen = txtTimKiemBN.getText().trim();

        if (hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên bệnh nhân", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        BenhNhan benhnhan = benhNhanController.getBenhNhanByName(hoTen);

        DefaultTableModel model = (DefaultTableModel) tbBenhNhan.getModel();
        model.setRowCount(0);

        if (benhnhan != null) {
            model.addRow(new Object[]{
                benhnhan.getId(),
                benhnhan.getHoTen(),
                benhnhan.getDiaChi(),
                benhnhan.getGioiTinh(),
                benhnhan.getSdt(),
                benhnhan.getNamSinh(),
                benhnhan.getTienSu()
            });
            int row = 0;
            tbBenhNhan.setRowSelectionInterval(row, row);
            tbBenhNhanMouseClicked(null);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân: " + hoTen, "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnTimKiemBNActionPerformed

    private void tbBenhNhanKBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBenhNhanKBMouseClicked
        int row = this.tbBenhNhanKB.getSelectedRow();
        int id = (int) (this.tbBenhNhanKB.getModel().getValueAt(row, 0));
        BenhNhan rs = benhNhanController.getBenhNhanById(id);//Goi ham lay du lieu theo ma loai
        if (rs != null)//Neu co du lieu
        {
            this.txtIDBN.setText(String.valueOf(rs.getId()));
            this.txtHoTenKB.setText(rs.getHoTen());
            this.txtDiaChiKB.setText(rs.getDiaChi());
            this.cbGioiTinhKB.setSelectedItem(rs.getGioiTinh());
            this.txtNamSinhKB.setText(String.valueOf(rs.getNamSinh()));
            this.txtTienSuKB.setText(rs.getTienSu());
        }
    }//GEN-LAST:event_tbBenhNhanKBMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String hoTen = txtTimKiemBNKB.getText().trim();

        if (hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên bệnh nhân", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        BenhNhan benhnhan = benhNhanController.getBenhNhanByName(hoTen);

        DefaultTableModel model = (DefaultTableModel) tbBenhNhanKB.getModel();
        model.setRowCount(0);

        if (benhnhan != null) {
            model.addRow(new Object[]{
                benhnhan.getId(),
                benhnhan.getHoTen(),
                benhnhan.getDiaChi(),
                benhnhan.getGioiTinh(),
                benhnhan.getSdt(),
                benhnhan.getNamSinh(),
                benhnhan.getTienSu()
            });
            int row = 0;
            tbBenhNhanKB.setRowSelectionInterval(row, row);
            tbBenhNhanMouseClicked(null);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân: " + hoTen, "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnThemKBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKBActionPerformed
        int idBN = Integer.parseInt(txtIDBN.getText());
        int idDV = Integer.parseInt(txtIDDV.getText());
        String trieuChung = txtTrieuChung.getText();
        String chuanDoan = txtChuanDoan.getText();
        String tenDV = cbTenDichVu.getSelectedItem().toString();
        float giaDV = Float.parseFloat(txtGiaDV.getText());
        if (trieuChung.isEmpty() || chuanDoan.isEmpty() || tenDV.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                KhamBenh khamBenh = new KhamBenh(0, idBN, idDV, trieuChung, chuanDoan, tenDV, giaDV);

                if (khambenhController.addKhamBenh(khamBenh)) {
                    JOptionPane.showMessageDialog(null, "Thêm thông tin khám bệnh thành công!");
                    loadKhamBenh(idBN);
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thông tin khám bệnh thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnThemKBActionPerformed

    private void tbKhamBenhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhamBenhMouseClicked
        int row = this.tbKhamBenh.getSelectedRow();
        int id = (int) (this.tbKhamBenh.getModel().getValueAt(row, 0));
        KhamBenh rs = khambenhController.getKhamBenhByID(id);
        if (rs != null)//Neu co du lieu
        {
            this.txtIDKB.setText(String.valueOf(rs.getId()));
            this.txtTrieuChung.setText(rs.getTrieuChung());
            this.txtChuanDoan.setText(rs.getChuanDoan());
            this.cbTenDichVu.setSelectedItem(rs.getTenDV());
            this.txtGiaDV.setText(String.valueOf(rs.getGiaDV()));

            this.txtIDKBTT.setText(String.valueOf(rs.getId()));
            this.txtTrieuChungTT.setText(rs.getTrieuChung());
            this.txtChuanDoanTT.setText(rs.getChuanDoan());

            this.txtTenDVTT.setText(rs.getTenDV());
            this.txtGiaDVTT.setText(String.valueOf(rs.getGiaDV()));

        }
    }//GEN-LAST:event_tbKhamBenhMouseClicked

    private void btnSuaKBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKBActionPerformed
        int id = Integer.parseInt(txtIDKB.getText());
        int idBN = Integer.parseInt(txtIDBN.getText());
        int idDV = Integer.parseInt(txtIDDV.getText());
        String trieuChung = txtTrieuChung.getText();
        String chuanDoan = txtChuanDoan.getText();
        String tenDV = cbTenDichVu.getSelectedItem().toString();
        float giaDV = Float.parseFloat(txtGiaDV.getText());

        if (trieuChung.isEmpty() || chuanDoan.isEmpty() || tenDV.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            try {

                KhamBenh khamBenh = new KhamBenh(id, idBN, idDV, trieuChung, chuanDoan, tenDV, giaDV);

                if (khambenhController.updateKhamBenh(khamBenh)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thông tin khám bệnh thành công!");
                    loadKhamBenh(idBN);
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thông tin khám bệnh thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSuaKBActionPerformed

    private void btnXoaKBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKBActionPerformed
        int id = Integer.parseInt(txtIDKB.getText());
        int idBN = Integer.parseInt(txtIDBN.getText());
        int result = JOptionPane.showConfirmDialog(null, "Bạn có chắn chắc muốn xoá?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (khambenhController.deleteKhamBenh(id)) {
                JOptionPane.showMessageDialog(null, "Xóa thông tin khám bệnh thành công!");
                loadKhamBenh(idBN);
                lamMoiKB();
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thông tin khám bệnh thất bại!");
            }
        }
    }//GEN-LAST:event_btnXoaKBActionPerformed

    private void btnLamMoiKBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiKBActionPerformed
        lamMoiKB();
    }//GEN-LAST:event_btnLamMoiKBActionPerformed

    private void btnThemCTTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTTTActionPerformed
        try {
            int idKB = Integer.parseInt(txtIDKBTT.getText());
            int idThuoc = Integer.parseInt(txtIDThuoc.getText());
            String tenThuoc = cbTenThuoc.getSelectedItem().toString();
            int sang = Integer.parseInt(txtSang.getText());
            int trua = Integer.parseInt(txtTrua.getText());
            int chieu = Integer.parseInt(txtChieu.getText());
            float gia = Float.parseFloat(txtGiaThuoc.getText());

            ChiTietToaThuoc cttt = new ChiTietToaThuoc(0, idThuoc, idKB, tenThuoc, sang, trua, chieu, gia);

            if (ctttController.addCTTT(cttt)) {
                JOptionPane.showMessageDialog(null, "Thêm thông tin toa thuốc thành công!");
                loadCTTT(idKB);
                thanhTien();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thông tin toa thuốc thất bại!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnThemCTTTActionPerformed

    private void tbCTTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTTTMouseClicked
        int row = this.tbCTTT.getSelectedRow();
        int id = (int) (this.tbCTTT.getModel().getValueAt(row, 0));
        ChiTietToaThuoc rs = ctttController.getCTTTById(id);
        if (rs != null) {
            this.txtIDTT.setText(String.valueOf(rs.getId()));
            this.txtIDThuoc.setText(String.valueOf(rs.getIdThuoc()));
            this.cbTenThuoc.setSelectedItem(rs.getTenThuoc());
            this.txtGiaThuoc.setText(String.valueOf(rs.getGia()));
            this.txtSang.setText(String.valueOf(rs.getSang()));
            this.txtTrua.setText(String.valueOf(rs.getTrua()));
            this.txtChieu.setText(String.valueOf(rs.getChieu()));
        }
    }//GEN-LAST:event_tbCTTTMouseClicked

    private void btnSuaCTTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTTTActionPerformed
        try {
            int id = Integer.parseInt(txtIDTT.getText());
            int idKB = Integer.parseInt(txtIDKBTT.getText());
            int idThuoc = Integer.parseInt(txtIDThuoc.getText());
            String tenThuoc = cbTenThuoc.getSelectedItem().toString();
            int sang = Integer.parseInt(txtSang.getText());
            int trua = Integer.parseInt(txtTrua.getText());
            int chieu = Integer.parseInt(txtChieu.getText());
            float gia = Float.parseFloat(txtGiaThuoc.getText());

            ChiTietToaThuoc cttt = new ChiTietToaThuoc(id, idThuoc, idKB, tenThuoc, sang, trua, chieu, gia);

            if (ctttController.updateCTTT(cttt)) {
                JOptionPane.showMessageDialog(null, "Cập nhật thông tin toa thuốc thành công!");
                loadCTTT(idKB);
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật thông tin toa thuốc thất bại!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSuaCTTTActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int idKB = Integer.parseInt(txtIDKBTT.getText());
        float thanhTien = Float.parseFloat(txtThanhTien.getText());
        int result = JOptionPane.showConfirmDialog(null, "Bạn có chắn chắc muốn thanh toán", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            HoaDon hoadon = new HoaDon(idKB, thanhTien);

            if (hoadonController.addHoaDon(hoadon)) {
                JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
                loadHoaDon();
                this.txtThanhTien.setText("0");
                CTTTmodel.setRowCount(0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Có lỗi khi thanh toán");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frNghiepVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frNghiepVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frNghiepVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frNghiepVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frNghiepVu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiBenhNhan;
    private javax.swing.JButton btnLamMoiCTTT;
    private javax.swing.JButton btnLamMoiKB;
    private javax.swing.JButton btnSuaBenhNhan;
    private javax.swing.JButton btnSuaCTTT;
    private javax.swing.JButton btnSuaKB;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemBenhNhan;
    private javax.swing.JButton btnThemCTTT;
    private javax.swing.JButton btnThemKB;
    private javax.swing.JButton btnTimKiemBN;
    private javax.swing.JButton btnXemBenhNhan;
    private javax.swing.JButton btnXoaBenhNhan;
    private javax.swing.JButton btnXoaKB;
    private javax.swing.JComboBox<String> cbGioiTinh;
    private javax.swing.JComboBox<String> cbGioiTinhKB;
    private javax.swing.JComboBox<DichVu> cbTenDichVu;
    private javax.swing.JComboBox<Thuoc> cbTenThuoc;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JTable tbBenhNhan;
    private javax.swing.JTable tbBenhNhanKB;
    private javax.swing.JTable tbCTTT;
    private javax.swing.JTable tbCTTTTT;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbKhamBenh;
    private javax.swing.JTextField txtChieu;
    private javax.swing.JTextField txtChuanDoan;
    private javax.swing.JTextField txtChuanDoanTT;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChiKB;
    private javax.swing.JTextField txtGiaDV;
    private javax.swing.JTextField txtGiaDVTT;
    private javax.swing.JTextField txtGiaThuoc;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtHoTenKB;
    private javax.swing.JTextField txtIDBN;
    private javax.swing.JTextField txtIDBenhNhan;
    private javax.swing.JTextField txtIDDV;
    private javax.swing.JTextField txtIDKB;
    private javax.swing.JTextField txtIDKBTT;
    private javax.swing.JTextField txtIDTT;
    private javax.swing.JTextField txtIDThuoc;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtNamSinhKB;
    private javax.swing.JTextField txtSang;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenDVTT;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTienSu;
    private javax.swing.JTextField txtTienSuKB;
    private javax.swing.JTextField txtTimKiemBN;
    private javax.swing.JTextField txtTimKiemBNKB;
    private javax.swing.JTextField txtTrieuChung;
    private javax.swing.JTextField txtTrieuChungTT;
    private javax.swing.JTextField txtTrua;
    // End of variables declaration//GEN-END:variables
}
