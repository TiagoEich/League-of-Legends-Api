# 🏆 Informações de campeões de League of Legends

Este repositório contém uma API desenvolvida com **Spring Boot**, focada em gerenciar dados de **campeões do League of Legends**, como nome, função, região, tier, estatísticas de jogo e mais. A API permite listar, adicionar, atualizar e remover campeões com base em atributos específicos.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Lombok
- PostgreSQL
- IDE: IntelliJ IDEA

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

| Método | Endpoint                     | Ação                              |
|--------|------------------------------|-----------------------------------|
| GET    | `/names`                     | Lista nomes de campeões           |
| GET    | `/region/{region}`           | Lista por região                  |
| GET    | `/class/{classType}`         | Lista por classe                  |
| GET    | `/role/{role}`               | Lista por função                  |
| GET    | `/tier/{tier}`               | Lista por tier                    |
| POST   | `/addChampion`               | Adiciona novo campeão             |
| PUT    | `/champions`                 | Atualiza campeão existente        |
| DELETE | `/champions/{name}/{role}`   | Remove campeão pelo nome e role   |


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

git clone https://github.com/TiagoEich/League-of-Legends-Api.git


Configure o application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/Nome_Do_Seu_Banco_De_Dados

spring.datasource.username=seu_usuario

spring.datasource.password=sua_senha

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true



baixe o arquivo CSV, disponpível no site: [Arquivo CSV ->](https://www.kaggle.com/datasets/uskeche/leauge-of-legends-champions-dataset)

