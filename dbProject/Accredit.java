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

	int count = 0;
	
	while (rs.next()) {
	    count++;
	    toBeAccredited += "<tr>\n <td>\n" + rs.getString("player1name") + "\n </td>\n <td>\n " + rs.getString("player2name") + "\n </td>\n <td>\n " + rs.getString("dateofmatch") + "\n </td>\n <td>\n " + rs.getString("slotnumber") + "\n </td>\n <td>\n <select id = \"" + count + "\" name = \"match" + count + "\">\n <option value=\"" + rs.getString("id") + ":1" + "\">select</option>\n <option value=\"" + rs.getString("id") + ":2" + "\">Player1</option>\n <option value=\"" + rs.getString("id") + ":3" + "\">Player2</option>\n </select>\n </td>\n </tr>\n";
	}
	request.setAttribute("accreditationRows",toBeAccredited);
    }
    
    public void handleAccreditionOfMatches (int id, HttpServletRequest request, Connection conn) throws SQLException{
	System.out.println("In handleAccreditionOfMatches of Accredit.java");
	
	int count = 1;

	while (request.getParameter("match" + count) != null) {
	    String[] value = request.getParameter("match" + count).toString().split(":");
	    count++;
	    int matchid = Integer.parseInt(value[0]);
	    if (value[1].equals("2")) {
		
	    }
	    else if (value[1].equals("3")) {
		
	    }
	}

    }
}
