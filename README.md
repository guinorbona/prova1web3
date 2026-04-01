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
```

---

## Instruções para inicializar os serviços

Cada microsserviço deve ser executado separadamente em um terminal.

### 1. product-service

```bash
cd product-service
mvn spring-boot:run
```

### 2. user-service

```bash
cd inventory-service
mvn spring-boot:run
```

### 3. inventory-service

```bash
cd user-service
mvn spring-boot:run
```

### 4. payment-service

```bash
cd payment-service
mvn spring-boot:run
```
### 5. order-service

```bash
cd order-service
mvn spring-boot:run
```

### 6. Bancos de Dados

Criar cada banco de dados dedicado para cada serviço.

```bash
CREATE DATABASE product_db;
CREATE DATABASE inventory_db;
CREATE DATABASE user_db;
CREATE DATABASE payment_db;
CREATE DATABASE order_db;
```

## Portas utilizadas

| Serviço           | Porta |
| ----------------- | ----- |
| product-service   | 8081  |
| inventory-service | 8082  |
| user-service      | 8083  |
| payment-service   | 8084  |
| order-service     | 8085  |
