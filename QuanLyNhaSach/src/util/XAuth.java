/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import entity.User;
/**
 *
 * @author nguye
 */
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
        return isLogin() && user.isVaiTro();
    }

    public static boolean isActive() {
        return isLogin() && user.isTrangThai();
    }
}

