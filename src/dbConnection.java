import java.sql.*;
import java.util.Scanner;

public class dbConnection {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the database password: ");

        String dbURL = "jdbc:mysql://localhost:3306/world";
        String dbName = "root";
        String dbPassword = scanner.next();

        try {
            Connection connection = DriverManager.getConnection(dbURL, dbName, dbPassword);
            String query = "SELECT c.Code, c.Name, cl.Language "
                    + "FROM Country c "
                    + "JOIN CountryLanguage cl ON c.Code = cl.CountryCode "
                    + "where cl.IsOfficial = 'T'"
                    + "LIMIT 10";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println("Country: " + rs.getString("Name") + ". Official language: " + rs.getString("Language"));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}
