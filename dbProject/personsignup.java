package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class personsignup extends HttpServlet{
    public String personsignupHandler (HttpServletRequest request, HttpSession session, Connection conn) throws SQLException{
        
        String query="";
        int flag=0;
        
        String personsignup="/signupAndLogin/personsignup.jsp";
        String playersignup="/signupAndLogin/playersignup.jsp";
        String coachsignup="/signupAndLogin/coachsignup.jsp";
        String umpiresignup="/signupAndLogin/umpiresignup.jsp";
        
        
        
       
        
        
        String name=request.getParameter("name");
        String age=request.getParameter("age");
        String gender=request.getParameter("gender");
        String address=request.getParameter("address");
        String emailaddress=request.getParameter("emailaddress");
        String phoneno=request.getParameter("phoneno");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String clubid=request.getParameter("clubid");

        try{
            
            Statement stmt = conn.createStatement();
            flag=0;
            //if((session.getAttribute("type").equals("1")))
            query="select username from player where username='"+username+"';";
            ResultSet rs = stmt.executeQuery (query);
            if(rs.next())
            {
                flag=1;
            }
            //else if((session.getAttribute("type").equals("2")))
            query="select username from coach where username='"+username+"';";
            rs = stmt.executeQuery (query);
            if(rs.next())
            {
                flag=1;
            }
            //else if((session.getAttribute("type").equals("3")))
            query="select username from Umpire where username='"+username+"';";
            rs = stmt.executeQuery (query);
            if(rs.next())
            {
                flag=1;
            }
            if(flag==0)
            {
                session.setAttribute("name",name);
                session.setAttribute("age",age);
                session.setAttribute("gender",gender);
                session.setAttribute("address",address);
                session.setAttribute("emailaddress",emailaddress);
                session.setAttribute("phoneno",phoneno);
                //phoneno can be empty string so we need to check for both empty and null when converting into int
                session.setAttribute("username",username);
                session.setAttribute("password",password);
                session.setAttribute("clubid",clubid);

                //ServletContext context = getServletContext();
                if(session.getAttribute("type").equals("1"))
                {
                    /*RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/playersignup.jsp");
                    dispatcher.forward(request, response);*/
                    return playersignup;
                }
                else if(session.getAttribute("type").equals("2"))
                {
                    /*RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/coachsignup.jsp");
                    dispatcher.forward(request, response);*/
                    return coachsignup;
                }
                else 
                    //if(session.getAttribute("type").equals("3"))
                {
                    /*RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/umpiresignup.jsp");
                    dispatcher.forward(request, response);*/
                    return umpiresignup;
                }
            }
            else
            {

                session.setAttribute("usernamealreadyexists",1);
                //ServletContext context = getServletContext();

                /*RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/personsignup.jsp");
                dispatcher.forward(request, response);*/
                return personsignup;

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
        return null;
    }    
}
