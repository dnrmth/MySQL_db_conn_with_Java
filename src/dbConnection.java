
import java.sql.*;

public class dbConnection {
    public static void main(String[] args) {

        String dbURL = "jdbc:mysql://localhost:3306/world";
        String dbName = "root";
        String dbPassword = "21091998";

        try {
            Connection connection = DriverManager.getConnection(dbURL, dbName, dbPassword);
            String query =  "SELECT c.Code, c.Name, cl.Language "
                    + "FROM Country c "
                    + "JOIN CountryLanguage cl ON c.Code = cl.CountryCode "
                    + "where cl.IsOfficial = 'T'"
                    + "LIMIT 10";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                System.out.println("O país: " + rs.getString("Name") + ". Fala a língua: " + rs.getString("Language"));
                Connection conn = DriverManager.getConnection(dbURL, dbName, dbPassword);
                conn.close();
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}
