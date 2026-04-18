package dao;

import entity.User;
import java.util.List;

public interface UserDAO {
    void insert(User user);

    void update(User user);

    void delete(String tenDangNhap);

    User selectById(String tenDangNhap);

    List<User> selectAll();

    List<User> selectByKeyword(String keyword);

    public User findById(String username);
}
