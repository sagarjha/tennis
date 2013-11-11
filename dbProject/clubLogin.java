package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

class playerLogin extends HttpServlet{
    public void playerLoginHandler (int id, HttpServletRequest request, ResultSet rs, Statement stmt) throws SQLException{
	// set various attributes required for displaying the correct information on the profile page
	request.setAttribute("name",rs.getString("name"));		    
	
	request.setAttribute("address", rs.getString("address"));
	request.setAttribute("emailid", rs.getString("emailid"));
	request.setAttribute("phoneno", rs.getString("phonenum"));
	request.setAttribute("description", rs.getString("description"));
	request.setAttribute("numcourts",rs.getString("numcourts"));
	request.setAttribute("coachingslot",rs.getString("coachingslot"));
	
	{
	{
	    String query = "select * from news where clubid="+id+" ;";
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
	    String news = "";
	    String news1 = "";
	    String news2 = "";
	    String news3 = "";
	    int count = 1; 
	    
	    if(rs.next()) {
			news += count + ". " + rs.getString("description") + "\\n";
			news1 = rs.getString("description");
			count++;
		}
		
		if(rs.next()) {
			news += count + ". " + rs.getString("description") + "\\n";
			news2 = rs.getString("description");
			count++;
		}
		
		if(rs.next()) {
			news += count + ". " + rs.getString("description") + "\\n";
			news3 = rs.getString("description");
			count++;
		}
	    
	    while (rs.next()) {
		news += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    System.out.println(news);
	    request.setAttribute("news",news);
	    request.setAttribute("news1",news1);
	    request.setAttribute("news2",news2);
	    request.setAttribute("news3",news3);
	}
	
	
	
	}
}
	
