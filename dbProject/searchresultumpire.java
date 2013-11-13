package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class searchresultumpire extends HttpServlet{
    public String searchresultumpireHandler (HttpServletRequest request, Connection conn, HttpSession session) throws SQLException{
        String redirectJsp="/profile/UmpireProfiletoOthers.jsp";
        int other_id=Integer.parseInt(session.getAttribute("searchotherid").toString());
        Statement stmt = conn.createStatement();
        String query="select * from umpire where id="+other_id+";";
        ResultSet rs = stmt.executeQuery (query);
        rs.next();
        // set various attributes required for displaying the correct information on the profile page
        int yearOfbirth = rs.getInt("yearOfBirth");
        int umpStartYear = rs.getInt("umpiringstartyear");
        
	request.setAttribute("name",rs.getString("name"));		    
	request.setAttribute("gender",rs.getString("gender"));
	request.setAttribute("address", rs.getString("address"));
	request.setAttribute("emailid", rs.getString("emailid"));
	request.setAttribute("phoneno", rs.getString("phonenum"));
	request.setAttribute("description", rs.getString("description"));
        request.setAttribute("clubid", rs.getInt("clubid"));
	
	String pictureFile = "../webapps/ROOT/profile/images/" + rs.getString("username");
	System.out.println(pictureFile);
	request.setAttribute("profilePicUrl","profile/images/defaultUmpirePic.jpeg");
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
	
        // get club name
        query = "Select name from club where id = "+ rs.getInt("clubid") + ";";
        rs = stmt.executeQuery(query);
        if(rs.next()){
            request.setAttribute("clubname", rs.getString("name"));
        }
        
        //get current year
        query = "Select extract(year from (select datevalue from constant where constantname = 'time')) as year;";
        rs = stmt.executeQuery(query);
        if(rs.next()){
            request.setAttribute("age", rs.getInt("year") - yearOfbirth);
            request.setAttribute("experience",rs.getInt("year") - umpStartYear);
        }
        return redirectJsp;
    }
}