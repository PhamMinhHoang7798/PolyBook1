package ui.staff;

import dao.impl.SanPhamDAOImpl;
import dao.impl.TheThanhVienDAOImpl;
import entity.SanPham;
import entity.TheThanhVien;
import util.XJdbc;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Timkiem extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Timkiem.class.getName());

    public Timkiem() {
        initComponents();
        
        fillTableHoaDon("");
        fillTableSanPham();
        tblTimTheThanhVien.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "SĐT", "Tên khách hàng", "Loại thẻ", "Điểm"
                }
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        fillTableThanhVien("");
        setPlaceholder(txtMaHoaDon, "Nhập mã hóa đơn...");
        setPlaceholder(txtTuNgay, "yyyy-MM-dd");
        setPlaceholder(txtDenNgay, "yyyy-MM-dd");
        setPlaceholder(txtMaSanPham, "Nhập mã hoặc tên sản phẩm...");
        setPlaceholder(txtSDTThanhVien, "Nhập số điện thoại...");
        tblTimKiemHoaDon.setDefaultEditor(Object.class, null);
        tblTimKiemSanPham.setDefaultEditor(Object.class, null);
        tblTimTheThanhVien.setDefaultEditor(Object.class, null);

    }

    void fillTableHoaDon(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblTimKiemHoaDon.getModel();
        model.setRowCount(0);
        String sql = """
        SELECT hd.MaHoaDon, kh.TenKhachHang, sp.TenSanPham, ct.SoLuong, ct.Gia
        FROM HoaDon hd
        JOIN HoaDonChiTiet ct ON hd.MaHoaDon = ct.MaHoaDon
        JOIN SanPham sp ON ct.MaSanPham = sp.MaSanPham
        JOIN KhachHang kh ON hd.MaKhachHang = kh.MaKhachHang
        WHERE hd.MaHoaDon LIKE ?
    """;
        try {
            ResultSet rs = XJdbc.query(sql, "%" + keyword + "%");
            while (rs.next()) {
                Object[] row = {
                    rs.getString("MaHoaDon"),
                    rs.getString("TenKhachHang"),
                    rs.getString("TenSanPham"),
                    rs.getInt("SoLuong"),
                    rs.getDouble("Gia")
                };
                model.addRow(row);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setPlaceholder(javax.swing.JTextField txt, String text) {
        txt.setText(text);
        txt.setForeground(java.awt.Color.GRAY);
        txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txt.getText().equals(text)) {
                    txt.setText("");
                    txt.setForeground(java.awt.Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txt.getText().isEmpty()) {
                    txt.setText(text);
                    txt.setForeground(java.awt.Color.GRAY);
                }
            }
        });
    }

    boolean isValidDate(String date) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    void fillTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblTimKiemSanPham.getModel();
        model.setRowCount(0);
        String sql = "SELECT MaSanPham, TenSanPham, SoLuongTon, DonGia FROM SanPham";
        try {
            ResultSet rs = XJdbc.query(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("MaSanPham"),
                    rs.getString("TenSanPham"),
                    rs.getInt("SoLuongTon"),
                    rs.getDouble("DonGia")
                });
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void fillTableThanhVien(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblTimTheThanhVien.getModel();
        model.setRowCount(0);
        try {
            TheThanhVienDAOImpl dao = new TheThanhVienDAOImpl();
            List<TheThanhVien> list = dao.selectByKeyword(keyword);
            boolean hasData = false;
            for (TheThanhVien tv : list) {
                hasData = true;
                int diem = tv.getDiemTichLuy();
                String loaiThe;
                if (diem >= 200) {
                    loaiThe = " Vàng";
                } else if (diem >= 150) {
                    loaiThe = " Bạc";
                } else if (diem >= 100) {
                    loaiThe = " Đồng";
                } else {
                    loaiThe = "Thường";
                }
                model.addRow(new Object[]{
                    tv.getSdt(),
                    tv.getTenKhachHang(),
                    loaiThe,
                    diem
                });
            }
            if (!hasData) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thành viên!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn!");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTimKiemHoaDon = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        txtTuNgay = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtDenNgay = new javax.swing.JTextField();
        btnLoc = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        btnTimHoaDon = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTimKiemSanPham = new javax.swing.JTable();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        btnTimSP = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTimTheThanhVien = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        txtSDTThanhVien = new javax.swing.JTextField();
        btnTimSDTthanhVien = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(59, 77, 169));
        jLabel1.setText("Tìm kiếm");

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblTimKiemHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblTimKiemHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên Khách hàng", "Tên sản phẩm", "Số lượng", "Giá"
            }
        ));
        tblTimKiemHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTimKiemHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTimKiemHoaDon);
        if (tblTimKiemHoaDon.getColumnModel().getColumnCount() > 0) {
            tblTimKiemHoaDon.getColumnModel().getColumn(1).setHeaderValue("Tên Khách hàng");
        }

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setText("Từ ngày: ");

        txtTuNgay.setColumns(8);
        txtTuNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTuNgayActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setText("Đến ngày: ");

        txtDenNgay.setColumns(8);
        txtDenNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDenNgayActionPerformed(evt);
            }
        });

        btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoc.setForeground(new java.awt.Color(12, 66, 139));
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setText("Mã hóa đơn:");

        txtMaHoaDon.setColumns(8);
        txtMaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHoaDonActionPerformed(evt);
            }
        });

        btnTimHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimHoaDon.setForeground(new java.awt.Color(12, 66, 139));
        btnTimHoaDon.setText("Tìm");
        btnTimHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(btnTimHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
        );

        jTabbedPane1.addTab("Tìm kiếm hóa đơn", jPanel2);

        tblTimKiemSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá"
            }
        ));
        tblTimKiemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTimKiemSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblTimKiemSanPham);

        txtMaSanPham.setColumns(8);
        txtMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanPhamActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("Mã sản phẩm:");

        btnTimSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimSP.setForeground(new java.awt.Color(12, 66, 139));
        btnTimSP.setText("Tìm");
        btnTimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tìm kiếm sản phẩm", jPanel3);

        tblTimTheThanhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Số điện thoại thành viên:", "Tên Khách hàng", "Loại thẻ", "Điểm hiện có"
            }
        ));
        tblTimTheThanhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTimTheThanhVienMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblTimTheThanhVien);

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setText("Số điện thoại thành viên:");

        txtSDTThanhVien.setColumns(8);
        txtSDTThanhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTThanhVienActionPerformed(evt);
            }
        });

        btnTimSDTthanhVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimSDTthanhVien.setForeground(new java.awt.Color(12, 66, 139));
        btnTimSDTthanhVien.setText("Tìm");
        btnTimSDTthanhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSDTthanhVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDTThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimSDTthanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37)
                        .addComponent(btnTimSDTthanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtSDTThanhVien)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tìm kiếm thẻ thành viên", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Tìm kiếm hóa đơn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTuNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTuNgayActionPerformed
        
    }//GEN-LAST:event_txtTuNgayActionPerformed

    private void txtDenNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDenNgayActionPerformed
        
    }//GEN-LAST:event_txtDenNgayActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed

        String tuNgay = txtTuNgay.getText();
        String denNgay = txtDenNgay.getText();
