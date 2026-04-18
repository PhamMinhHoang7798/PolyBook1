package dao;
// Lớp DAO (Data Access Object) chịu trách nhiệm kết nối và thực hiện các thao tác CRUD với cơ sở dữ liệu.

import entity.TheThanhVien;
import java.util.List;

public interface TheThanhVienDAO {

    List<TheThanhVien> selectAll();

    List<TheThanhVien> selectByKeyword(String keyword);
}
