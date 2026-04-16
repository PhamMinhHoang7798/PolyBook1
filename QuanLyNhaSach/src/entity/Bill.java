package entity;

import java.util.Date;

public class Bill {
    private String MaHoaDon;
    private String TenKhachHang;
    private String TenSanPham;
    private int SoLuong;
    private double KhuyenMai;
    private double TongTien;
    private Date NgayLap;

    private double DoanhThu;

    public String getMaHoaDon() { return MaHoaDon; }
    public void setMaHoaDon(String MaHoaDon) { this.MaHoaDon = MaHoaDon; }

    public String getTenKhachHang() { return TenKhachHang; }
    public void setTenKhachHang(String TenKhachHang) { this.TenKhachHang = TenKhachHang; }

    public String getTenSanPham() { return TenSanPham; }
    public void setTenSanPham(String TenSanPham) { this.TenSanPham = TenSanPham; }

    public int getSoLuong() { return SoLuong; }
    public void setSoLuong(int SoLuong) { this.SoLuong = SoLuong; }

    public double getKhuyenMai() { return KhuyenMai; }
    public void setKhuyenMai(double KhuyenMai) { this.KhuyenMai = KhuyenMai; }

    public double getTongTien() { return TongTien; }
    public void setTongTien(double TongTien) { this.TongTien = TongTien; }

    public Date getNgayLap() { return NgayLap; }
    public void setNgayLap(Date NgayLap) { this.NgayLap = NgayLap; }

    public double getDoanhThu() { return DoanhThu; }
    public void setDoanhThu(double DoanhThu) { this.DoanhThu = DoanhThu; }
}

