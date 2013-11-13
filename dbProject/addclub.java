package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class addclub extends HttpServlet{
    public String addclubHandler (HttpServletRequest request, Connection conn, HttpSession session) throws SQLException{
        String redirectJsp="/signupAndLogin/operationsuccessful.jsp";
        int pID=Integer.parseInt(session.getAttribute("accountid").toString());
        int cID=Integer.parseInt(session.getAttribute("other_id").toString());
        String query="Insert into Member values("+pID+", "+cID+");";
        try
        {
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(query);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return redirectJsp;
    }
}