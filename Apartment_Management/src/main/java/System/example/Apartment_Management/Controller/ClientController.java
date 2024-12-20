package System.example.Apartment_Management.Controller;

import System.example.Apartment_Management.Model.Client;
import System.example.Apartment_Management.Service.ClienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClienService clienService;

    @GetMapping("/cli")
    public String viewHomePage(Model model) {
        return findPaginated(1, "MaKhachHang", "asc", model);
    }

    @PostMapping("/saveClient")
    public String saveClient(@ModelAttribute("client") Client client, Model model) {
        clienService.addClient(client);
        return "redirect:/";
    }

    @PostMapping("/updateclient")
    public String UpdateClient(@ModelAttribute("client") Client client, Model model) {
        clienService.addClient(client);
        return "redirect:/cli";
    }

    @GetMapping("/UpdateClient/{MaKhachHang}")
    public String showFormForUpdate(@PathVariable(value = "MaKhachHang") int MaKhachHang, Model model) {
        Client client = clienService.getClient(MaKhachHang);
        model.addAttribute("client", client);
        return "Admin/updateClient";
    }

    @GetMapping("/deleteClient/{MaKhachHang}")
    public String deleteClient(@PathVariable(value = "MaKhachHang") int MaKhachHang) {
        this.clienService.deleteClient(MaKhachHang);
        return "redirect:/cli";

    }

    @GetMapping("/searchByHoTen")
    public String searchClientsByHoTen(@RequestParam("hoTen") String hoTen, Model model) {
        List<Client> clients = clienService.searchClientsByHoTen(hoTen);
        model.addAttribute("listclient", clients);
        return "Admin/Client";
    }

    @GetMapping("/client/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Client> page = clienService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Client> clientList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listclient", clientList);
        return "Admin/Client";
    }
}
