package System.example.Apartment_Management;

import System.example.Apartment_Management.Model.Resident;
import System.example.Apartment_Management.Service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import java.util.List;


@SpringBootApplication
public class ApartmentManagementApplication implements CommandLineRunner {

    @Autowired
    private ResidentService residentService;

    public static void main(String[] args) {
        SpringApplication.run(ApartmentManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Gọi phương thức findPaginated từ ResidentService
        int pageNo = 1;
        int pageSize = 5;
        String sortField = "hoTen";
        String sortDir = "asc";

        Page<Resident> page = residentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Resident> residentList = page.getContent();

        // In danh sách cư dân ra màn hình
        System.out.println("danh sach:");
        for (Resident resident : residentList) {
            System.out.println(resident);
        }
        System.out.println("listResident size: " + residentList.size());
    }
}
