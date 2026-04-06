package dao.impl;

import entity.hoadon;
import dao.hoadonDAO;
import util.XJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class hoadonDAOImpl implements hoadonDAO {

    // --- CÁC CÂU LỆNH SQL CƠ BẢN ---
    String INSERT_SQL = "INSERT INTO Hoadon (Username, CardId, NgayTao, TongTien, TrangThai) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE Hoadon SET Username = ?, CardId = ?, NgayTao = ?, TongTien = ?, TrangThai = ? WHERE Id = ?";
    String DELETE_SQL = "DELETE FROM Hoadon WHERE Id = ?";
    String SELECT_ALL_SQL = "SELECT * FROM Hoadon";
    String SELECT_BY_ID_SQL = "SELECT * FROM Hoadon WHERE Id = ?";
    
    // --- CÁC CÂU LỆNH SQL MỞ RỘNG ---
    String SELECT_BY_USERNAME = "SELECT * FROM Hoadon WHERE Username = ?";
    String SELECT_BY_CARD_ID = "SELECT * FROM Hoadon WHERE CardId = ?";
    String SELECT_SERVICING_BY_CARD_ID = "SELECT * FROM Hoadon WHERE CardId = ? AND TrangThai = 'Đang phục vụ'"; // Tùy chỉnh trạng thái
    String SELECT_BY_TIME_RANGE = "SELECT * FROM Hoadon WHERE NgayTao BETWEEN ? AND ?";
    String SELECT_BY_USER_AND_TIME = "SELECT * FROM Hoadon WHERE Username = ? AND NgayTao BETWEEN ? AND ?";

    // ================= IMPLEMENT CrudDAO =================
    @Override
    public hoadon create(hoadon entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
            entity.getUsername(), entity.getCardId(), entity.getNgayTao(), 
            entity.getTongTien(), entity.getTrangThai()
        );
        return entity; 
    }

    @Override
    public void update(hoadon entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
            entity.getUsername(), entity.getCardId(), entity.getNgayTao(), 
            entity.getTongTien(), entity.getTrangThai(), entity.getId()
        );
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<hoadon> findAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public hoadon findById(Long id) {
        List<hoadon> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    // ================= IMPLEMENT hoadonDAO =================
    @Override
    public List<hoadon> findByUsername(String username) {
        return this.selectBySql(SELECT_BY_USERNAME, username);
    }

    @Override
    public List<hoadon> findByCardId(Integer cardId) {
        return this.selectBySql(SELECT_BY_CARD_ID, cardId);
    }

    @Override
    public hoadon findServicingByCardId(Integer cardId) {
        List<hoadon> list = this.selectBySql(SELECT_SERVICING_BY_CARD_ID, cardId);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<hoadon> findByTimeRange(Date from, Date to) {
        // Có thể cần ép kiểu Date sang java.sql.Date tùy thuộc vào util của bạn
        return this.selectBySql(SELECT_BY_TIME_RANGE, from, to);
    }

    @Override
    public List<hoadon> findByUserAndTimeRange(String username, Date from, Date to) {
        return this.selectBySql(SELECT_BY_USER_AND_TIME, username, from, to);
    }

    // --- HÀM PHỤ TRỢ ---
    private List<hoadon> selectBySql(String sql, Object... args) {
        List<hoadon> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                hoadon hd = new hoadon();
                hd.setId(rs.getLong("Id"));
                hd.setUsername(rs.getString("Username"));
                hd.setCardId(rs.getInt("CardId"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTrangThai(rs.getString("TrangThai"));
                list.add(hd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}