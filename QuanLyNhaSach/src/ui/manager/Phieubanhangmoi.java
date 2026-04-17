package ui.manager;

import dao.impl.hoadonDAOImpl;
import entity.hoadon;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import util.XDialog;
import java.text.SimpleDateFormat;

public class Phieubanhangmoi extends javax.swing.JFrame {

    hoadonDAOImpl dao = new hoadonDAOImpl(); // Khai báo để dùng chung
    dao.impl.KhachHangDAOImpl khDAO = new dao.impl.KhachHangDAOImpl();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Phieubanhangmoi.class.getName());
    // Khai báo Model bảng và biến tổng tiền
    private javax.swing.table.DefaultTableModel modelGioHang;
    private double tongTien = 0;
    private double tienGiamGia = 0;

    public Phieubanhangmoi() {
        initComponents();
        init(); // Khởi tạo dữ liệu
    }

    private void init() {
        setLocationRelativeTo(null);
        modelGioHang = (javax.swing.table.DefaultTableModel) tblDanhSach.getModel();
        modelGioHang.setRowCount(0);
        // 1. Mặc định chọn Chuyển khoản
        jComboBox1.setSelectedItem("chuyển khoản");
        // 2. Gọi hàm ẩn các ô tiền mặt ngay khi mở trang
        toggleTienMatFields(false);
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        jTextField3.setText(sdf.format(new java.util.Date()));
    }

    // Hàm phụ trợ để ẩn/hiện các dòng tiền mặt cho gọn code
    private void toggleTienMatFields(boolean visible) {
        jLabel11.setVisible(visible);      // Nhãn "Tiền khách đưa"
        txtTienKhachDua.setVisible(visible); // Ô nhập tiền khách đưa
        jLabel17.setVisible(visible);      // Nhãn "Tiền thối"
        jLabel15.setVisible(visible);      // Nhãn hiển thị số tiền thối
    }

    // Hàm tính toán lại tổng tiền mỗi khi có thay đổi trong bảng
    private void tinhTongTien() {
        double tongTienHang = 0;
        // Tính tổng tiền từ bảng giỏ hàng
        for (int i = 0; i < modelGioHang.getRowCount(); i++) {
            tongTienHang += Double.parseDouble(modelGioHang.getValueAt(i, 4).toString());
        }
        // Tính tổng tiền thực tế sau khi trừ Voucher
        tongTien = tongTienHang - tienGiamGia;
        if (tongTien < 0) {
            tongTien = 0;
        }
        // --- ĐIỀU CHỈNH ĐỂ HIỂN THỊ TRỐNG ---
        if (tienGiamGia > 0) {
            txtMaVoucher.setText(String.format("%,.0f VNĐ", tienGiamGia));
        } else {
            txtMaVoucher.setText(""); // Nếu không có giảm giá thì để trống hoàn toàn
        }
        // ------------------------------------------------
        jLabel12.setText(String.format("%,.0f VNĐ", tongTien));
        // Cập nhật lại tiền thối (nếu có)
        txtTienKhachDuaKeyReleased(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDiem = new javax.swing.JLabel();
        txtLoaiThe = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        btnThanhToan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnInHoaĐon = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        txtMaVoucher = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(227, 227, 227));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.lightGray));

        jLabel1.setBackground(new java.awt.Color(33, 78, 147));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(35, 63, 121));
        jLabel1.setText("Tạo phiếu bán hàng mới");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Thông tin khách hàng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Điểm:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày bán:");

        jTextField1.setToolTipText("");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setToolTipText("");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setToolTipText("");

        jLabel6.setText(" ");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Khách hàng:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Loại Thẻ:");

        txtDiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDiem.setText(" ");

        txtLoaiThe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtLoaiThe.setText(" ");

        btnTim.setBackground(new java.awt.Color(65, 102, 160));
        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTim.setForeground(new java.awt.Color(255, 255, 255));
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                                    .addComponent(jTextField2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel16))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtLoaiThe, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(txtDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtLoaiThe)
                        .addComponent(jLabel16))
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(txtDiem)
                    .addComponent(btnTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Thông tin sản phẩm");

        jButton1.setBackground(new java.awt.Color(65, 102, 160));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Thêm sản phẩm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Thành tiền"
            }
        ));
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        btnThanhToan.setBackground(new java.awt.Color(96, 147, 104));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(209, 98, 98));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnHuy))
                .addGap(175, 175, 175))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Tổng tiền:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Giảm giá:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Phương thức:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Tiền khách đưa:");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "chuyển khoản", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText(" ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText(" ");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Tiền thối:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText(" ");

        btnInHoaĐon.setBackground(new java.awt.Color(96, 147, 104));
        btnInHoaĐon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnInHoaĐon.setForeground(new java.awt.Color(255, 255, 255));
        btnInHoaĐon.setText("IN HÓA ĐƠN");
        btnInHoaĐon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaĐonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText(" ");

        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        txtMaVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaVoucherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(492, 492, 492))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtMaVoucher))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnInHoaĐon, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMaVoucher))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17))
                .addGap(35, 35, 35)
                .addComponent(jLabel18)
                .addGap(10, 10, 10)
                .addComponent(btnInHoaĐon)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(333, 333, 333)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 345, Short.MAX_VALUE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // Hiện hộp thoại yêu cầu nhập Mã Sản Phẩm
        String maSP = javax.swing.JOptionPane.showInputDialog(this, "Nhập mã sản phẩm cần thêm:");
        if (maSP == null || maSP.trim().isEmpty()) {
            return; // Nếu người dùng bấm Hủy hoặc không nhập gì thì thoát
        }
        // Tìm sản phẩm từ Database thông qua DAO
        dao.SanPhamDAO spDAO = new dao.impl.SanPhamDAOImpl();
        entity.SanPham sp = spDAO.selectById(maSP.trim());

        if (sp != null) {
            // Hiện hộp thoại yêu cầu nhập Số Lượng (Mặc định là 1)
            String soLuongStr = javax.swing.JOptionPane.showInputDialog(this, "Nhập số lượng:", "1");
            if (soLuongStr == null) {
                return;
            }
            try {
                int soLuong = Integer.parseInt(soLuongStr);
                double thanhTien = soLuong * sp.getDonGia();

                // Đẩy dữ liệu vào jTable1 (đúng thứ tự cột bạn thiết kế)
                modelGioHang.addRow(new Object[]{
                    sp.getMaSanPham(),
                    sp.getTenSanPham(),
                    soLuong,
                    sp.getDonGia(),
                    thanhTien,
                    "Xóa" // Chữ hiển thị ở cột thao tác
                });
                tinhTongTien(); // Cập nhật lại nhãn tổng tiền ở dưới
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Số lượng phải là số hợp lệ!");
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm có mã: " + maSP);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        int row = tblDanhSach.getSelectedRow();
        if (row < 0) {
            return; // Nếu không chọn dòng nào thì bỏ qua
        }
        // Lấy thông tin từ các cột: 1-Tên SP, 2-Số lượng, 3-Giá
        String tenSP = modelGioHang.getValueAt(row, 1).toString();

        // Tạo bảng lựa chọn thao tác cho nhân viên
        Object[] options = {"Sửa số lượng", "Xóa sản phẩm", "Hủy bỏ"};
        int choice = javax.swing.JOptionPane.showOptionDialog(this,
                "Bạn muốn làm gì với sản phẩm: " + tenSP + "?",
                "Điều chỉnh giỏ hàng",
                javax.swing.JOptionPane.YES_NO_CANCEL_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (choice == 0) { // CHỌN: SỬA SỐ LƯỢNG
            String currentQty = modelGioHang.getValueAt(row, 2).toString();
            String input = javax.swing.JOptionPane.showInputDialog(this,
                    "Nhập số lượng mới cho [" + tenSP + "]:", currentQty);

            if (input != null && !input.isEmpty()) {
                try {
                    int slMoi = Integer.parseInt(input);
                    if (slMoi <= 0) {
                        util.XDialog.alert(this, "Số lượng phải lớn hơn 0! Nếu muốn xóa hãy chọn 'Xóa sản phẩm'.");
                        return;
                    }

                    double gia = Double.parseDouble(modelGioHang.getValueAt(row, 3).toString());

                    // Cập nhật lại Số lượng (cột 2) và Thành tiền (cột 4)
                    modelGioHang.setValueAt(slMoi, row, 2);
                    modelGioHang.setValueAt(slMoi * gia, row, 4);

                    // Tính lại tổng tiền và cập nhật giao diện
                    tinhTongTien();

                } catch (NumberFormatException e) {
                    util.XDialog.alert(this, "Vui lòng chỉ nhập số nguyên!");
                }
            }
        } else if (choice == 1) { // CHỌN: XÓA SẢN PHẨM
            if (util.XDialog.confirm(this, "Bạn chắc chắn muốn xóa sản phẩm này khỏi đơn hàng?")) {
                modelGioHang.removeRow(row);

                // Sau khi xóa phải tính lại tổng tiền ngay
                tinhTongTien();

                // Nếu xóa sạch giỏ hàng thì ẩn nút In hóa đơn đi cho chắc
                if (modelGioHang.getRowCount() == 0) {
                    btnInHoaĐon.setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // 1. Kiểm tra giỏ hàng
        if (modelGioHang.getRowCount() == 0) {
            util.XDialog.alert(this, "Giỏ hàng đang trống, không thể thanh toán!");
            return;
        }

        // 2. Kiểm tra logic Tiền mặt (MỚI THÊM)
        String phuongThuc = jComboBox1.getSelectedItem().toString();
        if (phuongThuc.equalsIgnoreCase("Tiền mặt")) {
            String txtKhachDua = txtTienKhachDua.getText().trim();

            if (txtKhachDua.isEmpty()) {
                util.XDialog.alert(this, "Vui lòng nhập số tiền khách đưa!");
                txtTienKhachDua.requestFocus();
                return;
            }

            try {
                double tienKhachDua = Double.parseDouble(txtKhachDua);
                if (tienKhachDua < tongTien) {
                    util.XDialog.alert(this, "Số tiền khách đưa không đủ để thanh toán!");
                    txtTienKhachDua.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                util.XDialog.alert(this, "Số tiền khách đưa phải là con số!");
                txtTienKhachDua.requestFocus();
                return;
            }
        }

        // 3. Tiến hành xử lý lưu Database & Tích điểm nếu vượt qua các bước kiểm tra trên
        try {
            // --- PHẦN LOGIC LƯU HÓA ĐƠN ---
            // (Chèn lệnh dao.insert(hd) của ông ở đây nếu cần)

            // --- LOGIC TÍCH ĐIỂM THÀNH VIÊN ---
            String sdt = jTextField2.getText().trim();
            if (!sdt.isEmpty()) {
                entity.KhachHang kh = khDAO.selectByPhone(sdt);
                if (kh != null) {
                    int diemCongThem = (int) (tongTien / 1000);
                    int diemTongMoi = kh.getDiemTichLuy() + diemCongThem;

                    kh.setDiemTichLuy(diemTongMoi);
                    kh.setLoaiThe(xacDinhLoaiThe(diemTongMoi));
                    khDAO.update(kh);

                    txtDiem.setText(String.valueOf(diemTongMoi));
                    txtLoaiThe.setText(kh.getLoaiThe());

                    util.XDialog.alert(this, "Thanh toán thành công!\nĐã cộng " + diemCongThem + " điểm cho khách.");
                } else {
                    util.XDialog.alert(this, "Thanh toán thành công (Khách lẻ)!");
                }
            } else {
                util.XDialog.alert(this, "Thanh toán thành công!");
            }

            // 4. HIỆN NÚT IN HÓA ĐƠN
            btnInHoaĐon.setVisible(true);

        } catch (Exception e) {
            util.XDialog.alert(this, "Lỗi khi xử lý thanh toán: " + e.getMessage());
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        modelGioHang.setRowCount(0);
        tongTien = 0;
        tienGiamGia = 0;

        // Xóa trắng dữ liệu khách hàng
        jTextField1.setText("");
        jTextField2.setText("");
        txtDiem.setText("");
        txtLoaiThe.setText("");
        txtMaVoucher.setText("");

        jLabel12.setText("0 VNĐ");
        // Đảm bảo không xóa nhầm tiêu đề jLabel16 như nãy nữa

        btnInHoaĐon.setVisible(false);
        util.XDialog.alert(this, "Đã làm mới hệ thống.");
    }//GEN-LAST:event_btnHuyActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        timKhachHang();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void txtMaVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaVoucherActionPerformed

        String maVoucher = txtMaVoucher.getText().trim();
        if (maVoucher.isEmpty()) {
            tienGiamGia = 0;
            tinhTongTien();
            return;
        }
        try {
            // Truy vấn trực tiếp từ bảng Voucher trong Database
            String sql = "SELECT Giagiam FROM Voucher WHERE MaVoucher = ?";
            // Sử dụng util.XQuery hoặc XJdbc tùy theo thư viện ông có
            java.sql.ResultSet rs = util.XJdbc.query(sql, maVoucher);

            if (rs.next()) {
                double giaTriGiam = rs.getDouble("Giagiam");
                // Theo quy ước của ông: 5.00 trong DB = 5,000 VNĐ
                tienGiamGia = giaTriGiam * 1000;

                util.XDialog.alert(this, "Áp dụng mã giảm giá thành công: " + String.format("%,.0f VNĐ", tienGiamGia));
                tinhTongTien(); // Tính lại toàn bộ tiền
            } else {
                util.XDialog.alert(this, "Mã giảm giá không tồn tại hoặc đã hết hạn!");
                tienGiamGia = 0;
                tinhTongTien();
            }
        } catch (Exception e) {
            util.XDialog.alert(this, " lỗi kiểm tra Voucher: " + e.getMessage());
        }
    }//GEN-LAST:event_txtMaVoucherActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased

        try {
            if (txtTienKhachDua.getText().trim().isEmpty()) {
                jLabel15.setText("0 VNĐ");
                return;
            }
            double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
            double tienThoi = tienKhachDua - tongTien; // tongTien đã được tính ở hàm tinhTongTien()
            if (tienThoi < 0) {
                jLabel15.setText("Khách đưa thiếu!");
            } else {
                jLabel15.setText(String.format("%,.0f VNĐ", tienThoi));
            }
        } catch (NumberFormatException e) {
            jLabel15.setText("Số tiền không hợp lệ!");
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void btnInHoaĐonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaĐonActionPerformed
        // 1. Thu thập thông tin từ giao diện
        String tenKH = jTextField1.getText().trim();
        if (tenKH.isEmpty()) {
            tenKH = "Khách lẻ";
        }

        String sdt = jTextField2.getText().trim();
        String ngay = jTextField3.getText().trim();
        String pthanhToan = jComboBox1.getSelectedItem().toString();

        // Thông tin thành viên
        String diem = txtDiem.getText().trim();
        String loaiThe = txtLoaiThe.getText().trim();

        // 2. Dựng nội dung biên lai
        StringBuilder bill = new StringBuilder();
        bill.append("        NHÀ SÁCH POLY BOOK\n");
        bill.append("   ĐC: 123 Trịnh Văn Bô, Nam Từ Liêm\n");
        bill.append("   SĐT: 0987.654.321\n");
        bill.append("------------------------------------------\n");
        bill.append(String.format(" KH: %-25s\n", tenKH));

        if (!sdt.isEmpty()) {
            bill.append(String.format(" SĐT: %-20s\n", sdt));
        }

        // --- LOGIC HIỂN THỊ ĐIỂM TRÊN BIÊN LAI ---
        if (!tenKH.equals("Khách lẻ") && !diem.isEmpty()) {
            bill.append(String.format(" Hạng thành viên: %-15s\n", loaiThe));
            bill.append(String.format(" Điểm tích lũy: %-15s\n", diem));
        }
        // ----------------------------------------

        bill.append(String.format(" Ngày: %-20s\n", ngay));
        bill.append(String.format(" Phương thức: %-15s\n", pthanhToan));
        bill.append("------------------------------------------\n");
        bill.append(String.format("%-18s %-3s %-10s %-10s\n", "Tên SP", "SL", "Giá", "T.Tiền"));

        // Duyệt giỏ hàng để in sản phẩm
        for (int i = 0; i < modelGioHang.getRowCount(); i++) {
            String ten = modelGioHang.getValueAt(i, 1).toString();
            String sl = modelGioHang.getValueAt(i, 2).toString();
            String gia = modelGioHang.getValueAt(i, 3).toString();
            String tt = modelGioHang.getValueAt(i, 4).toString();

            // Rút gọn tên nếu quá dài
            if (ten.length() > 15) {
                ten = ten.substring(0, 15) + "..";
            }
            bill.append(String.format("%-18s %-3s %-10s %-10s\n", ten, sl, gia, tt));
        }

        bill.append("------------------------------------------\n");

        // In giảm giá nếu có dùng Voucher
        if (tienGiamGia > 0) {
            bill.append(String.format(" GIẢM GIÁ:             -%,.0f VNĐ\n", tienGiamGia));
        }

        bill.append(String.format(" TỔNG CỘNG:            %,.0f VNĐ\n", tongTien));

        // In tiền khách đưa/thối nếu trả tiền mặt
        if (pthanhToan.equalsIgnoreCase("Tiền mặt")) {
            try {
                double khachDua = Double.parseDouble(txtTienKhachDua.getText().trim());
                bill.append(String.format(" TIỀN KHÁCH ĐƯA:      %,.0f VNĐ\n", khachDua));
                bill.append(String.format(" TIỀN THỐI LẠI:       %,.0f VNĐ\n", khachDua - tongTien));
            } catch (Exception e) {
                bill.append(" TIỀN KHÁCH ĐƯA:      (Chưa nhập)\n");
            }
        }

        bill.append("------------------------------------------\n");
        bill.append("      CẢM ƠN QUÝ KHÁCH. HẸN GẶP LẠI!\n");

        // 3. Hiển thị thông báo hóa đơn
        javax.swing.JTextArea txtArea = new javax.swing.JTextArea(bill.toString());
        txtArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 13));
        txtArea.setEditable(false);
        javax.swing.JOptionPane.showMessageDialog(this, new javax.swing.JScrollPane(txtArea), "BIÊN LAI BÁN HÀNG", 1);

        // 4. Tự động reset phiếu về trạng thái mới
        btnHuyActionPerformed(null);
    }//GEN-LAST:event_btnInHoaĐonActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        String method = jComboBox1.getSelectedItem().toString();

        if (method.equals("Tiền mặt")) {
            toggleTienMatFields(true);  // Hiện ra nếu là tiền mặt
        } else {
            toggleTienMatFields(false); // Ẩn đi nếu là chuyển khoản
            txtTienKhachDua.setText("");
            jLabel15.setText("0 VNĐ");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        timKhachHang();
    }//GEN-LAST:event_btnTimActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Phieubanhangmoi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnInHoaĐon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JLabel txtDiem;
    private javax.swing.JLabel txtLoaiThe;
    private javax.swing.JTextField txtMaVoucher;
    private javax.swing.JTextField txtTienKhachDua;
    // End of variables declaration//GEN-END:variables
// Hàm xác định hạng thẻ dựa trên điểm tích lũy (1000đ = 1 điểm)
    private String xacDinhLoaiThe(int diem) {
        if (diem >= 6000) {
            return "Kim cương"; // Chi tiêu >= 6 triệu
        }
        if (diem >= 4000) {
            return "Vàng";      // Chi tiêu >= 4 triệu
        }
        if (diem >= 2000) {
            return "Bạc";       // Chi tiêu >= 2 triệu
        }
        return "Đồng";          // Chi tiêu < 2 triệu
    }

    private void timKhachHang() {
        String sdt = jTextField2.getText().trim();
        if (sdt.isEmpty()) {
            util.XDialog.alert(this, "Vui lòng nhập số điện thoại khách hàng!");
            return;
        }

        try {
            // Gọi DAO tìm khách qua SĐT
            entity.KhachHang kh = khDAO.selectByPhone(sdt);

            if (kh != null) {
                // Nếu tìm thấy: Hiển thị thông tin
                jTextField1.setText(kh.getTenKhachHang());
                txtDiem.setText(String.valueOf(kh.getDiemTichLuy()));

                String loaiThe = xacDinhLoaiThe(kh.getDiemTichLuy());
                txtLoaiThe.setText(loaiThe);

                util.XDialog.alert(this, "Tìm thấy Thành viên: " + kh.getTenKhachHang() + "\nHạng: " + loaiThe);
            } else {
                // Nếu không tìm thấy: Báo khách lẻ
                util.XDialog.alert(this, "Số điện thoại này chưa có trong hệ thống!");
                jTextField1.setText("Khách lẻ");
                txtDiem.setText("0");
                txtLoaiThe.setText("Đồng");
            }
        } catch (Exception e) {
            util.XDialog.alert(this, "Lỗi khi tìm kiếm khách hàng!");
        }
    }
}
