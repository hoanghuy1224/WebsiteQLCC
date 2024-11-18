package System.example.Apartment_Management.Repository;

import System.example.Apartment_Management.Model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

}
