package molyanova_lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Molyanova_Lab2 extends Application {
    private static final  String url = "jdbc:mysql://localhost:3306/knigi?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
            
    private static  String user = "root";
    private static final String password = "";

    // Переменные JDBC для открытия и управления соединением
    private static Connection con;
    private static Statement stmt;
    static ResultSet rs;
   
    /*static void iniBD(){
        String query = "select * from Knigi";// вывод таблицы с базы данных select * from   .... //название таблицы

        try {
            //открытие подключения базы данных к серверу MySQL
            con = DriverManager.getConnection(url, user, password);

            // получение объекта Statement для выполнения запроса
            stmt = con.createStatement();

            // выполнение запроса SELECT
            rs = stmt.executeQuery(query);
            
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
           // try { con.close(); } catch(SQLException se) { /*can't do anything */ //}
            //try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            //try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
       /* }
    } */ 
    @Override
    public void start(Stage stage) throws Exception {
        //iniBD();
        FXMLLoader loader = new FXMLLoader (getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();    
        FXMLDocumentController controller = loader.getController();
        
        Scene scene = new Scene(root);
        controller.loadDB();
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    } 
}