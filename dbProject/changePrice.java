package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class changePrice extends HttpServlet{
    public String changePriceHandler (HttpServletRequest request,Connection conn, HttpSession session) throws SQLException{
    	
    	String redirectJsp = "/Functionality/priceChanged.jsp";
        int Vid = Integer.parseInt(session.getAttribute("accountid").toString());
        
        String pType = request.getParameter("pType");
        String brandName = request.getParameter("brandName");
        String price = request.getParameter("price");
        
        String query = "Select id as pId from item where lower(brand) like lower('"+brandName+"') and lower(type) like lower('"+ pType +"') ;";
        
        Statement stmt = conn.createStatement();
        System.out.println(query);
	    ResultSet rs = stmt.executeQuery (query);
	    
	    int id = 0;
	    if(rs.next()) id = rs.getInt("pId");
	    else {
	    	
	    	redirectJsp = "/Functionality/productDoesNotExist.jsp";
        	return redirectJsp;
        	
        }
        
        query = "Select * from sells where id = "+id+" and vendorid = " + Vid + ";";
        System.out.println(query);
	    rs = stmt.executeQuery (query);
	    
	    if(rs.next()) id = id + 0;
	    else {
	    	
	    	redirectJsp = "/Functionality/productDoesNotExist.jsp";
        	return redirectJsp;
        	
        }
        
        query = "Update Sells set Price = "+price+" where VendorID = "+Vid+" and id ="+id +";";

        System.out.println(query);
	    stmt.executeUpdate(query);
	    
	    return redirectJsp;
	    
	    }
	   }
	    
	    
        
        
