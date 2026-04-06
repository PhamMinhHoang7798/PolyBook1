package dao.impl;

import dao.CrudDAO;
import entity.hoadonchitiet;
import util.XJdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class hoadonchitietDAOImpl implements CrudDAO<hoadonchitiet, Integer> {

    String INSERT_SQL = "INSERT INTO HoaDonChiTiet (MaHoaDon, MaKhachHang, NgayLap, PhuongThucThanhToan, TrangThai, KhuyenMai, TongTien, MaVoucher, TenDangNhap) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    String UPDATE_SQL = "UPDATE HoaDonChiTiet SET MaKhachHang=?, NgayLap=?, PhuongThucThanhToan=?, TrangThai=?, KhuyenMai=?, TongTien=?, MaVoucher=?, TenDangNhap=? WHERE MaHoaDon=?";
    
    String DELETE_SQL = "DELETE FROM HoaDonChiTiet WHERE MaHoaDon=?";
    
    String SELECT_ALL = "SELECT * FROM HoaDonChiTiet";
    
    String SELECT_BY_ID = "SELECT * FROM HoaDonChiTiet WHERE MaHoaDon=?";

    public void insert(hoadonchitiet entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaHoaDon(),
                entity.getMaKhachHang(),
                entity.getNgayLap(),
                entity.getPhuongThucThanhToan(),
                entity.getTrangThai(),
                entity.getKhuyenMai(),
                entity.getTongTien(),
                entity.getMaVoucher(),
                entity.getTenDangNhap()
        );
    }

    @Override
    public void update(hoadonchitiet entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMaKhachHang(),
                entity.getNgayLap(),
                entity.getPhuongThucThanhToan(),
                entity.getTrangThai(),
                entity.getKhuyenMai(),
                entity.getTongTien(),
                entity.getMaVoucher(),
                entity.getTenDangNhap(),
                entity.getMaHoaDon()
        );
    }

    public void delete(Integer maHoaDon) {
        XJdbc.executeUpdate(DELETE_SQL, maHoaDon);
    }

    public List<hoadonchitiet> selectAll() {
        return selectBySql(SELECT_ALL);
    }

    public hoadonchitiet selectById(Integer maHoaDon) {
        List<hoadonchitiet> list = selectBySql(SELECT_BY_ID, maHoaDon);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<hoadonchitiet> selectBySql(String sql, Object... args) {
        List<hoadonchitiet> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                hoadonchitiet hd = new hoadonchitiet();
                hd.setMaHoaDon(rs.getInt("MaHoaDon"));
                hd.setMaKhachHang(rs.getString("MaKhachHang"));
                hd.setNgayLap(rs.getDate("NgayLap"));
                hd.setPhuongThucThanhToan(rs.getString("PhuongThucThanhToan"));
                hd.setTrangThai(rs.getString("TrangThai"));
                hd.setKhuyenMai(rs.getDouble("KhuyenMai"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setMaVoucher(rs.getString("MaVoucher"));
                hd.setTenDangNhap(rs.getString("TenDangNhap"));
                list.add(hd);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public hoadonchitiet create(hoadonchitiet entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<hoadonchitiet> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public hoadonchitiet findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
