package com.example.bookshop;

import com.example.bookshop.dao.AuthorDao;
import com.example.bookshop.dao.BookDao;
import com.example.bookshop.dao.GenreDao;
import com.example.bookshop.dao.PublisherDao;
import com.example.bookshop.entity.Author;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.Genre;
import com.example.bookshop.entity.Publisher;
import com.example.bookshop.util.IsbnGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@SpringBootApplication
public class BookShopApplication {
    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final PublisherDao publisherDao;

    @Bean @Transactional
    public ApplicationRunner runner(){
        return r -> {
            Author author1 = new Author("Charles Dicken","charles@gamil.com");
            Author author2 = new Author("Thomas Hardy", "thomas@gmail.com");

            Publisher publisher1 = new Publisher("New Era","newera@gamil.com");
            Publisher publisher2 = new Publisher("Sun","sun@gmail.com");

            Genre genre1 = new Genre("Tragedy");
            Genre genre2 = new Genre("Adventure");

            Book book1 = new Book(1, IsbnGenerator.generate(),
                    "Oliver Twist","Excellent",50.3,20,
                    "https://source.unsplash.com/400x300/?flower");
            Book book2 = new Book(2, IsbnGenerator.generate(),
                    "Great Expectation","Good Choice",25.3,20,
                    "https://source.unsplash.com/400x300/?nature");
            Book book3 = new Book(3, IsbnGenerator.generate(),
                    "Bleak House","Nice",22.3,20,
                    "https://source.unsplash.com/400x300/?ocean");
            Book book4 = new Book(4, IsbnGenerator.generate(),
                    "Under The Greenwood Tree","Excellent",50.3,20,
                    "https://source.unsplash.com/400x300/?mountain");
            Book book5 = new Book(5, IsbnGenerator.generate(),
                    "Return of the Native","Excellent",25.3,20,
                    "https://source.unsplash.com/400x300/?bird");
            Book book6 = new Book(6, IsbnGenerator.generate(),
                    "Far from the Maddening Crowd","Excellent",25.3,20,
                    "https://source.unsplash.com/400x300/?rabbit");

            //Mapping
            author1.addBook(book1);
            author1.addBook(book2);
            author1.addBook(book3);
            author2.addBook(book4);
            author2.addBook(book5);
            author2.addBook(book6);


            Publisher pub1 = publisherDao.save(publisher1);
            pub1.addBook(book1);
            pub1.addBook(book2);
            pub1.addBook(book3);

            Publisher pub2 = publisherDao.save(publisher2);
            pub2.addBook(book4);
            pub2.addBook(book5);
            pub2.addBook(book6);

            Genre gen1 = genreDao.save(genre1);
            book1.addGenre(gen1);
            book2.addGenre(gen1);
            book3.addGenre(gen1);

            Genre gen2 = genreDao.save(genre2);
            book4.addGenre(gen2);
            book5.addGenre(gen2);
            book6.addGenre(gen2);

            authorDao.save(author1);
            authorDao.save(author2);



        };


    }

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

}
