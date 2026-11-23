public class Mercado {
    public static final int PRECO_GRAO = 2;
    public static final int PRECO_TERRA = 1;


    public int getPrecoGrao() { return PRECO_GRAO; }
    public int getPrecoTerra() { return PRECO_TERRA; }

    public boolean comprarGraos(Cidade c, int qtd) {
        if (qtd <= 0) return false;
        int custo = qtd * PRECO_GRAO;
        if (c.removerFlorins(custo)) {
            c.adicionarGraos(qtd);
            return true;
        }
        return false;
    }

    public boolean venderGraos(Cidade c, int qtd) {
        if (qtd <= 0) return false;
        if (c.removerGraos(qtd)) {
            c.adicionarFlorins(qtd * PRECO_GRAO);
            return true;
        }
        return false;
    }

    public boolean comprarTerras(Cidade c, int qtd) {
        if (qtd <= 0) return false;
        int custo = qtd * PRECO_TERRA;
        if (c.removerFlorins(custo)) {
            c.adicionarTerras(qtd);
            return true;
        }
        return false;
    }

    public boolean venderTerras(Cidade c, int qtd) {
        if (qtd <= 0) return false;
        if (c.removerTerras(qtd)) {
            c.adicionarFlorins(qtd * PRECO_TERRA);
            return true;
        }
        return false;
    }
}