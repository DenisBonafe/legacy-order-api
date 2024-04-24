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

## **Tecnologias Utilizadas**

- [Spring Boot:](https://spring.io/projects/spring-boot) Framework Java para criação de aplicativos web.
- [H2 Database:](https://www.h2database.com/html/main.html) Banco de dados em memória para armazenamento dos pedidos.
- [JPA:](https://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html) API padrão do Java para mapeamento objeto-relacional.
- [Lombok:](https://projectlombok.org/) Biblioteca para simplificar o desenvolvimento reduzindo a quantidade de código boilerplate.
- [JUnit:](https://junit.org/junit5/docs/current/user-guide/) Framework de teste para Java.
- [Mockito:](https://site.mockito.org/) Framework para criação de mocks em testes unitários.
- [Insomnia:](https://support.insomnia.rest/) Ferramenta para testar APIs.

## **Referências**

- [Spring Boot](https://spring.io/projects/spring-boot): Framework Java para criação de aplicativos web.
- [Introdução ao Spring Boot](https://medium.com/@krishankantsinghal/spring-boot-start-up-guide-b857f02f67f9) (Medium)
- [Spring Boot - Mkyong.com](https://www.mkyong.com/tutorials/spring-boot-tutorials/) (Mkyong.com)
- [Spring Boot - Baeldung.com](https://www.baeldung.com/spring-boot) (Baeldung.com)

- [H2 Database](https://www.h2database.com/html/main.html): Banco de dados em memória para armazenamento dos pedidos.
- [Guia Completo do H2 Database](https://medium.com/javarevisited/h2-database-a-complete-introduction-20313c92438b) (Medium)
- [Getting Started with H2 Database](https://www.baeldung.com/spring-boot-h2-database) (Baeldung.com)

- [JPA](https://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html): API padrão do Java para mapeamento objeto-relacional.
- [Introdução ao JPA](https://medium.com/@mlukasavige/understanding-java-persistence-api-jpa-8a37d9e0d986) (Medium)
- [JPA - Baeldung.com](https://www.baeldung.com/jpa) (Baeldung.com)

- [Lombok](https://projectlombok.org/): Biblioteca para simplificar o desenvolvimento reduzindo a quantidade de código boilerplate.
- [Using Lombok](https://medium.com/@jakedowns/getting-started-with-project-lombok-24c05a0e104a) (Medium)
- [Lombok Tutorial](https://www.baeldung.com/intro-to-project-lombok) (Baeldung.com)

- [JUnit](https://junit.org/junit5/docs/current/user-guide/): Framework de teste para Java.
- [JUnit 5 - Writing Tests](https://medium.com/swlh/junit-5-writing-tests-1dcf1c8efc) (Medium)
- [JUnit 5 - Mkyong.com](https://www.mkyong.com/tutorials/junit5-tutorials/) (Mkyong.com)
- [JUnit 5 - Baeldung.com](https://www.baeldung.com/junit-5) (Baeldung.com)

- [Mockito](https://site.mockito.org/): Framework para criação de mocks em testes unitários.
- [Mockito - A Beginner's Guide](https://medium.com/swlh/mockito-a-beginners-guide-2b9aef1bd654) (Medium)
- [Mockito Tutorial](https://www.baeldung.com/mockito-series) (Baeldung.com)

- [Insomnia](https://support.insomnia.rest/): Ferramenta para testar APIs.
- [Insomnia REST Client - A Complete Guide](https://medium.com/@alexmngn/insomnia-rest-client-a-complete-guide-4dd8d28ef611) (Medium)
- [Using Insomnia for API Testing](https://www.baeldung.com/insomnia) (Baeldung.com)