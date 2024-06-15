package Pp.books_shop;

import Pp.books_shop.view.BookForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class BooksShopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new SpringApplicationBuilder(BooksShopApplication.class)
						.headless(false)
						.web(WebApplicationType.NONE).run(args);

		EventQueue.invokeLater(()-> {

			BookForm bookForm = context.getBean(BookForm.class);
			bookForm.setVisible(true);
		});
	}

}
