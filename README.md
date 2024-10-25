# Desafio de Código Vertical Lógística

Este projeto é uma simples implementação de um desafio de código proposto. O objetivo é demonstrar habilidades em desenvolvimento de software, design de sistema e integração com banco de dados.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- H2 Database (em memória)
- Docker e Docker Compose

## Estrutura do Projeto

O projeto contém as seguintes classes principais:

- **Modelos**: Representam as entidades do sistema, como `User`, `Order`, `OrderItem`, e `Product`.
- **Serviços**: Contêm a lógica de negócios, como `ApiReturnService`, `OrchestratorService`, `UserService`, `ProductService`, e `OrderItemService`.
- **Controladores**: Disponibilizam as APIs REST, como `OrderController`.
- **Utilitários**: Como `FileProcessor` para leitura de arquivos.

## Configuração do Banco de Dados

O projeto utiliza o **H2 Database** para simplificar a implementação e facilitar os testes. O H2 é um banco de dados em memória que não requer configuração adicional.

## Passo a Passo para Build do Projeto

Para construir e executar o projeto, siga os passos abaixo:

### 1. Clonar o Repositório

```bash
https://github.com/devpassos/desafioverticallogistica.git
cd desafioverticallogistica
```

### 2. Construir o Projeto
Se você estiver usando Maven, execute o seguinte comando:

```bash
mvn clean package
```
Se você estiver usando Gradle, execute:

```bash
./gradlew clean build
```
### 3. Criar o Container Docker
O projeto inclui um Dockerfile para criar a imagem Docker. Para construir e executar a aplicação com Docker Compose, execute:

```bash
docker-compose up --build
```

### 4. Acessar a Aplicação
Depois que o container estiver em execução, você pode acessar a aplicação em:

Aplicação: http://localhost:8080

Console H2: http://localhost:8080/h2-console

Use as seguintes credenciais para acessar o console H2:

JDBC URL: jdbc:h2:mem:orders_db

User Name: sa

Password: (deixe em branco)

### 5. Parar e Remover o Container
Para parar e remover o container que foi criado, você pode executar:

```bash
docker-compose down
```

### 6. Estrutura do Projeto
```bash
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── verticallogistica
│   │   │           └── desafio
│   │   │               ├── controller
│   │   │               │   └── OrderController.java
│   │   │               ├── dto
│   │   │               │   ├── OrderDTO.java
│   │   │               │   ├── ProductDTO.java
│   │   │               │   └── UserDTO.java
│   │   │               ├── model
│   │   │               │   ├── OrderItem.java
│   │   │               │   ├── Order.java
│   │   │               │   ├── Product.java
│   │   │               │   └── User.java
│   │   │               ├── repository
│   │   │               │   ├── OrderItemRepository.java
│   │   │               │   ├── OrderRepository.java
│   │   │               │   ├── ProductRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── service
│   │   │               │   ├── ApiReturnService.java
│   │   │               │   ├── OrchestratorService.java
│   │   │               │   ├── OrderItemService.java
│   │   │               │   ├── OrderService.java
│   │   │               │   ├── ProductService.java
│   │   │               │   └── UserService.java
│   │   │               ├── utils
│   │   │               │   └── FileProcessor.java
│   │   │               └── DesafioApplication.java
│   │   └── resources
│   │       ├── import
│   │       │   ├── data_1.txt
│   │       │   └── data_2.txt
│   │       ├── static
│   │       ├── templates
│   │       ├── application.properties
│   │       └── schema.sql
│   └── test
│       └── java
│           └── com
│               └── verticallogistica
│                   └── desafio
│                       ├── controller
│                       │   └── OrderControllerTest.java
│                       ├── dto
│                       │   ├── OrderDTOTest.java
│                       │   ├── ProductDTOTest.java
│                       │   └── UserDTOTest.java
│                       ├── model
│                       │   ├── OrderItemTest.java
│                       │   ├── OrderTest.java
│                       │   ├── ProductTest.java
│                       │   └── UserTest.java
│                       ├── service
│                       │   ├── ApiReturnServiceTest.java
│                       │   ├── OrchestratorServiceTest.java
│                       │   ├── OrderItemServiceTest.java
│                       │   ├── OrderServiceTest.java
│                       │   ├── ProductServiceTest.java
│                       │   └── UserServiceTest.java
│                       ├── utils
│                       │   └── FileProcessorTest.java
│                       └── DesafioApplicationTests.java
├── Desafio técnico - Vertical Logistica.pdf
├── docker-compose.yaml
├── Dockerfile
├── HELP.md
├── LICENSE
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md

```
#### Considerações Finais
Este projeto foi desenvolvido com o intuito de ser um exemplo simples e funcional, demonstrando a integração com um banco de dados em memória (H2) e a construção de uma API RESTful com Spring Boot.

Para testar a API, basta fazer uma requisição do tipo POST para o enpoint http://localhost:8080/api/orders  utilizando uma ferramente para simular requisições REST como o Postman.

Utilize no corpo da requisição o tipo form-data. Coloque o key (chave) com o nome do campo "file" e selecione o arquivo a ser importado. 

Existem dois exemplos de arquivos para importação no diretório /src/main/resources/import/.

Sinta-se à vontade para explorar o código e fazer melhorias!

#### Licença
Este projeto é licenciado sob a GNU GENERAL PUBLIC LICENSE v3 - veja o arquivo LICENSE para detalhes.