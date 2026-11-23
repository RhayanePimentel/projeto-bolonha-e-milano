public class Cidade {
    private String nome;
    private int anoAtual;
    private Jogador dono;
    private int florins;
    private int terras;
    private int graos;
    private int servos;
    private int taxaImposto;
    private String nivelJustica;
    private int soldados;
    private int construcoes;

    public Cidade(String nome, Jogador dono) {
        this.nome = nome;
        this.anoAtual = 1400; //[cite: 18, 105]
        this.dono = dono;
        this.florins = 1000;      //[cite: 29, 105]
        this.terras = 10000;    //[cite: 29, 105]
        this.graos = 500;       //[cite: 29, 105]
        this.servos = 200;      //[cite: 45, 105]
        this.taxaImposto = 10;  //[cite: 64, 105]
        this.nivelJustica = "Moderada"; //[cite: 64, 106]
        this.soldados = 0;      //[cite: 84, 106]
        this.construcoes = 0;   //[cite: 84, 106]
    }

    public String getNome() { return nome; }
    public int getAnoAtual() { return anoAtual; }
    public Jogador getDono() { return dono; }
    public int getFlorins() { return florins; }
    public int getTerras() { return terras; }
    public int getGraos() { return graos; }
    public int getServos() { return servos; }
    public int getTaxaImposto() { return taxaImposto; }
    public void setTaxaImposto(int taxaImposto) { this.taxaImposto = taxaImposto; }
    public String getNivelJustica() { return nivelJustica; }
    public void setNivelJustica(String nivelJustica) { this.nivelJustica = nivelJustica; }
    public int getSoldados() { return soldados; }
    public int getConstrucoes() { return construcoes; }

    public void avancarAno() {
        this.anoAtual++;
    }

    // Métodos seguros para recursos acumuláveis[cite: 106]
    public void adicionarFlorins(int qtd) { this.florins += qtd; }
    public boolean removerFlorins(int qtd) {
        if (this.florins >= qtd) { this.florins -= qtd; return true; }
        return false;
    }

    public void adicionarTerras(int qtd) { this.terras += qtd; }
    public boolean removerTerras(int qtd) {
        if (this.terras >= qtd) { this.terras -= qtd; return true; }
        return false;
    }

    public void adicionarGraos(int qtd) { this.graos += qtd; }
    public boolean removerGraos(int qtd) {
        if (this.graos >= qtd) { this.graos -= qtd; return true; }
        return false;
    }

    public void adicionarServos(int qtd) { this.servos += qtd; }
    public boolean removerServos(int qtd) {
        if (this.servos >= qtd) { this.servos -= qtd; return true; }
        return false;
    }

    public void adicionarSoldados(int qtd) { this.soldados += qtd; }
    public boolean removerSoldados(int qtd) {
        if (this.soldados >= qtd) { this.soldados -= qtd; return true; }
        return false;
    }

    public void adicionarConstrucoes(int qtd) { this.construcoes += qtd; }
    public boolean removerConstrucoes(int qtd) {
        if (this.construcoes >= qtd) { this.construcoes -= qtd; return true; }
        return false;
    }

    public void exibirStatus() {
        System.out.println("\n--- STATUS DA CIDADE ---");
        System.out.println("Cidade: " + nome + " (Ano: " + anoAtual + ")");
        System.out.println("Governante: " + dono.getNome() + " [" + dono.getTitulo() + "]");
        System.out.println("Florins (Dinheiro): " + florins);
        System.out.println("Terras: " + terras + " hectares");
        System.out.println("Grãos (Alimento): " + graos);
        System.out.println("Servos (População): " + servos);
        System.out.println("Taxa de Imposto: " + taxaImposto + "%");
        System.out.println("Nível de Justiça: " + nivelJustica);
        System.out.println("Soldados: " + soldados);
        System.out.println("Construções: " + construcoes);
        System.out.println("------------------------\n");
    }
}