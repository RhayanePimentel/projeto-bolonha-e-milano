public class Jogador {
    private String nome;
    private String titulo;

    public Jogador(String nome) {
        this.nome = nome;
        this.titulo = "Cavaleiro"; // Começa sempre como Cavaleiro[cite: 18, 21]
    }

    public String getNome() {
        return nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}