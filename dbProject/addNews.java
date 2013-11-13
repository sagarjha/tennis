package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class addNews extends HttpServlet{
    public String addNewsHandler (HttpServletRequest request,Connection conn, HttpSession session) throws SQLException{
    	
    	String redirectJsp = "/Functionality/newsAdded.jsp";
        int Cid = Integer.parseInt(session.getAttribute("accountid").toString());
            
        Statement stmt = conn.createStatement();
        
        String newsItem = request.getParameter("newsItem");
        
        String getAddId = "select idvalue from constant where constantname= 'news';";
		System.out.println(getAddId);
		ResultSet rs = stmt.executeQuery (getAddId);
		
		int newsId = 0;
		if(rs.next())
		newsId =rs.getInt("idvalue");
		
		
		try{
        
       	    
			
			conn.setAutoCommit(false);
			
			String query="Update Constant  set IDValue = IDValue + 1 where ConstantName = 'news';";
            stmt.executeUpdate(query);	
            System.out.println(query);
            		
			query = "Insert into news values("+newsId+",'"+ newsItem+"','"+ Cid+"');";
			stmt.executeUpdate(query);	
			System.out.println(query);
			
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
      
