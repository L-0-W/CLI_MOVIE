package type.api.API.movie.dados.model;

public class MOVIE {

    private String nome;
    private double ratings;

    MOVIE(String nome, double ratings) {
        this.nome = nome;
        this.ratings = ratings;
    }

    public String getNome() {
        return this.nome;
    }

    public double getRatings() {
        return this.ratings;
    }
}
