package dao;

import entity.hoadon;
import java.util.List;

public interface hoadonDAO {

    void insert(hoadon hd);

    void update(hoadon hd);

    void delete(long maHoaDon);

    hoadon selectById(long maHoaDon);

    List<hoadon> selectAll();
}
