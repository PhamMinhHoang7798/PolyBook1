package util;

import java.awt.Component;
import javax.swing.JOptionPane;
// Lớp XDialog cung cấp các phương thức hỗ trợ hiển thị hộp thoại (alert, confirm, prompt) giúp tương tác với người dùng.
public class XDialog {
    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message);
    }

    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(
                parent,
                message,
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        return result == JOptionPane.YES_OPTION;
    }

    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message);
    }
}

