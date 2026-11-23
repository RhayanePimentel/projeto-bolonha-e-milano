import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private boolean emExecucao;
    private Partida partida;
    private Cidade cidade;
    private Mercado mercado;
    private Agricultura agricultura;
    private Governo governo;
    private Construcoes construcoes;
    private Nobreza nobreza;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.emExecucao = true;
        this.mercado = new Mercado();
        this.agricultura = new Agricultura();
        this.governo = new Governo();
        this.construcoes = new Construcoes();
        this.nobreza = new Nobreza();
    }

    public void executar() {
        while (emExecucao) {
            exibirOpcoes();
            int opcao = lerOpcao();
            processarOpcao(opcao);
        }
        scanner.close();
    }

    private void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pausar() {
        System.out.println();
        System.out.print("Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    private void exibirOpcoes() {
        limparTela();
        System.out.println("========================================");
        System.out.println("           BOLONHA E MILANO");
        System.out.println("  Um jogo de estrategia economica medieval");
        System.out.println("========================================");
        if (partida != null && cidade != null) {
            System.out.println("Ano " + partida.getAnoAtual() + " | Vez de: "
                    + cidade.getDono().getNome() + " (" + cidade.getDono().getTitulo()
                    + ") | Cidade: " + cidade.getNome());
            System.out.println("----------------------------------------");
        }
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("3 - Mercado (comprar/vender graos e terras)");
        System.out.println("4 - Agricultura (colher e distribuir alimentos)");
        System.out.println("5 - Governo (impostos e justica)");
        System.out.println("6 - Construcoes (recrutar soldados e construir)");
        System.out.println("7 - Encerrar turno");
        System.out.println("8 - Ver classificacao");
        System.out.println("9 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private int lerInteiro() {
        String linha = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private int lerOpcao() {
        return lerInteiro();
    }

    private int lerQuantidade(String mensagem) {
        System.out.print(mensagem);
        return lerInteiro();
    }

    private boolean exigirJogoEmAndamento() {
        if (partida == null || cidade == null) {
            System.out.println("Nenhum jogo em andamento. Escolha a opcao 1 para comecar.");
            return false;
        }
        return true;
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: iniciarNovoJogo(); break;
            case 2: exibirStatus(); break;
            case 3: abrirMercado(); break;
            case 4: abrirAgricultura(); break;
            case 5: abrirGoverno(); break;
            case 6: abrirConstrucoes(); break;
            case 7: encerrarTurno(); break;
            case 8: exibirClassificacao(); break;
            case 9: exibirSobre(); break;
            case 0:
                emExecucao = false;
                System.out.println("Encerrando o jogo. Ate a proxima rodada!");
                break;
            default:
                System.out.println("Opcao invalida!");
        }
        if (emExecucao) {
            pausar();
        }
    }

    private void iniciarNovoJogo() {
        System.out.print("Quantos jogadores (1 a 4)? ");
        int qtdJogadores = lerInteiro();
        if (qtdJogadores < 1 || qtdJogadores > Partida.MAXIMO_JOGADORES) {
            System.out.println("Quantidade invalida, iniciando com 2 jogadores.");
            qtdJogadores = 2;
        }

        Partida novaPartida = new Partida();
        String[] sugestoes = {"Bolonha", "Milano", "Florenca", "Veneza"};

        for (int i = 0; i < qtdJogadores; i++) {
            System.out.print("Nome do jogador " + (i + 1) + ": ");
            String nomeJogador = scanner.nextLine().trim();
            if (nomeJogador.isEmpty()) nomeJogador = "Jogador " + (i + 1);

            String sugestao = sugestoes[i];
            System.out.print("Nome da cidade de " + nomeJogador + " (Enter = " + sugestao + "): ");
            String nomeCidade = scanner.nextLine().trim();
            if (nomeCidade.isEmpty()) nomeCidade = sugestao;

            Jogador jogador = new Jogador(nomeJogador);
            Cidade novaCidade = new Cidade(nomeCidade, jogador);
            novaPartida.adicionarCidade(novaCidade);
        }

        this.partida = novaPartida;
        this.cidade = partida.getCidadeAtual();

        System.out.println("\nNovo jogo iniciado! Ano " + partida.getAnoAtual()
                + ". A partida vai ate o fim de " + partida.getAnoFinal()
                + " ou ate alguem virar Rei/Rainha.");
        System.out.println("Comeca: " + cidade.getDono().getNome()
                + " (" + cidade.getDono().getTitulo() + "), de " + cidade.getNome() + ".");
        cidade.exibirStatus();
    }

    private void exibirStatus() {
        if (!exigirJogoEmAndamento()) return;
        cidade.exibirStatus();
    }

    private void abrirMercado() {
        if (!exigirJogoEmAndamento()) return;
        boolean noSubmenu = true;
        while (noSubmenu) {
            System.out.println("\n-------------- MERCADO --------------");
            System.out.println("Florins: " + cidade.getFlorins() + " | Graos: " + cidade.getGraos() + " | Terras: " + cidade.getTerras() + " ha");
            System.out.println("Preco do grao: " + Mercado.PRECO_GRAO + " florins | Preco da terra: " + Mercado.PRECO_TERRA + " florim/ha");
            System.out.println("1 - Comprar graos");
            System.out.println("2 - Vender graos");
            System.out.println("3 - Comprar terras");
            System.out.println("4 - Vender terras");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opcao: ");
            int op = lerOpcao();
            if (op == 0) {
                noSubmenu = false;
            } else if (op == 1) {
                int qtd = lerQuantidade("Quantidade de graos para comprar: ");
                if (mercado.comprarGraos(cidade, qtd)) System.out.println("Compra realizada!");
                else System.out.println("Nao foi possivel comprar.");
            } else if (op == 2) {
                int qtd = lerQuantidade("Quantidade de graos para vender: ");
                if (mercado.venderGraos(cidade, qtd)) System.out.println("Venda realizada!");
                else System.out.println("Nao foi possivel vender.");
            } else if (op == 3) {
                int qtd = lerQuantidade("Hectares de terra para comprar: ");
                if (mercado.comprarTerras(cidade, qtd)) System.out.println("Compra de terras realizada!");
                else System.out.println("Nao foi possivel comprar.");
            } else if (op == 4) {
                int qtd = lerQuantidade("Hectares de terra para vender: ");
                if (mercado.venderTerras(cidade, qtd)) System.out.println("Venda de terras realizada!");
                else System.out.println("Nao foi possivel vender.");
            } else {
                System.out.println("Opcao invalida!");
            }
        }
    }

    private void abrirAgricultura() {
        if (!exigirJogoEmAndamento()) return;
        boolean noSubmenu = true;
        while (noSubmenu) {
            System.out.println("\n------------ AGRICULTURA ------------");
            System.out.println("Terras: " + cidade.getTerras() + " hectares");
            System.out.println("Servos: " + cidade.getServos());
            System.out.println("Graos em estoque: " + cidade.getGraos());
            System.out.println("1 - Colher (processar safra do ano)");
            System.out.println("2 - Distribuir alimentos");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opcao: ");
            int op = lerOpcao();
            if (op == 0) {
                noSubmenu = false;
            } else if (op == 1) {
                int hectares = agricultura.processarColheita(cidade);
                if (hectares == -1) {
                    System.out.println("A colheita deste ano ja foi realizada!");
                } else {
                    System.out.println("Colheita processada! Hectares cultivados: " + hectares);
                    System.out.println("Graos em estoque agora: " + cidade.getGraos());
                }
            } else if (op == 2) {
                int perc = lerQuantidade("Percentual dos graos a distribuir (20 a 80): Quantidade: ");
                if (agricultura.distribuirAlimentos(cidade, perc)) {
                    System.out.println("Alimentos distribuidos! Servos: " + cidade.getServos() + " | Graos em estoque: " + cidade.getGraos());
                } else {
                    System.out.println("Percentual invalido ou graos insuficientes.");
                }
            } else {
                System.out.println("Opcao invalida!");
            }
        }
    }

    private void abrirGoverno() {
        if (!exigirJogoEmAndamento()) return;
        boolean noSubmenu = true;
        while (noSubmenu) {
            System.out.println("\n-------------- GOVERNO --------------");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Taxa de imposto: " + cidade.getTaxaImposto() + "%");
            System.out.println("Nivel de justica: " + cidade.getNivelJustica());
            System.out.println("1 - Arrecadar impostos");
            System.out.println("2 - Definir taxa de imposto");
            System.out.println("3 - Definir nivel de justica");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opcao: ");
            int op = lerOpcao();
            if (op == 0) {
                noSubmenu = false;
            } else if (op == 1) {
                int valor = governo.arrecadarImpostos(cidade);
                if (valor == -1) {
                    System.out.println("Os impostos deste ano ja foram arrecadados!");
                } else {
                    System.out.println("Impostos arrecadados: " + valor + " florins.");
                    System.out.println("Florins agora: " + cidade.getFlorins());
                }
            } else if (op == 2) {
                int taxa = lerQuantidade("Nova taxa de imposto (0 a 50): Quantidade: ");
                if (governo.definirTaxaImposto(cidade, taxa)) {
                    System.out.println("Taxa de imposto definida como " + taxa + "%.");
                } else {
                    System.out.println("Taxa invalida (permitido: 0 a 50%).");
                }
            } else if (op == 3) {
                System.out.println("1 - Justa");
                System.out.println("2 - Moderada");
                System.out.println("3 - Severa");
                int nj = lerQuantidade("Escolha o nivel de justica: ");
                String nivel = (nj == 1) ? "Justa" : (nj == 2) ? "Moderada" : (nj == 3) ? "Severa" : null;
                if (governo.definirJustica(cidade, nivel)) {
                    System.out.println("Nivel de justica definido como " + nivel + ".");
                } else {
                    System.out.println("Opcao de justica invalida.");
                }
            } else {
                System.out.println("Opcao invalida!");
            }
        }
    }

    private void abrirConstrucoes() {
        if (!exigirJogoEmAndamento()) return;
        boolean noSubmenu = true;
        while (noSubmenu) {
            System.out.println("\n------------ CONSTRUCOES ------------");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Servos: " + cidade.getServos());
            System.out.println("Soldados: " + cidade.getSoldados());
            System.out.println("Construcoes: " + cidade.getConstrucoes());
            System.out.println("Preco do soldado: " + Construcoes.CUSTO_SOLDADO_FLORINS + " florins + " + Construcoes.CUSTO_SOLDADO_SERVOS + " servo");
            System.out.println("Preco da construcao: " + Construcoes.CUSTO_CONSTRUCAO_FLORINS + " florins");
            System.out.println("1 - Recrutar soldados");
            System.out.println("2 - Construir");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opcao: ");
            int op = lerOpcao();
            if (op == 0) {
                noSubmenu = false;
            } else if (op == 1) {
                int qtd = lerQuantidade("Quantidade: ");
                if (construcoes.recrutarSoldados(cidade, qtd)) {
                    System.out.println("Soldados recrutados! Soldados: " + cidade.getSoldados() + " | Servos: " + cidade.getServos());
                } else {
                    System.out.println("Recursos insuficientes ou quantidade invalida.");
                }
            } else if (op == 2) {
                int qtd = lerQuantidade("Quantidade: ");
                if (construcoes.construir(cidade, qtd)) {
                    System.out.println("Construcao concluida! Construcoes: " + cidade.getConstrucoes());
                } else {
                    System.out.println("Florins insuficientes ou quantidade invalida.");
                }
            } else {
                System.out.println("Opcao invalida!");
            }
        }
    }

    private void encerrarTurno() {
        if (!exigirJogoEmAndamento()) return;
        String nomeAnterior = cidade.getDono().getNome();
        boolean fimDoAno = partida.passarTurno();
        System.out.println("Turno de " + nomeAnterior + " encerrado.");

        if (fimDoAno) {
            int anoEncerrado = partida.getAnoAtual();
            List<Cidade> promovidas = partida.encerrarAno(nobreza);
            System.out.println("\n========== FIM DO ANO " + anoEncerrado + " ==========");
            for (Cidade c : promovidas) {
                System.out.println(c.getDono().getNome() + ", de " + c.getNome()
                        + ", agora e " + c.getDono().getTitulo() + "!");
            }
            exibirClassificacao();

            if (partida.terminou()) {
                encerrarPartida();
                return;
            }
            System.out.println("Comeca o ano " + partida.getAnoAtual() + ".");
        }

        this.cidade = partida.getCidadeAtual();
        System.out.println("Agora e a vez de " + cidade.getDono().getNome()
                + " (" + cidade.getDono().getTitulo() + "), de " + cidade.getNome() + ".");
    }

    private void exibirClassificacao() {
        if (!exigirJogoEmAndamento()) return;
        System.out.println("\n----------- CLASSIFICACAO -----------");
        for (Cidade c : partida.getCidades()) {
            int prestigio = nobreza.calcularPrestigio(c);
            int prox = nobreza.prestigioParaProximoTitulo(c.getDono());
            String meta = (prox == -1) ? "titulo maximo!" : "proximo titulo com " + prox;
            System.out.println(c.getNome() + " | " + c.getDono().getNome()
                    + " (" + c.getDono().getTitulo() + ") | prestigio " + prestigio
                    + " (" + meta + ")");
        }
        System.out.println("-------------------------------------");
    }

    private void encerrarPartida() {
        Cidade vencedora = partida.determinarVencedor(nobreza);
        System.out.println("\n============ FIM DE JOGO ============");
        if (vencedora != null) {
            System.out.println("Vencedor(a): " + vencedora.getDono().getNome()
                    + " (" + vencedora.getDono().getTitulo() + "), governante de "
                    + vencedora.getNome() + " com " + nobreza.calcularPrestigio(vencedora)
                    + " de prestigio!");
        }
        this.partida = null;
        this.cidade = null;
    }

    private void exibirSobre() {
        System.out.println("\nBolonha e Milano - Jogo de Estrategia Economica Medieval");
        System.out.println("Governe sua cidade-Estado a partir de 1400.");
        System.out.println("Acumule prestigio com construcoes, soldados, servos e terras");
        System.out.println("para subir de Cavaleiro ate Rei/Rainha antes de 1410!");
    }
}