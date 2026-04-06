package dao.impl;

import dao.CrudDAO;
import entity.hoadon;
import util.XJdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class hoadonDAOImpl implements CrudDAO<hoadon, Integer> {

    String INSERT_SQL = "INSERT INTO HoaDon (MaHoaDon, MaSanPham, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE HoaDon SET MaSanPham=?, SoLuong=?, DonGia=? WHERE MaHoaDon=?";
    String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHoaDon=?";
    String SELECT_ALL = "SELECT * FROM HoaDon";
    String SELECT_BY_ID = "SELECT * FROM HoaDon WHERE MaHoaDon=?";

    public void insert(hoadon entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaHoaDon(),
                entity.getMaSanPham(),
                entity.getSoLuong(),
                entity.getDonGia()
        );
    }

    @Override
    public void update(hoadon entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMaSanPham(),
                entity.getSoLuong(),
                entity.getDonGia(),
                entity.getMaHoaDon()
        );
    }

    public void delete(Integer maHoaDon) {
        XJdbc.executeUpdate(DELETE_SQL, maHoaDon);
    }

    public List<hoadon> selectAll() {
        return selectBySql(SELECT_ALL);
    }

    public hoadon selectById(Integer maHoaDon) {
        List<hoadon> list = selectBySql(SELECT_BY_ID, maHoaDon);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<hoadon> selectBySql(String sql, Object... args) {
        List<hoadon> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                hoadon hd = new hoadon();
                hd.setMaHoaDon(rs.getInt("MaHoaDon"));
                hd.setMaSanPham(rs.getString("MaSanPham"));
                hd.setSoLuong(rs.getInt("SoLuong"));
                hd.setDonGia(rs.getDouble("DonGia"));
                list.add(hd);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public hoadon create(hoadon entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<hoadon> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public hoadon findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
