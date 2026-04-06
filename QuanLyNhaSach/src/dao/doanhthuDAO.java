package dao;

import entity.doanhthu;
import java.util.Date;
import java.util.List;

public interface doanhthuDAO {

    List<doanhthu.TheoVoucher> thongKeTheoVoucher(Date tuNgay, Date denNgay);

    List<doanhthu.TheoNguoiDung> thongKeTheoNguoiDung(Date tuNgay, Date denNgay);
}
