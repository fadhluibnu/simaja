/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Model;

/**
 *
 * @author fibnu
 */
public class MataPelajaran {
    
    private Integer id;
    private String kodeMapel;
    private String namaMapel;
    private Integer kkm;
    private String deskripsi;
    private String nipPengajar;
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * @return the kodeMapel
     */
    public String getKodeMapel() {
        return kodeMapel;
    }

    /**
     * @param kodeMapel the kodeMapel to set
     */
    public void setKodeMapel(String kodeMapel) {
        this.kodeMapel = kodeMapel;
    }

    /**
     * @return the namaMapel
     */
    public String getNamaMapel() {
        return namaMapel;
    }

    /**
     * @param namaMapel the namaMapel to set
     */
    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }

    /**
     * @return the kkm
     */
    public Integer getKkm() {
        return kkm;
    }

    /**
     * @param kkm the kkm to set
     */
    public void setKkm(Integer kkm) {
        this.kkm = kkm;
    }

    /**
     * @return the deskripsi
     */
    public String getDeskripsi() {
        return deskripsi;
    }

    /**
     * @param deskripsi the deskripsi to set
     */
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    /**
     * @return the nipPengajar
     */
    public String getNipPengajar() {
        return nipPengajar;
    }

    /**
     * @param nipPengajar the nipPengajar to set
     */
    public void setNipPengajar(String nipPengajar) {
        this.nipPengajar = nipPengajar;
    }
    
    
    
}
