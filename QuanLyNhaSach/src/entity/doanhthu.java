package entity;

public class doanhthu {

    // ===== THỐNG KÊ THEO VOUCHER =====
    public static class TheoVoucher {
        private String maVoucher;
        private double tongDoanhThu;
        private int soLuongHoaDon;

        public String getMaVoucher() {
            return maVoucher;
        }

        public void setMaVoucher(String maVoucher) {
            this.maVoucher = maVoucher;
        }

        public double getTongDoanhThu() {
            return tongDoanhThu;
        }

        public void setTongDoanhThu(double tongDoanhThu) {
            this.tongDoanhThu = tongDoanhThu;
        }

        public int getSoLuongHoaDon() {
            return soLuongHoaDon;
        }

        public void setSoLuongHoaDon(int soLuongHoaDon) {
            this.soLuongHoaDon = soLuongHoaDon;
        }
    }

    // ===== THỐNG KÊ THEO NGƯỜI DÙNG =====
    public static class TheoNguoiDung {
        private String tenDangNhap;
        private double tongDoanhThu;
        private int soHoaDon;

        public String getTenDangNhap() {
            return tenDangNhap;
        }

        public void setTenDangNhap(String tenDangNhap) {
            this.tenDangNhap = tenDangNhap;
        }

        public double getTongDoanhThu() {
            return tongDoanhThu;
        }

        public void setTongDoanhThu(double tongDoanhThu) {
            this.tongDoanhThu = tongDoanhThu;
        }

        public int getSoHoaDon() {
            return soHoaDon;
        }

        public void setSoHoaDon(int soHoaDon) {
            this.soHoaDon = soHoaDon;
        }
    }
}
