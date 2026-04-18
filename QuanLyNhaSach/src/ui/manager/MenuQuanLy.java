package ui.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import ui.staff.Login;
import ui.staff.Timkiem;

public class MenuQuanLy extends javax.swing.JFrame {

    // Kích thước Avatar cố định cho đồng nhất
    private final int AVATAR_SIZE = 110;

    public MenuQuanLy() {
        initComponents();
        setupAvatar(); // Khởi tạo các tùy chỉnh cho Avatar
        this.setLocationRelativeTo(null);
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 700));
        jPanel1.setMinimumSize(new java.awt.Dimension(250, 700));
    }

    /**
     * Thiết lập hiển thị và sự kiện cho Avatar
     */
    private void setupAvatar() {
    lblAvatar.setText("");
    lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
    lblAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // 1. Lấy thông tin người dùng đang đăng nhập
    entity.User user = util.XAuth.user; 

    if (user != null && user.getHinhAnh() != null && !user.getHinhAnh().isEmpty()) {
        try {
            // 2. Tìm file ảnh trong thư mục logos của dự án
            File file = new File("logos", user.getHinhAnh());
            if (file.exists()) {
                lblAvatar.setIcon(makeCircularIcon(file.toURI().toURL(), AVATAR_SIZE));
            } else {
                loadDefaultAdminIcon(); // Nếu file bị xóa mất thì load mặc định
            }
        } catch (Exception e) {
            loadDefaultAdminIcon();
        }
    } else {
        loadDefaultAdminIcon();
    }

    // 3. Đảm bảo sự kiện click chỉ được đăng ký một lần
    for (java.awt.event.MouseListener ml : lblAvatar.getMouseListeners()) {
        lblAvatar.removeMouseListener(ml);
    }
    lblAvatar.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            chooseNewAvatar();
        }
    });
}

