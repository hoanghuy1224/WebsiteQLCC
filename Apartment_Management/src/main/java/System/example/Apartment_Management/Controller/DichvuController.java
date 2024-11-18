package System.example.Apartment_Management.Controller;

import System.example.Apartment_Management.Model.Apartment;
import System.example.Apartment_Management.Model.Dichvu;
import System.example.Apartment_Management.Service.DichvuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DichvuController {

    @Autowired
    private DichvuService dichvuService;

    @GetMapping("/dv")
    public String viewHomePage(Model model) {
        return findPaginated(1, "MaDichVu", "asc", model);
    }
    @GetMapping("/showDichvuu")
    public String showDichvu(Model model) {
        Dichvu dichvu = new Dichvu();
        model.addAttribute("dichvu", dichvu);
        return "Admin/addDichvu";
    }

    @PostMapping("/saveDichvu")
    public String saveDichvu(@ModelAttribute("dichvu") Dichvu dichvu) {
        dichvuService.add(dichvu);
        return "redirect:/dv";
    }

    @GetMapping("/Updatedichvu/{MaDichVu}")
    public String UpdateDichvu(@PathVariable(value = "MaDichVu") int MaDichVu, Model model) {
        Dichvu dichvu = dichvuService.findById(MaDichVu);
        model.addAttribute("dichvus", dichvu);
        return "Admin/updateDichvu";
    }

    @GetMapping("/deletedichvu/{MaDichVu}")
    public String deleteDichvu(@PathVariable(value = "MaDichVu") int MaDichVu) {
        this.dichvuService.deleteById(MaDichVu);
        return "redirect:/dv";

    }
    @GetMapping("/Dichvu/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Dichvu> page = dichvuService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Dichvu> listDichvu = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("lisDichvu", listDichvu);
        return "Admin/Service";
    }
}
