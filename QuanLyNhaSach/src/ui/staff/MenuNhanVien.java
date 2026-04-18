package ui.staff;

import dao.impl.UserDAOImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
import ui.manager.Phieubanhangmoi;
import ui.staff.DoiMatKhau;
import ui.staff.Timkiem;

public class MenuNhanVien extends javax.swing.JFrame {

    private final int AVATAR_SIZE = 110;

    public MenuNhanVien() {
        try {
            initComponents();
            setupAvatar();
            this.setLocationRelativeTo(null);
            
            // Đảm bảo các Panel có kích thước ban đầu
            jPanel1.setPreferredSize(new java.awt.Dimension(250, 700));
            jPanel2.setLayout(new BorderLayout());
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo giao diện: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
    public void refreshProfile() {
    // Truy vấn lại Database để lấy thông tin mới nhất của User hiện tại
    UserDAOImpl dao = new UserDAOImpl();
    util.XAuth.user = dao.selectById(util.XAuth.user.getTenDangNhap());
    setupAvatar(); // Vẽ lại ảnh tròn
    jLabel1.setText(util.XAuth.user.getHoTen()); // Cập nhật tên hiển thị
}

    private void setupAvatar() {
    lblAvatar.setText("");
    lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
    lblAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // 1. Lấy thông tin người dùng đang đăng nhập từ hệ thống
    entity.User user = util.XAuth.user; 

    if (user != null && user.getHinhAnh() != null && !user.getHinhAnh().isEmpty()) {
        try {
            // 2. Tìm file ảnh dựa trên tên file lưu trong Database
            // Giả sử bạn lưu ảnh trong thư mục "logos" ngang hàng với src
            File file = new File("logos", user.getHinhAnh());
            if (file.exists()) {
                lblAvatar.setIcon(makeCircularIcon(file.toURI().toURL(), AVATAR_SIZE));
            } else {
                loadDefaultAvatar();
            }
        } catch (Exception e) {
            loadDefaultAvatar();
        }
    } else {
        loadDefaultAvatar();
    }

    // Sự kiện click nếu bạn vẫn muốn cho phép đổi nhanh (hoặc bỏ qua nếu chỉ muốn đổi ở QLNguoiDung)
    lblAvatar.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Nếu muốn liên kết DB, khi chọn xong ở đây bạn phải gọi DAO.update(user)
            chooseNewAvatar(); 
        }
    });
}

// Hàm phụ để load ảnh mặc định khi không có dữ liệu DB
private void loadDefaultAvatar() {
    try {
        URL url = getClass().getResource("/icon/nhanvien.jpg");
        if (url != null) {
            lblAvatar.setIcon(makeCircularIcon(url, AVATAR_SIZE));
        } else {
            lblAvatar.setText("No Image");
            lblAvatar.setForeground(Color.WHITE);
        }
    } catch (Exception e) {
        System.err.println("Lỗi load ảnh mặc định");
    }
}

    private void chooseNewAvatar() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                lblAvatar.setIcon(makeCircularIcon(file.toURI().toURL(), AVATAR_SIZE));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private ImageIcon makeCircularIcon(URL imagePath, int size) {
        try {
            BufferedImage master = ImageIO.read(imagePath);
            if (master == null) return null;
            int diameter = Math.min(master.getWidth(), master.getHeight());
            int x = (master.getWidth() - diameter) / 2;
            int y = (master.getHeight() - diameter) / 2;
            BufferedImage cropped = master.getSubimage(x, y, diameter, diameter);

            BufferedImage output = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = output.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setClip(new java.awt.geom.Ellipse2D.Double(0, 0, size, size));
            g2.drawImage(cropped, 0, 0, size, size, null);
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
            // Chống lỗi nếu form con trống
            javax.swing.JPanel mainPanel = (javax.swing.JPanel) form.getContentPane().getComponent(0);
            jPanel2.add(mainPanel, BorderLayout.CENTER);
        } catch (Exception e) {
            jPanel2.add(form.getContentPane(), BorderLayout.CENTER);
        }
        jPanel2.revalidate();
        jPanel2.repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        btnTaoHoaDonMoi = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnDoiMatKhau = new javax.swing.JButton();
        lblAvatar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(25, 53, 94));
        jPanel1.setPreferredSize(new java.awt.Dimension(240, 603));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nhân viên");

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setBorderPainted(false);
        btnTimKiem.setContentAreaFilled(false);
        btnTimKiem.setFocusPainted(false);
        btnTimKiem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnTaoHoaDonMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaoHoaDonMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHoaDonMoi.setText("Tạo hóa đơn mới");
        btnTaoHoaDonMoi.setBorderPainted(false);
        btnTaoHoaDonMoi.setContentAreaFilled(false);
        btnTaoHoaDonMoi.setFocusPainted(false);
        btnTaoHoaDonMoi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTaoHoaDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonMoiActionPerformed(evt);
            }
        });

        btnLogOut.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLogOut.setForeground(new java.awt.Color(255, 0, 51));
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

        btnDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMatKhau.setText("Đổi mật khẩu");
        btnDoiMatKhau.setBorderPainted(false);
        btnDoiMatKhau.setContentAreaFilled(false);
        btnDoiMatKhau.setFocusPainted(false);
        btnDoiMatKhau.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });

        lblAvatar.setPreferredSize(new java.awt.Dimension(50, 50));
        lblAvatar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAvatarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(66, 66, 66))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoHoaDonMoi)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnLogOut)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaoHoaDonMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDoiMatKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogOut)
                .addGap(79, 79, 79))
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(716, 630));
        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed

        showPanel(new DoiMatKhau());
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

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

    private void btnTaoHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonMoiActionPerformed

        showPanel(new Phieubanhangmoi());
    }//GEN-LAST:event_btnTaoHoaDonMoiActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

        showPanel(new Timkiem());
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void lblAvatarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAvatarMouseClicked
        // 1. Khởi tạo bộ chọn file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn ảnh đại diện mới");
    fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));

    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        try {
            File file = fileChooser.getSelectedFile();
            
            // 2. Lưu file vật lý vào thư mục 'logos' của dự án
            // Hàm này sẽ copy file vào folder để app có thể đọc được sau này
            util.XImage.save(file); 
            
            // 3. Lấy đối tượng người dùng hiện tại từ XAuth
            entity.User currentUser = util.XAuth.user;
            if (currentUser == null) {
                util.XDialog.alert(this, "Phiên đăng nhập hết hạn!");
                return;
            }

            // 4. Cập nhật tên file ảnh mới vào đối tượng User
            currentUser.setHinhAnh(file.getName());

            // 5. Gọi DAO để UPDATE trực tiếp vào Database
            UserDAOImpl dao = new UserDAOImpl();
            dao.update(currentUser); // Thực hiện câu lệnh UPDATE trong SQL

            // 6. Cập nhật lại giao diện ngay lập tức
            setupAvatar(); 
            
            util.XDialog.alert(this, "Đã đổi ảnh đại diện và lưu vào hệ thống!");
            
        } catch (Exception e) {
            util.XDialog.alert(this, "Lỗi khi lưu ảnh vào cơ sở dữ liệu!");
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_lblAvatarMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new MenuNhanVien().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnTaoHoaDonMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAvatar;
    // End of variables declaration//GEN-END:variables
}
