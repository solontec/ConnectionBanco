package org.example;

import java.net.Authenticator;
import java.sql.Connection;     //a porta que a gente abriu
import java.sql.PreparedStatement; // ajuda a fazaer perguntas seguras ao banco
import java.sql.ResultSet; // Onde recebemos as respostas do banco
import java.sql.SQLException; // ajuda a resolver se houve algum problema
import java.util.Scanner;



public class LoginSystem {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // prepara o leitor do teclado

        System.out.println("bem vindo ao login");

        System.out.println("Para entrar digite seu userr");
        String user = scanner.nextLine(); // guarda os dados que você digitou do user dentro dessa variavel

        System.out.println("Agora digite a sua senha para entrar");
        String password = scanner.nextLine(); // guarda os dados que você inseriu no pass dentro da variavel também

        if (authenticateUser(user, password)) {
            System.out.println("Login realizado com sucesso!" + user);
        } else {
            System.out.println("Droga, nome de usuario ou senha não foram encontrados, tente novamente");
        }
        scanner.close(); // fecha o leitor do teclado
    }

    /**
     * Este é o metodo que vai perguntar para o banco se o usuario e a senha estão corretos
     * Eles nos devolve TRUE se estiver certo, e FALSE se estiver errado
     */

    public static boolean authenticateUser(String user, String password) {
        Connection connection = null; // vai começar sem a porta
        PreparedStatement preparedStatement = null; // começamos sem a pergunta pronta
        ResultSet resultSet = null; // começamos sem a resposta
        boolean isAuthenticated = false; // por enquanto não está autenticado

        try {
            connection = DatabaseConnection.getConnection(); // pede para abrir a porta do banco de dados
            // Agora, a gente monta a pergunta para o caderno.
            // A pergunta é: "Existe alguém na tabela 'usuarios' que tem esse nome E essa senha?"
            // Os '?' são lugares seguros onde vamos colocar o nome e a senha.

            String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user); // coloca o user nesse primeiro
            preparedStatement.setString(2, password); // coloca a pass nessa segunda

            resultSet = preparedStatement.executeQuery(); // manda a pergunta pro banco e adquiri as respostas.

            if (resultSet.next()) { //  Isso significa: "Tem uma próxima linha na resposta?" ???
                isAuthenticated = true; // sim!! Então o login pode ser realizado e o user ta atenticado

            }

        } catch (SQLException e) {
            // se der problema na hora de fzer a pergunta ou pegar a resposta da pessoa
            System.err.println("Ops, deu erro  na hora de checar o login" + e.getMessage()); // perguntar o que é o E message
            e.printStackTrace();

        } finally {
            // IMPORTANTE PRA CARAMBA ESTA PARTE!!
            // É COMO ARRUMAR A BAGUNÇA DEPOIS DE BRINCAR
            // A GENTE FECHA AS COISAS NA ORDEM INVERSA QUE ABRIU
        }

        try {
            if (resultSet != null) resultSet.close(); // Fecha a resposta ???
            if (preparedStatement != null) preparedStatement.close(); // Fecha a pergunta pronta????
            DatabaseConnection.closeConnection(connection);     // E fecha a porta do banco!
        } catch (SQLException e) {
            System.out.println("Deu um problema para arrumar a bagunça"); // pq arrumar bagunça, nao entendi
            e.printStackTrace();
        }
        return isAuthenticated; // devolve se conseguiu autenticar ou n
    }

}
