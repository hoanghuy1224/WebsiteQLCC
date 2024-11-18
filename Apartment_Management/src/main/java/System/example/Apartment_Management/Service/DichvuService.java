package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Dichvu;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DichvuService {
    List<Dichvu> findAll();
    Dichvu findById(int id);
    Dichvu add(Dichvu dichvu);
    Dichvu update(Dichvu dichvu);
    void deleteById(int id);
    Page<Dichvu> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection); // Trả về Page<Dichvu>
}
