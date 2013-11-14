package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class playerLogin extends HttpServlet{
    public void playerLoginHandler (int id, HttpServletRequest request, ResultSet rs, Statement stmt) throws SQLException{
	System.out.println("In playerLogin.java");
	// set various attributes required for displaying the correct information on the profile page
	request.setAttribute("name",rs.getString("name"));		    
	request.setAttribute("gender",rs.getString("gender"));
	int yearOfbirth=rs.getInt("yearOfBirth");
	request.setAttribute("address", rs.getString("address"));
	request.setAttribute("emailid", rs.getString("emailid"));
	request.setAttribute("phoneno", rs.getString("phonenum"));
	request.setAttribute("description", rs.getString("description"));
	request.setAttribute("numMatchesPlayed",rs.getString("numMatchesPlayed"));
	request.setAttribute("numMatchesWon",rs.getString("numMatchesWon"));
	request.setAttribute("numMatchesLost",rs.getInt("numMatchesPlayed") - rs.getInt("numMatchesWon"));
	request.setAttribute("rating",rs.getString("rating"));
	request.setAttribute("highestRatingAchieved",rs.getString("highestRatingAchieved"));
	String pictureFile = "../webapps/ROOT/profile/images/" + rs.getString("username");
	request.setAttribute("profilePicUrl","profile/images/defaultPic.jpeg");
	File f = new File(pictureFile+".jpeg");
	if (f.exists()) {
	    request.setAttribute("profilePicUrl","profile/images/"+rs.getString("username")+".jpeg");
	}

	f = new File(pictureFile+".jpg");
	if (f.exists()) {
	    request.setAttribute("profilePicUrl","profile/images/"+rs.getString("username")+".jpg");
	}

	f = new File(pictureFile+".png");
	if (f.exists()) {
	    request.setAttribute("profilePicUrl","profile/images/"+rs.getString("username")+".png");
	}
	
	// set the attribute private notes
	{
	    String query = "select * from record where playerid="+id+" and type='Private';";
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
	    String records = "";
	    int count = 1;
	    while (rs.next()) {
		records += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    request.setAttribute("privateNotes",records);
	}

	// set the attribute public notes
	{
	    String query = "select * from record where playerid="+id+" and type='Public';";
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
	    String records = "";
	    int count = 1;
	    if (rs.next()) {
		request.setAttribute("publicNotes1",rs.getString("description"));
		records += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    if (rs.next()) {
		request.setAttribute("publicNotes2",rs.getString("description"));
		records += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    if (rs.next()) {
		request.setAttribute("publicNotes3",rs.getString("description"));
		records += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    while (rs.next()) {
		records += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    request.setAttribute("publicNotes",records);
	}

	// set the attribute UpcomingMatches
	{
	    rs = stmt.executeQuery ("select datevalue from constant where constantname='time'");
	    rs.next();
	    String date = rs.getString("datevalue");
	    
	    String query = "select P1.Name as player1, P2.Name as player2, Clb.Name as club, M.DateOfMatch, M.SlotNumber, matchtype from Match as M, Player as P1, Player as P2, Club as Clb where (M.Player1ID=" + id + " or M.Player2ID=" + id + ") and P2.ID=M.Player1ID and P1.ID=M.Player2ID and Clb.ID=M.ClubID and status = 'Upcoming' and dateofmatch > '" + date + "' order by dateofmatch;";
	    
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
	    String matches = "";
	    int count = 1;
	    if (rs.next()) {
		if (rs.getString("player1").equals(request.getAttribute("name"))) {
		    request.setAttribute("UpcomingMatches1", "vs " + rs.getString("player2") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch"));
		    matches += count + ". vs " + rs.getString("player2") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch") + "\\n";
		    count++;
		}
		else {
		    request.setAttribute("UpcomingMatches1", "vs " + rs.getString("player1") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch"));
		    matches += count + ". vs " + rs.getString("player1") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch") + "\\n";
		    count++;
		}
	    }
	    if (rs.next()) {
		if (rs.getString("player1").equals(request.getAttribute("name"))) {
		    request.setAttribute("UpcomingMatches2", "vs " + rs.getString("player2") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch"));
		    matches += count + ". vs " + rs.getString("player2") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch") + "\\n";
		    count++;
		}
		else {
		    request.setAttribute("UpcomingMatches2", "vs " + rs.getString("player1") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch"));
		    matches += count + ". vs " + rs.getString("player1") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch") + "\\n";
		    count++;
		}
	    }
	    if (rs.next()) {
		if (rs.getString("player1").equals(request.getAttribute("name"))) {
		    request.setAttribute("UpcomingMatches3", "vs " + rs.getString("player2") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch"));
		    matches += count + ". vs " + rs.getString("player2") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch") + "\\n";
		    count++;
		}
		else {
		    request.setAttribute("UpcomingMatches3", "vs " + rs.getString("player1") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch"));
		    matches += count + ". vs " + rs.getString("player1") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch") + "\\n";
		    count++;
		}
	    }
	    while (rs.next()) {
		if (rs.getString("player1").equals(request.getAttribute("name"))) {
		    matches += count + ". vs " + rs.getString("player2") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch") + "\\n";
		    count++;
		}
		else {
		    matches += count + ". vs " + rs.getString("player1") + " at " + rs.getString("club") + " on " + rs.getString("dateofmatch") + "\\n";
		    count++;
		}
	    }
	    request.setAttribute("UpcomingMatches",matches);
	}
        
        //Calculating Age
        {
            String query = "Select extract(year from (select datevalue from constant where constantname = 'time')) as year;";
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                request.setAttribute("age", rs.getInt("year") - yearOfbirth);
            }
        }

	// Club News
	{
	    String query = "select description from member, news where member.playerid="+id+" and member.clubid=news.clubid;";
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
            String news="";
            String news1=null;
            String news2=null;
            String news3=null;

	    int count = 1;
	    if (rs.next()) {
		request.setAttribute("news1",rs.getString("description"));
		news += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    if (rs.next()) {
		request.setAttribute("news2",rs.getString("description"));
		news += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    if (rs.next()) {
		request.setAttribute("news3",rs.getString("description"));
		news += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    while (rs.next()) {
		news += count + ". " + rs.getString("description") + "\\n";
		count++;
	    }
	    request.setAttribute("news",news);
	}

	// Club Names
	{
	    String query = "select name from member, club where member.playerid="+id+" and member.clubid=club.id";
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
            String club="";
            String club1=null;
            String club2=null;
            String club3=null;

	    int count = 1;
	    if (rs.next()) {
		request.setAttribute("club1",rs.getString("name"));
		club += count + ". " + rs.getString("name") + "\\n";
		count++;
	    }
	    if (rs.next()) {
		request.setAttribute("club2",rs.getString("name"));
		club += count + ". " + rs.getString("name") + "\\n";
		count++;
	    }
	    if (rs.next()) {
		request.setAttribute("club3",rs.getString("name"));
		club += count + ". " + rs.getString("name") + "\\n";
		count++;
	    }
	    while (rs.next()) {
		club += count + ". " + rs.getString("name") + "\\n";
		count++;
	    }
	    request.setAttribute("allclubs",club);
	}
        
        //Clubs in which he is not a member
        {
            String query="select name,id from club except select clb.name as name,clb.id as id from member as m, club as clb where m.playerid="+id+" and m.clubid=clb.id;";
            
            List <Integer> clubids = new ArrayList<Integer>();
            List <String> clubnames = new ArrayList<String>();
            rs=stmt.executeQuery(query);
            while(rs.next()){
                clubids.add(rs.getInt("id"));
                clubnames.add(rs.getString("name"));
            }
            request.setAttribute("clubids", clubids);
            request.setAttribute("clubnames", clubnames);
            
            query="select ch.name as name, ch.id as id from coach as ch,teachesat as ta where ta.coachid=ch.id and ta.clubid in (select id from club except select clb.id as id from member as m, club as clb where m.playerid="+id+" and m.clubid=clb.id);";
            List <Integer> coachids = new ArrayList<Integer>();
            List <String> coachnames = new ArrayList<String>();
            rs=stmt.executeQuery(query);
            while(rs.next()){
                coachids.add(rs.getInt("id"));
                coachnames.add(rs.getString("name"));
            }
            request.setAttribute("coachids", coachids);
            request.setAttribute("coachnames", coachnames);
        }
    }
}
