package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Payment;
import System.example.Apartment_Management.Model.Staff;
import System.example.Apartment_Management.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServicelmpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaffById(int id) {
        Optional<Staff> optionalStaff = staffRepository.findById(id);
        return optionalStaff.orElse(null);
    }

    //    @Override
//    public Staff updateStaff(Staff staff) {
//        return staffRepository.save(staff);
//    }
    @Override
    public List<Staff> SearchStaff(String hoten) {
        return staffRepository.findByHoTenContainingIgnoreCase(hoten);
    }

    @Override
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(int id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Page<Staff> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.staffRepository.findAll(pageable);
    }
}
