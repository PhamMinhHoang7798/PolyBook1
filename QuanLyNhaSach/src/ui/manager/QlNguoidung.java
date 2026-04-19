package ui.manager;

import dao.impl.UserDAOImpl;
import entity.User;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import util.XDialog;

public class QlNguoidung extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(QlNguoidung.class.getName());

    public QlNguoidung() {
        initComponents();

        loadTable("");
        javax.swing.ButtonGroup roleGroup = new javax.swing.ButtonGroup();
        roleGroup.add(rbtQuanLy);
        roleGroup.add(rbtNhanVien);

        javax.swing.ButtonGroup statusGroup = new javax.swing.ButtonGroup();
        statusGroup.add(rbtHoatDong);
        statusGroup.add(rbtTamDung);
        tblQLNguoiDung.setDefaultEditor(Object.class, null);
    }
    private UserDAOImpl dao = new UserDAOImpl();
    private String hinhAnh = null;

    private entity.User getForm() {
        entity.User u = new entity.User();
        u.setTenDangNhap(txtTenDangNhap.getText().trim());
        u.setMatKhau(new String(txtMatkhau.getText())); // Hoặc txtMatkhau.getText() tùy loại field
        u.setHoTen(txtHoten.getText().trim());
        u.setHinhAnh(hinhAnh); // Biến hinhAnh lưu tên file ảnh

        // Xử lý Vai trò: Chỉ còn Quản lý (2) và Nhân viên (1)
        if (rbtQuanLy.isSelected()) {
            u.setVaiTro(2);
        } else {
            // Mặc định là nhân viên nếu rbtNhanVien được chọn hoặc chưa chọn gì
            u.setVaiTro(1);
        }

        // Xử lý Trạng thái: Hoạt động (true) / Tạm dừng (false)
        u.setTrangThai(rbtHoatDong.isSelected());

        return u;
    }

    private void loadTable(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblQLNguoiDung.getModel();
        model.setRowCount(0);
        List<User> list;
        // Nếu ô tìm kiếm trống thì lấy tất cả, ngược lại thì tìm theo từ khóa
        if (keyword == null || keyword.trim().isEmpty()) {
            list = dao.selectAll();
        } else {
            list = dao.selectByKeyword(keyword);
        }
        for (User u : list) {
            model.addRow(new Object[]{
                u.getTenDangNhap() == null ? "" : u.getTenDangNhap(),
                u.getMatKhau() == null ? "" : u.getMatKhau(),
                u.getHoTen() == null ? "" : u.getHoTen(),
                u.getHinhAnh() == null ? "" : u.getHinhAnh(),
                u.getVaiTro() == 2 ? "Quản lý" : "Nhân viên",
                u.isTrangThai() ? "Hoạt động" : "Tạm dừng"
            });
        }
    }

    private void insert() {
        try {
            if (txtTenDangNhap.getText().isEmpty() || txtMatkhau.getText().isEmpty()) {
                XDialog.alert(this, "Không được để trống!");
                return;
            }
            if (!txtMatkhau.getText().equals(txtXacNhanMatKhau.getText())) {
                XDialog.alert(this, "Mật khẩu không khớp!");
                return;
            }
            dao.insert(getForm());
            XDialog.alert(this, "Thêm thành công!");
            loadTable("");
            clearForm();

        } catch (Exception e) {
            XDialog.alert(this, "Thêm thất bại!");
            e.printStackTrace();
        }
    }

    private void update() {
        try {
            if (!txtMatkhau.getText().equals(txtXacNhanMatKhau.getText())) {
                XDialog.alert(this, "Mật khẩu không khớp!");
                return;
            }
            dao.update(getForm());
            XDialog.alert(this, "Cập nhật thành công!");
            loadTable("");
        } catch (Exception e) {
            XDialog.alert(this, "Cập nhật thất bại!");
        }
        
    }

    private void delete() {
        String maNV = txtTenDangNhap.getText();
        try {
            dao.delete(maNV);
            this.clearForm();
            this.loadTable("");
            util.XDialog.alert(this, "Xóa thành công!");
        } catch (Exception e) {
            // Nếu lỗi do khóa ngoại, máy sẽ nhảy vào đây
            util.XDialog.alert(this, "Không thể xóa nhân viên này vì đã có dữ liệu hóa đơn liên quan!\n"
                    + "Gợi ý: Hãy chuyển trạng thái sang 'Tạm dừng'.");
        }
    }

    private void clearForm() {
        // 1. Xóa các ô văn bản
        txtTenDangNhap.setText("");
        txtMatkhau.setText("");
        txtXacNhanMatKhau.setText("");
        txtHoten.setText("");

        // 2. Reset vai trò (Mặc định chọn Nhân viên)
        rbtNhanVien.setSelected(true);
        // rbtQuanLy.setSelected(false); // roleGroup sẽ tự xử lý nếu đã thêm vào ButtonGroup

        // 3. Reset trạng thái (Mặc định Hoạt động)
        rbtHoatDong.setSelected(true);

        // 4. Xóa hình ảnh hiển thị
        lblHinh.setIcon(null);
        lblHinh.setText("Chọn ảnh"); // Nếu ông có để text hướng dẫn
        hinhAnh = null;

        // 5. Đưa con trỏ về ô đầu tiên
        txtTenDangNhap.requestFocus();
    }

    private String getValueSafe(int row, int col) {
        Object value = tblQLNguoiDung.getValueAt(row, col);
        return value == null ? "" : value.toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLNguoiDung = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMatkhau = new javax.swing.JTextField();
        txtXacNhanMatKhau = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        rbtQuanLy = new javax.swing.JRadioButton();
        rbtNhanVien = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        rbtHoatDong = new javax.swing.JRadioButton();
        rbtTamDung = new javax.swing.JRadioButton();
        btnTaoMoi = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnNhapMoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(716, 630));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Quản lý người dùng");

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        tblQLNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên đăng nhập", "Mật khẩu", "Họ tên", "Hình ảnh", "Vai trò", "Trạng thái"
            }
        ));
        tblQLNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLNguoiDungMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLNguoiDung);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Danh sách ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Tên Đăng Nhập");

        txtTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDangNhapActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Mật Khẩu");

        txtMatkhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatkhauActionPerformed(evt);
            }
        });

        txtXacNhanMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtXacNhanMatKhauActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Xác nhận mật khẩu");

        txtHoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHotenActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Họ Tên");

        lblHinh.setText(" ");
        lblHinh.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), java.awt.Color.gray));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Vai trò:");

        rbtQuanLy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbtQuanLy.setText("Quản lý");

        rbtNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbtNhanVien.setText("Nhân viên");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Trạng thái:");

        rbtHoatDong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbtHoatDong.setText("Hoạt động");

        rbtTamDung.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbtTamDung.setText("Tạm dừng");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtQuanLy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtHoatDong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtTamDung)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtHoatDong)
                        .addComponent(rbtTamDung)
                        .addComponent(jLabel4))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(rbtQuanLy)
                        .addComponent(rbtNhanVien)))
                .addGap(27, 27, 27))
        );

        btnTaoMoi.setBackground(new java.awt.Color(204, 255, 204));
        btnTaoMoi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTaoMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnTaoMoi.setText("Thêm");
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(255, 255, 204));
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 204, 204));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnNhapMoi.setBackground(new java.awt.Color(204, 255, 255));
        btnNhapMoi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhapMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnNhapMoi.setText("Nhập mới");
        btnNhapMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                    .addComponent(txtMatkhau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtXacNhanMatKhau)
                    .addComponent(jLabel9)
                    .addComponent(txtHoten, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnCapNhat)
                .addGap(32, 32, 32)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNhapMoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel8)
                            .addGap(18, 18, 18)
                            .addComponent(txtMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        jTabbedPane1.addTab("Biểu mẫu", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDangNhapActionPerformed

    }//GEN-LAST:event_txtTenDangNhapActionPerformed

    private void txtMatkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatkhauActionPerformed

    }//GEN-LAST:event_txtMatkhauActionPerformed

    private void txtXacNhanMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtXacNhanMatKhauActionPerformed

    }//GEN-LAST:event_txtXacNhanMatKhauActionPerformed

    private void txtHotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHotenActionPerformed

    }//GEN-LAST:event_txtHotenActionPerformed

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed

        insert();
    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed

        update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnNhapMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapMoiActionPerformed

        clearForm();
    }//GEN-LAST:event_btnNhapMoiActionPerformed

    private void tblQLNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLNguoiDungMouseClicked

        if (evt.getClickCount() == 2) {
            int row = tblQLNguoiDung.getSelectedRow();
            if (row < 0) {
                return;
            }
            txtTenDangNhap.setText(getValueSafe(row, 0));
            txtMatkhau.setText(getValueSafe(row, 1));
            txtHoten.setText(getValueSafe(row, 2));
            // ===== HÌNH ẢNH =====
            String img = getValueSafe(row, 3);
            hinhAnh = img;
            if (img != null && !img.isEmpty()) {
                java.io.File file = new java.io.File("src/img/" + img);

                if (file.exists()) {
                    javax.swing.ImageIcon icon = new javax.swing.ImageIcon(file.getAbsolutePath());

                    java.awt.Image scaled = icon.getImage().getScaledInstance(
                            lblHinh.getWidth(),
                            lblHinh.getHeight(),
                            java.awt.Image.SCALE_SMOOTH
                    );
                    lblHinh.setIcon(new javax.swing.ImageIcon(scaled));
                } else {
                    System.out.println("Không tìm thấy ảnh: " + file.getAbsolutePath());
                    lblHinh.setIcon(null);
                }
            } else {
                lblHinh.setIcon(null);
            }
            // ===== VAI TRÒ =====
            String vaiTro = getValueSafe(row, 4);
            rbtQuanLy.setSelected(false);
            rbtNhanVien.setSelected(false);

            if (vaiTro.equals("Quản lý")) {
                rbtQuanLy.setSelected(true);
            } else if (vaiTro.equals("Nhân viên")) {
                rbtNhanVien.setSelected(true);
            }
            // ===== TRẠNG THÁI =====
            String trangThai = getValueSafe(row, 5);
            rbtHoatDong.setSelected(false);
            rbtTamDung.setSelected(false);
            if (trangThai.equals("Hoạt động")) {
                rbtHoatDong.setSelected(true);
            } else {
                rbtTamDung.setSelected(true);
            }
            // chuyển tab sang form
            jTabbedPane1.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblQLNguoiDungMouseClicked

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked

        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        if (fileChooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            // thư mục lưu ảnh trong project
            java.io.File dir = new java.io.File("src/img");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // file đích
            java.io.File dest = new java.io.File(dir, file.getName());
            try {
                java.nio.file.Files.copy(
                        file.toPath(),
                        dest.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );
                // lưu tên ảnh
                hinhAnh = file.getName();
                // hiển thị lên label
                javax.swing.ImageIcon icon = new javax.swing.ImageIcon(dest.getAbsolutePath());
                java.awt.Image img = icon.getImage().getScaledInstance(
                        lblHinh.getWidth(),
                        lblHinh.getHeight(),
                        java.awt.Image.SCALE_SMOOTH
                );
                lblHinh.setIcon(new javax.swing.ImageIcon(img));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed

        loadTable(txtTim.getText().trim());
    }//GEN-LAST:event_btnTimActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased

        loadTable(txtTim.getText().trim());
    }//GEN-LAST:event_txtTimKeyReleased

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
        java.awt.EventQueue.invokeLater(() -> new QlNguoidung().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnNhapMoi;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JRadioButton rbtHoatDong;
    private javax.swing.JRadioButton rbtNhanVien;
    private javax.swing.JRadioButton rbtQuanLy;
    private javax.swing.JRadioButton rbtTamDung;
    private javax.swing.JTable tblQLNguoiDung;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtMatkhau;
    private javax.swing.JTextField txtTenDangNhap;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables
}
