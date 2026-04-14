package entity;

import java.util.Date;

public class hoadon {
    private String maHoaDon;
    private String maKhachHang;
    private Date ngayLap;
    private String phuongThucThanhToan;
    private String trangThai;
    private Double khuyenMai;
    private Double tongTien;
    private String maVoucher;
    private String tenDangNhap;

    public hoadon() {}

    public hoadon(String maHoaDon, String maKhachHang, Date ngayLap,
                  String phuongThucThanhToan, String trangThai,
                  Double khuyenMai, Double tongTien,
                  String maVoucher, String tenDangNhap) {
        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
        this.ngayLap = ngayLap;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThai = trangThai;
        this.khuyenMai = khuyenMai;
        this.tongTien = tongTien;
        this.maVoucher = maVoucher;
        this.tenDangNhap = tenDangNhap;
    }

    // getter setter
    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }

    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) { this.maKhachHang = maKhachHang; }

    public Date getNgayLap() { return ngayLap; }
    public void setNgayLap(Date ngayLap) { this.ngayLap = ngayLap; }

    public String getPhuongThucThanhToan() { return phuongThucThanhToan; }
    public void setPhuongThucThanhToan(String phuongThucThanhToan) { this.phuongThucThanhToan = phuongThucThanhToan; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public Double getKhuyenMai() { return khuyenMai; }
    public void setKhuyenMai(Double khuyenMai) { this.khuyenMai = khuyenMai; }

    public Double getTongTien() { return tongTien; }
    public void setTongTien(Double tongTien) { this.tongTien = tongTien; }

    public String getMaVoucher() { return maVoucher; }
    public void setMaVoucher(String maVoucher) { this.maVoucher = maVoucher; }

    public String getTenDangNhap() { return tenDangNhap; }
    public void setTenDangNhap(String tenDangNhap) { this.tenDangNhap = tenDangNhap; }
}