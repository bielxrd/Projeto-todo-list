# To-Do List API com Spring Boot e Maven

Este é um projeto de API To-Do List (lista de tarefas) construído com Spring Boot, Java, e Maven. A aplicação oferece um sistema de gerenciamento de tarefas e autenticação de usuário.

# Testando a API

Para testar a API da aplicação, recomendo o uso de ferramentas como [Postman](https://www.postman.com/) ou [APIDOG](https://apidog.com/).

## Tecnologias Utilizadas

- **Spring Boot**: Framework para criar aplicativos Java de maneira rápida e fácil.
- **Java**: Linguagem de programação principal.
- **Maven**: Gerenciador de dependências.
- **H2 Database**: Banco de dados em memória.
- **Lombok**: Biblioteca para simplificar o desenvolvimento reduzindo a escrita de código boilerplate.
- **BCrypt**: Biblioteca para criptografar senhas.
- **Docker**: Contêiner para empacotar a aplicação.

## Estrutura do Projeto

O projeto é dividido em pacotes e classes que utilizam anotações do Spring Boot para simplificar o desenvolvimento:

- **`UserModel`**: Classe que representa um usuário com anotações `@Entity`, `@GeneratedValue`, `@Id`, etc.
- **`UserController`**: Controlador para gerenciar ações relacionadas a usuários, com anotações `@RestController` e `@RequestMapping`.
- **`iUserRepository`**: Interface que estende `JpaRepository` para operações com usuários.
- **`TaskModel`**: Classe que representa uma tarefa com anotações `@Entity`, `@GeneratedValue`, `@Id`, etc.
- **`TaskController`**: Controlador para gerenciar ações relacionadas a tarefas, com anotações `@RestController` e `@RequestMapping`.
- **`iTaskRepository`**: Interface que estende `JpaRepository` para operações com tarefas.
- **`FilterTaskAuth`**: Filtro de autenticação com anotações `@Component` e `@Override` para autenticar solicitações.
- **`ExceptionHandlerController`**: Controlador de exceções para lidar com exceções personalizadas.
- **`Dockerfile`**: Arquivo Docker para criar uma imagem Docker da aplicação.
- **`pom.xml`**: Arquivo de configuração do Maven com todas as dependências do projeto.

## Configuração do Banco de Dados

A configuração do banco de dados é definida no arquivo `application.properties`, incluindo a URL, o driver, o nome de usuário e a senha do H2 Database.

```properties
spring.datasource.url=jdbc:h2:mem:todolist
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

## Anotações do Spring Boot

Neste projeto, várias anotações do Spring Boot são usadas para simplificar o desenvolvimento e a configuração da aplicação. Aqui estão as explicações de algumas das principais anotações usadas:

- **@RestController**: O `@RestController` indica que esta classe vai tratar operações CRUD (Create, Read, Update, Delete) em um aplicativo web. Ele age como um controlador central para lidar com solicitações HTTP e fornecer respostas apropriadas.

- **@Entity**: A anotação `@Entity` é usada para marcar uma classe como uma entidade JPA (Java Persistence API). Uma entidade representa uma tabela em um banco de dados relacional. No seu projeto, `UserModel` e `TaskModel` são entidades que são mapeadas para tabelas no banco de dados H2.

- **@Column**: A anotação `@Column` é usada para personalizar o mapeamento entre um campo de entidade e uma coluna de banco de dados. Por exemplo, você pode usar `@Column(unique = true)` para indicar que um campo deve ser único na tabela do banco de dados.

- **@Component**: A anotação `@Component` é usada para indicar que uma classe é um componente gerenciado pelo Spring. Os componentes gerenciados pelo Spring podem ser injetados em outras partes do código usando `@Autowired`. No seu projeto, `FilterTaskAuth` é um exemplo de classe anotada com `@Component`.

- **@GetMapping**: A anotação `@GetMapping` é usada para mapear métodos que tratam solicitações HTTP GET. Isso significa que esses métodos respondem a solicitações de leitura de dados, como listar tarefas existentes.

- **@PostMapping**: O `@PostMapping` é usado para mapear métodos que tratam solicitações HTTP POST. Esses métodos são responsáveis por criar novos recursos, como adicionar uma nova tarefa ao sistema.

- **@PutMapping**: O `@PutMapping` é usado para mapear métodos que tratam solicitações HTTP PUT. Esses métodos atualizam recursos existentes, como modificar os detalhes de uma tarefa.

- **@DeleteMapping**: A anotação `@DeleteMapping` é usada para mapear métodos que tratam solicitações HTTP DELETE. Esses métodos excluem recursos, como remover uma tarefa do sistema.

- **@RequestBody**: O `@RequestBody` indica que um método espera receber dados da solicitação HTTP no corpo da mensagem, geralmente no formato JSON. Isso permite que os dados enviados pelo cliente sejam transformados em objetos Java para serem processados na lógica do método.

- **ResponseEntity**: A classe `ResponseEntity` é usada para criar respostas personalizadas que incluem dados e informações sobre o status da resposta. É usado para enviar respostas HTTP com códigos de status, como "OK" (200) ou "Criado" (201), juntamente com os dados do recurso solicitado.

- **@Autowired**: A anotação `@Autowired` é usada para solicitar ao Spring Framework a injeção de dependências. Isso significa que o Spring fornecerá automaticamente instâncias de classes necessárias, como repositórios, para serem usadas em outras partes do código.

# README - Aplicação To-Do List

Neste projeto, criamos uma aplicação de lista de tarefas usando o Spring Boot. Abaixo, descrevemos os endpoints disponíveis na aplicação e como o tratamento de exceções é realizado.

## Endpoints

O aplicativo possui os seguintes endpoints para realizar operações de gerenciamento de tarefas:

### 1. Criar um novo usuário

- **URL**: `https://projetotodolist-rocket.onrender.com/users/`
- **Método**: POST
- **Descrição**: Este endpoint permite criar um novo usuário no sistema. É necessário fornecer um corpo de solicitação JSON contendo informações do usuário, como nome de usuário, nome e senha. A senha será criptografada antes de ser armazenada no banco de dados.
- **Autenticação**: Não é necessária autenticação para criar um novo usuário.

### 2. Adicionar uma nova tarefa

- **URL**: `https://projetotodolist-rocket.onrender.com/tasks/`
- **Método**: POST
- **Descrição**: Este endpoint permite adicionar uma nova tarefa ao sistema. É necessário fornecer um corpo de solicitação JSON contendo detalhes da tarefa, como descrição, título, data de início e data de término.
- **Autenticação**: É necessária autenticação usando Basic Auth, onde o nome de usuário e a senha do usuário são usados para verificar as permissões.

### 3. Atualizar uma tarefa existente

- **URL**: `https://projetotodolist-rocket.onrender.com/tasks/{id da tarefa}`
- **Método**: PUT
- **Descrição**: Este endpoint permite atualizar os detalhes de uma tarefa existente. É necessário fornecer o ID da tarefa que deseja atualizar no lugar de `{id da tarefa}` na URL. O corpo da solicitação deve conter as informações atualizadas da tarefa.
- **Autenticação**: É necessária autenticação usando Basic Auth, onde o nome de usuário e a senha do usuário são usados para verificar as permissões.

## Tratamento de Exceções

O aplicativo trata várias exceções para garantir que as operações sejam seguras e confiáveis. Aqui estão alguns dos cenários de tratamento de exceções:

- Se as datas de início ou término de uma tarefa estiverem incorretas (por exemplo, a data de início está após a data de término), o aplicativo retornará uma resposta com um código de status 400 (Bad Request) indicando o erro.

- Se um usuário tentar atualizar uma tarefa que pertence a outro usuário, o aplicativo retornará uma resposta com um código de status 400 (Bad Request) indicando que o usuário não tem permissão para alterar essa tarefa.

- Se um usuário tentar adicionar uma tarefa ou fazer qualquer outra operação sem autenticação adequada, o aplicativo retornará uma resposta com um código de status 401 (Unauthorized).

# Deploy no Render.com

Este projeto foi implantado no [Render.com](https://render.com/), uma plataforma de hospedagem e implantação fácil de usar. 

