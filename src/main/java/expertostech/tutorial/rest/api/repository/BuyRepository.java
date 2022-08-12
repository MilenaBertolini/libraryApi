package expertostech.tutorial.rest.api.repository;

import expertostech.tutorial.rest.api.model.BuysModel;
import org.springframework.data.repository.CrudRepository;

public interface BuyRepository extends CrudRepository<BuysModel, Integer> {
}
