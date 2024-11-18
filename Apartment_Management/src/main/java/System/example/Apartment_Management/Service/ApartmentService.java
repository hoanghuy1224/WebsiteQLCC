package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Apartment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApartmentService {
    List<Apartment> getAll();
    Apartment getById(int maCanHo);
    Apartment add(Apartment apartment);
    Apartment update(Apartment apartment);
    Apartment delete(int maCanHo);
    Page<Apartment> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
