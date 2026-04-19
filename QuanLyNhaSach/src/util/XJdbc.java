package util;

import java.sql.*;

public class XJdbc {

    // --- CẤU HÌNH THÔNG TIN DATABASE ---
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // Tên Driver SQL Server
    private static final String URL
            = "jdbc:sqlserver://localhost:1433;" // Địa chỉ host và cổng
            + "databaseName=PolyBook;" // Tên cơ sở dữ liệu
            + "encrypt=true;" // Bật mã hóa kết nối
            + "trustServerCertificate=true;"; // Tin tưởng chứng chỉ server

    private static final String USER = "sa"; // Tài khoản SQL Server
    private static final String PASS = "12345"; // Mật khẩu SQL Server

    // --- NẠP DRIVER KHI LỚP ĐƯỢC GỌI ---
    static {
        try {
            Class.forName(DRIVER); // Nạp Driver vào hệ thống
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không load được JDBC Driver", e); // Báo lỗi nếu thiếu thư viện
        }
    }

    // --- PHƯƠNG THỨC THIẾT LẬP KẾT NỐI ---
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS); // Trả về đối tượng Connection
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kết nối database", e); // Báo lỗi nếu sai URL/User/Pass
        }
    }

    // --- THỰC THI CÁC CÂU LỆNH CẬP NHẬT (INSERT/UPDATE/DELETE) ---
    public static int executeUpdate(String sql, Object... args) {
        try (
                Connection con = getConnection(); // Mở kết nối
                 PreparedStatement ps = con.prepareStatement(sql) // Chuẩn bị câu lệnh SQL
                ) {
            setParams(ps, args); // Điền tham số vào dấu ?
            return ps.executeUpdate(); // Trả về số dòng bị tác động
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi executeUpdate: " + sql, e); // Báo lỗi thực thi
        }
    }

    // --- THỰC THI CÁC CÂU LỆNH TRUY VẤN (SELECT) ---
    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            Connection con = getConnection(); // Mở kết nối
            PreparedStatement ps = con.prepareStatement(sql); // Chuẩn bị câu lệnh
            setParams(ps, args); // Điền tham số
            return ps.executeQuery(); // Trả về tập kết quả ResultSet
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi executeQuery: " + sql, e); // Báo lỗi truy vấn
        }
    }

    // --- PHƯƠNG THỨC RÚT GỌN CHO QUERY ---
    public static ResultSet query(String sql, Object... args) {
        return executeQuery(sql, args); // Gọi lại hàm executeQuery
    }

    // --- LẤY MỘT GIÁ TRỊ ĐƠN LẺ (VÍ DỤ: COUNT, MAX, ...) ---
    public static Object getValue(String sql, Object... args) {
        try (
                Connection con = getConnection(); // Kết nối
                 PreparedStatement ps = con.prepareStatement(sql) // Chuẩn bị lệnh
                ) {
            setParams(ps, args); // Điền tham số
            ResultSet rs = ps.executeQuery(); // Thực thi
            if (rs.next()) {
                return rs.getObject(1); // Lấy giá trị ở cột đầu tiên
            }
            return null; // Trả về null nếu không có dữ liệu
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getValue: " + sql, e); // Báo lỗi
        }
    }

    // --- HỖ TRỢ ĐIỀN THAM SỐ VÀO CÂU LỆNH SQL ---
    private static void setParams(PreparedStatement ps, Object... args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]); // Chèn giá trị vào vị trí thứ i+1 của dấu ?
        }
    }
}
