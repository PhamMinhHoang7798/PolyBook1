package util;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class XQuery {

    // ================= LẤY 1 OBJECT =================
    public static <B> B getSingleBean(Class<B> beanClass, String sql, Object... values) {
        List<B> list = getBeanList(beanClass, sql, values);
        return list.isEmpty() ? null : list.get(0);
    }

    // ================= LẤY DANH SÁCH =================
    public static <B> List<B> getBeanList(Class<B> beanClass, String sql, Object... values) {
        List<B> list = new ArrayList<>();

        try {
            ResultSet rs = XJdbc.executeQuery(sql, values);

            while (rs.next()) {
                B bean = readBean(rs, beanClass);
                list.add(bean);
            }

            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn dữ liệu", e);
        }

        return list;
    }

    // ================= MAP RESULTSET -> OBJECT =================
    private static <B> B readBean(ResultSet rs, Class<B> beanClass) throws Exception {
        B bean = beanClass.getDeclaredConstructor().newInstance();
        Method[] methods = beanClass.getDeclaredMethods();

        for (Method method : methods) {
            String methodName = method.getName();

            // chỉ lấy setter
            if (methodName.startsWith("set") && method.getParameterCount() == 1) {
                String columnName = methodName.substring(3);
                Class<?> paramType = method.getParameterTypes()[0];

                try {
                    Object value;

                    // xử lý boolean riêng
                    if (paramType == boolean.class || paramType == Boolean.class) {
                        value = rs.getBoolean(columnName);
                    } else if (paramType == int.class || paramType == Integer.class) {
                        value = rs.getInt(columnName);
                    } else if (paramType == double.class || paramType == Double.class) {
                        value = rs.getDouble(columnName);
                    } else if (paramType == String.class) {
                        value = rs.getString(columnName);
                    } else {
                        value = rs.getObject(columnName);
                    }

                    method.invoke(bean, value);

                } catch (Exception e) {
                    System.out.println("Không tìm thấy cột: " + columnName);
                }
            }
        }

        return bean;
    }
}
