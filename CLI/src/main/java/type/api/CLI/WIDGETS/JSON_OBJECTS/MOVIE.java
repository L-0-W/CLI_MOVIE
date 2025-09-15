package type.api.CLI.WIDGETS.JSON_OBJECTS;

public class MOVIE {

    private String nome;
    private double preco;

    public MOVIE() {}

    public String getNome() {
        return this.nome;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
