package molyanova_lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class DateBaseKniga{
    private static final  String url = "jdbc:mysql://localhost:3306/knigi?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static  String user = "root";
    private static final String password = "";
    
    String[] columns;//название столблов таблицы
    Class[] columnClass;//тип столбцов таблицы
    
    String sqlQuery;

    // Переменные JDBC для открытия и управления соединением
    private static Connection con;
    private static Statement stmt;
    static ResultSet rs;
    
    private DefaultTableModel dtm;
    
    private Object[][] rsm;
    
    DateBaseKniga(){
        try {
            //открытие подключения базы данных к серверу MySQL
            con = DriverManager.getConnection(url, user, password);
            // получение объекта Statement для выполнения запроса
            stmt = con.createStatement();
            // выполнение запроса SELECT
          //  rs = stmt.executeQuery(query);
            
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
        }
    }
    
    public DefaultTableModel MyTableModeIzdatel () {
    try {
        stmt = con.createStatement();
        rs = stmt.executeQuery(sqlQuery);
        
        createTable(rs,columns,columnClass);
        
        close(stmt);
        return dtm;
    }
    catch(SQLException e){
        return null;
    }
}
    private void createTable (ResultSet rs, String [] columns, final Class [] columnClass){
        try{
            ResultSetMetaData meta = rs.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            
            dtm = new DefaultTableModel (rsm,columns){
                @Override
                public boolean isCellEditable
                        (int row, int column){
                            return false;
                        }
                @Override
                public Class<?> getColumnClass(int columnIndex){
                    return columnClass [columnIndex];
                }
            };
            while (rs.next()){
                Object[] rowData = new Object[numberOfColumns];
                for (int i = 0; i < rowData.length; ++i){
                    rowData[i] = rs.getObject(i + 1);
                }
                dtm.addRow(rowData);
            }
        }
        catch (SQLException e){
            System.out.println("Error createTable");
        }
    }
    private void close (java.sql.Statement stmt){
        if (stmt!=null){
            try { stmt.close();
            }
            catch(Exception e){}
        }
    }
    /*public void deleteItem(){
        try {
            String sqlDel = "delete from Avtor where IdAvtor='"+tfam.getText()+"'";
            stmt= conn.createStatement();
            stmt.executeUpdate(sqlQuery);
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    public void stop(){
        //close connection ,stmt and resultset here
         try { con.close(); } catch(SQLException se) { /*can't do anything */ }
         try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
         try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
    }   
}