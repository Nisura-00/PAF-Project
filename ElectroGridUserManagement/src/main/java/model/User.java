package model;
import java.sql.* ;

public class User
{

public Connection connect()
{
         Connection con = null;
          try
          {
             Class.forName("com.mysql.jdbc.Driver");
             con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usermanagement",
                                              "root", "");
//For testing
          System.out.print("Successfully connected");
          }
          catch(Exception e)
          {
             e.printStackTrace();
          }
return con;
}


//read
public String readItems()
{
        String output = "";
        try
        {
            Connection con = connect();
            if (con == null)
            {
                 return "Error while connecting to the database for reading.";
            }
        // Prepare the html table to be displayed
        output = "<table border='1'><tr><th>Name</th>"
               +"<th>Phone Number</th><th>Email</th>"
               + "<th>User Name</th>"
               + "<th>Password</th>"
               + "<th>Update</th><th>Remove</th></tr>";
        String query = "select * from users";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
       // iterate through the rows in the result set
       while (rs.next())
      {
               String userID = Integer.toString(rs.getInt("userID"));
               String name = rs.getString("name");
               String phoneNum = Integer.toString(rs.getInt("phoneNum"));
               String email = rs.getString("email");
               String userName = rs.getString("userName");
               String password = rs.getString("password");
               
       // Add a row into the html table
       output += "<tr><td>" + name + "</td>";
       output += "<td>" + phoneNum + "</td>";
       output += "<td>" + email + "</td>";
       output += "<td>" + userName + "</td>";
       output += "<td>" + password + "</td>";
      
       //buttons
       output += "<td><input name='btnUpdate' "
              + " type='button' value='Update'></td>"
              + "<td><form method='post' action='users.jsp'>"
              + "<input name='btnRemove' "
              + " type='submit' value='Remove'>"
              + "<input name='userID' type='hidden' "
              + " value='" + userID + "'>" + "</form></td></tr>";
        }
        con.close();
        //Complete the html table
        output += "</table>";
       }
       catch (Exception e)
        {
           output = "Error while reading the users.";
           System.err.println(e.getMessage());
        }
        return output;
}
}