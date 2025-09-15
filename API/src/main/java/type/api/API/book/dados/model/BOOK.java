package type.api.API.book.dados.model;

public class BOOK {

    private int book_id;
    private String isbn;
    private String title;
    private int publication_date;
    private String cover_image_url;
    private String authors_name;

    public BOOK(
        int book_id,
        String isbn,
        String title,
        int publication_date,
        String cover_image_url,
        String authors_name
    ) {
        this.book_id = book_id;
        this.isbn = isbn;
        this.title = title;
        this.publication_date = publication_date;
        this.cover_image_url = cover_image_url;
        this.authors_name = authors_name;
    }

    public String getTitle() {
        return this.title;
    }

    public int getBook_Id() {
        return this.book_id;
    }

    public String getISBN() {
        return this.isbn;
    }

    public int getPublicationDate() {
        return this.publication_date;
    }

    public String getCover_Image_Url() {
        return this.cover_image_url;
    }

    public String getAuthorsName() {
        return this.authors_name;
    }
}
