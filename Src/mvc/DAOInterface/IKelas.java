/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.DAOInterface;

import java.util.List;
import mvc.Model.Kelas;
/**
 *
 * @author nabig
 */
public interface IKelas {
    public void insert(Kelas b);
    public void update(Kelas b);
    public void delete(int kelasId);
    public List<Kelas> getAll();
    public List<Kelas> getCariNamaKelas(String namaKelas);
}
