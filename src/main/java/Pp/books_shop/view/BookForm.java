package Pp.books_shop.view;

import Pp.books_shop.model.Book;
import Pp.books_shop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

@Component
public class BookForm extends JFrame {
    BookService bookService;
    private JPanel panel;
    private JTable booksTable;

    // BUTTONS
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;

    // TEXT FIELDS
    private JTextField idText;
    private JTextField bookTitle;
    private JTextField authorText;
    private JTextField priceText;
    private JTextField stockText;
    private DefaultTableModel tableModel;

    @Autowired
    public BookForm(BookService bookService){
        this.bookService = bookService;
        startForm();
        addButton.addActionListener(e -> addBook());
        booksTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadSelectedBook();
            }
        });
        modifyButton.addActionListener(e->modifyBook()); {
        }
        deleteButton.addActionListener(e-> deleteBook()); {
        };
    }

    private void startForm(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - getWidth()/ 2);
        int y = (screenSize.height = getHeight() / 2);
        setLocation(x, y);
    }

    private void addBook(){
        // read form values
        if(bookTitle.getText().isEmpty()){
            showMessage("Please type the book name");
            bookTitle.requestFocusInWindow();
            return;
        }
        var bookName =  bookTitle.getText();
        var author = authorText.getText();
        var price = Double.parseDouble(priceText.getText());
        var stock = Integer.parseInt(stockText.getText());

        var book =  new Book(bookName, author, price, stock);
        this.bookService.saveBook(book);
        showMessage("Book added successfully");
        clearForm();
        listBooks();
    }

    public void loadSelectedBook(){
        var row = booksTable.getSelectedRow();
        if(row != -1) {
            String idBook = booksTable.getValueAt(row, 0).toString();

            idText.setText(idBook);
            String bookText = booksTable.getValueAt(row, 1).toString();
            bookTitle.setText(bookText);
            String author = booksTable.getValueAt(row, 2).toString();
            authorText.setText(author);
            String price = booksTable.getValueAt(row, 3).toString();
            priceText.setText(price);
            String stock = booksTable.getValueAt(row, 4).toString();
            stockText.setText(stock);
        }
    }

    private void modifyBook(){
        if(idText.getText().isEmpty()){
            showMessage("Please you must select the id of the book");
        }else{
            if(bookTitle.getText().isEmpty()){
                showMessage("Please type the book name");
                bookTitle.requestFocusInWindow();
                return;
            }
            var idBook =  Integer.parseInt(idText.getText());
            var bookName = bookTitle.getText();
            var author = authorText.getText();
            var price = Double.parseDouble(priceText.getText());
            var stock = Integer.parseInt(stockText.getText());
            var book =  new Book(idBook,bookName, author, price, stock);
            this.bookService.saveBook(book);
            showMessage("Book modified successfully");
            clearForm();
            listBooks();
        }
    }

    private void deleteBook(){
        if(idText.getText().isEmpty()){
            showMessage("Please select the id of the book");
        }else{
            var idBook =  Integer.parseInt(idText.getText());
            this.bookService.deleteBook(bookService.getBookById(idBook));
            showMessage("Book deleted successfully");
            clearForm();
            listBooks();
        }
    }

    private void showMessage(String text){
        JOptionPane.showMessageDialog(this, text);
    }

    private void clearForm(){
        bookTitle.setText("");
        authorText.setText("");
        priceText.setText("");
        stockText.setText("");
    }

    private void createUIComponents() {

        idText = new JTextField("");
        idText.setVisible(false);

        this.tableModel = new DefaultTableModel(0,5);
        String[] headboard = {"Id","Book","Author","Price","Stock"};
        this.tableModel.setColumnIdentifiers(headboard);

        this.booksTable = new JTable(this.tableModel);
        listBooks();

    }

    private void listBooks(){
       this.tableModel.setRowCount(0);
       var books = bookService.getAllBooks();
       books.forEach((book) ->{
           Object[] row = {
                   book.getId_book(),
                   book.getBook_name(),
                   book.getAuthor(),
                   book.getPrice(),
                   book.getStock()
           };
           this.tableModel.addRow(row);
       });
    }

}
