package entity;

public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private int soLuongTon;
    private double dongia;

    public SanPham() {}

    public SanPham(String maSanPham, String tenSanPham, int soLuongTon, double dongia) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuongTon = soLuongTon;
        this.dongia = dongia;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public double getDonGia() {
        return dongia;
    }

    public void setDonGia(double dongia) {
        this.dongia = dongia;
    }
}