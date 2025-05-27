<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Koneksi;
=======
/*package mvc.Koneksi;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.koneksi;
>>>>>>> yoga
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
<<<<<<< HEAD
 * @author NBN0C
=======
 * @author gansp
>>>>>>> yoga
 */
public class Koneksi {
    static Connection con;
    
    public static Connection connection() {
        if (con == null) {
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("simaja");
            data.setUser("root");
            data.setPassword("");
<<<<<<< HEAD
=======
            
>>>>>>> yoga
            try {
                con = data.getConnection();
            } catch (SQLException ex) {
                System.out.println("tidak konek");
            }
        }
        return con;
    }
}

