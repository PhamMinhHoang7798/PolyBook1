package dao;

import entity.hoadonchitiet;
import java.util.List;

public interface hoadonchitietDAO extends CrudDAO<hoadonchitiet, Integer> {
    List<hoadonchitiet> findByHoaDon(String maHoaDon);
}