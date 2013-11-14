package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class addcoachingslotcoach extends HttpServlet{
    public String addcoachslot(HttpServletRequest request, Connection conn, HttpSession session){
        int clubid = 0;
        int coachid = 0;
        String redirectJsp="";        
        if((request.getParameter("newclub").equals("")))
        {
            redirectJsp="/signupAndLogin/operationnotsuccessful.jsp";
        }
        else
        {
            clubid = Integer.parseInt(request.getParameter("newclub").toString());
            coachid = Integer.parseInt(session.getAttribute("accountid").toString());
            redirectJsp="/signupAndLogin/operationsuccessful.jsp";
        
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
        return redirectJsp;
    }
}