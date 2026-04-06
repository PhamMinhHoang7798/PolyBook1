package dao.impl;

import entity.hoadonchitiet;
import dao.hoadonchitietDAO;
import util.XJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class hoadonchitietDAOImpl implements hoadonchitietDAO {

    // --- SQL CƠ BẢN ---
    String INSERT_SQL = "INSERT INTO Hoadonchitiet (BillId, DrinkId, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE Hoadonchitiet SET BillId = ?, DrinkId = ?, SoLuong = ?, DonGia = ? WHERE Id = ?";
    String DELETE_SQL = "DELETE FROM Hoadonchitiet WHERE Id = ?";
    String SELECT_ALL_SQL = "SELECT * FROM Hoadonchitiet";
    String SELECT_BY_ID_SQL = "SELECT * FROM Hoadonchitiet WHERE Id = ?";
    
    // --- SQL MỞ RỘNG ---
    String SELECT_BY_BILL_ID = "SELECT * FROM Hoadonchitiet WHERE BillId = ?";
    String SELECT_BY_DRINK_ID = "SELECT * FROM Hoadonchitiet WHERE DrinkId = ?";

    // ================= IMPLEMENT CrudDAO =================
    @Override
    public hoadonchitiet create(hoadonchitiet entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getBillId(), entity.getDrinkId(), entity.getSoLuong(), entity.getDonGia());
        return entity;
    }

    @Override
    public void update(hoadonchitiet entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getBillId(), entity.getDrinkId(), entity.getSoLuong(), entity.getDonGia(), entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<hoadonchitiet> findAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public hoadonchitiet findById(Long id) {
        List<hoadonchitiet> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    // ================= IMPLEMENT hoadonchitietDAO =================
    @Override
    public List<hoadonchitiet> findByBillId(Long billId) {
        return this.selectBySql(SELECT_BY_BILL_ID, billId);
    }

    @Override
    public List<hoadonchitiet> findByDrinkId(String drinkId) {
        return this.selectBySql(SELECT_BY_DRINK_ID, drinkId);
    }

    // --- HÀM PHỤ TRỢ ---
    private List<hoadonchitiet> selectBySql(String sql, Object... args) {
        List<hoadonchitiet> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                hoadonchitiet hdct = new hoadonchitiet();
                hdct.setId(rs.getLong("Id"));
                hdct.setBillId(rs.getLong("BillId"));
                hdct.setDrinkId(rs.getString("DrinkId"));
                hdct.setSoLuong(rs.getInt("SoLuong"));
                hdct.setDonGia(rs.getDouble("DonGia"));
                list.add(hdct);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}