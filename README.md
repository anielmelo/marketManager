# **Market Manager**

O *Market Manager* é um sistema desenvolvido para gerenciar vendas e estoque de um mercado, adotando a arquitetura de microserviços para garantir escalabilidade e modularidade.

## **Propósito da Aplicação**

A aplicação permite o controle eficiente de produtos, reposições e vendas, garantindo que todas as operações sejam registradas de forma segura e organizada. A autenticação e autorização dos usuários são gerenciadas pelo **Keycloak**, permitindo a definição de permissões para diferentes tipos de usuários.

## **Arquitetura**

> Diagrama de componentes.


O sistema é baseado em **microserviços**, onde cada serviço possui uma responsabilidade específica e se comunica por meio de APIs REST. O tráfego é gerenciado por um API Gateway utilizando `spring-cloud-starter-gateway`, permitindo roteamento.

Os principais componentes incluem:

- **Serviço de Autenticação:** Responsável por gerenciar usuários e permissões (Keycloak).
- **Serviço de Estoque:** Gerencia o cadastro de produtos e a movimentação de estoque.
- **Serviço de Vendas:** Processa transações de venda e atualiza o estoque.
- **Gateway:** Controla o fluxo de requisições entre os serviços.

O sistema é **containerizado** usando **Docker Compose**, com instâncias do PostgreSQL para armazenamento de dados.

### **Documentação**

> Diagrama de classe.


Além do código-fonte, a aplicação conta com diagramas de atividade, caso de uso, classe, componentes e sequenciais, além de uma coleção Postman para facilitar a interação com as APIs para auxiliar no entendimento e manutenção do sistema:

```
├── docs
    ├── diagrams
    │   ├── atividade
    │   │   ├── atividade_atualiza_produto.svg
    │   │   ├── atividade_consulta_produto.svg
    │   │   ├── atividade_insere_produto.svg
    │   │   ├── atividade_insere_reposicao.svg
    │   │   ├── atividade_insere_venda.svg
    │   │   └── atividade_login.svg
    │   ├── caso_de_uso
    │   │   └── use_case_geral.svg
    │   ├── classe
    │   │   └── classe.svg
    │   ├── componente
    │   │   └── componentes.svg
    │   ├── conceitual
    │   │   └── conceitual.png
    │   └── sequencial
    │   │   ├── sequencia_insere_produto.svg
    │   │   ├── sequencia_insere_venda.svg
    │   │   └── sequencia_login.svg
    ├── doc-visao
    │   ├── Doc Visão - Market Manager.docx
    │   └── Doc Visão - Market Manager.pdf
    ├── postman_collection
    │   └── marketmanager.postman_collection.json
    └── slide
    │   ├── Slide - Market Manager.pdf
    │   └── Slide - Market Manager.pptx
```

- **Diagramas**: Contém diversos diagramas UML que representam os fluxos e a arquitetura do sistema.
- **Doc Visão**: Documento de visão do projeto, disponível em `.docx` e `.pdf`.
- **Coleção Postman**: Arquivo `.json` com as requisições para testar a API.
- **Slides**: Apresentação do projeto.

## Execução do Sistema

1. Clone o repositório:
    
    ```sh
    git clone https://github.com/anielmelo/marketManager.git
    cd marketManager
    ```
    
2. Execute o Docker Compose para iniciar os containers:
    
    ```sh
    docker-compose up -d
    ```
    
3. Execute os microserviços individualmente com:
    ```sh
    mvn clean package -DskipTests
    java -jar target/<nome-do-jar>.jar
    ```


## Endpoints e Gateway

O sistema utiliza **Spring Cloud Gateway** para rotear as requisições entre os microserviços. A porta padrão do gateway é `8000`.

Exemplo de requisição via Gateway:
- Inventory:

```sh
curl -X GET http://localhost:8000/inventory/products
```

- Sale:
```sh
curl -X GET http://localhost:8000/sale
```

## Keycloak

Keycloak é utilizado para autenticação e autorização. O container do Keycloak será iniciado com o Docker Compose e pode ser acessado em:

```
http://localhost:8080/
```

As credenciais padrão para acesso ao Keycloak são:

- **Usuário:** admin
- **Senha:** admin
