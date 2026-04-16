package dao;

import entity.Bill;
import java.util.List;

public interface BillDAO {

    List<Bill> getAll();

    List<Bill> search(String keyword);

    List<Bill> thongKeDoanhThu();

    List<Bill> filter(String from, String to);
}
