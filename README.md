# Prova 1 (DWE3) Sistema de Microsserviços

## Descrição da prova
Esta prova consiste em uma aplicação baseada em microsserviços desenvolvida em **Java com Spring Boot**, com comunicação entre serviços via **API REST** e persistência isolada por serviço utilizando **MySQL**.

A solução foi construída com base em uma arquitetura composta por cinco microsserviços independentes:

- **product-service**
- **user-service**
- **inventory-service**
- **payment-service**
- **order-service**

O **order-service** atua como orquestrador central do fluxo de criação de pedidos, realizando integração com os demais serviços para validar usuário, produto, estoque e processar pagamento.

---

## Arquitetura dos serviços

### 1. product-service
Responsável pelo cadastro e consulta de produtos.

### 2. user-service
Responsável pelo cadastro e consulta de usuários.

### 3. inventory-service
Responsável por armazenar e controlar a quantidade disponível em estoque por produto.

### 4. payment-service
Responsável por processar o pagamento de um pedido e retornar o status de aprovação ou rejeição.

### 5. order-service
Responsável por receber pedidos e orquestrar a comunicação com os demais serviços:
- valida usuário
- valida produto
- verifica estoque
- registra pedido
- chama pagamento
- atualiza status final do pedido
- reduz estoque em caso de aprovação

---

## Tecnologias utilizadas

- Java 25
- Spring Boot
- Spring Data JPA
- Spring Web
- Spring Cloud OpenFeign
- MySQL
- Maven
- Postman

---

## Pré-requisitos para execução

Antes de executar o projeto, é necessário ter instalado:

- **Java 25** ou superior
- **Maven 3.8+**
- **MySQL Workbench**
- **IDE** de preferência IntelliJ
- **Postman**

---

## Estrutura do repositório

```text
prova1web3/
├── product-service/
├── user-service/
├── inventory-service/
├── payment-service/
├── order-service/
├── evidencias/
├── database.sql
├── prova1web3.postman_collection
└── README.md
