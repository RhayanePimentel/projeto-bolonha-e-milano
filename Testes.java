public class Testes {
    private static int aprovados = 0;
    private static int reprovados = 0;

    public static void main(String[] args) {
        System.out.println("===== TESTES BOLONHA E MILANO =====");
        testarCidade();
        testarMercado();
        testarAgricultura();
        testarGoverno();
        testarConstrucoes();
        testarJogadorENobreza();
        testarPartida();

        System.out.println("\nAprovados: " + aprovados + " | Reprovados: " + reprovados);
        if (reprovados > 0) {
            System.exit(1);
        }
    }

    private static void verificar(String descricao, boolean condicao) {
        if (condicao) {
            aprovados++;
            System.out.println("[OK] " + descricao);
        } else {
            reprovados++;
            System.out.println("[FALHA] " + descricao);
        }
    }

    private static Cidade novaCidade() {
        return new Cidade("Bolonha", new Jogador("Teste"));
    }

    private static void testarCidade() {
        System.out.println("\nCidade");
        Cidade c = novaCidade();
        verificar("cidade nasce com 1000 florins", c.getFlorins() == 1000);
        verificar("cidade nasce no ano 1400", c.getAnoAtual() == 1400);
        verificar("removerFlorins acima do saldo devolve false", !c.removerFlorins(1500));
        verificar("... e nao altera o saldo", c.getFlorins() == 1000);
        c.marcarColheitaFeita();
        c.avancarAno();
        verificar("avancarAno soma 1 ao ano", c.getAnoAtual() == 1401);
        verificar("avancarAno libera a colheita de novo", !c.isColheitaFeita());
    }

    private static void testarMercado() {
        System.out.println("\nMercado");
        Mercado m = new Mercado();
        Cidade c = novaCidade();
        verificar("comprar 100 graos da certo", m.comprarGraos(c, 100));
        verificar("... e custa 200 florins", c.getFlorins() == 800);
        verificar("... e soma 100 graos", c.getGraos() == 600);
        verificar("comprar quantidade negativa e recusado", !m.comprarGraos(c, -10));
        verificar("comprar sem florins suficientes e recusado", !m.comprarGraos(c, 1000));
    }

    private static void testarAgricultura() {
        System.out.println("\nAgricultura");
        Agricultura ag = new Agricultura();
        Cidade c = novaCidade();
        int ha = ag.processarColheita(c);
        verificar("primeira colheita do ano cultiva 1000 hectares", ha == 1000);
        verificar("... e deixa 2000 graos", c.getGraos() == 2000);
        verificar("segunda colheita no mesmo ano devolve -1", ag.processarColheita(c) == -1);
        verificar("distribuir 10% e recusado (minimo 20%)", !ag.distribuirAlimentos(c, 10));
    }

    private static void testarGoverno() {
        System.out.println("\nGoverno");
        Governo gov = new Governo();
        Cidade c = novaCidade();
        verificar("taxa de 60% e recusada (maximo 50%)", !gov.definirTaxaImposto(c, 60));
        verificar("justica \"Branda\" e recusada", !gov.definirJustica(c, "Branda"));
        gov.definirJustica(c, "Severa");
        int imp = gov.arrecadarImpostos(c);
        verificar("200 servos, 10%, Severa: arrecada 26 florins", imp == 26);
        verificar("segunda arrecadacao no mesmo ano devolve -1", gov.arrecadarImpostos(c) == -1);
    }

    private static void testarConstrucoes() {
        System.out.println("\nConstrucoes");
        Construcoes cons = new Construcoes();
        Cidade c = novaCidade();
        c.removerServos(150);
        boolean recrutou = cons.recrutarSoldados(c, 100);
        verificar("recrutar 100 soldados com so 50 servos e recusado", !recrutou);
        verificar("... e NAO debita florins pela metade", c.getFlorins() == 1000);

        Cidade c2 = novaCidade();
        verificar("construir 10 da certo", cons.construir(c2, 10));
        verificar("... e custa 200 florins", c2.getFlorins() == 800);
    }

    private static void testarJogadorENobreza() {
        System.out.println("\nJogador e Nobreza");
        Nobreza nob = new Nobreza();
        Cidade c = novaCidade();
        verificar("prestigio inicial e 300", nob.calcularPrestigio(c) == 300);
        verificar("jogador comeca como Cavaleiro", c.getDono().getTitulo().equals("Cavaleiro"));
        verificar("com prestigio 300 nao sobe de titulo", !nob.verificarPromocao(c));

        c.adicionarConstrucoes(50);
        verificar("com 50 construcoes sobe para Barao", nob.verificarPromocao(c) && c.getDono().getTitulo().equals("Barao"));
        verificar("sobe no maximo um titulo por chamada", c.getDono().getNivelTitulo() == 1);

        while (!c.getDono().ehMonarca()) {
            c.getDono().promover();
        }
        verificar("promover para alem de Rei/Rainha e recusado", !c.getDono().promover());
    }

    private static void testarPartida() {
        System.out.println("\nPartida");
        Partida p = new Partida();
        Nobreza nob = new Nobreza();
        Cidade bolonha = new Cidade("Bolonha", new Jogador("Ana"));
        Cidade milano = new Cidade("Milano", new Jogador("Beto"));
        p.adicionarCidade(bolonha);
        p.adicionarCidade(milano);

        verificar("primeiro turno e de Bolonha", p.getCidadeAtual().getNome().equals("Bolonha"));
        verificar("passar o turno de Bolonha NAO fecha o ano", !p.passarTurno());
        verificar("agora e a vez de Milano", p.getCidadeAtual().getNome().equals("Milano"));
        verificar("passar o turno de Milano fecha o ano", p.passarTurno());

        milano.adicionarConstrucoes(50);
        p.encerrarAno(nob);
        verificar("encerrarAno avanca todas as cidades", bolonha.getAnoAtual() == 1401 && milano.getAnoAtual() == 1401);
        verificar("partida ainda nao terminou em 1401", !p.terminou());
        verificar("vencedor parcial e Milano (titulo mais alto)", p.determinarVencedor(nob).getNome().equals("Milano"));

        p.adicionarCidade(new Cidade("Florenca", new Jogador("C3")));
        p.adicionarCidade(new Cidade("Veneza", new Jogador("C4")));
        verificar("quinta cidade e recusada (maximo 4)", !p.adicionarCidade(new Cidade("Roma", new Jogador("C5"))));
    }
}