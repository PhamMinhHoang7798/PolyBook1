package dao.impl;

import dao.hoadonchitietDAO;
import entity.hoadonchitiet;
import util.XJdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class hoadonchitietDAOImpl implements hoadonchitietDAO {

    private final String INSERT_SQL =
            "INSERT INTO HoaDonChiTiet (MaCT, MaHoaDon, MaSanPham, SoLuong, Gia) VALUES (?,?,?,?,?)";

    private final String UPDATE_SQL =
            "UPDATE HoaDonChiTiet SET MaHoaDon=?, MaSanPham=?, SoLuong=?, Gia=? WHERE MaCT=?";

    private final String DELETE_SQL =
            "DELETE FROM HoaDonChiTiet WHERE MaCT=?";

    private final String SELECT_ALL_SQL =
            "SELECT * FROM HoaDonChiTiet";

    private final String SELECT_BY_ID_SQL =
            "SELECT * FROM HoaDonChiTiet WHERE MaCT=?";

    private final String SELECT_BY_HOADON_SQL =
            "SELECT * FROM HoaDonChiTiet WHERE MaHoaDon=?";

    @Override
    public hoadonchitiet create(hoadonchitiet entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaCT(),
                entity.getMaHoaDon(),
                entity.getMaSanPham(),
                entity.getSoLuong(),
                entity.getGia()
        );
        return entity;
    }

    @Override
    public void update(hoadonchitiet entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMaHoaDon(),
                entity.getMaSanPham(),
                entity.getSoLuong(),
                entity.getGia(),
                entity.getMaCT()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<hoadonchitiet> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public hoadonchitiet findById(Integer id) {
        List<hoadonchitiet> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<hoadonchitiet> findByHoaDon(String maHoaDon) {
        return selectBySql(SELECT_BY_HOADON_SQL, maHoaDon);
    }

    private List<hoadonchitiet> selectBySql(String sql, Object... args) {
        List<hoadonchitiet> list = new ArrayList<>();

        try {
            ResultSet rs = XJdbc.query(sql, args);

            while (rs.next()) {
                hoadonchitiet ct = new hoadonchitiet(
                        rs.getInt("MaCT"),
                        rs.getString("MaHoaDon"),
                        rs.getString("MaSanPham"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("Gia")
                );
                list.add(ct);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
