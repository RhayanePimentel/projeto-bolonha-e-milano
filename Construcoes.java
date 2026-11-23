public class Construcoes {
    public static final int CUSTO_SOLDADO_FLORINS = 5;
    public static final int CUSTO_SOLDADO_SERVOS = 1;
    public static final int CUSTO_CONSTRUCAO_FLORINS = 20;

    public boolean recrutarSoldados(Cidade cidade, int qtd) {
        if (qtd <= 0) return false;
        int custoFlorins = qtd * CUSTO_SOLDADO_FLORINS;
        int custoServos = qtd * CUSTO_SOLDADO_SERVOS;
        if (cidade.getFlorins() < custoFlorins || cidade.getServos() < custoServos) {
            return false;
        }
        cidade.removerFlorins(custoFlorins);
        cidade.removerServos(custoServos);
        cidade.adicionarSoldados(qtd);
        return true;
    }

    public boolean construir(Cidade cidade, int qtd) {
        if (qtd <= 0) return false;
        int custoFlorins = qtd * CUSTO_CONSTRUCAO_FLORINS;
        if (!cidade.removerFlorins(custoFlorins)) {
            return false;
        }
        cidade.adicionarConstrucoes(qtd);
        return true;
    }
}