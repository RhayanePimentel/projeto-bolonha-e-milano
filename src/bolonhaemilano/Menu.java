import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private boolean emExecucao;
    private Jogador jogador;
    private Cidade cidade;
    private Mercado mercado;
    private Agricultura agricultura;
    private Governo governo;
    private Construcoes construcoes;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.emExecucao = true;
        this.mercado = new Mercado();
        this.agricultura = new Agricultura();
        this.governo = new Governo();
        this.construcoes = new Construcoes();
    }

    public void executar() {
        while (emExecucao) {
            exibirOpcoes();
            int opcao = lerOpcao();
            processarOpcao(opcao);
        }
        scanner.close();
    }

    private void exibirOpcoes() {
        System.out.println("\n=== BOLONHA E MILANO ===");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("3 - Mercado (Comprar/Vender)");
        System.out.println("4 - Agricultura (Colher/Alimentar)");
        System.out.println("5 - Governo (Impostos e Justiça)");
        System.out.println("6 - Construções (Soldados e Obras)");
        System.out.println("7 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Retorna inválido se digitar letras
        }
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                iniciarNovoJogo();
                break;
            case 2:
                if (verificarJogoIniciado()) cidade.exibirStatus();
                break;
            case 3:
                if (verificarJogoIniciado()) abrirMercado();
                break;
            case 4:
                if (verificarJogoIniciado()) abrirAgricultura();
                break;
            case 5:
                if (verificarJogoIniciado()) abrirGoverno();
                break;
            case 6:
                if (verificarJogoIniciado()) abrirConstrucoes();
                break;
            case 7:
                exibirSobre();
                break;
            case 0:
                emExecucao = false;
                System.out.println("Encerrando o jogo. Até logo!");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private boolean verificarJogoIniciado() {
        if (cidade == null) {
            System.out.println("Aviso: Nenhum jogo iniciado! Escolha a opção 1 primeiro.");
            return false;
        }
        return true;
    }

    private void iniciarNovoJogo() {
        System.out.print("Digite o nome do jogador (ou deixe em branco para 'Jogador 1'): ");
        String nomeJ = scanner.nextLine().trim();
        if (nomeJ.isEmpty()) nomeJ = "Jogador 1"; //[cite: 105]

        System.out.print("Digite o nome da cidade (ou deixe em branco para 'Bolonha'): ");
        String nomeC = scanner.nextLine().trim();
        if (nomeC.isEmpty()) nomeC = "Bolonha"; //[cite: 105]

        jogador = new Jogador(nomeJ);
        cidade = new Cidade(nomeC, jogador);
        System.out.println("Jogo iniciado com sucesso!");
        cidade.exibirStatus();
    }

    private void abrirMercado() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- MERCADO (Florins disponíveis: " + cidade.getFlorins() + ") ---");
            System.out.println("1 - Comprar grãos (Preço: " + mercado.getPrecoGrao() + ")");
            System.out.println("2 - Vender grãos");
            System.out.println("3 - Comprar terras (Preço: " + mercado.getPrecoTerra() + ")");
            System.out.println("4 - Vender terras");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha: ");
            op = lerOpcao();

            if (op >= 1 && op <= 4) {
                System.out.print("Digite a quantidade: ");
                int qtd = lerOpcao();
                boolean sucesso = false;

                switch (op) {
                    case 1: sucesso = mercado.comprarGraos(cidade, qtd); break;
                    case 2: sucesso = mercado.venderGraos(cidade, qtd); break;
                    case 3: sucesso = mercado.comprarTerras(cidade, qtd); break;
                    case 4: sucesso = mercado.venderTerras(cidade, qtd); break;
                }
                if (sucesso) System.out.println("Operação realizada com sucesso!");
                else System.out.println("Operação falhou (recursos insuficientes ou quantidade inválida).");
            }
        }
    }

    private void abrirAgricultura() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- AGRICULTURA ---");
            System.out.println("1 - Colher safra");
            System.out.println("2 - Distribuir alimentos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = lerOpcao();

            if (op == 1) {
                int colhido = agricultura.processarColheita(cidade);
                System.out.println("Colheita realizada! Hectares cultivados: " + colhido);
            } else if (op == 2) {
                System.out.print("Digite o percentual de grãos para distribuir (20 a 80): ");
                int perc = lerOpcao();
                if (agricultura.distribuirAlimentos(cidade, perc)) {
                    System.out.println("Alimentos distribuídos com sucesso!");
                } else {
                    System.out.println("Percentual inválido (deve ser entre 20 e 80).");
                }
            }
        }
    }

    private void abrirGoverno() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- GOVERNO ---");
            System.out.println("1 - Arrecadar impostos");
            System.out.println("2 - Definir taxa de imposto (Atual: " + cidade.getTaxaImposto() + "%)");
            System.out.println("3 - Definir nível de justiça (Atual: " + cidade.getNivelJustica() + ")");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = lerOpcao();

            if (op == 1) {
                int arrecadado = governo.arrecadarImpostos(cidade);
                System.out.println("Impostos arrecadados com sucesso! Total obtido: " + arrecadado + " florins.");
            } else if (op == 2) {
                System.out.print("Digite a nova taxa (0 a 50): ");
                int taxa = lerOpcao();
                if (governo.definirTaxaImposto(cidade, taxa)) {
                    System.out.println("Taxa atualizada!");
                } else {
                    System.out.println("Taxa inválida.");
                }
            } else if (op == 3) {
                System.out.println("Escolha a justiça: 1-Justa | 2-Moderada | 3-Severa");
                int jOp = lerOpcao();
                String jStr = "Moderada";
                if (jOp == 1) jStr = "Justa";
                else if (jOp == 2) jStr = "Moderada";
                else if (jOp == 3) jStr = "Severa";
                
                if (governo.definirJustica(cidade, jStr)) {
                    System.out.println("Nível de justiça atualizado!");
                }
            }
        }
    }

    private void abrirConstrucoes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- CONSTRUÇÕES E DEFESA ---");
            System.out.println("1 - Recrutar soldados (Custo: 5 florins + 1 servo)");
            System.out.println("2 - Construir edifícios (Custo: 20 florins)");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = lerOpcao();

            if (op == 1) {
                System.out.print("Quantos soldados deseja recrutar? ");
                int qtd = lerOpcao();
                if (construcoes.recrutarSoldados(cidade, qtd)) {
                    System.out.println("Soldados recrutados com sucesso!");
                } else {
                    System.out.println("Falha ao recrutar (verifique florins e servos disponíveis).");
                }
            } else if (op == 2) {
                System.out.print("Quantas construções deseja erguer? ");
                int qtd = lerOpcao();
                if (construcoes.construir(cidade, qtd)) {
                    System.out.println("Construções erguidas com sucesso!");
                } else {
                    System.out.println("Falha ao construir (florins insuficientes).");
                }
            }
        }
    }

    private void exibirSobre() {
        System.out.println("\n--- SOBRE O JOGO ---");
        System.out.println("Bolonha e Milano - Jogo de estratégia econômica medieval.");
        System.out.println("Desenvolvido para aprendizado de Orientação a Objetos em Java.");
    }
}