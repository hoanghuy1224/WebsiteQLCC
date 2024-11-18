package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Resident;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ResidentService {
    Resident getResident(int maCuDan);
    Resident saveResident(Resident resident);
    Resident updateResident(Resident resident);
    Resident deleteResident(int maCuDan);
    List<Resident> getAllResident();
//    List<Resident> findByName(String hoTen);
    Page<Resident> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
