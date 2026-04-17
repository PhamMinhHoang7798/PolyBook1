package dao;

import entity.KhachHang;
import java.util.List;

public interface KhachHangDAO {

    void insert(KhachHang kh);

    void update(KhachHang kh);

    void delete(String maKhachHang);

    KhachHang selectById(String maKhachHang);

    KhachHang selectByPhone(String sdt);

    List<KhachHang> selectAll();

    List<KhachHang> selectByKeyword(String keyword);
}
