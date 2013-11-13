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
        
        String query = "Select id as pId from items where lower(brand) like lower('"+brandName+"') and type like lower('"+ pType +"') ;";
        
        Statement stmt = conn.createStatement();
        System.out.println(query);
	    ResultSet rs = stmt.executeQuery (query);
	    
	    int id = 0;
	    if(rs.next()) id = rs.getInt("pId");
	    else {
	    	
	    	redirectJsp = "/Functionality/productDoesNotExist.jsp";
        	return redirectJsp;
        	
        }
        
        String query = "Select * from sells where pId = "+id+" and vendorid = " + Vid + ";";
        System.out.println(query);
	    rs = stmt.executeQuery (query);
	    
	    if(rs.next()) id = id + 0;
	    else {
	    	
	    	redirectJsp = "/Functionality/productDoesNotExist.jsp";
        	return redirectJsp;
        	
        }
        
        String query = "Update Sells set Price = "+newPrice+" where VendorID = "+Vid+" and itemID ="+pID +";";

        System.out.println(query);
	    stmt.executeUpdate(query);
	    
	    return redirectJSP;
	    
	    }
	   }
	    
	    
        
        
