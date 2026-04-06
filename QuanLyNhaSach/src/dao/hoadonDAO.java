package dao;

import dao.impl.hoadonDAOImpl;
import java.util.Date;
import java.util.List;
import entity.hoadon;

public interface hoadonDAO extends CrudDAO<hoadon, Long>{
    public List<hoadon> findByUsername(String username);
    public List<hoadon> findByCardId(Integer cardId);
    public hoadon findServicingByCardId(Integer cardId);
    public List<hoadon> findByTimeRange(Date from, Date to);
    public List<hoadon> findByUserAndTimeRange(String username, Date from, Date to);
}
