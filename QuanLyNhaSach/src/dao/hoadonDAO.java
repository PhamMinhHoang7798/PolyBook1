package dao;

import entity.hoadon;
import java.util.List;

public interface hoadonDAO {
    hoadon create(hoadon entity);
    void update(hoadon entity);
    void deleteById(String id);
    List<hoadon> findAll();
    hoadon findById(String id);
}
