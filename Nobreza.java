public class Nobreza {
    public static final int PESO_CONSTRUCAO = 10;
    public static final int PESO_SOLDADO = 2;
    public static final int PESO_SERVO = 1;
    public static final int HECTARES_POR_PONTO = 100;
    public static final int[] PRESTIGIO_MINIMO = {0, 400, 550, 700, 850, 1000};

    public int calcularPrestigio(Cidade cidade) {
        return (cidade.getConstrucoes() * PESO_CONSTRUCAO)
             + (cidade.getSoldados() * PESO_SOLDADO)
             + (cidade.getServos() * PESO_SERVO)
             + (cidade.getTerras() / HECTARES_POR_PONTO);
    }

    public int prestigioParaProximoTitulo(Jogador jogador) {
        if (jogador.ehMonarca()) {
            return -1;
        }
        return PRESTIGIO_MINIMO[jogador.getNivelTitulo() + 1];
    }

    public boolean verificarPromocao(Cidade cidade) {
        Jogador dono = cidade.getDono();
        int necessario = prestigioParaProximoTitulo(dono);
        if (necessario == -1) {
            return false;
        }
        if (calcularPrestigio(cidade) >= necessario) {
            return dono.promover();
        }
        return false;
    }
}