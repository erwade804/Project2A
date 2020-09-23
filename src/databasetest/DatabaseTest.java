package databasetest;


import java.sql.*;
import org.json.simple.*;


public class DatabaseTest {
    static Connection con;

    public static void main(String[] args) {
        getJSONData();
    }
    
    
    public static JSONArray getJSONData(){
        JSONArray last = new JSONArray();
        try {
            String server = ("jdbc:mysql://localhost/p2_test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            String username = "root";
            String password = "CS488";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            
            con = DriverManager.getConnection(server, username, password);
            
            Statement st = con.createStatement();
            String sql = "SELECT * FROM p2_test.people";
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                JSONObject obj = new JSONObject();
                obj.put("firstname", rs.getString("firstname"));
                obj.put("middleinitial", rs.getString("middleinitial"));
                obj.put("lastname", rs.getString("lastname"));
                obj.put("address", rs.getString("address"));
                obj.put("city", rs.getString("city"));
                obj.put("state", rs.getString("state"));
                obj.put("zip", rs.getString("zip"));
                last.add(obj);

            }   
            con.close();
            
        }catch(Exception e){
        }
        return last;
    }
    
}