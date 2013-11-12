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
	System.out.println("tournamentName");
    }
}
