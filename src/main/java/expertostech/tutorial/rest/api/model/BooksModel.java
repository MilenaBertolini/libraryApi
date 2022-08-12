package expertostech.tutorial.rest.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.websocket.Decoder.BinaryStream;

@Entity(name = "books")
public class BooksModel {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false, length = 500)
    public String title;

    @Column(nullable = false, length = 300)
    public String author;

    @Column(nullable = false, length = 300)
    public String publisher;

    @Lob
    @Column(nullable = true)
    private byte[] image;

    @Column(nullable = false)
    public Integer publishYear;

    @Column(nullable = false, precision = 2, length = 10)
    public Double price;

    @Column(nullable = false, length = 10)
    public Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
