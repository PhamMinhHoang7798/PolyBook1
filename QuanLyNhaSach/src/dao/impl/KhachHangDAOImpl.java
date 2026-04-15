//Name : Phạm Minh Hoàng 
//Mssv : PS47440
//Date : 
//Slide:
package dao.impl;

import dao.KhachHangDAO;
import entity.KhachHang;
import java.util.List;
import util.XJdbc;
import util.XQuery;

public class KhachHangDAOImpl implements KhachHangDAO {

    @Override
    public void insert(KhachHang kh) {
        String sql = "INSERT INTO KhachHang (MaKhachHang, TenKhachHang, SoDienThoai, LoaiThe, DiemTichLuy) VALUES (?, ?, ?, ?, ?)";
        util.XJdbc.executeUpdate(sql, kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getSoDienThoai(), kh.getLoaiThe(), kh.getDiemTichLuy());
    }

    @Override
    public void update(KhachHang kh) {
        String sql = "UPDATE KhachHang SET TenKhachHang=?, SoDienThoai=?, LoaiThe=?, DiemTichLuy=? WHERE MaKhachHang=?";
        util.XJdbc.executeUpdate(sql, kh.getTenKhachHang(), kh.getSoDienThoai(), kh.getLoaiThe(), kh.getDiemTichLuy(), kh.getMaKhachHang());
    }

    @Override
    public void delete(String maKhachHang) { // Tham số là String
        String sql = "DELETE FROM KhachHang WHERE MaKhachHang=?";
        util.XJdbc.executeUpdate(sql, maKhachHang);
    }

    @Override
    public KhachHang selectById(String maKhachHang) { // Đổi 'int' thành 'String'
        String sql = "SELECT * FROM KhachHang WHERE MaKhachHang=?";
        return util.XQuery.getSingleBean(KhachHang.class, sql, maKhachHang);
    }

    @Override
    public KhachHang selectByPhone(String sdt) {
        String sql = "SELECT * FROM KhachHang WHERE SoDienThoai=?";
        return XQuery.getSingleBean(KhachHang.class, sql, sdt);
    }

    @Override
    public List<KhachHang> selectAll() {
        String sql = "SELECT * FROM KhachHang";
        return XQuery.getBeanList(KhachHang.class, sql);
    }

    @Override
    public List<KhachHang> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KhachHang WHERE TenKhachHang LIKE ? OR SoDienThoai LIKE ?";
        return XQuery.getBeanList(KhachHang.class, sql, "%" + keyword + "%", "%" + keyword + "%");
    }
}
