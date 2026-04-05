package poly.book.dao;

import java.util.List;
import entity.hoadonchitiet;

public interface hoadonchitietDAO extends CrudDAO<hoadonchitiet, Long>{
    public List<hoadonchitiet> findByBillId(Long billId);
    public List<hoadonchitiet> findByDrinkId(String drinkId);
}
