/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvc.DAOInterface;

import java.util.List;
import mvc.Model.Siswa;

/**
 *
 * @author HP
 */
public interface ISiswa {
    public void insert(Siswa s);
    public void update(Siswa s);
    public void delete(String nis);
    public List<Siswa> getAll();
    public List<Siswa> getCariSiswa(String nama);
}
