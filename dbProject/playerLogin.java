package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

class playerLogin extends HttpServlet{
    public void playerLoginHandler (int id, HttpServletRequest request, ResultSet rs, Statement stmt) throws SQLException{
	System.out.println("In playerLogin.java");
	// set various attributes required for displaying the correct information on the profile page
	request.setAttribute("name",rs.getString("name"));		    
	request.setAttribute("gender",rs.getString("gender"));
	request.setAttribute("age",2013 - rs.getInt("yearOfBirth"));
	request.setAttribute("address", rs.getString("address"));
	request.setAttribute("emailid", rs.getString("emailid"));
	request.setAttribute("phoneno", rs.getString("phonenum"));
	request.setAttribute("description", rs.getString("description"));
	request.setAttribute("numMatchesPlayed",rs.getString("numMatchesPlayed"));
	request.setAttribute("numMatchesWon",rs.getString("numMatchesWon"));
	request.setAttribute("numMatchesLost",rs.getInt("numMatchesPlayed") - rs.getInt("numMatchesWon"));
	request.setAttribute("rating",rs.getString("rating"));
	request.setAttribute("highestRatingAchieved",rs.getString("highestRatingAchieved"));
		    
	// set the attribute private notes
	{
	    String query = "select * from record where playerid="+id+" and type='Private';";
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
	    String records = "";
	    int count = 1;
	    while (rs.next()) {
		records += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    System.out.println(records);
	    request.setAttribute("privateNotes",records);
	}

	// set the attribute public notes
	{
	    String query = "select * from record where playerid="+id+" and type='Public';";
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
	    String records = "";
	    int count = 1;
	    while (rs.next()) {
		records += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    System.out.println(records);
	    request.setAttribute("publicNotes",records);
	}
    }
}
