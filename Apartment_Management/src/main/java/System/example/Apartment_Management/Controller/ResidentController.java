package System.example.Apartment_Management.Controller;

import System.example.Apartment_Management.Model.Apartment;
import System.example.Apartment_Management.Model.Resident;
import System.example.Apartment_Management.Service.ApartmentService;
import System.example.Apartment_Management.Service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/rsd")
    public String viewHomePage(Model model) {
        return findPaginated(1, "hoTen", "asc", model);
    }

    @GetMapping("/showRident")
    public String showNewResidentForm(Model model) {
        Resident resident = new Resident();
        model.addAttribute("resident", resident);
        List<Apartment> canHoList = apartmentService.getAll();
        model.addAttribute("canHoList", canHoList);
        return "Admin/addresident";
    }

    @PostMapping("/saveRident")
    public String saveResident(@ModelAttribute("resident") Resident resident) {
        residentService.saveResident(resident);
        return "redirect:/Resident";
    }

    @GetMapping("/showFormForUpdate/{maCuDan}")
    public String showFormForUpdate(@PathVariable(value = "maCuDan") int maCuDan, Model model) {
        Resident resident = residentService.getResident(maCuDan);
        model.addAttribute("resident", resident);
        List<Apartment> canHoList = apartmentService.getAll();
        model.addAttribute("canHoList", canHoList);
        return "Admin/updateresident";
    }

//    @GetMapping("/searchByName")
//    public String searchResidentsByName(@RequestParam("hoTen") String hoTen, Model model) {
//        List<Resident> residents = residentService.findByName(hoTen);
//        model.addAttribute("listResident", residents);
//        return "redirect:/rsd";
//    }

    @GetMapping("/deleteResident/{maCuDan}")
    public String deleteResident(@PathVariable(value = "maCuDan") int maCuDan) {
        this.residentService.deleteResident(maCuDan);
        return "redirect:/rsd";

    }

    @GetMapping("/resident/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Resident> page = residentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Resident> listResident = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listResident", listResident);
        return "Admin/Resident";
    }


}

