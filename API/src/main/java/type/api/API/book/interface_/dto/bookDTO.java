package type.api.API.book.interface_.dto;

import type.api.API.book.dados.model.BOOK;

public class bookDTO {

    private String nome;
    private double ratings;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public String getNome() {
        return this.nome;
    }

    public double getRatings() {
        return this.ratings;
    }

    public static bookDTO fromEntity(BOOK book) {
        bookDTO dto = new bookDTO();

        //dto.setNome(book.getNome());
        //dto.setRatings(book.getRatings());

        return dto;
    }
}
