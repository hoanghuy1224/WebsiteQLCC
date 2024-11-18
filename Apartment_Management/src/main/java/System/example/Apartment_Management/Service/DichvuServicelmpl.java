package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Dichvu;
import System.example.Apartment_Management.Repository.DichvuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DichvuServicelmpl implements DichvuService {

    @Autowired
    private DichvuRepository dichvuRepository;

    @Override
    public List<Dichvu> findAll() {
        return dichvuRepository.findAll();
    }

    @Override
    public Dichvu findById(int id) {
        Optional<Dichvu> dichvu = dichvuRepository.findById(id);
        return dichvu.orElse(null);
    }

    @Override
    public Dichvu add(Dichvu dichvu) {
        return dichvuRepository.save(dichvu);
    }

    @Override
    public Dichvu update(Dichvu dichvu) {
        return dichvuRepository.save(dichvu);
    }

    @Override
    public void deleteById(int id) {
        dichvuRepository.deleteById(id);
    }

    @Override
    public Page<Dichvu> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.dichvuRepository.findAll(pageable);
    }
}
