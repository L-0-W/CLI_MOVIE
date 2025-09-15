package type.api.API.movie.interface_.dto;

import type.api.API.movie.dados.model.MOVIE;

public class movieDTO {

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

    public static movieDTO fromEntity(MOVIE movie) {
        movieDTO dto = new movieDTO();

        dto.setNome(movie.getNome());
        dto.setRatings(movie.getRatings());

        return dto;
    }
}
