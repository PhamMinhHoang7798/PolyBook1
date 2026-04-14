package dao;

import entity.TheThanhVien;
import java.util.List;

public interface TheThanhVienDAO {

    List<TheThanhVien> selectAll();

    List<TheThanhVien> selectByKeyword(String keyword);
}
