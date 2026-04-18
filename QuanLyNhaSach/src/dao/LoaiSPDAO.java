package dao;
// Lớp DAO (Data Access Object) chịu trách nhiệm kết nối và thực hiện các thao tác CRUD với cơ sở dữ liệu.
import entity.LoaiSP;
import java.util.List;

public interface LoaiSPDAO {
    void insert(LoaiSP entity);
    void update(LoaiSP entity);
    void delete(String maLoai);
    List<LoaiSP> selectAll();
    LoaiSP findById(String maLoai);// Tìm loại sản phẩm theo mã (trả về 1 đối tượng hoặc null)
    List<LoaiSP> selectByKeyword(String keyword);
}