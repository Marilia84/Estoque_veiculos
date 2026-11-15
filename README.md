# carroProject — Sistema Automotivo: Gestão de Estoque de Veículos

API REST em Java 17 + Spring Boot 3 + MySQL.

## Como rodar
1. Tenha o JDK 17+ e o MySQL rodando.
2. Em `src/main/resources/application.properties`, troque `spring.datasource.password` pela sua senha.
3. No terminal, dentro da pasta do projeto: `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`).
4. A API sobe em http://localhost:8080

## Endpoints
| Método | Rota | O que faz |
|---|---|---|
| POST | /marcas | cria marca `{"nome":"Fiat"}` |
| GET | /marcas, /marcas/{id} | lista / busca |
| PUT | /marcas/{id} | atualiza |
| DELETE | /marcas/{id} | remove |
| POST | /modelos | cria modelo `{"nome":"Uno","marcaId":1}` |
| GET | /modelos?marcaId=1 | lista (filtro opcional por marca) |
| PUT / DELETE | /modelos/{id} | atualiza / remove |
| POST | /veiculos | cria veículo (exemplo abaixo) |
| GET | /veiculos?marca=&modelo=&ano=&precoMin=&precoMax=&status= | consulta com filtros opcionais |
| PUT | /veiculos/{id} | atualiza preço, quilometragem, status |
| DELETE | /veiculos/{id} | remove |

Exemplo de veículo:
```json
{ "modeloId": 1, "ano": 2020, "cor": "Prata", "preco": 42500.00, "quilometragem": 38000, "status": "DISPONIVEL" }
```
Status possíveis: DISPONIVEL, VENDIDO, RESERVADO.
