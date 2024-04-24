# **API de Pedidos**

Este projeto é uma API para processamento e armazenamento de pedidos de um sistema legado. Ele permite o upload de arquivos em formato txt contendo pedidos e fornece endpoints para listar os pedidos armazenados.

## **Rodando o Projeto Localmente**

Para rodar o projeto localmente, siga estas etapas:

1. Certifique-se de ter o Java JDK 17 instalado em seu sistema. Você pode baixá-lo em [Java JDK 17](https://www.oracle.com/java/technologies/downloads/#java17).
2. Clone este repositório para o seu computador:
    
    ```bash
    git clone https://github.com/DenisBonafe/legacy-order-api.git
    ```
    
3. Navegue até o diretório do projeto:
    
    ```bash
    cd legacy-order-api    
    ```
    
4. Execute o projeto usando o Maven:
    
    ```bash
    ./mvnw spring-boot:run
    ```
    
5. O servidor será iniciado e a API estará acessível em **`http://localhost:8080`**.

6. Execute os testes da aplicação:

    ```bash
    ./mvnw clean test
    ```
7. Resultado dos testes pode ser encontrado no diretório `target/site/jacoco/index.html`

## **Tecnologias Utilizadas**

- [Spring Boot:](https://spring.io/projects/spring-boot) Framework Java para criação de aplicativos web.
- [H2 Database:](https://www.h2database.com/html/main.html) Banco de dados em memória para armazenamento dos pedidos.
- [JPA:](https://docs.spring.io/spring-data/jpa/reference/index.html) API padrão do Java para mapeamento objeto-relacional.
- [Lombok:](https://projectlombok.org/) Biblioteca para simplificar o desenvolvimento reduzindo a quantidade de código boilerplate.
- [JUnit:](https://junit.org/junit5/docs/current/user-guide/) Framework de teste para Java.
- [Mockito:](https://site.mockito.org/) Framework para criação de mocks em testes unitários.
- [Insomnia:](https://docs.insomnia.rest/) Ferramenta para testar APIs.

## **Referências**

- [Spring Boot - Mkyong.com](https://www.mkyong.com/tutorials/spring-boot-tutorials/) (Mkyong.com)
- [Spring Boot - Baeldung.com](https://www.baeldung.com/spring-boot) (Baeldung.com)

- [Getting Started with H2 Database](https://www.baeldung.com/spring-boot-h2-database) (Baeldung.com)

- [JPA - Baeldung.com](https://www.baeldung.com/jpa) (Baeldung.com)

- [Lombok Tutorial](https://www.baeldung.com/intro-to-project-lombok) (Baeldung.com)

- [JUnit 5 - Mkyong.com](https://www.mkyong.com/tutorials/junit5-tutorials/) (Mkyong.com)
- [JUnit 5 - Baeldung.com](https://www.baeldung.com/junit-5) (Baeldung.com)

- [Mockito Tutorial](https://www.baeldung.com/mockito-series) (Baeldung.com)

- [Using Insomnia for API Testing](https://www.baeldung.com/insomnia) (Baeldung.com)