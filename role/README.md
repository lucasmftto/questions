## Responsavel pelas roles do forum

# Projeto fórum de pergunstas e respostas

###### Configurar a conexão com BD no applications.properties.
    spring.datasource.url=jdbc:mysql://localhost:3306/questions
    spring.datasource.username=<meuUsuario>
    spring.datasource.password=<minhaSenha>

###### Criar os banco de dados: questions:
    CREATE DATABASE questions;

###### Para realizar a chamada dos andpoints pode utilizar o Swagger
    http://localhost:8080/swagger-ui.html#/

###### Tambem pode utilizar a collection do Postman Disponibilizado na raiz do projeto
    Questions.postman_collection.json

