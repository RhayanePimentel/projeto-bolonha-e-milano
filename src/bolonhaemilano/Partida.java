import java.util.ArrayList;
import java.util.List;

public class Partida {
    public static final int MAXIMO_JOGADORES = 4;
    public static final int ANO_FINAL = 1410;

    private List<Cidade> cidades;
    private int indiceAtual;

    public Partida() {
        this.cidades = new ArrayList<>();
        this.indiceAtual = 0;
    }

    public boolean adicionarCidade(Cidade cidade) {
        if (cidades.size() >= MAXIMO_JOGADORES) {
            return false;
        }
        cidades.add(cidade);
        return true;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public Cidade getCidadeAtual() {
        if (cidades.isEmpty()) return null;
        return cidades.get(indiceAtual);
    }

    public int getAnoAtual() {
        if (cidades.isEmpty()) return 1400;
        return cidades.get(0).getAnoAtual();
    }

    public int getAnoFinal() {
        return ANO_FINAL;
    }

    public boolean passarTurno() {
        indiceAtual++;
        if (indiceAtual >= cidades.size()) {
            indiceAtual = 0;
            return true;
        }
        return false;
    }

    public List<Cidade> encerrarAno(Nobreza nobreza) {
        List<Cidade> promovidas = new ArrayList<>();
        for (Cidade cidade : cidades) {
            cidade.avancarAno();
            if (nobreza.verificarPromocao(cidade)) {
                promovidas.add(cidade);
            }
        }
        return promovidas;
    }

    public boolean terminou() {
        if (cidades.isEmpty()) return false;
        if (getAnoAtual() > ANO_FINAL) {
            return true;
        }
        for (Cidade cidade : cidades) {
            if (cidade.getDono().ehMonarca()) {
                return true;
            }
        }
        return false;
    }

    public Cidade determinarVencedor(Nobreza nobreza) {
        if (cidades.isEmpty()) return null;
        Cidade vencedora = cidades.get(0);
        for (int i = 1; i < cidades.size(); i++) {
            Cidade candidata = cidades.get(i);
            int nivelCand = candidata.getDono().getNivelTitulo();
            int nivelVenc = vencedora.getDono().getNivelTitulo();

            if (nivelCand > nivelVenc) {
                vencedora = candidata;
            } else if (nivelCand == nivelVenc) {
                if (nobreza.calcularPrestigio(candidata) > nobreza.calcularPrestigio(vencedora)) {
                    vencedora = candidata;
                }
            }
        }
        return vencedora;
    }
}