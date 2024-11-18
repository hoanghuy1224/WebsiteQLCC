package System.example.Apartment_Management.Service;

import System.example.Apartment_Management.Model.Client;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienService {
    Client getClient(int id);
    List<Client> getAllClients();
    Client addClient(Client client);
    void deleteClient(int id);
    Client updateClient(Client client);
    Page<Client> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
