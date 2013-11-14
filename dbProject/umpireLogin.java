package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;

class umpireLogin extends HttpServlet{
    public void umpireLoginHandler (int id, HttpServletRequest request, ResultSet rs, Statement stmt, Connection conn) throws SQLException{
        System.out.println("In umpireLogin.java");
        
        int yearOfbirth = rs.getInt("yearOfBirth");
        int umpStartYear = rs.getInt("umpiringstartyear");
        
        // set various attributes required for displaying the correct information on the profile page
	request.setAttribute("name",rs.getString("name"));
	request.setAttribute("id",rs.getString("id"));
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
        String query = "Select name from club where id = "+ rs.getInt("clubid") + ";";
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
        
        
        query = "select P1.Name as player1, P2.Name as player2, M.DateOfMatch as date, M.SlotNumber as slot from Match as M, Player as P1, Player as P2, Competitive as C where C.UmpireID=" + id + " and M.ID=C.ID and P1.ID=M.Player1ID and P2.ID=M.Player2ID and M.status = 'Upcoming';";
        rs = stmt.executeQuery(query);
        String allMatches = "", match1 = null, match2= null, match3 = null, match4 = null, match5 = null;
        int count = 1;
        while(rs.next()){
            allMatches += count + ". " + rs.getString("player1") + " vs " + rs.getString("player2") + " on "
		+ rs.getString("date") + " in slot " + rs.getString("slot") + "\\n";
            if(count==1){
                match1 = rs.getString("player1") + " vs " + rs.getString("player2") + " on "
                    + rs.getString("date") + " in slot " + rs.getString("slot");
            }
            if(count==2){
                match2 = rs.getString("player1") + " vs " + rs.getString("player2") + " on "
                    + rs.getString("date") + " in slot " + rs.getString("slot");
            }
            if(count==3){
                match3 = rs.getString("player1") + " vs " + rs.getString("player2") + " on "
                    + rs.getString("date") + " in slot " + rs.getString("slot");
            }
            if(count==4){
                match4 = rs.getString("player1") + " vs " + rs.getString("player2") + " on "
                    + rs.getString("date") + " in slot " + rs.getString("slot");
            }
            if(count==5){
                match5 = rs.getString("player1") + " vs " + rs.getString("player2") + " on "
                    + rs.getString("date") + " in slot " + rs.getString("slot");
            }
            count++;
        }
        request.setAttribute("match1", match1);
        request.setAttribute("match2", match2);
        request.setAttribute("match3", match3);
        request.setAttribute("match4", match4);
        request.setAttribute("match5", match5);
        request.setAttribute("allMatches", allMatches);
    }
}
