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
    private boolean colheitaFeita;
    private boolean impostosArrecadados;

    public Cidade(String nome, Jogador dono) {
        this.nome = nome;
        this.dono = dono;
        this.anoAtual = 1400;
        this.florins = 1000;
        this.terras = 10000;
        this.graos = 500;
        this.servos = 200;
        this.taxaImposto = 10;
        this.nivelJustica = "Moderada";
        this.soldados = 0;
        this.construcoes = 0;
        this.colheitaFeita = false;
        this.impostosArrecadados = false;
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

    public boolean isColheitaFeita() { return colheitaFeita; }
    public void marcarColheitaFeita() { this.colheitaFeita = true; }
    public boolean isImpostosArrecadados() { return impostosArrecadados; }
    public void marcarImpostosArrecadados() { this.impostosArrecadados = true; }

    public void avancarAno() {
        this.anoAtual++;
        this.colheitaFeita = false;
        this.impostosArrecadados = false;
    }

    public void adicionarFlorins(int qtd) { if (qtd > 0) this.florins += qtd; }
    public boolean removerFlorins(int qtd) {
        if (qtd <= 0 || qtd > this.florins) return false;
        this.florins -= qtd;
        return true;
    }

    public void adicionarTerras(int qtd) { if (qtd > 0) this.terras += qtd; }
    public boolean removerTerras(int qtd) {
        if (qtd <= 0 || qtd > this.terras) return false;
        this.terras -= qtd;
        return true;
    }

    public void adicionarGraos(int qtd) { if (qtd > 0) this.graos += qtd; }
    public boolean removerGraos(int qtd) {
        if (qtd <= 0 || qtd > this.graos) return false;
        this.graos -= qtd;
        return true;
    }

    public void adicionarServos(int qtd) { if (qtd > 0) this.servos += qtd; }
    public boolean removerServos(int qtd) {
        if (qtd <= 0 || qtd > this.servos) return false;
        this.servos -= qtd;
        return true;
    }

    public void adicionarSoldados(int qtd) { if (qtd > 0) this.soldados += qtd; }
    public boolean removerSoldados(int qtd) {
        if (qtd <= 0 || qtd > this.soldados) return false;
        this.soldados -= qtd;
        return true;
    }

    public void adicionarConstrucoes(int qtd) { if (qtd > 0) this.construcoes += qtd; }
    public boolean removerConstrucoes(int qtd) {
        if (qtd <= 0 || qtd > this.construcoes) return false;
        this.construcoes -= qtd;
        return true;
    }

    public void exibirStatus() {
        System.out.println("----------------------------------------");
        System.out.println("Cidade: " + nome);
        System.out.println("Governante: " + dono.getNome() + " (" + dono.getTitulo() + ")");
        System.out.println("Ano: " + anoAtual);
        System.out.println("Florins: " + florins);
        System.out.println("Terras: " + terras + " hectares");
        System.out.println("Graos em estoque: " + graos + " unidades");
        System.out.println("Servos: " + servos);
        System.out.println("Taxa de imposto: " + taxaImposto + "%");
        System.out.println("Nivel de justica: " + nivelJustica);
        System.out.println("Soldados: " + soldados);
        System.out.println("Construcoes: " + construcoes);
        System.out.println("Colheita deste ano: " + (colheitaFeita ? "feita" : "pendente"));
        System.out.println("Impostos deste ano: " + (impostosArrecadados ? "arrecadados" : "pendentes"));
        System.out.println("----------------------------------------");
    }
}