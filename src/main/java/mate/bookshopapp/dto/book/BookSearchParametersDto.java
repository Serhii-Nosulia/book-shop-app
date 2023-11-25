package mate.bookshopapp.dto.book;

public record BookSearchParametersDto(String[] title,
                                      String[] author,
                                      String[] isbn) {
}
