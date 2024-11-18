package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {
    List<Staff> getAllStaff();
    Staff getStaffById(int id);
    Staff updateStaff(Staff staff);
    Staff addStaff(Staff staff);
    void deleteStaff(int id);
    Page<Staff> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
