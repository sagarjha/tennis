package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class clubsignup extends HttpServlet{
    public String clubsignupHandler (HttpServletRequest request, HttpSession session, Connection conn) throws SQLException{
        
        String query="";
        int flag=0;
        
        String clubsignup="/signupAndLogin/clubsignup.jsp";
        String clubsignup2="/signupAndLogin/clubsignup2.jsp";
        
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        String emailaddress=request.getParameter("emailaddress");
        String phoneno=request.getParameter("phoneno");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        int courtnum=Integer.parseInt(request.getParameter("courtnum"));
        int coachingslot=Integer.parseInt(request.getParameter("coachingslot"));

        //**********************************************

        try{
            Statement stmt = conn.createStatement();
            query="select username from club where username='"+username+"';";
            ResultSet rs = stmt.executeQuery (query);

            flag=0;
            if(rs.next())
            {
                flag=1;
            }
            if(flag==0)
            {
                session.setAttribute("name",name);
                session.setAttribute("address",address);
                session.setAttribute("emailaddress",emailaddress);
                session.setAttribute("phoneno",phoneno);
                session.setAttribute("username",username);
                session.setAttribute("password",password);
                session.setAttribute("courtnum",courtnum);
                session.setAttribute("coachingslot",coachingslot);



                /*ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/clubsignup2.jsp");
                dispatcher.forward(request, response);*/
                return clubsignup2;
            }
            else
            {

                session.setAttribute("usernamealreadyexists",1);
                /*ServletContext context = getServletContext();

                RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/clubsignup.jsp");
                dispatcher.forward(request, response);*/
                return clubsignup;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
}