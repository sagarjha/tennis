package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

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
	    System.out.println(records);
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
	    System.out.println(records);
	    request.setAttribute("publicNotes",records);
	}

	// set the attribute UpcomingMatches
	{
	    String query = "select P1.Name as player1, P2.Name as player2, Clb.Name as club, M.DateOfMatch, M.SlotNumber, matchtype from Match as M, Player as P1, Player as P2, Club as Clb where (M.Player1ID=" + id + " or M.Player2ID=" + id + ") and P2.ID=M.Player1ID and P1.ID=M.Player2ID and Clb.ID=M.ClubID;";
	    
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
	    System.out.println(matches);
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

	// More Club and More News
	{
	    String query = "select clubid from member where playerid="+id+";";
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
	    String news = "";
            String record_news="";
            int clubid=0;
            int club1id=0;
            int club2id=0;
            int club3id=0;
            String club=null;
            String club1=null;
            String club2=null;
            String club3=null;
            String news=null;
            String news1=null;
            String news2=null;
            String news3=null;
            
	    int count = 1;
            int count2=1;    //for news
            String clubName="";
            String clubNews="";
	    while (rs.next()) {
                clubid=rs.getInt("clubid");
                clubName="select name from club where id="+clubid+";";
                clubNews="select description from news where clubid="+clubid+";";
                ResultSet rs2=stmt2.executeQuery(clubName);
                ResultSet rs3=stmt3.executeQuery(clubNews);
                if(rs2.next())
		    {
			//Club Name Part
			club=rs2.getString("name");
			if(count==1)
			    {
				club1id=rs.getInt("clubid");
				club1=rs2.getString("name");
				request.setAttribute("club1",club1);
			    }
			if(count==2)
			    {
				club2id=rs.getInt("clubid");
				club2=rs2.getString("name");
				request.setAttribute("club2",club2);
			    }
			if(count==3)
			    {
				club3id=rs.getInt("clubid");
				club3=rs2.getString("name");
				request.setAttribute("club3",club3);
			    }
			news += count + ". " + club + "\\n";
			count++;
		    }
                
                //News for each club
                while(rs3.next())
		    {
			news=rs3.getString("description");
			if(count2==1)
			    {
				news1=rs3.getString("description");
				request.setAttribute("news1",news1);
			    }
			if(count2==2)
			    {
				news2=rs3.getString("description");
				request.setAttribute("news2",news2);
			    }
			if(count2==3)
			    {
				news3=rs3.getString("description");
				request.setAttribute("news3",news3);
			    }
			record_news+= count2+". "+ news + "\\n";
			count2++;
		    }
                
	    }
	    System.out.println(news);
            request.setAttribute("morenews",record_news);
	    request.setAttribute("moreclub",news);
	}
    }
}
