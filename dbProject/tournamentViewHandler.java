package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class tournamentViewHandler extends HttpServlet{
    public void handleView (Connection conn) throws SQLException{
	System.out.println("In tournamentViewHandler.java");
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery ("select datevalue from constant where constantname='time'");
	rs.next();
	String date = rs.getString("datevalue");
	
	// upcoming tournaments
	String query = "select * from tournament where winnerid is null and startdate > '" + date + "' order by startdate";
	System.out.println(query);
	rs = stmt.executeQuery (query);
	String upcomingTournamentsData = "";
	while (rs.next()) {
	    upcomingTournamentsData += "<tr>\n<td>\n<a href=\"clubtournament.jsp\">King's Club Annual Tournament</a>\n </td>\n <td>\nKing's Club\n</td>\n<td>\n	09/09/2013\n</td>\n<td>\n32\n</td>\n<td>\n20000\n</td>\n</tr>\n";
	}

	// ongoing tournaments
    }
}
