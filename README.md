# Exceções em Java:

## Resumo sobre Exceções:

![](/assets/excecoes.png)

## Hierarquia da classe Throwable:
![](https://image.slidesharecdn.com/java13excecoes-1228391273316874-8/95/java-13-excecoes-35-728.jpg?cb=1233320054)  
<sub>Fonte: Slideshare</sub>

## Exemplos de uso:

### Lançando uma exceção não checada:
Repare no `throw` no retorno do método. Ou seja, se o método não retornar um resultado esperado ocasionando algum problema, uma exceção será lançada.

```java
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
```

Podemos lançar uma exceção usando na chamada do método. O que indica que quem for chamar vai ter que tratar. Porém como se trata de uma exceção não checada (ou verificada), é opcional o seu tratamento.  
**Não realizando o tratamento:**

```java
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

// Não sendo tratada
public void alterarTelefoneContato(String telefone, String novoTelefone) {
        Contato contato = contatos.get(buscarPosicaoPorTelefone(telefone));
        contato.alterarTelefone(novoTelefone);
        System.out.println(contato);
    }
```
**Realizando o tratamento:**
```java
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

// Sendo tratada
public void alterarNomeContato(String nome, String novoNome) {
        Contato contato = contatos.get(buscarPosicaoPorNome(nome));
        contato.alterarNome(novoNome);
        System.out.println(contato);
    }
```

### Exceção personalizada:
Podemos criar nossas exceções para que a aplicaçção fique mais personalizada a nossa regra de negócio.
```java
public class EmailNotFoundException extends Exception{

    public EmailNotFoundException(String msg) {
        super(msg);
    }
}

```

### Lançando uma exceção checada:

No código a seguir, o programa irá interromper o fluxo pois como método buscarPosicaoPorEmail está retornando a posição, ou seja um número inteiro.
```java

// Usando a nossa exceção personalizada criada acima:

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

public void pesquisarContatoEmail(String email) {
        try {
            Contato contatoPesquisado = contatos.get(buscarPosicaoPorEmail(email));
            System.out.println(contatoPesquisado);

        } catch (EmailNotFoundException e) {
            System.out.println("Email não encontrado.");
        }

    }
```
Neste outro caso, o programa continua pois foi lançada uma exceção através do `throw`. Será impressa no controle a mensagem do método não ter dado certo, mas não atrapalha o fluxo do programa.
```java

// Usando a nossa exceção personalizada:
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
        throw new EmailNotFoundException("O email inserido não foi encontrado nos registros.");
    }

public void pesquisarContatoEmail(String email) {
        try {
            Contato contatoPesquisado = contatos.get(buscarPosicaoPorEmail(email));
            System.out.println(contatoPesquisado);

        } catch (EmailNotFoundException e) {
          System.out.println("O email inserido não foi encontrado nos registros.");

    }
```

### Mútiplos catches, finally e try-with-resources  

**Múltiplos Catches e finally**  

Podemos capturar mais de um tipo de exceção através do catch. Isso pode assegurar em que caso uma exceção não seja chamada, uma outra irá chamar. 

```java
try {
// tentativa: código
}
// objeto do erro + nome dado 
catch (ArithmeticException e1) {
}
catch (ArrayIndexOutOfBoundException e2) 

// Uma outra forma de fazer multi catches, disponível a partir do Java 7:

try {
// tentativa: código
}
// objeto do erro + nome dado 
catch (ArithmeticException | ArrayIndexOutOfBoundException exc) {
}
```
O finally pode ser usado opcionalmente e significa que o código deve ser executado independentemente se uma exceção for lançada ou não. 

```java
try {
// tentativa: código
}
// objeto do erro + nome dado 
catch (ArithmeticException | ArrayIndexOutOfBoundException exc) {
}
finally {
 System.out.println("Mensagem que irá aparecer independentemente se lançar exceção ou não.");
```

**try-with-resources**  

É importante se atentar que um try sozinho não é válido, é exigido pelo menos um `catch` ou um `finally`.
O try-with-resources é veio apartir do Java 7 e simplifica o uso de recursos que precisam ser fechados após o uso (ver interface `AutoCloseable`), garantindo que sejam fechados automaticamente após a execução de um bloco try, mesmo em caso de exceção. 
Com isso, elimina a necessidade de blocos finally para fechar recursos, proporcionando um código mais conciso e seguro.

Um código sem o `catch`:
```java
try (ResourceType resource = new ResourceType()) {
    // código que utiliza o recurso
} catch (ExceptionType e) {
    // tratamento de exceções, se necessário
}

```

## Boas práticas
Abaixo algumas boas práticas ao serem atentadas ao usar exceções:
* Ao tratar uma exceção, às vezes não sabemos quais possíveis erros podem acontecer. Alguns casos são colocados no `catch` a superclasse `Thowrable`, mas isso não é uma boa prática porque dentro dessa classe também possui a classe `Error`. Além disso
  pode mascarar os problemas e dar trabalho para indentificá-los;
* Utilize as exceções de `RuntimeException` para indicar erros de programação;
* As throwables não verificadas que forem implementadas devem ser subclasses da `RuntimeException`, direta ou indiretamente.
* Em casos de dúvida, lance exceções não verificadas;
* Crie exceções personalizadas apenas quando necessário para sinalizar condições específicas no seu código;
* Ao lidar com situações em que precisam ser fechadas, não hesite em usar o try-with-resources para garantir que os recursos sejam finalizados corretamente.
