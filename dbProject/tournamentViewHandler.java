package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class tournamentViewHandler extends HttpServlet{
    public void handleView (Connection conn) throws SQLException{
	System.out.println("In tournamentViewHandler.java");
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery ("select datevalue from constant where constantname=time");
	String date = rs.getString("datevalue");
	// upcoming tournaments
	// String query = "select * from tournament where winnerid is null and startdate > " order by startdate;
    }
}
