package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Resident;
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
import java.util.Optional;

@Service
public class ResidentServicelmpl implements ResidentService {
    @Autowired
    private ResidentRepository residentRepository;


    @Override
    public Resident getResident(int maCuDan) {
        Optional<Resident> resident = residentRepository.findById(maCuDan);
        return resident.orElse(null);
    }

    @Override
    public Resident saveResident(Resident resident) {
        return residentRepository.save(resident);
    }

    @Override
    public Resident updateResident(Resident resident) {
        if (residentRepository.existsById(resident.getMaCuDan())) {
            return residentRepository.save(resident);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Resident deleteResident(int maCuDan) {
        Optional<Resident> resident = residentRepository.findById(maCuDan);
        if (resident.isPresent()) {
            residentRepository.deleteById(maCuDan);
            return resident.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Resident> getAllResident() {
        return residentRepository.findAll();
    }

    @Override
    public List<Resident> findByName(String hoTen) {
        return residentRepository.findByHoTenContainingIgnoreCase(hoTen);
    }

    @Override
    public Page<Resident> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Resident> page = residentRepository.findAll(pageable);

        System.out.println("Dữ liệu trong trang: " + page.getContent());

        return page;
    }


}
