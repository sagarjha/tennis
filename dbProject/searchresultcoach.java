package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class searchresultcoach extends HttpServlet{
    public String searchresultcoachHandler (HttpServletRequest request, Connection conn, HttpSession session) throws SQLException{
        String redirectJsp="/profile/CoachProfiletoOthers.jsp";
        int other_id=Integer.parseInt(session.getAttribute("searchotherid").toString());
        Statement stmt = conn.createStatement();
        String query="select * from coach where id="+other_id+";";
        ResultSet rs = stmt.executeQuery (query);
        
        
        //***************************************************************************************
        rs.next();
        int coachingStartYear = rs.getInt("coachingstartyear");
        int yearOfbirth = rs.getInt("yearOfBirth");
        request.setAttribute("name",rs.getString("name"));		    
	request.setAttribute("gender",rs.getString("gender"));
//	request.setAttribute("age",2013 - rs.getInt("yearOfBirth"));
	request.setAttribute("address", rs.getString("address"));
	request.setAttribute("emailid", rs.getString("emailid"));
	request.setAttribute("phoneno", rs.getString("phonenum"));
	request.setAttribute("description", rs.getString("description"));

	request.setAttribute("rating",rs.getString("rating"));

	String pictureFile = "../webapps/ROOT/profile/images/" + rs.getString("username");
	System.out.println(pictureFile);
	request.setAttribute("profilePicUrl","profile/images/defaultCoachPic.jpeg");
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
        
        
        //Calculating Age and Experience
        {
            query = "Select extract(year from (select datevalue from constant where constantname = 'time')) as year;";
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                request.setAttribute("age", rs.getInt("year") - yearOfbirth);
                request.setAttribute("experience",rs.getInt("year") - coachingStartYear);
            }
        }
        
        //***************************************************************************************
        
        //Set his clubs
        {
            rs = stmt.executeQuery ("select Clb.Name as Name from Club as Clb, teachesat as ta where ta.coachID="+other_id+" and Clb.ID=ta.ClubID;");
	    String records="";
            int count=1;
            
            
            if (rs.next()) {
		request.setAttribute("club1",rs.getString("Name"));
		records += count + ". " + rs.getString("Name") + "\\n";
		count++;
	    }
	    if (rs.next()) {
		request.setAttribute("club2",rs.getString("Name"));
		records += count + ". " + rs.getString("Name") + "\\n";
		count++;
	    }
	    if (rs.next()) {
		request.setAttribute("club3",rs.getString("Name"));
		records += count + ". " + rs.getString("Name") + "\\n";
		count++;
	    }
            
            while (rs.next()) {
		records += count + ". " + rs.getString("Name") + "\\n";
		count++;
	    }
            request.setAttribute("moreClubs", records);
        }
        
        
        //*************************************************************************************
        
        // set whether to display rate this coach or not
        {
        
        query = "select * from training where playerid = " + Integer.parseInt(session.getAttribute("accountid").toString())
                + " and coachid = "  + other_id + ";";
        rs = stmt.executeQuery(query);
        System.out.println(query);
        if(rs.next()){
            session.setAttribute("playerTaughtByCoach", 1);
        }
        else {
            session.setAttribute("playerTaughtByCoach", 0);
        }
            
        }
        
        	
        return redirectJsp;
    }
}
