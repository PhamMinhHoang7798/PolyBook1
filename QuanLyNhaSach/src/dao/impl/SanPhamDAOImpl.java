package dao.impl;

import dao.SanPhamDAO;
import entity.SanPham;
import util.XJdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAOImpl implements SanPhamDAO {

    @Override
    public List<SanPham> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM SanPham WHERE MaSanPham LIKE ? OR TenSanPham LIKE ?";
        return selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    @Override
    public List<SanPham> selectAll() {
        String sql = "SELECT * FROM SanPham";
        return selectBySql(sql);
    }

    @Override
    public SanPham selectById(String id) {
        String sql = "SELECT * FROM SanPham WHERE MaSanPham = ?";
        List<SanPham> list = selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void insert(SanPham sp) {
        String sql = "INSERT INTO SanPham (MaSanPham, TenSanPham, DonGia, SoLuongTon) VALUES (?, ?, ?, ?)";
        XJdbc.update(sql,
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                sp.getDonGia(),
                sp.getSoLuongTon()
        );
    }

    @Override
    public void update(SanPham sp) {
        String sql = "UPDATE SanPham SET TenSanPham=?, DonGia=?, SoLuongTon=? WHERE MaSanPham=?";
        XJdbc.update(sql,
                sp.getTenSanPham(),
                sp.getDonGia(),
                sp.getSoLuongTon(),
                sp.getMaSanPham()
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM SanPham WHERE MaSanPham=?";
        XJdbc.update(sql, id);
    }

    // ⭐ METHOD CHUNG DUY NHẤT
    private List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();

        try {
            ResultSet rs = XJdbc.query(sql, args);

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getString("MaSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setDonGia(rs.getDouble("DonGia"));     
                sp.setSoLuongTon(rs.getInt("SoLuongTon"));

                list.add(sp);
            }

            rs.getStatement().getConnection().close();

        } catch (Exception e) {
            System.out.println("❌ Lỗi truy vấn sản phẩm!");
            e.printStackTrace();
        }

        return list;
    }
}