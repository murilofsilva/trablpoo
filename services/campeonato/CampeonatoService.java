package services.campeonato;

import java.util.List;

import interfaces.ItemMenu;
import modelos.Campeonato;
import repositories.CampeonatoRepository;
import util.ConsoleResources;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criar'");
    }

    public void editar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }
    
}
