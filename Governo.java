public class Governo {
    public static final int TAXA_MAXIMA = 50;
    public static final double MULTIPLICADOR_JUSTA = 0.8;
    public static final double MULTIPLICADOR_MODERADA = 1.0;
    public static final double MULTIPLICADOR_SEVERA = 1.3;

    public int arrecadarImpostos(Cidade cidade) {
        if (cidade.isImpostosArrecadados()) {
            return -1;
        }
        double base = (cidade.getServos() * cidade.getTaxaImposto()) / 100.0;
        double mult = multiplicadorPara(cidade.getNivelJustica());
        int arrecadado = (int) (base * mult);

        cidade.adicionarFlorins(arrecadado);
        cidade.marcarImpostosArrecadados();
        return arrecadado;
    }

    public boolean definirTaxaImposto(Cidade cidade, int taxa) {
        if (taxa < 0 || taxa > TAXA_MAXIMA) {
            return false;
        }
        cidade.setTaxaImposto(taxa);
        return true;
    }

    public boolean definirJustica(Cidade cidade, String nivel) {
        if (nivel == null) return false;
        if (nivel.equals("Justa") || nivel.equals("Moderada") || nivel.equals("Severa")) {
            cidade.setNivelJustica(nivel);
            return true;
        }
        return false;
    }

    private double multiplicadorPara(String nivel) {
        if ("Justa".equals(nivel)) return MULTIPLICADOR_JUSTA;
        if ("Severa".equals(nivel)) return MULTIPLICADOR_SEVERA;
        return MULTIPLICADOR_MODERADA;
    }
}