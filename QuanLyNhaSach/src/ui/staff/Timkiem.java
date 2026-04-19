package ui.staff;

import dao.impl.SanPhamDAOImpl;
import entity.SanPham;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

// Lớp Timkiem: Cung cấp giao diện tra cứu thông tin đa năng cho nhân viên
public class Timkiem extends javax.swing.JFrame {

    // Khởi tạo đối tượng DAO để làm việc với dữ liệu khách hàng
    dao.impl.KhachHangDAOImpl khDAO = new dao.impl.KhachHangDAOImpl();

    // Logger dùng để ghi lại lịch sử hoạt động hoặc lỗi của form này
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Timkiem.class.getName());

    public Timkiem() {
        initComponents(); // Khởi tạo giao diện Swing
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở chính giữa màn hình

        // Thiết lập Timer cho việc tìm kiếm Hóa đơn: Tự động tìm sau 300ms khi ngừng gõ phím
        javax.swing.Timer timerHD = new javax.swing.Timer(300, e -> {
            fillTableHoaDon(txtMaHoaDon.getText().trim()); // Thực hiện đổ dữ liệu hóa đơn
        });
        timerHD.setRepeats(false); // Đảm bảo timer chỉ chạy một lần sau mỗi lần reset
        txtMaHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timerHD.restart(); // Khi người dùng gõ phím, khởi động lại timer (tránh truy vấn database liên tục)
            }
        });

        // Thiết lập Timer cho việc tìm kiếm Sản phẩm (tương tự như Hóa đơn)
        javax.swing.Timer timerSP = new javax.swing.Timer(300, e -> {
            fillTableSanPham(txtMaSanPham.getText().trim());
        });
        timerSP.setRepeats(false);
        txtMaSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timerSP.restart();
            }
        });

        // Thiết lập Timer cho việc tìm kiếm Thành viên/Khách hàng
        javax.swing.Timer timerTV = new javax.swing.Timer(300, e -> {
            fillTableThanhVien(txtTim.getText().trim());
        });
        timerTV.setRepeats(false);
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timerTV.restart();
            }
        });

        // Load toàn bộ dữ liệu lên các bảng ngay khi vừa mở form
        fillTableHoaDon("");
        fillTableSanPham("");
        fillTableThanhVien("");

        // Chế độ Read-only: Không cho phép người dùng sửa trực tiếp dữ liệu trên các bảng
        tblTimKiemHoaDon.setDefaultEditor(Object.class, null);
        tblTimKiemSanPham.setDefaultEditor(Object.class, null);
        tblDanhSach.setDefaultEditor(Object.class, null);
    }

    // Phương thức fillTableHoaDon: Truy vấn và hiển thị danh sách hóa đơn dựa trên từ khóa
    void fillTableHoaDon(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblTimKiemHoaDon.getModel();
        model.setRowCount(0); // Làm trống bảng trước khi nạp dữ liệu mới

        // Câu lệnh SQL JOIN 4 bảng để lấy thông tin chi tiết hóa đơn
        String sql = "SELECT hd.MaHoaDon, kh.TenKhachHang, sp.TenSanPham, ct.SoLuong, ct.Gia "
                + "FROM HoaDon hd JOIN HoaDonChiTiet ct ON hd.MaHoaDon = ct.MaHoaDon "
                + "JOIN SanPham sp ON ct.MaSanPham = sp.MaSanPham "
                + "JOIN KhachHang kh ON hd.MaKhachHang = kh.MaKhachHang "
                + "WHERE hd.MaHoaDon LIKE ? OR kh.TenKhachHang LIKE ?";
        try {
            // Sử dụng XJdbc để thực thi câu lệnh truy vấn
            ResultSet rs = util.XJdbc.query(sql, "%" + keyword + "%", "%" + keyword + "%");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString(1), // Mã hóa đơn
                    rs.getString(2), // Tên khách hàng
                    rs.getString(3), // Tên sản phẩm
                    rs.getInt(4), // Số lượng mua
                    util.XHelper.formatMoney(rs.getDouble(5)) // Định dạng tiền tệ (VNĐ)
                });
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để kiểm tra
        }
    }

    // Phương thức fillTableSanPham: Hiển thị danh sách sản phẩm tìm được
    void fillTableSanPham(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblTimKiemSanPham.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trên bảng
        try {
            SanPhamDAOImpl dao = new SanPhamDAOImpl(); // Khởi tạo lớp xử lý sản phẩm
            List<SanPham> list = dao.selectByKeyword(keyword); // Tìm sản phẩm theo từ khóa
            for (entity.SanPham sp : list) {
                model.addRow(new Object[]{
                    sp.getMaSanPham(),
                    sp.getMaLoai(),
                    sp.getTenSanPham(),
                    util.XHelper.formatMoney(sp.getDonGia()), // Định dạng giá bán
                    sp.getSoLuongTon() // Số lượng còn lại trong kho
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức fillTableThanhVien: Hiển thị thông tin khách hàng/thành viên
    void fillTableThanhVien(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0); // Clear dữ liệu bảng

        try {
            // Lấy danh sách khách hàng từ cơ sở dữ liệu thông qua DAO
            List<entity.KhachHang> list = khDAO.selectByKeyword(keyword);

            for (entity.KhachHang kh : list) {
                model.addRow(new Object[]{
                    kh.getSoDienThoai(), // Số điện thoại (dùng làm ID)
                    kh.getTenKhachHang(),
                    kh.getLoaiThe(), // Hạng thẻ (Đồng, Bạc, Vàng...)
                    kh.getDiemTichLuy() // Điểm tích lũy hiện tại
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            util.XDialog.alert(this, "Lỗi truy vấn dữ liệu khách hàng!");
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
        jLabel35 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        btnTimHoaDon = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTimKiemSanPham = new javax.swing.JTable();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        btnTimSP = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        btnTimKhack = new javax.swing.JButton();

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tìm kiếm sản phẩm", jPanel3);

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Số điện thoại", "Tên Khách hàng", "Loại thẻ", "Điểm hiện có"
            }
        ));
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        btnTimKhack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKhack.setForeground(new java.awt.Color(12, 66, 139));
        btnTimKhack.setText("Tìm");
        btnTimKhack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKhackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKhack, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKhack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tìm kiếm thành viên ", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
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

    private void txtMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHoaDonActionPerformed

    }//GEN-LAST:event_txtMaHoaDonActionPerformed

    private void btnTimHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimHoaDonActionPerformed
        fillTableHoaDon(txtMaHoaDon.getText().trim());
    }//GEN-LAST:event_btnTimHoaDonActionPerformed

    private void txtMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPhamActionPerformed

    }//GEN-LAST:event_txtMaSanPhamActionPerformed

    private void btnTimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPActionPerformed
        fillTableSanPham(txtMaSanPham.getText().trim());
    }//GEN-LAST:event_btnTimSPActionPerformed

    private void tblTimKiemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemSanPhamMouseClicked

    }//GEN-LAST:event_tblTimKiemSanPhamMouseClicked

    private void tblTimKiemHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemHoaDonMouseClicked

    }//GEN-LAST:event_tblTimKiemHoaDonMouseClicked

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        fillTableThanhVien(txtTim.getText().trim());
    }//GEN-LAST:event_txtTimKeyReleased

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked

    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void btnTimKhackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKhackActionPerformed
        fillTableThanhVien(txtTim.getText().trim());
    }//GEN-LAST:event_btnTimKhackActionPerformed
    // Hàm phụ trợ: Xác định hạng thẻ dựa trên số điểm tích lũy của khách
    private String xacDinhLoaiThe(int diem) {
        if (diem >= 200) {
            return "Vàng";
        } else if (diem >= 150) {
            return "Bạc";
        } else if (diem >= 100) {
            return "Đồng";
        } else {
            return "Thường";
        }
    }

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
    private javax.swing.JButton btnTimHoaDon;
    private javax.swing.JButton btnTimKhack;
    private javax.swing.JButton btnTimSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTable tblTimKiemHoaDon;
    private javax.swing.JTable tblTimKiemSanPham;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables

}
