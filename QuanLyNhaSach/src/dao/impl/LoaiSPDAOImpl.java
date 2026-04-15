package dao.impl;

import entity.LoaiSP;
import dao.LoaiSPDAO;
import util.XJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiSPDAOImpl implements LoaiSPDAO {

    // Cần thay đổi tên bảng [LoaiSP] và các cột cho khớp SQL của bạn
    String INSERT_SQL = "INSERT INTO LoaiSanPham (MaLoai, TenLoai) VALUES (?, ?)";
    String UPDATE_SQL = "UPDATE LoaiSanPham SET TenLoai = ? WHERE MaLoai = ?";
    String DELETE_SQL = "DELETE FROM LoaiSanPham WHERE MaLoai = ?";
    String SELECT_ALL_SQL = "SELECT * FROM LoaiSanPham";
    String SELECT_BY_ID_SQL = "SELECT * FROM LoaiSanPham WHERE MaLoai = ?";

    @Override
    public void insert(LoaiSP entity) {
        // Cần truyền cả MaLoai và TenLoai vì ông nhập tay
        String sql = "INSERT INTO LoaiSanPham (MaLoai, TenLoai) VALUES (?, ?)";
        XJdbc.executeUpdate(sql, entity.getMaLoai(), entity.getTenLoai());
    }

    @Override
    public void update(LoaiSP entity) {
        // Bỏ dấu phẩy thừa trước chữ WHERE
        String sql = "UPDATE LoaiSanPham SET TenLoai = ? WHERE MaLoai = ?";
        XJdbc.executeUpdate(sql, entity.getTenLoai(), entity.getMaLoai());
    }

    @Override
    public void delete(String maLoai) {
        XJdbc.executeUpdate(DELETE_SQL, maLoai);
    }

    @Override
    public List<LoaiSP> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public LoaiSP findById(String maLoai) {
        List<LoaiSP> list = this.selectBySql(SELECT_BY_ID_SQL, maLoai);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    // Hàm phụ trợ dùng chung để truy vấn danh sách LoaiSP
    private List<LoaiSP> selectBySql(String sql, Object... args) {
        List<LoaiSP> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                LoaiSP loaiSP = new LoaiSP();
                // Map dữ liệu từ ResultSet vào Entity
                loaiSP.setMaLoai(rs.getString("MaLoai"));
                loaiSP.setTenLoai(rs.getString("TenLoai"));
                list.add(loaiSP);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LoaiSP> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM LoaiSanPham WHERE MaLoai LIKE ? OR TenLoai LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }
}
