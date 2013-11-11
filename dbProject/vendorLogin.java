package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

class vendorLogin extends HttpServlet{
    public void vendorLoginHandler (int id, HttpServletRequest request, ResultSet rs, Statement stmt) throws SQLException{
        // set various attributes required for displaying the correct information on the profile page
	request.setAttribute("name",rs.getString("name"));		    
	request.setAttribute("address", rs.getString("address"));
	request.setAttribute("emailid", rs.getString("emailid"));
	request.setAttribute("phoneno", rs.getString("phonenum"));
	request.setAttribute("description", rs.getString("description"));
        
        // get all the items sold by the vendor
        
        String query = "select I.Type as type, I.Brand as brand, S.Price as price from Item as I, Sells as S where S.VendorID="+id+" and S.ID=I.ID;";
        System.out.println(query);
        rs = stmt.executeQuery (query);
        String allItems = "Items Sold:\\n";
        String type1 = null, type2= null, type3= null, brand1 ="", brand2 = "", brand3 = "";
        int price1 = -1, price2 = -1, price3 = -1, count = 1;
        while(rs.next()){
            allItems += count + "."+ rs.getString("brand") + " " + rs.getString("type") + " for Rs." + rs.getInt("price") + "\\n";
            if(count ==1){
                type1 = rs.getString("type");
                brand1 = rs.getString("brand");
                price1 = rs.getInt("price");
                }
            if(count ==2){
                type2 = rs.getString("type");
                brand2 = rs.getString("brand");
                price2 = rs.getInt("price");
                }
            if(count ==3){
                type3 = rs.getString("type");
                brand3 = rs.getString("brand");
                price3 = rs.getInt("price");
                }
            count++;
            }
        System.out.println(allItems);
        request.setAttribute("allItems", allItems);
        request.setAttribute("type1", type1);
        request.setAttribute("type2", type2);
        request.setAttribute("type3", type3);
        request.setAttribute("brand1", brand1);
        request.setAttribute("brand2", brand2);
        request.setAttribute("brand3", brand3);
        request.setAttribute("price1", price1);
        request.setAttribute("price2", price2);
        request.setAttribute("price3", price3);
        }
    }