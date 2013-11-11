package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

class umpireLogin extends HttpServlet{
    public void umpireLoginHandler (int id, HttpServletRequest request, ResultSet rs, Statement stmt) throws SQLException{
        System.out.println("In umpireLogin.java");
        
        int yearOfbirth = rs.getInt("yearOfBirth");
        int umpStartYear = rs.getInt("umpiringstartyear");
        
        // set various attributes required for displaying the correct information on the profile page
	request.setAttribute("name",rs.getString("name"));		    
	request.setAttribute("gender",rs.getString("gender"));
	request.setAttribute("address", rs.getString("address"));
	request.setAttribute("emailid", rs.getString("emailid"));
	request.setAttribute("phoneno", rs.getString("phonenum"));
	request.setAttribute("description", rs.getString("description"));
        request.setAttribute("clubid", rs.getInt("clubid"));
        
        // get club name
        String query = "Select name from club where id = "+ rs.getInt("clubid") + ";";
        rs = stmt.executeQuery(query);
        if(rs.next()){
            request.setAttribute("clubname", rs.getString("name"));
        }
        
        //get current year
        query = "Select extract(year from (select datevalue from constant where constantname = 'time')) as year;";
        rs = stmt.executeQuery(query);
        if(rs.next()){
            request.setAttribute("age", rs.getInt("year") - yearOfbirth);
            request.setAttribute("experience",rs.getInt("year") - umpStartYear);
        }
        
        
        
    }
}