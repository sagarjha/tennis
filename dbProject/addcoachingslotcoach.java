package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class addcoachingslotcoach extends HttpServlet{
    public void addcoachslot(HttpServletRequest request, Connection conn, HttpSession session){
        int clubid = Integer.parseInt(request.getParameter("newclub").toString());
        int coachid = Integer.parseInt(session.getAttribute("accountid").toString());
        
        try{
            String query = "Insert into teachesat values(" + coachid + ", " + clubid  +");"; 
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println(query);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}