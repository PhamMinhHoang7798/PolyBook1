/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import dao.*;
import entity.User;
import java.util.List;
/**
 *
 * @author nguye
 */
public interface UserDAO {
    void insert(User user);

    void update(User user);

    void delete(String tenDangNhap);

    User selectById(String tenDangNhap);

    List<User> selectAll();

    List<User> selectByKeyword(String keyword);

    public User findById(String username);
}
