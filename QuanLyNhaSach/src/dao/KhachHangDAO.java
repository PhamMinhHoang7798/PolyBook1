package dao;
// Lớp DAO (Data Access Object) chịu trách nhiệm kết nối và thực hiện các thao tác CRUD với cơ sở dữ liệu.
import entity.KhachHang;
import java.util.List;

public interface KhachHangDAO {

    void insert(KhachHang kh);

    void update(KhachHang kh);

    void delete(String maKhachHang);

    KhachHang selectById(String maKhachHang);

    KhachHang selectByPhone(String sdt);

    List<KhachHang> selectAll();

    List<KhachHang> selectByKeyword(String keyword);// Tìm kiếm khách hàng theo từ khóa (tên, SĐT,...)
}
