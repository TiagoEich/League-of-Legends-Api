# 🏆 League of Legends Champions API

API REST desenvolvida com **Spring Boot** para gerenciar e consultar dados de campeões do League of Legends. Os dados são carregados automaticamente a partir de um arquivo CSV na primeira execução, e a API permite listar, filtrar, adicionar, atualizar e remover campeões com validações específicas por atributo.

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA** — acesso ao banco de dados
- **PostgreSQL** — banco de dados relacional
- **OpenCSV** — importação automática de dados via CSV
- **Lombok** — redução de código boilerplate
- **SpringDoc / Swagger UI** — documentação interativa da API
- **JUnit 5 + Mockito** — testes automatizados
- **Maven** — gerenciamento de dependências

---

## ✅ Funcionalidades

- 📋 Listar nomes de todos os campeões (sem duplicatas)
- 🌍 Filtrar campeões por **região**, **classe**, **função** ou **tier**
- ➕ Adicionar novos campeões com validação de dados
- ♻️ Atualizar informações de campeões existentes
- ❌ Deletar campeões por nome e função
- 📥 **Importação automática** do CSV na primeira execução via `CommandLineRunner`
- ✅ Validadores isolados por atributo (tier, role, região, classe)
- 🧪 Testes automatizados de controller, service, validators e CSV service

---

## 🗂️ Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/lol/champs_info/
│   │   ├── controller/         # Endpoints da API
│   │   ├── model/              # Entidade ChampionEntity
│   │   ├── repository/         # Interface JPA com queries customizadas
│   │   ├── service/
│   │   │   ├── ChampionService.java    # Regras de negócio
│   │   │   └── CsvService.java        # Importação automática do CSV
│   │   └── validators/         # Validadores por atributo
│   │       ├── AddChampionValidator.java
│   │       ├── TierValidator.java
│   │       ├── RoleValidator.java
│   │       ├── RegionValidator.java
│   │       └── ClassTypeValidator.java
│   └── resources/
│       ├── application.properties
│       ├── application-prod.properties
│       └── lolchamps.csv
└── test/                       # Testes automatizados
    └── java/com/lol/champs_info/
        ├── controller/
        ├── model/
        ├── service/
        └── validators/
```

---

## 🗃️ Estrutura do Banco de Dados

```
champions
├── id (PK, UUID)
├── name
├── region
├── classType
├── role
├── tier
├── score
├── trend
├── winRate
├── roleRate
├── pickRate
├── banRate
└── kda
```

---

## 📌 Endpoints

| Método   | Endpoint                    | Descrição                          | 
|----------|-----------------------------|------------------------------------|
| `GET`    | `/names`                    | Lista nomes de todos os campeões   |
| `GET`    | `/region/{region}`          | Filtra campeões por região         |
| `GET`    | `/class/{classType}`        | Filtra campeões por classe         |
| `GET`    | `/role/{role}`              | Filtra campeões por função         |
| `GET`    | `/tier/{tier}`              | Filtra campeões por tier           |
| `POST`   | `/addChampion`              | Adiciona novo campeão              |
| `PUT`    | `/champions`                | Atualiza campeão existente         |
| `DELETE` | `/deleteChampion`           | Remove campeão por nome e função   |

### Valores aceitos pelos filtros

- **Regiões:** `Bandle City`, `Bilgewater`, `Demacia`, `Freljord`, `Ionia`, `Ixtal`, `Noxus`, `Piltover`, `Runeterra`, `Shadow Isles`, `Shurima`, `Targon`, `The Void`, `Zaun`
- **Classes:** `Assassin`, `Fighter`, `Mage`, `Marksman`, `Support`, `Tank`
- **Funções:** `TOP`, `JUNGLE`, `MID`, `ADC`, `SUPPORT`
- **Tiers:** `S+`, `S`, `A`, `B`, `C`, `D`

---

## 📋 Exemplos de Requisição

**Adicionar campeão**
```json
POST /addChampion
{
  "name": "Ahri",
  "region": "Ionia",
  "classType": "Mage",
  "role": "MID",
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

**Deletar campeão**
```
DELETE /deleteChampion?name=Ahri&role=MID
```

---

## ▶️ Como Executar

### Pré-requisitos

- [Java 21+](https://www.oracle.com/java/technologies/downloads/)
- [PostgreSQL](https://www.postgresql.org/download/) instalado e rodando
- [Maven](https://maven.apache.org/) (ou usar o `./mvnw` incluído no projeto)
- Arquivo CSV dos campeões disponível em: [Kaggle — LoL Champions Dataset](https://www.kaggle.com/datasets/uskeche/leauge-of-legends-champions-dataset)

### Passo a passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/TiagoEich/league-of-legends-api.git
   ```

2. **Adicione o arquivo CSV**

   Baixe o arquivo CSV do link acima e coloque em:
   ```
   src/main/resources/lolchamps.csv
   ```

3. **Crie o banco de dados no PostgreSQL**
   ```sql
   CREATE DATABASE lolapi;
   ```

4. **Configure as variáveis de ambiente**

   Antes de executar, defina as variáveis de ambiente na sua máquina:
   - `DATASOURCE_URL` → ex: `jdbc:postgresql://localhost:5432/lolapi`
   - `DATASOURCE_USERNAME` → seu usuário do PostgreSQL
   - `DATASOURCE_PASSWORD` → sua senha do PostgreSQL

5. **Execute o projeto**
   ```bash
   ./mvnw spring-boot:run
   ```
   > Na primeira execução, os dados do CSV serão importados automaticamente para o banco.

6. **Acesse a documentação da API**

   Abra no navegador: `http://localhost:8080/swagger-ui.html`

---

## 🧪 Testes

O projeto conta com testes automatizados cobrindo as principais camadas:

- **Controller** — testes de endpoints com MockMvc
- **Service** — testes de lógica de negócio com Mockito
- **Validators** — testes unitários de cada validador
- **CsvService** — testes de importação e leitura do arquivo CSV
- **Model** — testes de criação, equals, hashCode e toString

Para executar os testes:
```bash
./mvnw test
```
