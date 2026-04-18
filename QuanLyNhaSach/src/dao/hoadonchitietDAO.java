package dao;
// Lớp DAO (Data Access Object) chịu trách nhiệm kết nối và thực hiện các thao tác CRUD với cơ sở dữ liệu.

import entity.hoadonchitiet;
import java.util.List;

public interface hoadonchitietDAO {
    hoadonchitiet create(hoadonchitiet entity);
    void update(hoadonchitiet entity);
    void deleteById(int id);
    List<hoadonchitiet> findAll();
    hoadonchitiet findById(int id);
    List<hoadonchitiet> findByHoaDon(String maHD);
}
