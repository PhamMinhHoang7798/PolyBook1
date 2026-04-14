package dao.impl;

import dao.TheThanhVienDAO;
import entity.TheThanhVien;
import util.XJdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TheThanhVienDAOImpl implements TheThanhVienDAO {

    String SELECT_ALL_SQL = """
        SELECT MaThe, MaKhachHang, SDT, TenKhachHang, DiemTichLuy
        FROM TheThanhVien
    """;

    String SELECT_BY_KEYWORD_SQL = """
        SELECT MaThe, MaKhachHang, SDT, TenKhachHang, DiemTichLuy
        FROM TheThanhVien
        WHERE SDT LIKE ? OR TenKhachHang LIKE ?
    """;

    @Override
    public List<TheThanhVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<TheThanhVien> selectByKeyword(String keyword) {
        String key = "%" + keyword + "%";
        return selectBySql(SELECT_BY_KEYWORD_SQL, key, key);
    }

    private List<TheThanhVien> selectBySql(String sql, Object... args) {
        List<TheThanhVien> list = new ArrayList<>();

        try {
            ResultSet rs = XJdbc.query(sql, args);

            while (rs.next()) {
                TheThanhVien tv = new TheThanhVien();

                tv.setMaThe(rs.getString("MaThe"));
                tv.setMaKhachHang(rs.getString("MaKhachHang"));
                tv.setSdt(rs.getString("SDT"));
                tv.setTenKhachHang(rs.getString("TenKhachHang"));
                tv.setDiemTichLuy(rs.getInt("DiemTichLuy"));

                list.add(tv);
            }

            rs.getStatement().getConnection().close();

        } catch (Exception e) {
            throw new RuntimeException("Lỗi DAO TheThanhVien", e);
        }

        return list;
    }
}
