package dao.impl;

import dao.hoadonDAO;
import entity.hoadon;
import util.XJdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class hoadonDAOImpl implements hoadonDAO {

    String INSERT = "INSERT INTO HoaDon VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE = "UPDATE HoaDon SET MaKhachHang=?, NgayLap=?, PhuongThucThanhToan=?, TrangThai=?, KhuyenMai=?, TongTien=?, MaVoucher=?, TenDangNhap=? WHERE MaHoaDon=?";
    String DELETE = "DELETE FROM HoaDon WHERE MaHoaDon=?";
    String SELECT_ALL = "SELECT * FROM HoaDon";
    String SELECT_ID = "SELECT * FROM HoaDon WHERE MaHoaDon=?";

    public hoadon create(hoadon hd) {
        XJdbc.executeUpdate(INSERT,
                hd.getMaHoaDon(),
                hd.getMaKhachHang(),
                hd.getNgayLap(),
                hd.getPhuongThucThanhToan(),
                hd.getTrangThai(),
                hd.getKhuyenMai(),
                hd.getTongTien(),
                hd.getMaVoucher(),
                hd.getTenDangNhap()
        );
        return hd;
    }

    public void update(hoadon hd) {
        XJdbc.executeUpdate(UPDATE,
                hd.getMaKhachHang(),
                hd.getNgayLap(),
                hd.getPhuongThucThanhToan(),
                hd.getTrangThai(),
                hd.getKhuyenMai(),
                hd.getTongTien(),
                hd.getMaVoucher(),
                hd.getTenDangNhap(),
                hd.getMaHoaDon()
        );
    }

    public void deleteById(String id) {
        XJdbc.executeUpdate(DELETE, id);
    }

    public List<hoadon> findAll() { return selectBySql(SELECT_ALL); }

    public hoadon findById(String id) {
        List<hoadon> list = selectBySql(SELECT_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<hoadon> selectBySql(String sql, Object... args) {
        List<hoadon> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                hoadon hd = new hoadon();
                hd.setMaHoaDon(rs.getString(1));
                hd.setMaKhachHang(rs.getString(2));
                hd.setNgayLap(rs.getDate(3));
                hd.setPhuongThucThanhToan(rs.getString(4));
                hd.setTrangThai(rs.getString(5));
                hd.setKhuyenMai(rs.getDouble(6));
                hd.setTongTien(rs.getDouble(7));
                hd.setMaVoucher(rs.getString(8));
                hd.setTenDangNhap(rs.getString(9));
                list.add(hd);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
