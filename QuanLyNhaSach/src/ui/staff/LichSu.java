package ui.staff;

import dao.BillDAO;
import dao.impl.BillDAOImpl;
import entity.Bill;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class LichSu extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LichSu.class.getName());

    public LichSu() {
        initComponents();

        loadTable();
        addPlaceholder(txtTuNgay, "yyyy-MM-dd");
        addPlaceholder(txtDenNgay, "yyyy-MM-dd");
        addPlaceholder(txtNhapThongTin, "Nhập mã hóa đơn hoặc tên khách hàng...");

        //CHẶN NHẬP CHỮ
        addDateInputFilter(txtTuNgay);
        addDateInputFilter(txtDenNgay);
    }

    private BillDAO dao = new BillDAOImpl();

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tblLichSuGiaoDich.getModel();
        model.setRowCount(0);
        List<Bill> list = dao.getAll();
        for (Bill b : list) {
            model.addRow(new Object[]{
                b.getMaHoaDon(),
                b.getTenKhachHang(),
                b.getTenSanPham(),
                b.getSoLuong(),
                b.getKhuyenMai(),
                String.format("%,.0f VNĐ", b.getTongTien()),
                b.getNgayLap()
            });
        }
    }

    private boolean isFutureDate(String dateStr) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);

            java.util.Date inputDate = sdf.parse(dateStr);
            java.util.Date today = new java.util.Date();

            return inputDate.after(today); // ngày lớn hơn hiện tại
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isFromAfterTo(String from, String to) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d1 = sdf.parse(from);
            java.util.Date d2 = sdf.parse(to);
            return d1.after(d2); // từ ngày > đến ngày
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidSearch(String text) {
        return text.matches("^[a-zA-Z0-9\\sÀ-ỹ]+$");
    }

    private boolean isValidDate(String dateStr) {
        // bắt buộc đúng format + chỉ số
        if (!dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtNhapThongTin = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        txtTuNgay = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtDenNgay = new javax.swing.JTextField();
        btnLoc = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLichSuGiaoDich = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(27, 51, 132));
        jLabel1.setText("Lịch sử giao dịch");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("Nhập thông tin:");

        txtNhapThongTin.setColumns(8);
        txtNhapThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNhapThongTinActionPerformed(evt);
            }
        });

        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTim.setForeground(new java.awt.Color(12, 66, 139));
        btnTim.setIcon(new javax.swing.ImageIcon("D:\\Java\\JavaApplication5\\src\\img\\img\\search.png")); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

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

        btnLoc.setBackground(new java.awt.Color(28, 86, 156));
        btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLoc.setForeground(new java.awt.Color(255, 255, 255));
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        tblLichSuGiaoDich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên Khách hàng", "Tên sản phẩm", "Số lượng", "Giảm giá ", "Tổng tiền ", "Ngày xuất hóa đơn"
            }
        ));
        tblLichSuGiaoDich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuGiaoDichMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLichSuGiaoDich);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNhapThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel43)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 80, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtNhapThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel34))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNhapThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNhapThongTinActionPerformed
        
    }//GEN-LAST:event_txtNhapThongTinActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        
        String key = txtNhapThongTin.getText().trim();
        if (key.isEmpty() || key.equals("Nhập mã hóa đơn hoặc tên khách hàng...")) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "⚠ Vui lòng nhập mã hóa đơn hoặc tên khách hàng!");
            return;
        }
        if (!isValidSearch(key)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "⚠ Không được nhập ký tự đặc biệt!");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblLichSuGiaoDich.getModel();
        model.setRowCount(0);
        List<Bill> list = dao.search(key);
        if (list.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "❗ Không tìm thấy hóa đơn!");
            return;
        } 
        for (Bill b : list) {
            model.addRow(new Object[]{
                b.getMaHoaDon(),
                b.getTenKhachHang(),
                b.getTenSanPham(),
                b.getSoLuong(),
                b.getKhuyenMai(),
                String.format("%,.0f VNĐ", b.getTongTien()),
                b.getNgayLap()
            });
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void txtTuNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTuNgayActionPerformed
        
    }//GEN-LAST:event_txtTuNgayActionPerformed

    private void txtDenNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDenNgayActionPerformed
        
    }//GEN-LAST:event_txtDenNgayActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
                   
        String from = txtTuNgay.getText().trim();
        String to = txtDenNgay.getText().trim();
        if (from.equals("yyyy-MM-dd") || to.equals("yyyy-MM-dd")) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "⚠ Vui lòng chọn ngày hợp lệ!");
            return;
        }
        if (!isValidDate(from) || !isValidDate(to)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "⚠ Ngày phải đúng định dạng yyyy-MM-dd!\nVD: 2026-04-11");
            return;
        }
        if (isFutureDate(from) || isFutureDate(to)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "⚠ Không được nhập ngày trong tương lai!");
            return;
        }
        if (isFromAfterTo(from, to)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "⚠ Từ ngày không được lớn hơn đến ngày!");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblLichSuGiaoDich.getModel();
        model.setRowCount(0);
        List<Bill> list = dao.filter(from, to);
        if (list.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "❗ Không có dữ liệu trong khoảng thời gian này!");
            return;
        }
        for (Bill b : list) {
            model.addRow(new Object[]{
                b.getMaHoaDon(),
                b.getTenKhachHang(),
                b.getTenSanPham(),
                b.getSoLuong(),
                b.getKhuyenMai(),
                String.format("%,.0f VNĐ", b.getTongTien()),
                b.getNgayLap()
            });
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void tblLichSuGiaoDichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuGiaoDichMouseClicked
        
        if (evt.getClickCount() == 2) {
            int row = tblLichSuGiaoDich.getSelectedRow();

            String maHD = tblLichSuGiaoDich.getValueAt(row, 0).toString();

            javax.swing.JOptionPane.showMessageDialog(this,
                    "Bạn chọn hóa đơn: " + maHD);
        }
    }//GEN-LAST:event_tblLichSuGiaoDichMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new LichSu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblLichSuGiaoDich;
    private javax.swing.JTextField txtDenNgay;
    private javax.swing.JTextField txtNhapThongTin;
    private javax.swing.JTextField txtTuNgay;
    // End of variables declaration//GEN-END:variables

    private void addPlaceholder(javax.swing.JTextField txt, String placeholder) {
        txt.setText(placeholder);
        txt.setForeground(java.awt.Color.GRAY);
        txt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txt.getText().equals(placeholder)) {
                    txt.setText("");
                    txt.setForeground(java.awt.Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txt.getText().isEmpty()) {
                    txt.setText(placeholder);
                    txt.setForeground(java.awt.Color.GRAY);
                }
            }
        });
    }

    private void addDateInputFilter(javax.swing.JTextField txt) {
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();

                // chỉ cho nhập số và dấu -
                if (!Character.isDigit(c) && c != '-') {
                    evt.consume(); // ❌ chặn
                }
            }
        });
    }
}
