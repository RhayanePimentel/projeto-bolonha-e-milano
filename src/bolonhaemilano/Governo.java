public class Governo {
    public int arrecadarImpostos(Cidade c) {
        int base = (c.getServos() * c.getTaxaImposto()) / 100;
        double mult = multiplicadorPara(c.getNivelJustica());
        int arrecadado = (int) (base * mult);
        c.adicionarFlorins(arrecadado);
        return arrecadado;
    }

    public boolean definirTaxaImposto(Cidade c, int taxa) {
        if (taxa >= 0 && taxa <= 50) { //[cite: 108]
            c.setTaxaImposto(taxa);
            return true;
        }
        return false;
    }

    public boolean definirJustica(Cidade c, String nivel) {
        if (nivel.equals("Justa") || nivel.equals("Moderada") || nivel.equals("Severa")) { //[cite: 108]
            c.setNivelJustica(nivel);
            return true;
        }
        return false;
    }

    private double multiplicadorPara(String nivel) {
        switch (nivel) {
            case "Justa": return 0.8;     //[cite: 108]
            case "Severa": return 1.3;    //[cite: 108]
            default: return 1.0;          // Moderada[cite: 108]
        }
    }
}