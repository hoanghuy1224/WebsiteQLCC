package System.example.Apartment_Management.Controller;

import System.example.Apartment_Management.Model.Apartment;
import System.example.Apartment_Management.Model.Resident;
import System.example.Apartment_Management.Model.Staff;
import System.example.Apartment_Management.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/sta")
    public String viewHomePage(Model model) {
        return findPaginated(1, "HoTen", "asc", model);
    }

    @GetMapping("/showstaff")
    public String showStaff(Model model) {
        Staff staff = new Staff();
        model.addAttribute("staff", staff);
        return "Admin/addStaff";
    }

    @PostMapping("/saveStaff")
    public String saveStaff(@ModelAttribute("staff") Staff staff) {
        staffService.addStaff(staff);
        return "redirect:/sta";
    }

    @GetMapping("/UpdateStaff/{MaNhanVien}")
    public String showFormForUpdate(@PathVariable(value = "MaNhanVien") int MaNhanVien, Model model) {
        Staff staff = staffService.getStaffById(MaNhanVien);
        model.addAttribute("staff", staff);
        return "Admin/updateStaff";
    }

    @GetMapping("/deleteStaff/{MaNhanVien}")
    public String deleteApartment(@PathVariable(value = "MaNhanVien") int MaNhanVien) {
        this.staffService.deleteStaff(MaNhanVien);
        return "redirect:/sta";

    }

    @GetMapping("/SearchStaff")
    public String SearchStaff(@RequestParam(value = "HoTen") String hoten, Model model) {
        List<Staff> list = staffService.SearchStaff(hoten);
        model.addAttribute("liststaff", list);
        return "Admin/Staff";
    }

    @GetMapping("/staff/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Staff> page = staffService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Staff> staffList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("liststaff", staffList);
        return "Admin/Staff";
    }
}
