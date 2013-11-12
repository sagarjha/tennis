package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class umpiresignup extends HttpServlet{
    public String umpiresignupHandler (HttpServletRequest request, HttpSession session, Connection conn) throws SQLException{
        
        String query="";
        String redirectJsp="";
        
        int new_id=0;
        String experience=request.getParameter("experience");
        String description=request.getParameter("description");
        try
        {
            session.setAttribute("experience",experience);
            session.setAttribute("description",description);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        try{
            Statement stmt = conn.createStatement();
            query="Select IDValue from Constant where ConstantName = 'account';";
            ResultSet rs = stmt.executeQuery (query);
            if(rs.next())
            {    
                new_id=rs.getInt("idvalue");
                System.out.println(new_id);
            }
            conn.setAutoCommit(false);
            query="Update Constant set IDValue = IDValue+1 where ConstantName = 'account';";
            stmt.executeUpdate(query);
            int yearofbirth=0;
            int umpiringstartyear=0;
            query="Insert into umpire(ID,Name, Username, Password,  Address, Description,PhoneNum,EmailID, YearOfBirth, Gender, umpiringstartyear, clubid) values ("+new_id+", '"+session.getAttribute("name")+"', '"+session.getAttribute("username")+"', '"+session.getAttribute("password")+"','" +session.getAttribute("address")+"', '"+session.getAttribute("description")+"', '"+session.getAttribute("phoneno")+"', '"+session.getAttribute("emailaddress")+"', "+yearofbirth+", '"+session.getAttribute("gender")+"', "+umpiringstartyear+","+session.getAttribute("clubid")+");";
            stmt.executeUpdate(query);
            conn.commit();
        }
        catch(SQLException e)
        {
            System.out.println(e);
            if (conn != null) 
            {
                try 
                {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } 
                catch(SQLException excep) 
                {
                    System.out.println(excep);
                }
            }
        }
        finally
        {
            try
            {
                conn.setAutoCommit(true);
            }
            catch(SQLException excep2)
            {
                System.out.println(excep2);
            }
        }
        return redirectJsp;
    }
}