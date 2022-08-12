package expertostech.tutorial.rest.api.repository;

import expertostech.tutorial.rest.api.model.BooksModel;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BooksModel, Integer> {
}
