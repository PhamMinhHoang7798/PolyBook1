package entity;

public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private int soLuongTon;
    private double donGia;
    private String maLoai;

    public SanPham() {
    }

    public SanPham(String maSanPham, String tenSanPham, int soLuongTon, double donGia, String maLoai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.maLoai = maLoai;
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
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }
}
