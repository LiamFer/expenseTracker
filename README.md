# 💸 Expense Tracker API

API REST desenvolvida em Java com Spring Boot para controle de despesas pessoais. Permite que cada usuário gerencie suas próprias despesas, com autenticação segura via JWT e filtros avançados por período de tempo.

> Desafio completo disponível em: [roadmap.sh/projects/expense-tracker-api](https://roadmap.sh/projects/expense-tracker-api)

---

## 📦 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Security + JWT**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Jakarta Validation**
- **Maven**

---

## 🧑‍💻 Funcionalidades

### 👤 Autenticação
- [x] Cadastro de novos usuários (`/auth/register`)
- [x] Login com retorno de JWT (`/auth/login`)
- [x] Proteção de rotas privadas com JWT

### 💰 Despesas
- [x] Cadastrar nova despesa
- [x] Atualizar despesa existente
- [x] Excluir despesa
- [x] Listar despesas do usuário autenticado
- [x] Filtros por data:
  - Última semana
  - Último mês
  - Últimos 3 meses
  - Intervalo customizado (`startDate`, `endDate`)

---

## 📋 Categorias de Despesa

As categorias disponíveis no sistema:

- 🛒 `Groceries`
- 🎮 `Leisure`
- 💻 `Electronics`
- 💡 `Utilities`
- 👕 `Clothing`
- ❤️ `Health`
- 🗃️ `Others`

---

## 🛠️ Configurações de Ambiente

Adicione as variáveis de ambiente no seu sistema ou arquivo `.env`:

```env
# Informações da aplicação
spring.application.name=expenseTracker
server.port=3000

# JWT
spring.jwt.secret=SEU_SEGREDO_JWT

# PostgreSQL
DB_URL=jdbc:postgresql://localhost:5432/expense_db
DB_USER=seu_usuario
DB_PASS=sua_senha
```

---

## 🔐 Segurança

Toda autenticação é feita via JWT com o issuer `expense-api`. O token deve ser enviado no header das requisições:

```
Authorization: Bearer SEU_TOKEN
```

---

## 📄 Exemplo de JSON para criação de despesa

```json
{
  "title": "Hamburguer",
  "category": "Groceries",
  "price": 24.90,
  "date": "2025-06-23T14:30:00"
}
```

---

## 🧪 Testes e Validações

* Todas as rotas protegidas exigem autenticação via token.
* Os campos são validados com anotações como `@NotBlank`, `@NotNull`, etc.
* Em caso de erro, a resposta segue um padrão estruturado com `ApiError`.

---

## 📁 Estrutura de Pacotes

Organização em camadas:

```
com.liamfer.expenseTracker
├── controller        # Endpoints REST
├── domain            # Entidades JPA
├── DTO               # Objetos de Transferência de Dados
├── enums             # Enumerações fixas (Categoria, Papéis)
├── exceptions        # Exceções personalizadas
├── infra             # Filtros e configurações globais
├── repository        # Interfaces JPA
├── service           # Regras de negócio
```

---

## 🚀 Como rodar localmente

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/expense-tracker-api.git
   ```

2. Configure as variáveis de ambiente.

3. Suba o banco com Podman, Docker ou use um banco local PostgreSQL.

4. Rode o projeto:

   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🧠 Sobre o desafio

Este projeto faz parte da trilha de backend no roadmap.sh, sendo o último desafio da seção de nível *iniciante*. Com ele, pratiquei:

* Autenticação com Spring Security e JWT
* Organização de API RESTful
* CRUD com JPA
* Paginação e filtros personalizados
* Tratamento global de erros

👉 Acesse: [https://roadmap.sh/projects/expense-tracker-api](https://roadmap.sh/projects/expense-tracker-api)
