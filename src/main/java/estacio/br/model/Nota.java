package estacio.br.model;

public class Nota {
    private int id;
    private int nota;
    private int id_usuario;
    private int id_game;

    public int getIdGame() {
        return id_game;
    }

    public void setIdGame(int id_game) {
        this.id_game = id_game;
    }

    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
