/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import entity.LoaiSP;
import util.XJdbc;
import java.sql.*;
import java.util.*;

public class LoaiSPDAOImpl {

    String insert_sql = "INSERT INTO LoaiSanPham VALUES (?,?)";
    String update_sql = "UPDATE LoaiSanPham SET TenLoai=? WHERE MaLoai=?";
    String delete_sql = "DELETE FROM LoaiSanPham WHERE MaLoai=?";
    String select_all = "SELECT * FROM LoaiSanPham";
    String select_by_id = "SELECT * FROM LoaiSanPham WHERE MaLoai=?";


    public void insert(LoaiSP entity) {
        XJdbc.executeUpdate(insert_sql,
                entity.getMaLoai(),
                entity.getTenLoai());
    }

    public void update(LoaiSP entity) {
        XJdbc.executeUpdate(update_sql,
                entity.getTenLoai(),
                entity.getMaLoai());
    }

    public void delete(String maLoai) {
        XJdbc.executeUpdate(delete_sql, maLoai);
    }

    public List<LoaiSP> selectAll() {
        return selectBySql(select_all);
    }

    public LoaiSP findById(String maLoai) {
        List<LoaiSP> list = selectBySql(select_by_id, maLoai);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<LoaiSP> selectBySql(String sql, Object... args) {
        List<LoaiSP> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                LoaiSP l = new LoaiSP();
                l.setMaLoai(rs.getString("MaLoai"));
                l.setTenLoai(rs.getString("TenLoai"));
                list.add(l);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

