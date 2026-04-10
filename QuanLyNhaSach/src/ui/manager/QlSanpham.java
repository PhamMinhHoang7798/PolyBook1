/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.manager;

import dao.SanPhamDAO;
import dao.impl.SanPhamDAOImpl;
import entity.SanPham;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.LoaiSPDAO;
import dao.impl.LoaiSPDAOImpl;
import entity.LoaiSP;
/**
 *
 * @author nguye
 */
public class QlSanpham extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(QlSanpham.class.getName());

    /**
     * Creates new form QlSanpham
     */
    public QlSanpham() {
        initComponents();
        loadTable();
        tblQLSanPham.setDefaultEditor(Object.class, null);
        tblQLSanPham.setRowHeight(30);
        tblQLSanPham.getColumnModel().getColumn(0).setPreferredWidth(80);
        tblQLSanPham.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblQLSanPham.getColumnModel().getColumn(2).setPreferredWidth(250);
        tblQLSanPham.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblQLSanPham.getColumnModel().getColumn(4).setPreferredWidth(80);
        loadComboBox();
    }
    LoaiSPDAO loaiDAO = new LoaiSPDAOImpl();
    SanPhamDAO dao = new SanPhamDAOImpl();
    int row = -1;
    
    void loadComboBox() {
    cmbLoaiSP.removeAllItems();

    for (LoaiSP l : loaiDAO.selectAll()) {
        cmbLoaiSP.addItem(l);
    }
}

    void loadTable() {
    DefaultTableModel model = (DefaultTableModel) tblQLSanPham.getModel();  
    model.setRowCount(0);
   
    try {
        for (SanPham sp : dao.selectAll()) {
    LoaiSP loai = loaiDAO.findById(sp.getMaLoai()); // lấy loại

    model.addRow(new Object[]{
        sp.getMaSanPham(),
        loai != null ? loai.getTenLoai() : "", // HIỂN THỊ TÊN
        sp.getTenSanPham(),
        String.format("%,.0f", sp.getDonGia()).replace(",", ".") + " VND",
        sp.getSoLuongTon()
    });
}
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    SanPham getForm() {
    SanPham sp = new SanPham();

    try {
        if (txtMaSP.getText().trim().isEmpty() || txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống Mã SP hoặc Tên SP!");
            return null;
        }

        if (txtGia.getText().trim().isEmpty() || txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá và Số lượng không được để trống!");
            return null;
        }
        if (cmbLoaiSP.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm!");
            return null;
        }
        sp.setMaSanPham(txtMaSP.getText().trim());
        sp.setTenSanPham(txtTenSP.getText().trim());

        double gia = Double.parseDouble(
        txtGia.getText().replace(".", "").trim()
        );
        int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

        // 🔴 Validate số âm
        if (gia < 0 || soLuong < 0) {
            JOptionPane.showMessageDialog(this, "Giá và Số lượng phải >= 0!");
            return null;
        }
        sp.setDonGia(gia);
        sp.setSoLuongTon(soLuong);
        // 🔴 Lấy từ combobox
        LoaiSP loai = (LoaiSP) cmbLoaiSP.getSelectedItem();
        sp.setMaLoai(loai.getMaLoai());

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Giá phải là số thực, Số lượng phải là số nguyên!");
        return null;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi dữ liệu!");
        return null;
    }

    return sp;
}
    void setForm(SanPham sp) {
    txtMaSP.setText(sp.getMaSanPham());
    txtTenSP.setText(sp.getTenSanPham());
    txtGia.setText(String.valueOf(sp.getDonGia()));
    txtSoLuong.setText(String.valueOf(sp.getSoLuongTon()));

    // set combobox
    for (int i = 0; i < cmbLoaiSP.getItemCount(); i++) {
        LoaiSP loai = cmbLoaiSP.getItemAt(i);
        if (loai.getMaLoai().equals(sp.getMaLoai())) {
            cmbLoaiSP.setSelectedIndex(i);
            break;
        }
    }
}

    void edit() {
    String ma = tblQLSanPham.getValueAt(row, 0).toString();
    SanPham sp = dao.selectById(ma);
    if (sp != null) {
        setForm(sp);
    }
    txtMaSP.setEditable(false);

}

    void insert() {
    SanPham sp = getForm();
    if (sp == null) return;
    try {
        dao.insert(sp);
        loadTable();
        JOptionPane.showMessageDialog(this, "Thêm thành công");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi thêm");
        e.printStackTrace();
    }
}

    void update() {
    SanPham sp = getForm();
    if (sp == null) return;

    try {
        dao.update(sp);
        loadTable();
        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi cập nhật");
    }
}
    

    void delete() {
    String ma = txtMaSP.getText();

    if (ma.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm để xóa!");
        return;
    }

    try {
        dao.delete(ma);
        loadTable();
        clearForm();
        JOptionPane.showMessageDialog(this, "Xóa thành công");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi xóa");
    }
}

    void clearForm() {
    txtMaSP.setText("");
    txtTenSP.setText("");
    txtGia.setText("");
    txtSoLuong.setText("");
    txtMaSP.setEditable(true);
    if (cmbLoaiSP.getItemCount() > 0) {
    cmbLoaiSP.setSelectedIndex(0);
}
}

    void timKiem() {
    String keyword = txtTim.getText();

    DefaultTableModel model = (DefaultTableModel) tblQLSanPham.getModel();
    model.setRowCount(0);

    for (SanPham sp : dao.selectAll()) {
        if (sp.getTenSanPham().toLowerCase().contains(keyword.toLowerCase())) {
            model.addRow(new Object[]{
                sp.getMaSanPham(),
                sp.getMaLoai(),
                sp.getTenSanPham(),
                sp.getDonGia(),
                sp.getSoLuongTon()
            });
        }
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLSanPham = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnCapNhat = new javax.swing.JButton();
        btnTao = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnNhapMoi = new javax.swing.JButton();
        txtSoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        cmbLoaiSP = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(716, 630));

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        tblQLSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblQLSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", " Loại sản phẩm", "Tên sản phẩm", "Giá", "Số lượng"
            }
        ));
        tblQLSanPham.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblQLSanPhamAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblQLSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLSanPham);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Danh sách ", jPanel2);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Mã sản phẩm");

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Loại sản phẩm");

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon("D:\\Java\\JavaApplication5\\src\\img\\img\\Edit.png")); // NOI18N
        btnCapNhat.setText("Cập nhập");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnTao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTao.setIcon(new javax.swing.ImageIcon("D:\\Java\\JavaApplication5\\src\\img\\img\\Accept.png")); // NOI18N
        btnTao.setText("Tạo mới");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon("D:\\Java\\JavaApplication5\\src\\img\\img\\Delete.png")); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnNhapMoi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhapMoi.setIcon(new javax.swing.ImageIcon("D:\\Java\\JavaApplication5\\src\\img\\img\\Add.png")); // NOI18N
        btnNhapMoi.setText("Nhập mới");
        btnNhapMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapMoiActionPerformed(evt);
            }
        });

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Tên sản phẩm");

        txtTenSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Số lượng");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Giá:");

        txtGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        cmbLoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                            .addComponent(cmbLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoLuong)
                            .addComponent(jLabel4)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                            .addComponent(jLabel5)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(58, Short.MAX_VALUE)
                        .addComponent(btnTao)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhat)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNhapMoi)
                        .addGap(36, 36, 36)))
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(cmbLoaiSP))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(104, 104, 104)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTao, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab("Biểu mẫu", jPanel3);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Quản lý sản phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSPActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void tblQLSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSanPhamMouseClicked
        row = tblQLSanPham.getSelectedRow();

    // Double click
    if (evt.getClickCount() == 2) {
        edit();

        // chuyển tab qua "Biểu mẫu"
        jTabbedPane1.setSelectedIndex(1);
    }
    }//GEN-LAST:event_tblQLSanPhamMouseClicked

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        // TODO add your handling code here:
        this.insert();
    }//GEN-LAST:event_btnTaoActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnNhapMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapMoiActionPerformed
        // TODO add your handling code here:
        this.clearForm();
    }//GEN-LAST:event_btnNhapMoiActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        this.timKiem();
    }//GEN-LAST:event_btnTimActionPerformed

    private void tblQLSanPhamAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblQLSanPhamAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblQLSanPhamAncestorAdded

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new QlSanpham().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnNhapMoi;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<LoaiSP> cmbLoaiSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblQLSanPham;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
