package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Apartment;
import System.example.Apartment_Management.Repository.ApartmentRepository;
import System.example.Apartment_Management.Repository.HopdongRepository;
import System.example.Apartment_Management.Repository.ResidentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServicelmpl implements ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public List<Apartment> getAll() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment getById(int maCanHo) {
        return apartmentRepository.findById(maCanHo).orElse(null);
    }

        @Override
    public Apartment add(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @Override
    public Apartment update(Apartment apartment) {
        if (apartmentRepository.existsById(apartment.getMaCanHo())) {
            return apartmentRepository.save(apartment);
        } else {
            return null;
        }

    }
    @Override
    public List<Apartment> search(String toaNha){
        return apartmentRepository.findByToaNhaContainingIgnoreCase(toaNha);
    }

    @Override
    @Transactional
    public Apartment delete(int maCanHo) {
        Apartment apartment = getById(maCanHo);
        if (apartment != null) {
            try {
                apartmentRepository.delete(apartment);
            } catch (Exception e) {
                // Ghi log và xử lý lỗi
                System.err.println("Error deleting apartment or residents: " + e.getMessage());
                throw e;
            }
        }
        return apartment;
    }

    @Override
    public Page<Apartment> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.apartmentRepository.findAll(pageable);
    }
}
