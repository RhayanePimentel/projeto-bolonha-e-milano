public class Agricultura {
    public static final int HECTARES_POR_SERVO = 5;
    public static final int RENDIMENTO_POR_HECTARE = 2;
    public static final int SERVOS_POR_GRAO = 2;
    public static final int CRESCIMENTO_PERCENTUAL = 5;
    public static final int PERCENTUAL_MINIMO = 20;
    public static final int PERCENTUAL_MAXIMO = 80;

    public int processarColheita(Cidade cidade) {
        if (cidade.isColheitaFeita()) {
            return -1;
        }
        int capacidade = cidade.getServos() * HECTARES_POR_SERVO;
        int hectaresCultivados = Math.min(cidade.getTerras(), capacidade);
        int graosColhidos = hectaresCultivados * RENDIMENTO_POR_HECTARE;
        int sementes = hectaresCultivados / 2;

        cidade.adicionarGraos(graosColhidos);
        cidade.removerGraos(sementes);
        cidade.marcarColheitaFeita();
        return hectaresCultivados;
    }

    public boolean distribuirAlimentos(Cidade cidade, int percentual) {
        if (percentual < PERCENTUAL_MINIMO || percentual > PERCENTUAL_MAXIMO) {
            return false;
        }
        int graosDistribuidos = (cidade.getGraos() * percentual) / 100;
        if (graosDistribuidos <= 0) {
            return false;
        }
        cidade.removerGraos(graosDistribuidos);

        int servosAlimentados = graosDistribuidos * SERVOS_POR_GRAO;
        if (servosAlimentados >= cidade.getServos()) {
            int novosServos = (cidade.getServos() * CRESCIMENTO_PERCENTUAL) / 100;
            cidade.adicionarServos(novosServos);
        } else {
            int diferenca = cidade.getServos() - servosAlimentados;
            int perda = diferenca / 2;
            if (perda > 0) {
                cidade.removerServos(Math.min(perda, cidade.getServos()));
            }
        }
        return true;
    }
}