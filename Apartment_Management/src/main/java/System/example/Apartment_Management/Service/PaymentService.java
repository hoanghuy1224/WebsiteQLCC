package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Apartment;
import System.example.Apartment_Management.Model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPayment(int id);
    Payment deletePayment(int id);
    Payment SavePayment(Payment payment);
    Page<Payment> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
