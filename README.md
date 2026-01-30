# 🚀 Projeto Demo - Spring Boot + Docker

## 🔧 Tecnologias e informações principais
[![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-3.0-green?style=for-the-badge)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=for-the-badge)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)](https://opensource.org/licenses/MIT)



---

## 📌 Descrição
Este projeto foi desenvolvido como parte de um processo seletivo/vaga.  
Ele utiliza **Spring Boot** para a aplicação principal e **Docker Compose** para orquestrar os serviços necessários (incluindo banco de dados e armazenamento).

---

## 📝 Dados da inscrição / vaga
- **Candidato:** Marcel Guedes Martins  
- **Repositório:** [marcelguedesmartins823709](https://github.com/MarcelGuedes/marcelguedesmartins823709)  
- **Objetivo:** Demonstrar habilidades em Java, Spring Boot, Docker e boas práticas de documentação.

---

## 🚀 Instruções de Instalação e Execução

### ✅ Pré-requisitos
Antes de rodar o projeto, certifique-se de ter instalado:
- [Java 17+](https://adoptium.net/)  
- [Maven](https://maven.apache.org/)  
- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/)  
- [PostgreSQL](https://www.postgresql.org/)  
- [Git](https://git-scm.com/)  

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

## 📂 Estrutura de Dados e Decisões de Arquitetura

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

---
## 🔒 Autenticação JWT
- `POST /api/v1/auth/login` → Gera token JWT válido por tempo limitado.
- `POST /api/v1/auth/refresh` → Renova token próximo da expiração.
- Todos os endpoints protegidos exigem o header:

Authorization: Bearer <token>


---

## 📂 Upload de Arquivos (MinIO)
- `POST /api/v1/files/upload` → Gera URL pré-assinada para upload.
- `GET /api/v1/files/{filename}` → Recupera arquivo via URL pré-assinada.
- Armazenamento configurado via **MinIO** em container Docker.

---

## 📡 WebSocket
- Conexão em `ws://localhost:8080/ws/notifications`.
- Notificações em tempo real quando novos artistas ou regionais são cadastrados.

---

## ❤️ Health Check
- `GET /actuator/health` → Verifica status da aplicação.
- `GET /actuator/info` → Exibe informações da build e ambiente.

---

## 📖 Documentação Swagger
- Acesse a documentação interativa em:

http://localhost:8080/swagger-ui.html


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

---

## 📌 Considerações finais

### Histórico de commits
O projeto foi desenvolvido com commits pequenos e descritivos, para facilitar revisão e manutenção.

### Limitações
- Não foram implementados testes de integração completos devido ao tempo do desafio.  
- Priorizamos a implementação dos endpoints principais e a orquestração com Docker.
-  
### Possibilidade de evolução
- Criar testes de integração com Testcontainers.  
- Expandir relacionamento N:N para incluir mais atributos (ex: ano de lançamento do álbum).  

### ✅ Requisitos do edital atendidos
- README com documentação, dados de inscrição e instruções de execução/teste ✔️  
- Codificação como se fosse para produção, com possibilidade de evolução ✔️  
- Relacionamento Artista-Álbum N:N ✔️  
- Uso de projeto base + dependências necessárias ✔️  
- Exemplos como carga inicial do banco ✔️  
- Aplicação empacotada como imagens Docker ✔️  
- Containers orquestrados (API + MinIO + BD) via docker-compose ✔️  