// bỏ placeholder
        if (tuNgay.equals("yyyy-MM-dd")) {
            tuNgay = "";
        }
        if (denNgay.equals("yyyy-MM-dd")) {
            denNgay = "";
        }
// validate
        if (!tuNgay.isEmpty() && !isValidDate(tuNgay)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Từ ngày không hợp lệ (đúng dạng yyyy-MM-dd)");
            txtTuNgay.requestFocus();
            return;
        }
        if (!denNgay.isEmpty() && !isValidDate(denNgay)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Đến ngày không hợp lệ (đúng dạng yyyy-MM-dd)");
            txtDenNgay.requestFocus();
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblTimKiemHoaDon.getModel();
        model.setRowCount(0);
        String sql = """
    SELECT hd.MaHoaDon, kh.TenKhachHang, sp.TenSanPham, ct.SoLuong, ct.Gia
    FROM HoaDon hd
    JOIN HoaDonChiTiet ct ON hd.MaHoaDon = ct.MaHoaDon
    JOIN SanPham sp ON ct.MaSanPham = sp.MaSanPham
    JOIN KhachHang kh ON hd.MaKhachHang = kh.MaKhachHang
    WHERE (hd.NgayLap >= ? OR ? IS NULL)
      AND (hd.NgayLap <= ? OR ? IS NULL)
""";
        try {
            String tuNgayParam = tuNgay.isEmpty() ? null : tuNgay;
            String denNgayParam = denNgay.isEmpty() ? null : denNgay;

            ResultSet rs = XJdbc.query(sql,
                    tuNgayParam, tuNgayParam,
                    denNgayParam, denNgayParam
            );
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                model.addRow(new Object[]{
                    rs.getString("MaHoaDon"),
                    rs.getString("TenKhachHang"),
                    rs.getString("TenSanPham"),
                    rs.getInt("SoLuong"),
                    rs.getDouble("Gia")
                });
            }
            if (!hasData) {
                javax.swing.JOptionPane.showMessageDialog(this, "Không có dữ liệu trong khoảng ngày này!");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void txtMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHoaDonActionPerformed
        
    }//GEN-LAST:event_txtMaHoaDonActionPerformed

    private void btnTimHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimHoaDonActionPerformed
        
        String ma = txtMaHoaDon.getText().trim();
        if (ma.isEmpty() || ma.startsWith("Nhập")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn!");
            txtMaHoaDon.requestFocus();
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblTimKiemHoaDon.getModel();
        model.setRowCount(0);
        String sql = """
    SELECT hd.MaHoaDon, kh.TenKhachHang, sp.TenSanPham, ct.SoLuong, ct.Gia
    FROM HoaDon hd
    JOIN HoaDonChiTiet ct ON hd.MaHoaDon = ct.MaHoaDon
    JOIN SanPham sp ON ct.MaSanPham = sp.MaSanPham
    JOIN KhachHang kh ON hd.MaKhachHang = kh.MaKhachHang
    WHERE hd.MaHoaDon LIKE ?
""";
        try {
            ResultSet rs = XJdbc.query(sql, "%" + ma + "%");

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                model.addRow(new Object[]{
                    rs.getString("MaHoaDon"),
                    rs.getString("TenKhachHang"),
                    rs.getString("TenSanPham"),
                    rs.getInt("SoLuong"),
                    rs.getDouble("Gia")
                });
            }
            if (!hasData) {
                javax.swing.JOptionPane.showMessageDialog(this, "Mã hóa đơn không tồn tại!");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimHoaDonActionPerformed

    private void txtMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPhamActionPerformed
        
    }//GEN-LAST:event_txtMaSanPhamActionPerformed

    private void btnTimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPActionPerformed
        
        try {
            SanPhamDAOImpl dao = new SanPhamDAOImpl();
            String keyword = txtMaSanPham.getText();
            if (keyword.equals("Nhập mã hoặc tên sản phẩm...")) {
                keyword = "";
            }
            List<SanPham> list = dao.selectByKeyword(keyword);
            DefaultTableModel model = (DefaultTableModel) tblTimKiemSanPham.getModel();
            model.setRowCount(0);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm!");
                return;
            }
            for (SanPham sp : list) {
                model.addRow(new Object[]{
                    sp.getMaSanPham(),
                    sp.getTenSanPham(),
                    sp.getSoLuongTon(),
                    sp.getDonGia()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi truy vấn sản phẩm!");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimSPActionPerformed

    private void txtSDTThanhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTThanhVienActionPerformed
        
    }//GEN-LAST:event_txtSDTThanhVienActionPerformed

    private void btnTimSDTthanhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSDTthanhVienActionPerformed
        
        String sdt = txtSDTThanhVien.getText();
        if (sdt.equals("Nhập số điện thoại...") || sdt.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại!");
            txtSDTThanhVien.requestFocus();
            return;
        }
        fillTableThanhVien(sdt);
    }//GEN-LAST:event_btnTimSDTthanhVienActionPerformed

    private void tblTimTheThanhVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimTheThanhVienMouseClicked
        
    }//GEN-LAST:event_tblTimTheThanhVienMouseClicked

    private void tblTimKiemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemSanPhamMouseClicked
        
    }//GEN-LAST:event_tblTimKiemSanPhamMouseClicked

    private void tblTimKiemHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemHoaDonMouseClicked
        
    }//GEN-LAST:event_tblTimKiemHoaDonMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Timkiem().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnTimHoaDon;
    private javax.swing.JButton btnTimSDTthanhVien;
    private javax.swing.JButton btnTimSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblTimKiemHoaDon;
    private javax.swing.JTable tblTimKiemSanPham;
    private javax.swing.JTable tblTimTheThanhVien;
    private javax.swing.JTextField txtDenNgay;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSDTThanhVien;
    private javax.swing.JTextField txtTuNgay;
    // End of variables declaration//GEN-END:variables
}
