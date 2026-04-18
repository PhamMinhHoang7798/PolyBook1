package util;

import java.text.DecimalFormat;

public class XHelper {
    // Hàm format số tiền
    public static String formatMoney(double money) {
        // "###,###" giúp hiển thị 15000000 thành 15.000.000
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(money);
    }
}