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

---

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




