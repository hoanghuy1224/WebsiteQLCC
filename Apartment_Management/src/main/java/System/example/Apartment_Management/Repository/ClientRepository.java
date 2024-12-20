package System.example.Apartment_Management.Repository;

import System.example.Apartment_Management.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByHoTenContainingIgnoreCase(String hoTen);
}
