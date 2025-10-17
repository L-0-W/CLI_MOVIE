package type.api.CLI.WIDGETS.JSON_OBJECTS;

public class BOOK {

    private int book_id;
    private String isbn;
    private String title;
    private String publication_date;
    private String cover_image_url;
    private String authors_name;

    public BOOK() {}

    public String getTitle() {
        return title;
    }

    public int getBook_Id() {
        return book_id;
    }

    public String getISBN() {
        return isbn;
    }

    public String getPublicationDate() {
        return publication_date;
    }

    public String getCover_Image_Url() {
        return cover_image_url;
    }

    public String getAuthorsName() {
        return authors_name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBook_Id(int book_id) {
        this.book_id = book_id;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public void setPublicationDate(String publication_date) {
        this.publication_date = publication_date;
    }

    public void setCover_Image_Url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }

    public void setAuthorsName(String authors_name) {
        this.authors_name = authors_name;
    }
}
