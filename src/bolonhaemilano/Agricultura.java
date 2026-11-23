public class Agricultura {
    private final int HECTARES_POR_SERVO = 5;       //[cite: 107]
    private final int RENDIMENTO_POR_HECTARE = 2;     //[cite: 107]
    private final int SERVOS_POR_GRAO = 5;          //[cite: 107]
    private final int CRESCIMENTO_PERCENTUAL = 5;     //[cite: 107]

    public int processarColheita(Cidade c) {
        int hectaresPossiveis = c.getServos() * HECTARES_POR_SERVO;
        int hectaresCultivados = Math.min(c.getTerras(), hectaresPossiveis);
        int sementes = hectaresCultivados / 2;

        if (c.getGraos() < sementes) {
            sementes = c.getGraos();
            hectaresCultivados = sementes * 2;
        }

        c.removerGraos(sementes);
        int producao = hectaresCultivados * RENDIMENTO_POR_HECTARE;
        c.adicionarGraos(producao);
        return hectaresCultivados;
    }

    public boolean distribuirAlimentos(Cidade c, int percentual) {
        if (percentual < 20 || percentual > 80) return false; //[cite: 107, 108]

        int alimentoDistribuido = (c.getGraos() * percentual) / 100;
        c.removerGraos(alimentoDistribuido);

        int necessidade = c.getServos() / SERVOS_POR_GRAO;
        if (alimentoDistribuido >= necessidade) {
            int crescimento = (c.getServos() * CRESCIMENTO_PERCENTUAL) / 100;
            c.adicionarServos(crescimento);
        } else {
            int deficit = necessidade - alimentoDistribuido;
            c.removerServos(Math.min(deficit, c.getServos()));
        }
        return true;
    }
}