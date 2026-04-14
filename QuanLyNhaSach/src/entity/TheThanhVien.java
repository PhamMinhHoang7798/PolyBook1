package entity;

public class TheThanhVien {

    private String maThe;
    private String maKhachHang;
    private String sdt;
    private String tenKhachHang;
    private int diemTichLuy;

    public TheThanhVien() {
    }

    public TheThanhVien(String maThe, String maKhachHang, String sdt, String tenKhachHang, int diemTichLuy) {
        this.maThe = maThe;
        this.maKhachHang = maKhachHang;
        this.sdt = sdt;
        this.tenKhachHang = tenKhachHang;
        this.diemTichLuy = diemTichLuy;
    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }
}
