package guru.springframework.spring5webapp.bootsrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("started in bootstrap");
    Publisher cda = new Publisher("CDA", "addressLineOne", "Warsaw", "Mazowieckie", "13-145");
    publisherRepository.save(cda);

    Author eric = new Author("Eric", "Evans");
    Book book = new Book("Title", "isbn");
    eric.getBooks().add(book);
    book.getAuthors().add(eric);

    book.setPublisher(cda);
    cda.getBooks().add(book);


    authorRepository.save(eric);
    bookRepository.save(book);
    publisherRepository.save(cda);

    Author rod = new Author("Rod", "Johnson");
    Book noEJB = new Book("J2EE without EJB", "213213");
    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);
    noEJB.setPublisher(cda);
    cda.getBooks().add(noEJB);

    authorRepository.save(rod);
    bookRepository.save(noEJB);
    publisherRepository.save(cda);

    System.out.println("Number of books = " + bookRepository.count());
    System.out.println("Number of Authors = " + authorRepository.count());
    System.out.println("Number of publishers = " + publisherRepository.count());
    System.out.println("Number of publisher's books = " + cda.getBooks().size());
  }
}
