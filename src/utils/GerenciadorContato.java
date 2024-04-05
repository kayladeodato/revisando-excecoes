package utils;

import exceptions.EmailNotFoundException;
import models.Contato;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorContato {

    List<Contato> contatos = new ArrayList<>();
    int posicao = 0;

    public void adicionarContato(Contato contato) {
        if (contatos.contains(contato)) {
            System.out.println("O contato já existe.");
        } else {
            contatos.add(contato);
        }
    }

    public void removerContato(String contato, String telefone) {
        contatos.remove(buscarPosicaoPorNomeETelefone(contato, telefone));
    }

    // Usando exceção não checada e sendo tratada
    public void alterarNomeContato(String nome, String novoNome) {
        Contato contato = contatos.get(buscarPosicaoPorNome(nome));
        contato.alterarNome(novoNome);
        System.out.println(contato);
    }
    // Usando exceção não checada e não sendo tratada
    public void alterarTelefoneContato(String telefone, String novoTelefone) {
        Contato contato = contatos.get(buscarPosicaoPorTelefone(telefone));
        contato.alterarTelefone(novoTelefone);
        System.out.println(contato);
    }

    // Usando exceção não checada e sendo tratada
    public void alterarEmailContato(String email, String novoEmail) {
        try{
            Contato contato = contatos.get(buscarPosicaoPorNome(email));
            contato.alterarEmail(novoEmail);
            System.out.println(contato);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("O E-mail informado do Contato não foi localizado.");
        }
    }

    // Usando exceção não checada e sendo tratada
    public void pesquisarContatoNome(String nome) {
        try{
            Contato contatoPesquisado = contatos.get(buscarPosicaoPorNome(nome));
            System.out.println(contatoPesquisado);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("O nome informado não foi localizado.");
        }
    }

    // Usando exceção não checada e não sendo tratada
    public void pesquisarContatoTelefone(String telefone) {
        Contato contatoPesquisado = contatos.get(buscarPosicaoPorTelefone(telefone));
        System.out.println(contatoPesquisado);
    }

    // Tratando uma exceção personalizada EmailNotFound
    public void pesquisarContatoEmail(String email) {
        try {
            Contato contatoPesquisado = contatos.get(buscarPosicaoPorEmail(email));
            System.out.println(contatoPesquisado);


            /* Essa mensagem será impressa no console.
            Usando o return no método buscarPosicaoPorEmail -> throw new EmailNotFoundException("O email inserido não foi encontrado nos registros.");
            Dessa forma o programa continua sendo executado caso não encontre o e-mail informado.

        } catch (EmailNotFoundException e) {
          System.out.println("O email inserido não foi encontrado nos registros.");
        }*/

        /* Dessa forma o programa irá parar.
        Usando o return no método buscarPosicaoPorEmail -> return posicao;
        Como está retornando posição, o código é interrompido ao chegar no método.
        */

        } catch (EmailNotFoundException e) {
            System.out.println("Email não encontrado.");
        }

    }

    // lançando uma exceção não checada (estende de RuntimeException). Vamos tratar
    public int buscarPosicaoPorNome(String nome) throws RuntimeException {
        posicao = 0;
        for (Contato contato: contatos) {
            if (contato.getNome().equals(nome)) {
                break;
            }
            else {
                posicao++;
            }
        }

        return posicao;
    }

    // lançando uma exceção não checada (estende de RuntimeException). Não vamos tratar
    public int buscarPosicaoPorTelefone(String telefone) throws IndexOutOfBoundsException {
        posicao = 0;
        for (Contato contato: contatos) {
            if (contato.getTelefone().equals(telefone)) {
                break;
            }
            else {
                posicao++;
            }
        }

        return posicao;
    }

    // lançando uma exceção checada (EmailNotFoundException estende de Exception) na assinatura do método para indicar
    // que a responsabilidade de tratá-la é do método chamador.
    public int buscarPosicaoPorEmail(String email) throws EmailNotFoundException {
        posicao = 0;
        for (Contato contato: contatos) {
            if (contato.getEmail().equals(email)) {
                return posicao;
            }
            else {
                posicao++;
            }
        }
        return posicao;
    }

    // Lançando uma exceção não checada
    public int buscarPosicaoPorNomeETelefone(String nome, String telefone) {
        posicao = 0;
        for (Contato contato: contatos) {
            if (contato.getNome().equals(nome) && contato.getTelefone().equals(telefone)) {
                contatos.get(posicao);
                return posicao;
            }
            else {
                posicao++;
            }
        }

        throw new IndexOutOfBoundsException("O nome ou telefone não foi encontrado nos registros.");
    }

    public void visualizarAgenda() {
        for (Contato contato: contatos) {
            System.out.println(contato);
        }
    }

}
