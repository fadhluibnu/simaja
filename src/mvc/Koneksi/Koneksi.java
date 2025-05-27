package mvc.Koneksi;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author HP
 */
public class Koneksi {
     // Variabel koneksi bersifat statik (agar bisa diakses tanpa buat objek)
    static Connection con;

    // Method untuk mendapatkan koneksi ke database
    public static Connection connection() {
        if (con == null) {
            // Membuat objek data source untuk MySQL
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("simaja"); // Nama database
            data.setUser("root");            // Username MySQL
            data.setPassword("");            // Password MySQL (kosong)

            try {
                con = data.getConnection();  // Mencoba membuat koneksi
            } catch (SQLException ex) {
                System.out.println("Tidak konek: " + ex.getMessage());
            }
        }
        return con;
    }
}
