package dao.impl;

import entity.User;
import dao.UserDAO;
import util.XJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    // Cần thay đổi tên bảng [Users] và các cột cho khớp SQL của bạn
    String INSERT_SQL = "INSERT INTO Users (Username, Password, Fullname, Role) VALUES (?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE Users SET Password = ?, Fullname = ?, Role = ? WHERE Username = ?";
    String DELETE_SQL = "DELETE FROM Users WHERE Username = ?";
    String SELECT_ALL_SQL = "SELECT * FROM Users";
    String SELECT_BY_ID_SQL = "SELECT * FROM Users WHERE Username = ?";
    String SELECT_BY_KEYWORD_SQL = "SELECT * FROM Users WHERE Fullname LIKE ?";

    @Override
    public void insert(User user) {
        // Thay các get... bằng đúng tên hàm trong file entity.User
        XJdbc.executeUpdate(INSERT_SQL, user.getMaUser(), user.getMatKhau(),user.getHoTen(), user.isVaiTro());
    }

    @Override
    public void update(User user) {
        XJdbc.executeUpdate(UPDATE_SQL, user.getMatKhau(), user.getHoTen(), user.isVaiTro(), user.getMaUser());
    }

    @Override
    public void delete(String tenDangNhap) {
        XJdbc.executeUpdate(DELETE_SQL, tenDangNhap);
    }

    @Override
    public User selectById(String tenDangNhap) {
        List<User> list = this.selectBySql(SELECT_BY_ID_SQL, tenDangNhap);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<User> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<User> selectByKeyword(String keyword) {
        return this.selectBySql(SELECT_BY_KEYWORD_SQL, "%" + keyword + "%");
    }

    @Override
    public User findById(String username) {
        // Dùng chung logic với selectById vì chức năng giống nhau
        return this.selectById(username);
    }

    // Hàm phụ trợ dùng chung để truy vấn danh sách User
    private List<User> selectBySql(String sql, Object... args) {
        List<User> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                User user = new User();
                // Map dữ liệu từ ResultSet vào Entity
                user.setMaUser(rs.getString("Username"));
                user.setMatKhau(rs.getString("Password"));
                user.setHoTen(rs.getString("Fullname"));
                user.setVaiTro(rs.getBoolean("Role")); // Giả sử Role là boolean
                list.add(user);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}