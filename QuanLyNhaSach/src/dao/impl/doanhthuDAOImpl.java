package dao.impl;

import entity.doanhthu;
import dao.doanhthuDAO;
import util.XJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class doanhthuDAOImpl implements doanhthuDAO {

    // Thay đổi câu query tính tổng doanh thu theo Category / User sao cho khớp với DB của bạn
    String SQL_REVENUE_BY_CATEGORY = "{CALL sp_DoanhThu_ByCategory(?, ?)}"; // Hoặc dùng SELECT ... GROUP BY
    String SQL_REVENUE_BY_USER = "{CALL sp_DoanhThu_ByUser(?, ?)}"; 

    @Override
    public List<doanhthu.ByCategory> getByCategory(Date begin, Date end) {
        List<doanhthu.ByCategory> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(SQL_REVENUE_BY_CATEGORY, begin, end);
            while (rs.next()) {
                doanhthu.ByCategory tk = new doanhthu.ByCategory();
                // Map theo cấu trúc của class ByCategory trong entity.doanhthu
                tk.setCategoryName(rs.getString("CategoryName"));
                tk.setTotalRevenue(rs.getDouble("TotalRevenue"));
                tk.setTotalQuantity(rs.getInt("TotalQuantity"));
                list.add(tk);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<doanhthu.ByUser> getByUser(Date begin, Date end) {
        List<doanhthu.ByUser> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(SQL_REVENUE_BY_USER, begin, end);
            while (rs.next()) {
                doanhthu.ByUser tk = new doanhthu.ByUser();
                // Map theo cấu trúc của class ByUser trong entity.doanhthu
                tk.setUsername(rs.getString("Username"));
                tk.setTotalRevenue(rs.getDouble("TotalRevenue"));
                tk.setBillCount(rs.getInt("BillCount"));
                list.add(tk);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}