package application;

import dao.PedidoDAO;
import dao.ProdutoDAO;

import db.DB;
import db.DbIntegrityExeption;

import entities.Pedido;
import entities.Produto;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.List;

public class MainApp extends Application {


    private ClienteDAO clienteDao = new ClienteDAO();
    private ProdutoDAO produtoDao = new ProdutoDAO();
    private EntregadorDAO entregadorDao = new EntregadorDAO();
    private PedidoDAO pedidoDao = new PedidoDAO();

    private Label lblStatus;
    private Label lblConexao;

    private TextField txtClienteId;
    private TextField txtClienteNome;
    private TextField txtClienteTelefone;
    private TextField txtClienteEmail;
    private TableView<Cliente> tabelaClientes;

    private TextField txtProdutoId;
    private TextField txtProdutoNome;
    private TextField txtProdutoPreco;
    private TableView<Produto> tabelaProdutos;

    private TextField txtEntregadorId;
    private TextField txtEntregadorNome;
    private TextField txtEntregadorTelefone;
    private TableView<Entregador> tabelaEntregadores;

    private TextField txtPedidoId;
    private ComboBox<Cliente> cbCliente;
    private ComboBox<Produto> cbProduto;
    private ComboBox<Entregador> cbEntregador;
    private TextField txtQuantidade;
    private ComboBox<String> cbStatus;
    private TableView<Pedido> tabelaPedidos;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sistema de Delivery");

        lblStatus = new Label("Status: sistema iniciado.");
        lblStatus.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");

        TabPane abas = new TabPane();

        Tab abaInicio = new Tab("Início", criarTelaInicio());
        Tab abaClientes = new Tab("Clientes", criarTelaClientes());
        Tab abaProdutos = new Tab("Produtos", criarTelaProdutos());
        Tab abaEntregadores = new Tab("Entregadores", criarTelaEntregadores());
        Tab abaPedidos = new Tab("Pedidos", criarTelaPedidos());

        abaInicio.setClosable(false);
        abaClientes.setClosable(false);
        abaProdutos.setClosable(false);
        abaEntregadores.setClosable(false);
        abaPedidos.setClosable(false);

        abas.getTabs().addAll(abaInicio, abaClientes, abaProdutos, abaEntregadores, abaPedidos);

        VBox root = new VBox(10, abas, lblStatus);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 900, 650);
        stage.setScene(scene);
        stage.show();

        testarConexao();
        atualizarListas();
    }

    private VBox criarTelaInicio() {
        Label titulo = new Label("Aplicativo Delivery");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label texto = new Label("BEM VINDOS.");
        lblConexao = new Label("Testando conexão...");

        return new VBox(15, titulo, texto, lblConexao);
    }

    private VBox criarTelaClientes() {
        txtClienteId = new TextField();
        txtClienteId.setPromptText("ID automático");
        txtClienteId.setEditable(false);

        txtClienteNome = new TextField();
        txtClienteNome.setPromptText("Nome do cliente");

        txtClienteTelefone = new TextField();
        txtClienteTelefone.setPromptText("Telefone");

        txtClienteEmail = new TextField();
        txtClienteEmail.setPromptText("Email");

        tabelaClientes = new TableView<>();

        TableColumn<Cliente, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(x -> new SimpleIntegerProperty(x.getValue().getId()).asObject());
        colId.setPrefWidth(60);

        TableColumn<Cliente, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getNome()));
        colNome.setPrefWidth(180);

        TableColumn<Cliente, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getTelefone()));
        colTelefone.setPrefWidth(140);

        TableColumn<Cliente, String> colEndereco = new TableColumn<>("Email");
        colEndereco.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getEmail()));
        colEndereco.setPrefWidth(280);

        tabelaClientes.getColumns().addAll(colId, colNome, colTelefone, colEndereco);
        tabelaClientes.setPrefHeight(300);

        tabelaClientes.setOnMouseClicked(e -> {
            Cliente c = tabelaClientes.getSelectionModel().getSelectedItem();
            if (c != null) {
                txtClienteId.setText(String.valueOf(c.getId()));
                txtClienteNome.setText(c.getNome());
                txtClienteTelefone.setText(c.getTelefone());
                txtClienteEmail.setText(c.getEmail());
            }
        });

        Button btnSalvar = new Button("Salvar");
        Button btnEditar = new Button("Editar");
        Button btnDeletar = new Button("Deletar");
        Button btnLimpar = new Button("Limpar");

        btnSalvar.setOnAction(e -> salvarCliente());
        btnEditar.setOnAction(e -> editarCliente());
        btnDeletar.setOnAction(e -> deletarCliente());
        btnLimpar.setOnAction(e -> limparCliente());

        HBox botoes = new HBox(10, btnSalvar, btnEditar, btnDeletar, btnLimpar);

        return new VBox(8,
                new Label("ID:"), txtClienteId,
                new Label("Nome:"), txtClienteNome,
                new Label("Telefone:"), txtClienteTelefone,
                new Label("Email:"), txtClienteEmail,
                botoes,
                tabelaClientes
        );
    }

    private VBox criarTelaProdutos() {
        txtProdutoId = new TextField();
        txtProdutoId.setPromptText("ID automático");
        txtProdutoId.setEditable(false);

