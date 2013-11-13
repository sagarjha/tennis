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
     
	{
	    query = "select match.id,dateofmatch,slotnumber,player1id,player2id from competitive,match where competitive.id=match.id and umpireid = " + request.getAttribute("id") + " and status = 'Accreditation Pending';";
	    System.out.println(query);
	    rs = stmt.executeQuery(query);
	    String accreditMatch1="";
	    String accreditMatch2="";
	    String accreditMatch3="";
	    
	    if(rs.next()) {
		Statement stmt1 = conn.createStatement();
		String innerQuery = "select name from player where id = " + rs.getString("player1id");
		System.out.println(innerQuery);
		ResultSet rs1 = stmt1.executeQuery(innerQuery);
		rs1.next();
		String name1 = rs1.getString("name");
		innerQuery = "select name from player where id = " + rs.getString("player2id");
		System.out.println(innerQuery);
		rs1 = stmt1.executeQuery(innerQuery);
		rs1.next();
		accreditMatch1 = "name1 - " + rs1.getString("name2") + "\t" + rs.getString("dateofmatch") + "\t<input type=\"Submit\" style=\"color: blue; background-color: grey\" name=\"Accredit\" value = " + rs.getString("id") +"> Accredit"; 
	    }
	    if(rs.next()) {
		Statement stmt1 = conn.createStatement();
		String innerQuery = "select name from player where id = " + rs.getString("player1id");
		System.out.println(innerQuery);
		ResultSet rs1 = stmt1.executeQuery(innerQuery);
		String name1 = rs1.getString("name");
		innerQuery = "select name from player where id = " + rs.getString("player2id");
		System.out.println(innerQuery);
		rs1 = stmt1.executeQuery(innerQuery);
		accreditMatch2 = "name1 - " + rs1.getString("name2") + "\t" + rs.getString("dateofmatch") + "\t<input type=\"Submit\" style=\"color: blue; background-color: grey\" name=\"Accredit\" value = " + rs.getString("id") +"> Accredit"; 
	    }
	    if(rs.next()) {
		Statement stmt1 = conn.createStatement();
		String innerQuery = "select name from player where id = " + rs.getString("player1id");
		System.out.println(innerQuery);
		ResultSet rs1 = stmt1.executeQuery(innerQuery);
		String name1 = rs1.getString("name");
		innerQuery = "select name from player where id = " + rs.getString("player2id");
		System.out.println(innerQuery);
		rs1 = stmt1.executeQuery(innerQuery);
		accreditMatch3 = "name1 - " + rs1.getString("name2") + "\t" + rs.getString("dateofmatch") + "\t<input type=\"Submit\" style=\"color: blue; background-color: grey\" name=\"Accredit\" value = " + rs.getString("id") +"> Accredit"; 
	    }
	}
           
    }
}
