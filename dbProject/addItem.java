package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class addItem extends HttpServlet{
    public String itemAdditionHandler (HttpServletRequest request,Connection conn, HttpSession session) throws SQLException{
    	
    	String redirectJsp = "/Functionality/itemadded.jsp";
        int Vid = Integer.parseInt(session.getAttribute("accountid").toString());
            
        
        
        String pType = request.getParameter("pType");
        String brandName = request.getParameter("brandName");
        String price = request.getParameter("price");
        
        String query = "select * from item where lower(brand) like lower('"+brandName+"') and lower(type) like lower('"+pType+"');";
        int option;
        
        Statement stmt = conn.createStatement();
        System.out.println(query);
	    ResultSet rs = stmt.executeQuery (query);
	    
	    if(rs.next()) option = 1;
	    else option = 0;
	    
	    if(option == 0)	{
			
			String getAddId = "select idvalue from constant where constantname= 'item';";
			System.out.println(getAddId);
			rs = stmt.executeQuery (getAddId);
			
			int id = 1;
			if(rs.next())
			id = rs.getInt("idvalue");
			
		try{
        
       	    
			
			conn.setAutoCommit(false);
			
			query="Update Constant  set IDValue = IDValue + 1 where ConstantName = 'item';";
            stmt.executeUpdate(query);	
            System.out.println(query);
            		
			query = "Insert into Item values("+id+",'"+ brandName+"','"+ pType+"');";
			stmt.executeUpdate(query);	
			System.out.println(query);
			
			query = "Insert into Sells values ("+id+","+ Vid+","+ price+");";
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
    
    
    else	{
    	
    	query = "select * from item where lower(brand) like lower('"+brandName+"') and lower(type) like lower('"+pType+"');";
        System.out.println(query);
	    rs = stmt.executeQuery (query);
	    
            int id = 0;
        if(rs.next())
	    id = rs.getInt("id");
    	
    	
    	String doesItemExist = "Select * from Sells where vendorId = "+ Vid +" and id=" + id + ";";
    	System.out.println(doesItemExist);
		rs = stmt.executeQuery (doesItemExist);
		
		if(rs.next()) {
			
			redirectJsp = "/Functionality/itemadded.jsp";
			System.out.println("Item existes:");
			return redirectJsp;
			
		}
		
		query = "Insert into Sells values ("+id+","+ Vid+","+ price+");";
		System.out.println(query);
		stmt.executeUpdate(query);
		
		return redirectJsp;
		
	}
	
	
	}
}	
		

