package System.example.Apartment_Management.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "Resident")
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maCuDan;

    @Column(name = "HoTen", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "SoDienThoai", length = 15)
    private String soDienThoai;

    @Column(name = "Email", length = 100)
    private String email;

    // Quan hệ nhiều-một với Apartment
    @ManyToOne
    @JoinColumn(name = "ma_can_ho", nullable = false)
    private Apartment canHo;

    // Quan hệ một-nhiều với Resident
    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<Hopdong> hopdongList;

    @OneToMany(mappedBy = "cuDan", cascade = CascadeType.REMOVE)
    private List<Payment> paymentList;

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<Hopdong> getHopdongList() {
        return hopdongList;
    }

    public void setHopdongList(List<Hopdong> hopdongList) {
        this.hopdongList = hopdongList;
    }

    // Getters and Setters
    public Integer getMaCuDan() {
        return maCuDan;
    }

    public void setMaCuDan(Integer maCuDan) {
        this.maCuDan = maCuDan;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Apartment getCanHo() {
        return canHo;
    }

    public void setCanHo(Apartment canHo) {
        this.canHo = canHo;
    }

//    @Override
//    public String toString() {
//        return "Resident{" +
//                "maCuDan=" + maCuDan +
//                ", hoTen='" + hoTen + '\'' +
//                ", soDienThoai='" + soDienThoai + '\'' +
//                ", email='" + email + '\'' +
//                ", canHo=" + (canHo != null ? canHo.getSoCanHo() : "null") +
//                '}';
//    }

}