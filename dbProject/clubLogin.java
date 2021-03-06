package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;

class clubLogin extends HttpServlet{
    public void clubLoginHandler (int id, HttpServletRequest request, ResultSet rs, Statement stmt) throws SQLException{
	// set various attributes required for displaying the correct information on the profile page
	request.setAttribute("name",rs.getString("name"));		    

	request.setAttribute("address", rs.getString("address"));
	request.setAttribute("emailid", rs.getString("emailid"));
	request.setAttribute("phoneno", rs.getString("phonenum"));
	request.setAttribute("description", rs.getString("description"));
	request.setAttribute("numcourts",rs.getString("numcourts"));
	request.setAttribute("coachingslot",rs.getString("coachingslot"));
	String pictureFile = "../webapps/ROOT/profile/images/" + rs.getString("username");
	request.setAttribute("profilePicUrl","profile/images/defaultClubPic.jpg");
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

	{
	    {
		String query = "select * from news where clubid="+id+" ;";
		System.out.println(query);
		rs = stmt.executeQuery (query);
		String news = "";
		String news1 = "";
		String news2 = "";
		String news3 = "";
		int count = 1; 

		if(rs.next()) {
		    news += count + ". " + rs.getString("description") + "\\n";
		    news1 = rs.getString("description");
		    count++;
		}

		if(rs.next()) {
		    news += count + ". " + rs.getString("description") + "\\n";
		    news2 = rs.getString("description");
		    count++;
		}

		if(rs.next()) {
		    news += count + ". " + rs.getString("description") + "\\n";
		    news3 = rs.getString("description");
		    count++;
		}

		while (rs.next()) {
		    news += count + ". " + rs.getString("description") + "\\n";
		    count++;
		}

		request.setAttribute("news",news);
		request.setAttribute("news1",news1);
		request.setAttribute("news2",news2);
		request.setAttribute("news3",news3);
	    }

	    {
		String query = "select P.name from Player as P, Member as M where M.ClubID="+id+" and M.PlayerID=P.ID order by P.Name;";
		System.out.println(query);
		rs = stmt.executeQuery (query);
		String player = "";
		String player1 = "";
		String player2 = "";
		String player3 = "";
		int count = 1; 

		if(rs.next()) {
		    player += count + ". " + rs.getString("name") + "\\n";
		    player1 = rs.getString("name");
		    count++;
		}

		if(rs.next()) {
		    player += count + ". " + rs.getString("name") + "\\n";
		    player2 = rs.getString("name");
		    count++;
		}

		if(rs.next()) {
		    player += count + ". " + rs.getString("name") + "\\n";
		    player3 = rs.getString("name");
		    count++;
		}

		while (rs.next()) {
		    player += count + ". " + rs.getString("name") + "\\n";
		    count++;
		}

		request.setAttribute("player",player);
		request.setAttribute("player1",player1);
		request.setAttribute("player2",player2);
		request.setAttribute("player3",player3);
	    }
	    {

		{
		    String query = "select Ch.Name from Coach as Ch, TeachesAt as Ta where Ta.ClubID="+id+" and Ta.CoachID=Ch.ID";

		    System.out.println(query);
		    rs = stmt.executeQuery (query);
		    String coach = "";
		    String coach1 = "";
		    String coach2 = "";
		    String coach3 = "";
		    int count = 1; 

		    if(rs.next()) {
			coach += count + ". " + rs.getString("name") + "\\n";
			coach1 = rs.getString("name");
			count++;
		    }

		    if(rs.next()) {
			coach += count + ". " + rs.getString("name") + "\\n";
			coach2 = rs.getString("name");
			count++;
		    }

		    if(rs.next()) {
			coach += count + ". " + rs.getString("name") + "\\n";
			coach3 = rs.getString("name");
			count++;
		    }

		    while (rs.next()) {
			coach += count + ". " + rs.getString("name") + "\\n";
			count++;
		    }

		    request.setAttribute("coach",coach);
		    request.setAttribute("coach1",coach1);
		    request.setAttribute("coach2",coach2);
		    request.setAttribute("coach3",coach3);
		}
		
		{
		    String query = "select U.Name from Umpire as U where U.ClubID="+id+";";

		    System.out.println(query);
		    rs = stmt.executeQuery (query);
		    String umpire = "";
		    String umpire1 = "";
		    String umpire2 = "";
		    String umpire3 = "";
		    int count = 1; 

		    if(rs.next()) {
			umpire += count + ". " + rs.getString("name") + "\\n";
			umpire1 = rs.getString("name");
			count++;
		    }

		    if(rs.next()) {
			umpire += count + ". " + rs.getString("name") + "\\n";
			umpire2 = rs.getString("name");
			count++;
		    }

		    if(rs.next()) {
			umpire += count + ". " + rs.getString("name") + "\\n";
			umpire3 = rs.getString("name");
			count++;
		    }

		    while (rs.next()) {
			umpire += count + ". " + rs.getString("name") + "\\n";
			count++;
		    }

		    request.setAttribute("umpire",umpire);
		    request.setAttribute("umpire1",umpire1);
		    request.setAttribute("umpire2",umpire2);
		    request.setAttribute("umpire3",umpire3);


		}
	    }
	}
	// set the attribute UpcomingMatches
	{
	    rs = stmt.executeQuery ("select datevalue from constant where constantname='time'");
	    rs.next();
	    String date = rs.getString("datevalue");
	    
	    String query = "select P1.Name as player1, P2.Name as player2, M.DateOfMatch, M.SlotNumber, matchtype from Match as M, Player as P1, Player as P2, Club as Clb where Clb.id=" + id + " and P2.ID=M.Player1ID and P1.ID=M.Player2ID and Clb.ID=M.ClubID and dateofmatch > '" + date + "' order by dateofmatch;";
	    
	    System.out.println(query);
	    rs = stmt.executeQuery (query);
	    String matches = "";
	    int count = 1;
	    if (rs.next()) {
		request.setAttribute("UpcomingMatches1", rs.getString("player1") + " vs " + rs.getString("player2") + " on " + rs.getString("dateofmatch"));
		matches += count + ". " + rs.getString("player1") +" vs " + rs.getString("player2") + " on " + rs.getString("dateofmatch") + "\\n";
		count++;
	    }
	    if (rs.next()) {
		request.setAttribute("UpcomingMatches2", rs.getString("player1") + " vs " + rs.getString("player2") + " on " + rs.getString("dateofmatch"));
		matches += count + ". " + rs.getString("player1") + " vs " + rs.getString("player2") + " on " + rs.getString("dateofmatch") + "\\n";
		count++;
	    }
	    if (rs.next()) {
		request.setAttribute("UpcomingMatches3", rs.getString("player1") + " vs " + rs.getString("player2") + " on " + rs.getString("dateofmatch"));
		matches += count + ". " + rs.getString("player1") + " vs " + rs.getString("player2") + " on " + rs.getString("dateofmatch") + "\\n";
		count++;
	    }
	    while (rs.next()) {
		if (rs.getString("player1").equals(request.getAttribute("name"))) {
		    matches += count + ". " + rs.getString("player1") +" vs " + rs.getString("player2") + " on " + rs.getString("dateofmatch") + "\\n";
		    count++;
		}
		else {
		    matches += count + ". " + rs.getString("player1") + " vs " + rs.getString("player2") + " on " + rs.getString("dateofmatch") + "\\n";
		    count++;
		}
	    }
	    request.setAttribute("UpcomingMatches",matches);
	}
        
    }
}
	
