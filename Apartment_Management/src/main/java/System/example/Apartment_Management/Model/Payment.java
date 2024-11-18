package System.example.Apartment_Management.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaThanhToan")
    private Integer maThanhToan;

    //    @ManyToOne
//    @JoinColumn(name = "MaCuDan", nullable = false)
//    private Resident cuDan;
//
//    @ManyToOne
//    @JoinColumn(name = "MaDichVu", nullable = false)
//    private Dichvu dichVu;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MaCuDan", nullable = false)
    private Resident cuDan;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MaDichVu", nullable = false)
    private Dichvu dichVu;

    @Column(name = "SoTien", nullable = false)
    private Float soTien;

    @Column(name = "NgayThanhToan", nullable = false)
    private LocalDateTime ngayThanhToan;

    public Payment() {
        this.ngayThanhToan = LocalDateTime.now(); // Giá trị mặc định là thời điểm hiện tại
    }

    public Payment(Resident cuDan, Dichvu dichVu, Float soTien) {
        this.cuDan = cuDan;
        this.dichVu = dichVu;
        this.soTien = soTien;
        this.ngayThanhToan = LocalDateTime.now(); // Giá trị mặc định là thời điểm hiện tại
    }

    public Integer getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(Integer maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public Resident getCuDan() {
        return cuDan;
    }

    public void setCuDan(Resident cuDan) {
        this.cuDan = cuDan;
    }

    public Dichvu getDichVu() {
        return dichVu;
    }

    public void setDichVu(Dichvu dichVu) {
        this.dichVu = dichVu;
    }

    public Float getSoTien() {
        return soTien;
    }

    public void setSoTien(Float soTien) {
        this.soTien = soTien;
    }

    public LocalDateTime getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDateTime ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }
}
