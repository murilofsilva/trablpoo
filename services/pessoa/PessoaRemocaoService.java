package services.pessoa;

import modelos.Pessoa;
import repositories.PessoaRepository;
import services.MenuService;
import util.ConsoleResources;

import java.util.List;
import java.util.Objects;

public class PessoaRemocaoService {
    private static final ConsoleResources consoleResources = new ConsoleResources();
    private static final PessoaRepository pessoaRepository = new PessoaRepository();
    private static final AuxiliarService auxiliarService = new AuxiliarService();
    private static final CoachService coachService = new CoachService();
    private static final GerenteService gerenteService = new GerenteService();
    private static final JogadorService jogadorService = new JogadorService();
    private static final OrganizadorService organizadorService = new OrganizadorService();


    public void remover(String context) {
        String cpfCnpj = consoleResources.getStringFromConsole("Informe o cpf/cnpj do " + context);
        Pessoa pessoa = filtrar(PessoaRepository.pessoas, context, cpfCnpj);
        if (Objects.isNull(pessoa)) {
            System.out.println("Cpf/cnpj não existe no sistema! Tente novamente.");
            remover(context);
        }
        pessoaRepository.remover(pessoa);
        System.out.println("Remoção realizada com sucesso!");
        MenuService.processaMenu();
    }

    private Pessoa filtrar(List<Pessoa> pessoas, String context, String cpfCnpj) {
        Pessoa pessoa = null;
        switch (context) {
            case "auxiliar":
                pessoa = auxiliarService.filtrar(pessoas).stream().filter(a -> a.getCpfCnpj().equals(cpfCnpj)).findFirst().orElse(null);
                break;
            case "coach":
                pessoa = coachService.filtrar(pessoas).stream().filter(a -> a.getCpfCnpj().equals(cpfCnpj)).findFirst().orElse(null);
                break;
            case "gerente":
                pessoa = gerenteService.filtrar(pessoas).stream().filter(a -> a.getCpfCnpj().equals(cpfCnpj)).findFirst().orElse(null);
                break;
            case "jogador":
                pessoa = jogadorService.filtrar(pessoas).stream().filter(a -> a.getCpfCnpj().equals(cpfCnpj)).findFirst().orElse(null);
                break;
            case "organizador":
                pessoa = organizadorService.filtrar(pessoas).stream().filter(a -> a.getCpfCnpj().equals(cpfCnpj)).findFirst().orElse(null);
                break;
        }
        return pessoa;
    }
}
