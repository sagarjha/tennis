package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class addclub extends HttpServlet{
    public String addclubHandler (HttpServletRequest request, Connection conn, HttpSession session) throws SQLException{
        System.out.println("Entered Addclub\n");
        String redirectJsp="/signupAndLogin/operationsuccessful.jsp";
        int pID=Integer.parseInt(session.getAttribute("accountid").toString());
        int cID=Integer.parseInt(session.getAttribute("searchotherid").toString());
        String query="Insert into Member(playerid, clubid) values("+pID+", "+cID+");";
        try
        {
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(query);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        System.out.println("Left Addclub\n");
        return redirectJsp;
        
    }
}