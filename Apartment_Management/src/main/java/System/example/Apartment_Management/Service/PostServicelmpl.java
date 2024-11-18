package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Post;
import System.example.Apartment_Management.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PostServicelmpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServicelmpl.class);

    @Autowired
    private PostRepository postRepository;

    private final String uploadDir = "C:\\Users\\hoang quang huy\\IdeaProjects\\Apartment_Management\\Apartment_Management\\src\\main\\resources\\static\\upload";

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(Post post, MultipartFile image) throws IOException {
        Post existingPost = post.getId() != null ? postRepository.findById(post.getId()).orElse(null) : null;
        if (existingPost != null) {
            // Cập nhật các trường khác
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());

            if (image != null && !image.isEmpty()) {
                // Xử lý lưu ảnh mới
                String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File file = new File(directory, imageName);
                try {
                    image.transferTo(file);
                    existingPost.setImageUrl(imageName); // Cập nhật URL ảnh mới
                } catch (IOException e) {
                    logger.error("Error save file", e);
                    throw e;
                }
            } else {
                // Giữ nguyên URL ảnh cũ nếu không có ảnh mới
                existingPost.setImageUrl(existingPost.getImageUrl());
            }
            // Lưu đối tượng đã được cập nhật
            postRepository.save(existingPost);
        } else {
            // Nếu là bài đăng mới, xử lý ảnh và lưu bài đăng
            if (image != null && !image.isEmpty()) {
                String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File file = new File(directory, imageName);
                image.transferTo(file);
                post.setImageUrl(imageName);
            }
            postRepository.save(post); // Lưu bài đăng mới
        }
    }


    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.postRepository.findAll(pageable);
    }
}
