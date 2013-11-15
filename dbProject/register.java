package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class register extends HttpServlet{
    public String registerHandler (HttpServletRequest request, HttpSession session, Connection conn) throws SQLException{
        String role=request.getParameter("role");
            
        session.setAttribute("type",role);
        session.setAttribute("usernamealreadyexists",0);
        
        String personsignup="/signupAndLogin/personsignup.jsp";
        String clubsignup="/signupAndLogin/clubsignup.jsp";
        String vendorsignup="/signupAndLogin/vendorsignup.jsp";
        
        Statement stmt = conn.createStatement();
        String query="select club.id as id,club.name as name from club;";
            
            List <Integer> clubids = new ArrayList<Integer>();
            List <String> clubnames = new ArrayList<String>();
            ResultSet rs =stmt.executeQuery(query);
            while(rs.next()){
                clubids.add(rs.getInt("id"));
                clubnames.add(rs.getString("name"));
            }
            request.setAttribute("clubids", clubids);
            request.setAttribute("clubnames", clubnames);
        
        if((session.getAttribute("type").equals("1"))|
           (session.getAttribute("type").equals("2"))|
           (session.getAttribute("type").equals("3")))
            {   
                return personsignup;
            }
        else if(session.getAttribute("type").equals("4"))
            {
                return clubsignup;
            }
        else
            {
                return vendorsignup;
            }
        
        
    }
}
