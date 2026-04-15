package dao.impl;

import dao.BillDAO;
import entity.Bill;
import java.util.List;
import util.XQuery;

public class BillDAOImpl implements BillDAO {

    @Override
    public List<Bill> getAll() {
        String sql = """
            SELECT 
                hd.MaHoaDon AS MaHoaDon,
                kh.TenKhachHang AS TenKhachHang,
                sp.TenSanPham AS TenSanPham,
                hdct.SoLuong AS SoLuong,
                hd.KhuyenMai AS KhuyenMai,
                hd.TongTien AS TongTien,
                hd.NgayLap AS NgayLap
            FROM HoaDon hd
            JOIN HoaDonChiTiet hdct ON hd.MaHoaDon = hdct.MaHoaDon
            JOIN KhachHang kh ON hd.MaKhachHang = kh.MaKhachHang
            JOIN SanPham sp ON hdct.MaSanPham = sp.MaSanPham
            ORDER BY hd.NgayLap DESC
        """;
        return XQuery.getBeanList(Bill.class, sql); 
    }

    @Override
    public List<Bill> search(String keyword) {
        String sql = """
            SELECT 
                hd.MaHoaDon AS MaHoaDon,
                kh.TenKhachHang AS TenKhachHang,
                sp.TenSanPham AS TenSanPham,
                hdct.SoLuong AS SoLuong,
                hd.KhuyenMai AS KhuyenMai,
                hd.TongTien AS TongTien,
                hd.NgayLap AS NgayLap
            FROM HoaDon hd
            JOIN HoaDonChiTiet hdct ON hd.MaHoaDon = hdct.MaHoaDon
            JOIN KhachHang kh ON hd.MaKhachHang = kh.MaKhachHang
            JOIN SanPham sp ON hdct.MaSanPham = sp.MaSanPham
            WHERE hd.MaHoaDon LIKE ? OR kh.TenKhachHang LIKE ?
        """;
        return XQuery.getBeanList(Bill.class, sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    @Override
    public List<Bill> thongKeDoanhThu() {
        String sql = """
            SELECT 
                NgayLap AS NgayLap,
                SUM(TongTien) AS DoanhThu
            FROM HoaDon
            GROUP BY NgayLap
            ORDER BY NgayLap DESC
        """;
        return XQuery.getBeanList(Bill.class, sql);
    }

    @Override
    public List<Bill> filter(String from, String to) {
        String sql = """
            SELECT 
                hd.MaHoaDon AS MaHoaDon,
                kh.TenKhachHang AS TenKhachHang,
                sp.TenSanPham AS TenSanPham,
                hdct.SoLuong AS SoLuong,
                hd.KhuyenMai AS KhuyenMai,
                hd.TongTien AS TongTien,
                hd.NgayLap AS NgayLap
            FROM HoaDon hd
            JOIN HoaDonChiTiet hdct ON hd.MaHoaDon = hdct.MaHoaDon
            JOIN KhachHang kh ON hd.MaKhachHang = kh.MaKhachHang
            JOIN SanPham sp ON hdct.MaSanPham = sp.MaSanPham
            WHERE hd.NgayLap BETWEEN ? AND ?
        """;
        return XQuery.getBeanList(Bill.class, sql, from, to);
    }
}