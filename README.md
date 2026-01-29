# 🚀 Projeto Demo - Spring Boot + Docker

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.0-green?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

---

## 📌 Descrição
Este projeto foi desenvolvido como parte de um processo seletivo/vaga.  
Ele utiliza **Spring Boot** para a aplicação principal e **Docker Compose** para orquestrar os serviços necessários (incluindo banco de dados e armazenamento).

---

## 📝 Dados da inscrição / vaga
- **Candidato:** Marcel Guedes Martins  
- **Repositório:** [marcelguedesmartins823709](https://github.com/MarcelGuedes/marcelguedesmartins823709)  
- **Objetivo:** Demonstrar habilidades em Java, Spring Boot, Docker e boas práticas de documentação.
## 🚀 Instruções de Instalação e Execução
# Documentação do Projeto

## Estrutura de Dados e Decisões de Arquitetura

O candidato deverá propor a **estrutura de dados de cada tabela** de forma coerente e documentar as decisões e a arquitetura adotada neste projeto.

### Estrutura das Tabelas

- **artists**
  - `id` (BIGINT, PK, auto increment)
  - `name` (VARCHAR(200), NOT NULL)
  - `type` (VARCHAR(50), NOT NULL)

- **albums**
  - `id` (BIGINT, PK, auto increment)
  - `title` (VARCHAR(200), NOT NULL)

- **artist_album** (tabela de relacionamento N:N)
  - `album_id` (BIGINT, FK → albums.id)
  - `artist_id` (BIGINT, FK → artists.id)
  - PK composta (`album_id`, `artist_id`)

- **regionais**
  - `id` (BIGINT, PK, auto increment)
  - `nome` (VARCHAR(200), NOT NULL)
  - `ativo` (BOOLEAN, DEFAULT TRUE, NOT NULL)

### Decisões de Arquitetura

- Utilização de **Flyway** para versionamento e controle de migrations (`V1` a `V10`).
- Definição de **chaves primárias** com `IDENTITY` para geração automática de IDs.
- Relacionamento **N:N** entre `artists` e `albums` implementado via tabela intermediária `artist_album`.
- Inclusão de **dados iniciais** (inserts) para garantir consistência e facilitar testes.
- Índice criado em `regionais(nome)` para otimizar consultas por nome.
- Entidades Java (`Album`, `Artist`, `Regional`) mapeadas com **JPA/Hibernate**, refletindo fielmente a estrutura das tabelas.

---

### Pré-requisitos
Antes de rodar o projeto, certifique-se de ter instalado:
- **Java 17** (ou versão compatível)
- **Maven** (para build e gerenciamento de dependências)
- **PostgreSQL** (banco de dados relacional)
- **Git** (para clonar o repositório)

### Passo a passo

1. **Clonar o repositório**
   ```bash
   git clone https://github.com/MarcelGuedes/marcelguedesmartins823709.git
   cd marcelguedesmartins823709

---
## 🚀 Instruções de Instalação e Execução

### Pré-requisitos
Antes de rodar o projeto, certifique-se de ter instalado:
- **Java 17** (ou versão compatível)
- **Maven** (para build e gerenciamento de dependências)
- **PostgreSQL** (banco de dados relacional)
- **Git** (para clonar o repositório)

### Passo a passo

1. **Clonar o repositório**
   ```bash
   git clone https://github.com/MarcelGuedes/marcelguedesmartins823709.git
   cd marcelguedesmartins823709

## ⚙️ Como executar o projeto

### ✅ Pré-requisitos
- [Java 17+](https://adoptium.net/)  
- [Maven](https://maven.apache.org/)  
- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/)  

### ▶️ Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/MarcelGuedes/marcelguedesmartins823709.git
   cd marcelguedesmartins823709
   ```

2. Compile e rode os testes:
   ```bash
   mvn clean install
   ```

3. Suba os serviços com Docker Compose:
   ```bash
   docker-compose up -d
   ```

---

## 🧪 Como testar

### Testes automatizados
```bash
mvn test
```

### Testes manuais
Use ferramentas como **Postman** ou **cURL** para chamar os endpoints REST.

Exemplo:
```bash
curl http://localhost:8080/api/hello
```

---
## 🌐 Endpoints da API

### Artists
- `GET /api/v1/artists` → Lista todos os artistas
- `GET /api/v1/artists/{id}` → Busca artista por ID
- `POST /api/v1/artists` → Cria novo artista
- `PUT /api/v1/artists/{id}` → Atualiza artista existente
- `DELETE /api/v1/artists/{id}` → Remove artista

### Regionais
- `GET /api/v1/regionais` → Lista todas as regionais
- `GET /api/v1/regionais/{id}` → Busca regional por ID
- `POST /api/v1/regionais` → Cria nova regional
- `PUT /api/v1/regionais/{id}` → Atualiza regional existente
- `DELETE /api/v1/regionais/{id}` → Remove regional

## 📂 Estrutura do projeto
```
├── src/
│   ├── main/
│   │   ├── java/        # Código fonte da aplicação
│   │   └── resources/   # Configurações
│   └── test/            # Testes automatizados
├── pom.xml              # Configuração Maven
├── docker-compose.yml   # Orquestração de serviços
└── README.md            # Documentação
```

---

## 🚀 Tecnologias utilizadas
- Java 17 + Spring Boot  
- Maven  
- Docker & Docker Compose  
- JUnit para testes  

---

## 📌 Observações
- O arquivo `minio.exe` foi removido do repositório por exceder o limite de 100 MB do GitHub.  
- O serviço de armazenamento pode ser configurado diretamente via Docker Compose sem necessidade de binários locais.




