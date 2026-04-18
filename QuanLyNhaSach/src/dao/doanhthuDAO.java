package dao;
// Lớp DAO (Data Access Object) chịu trách nhiệm kết nối và thực hiện các thao tác CRUD với cơ sở dữ liệu.

import entity.doanhthu;
import java.util.Date;
import java.util.List;

public interface doanhthuDAO {

    List<doanhthu.TheoVoucher> thongKeTheoVoucher(Date tuNgay, Date denNgay);

    List<doanhthu.TheoNguoiDung> thongKeTheoNguoiDung(Date tuNgay, Date denNgay);

    List<Object[]> thongKeDoanhThuTheoNgay(Date tuNgay, Date denNgay);
}
