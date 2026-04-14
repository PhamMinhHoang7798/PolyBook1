package entity;

public class User {
    private String tenDangNhap;
    private String matKhau;
    private String hoTen;
    private String hinhAnh;
    private int vaiTro; // 0 khách, 1 nhân viên, 2 quản lý
    private boolean trangThai;

    public User() {
    }

    public User(String tenDangNhap, String matKhau, String hoTen,
            String hinhAnh, int vaiTro, boolean trangThai) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.hinhAnh = hinhAnh;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    // ===== ROLE =====
    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

}
