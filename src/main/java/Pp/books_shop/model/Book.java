package Pp.books_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_book;
    private String book_name;
    private String author;
    private Double price;
    private Integer stock;

    public Book(String book_name, String author, Double price, Integer stock) {
        this.book_name = book_name;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return
                "\nid_book --> " + id_book +
                "\nbook_name --> " + book_name +
                "\nauthor --> " + author +
                "\nprice --> " + price +
                "\nstock --> " + stock;
    }
}
