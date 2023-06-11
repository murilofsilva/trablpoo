package services.campeonato;

import modelos.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import interfaces.ItemMenu;
import modelos.Campeonato;
import modelos.Jogo;
import modelos.Localidade;
import repositories.CampeonatoRepository;
import repositories.JogoRepository;
import util.ConsoleResources;
import util.DataResources;

public class CampeonatoService implements ItemMenu {
    private static final ConsoleResources consoleResources = new ConsoleResources();

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização dos campeonatos");
        List<Campeonato> campeonatos;

        while (true) {
            String nomePesquisado = consoleResources.getStringFromConsole("Informe o nome do campeonato: ");
            campeonatos = CampeonatoRepository.obter(nomePesquisado);
    
            if (campeonatos.size() > 0)
                break;

            System.out.println("Nenhum campeonato encontrado com esse nome!");
        }

        imprimirListagemCampeonatos(campeonatos);
        int id = consoleResources.getNumberFromConsole("Informe o ID do campeonato para visualizar informações detalhadas: ");

        for (Campeonato campeonato : campeonatos) {
            if (campeonato.getId() == id) {
                ConsoleResources.pularVariasLinhas();
                ConsoleResources.exibirTitulo("informações do campeonato");
                System.out.println(campeonato.obterInformacoesDetalhadas());
                ConsoleResources.pausarConsole();
            }
        }
    }

    private static void imprimirListagemCampeonatos(List<Campeonato> campeonatos) {
        for (Campeonato campeonato : campeonatos) {
            System.out.println(campeonato.obterInformacoes());
        }
    }

    public void criar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("cadastro de campeonato");

        String nome = consoleResources.getStringFromConsole("Informe o nome do campeonato: ");
        LocalDate dataInicio = DataResources.getAndValidateDate("Informe a data de início do campeonato: ");
        LocalDate dataFim = DataResources.getAndValidateDate("Informe a data de fim do campeonato: ");
        String pais = consoleResources.getStringFromConsole("Informe o país em que ocorrerá o campeonato: ");
        String estado = consoleResources.getStringFromConsole("Informe o estado em que ocorrerá o campeonato: ");
        String municipio = consoleResources.getStringFromConsole("Informe o município em que ocorrerá o campeonato: ");
        Jogo jogo;

        while (true) {
            String nomeJogo = consoleResources.getStringFromConsole("Informe o nome do jogo do campeonato: ");
            jogo = JogoRepository.obterPorNome(nomeJogo).get(0);
            
            if (jogo != null)
                break;

            System.out.println("Nenhum jogo encontrado com esse nome!");
        }

        Localidade localizacao = new Localidade(pais, estado, municipio);
        Campeonato campeonato = new Campeonato(nome, dataInicio, dataFim, localizacao, new ArrayList<Time>(), jogo);
        CampeonatoRepository.salvar(campeonato);
        System.out.println("Campeonato cadastrado com sucesso!");
        ConsoleResources.pausarConsole();
    }

    public void editar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }
    
}
