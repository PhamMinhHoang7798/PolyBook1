//Name : Phạm Minh Hoàng 
//Mssv : PS47440
//Date : 
//Slide:
package entity;

import java.util.Scanner;

public class KhachHang {

    private int maKhachHang;
    private String tenKhachHang;
    private String soDienThoai;
    private String loaiThe;
    private int diemTichLuy;

    public KhachHang() {
    }

    public KhachHang(int maKhachHang, String tenKhachHang, String soDienThoai, String loaiThe, int diemTichLuy) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.loaiThe = loaiThe;
        this.diemTichLuy = diemTichLuy;
    }

    // Getter và Setter
    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getLoaiThe() {
        return loaiThe;
    }

    public void setLoaiThe(String loaiThe) {
        this.loaiThe = loaiThe;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }
}
