package dao.impl;

import dao.hoadonDAO;
import entity.hoadon;
import util.XJdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class hoadonDAOImpl implements hoadonDAO {

    String INSERT_SQL = "INSERT INTO HoaDon VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE HoaDon SET MaKhachHang=?, NgayLap=?, PhuongThucThanhToan=?, TrangThai=?, KhuyenMai=?, TongTien=?, MaVoucher=?, TenDangNhap=? WHERE MaHoaDon=?";
    String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHoaDon=?";
    String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE MaHoaDon=?";

    @Override
    public hoadon create(hoadon entity) {
        XJdbc.update(INSERT_SQL,
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
        return entity;
    }

    @Override
    public void update(hoadon entity) {
        XJdbc.update(UPDATE_SQL,
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

    @Override
    public void deleteById(String id) {
        XJdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<hoadon> findAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public hoadon findById(String id) {
        List<hoadon> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<hoadon> selectBySql(String sql, Object... args) {
        List<hoadon> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                hoadon hd = new hoadon(
                        rs.getString("MaHoaDon"),
                        rs.getString("MaKhachHang"),
                        rs.getDate("NgayLap"),
                        rs.getString("PhuongThucThanhToan"),
                        rs.getString("TrangThai"),
                        rs.getDouble("KhuyenMai"),
                        rs.getDouble("TongTien"),
                        rs.getString("MaVoucher"),
                        rs.getString("TenDangNhap")
                );
                list.add(hd);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}