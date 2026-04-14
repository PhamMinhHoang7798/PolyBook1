package dao;

import entity.SanPham;
import java.util.List;

public interface SanPhamDAO {

    void insert(SanPham sp);

    void update(SanPham sp);

    void delete(String maSanPham);

    SanPham selectById(String maSanPham);

    List<SanPham> selectAll();

    List<SanPham> selectByKeyword(String keyword);
}