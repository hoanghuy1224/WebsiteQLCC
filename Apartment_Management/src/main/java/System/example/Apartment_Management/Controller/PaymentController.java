package System.example.Apartment_Management.Controller;

import System.example.Apartment_Management.Model.Apartment;
import System.example.Apartment_Management.Model.Payment;
import System.example.Apartment_Management.Model.Resident;
import System.example.Apartment_Management.Model.Dichvu;
import System.example.Apartment_Management.Service.ApartmentService;
import System.example.Apartment_Management.Service.DichvuService;
import System.example.Apartment_Management.Service.PaymentService;
import System.example.Apartment_Management.Service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private DichvuService dichvuService;

    @GetMapping("/pay")
    public String viewHomePage(Model model) {
        return findPaginated(1, "MaThanhToan", "asc", model);
    }

    @GetMapping("/showPayment")
    public String showPayment(Model model) {
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        List<Resident> residents = residentService.getAllResident();
        model.addAttribute("residents", residents);
        List<Dichvu> dichvu = dichvuService.findAll();
        model.addAttribute("dichvu", dichvu);

        return "Admin/addPayment";
    }

    @GetMapping("/UpdatePayment/{MaThanhToan}")
    public String UpdatePayment(@PathVariable(value = "MaThanhToan") int MaThanhToan, Model model) {
        Payment payment = paymentService.getPayment(MaThanhToan);

        if (payment == null) {
            return "redirect:/error";
        }
        model.addAttribute("payment", payment);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedDate = (payment.getNgayThanhToan() != null) ? payment.getNgayThanhToan().format(formatter) : "";
        model.addAttribute("formattedStartDate", formattedDate);

        List<Resident> residents = residentService.getAllResident();
        model.addAttribute("residents", residents);
        List<Dichvu> dichvu = dichvuService.findAll();
        model.addAttribute("dichvu", dichvu);

        return "Admin/updatePayment";
    }


    @GetMapping("/deletePayment/{MaThanhToan}")
    public String deleteApartment(@PathVariable(value = "MaThanhToan") int MaThanhToan) {
        this.paymentService.deletePayment(MaThanhToan);
        return "redirect:/pay";

    }

    @PostMapping("/savePayment")
    public String savePayment(@ModelAttribute("payment") Payment payment) {
        paymentService.SavePayment(payment);
        return "redirect:/pay";
    }


    @GetMapping("/payment/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Payment> page = paymentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Payment> paymentList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("lispayment", paymentList);
        return "Admin/Payment";
    }

}
