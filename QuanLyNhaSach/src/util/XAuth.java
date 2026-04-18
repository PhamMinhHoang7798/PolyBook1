// Lớp quản lý thông tin người dùng đang đăng nhập và kiểm tra quyền truy cập trong hệ thống.
package util;
import entity.User;

public class XAuth {
    public static User user;

    public static void login(User u) {
        user = u;
    }

    public static void logout() {
        user = null;
    }

    public static User getUser() {
        return user;
    }

    public static boolean isLogin() {
        return user != null;
    }

    public static boolean isManager() {
        return isLogin() && user.getVaiTro() == 1;
    }

    public static boolean isActive() {
        return isLogin() && user.isTrangThai();
    }
}

