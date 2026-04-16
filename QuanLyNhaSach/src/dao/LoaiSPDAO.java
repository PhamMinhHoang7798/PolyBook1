package dao;

import dao.impl.LoaiSPDAOImpl;
import entity.LoaiSP;
import java.util.List;

public interface LoaiSPDAO {
    void insert(LoaiSP entity);
    void update(LoaiSP entity);
    void delete(String maLoai);
    List<LoaiSP> selectAll();
    LoaiSP findById(String maLoai);
    List<LoaiSP> selectByKeyword(String keyword);
}