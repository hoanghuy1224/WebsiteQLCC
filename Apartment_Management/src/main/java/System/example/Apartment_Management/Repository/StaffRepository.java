package System.example.Apartment_Management.Repository;

import System.example.Apartment_Management.Model.Client;
import System.example.Apartment_Management.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> findByHoTenContainingIgnoreCase(String hoTen);
}
