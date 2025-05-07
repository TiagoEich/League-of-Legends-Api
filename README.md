League of Legends Champions Info API 🏆
Uma API Spring Boot para gerenciar informações sobre campeões do League of Legends (LoL), incluindo dados como região, classe, função, tier e estatísticas de jogo.

Tecnologias Utilizadas
Java 21

Spring Boot 3.4.4

Spring Data JPA (PostgreSQL)

Lombok (para reduzir boilerplate)

Mockito/JUnit (testes unitários)

Swagger/OpenAPI (documentação da API)

Funcionalidades
✅ CRUD Completo

Adicionar, atualizar, buscar e deletar campeões.

✅ Filtros Avançados

Buscar campeões por:

Região (ex: Demacia, Noxus)

Classe (ex: Assassin, Mage)

Função (Role) (ex: Top, Mid, Jungle)

Tier (ex: S, A, B)

✅ Validações

Garante que os dados inseridos sigam regras específicas (ex: tier válido, região existente).

Como Rodar o Projeto
Pré-requisitos
JDK 21 instalado

PostgreSQL rodando (ou configurar outro banco em application.properties)

Maven

Passos
Clone o repositório:

bash
git clone https://github.com/seu-usuario/lol-champs-api.git
Configure o banco de dados em:
src/main/resources/application.properties

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lolchamps
spring.datasource.username=seu_user
spring.datasource.password=suasenha
Rode a aplicação:

bash
mvn spring-boot:run
Acesse a documentação (Swagger UI):
http://localhost:8080/swagger-ui.html

Endpoints da API
Método	Endpoint	Descrição
GET	/api/champions	Lista todos os campeões
GET	/api/champions/names	Retorna apenas os nomes dos campeões
GET	/api/champions/region/{region}	Filtra por região (ex: Demacia)
GET	/api/champions/class/{classType}	Filtra por classe (ex: Mage)
GET	/api/champions/role/{role}	Filtra por função (ex: Mid)
GET	/api/champions/tier/{tier}	Filtra por tier (ex: S)
POST	/api/champions	Adiciona um novo campeão
PUT	/api/champions	Atualiza um campeão existente
DELETE	/api/champions?name={name}&role={role}	Deleta um campeão por nome e função
Exemplo de JSON (POST/PUT)
json
{
  "name": "Ahri",
  "region": "Ionia",
  "classType": "Mage",
  "role": "Mid",
  "tier": "S",
  "score": 9.5,
  "trend": 1.2,
  "winRate": 52.3,
  "roleRate": 85.0,
  "pickRate": 15.7,
  "banRate": 30.2,
  "kda": 3.5
}
