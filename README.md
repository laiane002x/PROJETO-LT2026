# Projeto LT 2026

## Integrantes do grupo:

- ANA CAROLINA CARVALHO ROCHA
- REBECA BARBOSA DA SILVA PEREIRA
- ISADORA MIRANDA SANTOS
- LAIANE RIBEIRO DA SILVA
- KAUAN FELIPE DE OLIVEIRA SILVA

## Sobre o projeto

Este projeto foi desenvolvido como parte da disciplina de Linguagem Tecnica, com o objetivo de aplicar conceitos de programação em Java e integração com banco de dados relacional.

## ⚙️ Como configurar o banco de dados

1. Abra o MySQL (ou outro SGBD de sua preferência).
2. Crie o banco de dados executando o comando:

## Como rodar

1. Abra o MySQL Workbench.
2. Execute o arquivo `database.sql` que está na raiz do projeto.
3. Confira o arquivo `db.properties`:

```properties
dburl=jdbc:mysql://localhost:3306/delivery
user=root
password=1234
```

4. Se sua senha do MySQL for diferente, altere apenas o campo `password`.
5. Abra o projeto no IntelliJ.
6. Rode a classe `application.MainApp`.

## Telas

- Início
- Clientes
- Produtos
- Entregadores
- Pedidos

O projeto foi feito no mesmo estilo do professor, criando as telas diretamente no Java, sem FXML.
