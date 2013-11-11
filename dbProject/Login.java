package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Login extends HttpServlet{
    public Login () {
	
    }
    public String handleLogin (HttpServletRequest request, String username, String password, Connection conn, String type) {
	String vendorProfileJsp = "/profile/VendorprofileToHimself.jsp";
	String clubProfileJsp = "/profile/ClubProfiletoItself.jsp";
	String coachProfileJsp = "/profile/CoachProfiletohimeslf.jsp";
	String umpireProfileJsp = "/profile/UmpireprofiletoHimself.jsp";
	String playerProfileJsp = "/profile/playerProfiletoHimself.jsp";

	// Get the username from the request
	username = request.getParameter("username");
	// Get the password from the request
	password = request.getParameter("password");
	
	// Query the database to check the authenticity of the login
	try {
	    Statement stmt = conn.createStatement();
	    String loginQuery = "";

	    //Check if the user is a valid vendor
	    loginQuery= "select id from vendor where username = '" + username + "' and password = '" + password + "'";
	    System.out.println(loginQuery);
	    ResultSet rs = stmt.executeQuery (loginQuery);
	    if (rs.next()) {
		//login successful, direct to the vendor profile
		System.out.println ("A vendor has logged in!");
		type="vendor";
		return vendorProfileJsp;
	    }

	    //Check if the user is a valid club
	    loginQuery= "select id from club where username = '" + username + "' and password = '" + password + "'";
	    System.out.println(loginQuery);
	    rs = stmt.executeQuery (loginQuery);
	    if (rs.next()) {
		//login successful, direct to the club profile
		System.out.println ("A club has logged in!");
		type="club";
		return clubProfileJsp;
	    }

	    //Check if the user is a valid coach
	    loginQuery= "select id from coach where username = '" + username + "' and password = '" + password + "'";
	    System.out.println(loginQuery);
	    rs = stmt.executeQuery (loginQuery);
	    if (rs.next()) {
		//login successful, direct to the coach profile
		System.out.println ("A coach has logged in!");
		type="coach";
		return coachProfileJsp;
	    }

	    //Check if the user is a valid umpire
	    loginQuery= "select id from umpire where username = '" + username + "' and password = '" + password + "'";
	    System.out.println(loginQuery);
	    rs = stmt.executeQuery (loginQuery);
	    if (rs.next()) {
		//login successful, direct to the umpire profile
		System.out.println ("A umpire has logged in!");
		type="umpire";
		return umpireProfileJsp;
	    }

	    //Check if the user is a valid player
	    loginQuery= "select * from player where username = '" + username + "' and password = '" + password + "'";
	    System.out.println(loginQuery);
	    rs = stmt.executeQuery (loginQuery);
	    if (rs.next()) {
		// login successful, direct to the player profile
		System.out.println ("A player has logged in!");
		type="player";

		// store the id of the player for future queries
		int id = rs.getInt("id");

		playerLogin pL = new playerLogin ();
		pL.playerLoginHandler (id, request, rs, stmt);
		// // set various attributes required for displaying the correct information on the profile page
	    // 	request.setAttribute("name",rs.getString("name"));		    
	    // 	request.setAttribute("gender",rs.getString("gender"));
	    // 	request.setAttribute("age",2013 - rs.getInt("yearOfBirth"));
	    // 	request.setAttribute("address", rs.getString("address"));
	    // 	request.setAttribute("emailid", rs.getString("emailid"));
	    // 	request.setAttribute("phoneno", rs.getString("phonenum"));
	    // 	request.setAttribute("description", rs.getString("description"));
	    // 	request.setAttribute("numMatchesPlayed",rs.getString("numMatchesPlayed"));
	    // 	request.setAttribute("numMatchesWon",rs.getString("numMatchesWon"));
	    // 	request.setAttribute("numMatchesLost",rs.getInt("numMatchesPlayed") - rs.getInt("numMatchesWon"));
	    // 	request.setAttribute("rating",rs.getString("rating"));
	    // 	request.setAttribute("highestRatingAchieved",rs.getString("highestRatingAchieved"));
		    
	    // 	// set the attribute private notes
	    // 	{
	    // 	    String query = "select * from record where playerid="+id+" and type='Private';";
	    // 	    System.out.println(query);
	    // 	    rs = stmt.executeQuery (query);
	    // 	    String records = "";
	    // 	    int count = 1;
	    // 	    while (rs.next()) {
	    // 		records += count + ". " + rs.getString("description") + "\\n";
	    // 		count++;
	    // 	    }
	    // 	    System.out.println(records);
	    // 	    request.setAttribute("privateNotes",records);
	    // 	}

	    // 	// set the attribute public notes
	    // 	{
	    // 	    String query = "select * from record where playerid="+id+" and type='Public';";
	    // 	    System.out.println(query);
	    // 	    rs = stmt.executeQuery (query);
	    // 	    String records = "";
	    // 	    int count = 1;
	    // 	    while (rs.next()) {
	    // 		records += count + ". " + rs.getString("description") + "\\n";
	    // 		count++;
	    // 	    }
	    // 	    System.out.println(records);
	    // 	    request.setAttribute("publicNotes",records);
	    // 	}

	    // 	return playerProfileJsp;
	    }
	}
	catch (Exception e) {
	    System.out.println(e);
	}
	return "";
    }
}
