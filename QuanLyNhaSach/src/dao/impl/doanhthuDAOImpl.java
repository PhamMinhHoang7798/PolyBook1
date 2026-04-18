package dao.impl;

import dao.doanhthuDAO;
import entity.doanhthu;
import util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class doanhthuDAOImpl implements doanhthuDAO {

    // ===== SQL =====
    private static final String SQL_THEO_VOUCHER = """
        SELECT 
            MaVoucher,
            SUM(TongTien) AS TongDoanhThu,
            COUNT(*) AS SoLuongHoaDon
        FROM HoaDon
        WHERE NgayLap BETWEEN ? AND ?
        GROUP BY MaVoucher
    """;
    private static final String SQL_THEO_NGUOIDUNG = """
        SELECT 
            TenDangNhap,
            SUM(TongTien) AS TongDoanhThu,
            COUNT(*) AS SoHoaDon
        FROM HoaDon
        WHERE NgayLap BETWEEN ? AND ?
        GROUP BY TenDangNhap
    """;

    // ===== THỐNG KÊ THEO VOUCHER =====
    @Override
    public List<doanhthu.TheoVoucher> thongKeTheoVoucher(Date tuNgay, Date denNgay) {
        List<doanhthu.TheoVoucher> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = XJdbc.executeQuery(SQL_THEO_VOUCHER, tuNgay, denNgay);
            while (rs.next()) {
                doanhthu.TheoVoucher tk = new doanhthu.TheoVoucher();
                tk.setMaVoucher(rs.getString("MaVoucher"));
                tk.setTongDoanhThu(rs.getDouble("TongDoanhThu"));
                tk.setSoLuongHoaDon(rs.getInt("SoLuongHoaDon"));
                list.add(tk);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thống kê theo voucher", e);
        } finally {
            dongKetNoi(rs);
        }
        return list;
    }

    // ===== THỐNG KÊ THEO NGƯỜI DÙNG =====
    @Override
    public List<doanhthu.TheoNguoiDung> thongKeTheoNguoiDung(Date tuNgay, Date denNgay) {
        List<doanhthu.TheoNguoiDung> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = XJdbc.executeQuery(SQL_THEO_NGUOIDUNG, tuNgay, denNgay);
            while (rs.next()) {
                doanhthu.TheoNguoiDung tk = new doanhthu.TheoNguoiDung();
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setTongDoanhThu(rs.getDouble("TongDoanhThu"));
                tk.setSoHoaDon(rs.getInt("SoHoaDon"));
                list.add(tk);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thống kê theo người dùng", e);
        } finally {
            dongKetNoi(rs);
        }
        return list;
    }

    // ===== ĐÓNG KẾT NỐI =====
    private void dongKetNoi(ResultSet rs) {
        try {
            if (rs != null) {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ===== THỐNG KÊ DOANH THU THEO NGÀY =====
    @Override
    public List<Object[]> thongKeDoanhThuTheoNgay(Date tuNgay, Date denNgay) {
        List<Object[]> list = new ArrayList<>();
        // Query group theo ngày, lấy ra số đơn hàng, tổng SP, tổng doanh thu
        String sql = """
            SELECT 
                CAST(hd.NgayLap AS DATE) AS Ngay,
                COUNT(hd.MaHoaDon) AS SoDonHang,
                ISNULL(SUM(ct.TongSanPham), 0) AS TongSanPham,
                SUM(hd.TongTien) AS TongDoanhThu
            FROM HoaDon hd
            LEFT JOIN (
                SELECT MaHoaDon, SUM(SoLuong) AS TongSanPham 
                FROM HoaDonChiTiet 
                GROUP BY MaHoaDon
            ) ct ON hd.MaHoaDon = ct.MaHoaDon
            WHERE CAST(hd.NgayLap AS DATE) BETWEEN ? AND ?
            GROUP BY CAST(hd.NgayLap AS DATE)
            ORDER BY Ngay DESC
        """;
        ResultSet rs = null;
        try {
            rs = XJdbc.executeQuery(sql, tuNgay, denNgay);
            while (rs.next()) {
                Object[] row = {
                    rs.getDate("Ngay"),
                    rs.getInt("SoDonHang"),
                    rs.getInt("TongSanPham"),
                    rs.getDouble("TongDoanhThu")
                };
                list.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thống kê doanh thu theo ngày", e);
        } finally {
            dongKetNoi(rs);
        }
        return list;
    }
}
