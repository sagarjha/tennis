package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class setUpStall extends HttpServlet{
    public void setStall (HttpServletRequest request, Connection conn, HttpSession session) throws SQLException{
        int tournamentid = Integer.parseInt(session.getAttribute("tournamentid").toString());
        int vendorid = Integer.parseInt(session.getAttribute("accountid").toString());
        int discount = Integer.parseInt(request.getParameter("discount").toString());
        
        String query = "";
        Statement stmt = conn.createStatement();
        
        query = "insert into outlet values (" + vendorid + ", " + tournamentid + ", " + discount + ");";
        stmt.executeUpdate(query);
           
    }
}