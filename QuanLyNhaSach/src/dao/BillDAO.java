package dao;
// Lớp DAO (Data Access Object) chịu trách nhiệm kết nối và thực hiện các thao tác CRUD với cơ sở dữ liệu.
import entity.Bill;
import java.util.List;

public interface BillDAO {

    List<Bill> getAll();

    List<Bill> search(String keyword);

    List<Bill> thongKeDoanhThu();

    List<Bill> filter(String from, String to);// lọc theo ngày
}
