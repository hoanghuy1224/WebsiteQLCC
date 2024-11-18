package System.example.Apartment_Management.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Admin/dashboard"; // Trả về trang dashboard
    }

    @GetMapping("/Resident")
    public String Resident() {
        return "redirect:/rsd"; // Chuyển hướng đến /rsd để lấy dữ liệu
    }

    @GetMapping("/Apartment")
    public String Apartment() {
        return "redirect:/apm"; // Trả về trang Apartment
    }

    @GetMapping("/Service")
    public String Service() {
        return "redirect:/dv";// Trả về trang Service
    }

    @GetMapping("/Contract")
    public String Contract() {
        return "redirect:/hd"; // Trả về trang Contract
    }

    @GetMapping("/Staff")
    public String Staff() {
        return "redirect:/sta"; // Trả về trang Staff
    }

    @GetMapping("/Payment")
    public String Payment() {
        return "redirect:/pay"; // Trả về trang Payment
    }

    @GetMapping("/posst")
    public String post() {
        return "redirect:/post"; // Trả về trang Payment
    }

    @GetMapping("/client")
    public String Client() {
        return "redirect:/cli"; // Chuyển hướng đến /rsd để lấy dữ liệu
    }
}
