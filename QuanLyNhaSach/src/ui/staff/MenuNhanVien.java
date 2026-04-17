package ui.staff;

import ui.staff.Timkiem;
import ui.staff.DoiMatKhau;
import ui.manager.Phieubanhangmoi;

public class MenuNhanVien extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuNhanVien.class.getName());

    public MenuNhanVien() {
        initComponents();
        // Lệnh này ép Cửa sổ Menu tự động nhảy ra chính giữa màn hình
        this.setLocationRelativeTo(null);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(51, 51, 51)
                                    .addComponent(jLabel1))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(btnTaoHoaDonMoi))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaoHoaDonMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDoiMatKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogOut)
                .addGap(29, 29, 29))
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(716, 630));
        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

        showPanel(new Timkiem());
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnTaoHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonMoiActionPerformed

        showPanel(new Phieubanhangmoi());
    }//GEN-LAST:event_btnTaoHoaDonMoiActionPerformed

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

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed

        showPanel(new DoiMatKhau());
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new MenuNhanVien().setVisible(true));
    }

    private void showPanel(javax.swing.JFrame form) {
        jPanel2.removeAll();
        jPanel2.setLayout(new java.awt.BorderLayout());
        try {
            // 1. Bóc trực tiếp cái nền (jPanel1) từ form con ra, vứt bỏ lớp vỏ JFrame đi
            javax.swing.JPanel mainPanel = (javax.swing.JPanel) form.getContentPane().getComponent(0);
            // 2. Phủ màu trắng tinh cho nền để khử sạch mọi mảng xám
            mainPanel.setBackground(java.awt.Color.WHITE);
            // 3. Nhét thẳng cái ruột đó vào jPanel2 của Menu
            jPanel2.add(mainPanel, java.awt.BorderLayout.CENTER);
        } catch (Exception e) {
            // Dự phòng nếu form bị lỗi thiết kế thì dùng cách cũ
            jPanel2.add(form.getContentPane(), java.awt.BorderLayout.CENTER);
        }
        jPanel2.revalidate();
        jPanel2.repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnTaoHoaDonMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
