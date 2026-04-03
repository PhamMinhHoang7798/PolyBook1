/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.book.dao;
import entity.LoaiSP;
import java.util.List;

public interface LoaiSPDAO {
    void insert(LoaiSP entity);
    void update(LoaiSP entity);
    void delete(String maLoai);
    List<LoaiSP> selectAll();
    LoaiSP findById(String maLoai);
}