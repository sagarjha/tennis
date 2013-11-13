package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Accredit extends HttpServlet{
    public void handleAccredition (int id, HttpServletRequest request) throws SQLException{
	System.out.println("In Accredit.java");

	String query = "select match.id,dateofmatch,slotnumber,player1id,player2id from competitive,match where competitive.id=match.id and umpireid = " + id + " and status = 'Accreditation Pending';";
	System.out.println(query);
    }
}
