public class Construcoes {
    private final int PRECO_SOLDADO = 5;      //[cite: 109]
    private final int PRECO_CONSTRUCAO = 20;  //[cite: 109]

    public boolean recrutarSoldados(Cidade c, int qtd) {
        if (qtd <= 0) return false;
        int custoFlorins = qtd * PRECO_SOLDADO;
        int servosNecessarios = qtd * 1; // 1 servo por soldado[cite: 109]

        // Valida os dois recursos ANTES de alterar qualquer um[cite: 109]
        if (c.getFlorins() < custoFlorins || c.getServos() < servosNecessarios) {
            return false;
        }

        c.removerFlorins(custoFlorins);
        c.removerServos(servosNecessarios);
        c.adicionarSoldados(qtd);
        return true;
    }

    public boolean construir(Cidade c, int qtd) {
        if (qtd <= 0) return false;
        int custo = qtd * PRECO_CONSTRUCAO;
        if (c.removerFlorins(custo)) {
            c.adicionarConstrucoes(qtd);
            return true;
        }
        return false;
    }
}