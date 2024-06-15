package Pp.books_shop.service;

import Pp.books_shop.model.Book;

import java.util.List;

public interface IBookService {

    public List<Book> getAllBooks();

    public Book getBookById(Integer id);

    public void saveBook(Book book);

    public void deleteBook(Book book);

}
