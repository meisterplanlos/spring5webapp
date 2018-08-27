package guru.springframework.spring5webapp.bootstrap;




import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;




@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
  private AuthorRepository authorRepository;
  private BookRepository bookRepository;
  private PublisherRepository publisherRepository;



  public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository)
  {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }



  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
  {
    this.initData();
  }



  private void initData()
  {
    // Harper Collins
    Publisher harperCollins = new Publisher("Harper Collins", "195 Broadway, New York, NY 10007, USA");
    this.publisherRepository.save(harperCollins);

    // Worx
    Publisher worx = new Publisher("Worx", "42 Dreamy Hills, Backwater, NW 0815, Nowheristan");
    this.publisherRepository.save(worx);


    // Eric
    Author eric = new Author("Eric", "Evans");
    Book ddd = new Book("Domain Driven Design", "1234", harperCollins);
    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);
    this.authorRepository.save(eric);
    this.bookRepository.save(ddd);

    //Rod
    Author rod = new Author("Rod", "Johnson");
    Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);
    this.authorRepository.save(rod);
    this.bookRepository.save(noEJB);
  }
}
