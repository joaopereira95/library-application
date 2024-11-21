# Library application

## Ferramentas e softwares utilizados:
* React
* MUI
* Bootstrap
* Java 21
* Spring Boot 3
* PostgreSQL
* Docker

Para executar, certifique-se de que as portas **3000**, **8080** e **5432** não estejam sendo utilizadas por nenhum outro processo e que possui o Docker instalado em seu computador.

## Comandos para execução:
git clone https://github.com/joaopereira95/library-application.git  
cd library-application/  
docker-compose up  

### URL de acesso a aplicação web: 
http://localhost:3000/

### URL de acesso a documentação da API: 
http://localhost:8080/swagger-ui/index.html

## Descrição dos componentes:
**library-ui:** interface web para gerenciamento dos livros e consulta à API Google Books.  
**library-api:** endpoints para gerenciamento dos livros e consulta à API Google Books. 
