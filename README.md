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

```sql
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

## Evidência obrigatória de funcionamento

### 1. Criar produto

POST http://localhost:8081/products
```json
{
  "name": "Notebook Dell",
  "description": "16GB RAM, SSD 512GB",
  "price": 4500.00
}
```
### 2. Criar usuário

POST http://localhost:8083/users
```json
{
  "name": "Guilherme Marques",
  "email": "guilherme@email.com"
}
```

### 3. Criar estoque

POST http://localhost:8082/inventory
```json
{
  "productId": 1,
  "quantity": 10
}
```

### 4. Verificar estoque antes do pedido

GET http://localhost:8082/inventory/1
Resultado esperado:
quantidade = 10

### 5. Criar pedido

POST http://localhost:8085/orders
```json
{
  "userId": 1,
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}
```
### 6. Processamento de pagamento

O pagamento é processado automaticamente pelo payment-service durante a criação do pedido.
Regra implementada:
* até 10000 → APPROVED
* acima de 10000 → REJECTED
Neste exemplo:
* total = 9000 → pagamento aprovado

### 7. Confirmar pedido

GET http://localhost:8085/orders/1
Resultado esperado:
status = APPROVED

### 8. Verificar estoque após pedido

GET http://localhost:8082/inventory/1
Resultado esperado:
quantidade = 8

---

#### Observação
Alterar o usuário e senha nos arquivos `application.properties` dos serviços:
```properties
spring.application.name=inventory
server.port=8082
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=USUARIO_DB
spring.datasource.password=SENHA_DB
spring.jpa.hibernate.ddl-auto=update
```

