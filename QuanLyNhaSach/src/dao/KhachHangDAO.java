/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.KhachHang;
import java.util.List;
import dao.impl.KhachHangDAOImpl;

public interface KhachHangDAO {

    void insert(KhachHang kh);

    void update(KhachHang kh);

    void delete(int maKhachHang);

    KhachHang selectById(int maKhachHang);

    KhachHang selectByPhone(String sdt); // Tìm khách hàng nhanh tại quầy bằng SĐT

    List<KhachHang> selectAll();

    List<KhachHang> selectByKeyword(String keyword);
}
