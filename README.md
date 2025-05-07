# 🏆 LoL Champs Info - Spring Boot API

Este repositório contém uma API desenvolvida com **Spring Boot**, focada em gerenciar dados de **campeões do League of Legends**, como nome, função, região, tier, estatísticas de jogo e mais. A API permite listar, adicionar, atualizar e remover campeões com base em atributos específicos.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Lombok
- PostgreSQL
- IDE: IntelliJ IDEA ou VSCode

---

## 📚 Funcionalidades

- 📋 Listar campeões por nome, região, classe, tier ou função
- ➕ Adicionar novos campeões
- ♻️ Atualizar informações de campeões existentes
- ❌ Deletar campeões por nome e função
- ✅ Validação de dados por meio de validadores específicos

---

## 📦 Estrutura

- `model/ChampionEntity`: Entidade com todos os atributos dos campeões
- `service/ChampionService`: Lógica de negócio da aplicação
- `repository/ChampionRepository`: Interface com o banco de dados
- `validators/`: Classes que validam atributos como tier, função, região, etc.

---

## 📌 Exemplos de endpoints

| Método | Endpoint                          | Ação                             |
|--------|-----------------------------------|----------------------------------|
| GET    | `/champions/names`               | Lista nomes de campeões          |
| GET    | `/champions/region/{region}`     | Lista por região                 |
| GET    | `/champions/class/{classType}`   | Lista por classe                 |
| GET    | `/champions/role/{role}`         | Lista por função                 |
| GET    | `/champions/tier/{tier}`         | Lista por tier                   |
| POST   | `/champions`                     | Adiciona novo campeão            |
| PUT    | `/champions`                     | Atualiza campeão existente       |
| DELETE | `/champions/{name}/{role}`       | Remove campeão pelo nome e role  |

---

## 🧪 Exemplo de JSON

```json
{
  "name": "Ahri",
  "region": "Ionia",
  "classType": "Mage",
  "role": "Mid",
  "tier": "S",
  "score": 89.5,
  "trend": 1.2,
  "winRate": 52.3,
  "roleRate": 76.0,
  "pickRate": 34.5,
  "banRate": 12.1,
  "kda": 3.5
}
```
## 🛠️ Como executar
Clone o projeto:

bash
Copiar
Editar
git clone https://github.com/TiagoEich/League-of-Legends-Api.git


Configure o application.properties:spring.datasource.url=jdbc:postgresql://localhost:5432/lolapi
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

baixe o arquivo CSV, disponpível no site: [Arquivo CSV ->](https://www.kaggle.com/datasets/uskeche/leauge-of-legends-champions-dataset)

