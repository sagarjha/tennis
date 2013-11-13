package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Accredit extends HttpServlet{
    public void handleAccredition (int id, HttpServletRequest request, Connection conn) throws SQLException{
	System.out.println("In Accredit.java");
	Statement stmt = conn.createStatement();
	String query = "select match.id,dateofmatch,slotnumber,player1.name as player1name, player2.name as player2name from competitive,match,player as player1, player as player2 where player1.id=player1id and player2.id=player2id and competitive.id=match.id and umpireid = " + id + " and status = 'Accreditation Pending';";
	System.out.println(query);
	
	ResultSet rs = stmt.executeQuery(query);
	
	String toBeAccredited = "";

	while (rs.next()) {
	    toBeAccredited += "<tr>\n <td>\n" + rs.getString("player1name") + "\n </td>\n <td>\n " + rs.getString("player2name") + "\n </td>\n <td>\n " + rs.getString("dateofmatch") + "\n </td>\n <td>\n " + rs.getString("slotnumber") + "\n </td>\n <td>\n <select>\n <option value=\"1\">select</option>\n <option value=\"2\">Player1</option>\n <option value=\"3\">Player2</option>\n </select>\n </td>\n </tr>\n";
	}
	request.setAttribute("accreditationRows",toBeAccredited);
    }
}
