package System.example.Apartment_Management.Repository;

import System.example.Apartment_Management.Model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    List<Resident> findByHoTenContainingIgnoreCase(String hoTen);
}
