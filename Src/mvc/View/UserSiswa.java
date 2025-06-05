/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mvc.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mvc.Koneksi.Koneksi;

/**
 *
 * @author fibnu
 */
public class UserSiswa extends javax.swing.JFrame {

    private Connection connection;
    private String nis;
    private String namaSiswa;
    private String kelasId;
    private String namaKelas;
    
    /**
     * Creates new form UserSiswa without specific student data
     */
    public UserSiswa() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("SIMAJA - Sistem Manajemen Jadwal");
        connection = Koneksi.connection();
        // Default view without specific student data
//        lblWelcome.setText("Selamat Datang");
        lblKelas.setText("Kelas: -");
    }
    
    /**
     * Creates new form UserSiswa with specific student data
     * 
     * @param nis NIS of the student
     * @param namaSiswa Name of the student
     * @param kelasId ID of the student's class
     */
    public UserSiswa(String nis, String namaSiswa, String kelasId) {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("SIMAJA - Sistem Manajemen Jadwal");
        connection = Koneksi.connection();
        this.nis = nis;
        this.namaSiswa = namaSiswa;
        this.kelasId = kelasId;
        
        // Get class name
        try {
            String query = "SELECT nama_kelas FROM kelas WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, kelasId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                namaKelas = rs.getString("nama_kelas");
            } else {
                namaKelas = "Unknown";
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            namaKelas = "Error";
            e.printStackTrace();
        }
        
        // Set welcome message and class info
        lblWelcome.setText("Selamat Datang, " + namaSiswa);
        lblKelas.setText("Kelas: " + namaKelas);
        
        // Load jadwal for this student's class
        loadJadwalSiswa();
    }
    
    /**
     * Load schedule data for the current student's class
     */
    private void loadJadwalSiswa() {
        DefaultTableModel model = (DefaultTableModel) tblJadwal.getModel();
        model.setRowCount(0); // Clear existing data
//        "SELECT j.id, h.nama AS hari, j.jam_mulai, j.jam_selesai, " +
//                          "mp.nama_mapel, g.nama AS nama_guru " +
//                          "FROM jadwal j " +
//                          "JOIN matapelajaran mp ON j.mapel_id = mp.id " +
//                          "JOIN guru g ON j.guru_id = g.nip " +
//                          "JOIN hari h ON j.hari_id = h.id " +
//                          "WHERE j.kelas_id = ? " +
//                          "ORDER BY h.id, j.jam_mulai";
        try {
            // Query to get student schedule from jadwal table
            String query = "SELECT j.*," +
                           "mp.*, k.*, g.*" +
                           "FROM jadwal j " +
                           "JOIN matapelajaran mp ON j.kodeMapel = mp.kodeMapel " +
                           "JOIN kelas k ON j.kelasId = k.kelasId " +
                           "JOIN guru g ON j.nipGuru = g.nip " +
                           "WHERE j.kelasId = ? " +
                           "ORDER BY j.jamMulai";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, kelasId);
            
            ResultSet rs = statement.executeQuery();
            
            // Check if there are schedules
            if (!rs.isBeforeFirst()) {
                // No schedules found
                JOptionPane.showMessageDialog(this, 
                    "Belum ada jadwal untuk kelas ini.", 
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            
            // Populate table with data
            while (rs.next()) {
                Object[] row = {
                    rs.getString("id"),
                    rs.getString("hari"),
                    rs.getString("jamMulai"),
                    rs.getString("jamSelesai"),
                    rs.getString("mp.namaMapel"),
                    rs.getString("g.nama")
                };
                model.addRow(row);
            }
            
            rs.close();
            statement.close();
            
            // Set column widths for better display
            tblJadwal.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
            tblJadwal.getColumnModel().getColumn(1).setPreferredWidth(100); // Hari
            tblJadwal.getColumnModel().getColumn(2).setPreferredWidth(100); // Jam Mulai
            tblJadwal.getColumnModel().getColumn(3).setPreferredWidth(100); // Jam Selesai
            tblJadwal.getColumnModel().getColumn(4).setPreferredWidth(150); // Mata Pelajaran
            tblJadwal.getColumnModel().getColumn(5).setPreferredWidth(150); // Guru
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading jadwal: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        lblKelas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblJadwal = new javax.swing.JTable();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIMAJA - Sistem Manajemen Jadwal");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        headerPanel.setBackground(new java.awt.Color(0, 102, 255));

        lblHeader.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("JADWAL PELAJARAN SISWA");

        lblWelcome.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(255, 255, 255));
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcome.setText("Selamat Datang");

        lblKelas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblKelas.setForeground(new java.awt.Color(255, 255, 255));
        lblKelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKelas.setText("Kelas: -");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblKelas)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tblJadwal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblJadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Hari", "Jam Mulai", "Jam Selesai", "Mata Pelajaran", "Guru"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblJadwal.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblJadwal);

        btnLogout = new javax.swing.JButton();
        btnLogout.setBackground(new java.awt.Color(255, 102, 102));
        btnLogout.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // Confirm logout
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Return to OnBoarding
            OnBoarding onBoarding = new OnBoarding();
            onBoarding.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblKelas;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JTable tblJadwal;
    // End of variables declaration//GEN-END:variables
}
