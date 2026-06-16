# Projeto Delivery - JavaFX + JDBC + MySQL

Projeto corrigido para usar o banco `delivery` com as tabelas do dump enviado:
`cliente`, `produto`, `entregador`, `pedido`, `item_pedido` e `entrega`.

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
