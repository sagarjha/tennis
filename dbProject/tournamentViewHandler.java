package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class tournamentViewHandler extends HttpServlet{
    public void handleView (Connection conn, ServletRequest request) throws SQLException{
	System.out.println("In tournamentViewHandler.java");
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery ("select datevalue from constant where constantname='time'");
	rs.next();
	String date = rs.getString("datevalue");
	
	{
	    // upcoming tournaments
	    String query = "select * from tournament where winnerid is null and startdate > '" + date + "' order by startdate";
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
	    String upcomingTournamentsData = "";
	    while (rs.next()) {
		query = "select name from club where id = " + rs.getString("clubid");
		System.out.println(query);
		Statement stmt1 = conn.createStatement();
		ResultSet rs1 = stmt1.executeQuery (query);
		rs1.next();
		upcomingTournamentsData += "<tr>\n<td>\n" + "<button type=\"Submit\" style=\"border:none;color:blue\" name=\"TOURNAMENTVIEW\" value = \"" + rs.getString("id") + "\">" + rs.getString("name") + "\n </td>\n <td>\n" + rs1.getString("name") + "\n</td>\n<td>\n" + rs.getString("startdate") + "\n</td>\n<td>\n" + rs.getInt("numplayers") + "\n</td>\n<td>\n" + rs.getString("prizemoney") + "\n</td>\n</tr>\n";
	    }
	    System.out.println(upcomingTournamentsData);
	    request.setAttribute("upcomingTournamentsData",upcomingTournamentsData);
	}

	{
	    // ongoing tournaments
	    String query = "select * from tournament where winnerid is null and startdate <= '" + date + "' order by startdate";
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
	    String ongoingTournamentsData = "";
	    while (rs.next()) {
		query = "select name from club where id = " + rs.getString("clubid");
		System.out.println(query);
		Statement stmt1 = conn.createStatement();
		ResultSet rs1 = stmt1.executeQuery (query);
		rs1.next();
		ongoingTournamentsData += "<tr>\n<td>\n" + "<input type=\"Submit\" style=\"border:none;color:blue\" name=\"TOURNAMENTVIEW\" value = \"" + rs.getString("id") + "\"" + rs.getString("name") + ">\n </td>\n <td>\n" + rs1.getString("name") + "\n</td>\n<td>\n" + rs.getString("startdate") + "\n</td>\n<td>\n" + rs.getInt("numplayers") + "\n</td>\n<td>\n" + rs.getString("prizemoney") + "\n</td>\n</tr>\n";
	    }

	    request.setAttribute("ongoingTournamentsData",ongoingTournamentsData);
	}
    }
}
