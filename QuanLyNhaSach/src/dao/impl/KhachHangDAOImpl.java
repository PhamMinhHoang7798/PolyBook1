package dao.impl;

import dao.KhachHangDAO;
import entity.KhachHang;
import java.util.List;
import util.XJdbc;
import util.XQuery;

public class KhachHangDAOImpl implements KhachHangDAO {

    @Override
    public void insert(KhachHang kh) {
        String sql = """
            INSERT INTO KhachHang 
            (MaKhachHang, TenKhachHang, SoDienThoai, LoaiThe, DiemTichLuy) 
            VALUES (?, ?, ?, ?, ?)
        """;

        XJdbc.executeUpdate(sql,
                kh.getMaKhachHang(),
                kh.getTenKhachHang(),
                kh.getSoDienThoai(),
                kh.getLoaiThe(),
                kh.getDiemTichLuy()
        );
    }

    @Override
    public void update(KhachHang kh) {
        String sql = """
            UPDATE KhachHang 
            SET TenKhachHang=?, SoDienThoai=?, LoaiThe=?, DiemTichLuy=? 
            WHERE MaKhachHang=?
        """;

        XJdbc.executeUpdate(sql,
                kh.getTenKhachHang(),
                kh.getSoDienThoai(),
                kh.getLoaiThe(),
                kh.getDiemTichLuy(),
                kh.getMaKhachHang()
        );
    }

    @Override
    public void delete(String maKhachHang) {
        String sql = "DELETE FROM KhachHang WHERE MaKhachHang=?";
        XJdbc.executeUpdate(sql, maKhachHang);
    }

    @Override
    public KhachHang selectById(String maKhachHang) {
        String sql = "SELECT * FROM KhachHang WHERE MaKhachHang=?";
        return XQuery.getSingleBean(KhachHang.class, sql, maKhachHang);
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

    // 🔥 FIX QUAN TRỌNG: tìm kiếm KHÔNG DẤU + realtime
    @Override
    public List<KhachHang> selectByKeyword(String keyword) {

        String sql = """
            SELECT * FROM KhachHang
            WHERE 
                TenKhachHang COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?
                OR SoDienThoai LIKE ?
        """;

        String key = "%" + keyword + "%";

        return XQuery.getBeanList(
                KhachHang.class,
                sql,
                key,
                key
        );
    }
}
