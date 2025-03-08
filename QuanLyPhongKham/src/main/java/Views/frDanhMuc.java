/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Controllers.DichVuController;
import Controllers.ThuocController;
import Models.DichVu;
import Models.Thuoc;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public final class frDanhMuc extends javax.swing.JFrame {

    private final ThuocController thuocController;
    private final DichVuController dichVuController;
    private List<DichVu> dsdv;

    public frDanhMuc() {

        initComponents();
        thuocController = new ThuocController();
        dichVuController = new DichVuController();
        new DefaultTableModel();
        tbThuoc.setDefaultEditor(Object.class, null); //kh cho phép chỉnh sửa table
        tbDichVu.setDefaultEditor(Object.class, null);
        loadThuoc();
        loadDichVu();
    }

    private void loadThuoc() {
        List<Thuoc> thuocList = thuocController.getALLThuoc(); // Lấy dữ liệu từ controller
        DefaultTableModel model = (DefaultTableModel) tbThuoc.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        for (Thuoc thuoc : thuocList) {
            model.addRow(new Object[]{
                thuoc.getId(),
                thuoc.getTenThuoc(),
                thuoc.getSoLuong(),
                thuoc.getGia(),
                thuoc.getDonViTinh()
            });
        }
    }

    public void loadDichVu() {
        List<DichVu> dichVuList = dichVuController.getALLDichVu();
        DefaultTableModel model = (DefaultTableModel) tbDichVu.getModel();
        model.setRowCount(0);
        for (DichVu dichvu : dichVuList) {
            model.addRow(new Object[]{
                dichvu.getID(),
                dichvu.getTenDV(),
                dichvu.getGiaDV(),});
        }
    }

    public List<DichVu> getdichvu(){
     return dsdv;   
    }
    private void lamMoiThuoc(){
        this.txtIDThuoc.setText(null);
        this.txtTenThuoc.setText(null);
        this.txtSoLuong.setText(null);
        this.txtGiaThuoc.setText(null);
    }
    
    private void lamMoiDV(){
        this.txtIDDichVu.setText(null);
        this.txtTenDichVu.setText(null);
        this.txtGiaDichVu.setText(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIDThuoc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenThuoc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbDonViTinh = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtGiaThuoc = new javax.swing.JTextField();
        btnThemThuoc = new javax.swing.JButton();
        btnSuaThuoc = new javax.swing.JButton();
        btnXoaThuoc = new javax.swing.JButton();
        btnXemThuoc = new javax.swing.JButton();
        btnLamMoiThuoc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbThuoc = new javax.swing.JTable();
        txtTimKiemThuoc = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jToolBar3 = new javax.swing.JToolBar();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtIDDichVu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTenDichVu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtGiaDichVu = new javax.swing.JTextField();
        btnThemDV = new javax.swing.JButton();
        btnSuaDV = new javax.swing.JButton();
        btnXoaDV = new javax.swing.JButton();
        btnXemDV = new javax.swing.JButton();
        btnLamMoiDV = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDichVu = new javax.swing.JTable();
        txtTimKiemDV = new javax.swing.JTextField();
        btnTimKiemDV = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DANH MỤC");

        jToolBar2.setRollover(true);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thuốc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel1.setText("ID");

        txtIDThuoc.setEnabled(false);

        jLabel2.setText("Tên thuốc");

        jLabel3.setText("Số lượng");

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyTyped(evt);
            }
        });

        jLabel4.setText("Đơn vị tính");

        cbDonViTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Viên", "Lọ ", "Chai" }));

        jLabel5.setText("Giá");

        txtGiaThuoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiaThuocKeyTyped(evt);
            }
        });

        btnThemThuoc.setBackground(new java.awt.Color(0, 255, 0));
        btnThemThuoc.setText("Thêm");
        btnThemThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocActionPerformed(evt);
            }
        });

        btnSuaThuoc.setBackground(new java.awt.Color(0, 255, 0));
        btnSuaThuoc.setText("Sửa");
        btnSuaThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocActionPerformed(evt);
            }
        });

        btnXoaThuoc.setBackground(new java.awt.Color(0, 255, 0));
        btnXoaThuoc.setText("Xoá");
        btnXoaThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThuocActionPerformed(evt);
            }
        });

        btnXemThuoc.setBackground(new java.awt.Color(51, 255, 51));
        btnXemThuoc.setText("Xem");
        btnXemThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemThuocActionPerformed(evt);
            }
        });

        btnLamMoiThuoc.setBackground(new java.awt.Color(0, 255, 0));
        btnLamMoiThuoc.setText("Làm mới");
        btnLamMoiThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnThemThuoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addComponent(btnSuaThuoc)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaThuoc)
                        .addGap(8, 8, 8))
                    .addComponent(txtTenThuoc, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIDThuoc)
                    .addComponent(txtGiaThuoc, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbDonViTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSoLuong))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnXemThuoc)
                .addGap(32, 32, 32)
                .addComponent(btnLamMoiThuoc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách thuốc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tbThuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên thuốc", "Số lượng", "Giá", "Đơn vị tính"
            }
        ));
        tbThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbThuocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbThuoc);

        btnTimKiem.setBackground(new java.awt.Color(255, 153, 51));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addComponent(txtTimKiemThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiem)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jToolBar2.add(jPanel2);

        jTabbedPane1.addTab("Thuốc", jToolBar2);

        jToolBar3.setRollover(true);

        jPanel5.setBackground(new java.awt.Color(153, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin danh mục", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(283, 304));

        jLabel6.setText("ID");

        txtIDDichVu.setEnabled(false);

        jLabel7.setText("Tên dịch vụ");

        jLabel8.setText("Giá");

        txtGiaDichVu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiaDichVuKeyTyped(evt);
            }
        });

        btnThemDV.setBackground(new java.awt.Color(0, 255, 0));
        btnThemDV.setText("Thêm");
        btnThemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVActionPerformed(evt);
            }
        });

        btnSuaDV.setBackground(new java.awt.Color(0, 255, 0));
        btnSuaDV.setText("Sửa");
        btnSuaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDVActionPerformed(evt);
            }
        });

        btnXoaDV.setBackground(new java.awt.Color(0, 255, 0));
        btnXoaDV.setText("Xoá");
        btnXoaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDVActionPerformed(evt);
            }
        });

        btnXemDV.setBackground(new java.awt.Color(0, 255, 0));
        btnXemDV.setText("Xem");
        btnXemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemDVActionPerformed(evt);
            }
        });

        btnLamMoiDV.setBackground(new java.awt.Color(0, 255, 0));
        btnLamMoiDV.setText("Làm mới");
        btnLamMoiDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnThemDV)
                                .addGap(18, 18, 18)
                                .addComponent(btnSuaDV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaDV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGiaDichVu))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIDDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTenDichVu)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnXemDV)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoiDV)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemDV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemDV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiDV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(153, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tbDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tên dịch vụ", "Giá"
            }
        ));
        tbDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDichVuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbDichVu);

        btnTimKiemDV.setBackground(new java.awt.Color(255, 153, 51));
        btnTimKiemDV.setText("Tìm kiếm");
        btnTimKiemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtTimKiemDV, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiemDV))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemDV))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jToolBar3.add(jPanel4);

        jTabbedPane1.addTab("Dịch vụ", jToolBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocActionPerformed
        int id = Integer.parseInt(txtIDThuoc.getText()); // ID thuốc (phải lấy từ bảng hoặc TextField)
        String tenThuoc = txtTenThuoc.getText();
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        float gia = Float.parseFloat(txtGiaThuoc.getText());
        String donViTinh = cbDonViTinh.getSelectedItem().toString();

        if (tenThuoc.isEmpty() || donViTinh.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            Thuoc thuoc = new Thuoc(id, tenThuoc, soLuong, donViTinh, gia);

            if (thuocController.updateThuoc(thuoc)) {
                JOptionPane.showMessageDialog(null, "Cập nhật thuốc thành công!");
                loadThuoc(); // Tải lại dữ liệu vào bảng
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật thuốc thất bại!");
            }
        }
    }//GEN-LAST:event_btnSuaThuocActionPerformed

    private void btnXoaThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThuocActionPerformed
        int id = Integer.parseInt(txtIDThuoc.getText()); // ID thuốc (phải lấy từ bảng hoặc TextField)

        if (thuocController.deleteThuoc(id)) {
            JOptionPane.showMessageDialog(null, "Xóa thuốc thành công!");
            loadThuoc(); // Tải lại dữ liệu vào bảng
            lamMoiThuoc();
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thuốc thất bại!");
        }
    }//GEN-LAST:event_btnXoaThuocActionPerformed

    private void btnXemThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemThuocActionPerformed
        loadThuoc();
    }//GEN-LAST:event_btnXemThuocActionPerformed

    private void btnLamMoiThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiThuocActionPerformed
        lamMoiThuoc();
    }//GEN-LAST:event_btnLamMoiThuocActionPerformed

    private void btnThemThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocActionPerformed
        String tenThuoc = txtTenThuoc.getText();
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        float gia = Float.parseFloat(txtGiaThuoc.getText());
        String donViTinh = cbDonViTinh.getSelectedItem().toString();

        // Thêm thuốc mới vào cơ sở dữ liệu
        Thuoc thuoc = new Thuoc(0, tenThuoc, soLuong, donViTinh, gia);

        if (thuocController.addThuoc(thuoc)) {
            JOptionPane.showMessageDialog(null, "Thêm thuốc thành công!");
            loadThuoc(); // Tải lại dữ liệu vào bảng
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thuốc thất bại!");
        }
    }//GEN-LAST:event_btnThemThuocActionPerformed

    private void tbThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThuocMouseClicked
        //Lay chi so dong dang chon
        int row = this.tbThuoc.getSelectedRow();
        int id = (int) (this.tbThuoc.getModel().getValueAt(row, 0));
        Thuoc rs = thuocController.getThuocById(id);//Goi ham lay du lieu theo ma loai
        if (rs != null)//Neu co du lieu
        {
            this.txtIDThuoc.setText(String.valueOf(rs.getId()));
            this.txtTenThuoc.setText(rs.getTenThuoc());
            this.txtSoLuong.setText(String.valueOf(rs.getSoLuong()));
            this.txtGiaThuoc.setText(String.valueOf(rs.getGia()));
            this.cbDonViTinh.setSelectedItem(rs.getDonViTinh());
        }
    }//GEN-LAST:event_tbThuocMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // Lấy tên thuốc từ TextField
        String tenThuoc = txtTimKiemThuoc.getText().trim();

        // Kiểm tra nếu tên thuốc rỗng
        if (tenThuoc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thuốc", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Gọi phương thức tìm thuốc theo tên
        Thuoc thuoc = thuocController.getThuocByName(tenThuoc);

        // Lấy DefaultTableModel của JTable
        DefaultTableModel model = (DefaultTableModel) tbThuoc.getModel();

        // Xóa tất cả các hàng cũ trong JTable
        model.setRowCount(0);

        // Kiểm tra nếu tìm thấy thuốc
        if (thuoc != null) {
            // Thêm dòng thuốc tìm thấy vào JTable
            model.addRow(new Object[]{
                thuoc.getId(),
                thuoc.getTenThuoc(),
                thuoc.getSoLuong(),
                thuoc.getDonViTinh(),
                thuoc.getGia()
            });
            int row = 0;
            // Tạo sự kiện giả lập MouseClicked trên JTable
            tbThuoc.setRowSelectionInterval(row, row); // Chọn dòng đầu tiên
            tbThuocMouseClicked(null);
        } else {
            // Nếu không tìm thấy thuốc, hiển thị thông báo
            JOptionPane.showMessageDialog(this, "Không tìm thấy thuốc: " + tenThuoc, "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtSoLuongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyTyped
        char c = evt.getKeyChar();
        // Kiểm tra nếu ký tự không phải là số
        if (!Character.isDigit(c)) {
            evt.consume(); // Nếu không phải số, loại bỏ ký tự
        }
    }//GEN-LAST:event_txtSoLuongKeyTyped

    private void txtGiaThuocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaThuocKeyTyped
        char c = evt.getKeyChar();
        // Kiểm tra nếu ký tự không phải là số 
        if (!Character.isDigit(c)) {
            evt.consume(); // Nếu không phải số, loại bỏ ký tự
        }
    }//GEN-LAST:event_txtGiaThuocKeyTyped

    private void btnThemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVActionPerformed
        String tenDV = txtTenDichVu.getText();
        float giaDV = Float.parseFloat(txtGiaDichVu.getText());

        if (tenDV.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            // Thêm thuốc mới vào cơ sở dữ liệu
            DichVu dichvu = new DichVu(0, tenDV, giaDV);

            if (dichVuController.addDichVu(dichvu)) {
                JOptionPane.showMessageDialog(null, "Thêm dịch vụ thành công!");
                loadDichVu(); // Tải lại dữ liệu vào bảng
            } else {
                JOptionPane.showMessageDialog(null, "Thêm dịch vụ thất bại!");
            }
        }

    }//GEN-LAST:event_btnThemDVActionPerformed

    private void tbDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDichVuMouseClicked
        int row = this.tbDichVu.getSelectedRow();
        int id = (int) (this.tbDichVu.getModel().getValueAt(row, 0));
        DichVu rs = dichVuController.getDichVuById(id);//Goi ham lay du lieu theo ma loai
        if (rs != null)//Neu co du lieu
        {
            this.txtIDDichVu.setText(String.valueOf(rs.getID()));
            this.txtTenDichVu.setText(rs.getTenDV());
            this.txtGiaDichVu.setText(String.valueOf(rs.getGiaDV()));
        }
    }//GEN-LAST:event_tbDichVuMouseClicked

    private void btnSuaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDVActionPerformed
        int id = Integer.parseInt(txtIDDichVu.getText());
        String tenDV = txtTenDichVu.getText();
        float giaDV = Float.parseFloat(txtGiaDichVu.getText());

        // Thêm thuốc mới vào cơ sở dữ liệu
        DichVu dichvu = new DichVu(id, tenDV, giaDV);
        if (dichVuController.updateDichVu(dichvu)) {
            JOptionPane.showMessageDialog(null, "Cập nhật dịch vụ thành công!");
            loadDichVu(); // Tải lại dữ liệu vào bảng
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật dịch vụ thất bại!");
        }
    }//GEN-LAST:event_btnSuaDVActionPerformed

    private void btnXoaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDVActionPerformed
        int id = Integer.parseInt(txtIDDichVu.getText()); // ID thuốc (phải lấy từ bảng hoặc TextField)

        if (dichVuController.deleteDichVu(id)) {
            JOptionPane.showMessageDialog(null, "Xóa dịch vụ thành công!");
            loadDichVu(); // Tải lại dữ liệu vào bảng
        } else {
            JOptionPane.showMessageDialog(null, "Xóa dịch vụ thất bại!");
        }
    }//GEN-LAST:event_btnXoaDVActionPerformed

    private void btnLamMoiDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiDVActionPerformed
        lamMoiDV();
    }//GEN-LAST:event_btnLamMoiDVActionPerformed

    private void btnXemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemDVActionPerformed
        loadDichVu();
    }//GEN-LAST:event_btnXemDVActionPerformed

    private void txtGiaDichVuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaDichVuKeyTyped
        char c = evt.getKeyChar();
        // Kiểm tra nếu ký tự không phải là số
        if (!Character.isDigit(c)) {
            evt.consume(); // Nếu không phải số, loại bỏ ký tự
        }
    }//GEN-LAST:event_txtGiaDichVuKeyTyped

    private void btnTimKiemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemDVActionPerformed
        // Lấy tên thuốc từ TextField
        String tenDV = txtTimKiemDV.getText().trim();

        // Kiểm tra nếu tên thuốc rỗng
        if (tenDV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên dịch vụ", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Gọi phương thức tìm thuốc theo tên
        DichVu dichvu = dichVuController.getDichVuByName(tenDV);

        // Lấy DefaultTableModel của JTable
        DefaultTableModel model = (DefaultTableModel) tbDichVu.getModel();

        // Xóa tất cả các hàng cũ trong JTable
        model.setRowCount(0);

        // Kiểm tra nếu tìm thấy thuốc
        if (dichvu != null) {
            // Thêm dòng thuốc tìm thấy vào JTable
            model.addRow(new Object[]{
                dichvu.getID(),
                dichvu.getTenDV(),
                dichvu.getGiaDV()
            });
            int row = 0;
            // Tạo sự kiện giả lập MouseClicked trên JTable
            tbDichVu.setRowSelectionInterval(row, row); // Chọn dòng đầu tiên
            tbDichVuMouseClicked(null);
        } else {
            // Nếu không tìm thấy thuốc, hiển thị thông báo
            JOptionPane.showMessageDialog(this, "Không tìm thấy dịch vụ: " + tenDV, "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnTimKiemDVActionPerformed

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
            java.util.logging.Logger.getLogger(frDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frDanhMuc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiDV;
    private javax.swing.JButton btnLamMoiThuoc;
    private javax.swing.JButton btnSuaDV;
    private javax.swing.JButton btnSuaThuoc;
    private javax.swing.JButton btnThemDV;
    private javax.swing.JButton btnThemThuoc;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemDV;
    private javax.swing.JButton btnXemDV;
    private javax.swing.JButton btnXemThuoc;
    private javax.swing.JButton btnXoaDV;
    private javax.swing.JButton btnXoaThuoc;
    private javax.swing.JComboBox<String> cbDonViTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JTable tbDichVu;
    private javax.swing.JTable tbThuoc;
    private javax.swing.JTextField txtGiaDichVu;
    private javax.swing.JTextField txtGiaThuoc;
    private javax.swing.JTextField txtIDDichVu;
    private javax.swing.JTextField txtIDThuoc;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenDichVu;
    private javax.swing.JTextField txtTenThuoc;
    private javax.swing.JTextField txtTimKiemDV;
    private javax.swing.JTextField txtTimKiemThuoc;
    // End of variables declaration//GEN-END:variables
}
