package expertostech.tutorial.rest.api.repository;

import expertostech.tutorial.rest.api.model.ClientsModel;
import org.springframework.data.repository.CrudRepository;

public interface ClientsRepository extends CrudRepository<ClientsModel, Integer> {
}