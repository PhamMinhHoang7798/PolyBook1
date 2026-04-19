package ui.staff;

//Lớp Welcome: Màn hình chào mừng (Splash Screen) của ứng dụng PolyBook. 
//Mục đích: Hiển thị logo dự án và thanh tiến trình (progress bar) trước khi vào màn hình đăng nhập.
public class Welcome extends javax.swing.JFrame {

    // Logger để ghi lại các lỗi hệ thống nếu có trong quá trình khởi chạy
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Welcome.class.getName());

    public Welcome() {
        initComponents();// Khởi tạo các thành phần giao diện (nút, nhãn, thanh tiến trình)
        this.setLocationRelativeTo(null);//dòng này để đưa cửa sổ ra giữa màn hình
        startLoading();// Bắt đầu chạy tiến trình load thanh %
    }

//    open: Phương thức hỗ trợ hiển thị form ra giữa màn hình (dùng khi gọi từ các lớp khác).
    public void open() {
        this.setLocationRelativeTo(null);
    }

    //startLoading: Xử lý logic chạy thanh tiến trình (JProgressBar). 
    //Mục đích:Tạo hiệu ứng tải ứng dụng từ 0% đến 100%.
    void startLoading() {
        // Sử dụng một Thread (luồng) riêng để không làm treo giao diện người dùng
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    // Tạm dừng luồng trong 30ms để tạo tốc độ load (tổng cộng khoảng 3 giây)
                    Thread.sleep(30); // tốc độ load
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setValue(i);
                progressBar.setString(i + "%");
                if (i == 100) {
                    //// Khi tiến trình đạt 100%, đóng màn hình chào và mở màn hình Đăng nhập
                    dispose(); // đóng màn welcome

                    new Login().setVisible(true); // mở form chính (ví dụ Login)
                }
            }
        }).start();// Bắt đầu chạy luồng
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        progressBar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        progressBar.setStringPainted(true);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel1))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        java.awt.EventQueue.invokeLater(() -> new Welcome().setVisible(true));// Khởi tạo và hiển thị màn hình Welcome trên luồng sự kiện của Swing
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}
