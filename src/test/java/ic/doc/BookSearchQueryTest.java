package ic.doc;

import static ic.doc.QueryBuilder.aQuery;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import ic.doc.catalogues.Catalogue;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class BookSearchQueryTest {

  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  Catalogue britishLibraryCatalogue = context.mock(Catalogue.class);

  @Test
  public void searchesForBooksInMockCatalogueByAuthorSurname() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(britishLibraryCatalogue).searchFor("LASTNAME='dickens' ");
          }
        });
    List<Book> books = aQuery().withLastName("dickens").build().execute(britishLibraryCatalogue);
  }

  @Test
  public void searchesForBooksInMockCatalogueByAuthorFirstName() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(britishLibraryCatalogue).searchFor("FIRSTNAME='Jane' ");
          }
        });
    List<Book> books = aQuery().withFirstName("Jane").build().execute(britishLibraryCatalogue);
  }

  @Test
  public void searchesForBooksInMockCatalogueByTitle() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(britishLibraryCatalogue).searchFor("TITLECONTAINS(Two Cities) ");
          }
        });
    List<Book> books = aQuery().withTitle("Two Cities").build().execute(britishLibraryCatalogue);
  }

  @Test
  public void searchesForBooksInMockCatalogueBeforeGivenPublicationYear() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(britishLibraryCatalogue).searchFor("PUBLISHEDBEFORE(1700) ");
          }
        });
    List<Book> books = aQuery().withDateBefore(1700).build().execute(britishLibraryCatalogue);
  }

  @Test
  public void searchesForBooksInMockCatalogueAfterGivenPublicationYear() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(britishLibraryCatalogue).searchFor("PUBLISHEDAFTER(1950) ");
          }
        });
    List<Book> books = aQuery().withDateAfter(1950).build().execute(britishLibraryCatalogue);
  }

  @Test
  public void searchesForBooksInMockCatalogueWithCombinationOfParameters() {
    context.checking(
        new Expectations() {
          {
            exactly(1)
                .of(britishLibraryCatalogue)
                .searchFor("LASTNAME='dickens' PUBLISHEDBEFORE(1840) ");
          }
        });
    List<Book> books =
        aQuery()
            .withLastName("dickens")
            .withDateBefore(1840)
            .build()
            .execute(britishLibraryCatalogue);
  }

  @Test
  public void searchesForBooksInMockCatalogueWithCombinationOfTitleAndOtherParameters() {
    context.checking(
        new Expectations() {
          {
            exactly(1)
                .of(britishLibraryCatalogue)
                .searchFor("TITLECONTAINS(of) PUBLISHEDAFTER(1800) PUBLISHEDBEFORE(2000) ");
          }
        });
    List<Book> books =
        aQuery()
            .withTitle("of")
            .withDateAfter(1800)
            .withDateBefore(2000)
            .build()
            .execute(britishLibraryCatalogue);
  }
}
