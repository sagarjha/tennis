package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class addprivaterecord extends HttpServlet{
    public String addprivaterecordHandler (HttpServletRequest request, HttpSession session, Connection conn) throws SQLException{
        System.out.println("Entered Add Private Record");
        String redirectJsp="/signupAndLogin/operationsuccessful.jsp";
        String query="";
        String description=request.getParameter("description");
        int new_id=0;
        int my_id=Integer.parseInt(session.getAttribute("accountid").toString());
        
        try
        {
            Statement stmt = conn.createStatement();
            query="select idvalue from constant where constantname='record';";
            ResultSet rs = stmt.executeQuery (query);
            if(rs.next())
            {
                new_id=rs.getInt("idvalue");
            }
            conn.setAutoCommit(false);
            query="Insert into record values("+new_id+", 'Private', '"+description+"', "+my_id+");";
            stmt.executeUpdate(query);
            query="Update constant set idvalue=idvalue+1 where constantname='record';";
            stmt.executeUpdate(query);
            conn.commit();
            
        }
        catch(Exception e)
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