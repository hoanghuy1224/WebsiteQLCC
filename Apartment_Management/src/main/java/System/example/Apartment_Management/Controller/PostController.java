package System.example.Apartment_Management.Controller;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import System.example.Apartment_Management.Model.Post;
import System.example.Apartment_Management.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public String viewHomePage(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/showPost")
    public String showPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "Admin/addpost";
    }

    @PostMapping("/savePost")
    public String savePost(@ModelAttribute Post post, @RequestParam("image") MultipartFile image) {
        try {
            postService.save(post, image);
            return "redirect:/post";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/post?error=uploadError";
        }
    }

    @GetMapping("/UpdatePost/{id}")
    public String UpdatePostForm(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("post", postService.findById(id).orElseThrow());
        return "Admin/updatePost";
    }

    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable(value = "id")Long id) {
        postService.delete(id);
        return "redirect:/post";
    }

    @GetMapping("/Post/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Post> page = postService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Post> posts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("postt", posts);
        return "Admin/post-list";
    }
}
