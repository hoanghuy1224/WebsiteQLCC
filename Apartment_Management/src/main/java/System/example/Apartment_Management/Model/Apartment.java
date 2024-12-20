package System.example.Apartment_Management.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maCanHo;

    @Column(name = "SoCanHo", nullable = false, length = 10)
    private String soCanHo;

    @Column(name = "ToaNha", nullable = false, length = 50)
    private String toaNha;

    @Column(name = "Tang")
    private Integer tang;

    @Column(name = "DienTich")
    private Float dienTich;

    // Quan hệ một-nhiều với Resident
    @OneToMany(mappedBy = "canHo", cascade = CascadeType.REMOVE)
    private List<Resident> cuDanList;

    public List<Hopdong> getHopdongList() {
        return hopdongList;
    }

    public void setHopdongList(List<Hopdong> hopdongList) {
        this.hopdongList = hopdongList;
    }

    // Quan hệ một-nhiều với Resident
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE)
    private List<Hopdong> hopdongList;

    // Getters and Setters
    public Integer getMaCanHo() {
        return maCanHo;
    }

    public void setMaCanHo(Integer maCanHo) {
        this.maCanHo = maCanHo;
    }

    public String getSoCanHo() {
        return soCanHo;
    }

    public void setSoCanHo(String soCanHo) {
        this.soCanHo = soCanHo;
    }

    public String getToaNha() {
        return toaNha;
    }

    public void setToaNha(String toaNha) {
        this.toaNha = toaNha;
    }

    public Integer getTang() {
        return tang;
    }

    public void setTang(Integer tang) {
        this.tang = tang;
    }

    public Float getDienTich() {
        return dienTich;
    }

    public void setDienTich(Float dienTich) {
        this.dienTich = dienTich;
    }

    public List<Resident> getCuDanList() {
        return cuDanList;
    }

    public void setCuDanList(List<Resident> cuDanList) {
        this.cuDanList = cuDanList;
    }
}

//, cascade = CascadeType.REMOVE, orphanRemoval = true