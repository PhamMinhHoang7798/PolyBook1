package dao.impl;

import dao.SanPhamDAO;
import entity.SanPham;
import util.XJdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAOImpl implements SanPhamDAO {

    String INSERT_SQL = "INSERT INTO SanPham (MaSanPham, TenSanPham, DonGia, SoLuongTon, MaLoai) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE SanPham SET TenSanPham=?, DonGia=?, SoLuongTon=?, MaLoai=? WHERE MaSanPham=?";
    String DELETE_SQL = "DELETE FROM SanPham WHERE MaSanPham=?";
    String SELECT_ALL_SQL = "SELECT * FROM SanPham";
    String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE MaSanPham=?";
    String SELECT_BY_KEYWORD_SQL = 
    "SELECT * FROM SanPham " +
    "WHERE dbo.fn_RemoveVietnameseAccent(MaSanPham) LIKE dbo.fn_RemoveVietnameseAccent(?) " +
    "OR dbo.fn_RemoveVietnameseAccent(TenSanPham) LIKE dbo.fn_RemoveVietnameseAccent(?)";


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
    public void delete(String id) {
        // 1. ĐI DỌN DẸP BẢN GHI CON TRƯỚC (Phá vỡ ràng buộc khóa ngoại)
        // Xóa sạch các chi tiết hóa đơn có chứa mã sản phẩm này
        String sqlDeleteChiTiet = "DELETE FROM HoaDonChiTiet WHERE MaSanPham = ?";
        util.XJdbc.executeUpdate(sqlDeleteChiTiet, id);

        // 2. KHI ĐÃ SẠCH SẼ RÀNG BUỘC, TIẾN HÀNH XÓA SẢN PHẨM (BẢN GHI CHA)
        util.XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<SanPham> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public SanPham selectById(String id) {
        List<SanPham> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<SanPham> selectByKeyword(String keyword) {
        return selectBySql(
                SELECT_BY_KEYWORD_SQL,
                "%" + keyword + "%",
                "%" + keyword + "%"
        );
    }

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
                sp.setMaLoai(rs.getString("MaLoai"));

                list.add(sp);
            }

            rs.getStatement().getConnection().close();

        } catch (Exception e) {
            throw new RuntimeException("Lỗi SanPham DAO", e);
        }

        return list;
    }
}
