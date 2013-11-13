package dbProject;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class searchresultplayer extends HttpServlet{
    public String searchresultplayerHandler (HttpServletRequest request, Connection conn, HttpSession session) throws SQLException{
        String redirectJsp="/profile/playerProfiletoOthers.jsp";
        int other_id=Integer.parseInt(session.getAttribute("searchotherid").toString());
        Statement stmt = conn.createStatement();
        
        
        //***************Challenge Player Option**************
        int my_id=Integer.parseInt(session.getAttribute("accountid").toString());
        String query="select * from match where status='Challenge' and (player1id=my_id and player2id=other_id) or (player1id=other_id and player2id=my_id);";
        ResultSet rs=stmt.executeQuery (query);
        if(rs.next())
            request.setAttribute("challengeoption",1);  //Dont show challenge option
        else
        {
            query="select * from player where id=other_id;";
            rs=stmt.executeQuery (query);
            if(rs.next())
            request.setAttribute("challengeoption",0);  //Only if other_id is player show challenge option
        }
        //********************************************************
        
        
        query="select * from player where id="+other_id+";";
        rs = stmt.executeQuery (query);
        int yearOfbirth=0;
        
        if(rs.next())
        {
            request.setAttribute("name",rs.getString("name"));		    
            request.setAttribute("gender",rs.getString("gender"));
            yearOfbirth=rs.getInt("yearOfBirth");
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
            System.out.println(pictureFile);
            request.setAttribute("profilePicUrl","profile/images/defaultPic.jpeg");
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
        }
        
        // set the attribute public notes
	{
	    query = "select * from record where playerid="+other_id+" and type='Public';";
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
	    rs = stmt.executeQuery ("select datevalue from constant where constantname='time'");
	    rs.next();
	    String date = rs.getString("datevalue");
	    
	    query = "select P1.Name as player1, P2.Name as player2, Clb.Name as club, M.DateOfMatch, M.SlotNumber, matchtype from Match as M, Player as P1, Player as P2, Club as Clb where (M.Player1ID=" + other_id + " or M.Player2ID=" + other_id + ") and P2.ID=M.Player1ID and P1.ID=M.Player2ID and Clb.ID=M.ClubID and dateofmatch > '" + date + "' order by dateofmatch;";
	    
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
            query = "Select extract(year from (select datevalue from constant where constantname = 'time')) as year;";
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                request.setAttribute("age", rs.getInt("year") - yearOfbirth);
            }
        }
        
        //Set his clubs
        {
            rs = stmt.executeQuery ("select Clb.Name as Name from Club as Clb, Member as M where M.PlayerID="+other_id+" and Clb.ID=M.ClubID;");
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
        
        
        
        return redirectJsp;
    }
}