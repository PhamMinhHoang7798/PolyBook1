package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.XJdbc;

public interface CrudDAO<T, ID> {

    T create(T entity);

    void update(T entity);

    void deleteById(ID id);

    List<T> findAll();

    T findById(ID id);
}