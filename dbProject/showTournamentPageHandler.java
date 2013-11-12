package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class showTournamentPageHandler extends HttpServlet{
    public void handleShow (Connection conn, ServletRequest request) throws SQLException{
	System.out.println("In showTournamentPageHandler.java");
	Statement stmt = conn.createStatement();
	String tournamentName = request.getParameter ("TOURNAMENTVIEW");
	String name = request.getParameter("TOURNAMENTVIEW");
	System.out.println("tournamentName");
	request.setAttribute("name",name);
	String query = "select * from tournament where name = '" + name + "'";
	System.out.println(query);
	ResultSet rs = stmt.executeQuery(query);
	rs.next();
	request.setAttribute("startdate",rs.getString("startDate"));
	request.setAttribute("numplayers",rs.getString("numplayers"));
	request.setAttribute("prizemoney",rs.getString("prizemoney"));
	Statement stmt1 = conn.createStatement();
	query = "select name from club where id=" + rs.getString("clubid");
	System.out.println(query);
	ResultSet rs1 = stmt.executeQuery(query);
	rs1.next();
	request.setAttribute("club",rs1.getString("name"));
    }
}
