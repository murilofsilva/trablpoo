package services.pessoa;

import modelos.Jogador;
import modelos.Jogo;
import modelos.Localidade;
import modelos.Pessoa;
import repositories.JogoRepository;
import repositories.PessoaRepository;
import services.MenuService;
import util.ConsoleResources;
import util.DataResources;
import util.InscricaoResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JogadorService extends PessoaService {
    private static final String SWITCH_ITEM_DEFAULT_MESSAGE = "\nDigite a posição do item que deseja (considere que o primeiro elemento está na primeira posição): ";
    private final static ConsoleResources consoleResources = new ConsoleResources();
    private final static InscricaoResources inscricaoResources = new InscricaoResources();
    private static final JogoRepository jogoRepository = new JogoRepository();
    private static final PessoaRepository pessoaRepository = new PessoaRepository();

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização de jogador");

        while (true) {
            List<Pessoa> pessoas = buscarPessoasPorNome();
            List<Pessoa> jogadores = filtrar(pessoas);
            if (jogadores.size() > 0) {
                imprimirPessoasEncontradas(jogadores);
                break;
            }
            System.out.println("Nenhum jogador encontrado com esse nome!");
        }

        while (true) {
            Pessoa jogador = buscarPessoaPorCPF();
            if (jogador != null) {
                ConsoleResources.pularVariasLinhas();
                ConsoleResources.exibirTitulo("informações do jogador");
                imprimirInformacoesPessoa(jogador);
                break;
            }
            System.out.println("Nenhum jogador encontrado com esse CPF!");
        }
    }

    public void criar() {
        try {
            if (JogoRepository.jogos.isEmpty()) {
                System.out.println("Impossível cadastrar jogadores no momento pois não há jogos cadastrados no sistema.");
                MenuService.processaMenu();
            }

            ConsoleResources.exibirTitulo("Cadastro de jogador");
            String nome = consoleResources.getStringFromConsole("Informe o nome do Jogador: ");
            String cpfCnpj = inscricaoResources.getAndValidateCpfCnpj("jogador");
            LocalDate dataNascimento = DataResources.getAndValidateDate("Informe a data de nascimento do jogador: ");
            String pais = consoleResources.getStringFromConsole("Informe o país do jogador: ");
            String estado = consoleResources.getStringFromConsole("Informe o estado do jogador: ");
            String municipio = consoleResources.getStringFromConsole("Informe o município do jogador: ");
            String userName = getUserName();
            Jogo jogo = getJogo();

            Localidade localidade = new Localidade(pais, municipio, estado);
            Jogador jogador = new Jogador(nome, cpfCnpj, dataNascimento, localidade, userName, jogo);

            pessoaRepository.salvar(jogador);
        } catch (Exception e) {
            System.out.println("Ocorreram erros ao cadastrar um jogador. Entre em contato com o suporte.");
        }
    }

    private String getUserName() {
        String username = consoleResources.getStringFromConsole("Informe o nome de usuário do jogador: ");
        List<Jogador> jogadores = filtrar(PessoaRepository.pessoas).stream().map(pessoa -> (Jogador) pessoa).collect(Collectors.toList());
        if (!jogadores.stream().filter(j -> j.getNomeUsuario().equals(username)).collect(Collectors.toList()).isEmpty()) {
            System.out.println("Nome de usuário já está em uso! Tente novamente.");
            getUserName();
        }
        return username;
    }

    public void editar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("edição de jogador");

        String username = consoleResources.getStringFromConsole("Informe o nome de usuário do jogador que deseja editar: ");
        List<Jogador> jogadores = filtrar(PessoaRepository.pessoas).stream().map(j -> (Jogador) j).collect(Collectors.toList());
        Jogador jogador = jogadores.stream().filter(j -> j.getNomeUsuario().equals(username)).findFirst().orElse(null);

        if (Objects.isNull(jogador)) {
            System.out.println("Jogador não encontrado! Tente novamente.");
            editar();
        }

        int op = consoleResources.getNumberFromConsole("O que deseja editar?\n01 - Nome de usuário\n02 - Loacalidade");
        switch (op) {
            case 1:
                editarUserName(jogador, jogadores);
                break;
            case 2:
                editaLocalidade(jogador);
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                editar();
        }
    }

    private void editarUserName(Jogador jogador, List<Jogador> jogadores) {
        String newUserName = consoleResources.getStringFromConsole("Informe o novo nome de usuário: ");
        if (!jogadores.stream().filter(j -> j.getNomeUsuario().equals(newUserName)).collect(Collectors.toList()).isEmpty()) {
            System.out.println("Já existe um jogador com este nome de usuário! Tente novamente.");
            editarUserName(jogador, jogadores);
        }
        jogador.setNomeUsuario(newUserName);
    }

    private void editaLocalidade(Jogador jogador) {
        String pais = consoleResources.getStringFromConsole("Informe o país do jogador: ");
        String estado = consoleResources.getStringFromConsole("Informe o estado do jogador: ");
        String municipio = consoleResources.getStringFromConsole("Informe o municipio do jogador: ");
        jogador.setLocalidade(new Localidade(pais, municipio, estado));
    }

    public List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> jogadores = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Jogador) jogadores.add(pessoa);
        });
        return jogadores;
    }

    private Jogo getJogo() {
        List<Jogo> jogosEncontrados = jogoRepository.obter(consoleResources.getStringFromConsole("Informe o jogo do jogador, " +
                "os disponíveis são: " + JogoRepository.jogos.stream().map(Jogo::getNome).collect(Collectors.joining(", ")) + ": "));

        if (Objects.isNull(jogosEncontrados) || jogosEncontrados.isEmpty()) {
            System.out.println("Jogo não encontrado! Tente novamente.");
            getJogo();
        }

         /*
            O cenário abaixo jamais deve ocorrer, pois o cadastro de times não deve permitir que exista times duplicados no sistema.
            Mas caso ocorra alguma falha e isso venha acontecendo, existe essa validação que possibilita um segundo caminho para o usuário.
         */
        if (jogosEncontrados.size() > 1) {
            System.out.print("Foi encontrado mais de um jogo para este nome. São eles: " +
                    jogosEncontrados.stream().map(Jogo::getNome).collect(Collectors.joining(", ")));

            int item = consoleResources.getNumberFromConsole(SWITCH_ITEM_DEFAULT_MESSAGE);
            if (item > jogosEncontrados.size()) {
                System.out.println("Posição inválida! Tente novamente.");
                getJogo();
            }

            return jogosEncontrados.get(item - 1);
        }

        return jogosEncontrados.get(0);
    }

    public void remover() {
        pessoaRemocaoService.remover("jogador");
    }
}
