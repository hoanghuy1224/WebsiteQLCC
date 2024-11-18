package System.example.Apartment_Management.Controller;

import System.example.Apartment_Management.Model.Apartment;
import System.example.Apartment_Management.Model.Hopdong;
import System.example.Apartment_Management.Model.Resident;
import System.example.Apartment_Management.Service.ApartmentService;
import System.example.Apartment_Management.Service.HopdongService;
import System.example.Apartment_Management.Service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HopdongController {

    @Autowired
    private HopdongService hopdongService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ResidentService residentService;

    @GetMapping("/hd")
    public String viewHomePage(Model model) {
        return findPaginated(1, "MaHopDong", "asc", model);
    }

    @GetMapping("/showHopdong")
    public String showNewContractForm(Model model) {
        Hopdong hopdong = new Hopdong();
        model.addAttribute("hopdongg", hopdong);
        List<Apartment> apartments = apartmentService.getAll();
        model.addAttribute("apartments", apartments);
        List<Resident> residents = residentService.getAllResident();
        model.addAttribute("residents", residents);
        return "Admin/addcontract";
    }

    @PostMapping("/saveHopdong")
    public String saveContract(@ModelAttribute("hopdong") Hopdong hopdong) {
        hopdongService.save(hopdong);
        return "redirect:/Contract";
    }

    @GetMapping("/showFormContractUpdate/{maHopDong}")
    public String showFormForUpdate(@PathVariable(value = "maHopDong") int maHopDong, Model model) {
        Hopdong hopdong = hopdongService.findById(maHopDong);

        // Kiểm tra xem ngày bắt đầu và kết thúc có null không trước khi định dạng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedStartDate = (hopdong.getNgayBatDau() != null) ? hopdong.getNgayBatDau().format(formatter) : "";
        String formattedEndDate = (hopdong.getNgayKetThuc() != null) ? hopdong.getNgayKetThuc().format(formatter) : "";

        model.addAttribute("hopdong", hopdong);
        model.addAttribute("formattedStartDate", formattedStartDate);
        model.addAttribute("formattedEndDate", formattedEndDate);

        System.out.println("Ngay Bat Dau: " + hopdong.getNgayBatDau());
        System.out.println("Ngay Ket Thuc: " + hopdong.getNgayKetThuc());

        List<Apartment> apartments = apartmentService.getAll();
        model.addAttribute("apartments", apartments);
        List<Resident> residents = residentService.getAllResident();
        model.addAttribute("residents", residents);

        return "Admin/updateContract";
    }

    @GetMapping("/deleteContract/{maHopDong}")
    public String deleteHopdong(@PathVariable(value = "maHopDong") Hopdong maHopDong) {
        this.hopdongService.delete(maHopDong);
        return "redirect:/hd";

    }

    @GetMapping("/Contact/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Hopdong> page = hopdongService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Hopdong> hopdongs = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("hopdong", hopdongs);
        return "Admin/Contract";
    }


}
