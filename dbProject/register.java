package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class register extends HttpServlet{
    public String registerHandler (HttpServletRequest request, HttpSession session) throws SQLException{
        String role=request.getParameter("role");
            
        session.setAttribute("type",role);
        session.setAttribute("usernamealreadyexists",0);
        
        String personsignup="/signupAndLogin/personsignup.jsp";
        String clubsignup="/signupAndLogin/clubsignup.jsp";
        String vendorsignup="/signupAndLogin/vendorsignup.jsp";
        
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