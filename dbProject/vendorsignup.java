package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class vendorsignup extends HttpServlet{
    public String vendorsignupHandler (HttpServletRequest request, HttpSession session, Connection conn) throws SQLException{

        int flag=0;
        String query="";
        
        String vendorsignup="/signupAndLogin/vendorsignup.jsp";
        String vendorsignup2="/signupAndLogin/vendorsignup2.jsp";
        
        
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        String emailaddress=request.getParameter("emailaddress");
        String phoneno=request.getParameter("phoneno");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //**********************************************

        try{
            Statement stmt = conn.createStatement();
            query="select username from vendor where username='"+username+"';";
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
                //phoneno can be empty string so we need to check for both empty and null when converting into int
                session.setAttribute("username",username);
                session.setAttribute("password",password);



                /*ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/vendorsignup2.jsp");
                dispatcher.forward(request, response);*/
                return vendorsignup2;
            }
            else
            {

                session.setAttribute("usernamealreadyexists",1);
                /*ServletContext context = getServletContext();

                RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/vendorsignup.jsp");
                dispatcher.forward(request, response);*/
                return vendorsignup;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
        
    }    
}