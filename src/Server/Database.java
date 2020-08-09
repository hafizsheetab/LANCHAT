package Server;


import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Database {
    static String url = "jdbc:oracle:thin:@localhost:1521/XE";;
    static String admin = "SYSTEM";
    static String password = "1234";
    public static void putUserName(String userName){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url,admin,password);
            Statement statement = connection.createStatement();
            String sqlQuery = "INSERT INTO USERNAMES(NAME) VALUES('" + userName +"')";
            statement.execute(sqlQuery);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String getUserNames(){
        String userNames = "";
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url,admin,password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM USERNAMES";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            Integer count = 1;
            while(resultSet.next()){
                String userName = resultSet.getString(1);
                userNames = userNames + count.toString() + " " + userName + " ";
                count ++;
            }
            return userNames;


        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return userNames;
        }
    }
    public static void putIntoMessageLog(String fromUserName,String toUserName,String message){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url,admin,password);
            Statement statement = connection.createStatement();
            String sqlQuery = "INSERT INTO MESSAGE_LOG(FROMUSERNAME,TOUSERNAME,MESSAGE) VALUES('" + fromUserName + "','" + toUserName + "','" +message+"')";
            statement.execute(sqlQuery);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String getMessageLog(){
        String messageLogs = "";
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url,admin,password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM MESSAGE_LOG";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            Integer count = 1;
            while(resultSet.next()){
                String fromUserName = resultSet.getString(1);
                String toUserName = resultSet.getString(2);
                String message = resultSet.getString(3);
                String messageLog = fromUserName +" -> " + toUserName + " : " +message;
                messageLogs = messageLogs + count.toString() + " " + messageLog;
                count ++;
            }
            return messageLogs;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return messageLogs;
        }
    }
}
