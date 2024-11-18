package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Hopdong;
import System.example.Apartment_Management.Model.Post;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface PostService {
    List<Post> findAll();
    Optional<Post> findById(Long id);
    void save(Post post, MultipartFile image) throws IOException;
    void delete(Long id);
    Page<Post> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
