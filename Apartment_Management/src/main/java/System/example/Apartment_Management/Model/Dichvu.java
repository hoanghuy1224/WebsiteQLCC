package System.example.Apartment_Management.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Service")
public class Dichvu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDichVu")
    private Integer maDichVu;

    @Column(name = "TenDichVu", nullable = false, length = 100)
    private String tenDichVu;

    @Column(name = "Gia", nullable = false)
    private Float gia;

    @OneToMany(mappedBy = "dichVu", cascade = CascadeType.ALL)
    private List<Payment> paymentList;

    // Constructors, getters, and setters

    public Dichvu() {
    }

    public Dichvu(String tenDichVu, Float gia) {
        this.tenDichVu = tenDichVu;
        this.gia = gia;
    }

    public Integer getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(Integer maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
}
