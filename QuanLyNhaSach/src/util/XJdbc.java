package util;
// Lớp XJdbc cung cấp các phương thức hỗ trợ kết nối database và thực thi câu lệnh SQL như query, update và lấy dữ liệu.

import java.sql.*;

public class XJdbc {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
          + "databaseName=PolyBook;"
          + "encrypt=true;"
          + "trustServerCertificate=true;";

    private static final String USER = "sa";
    private static final String PASS = "12345";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không load được JDBC Driver", e);
        }
    }

    // ================= KẾT NỐI =================
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kết nối database", e);
        }
    }

    // ================= UPDATE (INSERT/UPDATE/DELETE) =================
    public static int executeUpdate(String sql, Object... args) {
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            setParams(ps, args);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi executeUpdate: " + sql, e);
        }
    }

    // ================= QUERY (SELECT) =================
    public static ResultSet executeQuery(String sql, Object... args) {
    try {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        setParams(ps, args);
        return ps.executeQuery();
    } catch (SQLException e) {
        throw new RuntimeException("Lỗi executeQuery: " + sql, e);
    }
}

    // ================= QUERY (shortcut) =================
    public static ResultSet query(String sql, Object... args) {
        return executeQuery(sql, args);
    }

    // ================= GET VALUE =================
    public static Object getValue(String sql, Object... args) {
        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            setParams(ps, args);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getObject(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getValue: " + sql, e);
        }
    }

    // ================= SET PARAM =================
    private static void setParams(PreparedStatement ps, Object... args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }
    }
}
