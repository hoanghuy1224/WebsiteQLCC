package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Hopdong;
import System.example.Apartment_Management.Repository.HopdongRepository;
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
public class HopdongServicelmpl implements HopdongService {

    @Autowired
    private HopdongRepository hopdongRepository;

    @Override
    public Hopdong save(Hopdong hopdong) {
        return hopdongRepository.save(hopdong);
    }

    @Override
    public Hopdong update(Hopdong hopdong) {
        // Kiểm tra xem dịch vụ có tồn tại không trước khi cập nhật
        Optional<Hopdong> existingDichvu = hopdongRepository.findById(hopdong.getMaHopDong());
        if (existingDichvu.isPresent()) {
            return hopdongRepository.save(hopdong);
        } else {
            throw new RuntimeException("Dịch vụ không tồn tại để cập nhật");
        }
    }

    @Override
    @Transactional
    public Hopdong delete(Hopdong hopdong) {
        Optional<Hopdong> existingHopdong = hopdongRepository.findById(hopdong.getMaHopDong());
        if (existingHopdong.isPresent()) {
            hopdongRepository.delete(hopdong);
            return hopdong;
        } else {
            throw new RuntimeException("Hợp đồng không tồn tại để xóa");
        }
    }


    @Override
    public Hopdong findById(int maHopDong) {
        return hopdongRepository.findById(maHopDong)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dịch vụ với ID: " + maHopDong));
    }

    @Override
    public List<Hopdong> findAll() {
        return hopdongRepository.findAll();
    }

    @Override
    public Page<Hopdong> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.hopdongRepository.findAll(pageable);
    }
}
