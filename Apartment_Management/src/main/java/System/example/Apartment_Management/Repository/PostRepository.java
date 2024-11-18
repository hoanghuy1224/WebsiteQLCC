package System.example.Apartment_Management.Repository;

import System.example.Apartment_Management.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
