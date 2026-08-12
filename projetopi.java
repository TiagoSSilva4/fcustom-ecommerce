package projetopifcustom;

import java.util.Scanner;

public class projetopi {

    public static Scanner leia = new Scanner(System.in);

    public static final int produtosmaximo = 10;
    public static final int historicomaximo = 50;

    public static String[] nomeProduto = new String[produtosmaximo];
    public static double[] precoProduto = new double[produtosmaximo];
    public static int[] estoqueProduto = new int[produtosmaximo];
    public static int totalProdutos = 0;

    public static String[] carrinhoNomeEstampa = new String[50];

    public static String[] historicoCliente = new String[historicomaximo];
    public static int[] historicoItens = new int[historicomaximo];
    public static double[] historicoValor = new double[historicomaximo];
    public static int totalVendas = 0;
    public static double totalGeralVendas = 0;

    public static final String loginADM = "adm";
    public static final String senhaADM = "1234";

    public static String enderecoCliente = "";

    public static String[] cores = new String[5];
    public static int totalCores = 0;

    public static String[] tamanhos = new String[4];
    public static int totalTamanhos = 0;

    public static String[] nomeEstampa = new String[5];
    public static double[] valorEstampa = new double[5];
    public static int totalEstampas = 0;

    public static void inicializarAtributos() {
        cores[0] = "Preto";
        cores[1] = "Branco";
        cores[2] = "Vermelho";
        cores[3] = "Azul";
        cores[4] = "Verde";
        totalCores = 5;

        tamanhos[0] = "P";
        tamanhos[1] = "M";
        tamanhos[2] = "G";
        tamanhos[3] = "GG";
        totalTamanhos = 4;

        nomeEstampa[0] = "Sem estampa";
        nomeEstampa[1] = "Logo F.Custom";
        nomeEstampa[2] = "Floral";
        nomeEstampa[3] = "Geometrico";
        nomeEstampa[4] = "Nome personalizado"; // sempre na ultima posicao
        valorEstampa[0] = 0.00;
        valorEstampa[1] = 0.00;
        valorEstampa[2] = 0.00;
        valorEstampa[3] = 0.00;
        valorEstampa[4] = 15.00;
        totalEstampas = 5;
    }

    // GUARD
    public static boolean estaVazio(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static String removerEspacos(String texto) {
        int inicio = 0;
        int fim = texto.length() - 1;

        while (inicio <= fim && texto.charAt(inicio) == ' ') {
            inicio++;
        }
        while (fim >= inicio && texto.charAt(fim) == ' ') {
            fim--;
        }

        if (inicio > fim) {
            return "";
        }

        return texto.substring(inicio, fim + 1);
    }

    public static boolean soLetras(String texto) {
        String letrasAcentuadas = "áàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ";

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            boolean espaco = c == ' ';
            boolean letraSimples = (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');

            boolean letracomAcento = false;
            for (int j = 0; j < letrasAcentuadas.length(); j++) {
                if (c == letrasAcentuadas.charAt(j)) {
                    letracomAcento = true;
                    break;
                }
            }

            if (!espaco && !letraSimples && !letracomAcento) {
                return false;
            }
        }
        return true;
    }

    public static boolean soDigitos(String texto) {
        if (texto.length() == 0) {
            return false;
        }
        for (int i = 0; i < texto.length(); i++) {
            boolean digito = texto.charAt(i) >= '0' && texto.charAt(i) <= '9';
            if (!digito) {
                return false;
            }
        }
        return true;
    }

    public static int lerInteiro(int min, int max) {
        int valor = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            String entrada = removerEspacos(leia.nextLine());

            if (estaVazio(entrada)) {
                System.out.print("  Entrada vazia! Digite entre " + min + " e " + max + ": ");
                continue;
            }

            if (!soDigitos(entrada)) {
                System.out.print("  Apenas numeros! Entre " + min + " e " + max + ": ");
                continue;
            }

            valor = 0;
            for (int i = 0; i < entrada.length(); i++) {
                valor = valor * 10 + (entrada.charAt(i) - '0');
            }

            boolean dentroDoIntervalo = valor >= min && valor <= max;
            if (dentroDoIntervalo) {
                entradaValida = true;
            } else {
                System.out.print("  Invalido! Digite entre " + min + " e " + max + ": ");
            }
        }

        return valor;
    }

    public static double lerDouble(double min, double max) {
        double valor = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            String entrada = removerEspacos(leia.nextLine());

            if (estaVazio(entrada)) {
                System.out.print("  Entrada vazia! Informe um valor: ");
                continue;
            }

            String entradaNormal = "";
            for (int i = 0; i < entrada.length(); i++) {
                char c = entrada.charAt(i);
                entradaNormal += (c == ',') ? '.' : c;
            }

            boolean formatoValido = entradaNormal.length() > 0;
            boolean temPonto = false;
            for (int i = 0; i < entradaNormal.length(); i++) {
                char c = entradaNormal.charAt(i);
                if (c == '.') {
                    if (temPonto) {
                        formatoValido = false;
                        break;
                    }
                    temPonto = true;
                } else if (c < '0' || c > '9') {
                    formatoValido = false;
                    break;
                }
            }

            if (!formatoValido) {
                System.out.print("  Valor invalido! Use ponto ou virgula: ");
                continue;
            }

            valor = Double.parseDouble(entradaNormal);

            boolean dentroDoIntervalo = valor >= min && valor <= max;
            if (dentroDoIntervalo) {
                entradaValida = true;
            } else {
                System.out.print("  Valor fora do permitido (" + min + " a " + max + "): ");
            }
        }

