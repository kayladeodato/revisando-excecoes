import models.Contato;
import utils.GerenciadorContato;

public class Main {
    public static void main(String[] args) {

        GerenciadorContato gerenciadorContato = new GerenciadorContato();

        gerenciadorContato.adicionarContato(new Contato("Chico", "839999-6868","chico@email.com"));
        gerenciadorContato.adicionarContato(new Contato("Elis", "839999-7070","elis@email.com"));
        gerenciadorContato.adicionarContato(new Contato("Abel", "839999-7272","abel@email.com"));
        gerenciadorContato.adicionarContato(new Contato("Abel", "11111111","abel@email.com"));

        gerenciadorContato.visualizarAgenda();
        System.out.println();

        gerenciadorContato.removerContato("Abel", "11111111");

        gerenciadorContato.visualizarAgenda();
        System.out.println();

        gerenciadorContato.pesquisarContatoNome("Elis");
        System.out.println();
        gerenciadorContato.pesquisarContatoTelefone("839999-6868");
        System.out.println();
        gerenciadorContato.pesquisarContatoEmail("abell@email.com");
        System.out.println();

        gerenciadorContato.alterarNomeContato("Eliss", "Elisinha");
        System.out.println();
        gerenciadorContato.alterarTelefoneContato("839999-7272", "839999-9999");
        System.out.println();

        gerenciadorContato.alterarEmailContato("chicoo@email.com","chico_linguica@email.com");



    }
}