package org.example;

import org.w3c.dom.ls.LSOutput;

import java.sql.Connection; // essa importação serve para abrir a porta para a conexão com o banco
import java.sql.DriverManager; // Ajuda abrir a porta
import java.sql.SQLException; // Ajuda a saber se houve algum problema no sql
import java.sql.SQLOutput;


public class DatabaseConnection {


    private static final String URL = "jdbc:mysql://localhost:3306/login_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // o codigo acima serve para abrirmos a conexão e a porta para conseguirmos interagirmos com alguma conexão.
    // ele vai d3evolver a porta aberta com o objeto ( connection )

    public static Connection getConnection(){
        Connection connection = null; // começa sem a porta aberta

        try{
            // Isso aqui é como falar para o Java: "Prepare-se para falar com o MySQL!"
            // É um código mágico que ele precisa pra saber com quem vai conversar.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Agora sim! A gente pede pro Java "abrir a porta" usando as informações do cartão.

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Porta conectada com sucesso!");
        } catch (ClassNotFoundException e) {
            // se o java não encontrar o banco ele não conecta e vai dar uma mensagem
            System.err.println("O java não encontrou o amigo dele SQL para fazer a conexão");
        } catch (SQLException e) {
            // caso de algum problema para abrir a porta
            System.err.println("deu problema para abrir a porta no banco de dados");
            e.printStackTrace(); // nesta linha esse codigo é utilizado para mostrar detalhes sobre o erro
        }

        return connection; // devolve a porta aberta ( Ou nada se der erro )
    }

    public static void closeConnection(Connection connection){
        if(connection != null){ // se a conexão não for nula, ou seja s ela existir, ela poderá ser fechada

            try{
                connection.close(); // ele vai pedir para fechar nesta linha
                System.out.println("fechou a porta valeu ");
            } catch(SQLException e){
                System.err.println("Deu um problema para fechar a porta no banco de dados");
                e.printStackTrace();

            }
        }
    }

}