// Hàm phụ để load ảnh mặc định
private void loadDefaultAdminIcon() {
    try {
        URL url = getClass().getResource("/icon/admin.jpg"); // Hoặc nhanvien.jpg
        if (url != null) {
            lblAvatar.setIcon(makeCircularIcon(url, AVATAR_SIZE));
        }
    } catch (Exception e) {}
}

    /**
     * Mở trình chọn file và cập nhật Avatar
     */
    private void chooseNewAvatar() {
    JFileChooser fc = new JFileChooser();
    fc.setDialogTitle("Chọn ảnh đại diện cho Quản lý");
    fc.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));

    if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        try {
            File file = fc.getSelectedFile();
            
            // 1. Lưu file vật lý vào thư mục logos (sử dụng tiện ích XImage)
            util.XImage.save(file); 
            
            // 2. Lấy đối tượng người dùng hiện tại (Admin)
            entity.User currentUser = util.XAuth.user;
            if (currentUser != null) {
                // 3. Cập nhật tên file ảnh vào đối tượng và Database
                currentUser.setHinhAnh(file.getName());
                
                dao.impl.UserDAOImpl dao = new dao.impl.UserDAOImpl();
                dao.update(currentUser); // Lưu vào SQL Server
                
                // 4. Vẽ lại ảnh tròn ngay lập tức trên Menu
                setupAvatar(); 
                
                util.XDialog.alert(this, "Đã cập nhật ảnh đại diện Admin!");
            }
        } catch (Exception ex) {
            util.XDialog.alert(this, "Lỗi khi cập nhật ảnh!");
            ex.printStackTrace();
        }
    }
}

    /**
     * Hàm xử lý cắt ảnh thành hình tròn chuyên nghiệp
     */
    private ImageIcon makeCircularIcon(URL imagePath, int size) {
        try {
            BufferedImage master = ImageIO.read(imagePath);

            // Cắt ảnh thành hình vuông từ trung tâm
            int diameter = Math.min(master.getWidth(), master.getHeight());
            int x = (master.getWidth() - diameter) / 2;
            int y = (master.getHeight() - diameter) / 2;
            BufferedImage cropped = master.getSubimage(x, y, diameter, diameter);

            // Tạo bản vẽ ARGB (hỗ trợ trong suốt)
            BufferedImage output = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = output.createGraphics();

            // Chống răng cưa cho viền mượt
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            // Tạo khuôn tròn và vẽ ảnh vào
            g2.setClip(new java.awt.geom.Ellipse2D.Double(0, 0, size, size));
            g2.drawImage(cropped, 0, 0, size, size, null);

            // Vẽ thêm viền trắng cho sang trọng
            g2.setClip(null);
            g2.setColor(Color.WHITE);
            g2.setStroke(new java.awt.BasicStroke(2f));
            g2.drawOval(1, 1, size - 2, size - 2);

            g2.dispose();
            return new ImageIcon(output);
        } catch (Exception e) {
            return null;
        }
    }

    private void showPanel(javax.swing.JFrame form) {
    jPanel2.removeAll();
    jPanel2.setLayout(new BorderLayout());
    try {
        javax.swing.JPanel mainPanel = (javax.swing.JPanel) form.getContentPane().getComponent(0);
        mainPanel.setBackground(Color.WHITE);
        jPanel2.add(mainPanel, BorderLayout.CENTER);
    } catch (Exception e) {
        jPanel2.add(form.getContentPane(), BorderLayout.CENTER);
    }
    
    // Quan trọng: Cập nhật lại giao diện mà không làm thay đổi kích thước tổng thể
    jPanel2.revalidate();
    jPanel2.repaint();
    
    // Ép JFrame giữ nguyên kích thước bạn đã thiết kế ban đầu
    // Đảm bảo nút đăng xuất không bị đẩy xuống dưới đáy khuất tầm mắt
    this.validate(); 
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnQuanlyLoaiSP = new javax.swing.JButton();
        btnQuanLySP = new javax.swing.JButton();
        btnQuanLyTheThanhVien = new javax.swing.JButton();
        btnQuanLyPhieuBanHang = new javax.swing.JButton();
        btnQuanLyNguoiDung = new javax.swing.JButton();
        btnQuanLyDoanhThu = new javax.swing.JButton();
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 700)); // Ép Menu giữ nguyên kích thước
        btnLogOut = new javax.swing.JButton();
        btnLogOut.setMargin(new java.awt.Insets(10, 10, 10, 10));
        lblAvatar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(25, 53, 94));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Admin");

        btnQuanlyLoaiSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuanlyLoaiSP.setForeground(new java.awt.Color(255, 255, 255));
        btnQuanlyLoaiSP.setText("Quản lý loại sản phẩm");
        btnQuanlyLoaiSP.setBorderPainted(false);
        btnQuanlyLoaiSP.setContentAreaFilled(false);
        btnQuanlyLoaiSP.setFocusPainted(false);
        btnQuanlyLoaiSP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuanlyLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanlyLoaiSPActionPerformed(evt);
            }
        });

        btnQuanLySP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuanLySP.setForeground(new java.awt.Color(255, 255, 255));
        btnQuanLySP.setText("Quản lý sản phẩm");
        btnQuanLySP.setBorderPainted(false);
        btnQuanLySP.setContentAreaFilled(false);
        btnQuanLySP.setFocusPainted(false);
        btnQuanLySP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuanLySP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLySPActionPerformed(evt);
            }
        });

        btnQuanLyTheThanhVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuanLyTheThanhVien.setForeground(new java.awt.Color(255, 255, 255));
        btnQuanLyTheThanhVien.setText("Quản lý thẻ thành viên");
        btnQuanLyTheThanhVien.setBorderPainted(false);
        btnQuanLyTheThanhVien.setContentAreaFilled(false);
        btnQuanLyTheThanhVien.setFocusPainted(false);
        btnQuanLyTheThanhVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuanLyTheThanhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyTheThanhVienActionPerformed(evt);
            }
        });

        btnQuanLyPhieuBanHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuanLyPhieuBanHang.setForeground(new java.awt.Color(255, 255, 255));
        btnQuanLyPhieuBanHang.setText("Quản lý phiếu bán hàng");
        btnQuanLyPhieuBanHang.setBorderPainted(false);
        btnQuanLyPhieuBanHang.setContentAreaFilled(false);
        btnQuanLyPhieuBanHang.setFocusPainted(false);
        btnQuanLyPhieuBanHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuanLyPhieuBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyPhieuBanHangActionPerformed(evt);
            }
        });

        btnQuanLyNguoiDung.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuanLyNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        btnQuanLyNguoiDung.setText("Quản lý người dùng");
        btnQuanLyNguoiDung.setBorderPainted(false);
        btnQuanLyNguoiDung.setContentAreaFilled(false);
        btnQuanLyNguoiDung.setFocusPainted(false);
        btnQuanLyNguoiDung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuanLyNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyNguoiDungActionPerformed(evt);
            }
        });

        btnQuanLyDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuanLyDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        btnQuanLyDoanhThu.setText("Quản lý doanh thu");
        btnQuanLyDoanhThu.setBorderPainted(false);
        btnQuanLyDoanhThu.setContentAreaFilled(false);
        btnQuanLyDoanhThu.setFocusPainted(false);
        btnQuanLyDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuanLyDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyDoanhThuActionPerformed(evt);
            }
        });

        btnLogOut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogOut.setForeground(new java.awt.Color(255, 255, 255));
        btnLogOut.setText("Đăng xuất");
        btnLogOut.setBorderPainted(false);
        btnLogOut.setContentAreaFilled(false);
        btnLogOut.setFocusPainted(false);
        btnLogOut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        lblAvatar.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuanLyPhieuBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuanlyLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuanLySP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuanLyNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuanLyDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuanLyTheThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuanlyLoaiSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuanLySP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuanLyTheThanhVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuanLyPhieuBanHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuanLyNguoiDung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuanLyDoanhThu)
                .addGap(63, 63, 63)
                .addComponent(btnLogOut)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        
        if (util.XDialog.confirm(this, "Bạn có muốn đăng xuất không?")) {
            // 1. Xóa thông tin đăng nhập cũ
            util.XAuth.logout();

            // 2. Đóng tất cả các cửa sổ đang mở (nếu cần) và giải phóng bộ nhớ
            this.dispose();

            // 3. Chạy màn hình Login trong EventQueue để đảm bảo an toàn luồng UI
            java.awt.EventQueue.invokeLater(() -> {
                new Login().setVisible(true);
            });
        }
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnQuanLyDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyDoanhThuActionPerformed
        
        if (util.XAuth.isManager()) {
            showPanel(new QLDoanhThu());
        } else {
            util.XDialog.alert(this, "Chỉ quản lý mới được xem doanh thu!");
        }
    }//GEN-LAST:event_btnQuanLyDoanhThuActionPerformed

    private void btnQuanLyNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyNguoiDungActionPerformed
        
        if (util.XAuth.isManager()) {
            showPanel(new QlNguoidung());
        } else {
            util.XDialog.alert(this, "Tài khoản của bạn không có quyền Quản lý!");
        }
    }//GEN-LAST:event_btnQuanLyNguoiDungActionPerformed

    private void btnQuanLyPhieuBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyPhieuBanHangActionPerformed
        
        showPanel(new Phieubanhangmoi());
    }//GEN-LAST:event_btnQuanLyPhieuBanHangActionPerformed

    private void btnQuanLyTheThanhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyTheThanhVienActionPerformed
        
        // 1. Kiểm tra quyền hạn (Tùy chọn: Thẻ thành viên thường dành cho Quản lý hoặc Nhân viên)
        // Nếu bạn muốn chỉ Quản lý mới được vào:
        if (util.XAuth.isManager()) {
            showPanel(new QLthetv());
        } else {
            // Nếu là nhân viên vẫn cho vào thì bỏ câu lệnh if/else này
            util.XDialog.alert(this, "Chức năng này yêu cầu quyền Quản lý!");
        }
    }//GEN-LAST:event_btnQuanLyTheThanhVienActionPerformed

    private void btnQuanLySPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLySPActionPerformed
        
        showPanel(new QlSanpham());
    }//GEN-LAST:event_btnQuanLySPActionPerformed

    private void btnQuanlyLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanlyLoaiSPActionPerformed
        
        showPanel(new QlLoaiSP());
    }//GEN-LAST:event_btnQuanlyLoaiSPActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

        showPanel(new Timkiem());
    }//GEN-LAST:event_btnTimKiemActionPerformed

   private void setupMenuButton(javax.swing.JButton btn, String text, String iconPath) {
        btn.setText(text);
        btn.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btn.setForeground(Color.WHITE);
        try {
            btn.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        } catch (Exception e) {}
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.addActionListener(this::menuActionHandler);
    }

    private void menuActionHandler(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == btnLogOut) {
            if (util.XDialog.confirm(this, "Bạn có muốn đăng xuất?")) {
                util.XAuth.logout();
                this.dispose();
                new Login().setVisible(true);
            }
        } else if (source == btnQuanLyDoanhThu) {
            if (util.XAuth.isManager()) showPanel(new QLDoanhThu());
            else util.XDialog.alert(this, "Yêu cầu quyền Quản lý!");
        } else if (source == btnQuanlyLoaiSP) {
            showPanel(new QlLoaiSP());
        } else if (source == btnQuanLySP) {
            showPanel(new QlSanpham());
        } else if (source == btnQuanLyTheThanhVien) {
            showPanel(new QLthetv());
        } else if (source == btnQuanLyNguoiDung) {
            showPanel(new QlNguoidung());
        } else if (source == btnQuanLyPhieuBanHang) {
            showPanel(new Phieubanhangmoi());
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
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MenuQuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(() -> new MenuQuanLy().setVisible(true));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnQuanLyDoanhThu;
    private javax.swing.JButton btnQuanLyNguoiDung;
    private javax.swing.JButton btnQuanLyPhieuBanHang;
    private javax.swing.JButton btnQuanLySP;
    private javax.swing.JButton btnQuanLyTheThanhVien;
    private javax.swing.JButton btnQuanlyLoaiSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAvatar;
    // End of variables declaration//GEN-END:variables
}
