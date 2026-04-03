package entity;

public class User {
    private String maUser;
    private String matKhau;
    private String hoTen;
    private boolean vaiTro; // true: Quản lý, false: Nhân viên

    public User() {
    }

    public User(String maUser, String matKhau, String hoTen, boolean vaiTro) {
        this.maUser = maUser;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
    }

    // Getter và Setter
    public String getMaUser() { return maUser; }
    public void setMaUser(String maUser) { this.maUser = maUser; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public boolean isVaiTro() { return vaiTro; }
    public void setVaiTro(boolean vaiTro) { this.vaiTro = vaiTro; }
}