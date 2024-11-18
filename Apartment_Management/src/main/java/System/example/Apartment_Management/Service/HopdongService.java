package System.example.Apartment_Management.Service;


import System.example.Apartment_Management.Model.Apartment;
import System.example.Apartment_Management.Model.Hopdong;
import System.example.Apartment_Management.Model.Resident;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HopdongService {
    Hopdong save(Hopdong hopdong);
    Hopdong update(Hopdong hopdong);
    Hopdong delete(Hopdong hopdong);
    Hopdong findById(int maHopDong);
    List<Hopdong> findAll();
    Page<Hopdong> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
