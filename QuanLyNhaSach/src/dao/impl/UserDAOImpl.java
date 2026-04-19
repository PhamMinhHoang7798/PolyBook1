package dao.impl;

import java.util.List;
import dao.UserDAO;
import entity.User;
import util.XJdbc;
import util.XQuery;

public class UserDAOImpl implements UserDAO {

    // --- KHAI BÁO CÁC CÂU LỆNH SQL TĨNH ---
    private final String createSql = """
            INSERT INTO NguoiDung (TenDangNhap, MatKhau, HoTen, HinhAnh, VaiTro, TrangThai)
            VALUES (?, ?, ?, ?, ?, ?)
            """; // Câu lệnh thêm mới

    private final String updateSql = """
            UPDATE NguoiDung SET MatKhau=?, HoTen=?, HinhAnh=?, VaiTro=?, TrangThai=?
            WHERE TenDangNhap=?
            """; // Câu lệnh cập nhật

    private final String deleteByIdSql = "DELETE FROM NguoiDung WHERE TenDangNhap=?"; // Câu lệnh xóa
    private final String findAllSql = "SELECT * FROM NguoiDung"; // Câu lệnh lấy tất cả
    private final String findByIdSql = "SELECT * FROM NguoiDung WHERE TenDangNhap=?"; // Tìm theo ID

    // --- THỰC THI THÊM MỚI NGƯỜI DÙNG ---
    public User create(User entity) {
        Object[] values = {
            entity.getTenDangNhap(), entity.getMatKhau(), entity.getHoTen(),
            entity.getHinhAnh(), entity.getVaiTro(), entity.isTrangThai()
        }; // Mảng chứa dữ liệu từ đối tượng entity
        XJdbc.executeUpdate(createSql, values); // Gọi XJdbc để lưu vào DB
        return entity;
    }

    // --- THỰC THI CẬP NHẬT THÔNG TIN ---
    @Override
    public void update(User entity) {
        Object[] values = {
            entity.getMatKhau(), entity.getHoTen(), entity.getHinhAnh(),
            entity.getVaiTro(), entity.isTrangThai(), entity.getTenDangNhap()
        }; // Thứ tự giá trị phải khớp với các dấu ? trong updateSql
        XJdbc.executeUpdate(updateSql, values); // Thực thi cập nhật
    }

    // --- XÓA NGƯỜI DÙNG THEO MÃ ---
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteByIdSql, id); // Thực thi xóa
    }

    // --- TRUY VẤN DANH SÁCH VÀ TÌM KIẾM ---
    public List<User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql); // Chuyển kết quả ResultSet thành danh sách List<User>
    }

    public User findById(String id) {
        return XQuery.getSingleBean(User.class, findByIdSql, id); // Lấy duy nhất một đối tượng User
    }

    @Override
    public List<User> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NguoiDung WHERE TenDangNhap LIKE ? OR HoTen LIKE ?"; // Tìm theo tên hoặc ID
        return util.XQuery.getBeanList(User.class, sql, "%" + keyword + "%", "%" + keyword + "%"); // Dùng toán tử % để tìm gần đúng
    }

    // --- CÁC PHƯƠNG THỨC TRIỂN KHAI TỪ INTERFACE ---
    @Override
    public void insert(User user) {
        create(user);
    } // Gọi lại hàm create

    @Override
    public void delete(String tenDangNhap) {
        deleteById(tenDangNhap);
    } // Gọi lại hàm deleteById

    @Override
    public User selectById(String tenDangNhap) {
        return XQuery.getSingleBean(User.class, findByIdSql, tenDangNhap);
    } // Tìm theo ID

    @Override
    public List<User> selectAll() {
        return findAll();
    } // Lấy hết danh sách
}
