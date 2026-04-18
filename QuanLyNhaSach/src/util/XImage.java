package util;
// Lớp XImage cung cấp các phương thức hỗ trợ xử lý hình ảnh như lưu file ảnh, đọc ảnh từ thư mục và lấy icon ứng dụng.
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class XImage {

    /**
     * Ảnh biểu tượng của ứng dụng (nếu có)
     */
    public static Image getAppIcon() {
        URL url = XImage.class.getResource("/icon/logo.png"); // Thay bằng logo của bạn
        return new ImageIcon(url).getImage();
    }

    /**
     * Sao chép file ảnh vào thư mục 'logos' của dự án.
     * @param src file nguồn được chọn từ JFileChooser
     */
    public static void save(File src) {
        File dst = new File("logos", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs(); // Tạo thư mục logos nếu chưa tồn tại
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Đọc ảnh từ thư mục 'logos'
     * @param fileName tên file ảnh (lấy từ Database)
     * @return ImageIcon để set vào JLabel
     */
    public static ImageIcon read(String fileName) {
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}