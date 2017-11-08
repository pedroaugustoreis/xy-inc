Aplicações necessárias para construir e executar a aplicação:

- JDK 8
- Maven 3
- MySQL

Para gerar a base de dados no MySQL executar o script localizado em docs/sql/poi.sql.
Esse script criará a base de dados e realizará os inserts de alguns POIs iniciais.


Com a base de dados criada, executar o seguinte comando para rodar os testes automáticos:

	mvn clean test

Para construir e realizar deploy da aplicação executar o seguinte comando:

	mvn clean spring-boot:run

A aplicação então estará disponível na porta 8080 e os seguintes serviços REST estarão disponíveis:

serviço para listagem de todos os POIs (HTTP GET):
	http://localhost:8080/api/poi

serviço para criação de um novo POI (HTTP POST):
	http://localhost:8080/api/poi

	modelo de payload (json):
	{"name": "Universidade", "positionX": 61, "positionY": 58}

serviço para listagem de todos os POIs a uma certa distância de um ponto de referência (HTTP GET)
	http://localhost:8080/api/poi/findByReferenceAndDistance?positionX=55&positionY=60&distance=10

commit 1
commit 2

commit 3
commit 4

commit 1 branch

commit 5


