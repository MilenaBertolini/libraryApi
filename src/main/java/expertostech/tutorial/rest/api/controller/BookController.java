package expertostech.tutorial.rest.api.controller;

import expertostech.tutorial.rest.api.entity.Book;
import expertostech.tutorial.rest.api.entity.Buys;
import expertostech.tutorial.rest.api.entity.Sell;
import expertostech.tutorial.rest.api.model.BooksModel;
import expertostech.tutorial.rest.api.model.BuysModel;
import expertostech.tutorial.rest.api.model.ClientsModel;
import expertostech.tutorial.rest.api.repository.BookRepository;
import expertostech.tutorial.rest.api.repository.BuyRepository;
import expertostech.tutorial.rest.api.repository.ClientsRepository;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BuyRepository buyRepository;

    @Autowired
    private ClientsRepository clientRepository;

    @GetMapping(path = "/api/book/{id}")
    public ResponseEntity<BooksModel> getById(@PathVariable("id") Integer id) {
        return bookRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/api/book/save")
    public BooksModel save(@RequestBody Book book) {
        BooksModel model = new BooksModel();
        model.setAuthor(book.getAuthor());
        model.setImage(DatatypeConverter.parseBase64Binary(book.getImage()));
        model.setPublisher(book.getPublisher());
        model.setTitle(book.getTitle());
        model.setPublishYear(book.getPublishYear());
        model.setQuantity(book.getQuantity());
        model.setPrice(book.getPrice());
        
        return bookRepository.save(model);
    }

    @PostMapping(path = "/api/book/sell")
    public ResponseEntity<Object> sellBooks(@RequestBody Sell sell){
        if(sell.getBuy().getQuantity() < 0){
            return ResponseEntity.badRequest().body("Quantity must be bigger then 0!");
        }

        if(!bookRepository.findById(sell.getBuy().getIdProduct()).isPresent()){
            return ResponseEntity.badRequest().body("Book doens't exists!");
        }
        
        BooksModel model = bookRepository.findById(sell.getBuy().getIdProduct()).get();

        if(model.getQuantity() <= 0){
            return ResponseEntity.badRequest().body("There is no stock for this book!");
        }
        else if(model.getQuantity() - sell.getBuy().getQuantity() < 0){
            return ResponseEntity.badRequest().body("There is only " + model.getQuantity() + " of this book!");
        }

        model.setQuantity(model.getQuantity() - sell.getBuy().getQuantity());

        ClientsModel clientModel = new ClientsModel();
        clientModel.setCpf(sell.getClient().getCpf());
        clientModel.setNome(sell.getClient().getNome());
        clientModel = clientRepository.save(clientModel);
        
        BuysModel buyModel = new BuysModel();
        buyModel.setIdProduct(model.getId());
        buyModel.setPrice(sell.getBuy().getPrice());
        buyModel.setQuantity(sell.getBuy().getQuantity());
        buyModel.setIdClient(clientModel.getId());
        buyModel.setSell(true);
        buyModel.setBuy(false);
        buyRepository.save(buyModel);
        
        return ResponseEntity.ok().body(bookRepository.save(model));
    }

    @PostMapping(path = "/api/book/buy")
    public ResponseEntity<Object> buyBooks(@RequestBody Buys buy){
        if(buy.getQuantity() < 0){
            return ResponseEntity.badRequest().body("Quantity must be bigger then 0!");
        }

        if(!bookRepository.findById(buy.getIdProduct()).isPresent()){
            return ResponseEntity.badRequest().body("Book doens't exists! Create the book then buy more units!");
        }
        
        BooksModel model = bookRepository.findById(buy.getIdProduct()).get();

        model.setQuantity(model.getQuantity() + buy.getQuantity());

        BuysModel buyModel = new BuysModel();
        buyModel.setIdProduct(model.getId());
        buyModel.setPrice(buy.getPrice());
        buyModel.setQuantity(buy.getQuantity());
        buyModel.setIdClient(9999);
        buyModel.setSell(false);
        buyModel.setBuy(true);
        buyRepository.save(buyModel);
        
        return ResponseEntity.ok().body(bookRepository.save(model));
    }

}
