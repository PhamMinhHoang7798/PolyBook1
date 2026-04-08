package dao.impl;

import dao.SanPhamDAO;
import entity.SanPham;
import util.XJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAOImpl implements SanPhamDAO {

    String INSERT_SQL = "INSERT INTO SanPham (MaSanPham, TenSanPham, DonGia, SoLuongTon, MaLoai) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE SanPham SET TenSanPham=?, DonGia=?, SoLuongTon=?, MaLoai=? WHERE MaSanPham=?";
    String DELETE_SQL = "DELETE FROM SanPham WHERE MaSanPham=?";
    String SELECT_ALL_SQL = "SELECT * FROM SanPham";
    String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE MaSanPham=?";

    @Override
    public void insert(SanPham sp) {
        XJdbc.executeUpdate(INSERT_SQL,
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                sp.getDonGia(),
                sp.getSoLuongTon(),
                sp.getMaLoai()
        );
    }

    @Override
    public void update(SanPham sp) {
        XJdbc.executeUpdate(UPDATE_SQL,
                sp.getTenSanPham(),
                sp.getDonGia(),
                sp.getSoLuongTon(),
                sp.getMaLoai(),
                sp.getMaSanPham()
        );
    }

    @Override
    public void delete(String maSanPham) {
        XJdbc.executeUpdate(DELETE_SQL, maSanPham);
    }

    @Override
    public SanPham selectById(String maSanPham) {
        List<SanPham> list = selectBySql(SELECT_BY_ID_SQL, maSanPham);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<SanPham> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    // ================= CORE =================
    protected List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getString("MaSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setDonGia(rs.getDouble("DonGia"));
                sp.setSoLuongTon(rs.getInt("SoLuongTon"));
                sp.setMaLoai(rs.getString("MaLoai"));
                list.add(sp);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
