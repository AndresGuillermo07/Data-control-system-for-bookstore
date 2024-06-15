package Pp.books_shop.repository;

import Pp.books_shop.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IBookRepository extends JpaRepository<Book, Integer> {
}
