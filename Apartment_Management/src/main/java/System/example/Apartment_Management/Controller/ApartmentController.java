package System.example.Apartment_Management.Controller;

import System.example.Apartment_Management.Model.Apartment;
import System.example.Apartment_Management.Service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/apm")
    public String viewHomePage(Model model) {
        return findPaginated(1, "soCanHo", "asc", model);
    }


    @GetMapping("/showApartment")
    public String showApartment(Model model) {
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        return "Admin/addApartment";
    }

    @PostMapping("/saveApartment")
    public String saveApartment(@ModelAttribute("apartment") Apartment apartment) {
        apartmentService.add(apartment);
        return "redirect:/apm";
    }

    @GetMapping("/showFormUpdate/{maCanHo}")
    public String showFormForUpdate(@PathVariable(value = "maCanHo") int maCanHo, Model model) {
        Apartment apartment = apartmentService.getById(maCanHo);
        model.addAttribute("apartment", apartment);
        return "Admin/updatApartment";
    }

    @GetMapping("/deleteApartment/{maCanHo}")
    public String deleteApartment(@PathVariable(value = "maCanHo") int maCanHo) {
        this.apartmentService.delete(maCanHo);
        return "redirect:/apm";

    }

    @GetMapping("/apartment/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Apartment> page = apartmentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Apartment> lisApartment = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("lisApartment", lisApartment);
        return "Admin/Apartment";
    }

}
