/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import java.util.List;
import dao.UserDAO;
import entity.User;
import util.XJdbc;
import util.XQuery;

public class UserDAOImpl implements UserDAO {

    private final String createSql = """
            INSERT INTO NguoiDung
            (TenDangNhap, MatKhau, HoTen, HinhAnh, VaiTro, TrangThai)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private final String updateSql = """
            UPDATE NguoiDung
            SET MatKhau=?, HoTen=?, HinhAnh=?, VaiTro=?, TrangThai=?
            WHERE TenDangNhap=?
            """;

    private final String deleteByIdSql = """
            DELETE FROM NguoiDung
            WHERE TenDangNhap=?
            """;

    private final String findAllSql = "SELECT * FROM NguoiDung";
    private final String findByIdSql = "SELECT * FROM NguoiDung WHERE TenDangNhap=?";

    public User create(User entity) {
        Object[] values = {
            entity.getTenDangNhap(),
            entity.getMatKhau(),
            entity.getHoTen(),
            entity.getHinhAnh(),
            entity.getVaiTro(),
            entity.isTrangThai()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(User entity) {
        Object[] values = {
            entity.getMatKhau(),
            entity.getHoTen(),
            entity.getHinhAnh(),
            entity.getVaiTro(),
            entity.isTrangThai(),
            entity.getTenDangNhap()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteByIdSql, id);
    }

    public List<User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql);
    }

    public User findById(String id) {
        return XQuery.getSingleBean(User.class, findByIdSql, id);
    }

    @Override
    public void insert(User user) {
        create(user);
    }


    @Override
    public void delete(String tenDangNhap) {
        deleteById(tenDangNhap);
    }

    @Override
    public User selectById(String tenDangNhap) {
        return XQuery.getSingleBean(User.class, findByIdSql, tenDangNhap);
    }

    @Override
    public List<User> selectAll() {
        return findAll();
    }


    @Override
    public List<User> selectByKeyword(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
