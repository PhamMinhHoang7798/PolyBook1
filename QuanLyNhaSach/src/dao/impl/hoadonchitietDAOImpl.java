package dao.impl;

import dao.hoadonchitietDAO;
import entity.hoadonchitiet;
import util.XJdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class hoadonchitietDAOImpl implements hoadonchitietDAO {

    String INSERT = "INSERT INTO HoaDonChiTiet VALUES (?,?,?,?,?)";
    String UPDATE = "UPDATE HoaDonChiTiet SET MaHoaDon=?, MaSanPham=?, SoLuong=?, Gia=? WHERE MaCT=?";
    String DELETE = "DELETE FROM HoaDonChiTiet WHERE MaCT=?";
    String SELECT_ALL = "SELECT * FROM HoaDonChiTiet";
    String SELECT_ID = "SELECT * FROM HoaDonChiTiet WHERE MaCT=?";
    String SELECT_HD = "SELECT * FROM HoaDonChiTiet WHERE MaHoaDon=?";

    public hoadonchitiet create(hoadonchitiet ct) {
        XJdbc.executeUpdate(INSERT,
                ct.getMaCT(),
                ct.getMaHoaDon(),
                ct.getMaSanPham(),
                ct.getSoLuong(),
                ct.getGia()
        );
        return ct;
    }

    public void update(hoadonchitiet ct) {
        XJdbc.executeUpdate(UPDATE,
                ct.getMaHoaDon(),
                ct.getMaSanPham(),
                ct.getSoLuong(),
                ct.getGia(),
                ct.getMaCT()
        );
    }

    public void deleteById(int id) {
        XJdbc.executeUpdate(DELETE, id);
    }

    public List<hoadonchitiet> findAll() { return selectBySql(SELECT_ALL); }

    public hoadonchitiet findById(int id) {
        List<hoadonchitiet> list = selectBySql(SELECT_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<hoadonchitiet> findByHoaDon(String maHD) {
        return selectBySql(SELECT_HD, maHD);
    }

    private List<hoadonchitiet> selectBySql(String sql, Object... args) {
        List<hoadonchitiet> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                hoadonchitiet ct = new hoadonchitiet();
                ct.setMaCT(rs.getInt(1));
                ct.setMaHoaDon(rs.getString(2));
                ct.setMaSanPham(rs.getString(3));
                ct.setSoLuong(rs.getInt(4));
                ct.setGia(rs.getDouble(5));
                list.add(ct);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
