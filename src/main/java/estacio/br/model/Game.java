package estacio.br.model;

public class Game {
    private int id;
    private String titulo;
    private String nome_imagem;
    private int id_genero;

    public int getIdGenero() {
        return id_genero;
    }

    public void setIdGenero(int id_genero) {
        this.id_genero = id_genero;
    }

    public String getNomeImagem() {
        return nome_imagem;
    }

    public void setNomeImagem(String url_image) {
        this.nome_imagem = url_image;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
