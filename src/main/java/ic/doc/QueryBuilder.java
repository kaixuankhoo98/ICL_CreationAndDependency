package ic.doc;

import java.util.List;

public class QueryBuilder {
  private String name1 = null;
  private String name2 = null;
  private String title = null;
  private Integer date1 = null;
  private Integer date2 = null;

  private QueryBuilder() {}

  public static QueryBuilder aQuery() {
    return new QueryBuilder();
  }

  public BookSearchQuery build() {
    return new BookSearchQuery(name1, name2, title, date1, date2);
  }

  public QueryBuilder withFirstName(String name1) {
    this.name1 = name1;
    return this;
  }

  public QueryBuilder withLastName(String name2) {
    this.name2 = name2;
    return this;
  }

  public QueryBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public QueryBuilder withDateAfter(Integer date1) {
    this.date1 = date1;
    return this;
  }

  public QueryBuilder withDateBefore(Integer date2) {
    this.date2 = date2;
    return this;
  }
}
