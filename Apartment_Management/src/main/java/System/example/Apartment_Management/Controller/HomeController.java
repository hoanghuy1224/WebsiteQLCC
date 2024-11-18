package System.example.Apartment_Management.Controller;

import System.example.Apartment_Management.Model.Client;
import System.example.Apartment_Management.Model.Post;
import System.example.Apartment_Management.Service.ClienService;
import System.example.Apartment_Management.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);

        // Tạo đối tượng Client trống cho form
        model.addAttribute("client", new Client());
        model.addAttribute("message", "Gưi thông tin thành công!");
        return "home/index";
    }
}
