package models;

public class Contato {

    private String nome;
    String telefone;
    String email;

    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void alterarNome(String nome) {
        this.nome = nome;
    }

    public void alterarTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void alterarEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nome + ", Telefone: " + telefone + ", Email: " + email;
    }
}
