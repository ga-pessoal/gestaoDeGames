package estacio.br.model;

public class Game {
    private int id;
    private String titulo;
    private String nome_imagem;
    private String genero;
    private int id_genero;
    private double nota;


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

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

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
