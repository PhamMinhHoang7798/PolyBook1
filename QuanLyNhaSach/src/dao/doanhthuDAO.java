package dao;

import dao.*;
import java.util.Date;
import java.util.List;
import entity.doanhthu;

public interface doanhthuDAO{
    List<doanhthu.ByCategory> getByCategory(Date begin, Date end);
    List<doanhthu.ByUser> getByUser(Date begin, Date end);
}