        return valor;
    }

    public static void voltarmenu() {
        System.out.print("\n  Pressione Enter para voltar ao menu...");
        leia.nextLine();
    }

    public static String lerEndereco() {
        System.out.println("\n  --- Endereco de Entrega ---");

        String rua = "";
        boolean ruaValida = false;
        while (!ruaValida) {
            System.out.print("  Rua: ");
            rua = removerEspacos(leia.nextLine());
            if (!estaVazio(rua)) {
                ruaValida = true;
            } else {
                System.out.println("  Rua nao pode ser vazia!");
            }
        }

        String numero = "";
        boolean numeroValido = false;
        while (!numeroValido) {
            System.out.print("  Numero: ");
            numero = removerEspacos(leia.nextLine());
            if (!estaVazio(numero)) {
                numeroValido = true;
            } else {
                System.out.println("  Numero nao pode ser vazio!");
            }
        }

        String bairro = "";
        boolean bairroValido = false;
        while (!bairroValido) {
            System.out.print("  Bairro: ");
            bairro = removerEspacos(leia.nextLine());
            if (!estaVazio(bairro)) {
                bairroValido = true;
            } else {
                System.out.println("  Bairro nao pode ser vazio!");
            }
        }

        String cidade = "";
        boolean cidadeValida = false;
        while (!cidadeValida) {
            System.out.print("  Cidade: ");
            cidade = removerEspacos(leia.nextLine());
            if (!estaVazio(cidade) && soLetras(cidade)) {
                cidadeValida = true;
            } else {
                System.out.println("  Cidade invalida! Use apenas letras.");
            }
        }

        String estado = "";
        boolean estadoValido = false;
        while (!estadoValido) {
            System.out.print("  Estado (ex: SP, RJ): ");
            String estadoDigitado = removerEspacos(leia.nextLine());

            estado = "";
            for (int i = 0; i < estadoDigitado.length(); i++) {
                char letra = estadoDigitado.charAt(i);
                estado += (letra >= 'a' && letra <= 'z') ? (char) (letra - 32) : letra;
            }

            if (!estaVazio(estado) && estado.length() == 2 && soLetras(estado)) {
                estadoValido = true;
            } else {
                System.out.println("  Estado invalido! Use a sigla com 2 letras (ex: SP).");
            }
        }

        String cep = "";
        boolean cepValido = false;
        while (!cepValido) {
            System.out.print("  CEP (somente numeros, 8 digitos): ");
            cep = removerEspacos(leia.nextLine());
            if (soDigitos(cep) && cep.length() == 8) {
                cepValido = true;
            } else {
                System.out.println("  CEP invalido! Digite exatamente 8 numeros.");
            }
        }

        System.out.print("  Complemento (opcional, Enter para pular): ");
        String complemento = removerEspacos(leia.nextLine());

        String endereco = rua + ", " + numero;
        if (!estaVazio(complemento)) {
            endereco += " - " + complemento;
        }
        endereco += ", " + bairro + ", " + cidade + " - " + estado + ", CEP: " + cep;

        return endereco;
    }

    public static void Produtos() {
        nomeProduto[0] = "Camisa";
        precoProduto[0] = 70.20;
        estoqueProduto[0] = 10;

        nomeProduto[1] = "Shorts";
        precoProduto[1] = 39.00;
        estoqueProduto[1] = 10;

        nomeProduto[2] = "Calca";
        precoProduto[2] = 85.90;
        estoqueProduto[2] = 10;

        nomeProduto[3] = "Conjunto";
        precoProduto[3] = 230.00;
        estoqueProduto[3] = 10;

        totalProdutos = 4;
    }

    public static String fazerLogin() {
        while (true) {
            System.out.println("\n===================================");
            System.out.println("       BEM-VINDO A F.CUSTOM        ");
            System.out.println("===================================");
            System.out.println("  1 - Entrar como Administrador");
            System.out.println("  2 - Entrar como Cliente");
            System.out.print("\n  Escolha (1 ou 2): ");
            int op = lerInteiro(1, 2);

            String tipoEscolhido = (op == 1) ? "Administrador" : "Cliente";
            System.out.println("  Voce escolheu: " + tipoEscolhido);
            System.out.print("  Confirmar? (sim/nao): ");
            String confTipo = removerEspacos(leia.nextLine());

            while (!confTipo.equalsIgnoreCase("sim") && !confTipo.equalsIgnoreCase("nao")) {
                System.out.println("  Digite apenas 'sim' ou 'nao'!");
                System.out.print("  Confirmar? (sim/nao): ");
                confTipo = removerEspacos(leia.nextLine());
            }

            if (confTipo.equalsIgnoreCase("nao")) {
                continue; // volta pro topo do while, reexibe o menu
            }

            if (op == 1) {
                return loginAdm();
            }

            // --- Cliente ---
            String nome = "";
            while (true) {
                System.out.print("\n  Seu nome: ");
                nome = removerEspacos(leia.nextLine());
                if (estaVazio(nome)) {
                    System.out.println("  Nome nao pode ser vazio!");
                } else if (!soLetras(nome)) {
                    System.out.println("  Use apenas letras no nome!");
                } else {
                    break;
                }
            }

            boolean nomeConfirmado = false;
            while (!nomeConfirmado) {
                System.out.println("\n  Nome: " + nome);
                System.out.print("  Confirmar nome? (sim/nao): ");
                String confirmar = removerEspacos(leia.nextLine());
                if (confirmar.equalsIgnoreCase("sim")) {
                    nomeConfirmado = true;
                } else if (confirmar.equalsIgnoreCase("nao")) {
                    while (true) {
                        System.out.print("  Seu nome: ");
                        nome = removerEspacos(leia.nextLine());
                        if (estaVazio(nome)) {
                            System.out.println("  Nome nao pode ser vazio!");
                        } else if (!soLetras(nome)) {
                            System.out.println("  Use apenas letras no nome!");
                        } else {
                            break;
                        }
                    }
                } else {
                    System.out.println("  Digite apenas 'sim' ou 'nao'!");
                }
            }

            System.out.println("\n  Bem-vindo, " + nome + "!");
            enderecoCliente = lerEndereco();

            boolean enderecoConfirmado = false;
            while (!enderecoConfirmado) {
                System.out.println("\n  Endereco: " + enderecoCliente);
                System.out.print("  Confirmar endereco? (sim/nao): ");
                String confirmar = removerEspacos(leia.nextLine());
                if (confirmar.equalsIgnoreCase("sim")) {
                    enderecoConfirmado = true;
                } else if (confirmar.equalsIgnoreCase("nao")) {
                    enderecoCliente = lerEndereco();
                } else {
                    System.out.println("  Digite apenas 'sim' ou 'nao'!");
                }
            }

            return nome;
        }
    }

    static String loginAdm() {
        int tentativas = 3;

        while (tentativas > 0) {
            System.out.print("\n  Login ADM: ");
            String login = removerEspacos(leia.nextLine());
            System.out.print("  Senha ADM: ");
            String senha = removerEspacos(leia.nextLine());

            if (login.equals(loginADM) && senha.equals(senhaADM)) {
                System.out.println("\n  Acesso liberado! Bem-vindo, Administrador.");
                return "ADM";
            }

            tentativas--;
            if (tentativas > 0) {
                System.out.println("  Login ou senha incorretos. Tentativas restantes: " + tentativas);
            }
        }

        System.out.println("\n  Acesso bloqueado. Encerrando...");
        return "BLOQUEADO";
    }

    public static void painelPrincipal(String usuario) {
        while (true) {
            System.out.println("\n===================================");

            if (usuario.equals("ADM")) {
                int op;
                do {
                    System.out.println("  PAINEL ADMINISTRADOR\n"
                            + "1 - Ver estoque\n"
                            + "2 - Repor estoque\n"
                            + "3 - Remover quantidade do estoque\n"
                            + "4 - Adicionar produto\n"
                            + "5 - Remover produto\n"
                            + "6 - Alterar produto\n"
                            + "7 - Alterar preco\n"
                            + "8 - Gerenciar atributo\n"
                            + "9 - Historico de vendas\n"
                            + "0 - Voltar ao login");

                    System.out.print("\n  Escolha (0 a 9): ");
                    op = lerInteiro(0, 9);

                    switch (op) {
                        case 1:
                            verEstoque();
                            voltarmenu();
                            break;
                        case 2:
                            reporEstoque();
                            voltarmenu();
                            break;
                        case 3:
                            removerQuantidadeEstoque();
                            voltarmenu();
                            break;
                        case 4:
                            adicionarProduto();
                            voltarmenu();
                            break;
                        case 5:
                            removerProduto();
                            voltarmenu();
                            break;
                        case 6:
                            alterarNome();
                            voltarmenu();
                            break;
                        case 7:
                            alterarPrecos();
                            voltarmenu();
                            break;
                        case 8:
                            gerenciarAtributos();
                            break;
                        case 9:
                            verHistoricoVendas();
                            voltarmenu();
                            break;
                        case 0:
                            // apenas sai do do-while; o while(true) externo chama fazerLogin() abaixo
                            break;
                    }
                } while (op != 0);

                if (op == 0) {
                    String novoUsuario = fazerLogin();
                    if (novoUsuario.equals("BLOQUEADO")) {
                        System.out.println("\n  Encerrando o sistema. Ate logo!");
                        return;
                    }
                    usuario = novoUsuario; // atualiza e repete o while(true)
                }

            } else {
                System.out.println("  Ola, " + usuario + "!");
                System.out.println("  Endereco: " + enderecoCliente);
                System.out.println("===================================");
                fazerPedido(usuario);
                return; // cliente finaliza o pedido e encerra
            }
        }
    }

    public static void verEstoque() {
        System.out.println("--ESTOQUE ATUAL--");
        for (int i = 0; i < totalProdutos; i++) {
            System.out.printf("  %-12s (R$ %6.2f) -> %d unidades%n",
                    nomeProduto[i], precoProduto[i], estoqueProduto[i]);
        }
    }

    public static void reporEstoque() {
        System.out.println("\n-- REPOR ESTOQUE --");
        for (int i = 0; i < totalProdutos; i++) {
            System.out.printf("  %d - %-12s (estoque atual: %d)%n", i + 1, nomeProduto[i], estoqueProduto[i]);
        }
        System.out.print("  Escolha (1 a " + totalProdutos + "): ");
        int op = lerInteiro(1, totalProdutos);
        int idx = op - 1;

        System.out.print("  Quantidade a adicionar: ");
        int quantidade = lerInteiro(1, 9999);

        estoqueProduto[idx] += quantidade;

        System.out.println("  Estoque atualizado! " + nomeProduto[idx] + " agora tem " + estoqueProduto[idx] + " unidades.");
    }

    public static void adicionarProduto() {
        System.out.println("\n-- ADICIONAR PRODUTO --");

        if (totalProdutos >= produtosmaximo) {
            System.out.println("  Catalogo cheio! Limite de " + produtosmaximo + " produtos atingido.");
            return;
        }

        String nome = "";
        while (true) {
            System.out.print("  Nome do produto: ");
            nome = removerEspacos(leia.nextLine());
            if (estaVazio(nome)) {
                System.out.println("  Nome nao pode ser vazio!");
            } else if (!soLetras(nome)) {
                System.out.println("  Use apenas letras!");
            } else {
                break;
            }
        }

        System.out.print("  Preco: R$ ");
        double preco = lerDouble(0.01, 9999.99);

        System.out.print("  Estoque inicial: ");
        int estoque = lerInteiro(0, 9999);

        nomeProduto[totalProdutos] = nome;
        precoProduto[totalProdutos] = preco;
        estoqueProduto[totalProdutos] = estoque;
        totalProdutos++;

        System.out.println("  Produto adicionado: " + nome + " | Preco: R$ " + preco + " | Estoque: " + estoque + " unidades.");
    }

    public static void removerProduto() {
        System.out.println("\n-- REMOVER PRODUTO --");
        for (int i = 0; i < totalProdutos; i++) {
            System.out.printf("  %d - %-12s (R$ %.2f | %d unidades)%n", i + 1, nomeProduto[i], precoProduto[i], estoqueProduto[i]);
        }
        System.out.print("  Escolha (1 a " + totalProdutos + "): ");
        int op = lerInteiro(1, totalProdutos);
        int idx = op - 1;

        System.out.println("  Removendo: " + nomeProduto[idx]);

        for (int i = idx; i < totalProdutos - 1; i++) {
            nomeProduto[i] = nomeProduto[i + 1];
            precoProduto[i] = precoProduto[i + 1];
            estoqueProduto[i] = estoqueProduto[i + 1];
        }

        totalProdutos--;
        System.out.println("  Produto removido! Catalogo agora tem " + totalProdutos + " produtos.");
    }

    public static void removerQuantidadeEstoque() {
        System.out.println("\n-- REMOVER QUANTIDADE DO ESTOQUE --");
        for (int i = 0; i < totalProdutos; i++) {
            System.out.printf("  %d - %-12s (estoque atual: %d)%n", i + 1, nomeProduto[i], estoqueProduto[i]);
        }
        System.out.print("  Escolha (1 a " + totalProdutos + "): ");
        int op = lerInteiro(1, totalProdutos);
        int idx = op - 1;

        if (estoqueProduto[idx] == 0) {
            System.out.println("  Estoque de " + nomeProduto[idx] + " ja esta zerado!");
            return;
        }

        System.out.print("  Quantidade a remover (max " + estoqueProduto[idx] + "): ");
        int quantidade = lerInteiro(1, estoqueProduto[idx]);

        estoqueProduto[idx] -= quantidade;

        System.out.println("  Estoque atualizado! " + nomeProduto[idx] + " agora tem " + estoqueProduto[idx] + " unidades.");
    }

    public static void alterarNome() {
        System.out.println("\n-- ALTERAR NOME DO PRODUTO --");
        for (int i = 0; i < totalProdutos; i++) {
            System.out.printf("  %d - %s%n", i + 1, nomeProduto[i]);
        }
        System.out.print("  Escolha (1 a " + totalProdutos + "): ");
        int op = lerInteiro(1, totalProdutos);
        int idx = op - 1;

        System.out.println("  Nome atual: " + nomeProduto[idx]);
        System.out.print("  Novo nome: ");
        String novoNome = "";
        while (true) {
            novoNome = removerEspacos(leia.nextLine());
            if (estaVazio(novoNome)) {
                System.out.print("  Nome nao pode ser vazio! Tente novamente: ");
            } else if (!soLetras(novoNome)) {
                System.out.print("  Use apenas letras! Tente novamente: ");
            } else {
                break;
            }
        }

        nomeProduto[idx] = novoNome;
        System.out.println("  Nome atualizado! Produto agora se chama: " + nomeProduto[idx]);
    }

    // Renomeado de alterarPreços para alterarPrecos (sem acento, evita encoding issues)
    public static void alterarPrecos() {
        System.out.println("\n-- ALTERAR PRECOS --");
        for (int i = 0; i < totalProdutos; i++) {
            System.out.printf("  %d - %-12s (atual: R$ %.2f)%n", i + 1, nomeProduto[i], precoProduto[i]);
        }
        System.out.print("  Escolha (1 a " + totalProdutos + "): ");
        int op = lerInteiro(1, totalProdutos);

        int idx = op - 1;

        System.out.print("  Novo preco para " + nomeProduto[idx] + ": R$ ");
        double novoPreco = lerDouble(0.01, 9999.99);

        precoProduto[idx] = novoPreco;

        System.out.printf("  Preco atualizado! %s agora custa R$ %.2f%n", nomeProduto[idx], precoProduto[idx]);
    }

    public static void gerenciarAtributos() {
        int op;
        do {
            System.out.println("\n-- GERENCIAR ATRIBUTOS --\n"
                    + "1 - Gerenciar cores\n"
                    + "2 - Gerenciar tamanhos\n"
                    + "3 - Gerenciar estampas\n"
                    + "0 - Voltar");
            System.out.print("\n  Escolha (0 a 3): ");
            op = lerInteiro(0, 3);

            switch (op) {
                case 1:
                    gerenciarCores();
                    break;
                case 2:
                    gerenciarTamanhos();
                    break;
                case 3:
                    gerenciarEstampas();
                    break;
            }
        } while (op != 0);
    }

    public static void gerenciarCores() {
        int op;
        do {
            System.out.println("\n-- GERENCIAR CORES --\n"
                    + "1 - Ver cores\n"
                    + "2 - Adicionar cor\n"
                    + "3 - Remover cor\n"
                    + "4 - Alterar cor\n"
                    + "0 - Voltar");
            System.out.print("\n  Escolha (0 a 4): ");
            op = lerInteiro(0, 4);

            switch (op) {
                case 1:
                    for (int i = 0; i < totalCores; i++) {
                        System.out.printf("  %d - %s%n", i + 1, cores[i]);
                    }
                    voltarmenu();
                    break;

                case 2:
                    if (totalCores >= cores.length) {
                        System.out.println("  Limite de " + cores.length + " cores atingido!");
                        voltarmenu();
                        break;
                    }
                    System.out.print("  Nome da nova cor: ");
                    String novaCor = "";
                    while (true) {
                        novaCor = removerEspacos(leia.nextLine());
                        if (estaVazio(novaCor)) {
                            System.out.print("  Nome nao pode ser vazio! Tente novamente: ");
                        } else if (!soLetras(novaCor)) {
                            System.out.print("  Use apenas letras! Tente novamente: ");
                        } else {
                            break;
                        }
                    }
                    cores[totalCores] = novaCor;
                    totalCores++;
                    System.out.println("  Cor adicionada: " + novaCor);
                    voltarmenu();
                    break;

                case 3:
                    if (totalCores == 0) {
                        System.out.println("  Nenhuma cor cadastrada!");
                        voltarmenu();
                        break;
                    }
                    for (int i = 0; i < totalCores; i++) {
                        System.out.printf("  %d - %s%n", i + 1, cores[i]);
                    }
                    System.out.print("  Escolha (1 a " + totalCores + "): ");
                    int idxRemoverC = lerInteiro(1, totalCores) - 1;
                    System.out.println("  Removendo: " + cores[idxRemoverC]);
                    for (int i = idxRemoverC; i < totalCores - 1; i++) {
                        cores[i] = cores[i + 1];
                    }
                    totalCores--;
                    System.out.println("  Cor removida!");
                    voltarmenu();
                    break;

                case 4:
                    if (totalCores == 0) {
                        System.out.println("  Nenhuma cor cadastrada!");
                        voltarmenu();
                        break;
                    }
                    for (int i = 0; i < totalCores; i++) {
                        System.out.printf("  %d - %s%n", i + 1, cores[i]);
                    }
                    System.out.print("  Escolha (1 a " + totalCores + "): ");
                    int idxAlterarC = lerInteiro(1, totalCores) - 1;
                    System.out.print("  Novo nome: ");
                    String nomeAlteradoC = "";
                    while (true) {
                        nomeAlteradoC = removerEspacos(leia.nextLine());
                        if (estaVazio(nomeAlteradoC)) {
                            System.out.print("  Nome nao pode ser vazio! Tente novamente: ");
                        } else if (!soLetras(nomeAlteradoC)) {
                            System.out.print("  Use apenas letras! Tente novamente: ");
                        } else {
                            break;
                        }
                    }
                    cores[idxAlterarC] = nomeAlteradoC;
                    System.out.println("  Cor atualizada: " + nomeAlteradoC);
                    voltarmenu();
                    break;
            }
        } while (op != 0);
    }

    public static void gerenciarTamanhos() {
        int op;
        do {
            System.out.println("\n-- GERENCIAR TAMANHOS --\n"
                    + "1 - Ver tamanhos\n"
                    + "2 - Adicionar tamanho\n"
                    + "3 - Remover tamanho\n"
                    + "4 - Alterar tamanho\n"
                    + "0 - Voltar");
            System.out.print("\n  Escolha (0 a 4): ");
            op = lerInteiro(0, 4);

            switch (op) {
                case 1:
                    for (int i = 0; i < totalTamanhos; i++) {
                        System.out.printf("  %d - %s%n", i + 1, tamanhos[i]);
                    }
                    voltarmenu();
                    break;

                case 2:
                    if (totalTamanhos >= tamanhos.length) {
                        System.out.println("  Limite de " + tamanhos.length + " tamanhos atingido!");
                        voltarmenu();
                        break;
                    }
                    System.out.print("  Nome do novo tamanho: ");
                    String novoTamanho = "";
                    while (true) {
                        novoTamanho = removerEspacos(leia.nextLine());
                        if (estaVazio(novoTamanho)) {
                            System.out.print("  Nome nao pode ser vazio! Tente novamente: ");
                        } else if (!soLetras(novoTamanho)) {
                            System.out.print("  Use apenas letras! Tente novamente: ");
                        } else {
                            break;
                        }
                    }
                    tamanhos[totalTamanhos] = novoTamanho;
                    totalTamanhos++;
                    System.out.println("  Tamanho adicionado: " + novoTamanho);
                    voltarmenu();
                    break;

                case 3:
                    if (totalTamanhos == 0) {
                        System.out.println("  Nenhum tamanho cadastrado!");
                        voltarmenu();
                        break;
                    }
                    for (int i = 0; i < totalTamanhos; i++) {
                        System.out.printf("  %d - %s%n", i + 1, tamanhos[i]);
                    }
                    System.out.print("  Escolha (1 a " + totalTamanhos + "): ");
                    int idxRemoverT = lerInteiro(1, totalTamanhos) - 1;
                    System.out.println("  Removendo: " + tamanhos[idxRemoverT]);
                    for (int i = idxRemoverT; i < totalTamanhos - 1; i++) {
                        tamanhos[i] = tamanhos[i + 1];
                    }
                    totalTamanhos--;
                    System.out.println("  Tamanho removido!");
                    voltarmenu();
                    break;

                case 4:
                    if (totalTamanhos == 0) {
                        System.out.println("  Nenhum tamanho cadastrado!");
                        voltarmenu();
                        break;
                    }
                    for (int i = 0; i < totalTamanhos; i++) {
                        System.out.printf("  %d - %s%n", i + 1, tamanhos[i]);
                    }
                    System.out.print("  Escolha (1 a " + totalTamanhos + "): ");
                    int idxAlterarT = lerInteiro(1, totalTamanhos) - 1;
                    System.out.print("  Novo nome: ");
                    String nomeAlteradoT = "";
                    while (true) {
                        nomeAlteradoT = removerEspacos(leia.nextLine());
                        if (estaVazio(nomeAlteradoT)) {
                            System.out.print("  Nome nao pode ser vazio! Tente novamente: ");
                        } else if (!soLetras(nomeAlteradoT)) {
                            System.out.print("  Use apenas letras! Tente novamente: ");
                        } else {
                            break;
                        }
                    }
                    tamanhos[idxAlterarT] = nomeAlteradoT;
                    System.out.println("  Tamanho atualizado: " + nomeAlteradoT);
                    voltarmenu();
                    break;
            }
        } while (op != 0);
    }

    public static void gerenciarEstampas() {
        int op;
        do {
            System.out.println("\n-- GERENCIAR ESTAMPAS --\n"
                    + "1 - Ver estampas\n"
                    + "2 - Adicionar estampa\n"
                    + "3 - Remover estampa\n"
                    + "4 - Alterar nome da estampa\n"
                    + "5 - Alterar preco da estampa\n"
                    + "0 - Voltar");
            System.out.print("\n  Escolha (0 a 5): ");
            op = lerInteiro(0, 5);

            switch (op) {
                case 1:
                    for (int i = 0; i < totalEstampas; i++) {
                        System.out.printf("  %d - %-20s (+R$ %.2f)%n", i + 1, nomeEstampa[i], valorEstampa[i]);
                    }
                    voltarmenu();
                    break;

                case 2:
                    if (totalEstampas >= nomeEstampa.length) {
                        System.out.println("  Limite de " + nomeEstampa.length + " estampas atingido!");
                        voltarmenu();
                        break;
                    }
                    System.out.print("  Nome da nova estampa: ");
                    String novaEstampa = "";
                    while (true) {
                        novaEstampa = removerEspacos(leia.nextLine());
                        if (estaVazio(novaEstampa)) {
                            System.out.print("  Nome nao pode ser vazio! Tente novamente: ");
                        } else if (!soLetras(novaEstampa)) {
                            System.out.print("  Use apenas letras! Tente novamente: ");
                        } else {
                            break;
                        }
                    }
                    System.out.print("  Preco da estampa: R$ ");
                    double novoPrecoE = lerDouble(0.00, 9999.99);

                    nomeEstampa[totalEstampas] = nomeEstampa[totalEstampas - 1];
                    valorEstampa[totalEstampas] = valorEstampa[totalEstampas - 1];
                    nomeEstampa[totalEstampas - 1] = novaEstampa;
                    valorEstampa[totalEstampas - 1] = novoPrecoE;
                    totalEstampas++;

                    System.out.println("  Estampa adicionada: " + novaEstampa);
                    voltarmenu();
                    break;

                case 3:
                    if (totalEstampas <= 1) {
                        System.out.println("  Nenhuma estampa removivel!");
                        voltarmenu();
                        break;
                    }
                    System.out.println("  Escolha a estampa para remover:");
                    for (int i = 0; i < totalEstampas - 1; i++) {
                        System.out.printf("  %d - %-20s (+R$ %.2f)%n", i + 1, nomeEstampa[i], valorEstampa[i]);
                    }
                    System.out.print("  Escolha (1 a " + (totalEstampas - 1) + "): ");
                    int idxRemoverE = lerInteiro(1, totalEstampas - 1) - 1;
                    System.out.println("  Removendo: " + nomeEstampa[idxRemoverE]);
                    for (int i = idxRemoverE; i < totalEstampas - 1; i++) {
                        nomeEstampa[i] = nomeEstampa[i + 1];
                        valorEstampa[i] = valorEstampa[i + 1];
                    }
                    totalEstampas--;
                    System.out.println("  Estampa removida!");
                    voltarmenu();
                    break;

                case 4:
                    if (totalEstampas <= 1) {
                        System.out.println("  Nenhuma estampa alteravel!");
                        voltarmenu();
                        break;
                    }
                    for (int i = 0; i < totalEstampas - 1; i++) {
                        System.out.printf("  %d - %s%n", i + 1, nomeEstampa[i]);
                    }
                    System.out.print("  Escolha (1 a " + (totalEstampas - 1) + "): ");
                    int idxAlterarE = lerInteiro(1, totalEstampas - 1) - 1;
                    System.out.print("  Novo nome: ");
                    String nomeAlteradoE = "";
                    while (true) {
                        nomeAlteradoE = removerEspacos(leia.nextLine());
                        if (estaVazio(nomeAlteradoE)) {
                            System.out.print("  Nome nao pode ser vazio! Tente novamente: ");
                        } else if (!soLetras(nomeAlteradoE)) {
                            System.out.print("  Use apenas letras! Tente novamente: ");
                        } else {
                            break;
                        }
                    }
                    nomeEstampa[idxAlterarE] = nomeAlteradoE;
                    System.out.println("  Estampa atualizada: " + nomeAlteradoE);
                    voltarmenu();
                    break;

                case 5:
                    if (totalEstampas <= 1) {
                        System.out.println("  Nenhuma estampa com preco alteravel!");
                        voltarmenu();
                        break;
                    }
                    for (int i = 0; i < totalEstampas - 1; i++) {
                        System.out.printf("  %d - %-20s (atual: R$ %.2f)%n", i + 1, nomeEstampa[i], valorEstampa[i]);
                    }
                    System.out.print("  Escolha (1 a " + (totalEstampas - 1) + "): ");
                    int idxPreco = lerInteiro(1, totalEstampas - 1) - 1;
                    System.out.print("  Novo preco: R$ ");
                    valorEstampa[idxPreco] = lerDouble(0.00, 9999.99);
                    System.out.printf("  Preco atualizado! %s agora custa R$ %.2f%n",
                            nomeEstampa[idxPreco], valorEstampa[idxPreco]);
                    voltarmenu();
                    break;
            }
        } while (op != 0);
    }

    public static void verHistoricoVendas() {
        System.out.println("--HISTORICO DE VENDAS--");

        if (totalVendas == 0) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        for (int i = 0; i < totalVendas; i++) {
            System.out.printf("  Venda #%d | Cliente: %-12s | Itens: %d | Total: R$ %.2f%n",
                    i + 1, historicoCliente[i], historicoItens[i], historicoValor[i]);
        }

        System.out.printf("%n  Total geral de vendas: R$ %.2f%n", totalGeralVendas);
    }

    public static void fazerPedido(String nomeCliente) {
        int[][] carrinho = new int[50][4];
        int cont = 0;
        boolean pedidoAtivo = true;
        String formaPagamento = "";

        // conta quantas unidades de cada produto ja estao no carrinho
        int[] qtdNoCarrinho = new int[produtosmaximo];

        while (pedidoAtivo) {

            // --- Produto ---
            int idxProdEscolhido = -1;
            int opProd = -1;
            while (idxProdEscolhido == -1) {
                if (opProd == -1) {
                    for (int i = 0; i < totalProdutos; i++) {
                        // mostra o estoque disponivel descontando o que ja esta no carrinho
                        int disponivelReal = estoqueProduto[i] - qtdNoCarrinho[i];
                        System.out.printf("  %d - %-12s R$ %.2f  (estoque: %d)%n",
                                i + 1, nomeProduto[i], precoProduto[i], disponivelReal);
                    }
                    System.out.print("\n  Escolha o produto (1 a " + totalProdutos + "): ");
                    opProd = lerInteiro(1, totalProdutos);
                    int disponivelReal = estoqueProduto[opProd - 1] - qtdNoCarrinho[opProd - 1];
                    if (disponivelReal == 0) {
                        System.out.println("  Produto sem estoque! Escolha outro.");
                        opProd = -1;
                        continue;
                    }
                    System.out.println("  Produto escolhido: " + nomeProduto[opProd - 1]);
                }
                System.out.print("  Confirmar? (sim/nao): ");
                String conf = removerEspacos(leia.nextLine());
                if (estaVazio(conf)) {
                    System.out.println("  Digite 'sim' ou 'nao'!");
                } else if (conf.equalsIgnoreCase("sim")) {
                    idxProdEscolhido = opProd - 1;
                } else if (conf.equalsIgnoreCase("nao")) {
                    opProd = -1;
                } else {
                    System.out.println("  Digite apenas 'sim' ou 'nao'!");
                }
            }
            carrinho[cont][0] = idxProdEscolhido;

            // --- Cor ---
            int idxCorEscolhida = -1;
            int opCor = -1;
            while (idxCorEscolhida == -1) {
                if (opCor == -1) {
                    System.out.println("\n  Cores disponiveis:");
                    for (int i = 0; i < totalCores; i++) {
                        System.out.printf("  %d - %s%n", i + 1, cores[i]);
                    }
                    System.out.print("\n  Escolha a cor (1 a " + totalCores + "): ");
                    opCor = lerInteiro(1, totalCores);
                    System.out.println("  Cor escolhida: " + cores[opCor - 1]);
                }
                System.out.print("  Confirmar? (sim/nao): ");
                String conf = removerEspacos(leia.nextLine());
                if (estaVazio(conf)) {
                    System.out.println("  Digite 'sim' ou 'nao'!");
                } else if (conf.equalsIgnoreCase("sim")) {
                    idxCorEscolhida = opCor - 1;
                } else if (conf.equalsIgnoreCase("nao")) {
                    opCor = -1;
                } else {
                    System.out.println("  Digite apenas 'sim' ou 'nao'!");
                }
            }
            carrinho[cont][1] = idxCorEscolhida;

            // --- Tamanho ---
            int idxTamEscolhido = -1;
            int opTam = -1;
            while (idxTamEscolhido == -1) {
                if (opTam == -1) {
                    System.out.println("\n  Tamanhos disponiveis:");
                    for (int i = 0; i < totalTamanhos; i++) {
                        System.out.printf("  %d - %s%n", i + 1, tamanhos[i]);
                    }
                    System.out.print("\n  Escolha o tamanho (1 a " + totalTamanhos + "): ");
                    opTam = lerInteiro(1, totalTamanhos);
                    System.out.println("  Tamanho escolhido: " + tamanhos[opTam - 1]);
                }
                System.out.print("  Confirmar? (sim/nao): ");
                String conf = removerEspacos(leia.nextLine());
                if (estaVazio(conf)) {
                    System.out.println("  Digite 'sim' ou 'nao'!");
                } else if (conf.equalsIgnoreCase("sim")) {
                    idxTamEscolhido = opTam - 1;
                } else if (conf.equalsIgnoreCase("nao")) {
                    opTam = -1;
                } else {
                    System.out.println("  Digite apenas 'sim' ou 'nao'!");
                }
            }
            carrinho[cont][2] = idxTamEscolhido;

            // --- Estampa ---
            int idxEstampaEscolhida = -1;
            int opEst = -1;
            while (idxEstampaEscolhida == -1) {
                if (opEst == -1) {
                    System.out.println("\n  Estampas disponiveis:");
                    for (int i = 0; i < totalEstampas; i++) {
                        System.out.printf("  %d - %-20s (+R$ %.2f)%n", i + 1, nomeEstampa[i], valorEstampa[i]);
                    }
                    System.out.print("\n  Escolha a estampa (1 a " + totalEstampas + "): ");
                    opEst = lerInteiro(1, totalEstampas);
                    System.out.println("  Estampa escolhida: " + nomeEstampa[opEst - 1]);
                }
                System.out.print("  Confirmar? (sim/nao): ");
                String conf = removerEspacos(leia.nextLine());
                if (estaVazio(conf)) {
                    System.out.println("  Digite 'sim' ou 'nao'!");
                } else if (conf.equalsIgnoreCase("sim")) {
                    idxEstampaEscolhida = opEst - 1;
                } else if (conf.equalsIgnoreCase("nao")) {
                    opEst = -1;
                } else {
                    System.out.println("  Digite apenas 'sim' ou 'nao'!");
                }
            }
            carrinho[cont][3] = idxEstampaEscolhida;

            // --- Nome personalizado ---
            if (nomeEstampa[idxEstampaEscolhida].equals("Nome personalizado")) {
                System.out.print("  Digite o nome para a estampa: ");
                String nomePersonalizado = "";
                while (true) {
                    nomePersonalizado = removerEspacos(leia.nextLine());
                    if (estaVazio(nomePersonalizado)) {
                        System.out.print("  Nome nao pode ser vazio! Tente novamente: ");
                    } else {
                        break;
                    }
                }
                carrinhoNomeEstampa[cont] = nomePersonalizado;
            } else {
                carrinhoNomeEstampa[cont] = "";
            }

            cont++;

            // --- Resumo do item ---
            int idxP = carrinho[cont - 1][0];
            int idxC = carrinho[cont - 1][1];
            int idxT = carrinho[cont - 1][2];
            int idxE = carrinho[cont - 1][3];

            String estampaExibir;
            if (nomeEstampa[idxE].equals("Nome personalizado")) {
                estampaExibir = carrinhoNomeEstampa[cont - 1];
            } else {
                estampaExibir = nomeEstampa[idxE];
            }

            System.out.println("\n  --- Resumo do item ---");
            System.out.println("  Produto : " + nomeProduto[idxP]);
            System.out.println("  Cor     : " + cores[idxC]);
            System.out.println("  Tamanho : " + tamanhos[idxT]);
            System.out.println("  Estampa : " + estampaExibir);
            System.out.printf("  Preco   : R$ %.2f%n", precoProduto[idxP] + valorEstampa[idxE]);
            System.out.println("\n  1 - Adicionar ao carrinho");
            System.out.println("  2 - Recomecar escolha");
            System.out.print("  Escolha (1 a 2): ");
            int opResumo = lerInteiro(1, 2);

            if (opResumo == 2) {
                cont--;
                continue;
            }

            // registra que mais uma unidade deste produto esta no carrinho
            qtdNoCarrinho[carrinho[cont - 1][0]]++;

            // --- Menu ---
            System.out.println("\n  O que deseja fazer?");
            System.out.println("  1 - Continuar comprando");
            System.out.println("  2 - Finalizar pedido");
            System.out.println("  3 - Remover ultimo item e finalizar");
            System.out.println("  4 - Remover ultimo item e continuar comprando");
            System.out.print("  Escolha (1 a 4): ");
            int op = lerInteiro(1, 4);

            switch (op) {
                case 1:
                    break;

                case 2:
                    boolean finalizando = true;
                    while (finalizando) {
                        System.out.println("\n-- FORMA DE PAGAMENTO --");
                        System.out.println("  1 - Pix");
                        System.out.println("  2 - Cartao de credito");
                        System.out.print("  Escolha (1 a 2): ");
                        int pagamento = lerInteiro(1, 2);
                        formaPagamento = (pagamento == 1) ? "Pix" : "Cartao de credito";

                        double totalPreview = 0;
                        for (int i = 0; i < cont; i++) {
                            totalPreview += precoProduto[carrinho[i][0]] + valorEstampa[carrinho[i][3]];
                        }
                        System.out.printf("%n  Total    : R$ %.2f%n", totalPreview);
                        System.out.println("  Pagamento: " + formaPagamento);

                        boolean confirmacaoValida = false;
                        String confirmar = "";
                        while (!confirmacaoValida) {
                            System.out.print("\n  Confirmar pedido? (sim/nao): ");
                            confirmar = removerEspacos(leia.nextLine());
                            if (estaVazio(confirmar)) {
                                System.out.println("  Digite 'sim' ou 'nao'!");
                            } else if (confirmar.equalsIgnoreCase("sim") || confirmar.equalsIgnoreCase("nao")) {
                                confirmacaoValida = true;
                            } else {
                                System.out.println("  Digite apenas 'sim' ou 'nao'!");
                            }
                        }

                        if (confirmar.equalsIgnoreCase("sim")) {
                            pedidoAtivo = false;
                            finalizando = false;
                        } else {
                            System.out.println("\n  O que deseja fazer?");
                            System.out.println("  1 - Mudar forma de pagamento");
                            System.out.println("  2 - Continuar comprando");
                            System.out.print("  Escolha (1 a 2): ");
                            int opNao = lerInteiro(1, 2);
                            if (opNao == 2) {
                                finalizando = false;
                            }
                            // se opNao == 1, finalizando continua true e volta pro topo
                        }
                    }
                    break;

                case 3:
                case 4:
                    // remove uma unidade do controle do carrinho
                    qtdNoCarrinho[carrinho[cont - 1][0]]--;
                    cont--;
                    System.out.println("  Item removido!");

                    if (cont == 0) {
                        boolean carrinhoVazioResolvido = false;
                        while (!carrinhoVazioResolvido) {
                            System.out.println("\n  Carrinho vazio! O que deseja fazer?");
                            System.out.println("  1 - Adicionar item");
                            System.out.println("  2 - Voltar ao login");
                            System.out.println("  3 - Encerrar sistema");
                            System.out.print("  Escolha (1 a 3): ");
                            int opVazio = lerInteiro(1, 3);
                            switch (opVazio) {
                                case 1:
                                    carrinhoVazioResolvido = true;
                                    break;
                                case 2:
                                    carrinhoVazioResolvido = true;
                                    pedidoAtivo = false;
                                    String novoUsuario = fazerLogin();
                                    if (!novoUsuario.equals("BLOQUEADO")) {
                                       painelPrincipal(novoUsuario);
                                    } else {
                                        System.out.println("\n  Encerrando o sistema. Ate logo!");
                                    }
                                    break;
                                case 3:
                                    carrinhoVazioResolvido = true;
                                    pedidoAtivo = false;
                                    System.out.println("\n  Encerrando o sistema. Ate logo!");
                                    System.exit(0);
                                    break;
                            }
                        }
                    } else {
                        if (op == 3) {
                            pedidoAtivo = false;
                        }
                    }
                    break;
            }

        } // fecha while(pedidoAtivo)

        if (cont == 0) {
            return;
        }

        // estoque ja foi decrementado ao adicionar ao carrinho, apenas exibe o resumo
        System.out.println("\n-- RESUMO DO PEDIDO --");
        double total = 0;
        for (int i = 0; i < cont; i++) {
            int idxProd = carrinho[i][0];
            int idxCor = carrinho[i][1];
            int idxTam = carrinho[i][2];
            int idxEstampa = carrinho[i][3];

            double preco = precoProduto[idxProd] + valorEstampa[idxEstampa];
            total += preco;
            estoqueProduto[idxProd]--;

            String nomeEstampaExibir;
            if (nomeEstampa[idxEstampa].equals("Nome personalizado")) {
                nomeEstampaExibir = carrinhoNomeEstampa[i];
            } else {
                nomeEstampaExibir = nomeEstampa[idxEstampa];
            }

            System.out.printf("  Item %d: %-12s | %s | %s | %-20s | R$ %.2f%n",
                    i + 1, nomeProduto[idxProd], cores[idxCor],
                    tamanhos[idxTam], nomeEstampaExibir, preco);
        }
        System.out.printf("%n  Total: R$ %.2f%n", total);

        historicoCliente[totalVendas] = nomeCliente;
        historicoItens[totalVendas] = cont;
        historicoValor[totalVendas] = total;
        totalVendas++;
        totalGeralVendas += total;

        System.out.println("\n  Pedido registrado! Obrigado, " + nomeCliente + "!");
        System.out.println("  Pagamento: " + formaPagamento);

        System.out.println("\n  O que deseja fazer?");
        System.out.println("  1 - Voltar ao login");
        System.out.println("  0 - Encerrar sistema");
        System.out.print("  Escolha (0 ou 1): ");
        int opFinal = lerInteiro(0, 1);

        if (opFinal == 1) {
            String novoUsuario = fazerLogin();
            if (!novoUsuario.equals("BLOQUEADO")) {
                painelPrincipal(novoUsuario);
            } else {
                System.out.println("\n  Encerrando o sistema. Ate logo!");
            }
        } else {
            System.out.println("\n  Encerrando o sistema. Ate logo!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Produtos();
        inicializarAtributos();
        String usuario = fazerLogin();

        if (usuario.equals("BLOQUEADO")) {
            System.out.println("\n  Encerrando o sistema. Ate logo!");
            return;
        }
        painelPrincipal(usuario);
        System.out.println("\n  Encerrando o sistema. Ate logo!");
    }
}