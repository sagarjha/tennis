package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class searchresultvendor extends HttpServlet{
    public String searchresultvendorHandler (HttpServletRequest request, Connection conn, HttpSession session) throws SQLException{
        String redirectJsp="/profile/VendorProfiletoOthers.jsp";
        
        int other_id=Integer.parseInt(session.getAttribute("searchotherid").toString());
        Statement stmt = conn.createStatement();
        String query="select * from vendor where id="+other_id+";";
        ResultSet rs = stmt.executeQuery (query);
        rs.next();
        
        request.setAttribute("name",rs.getString("name"));		    
	request.setAttribute("address", rs.getString("address"));
	request.setAttribute("emailid", rs.getString("emailid"));
	request.setAttribute("phoneno", rs.getString("phonenum"));
	request.setAttribute("description", rs.getString("description"));
	
	String pictureFile = "../webapps/ROOT/profile/images/" + rs.getString("username");
	System.out.println(pictureFile);
	request.setAttribute("profilePicUrl","profile/images/defaultVendorPic.jpeg");
	File f = new File(pictureFile+".jpeg");
	System.out.println(f.exists());
	if (f.exists()) {
	    request.setAttribute("profilePicUrl","profile/images/"+rs.getString("username")+".jpeg");
	}

	f = new File(pictureFile+".jpg");
	System.out.println(f.exists());
	if (f.exists()) {
	    request.setAttribute("profilePicUrl","profile/images/"+rs.getString("username")+".jpg");
	}

	f = new File(pictureFile+".png");
	System.out.println(f.exists());
	if (f.exists()) {
	    request.setAttribute("profilePicUrl","profile/images/"+rs.getString("username")+".png");
	}        
        // get all the items sold by the vendor
        
        query = "select I.Type as type, I.Brand as brand, S.Price as price from Item as I, Sells as S where S.VendorID="+other_id+" and S.ID=I.ID;";
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
        
        //View Stalls
        query="select Clb.name as clubname, T.name as tname from Club as Clb, Outlet as O, Tournament as T where O.vendorid="+other_id+" and T.ID=O.tournamentid and T.clubid=Clb.ID;";
        rs = stmt.executeQuery (query);
        String allStalls="";
        count=1;
        while(rs.next())
        {
            String clubname=rs.getString("clubname");
            String tname=rs.getString("tname");
            allStalls+=count+". Club:"+clubname+"    Tournament:"+tname+"\\n";
            count++;
        }
        request.setAttribute("allStalls",allStalls);
        return redirectJsp;
    }
}