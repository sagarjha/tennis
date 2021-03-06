package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Login extends HttpServlet{
    public Login () {
	
    }
    public String handleLogin (HttpServletRequest request,HttpSession session, String username, String password, Connection conn, String type) {
	String vendorProfileJsp = "/profile/VendorprofileToHimself.jsp";
	String clubProfileJsp = "/profile/ClubProfiletoItself.jsp";
	String coachProfileJsp = "/profile/CoachProfiletohimeslf.jsp";
	String umpireProfileJsp = "/profile/UmpireprofiletoHimself.jsp";
	String playerProfileJsp = "/profile/playerProfiletoHimself.jsp";
	String loginNotSuccessful = "/loginError.jsp";
        System.out.println("Entered Login");
	// Get the username from the request
        try{
            if(request.getParameter("username")!=null)
                {
                   // username = request.getParameter("username");
                    System.out.println("Entered Username");
                    session.setAttribute("username",request.getParameter("username"));
                }
                // Get the password from the request
             if(request.getParameter("password")!=null)
                {
                    //password = request.getParameter("password");
                    session.setAttribute("password",request.getParameter("password"));
                }
             username = session.getAttribute("username").toString();
             password = session.getAttribute("password").toString();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
	
        
	// Query the database to check the authenticity of the login
	try {
	    Statement stmt = conn.createStatement();
	    String loginQuery = "";

	    //Check if the user is a valid vendor
	    loginQuery= "select * from vendor where username = '" + username + "' and password = '" + password + "'";
	    System.out.println(loginQuery);
	    ResultSet rs = stmt.executeQuery (loginQuery);
	    if (rs.next()) {
		//login successful, direct to the vendor profile
		System.out.println ("A vendor has logged in!");
		type="vendor";
		
		// store the id of the vendor for future queries
		int id = rs.getInt("id");
		session.setAttribute("accountid", id);
		// call the vendor login handler
		vendorLogin vL = new vendorLogin ();
		vL.vendorLoginHandler(id,request,rs,stmt);
		
		return vendorProfileJsp;
	    }

	    //Check if the user is a valid club
	    loginQuery= "select * from club where username = '" + username + "' and password = '" + password + "'";
	    System.out.println(loginQuery);
	    rs = stmt.executeQuery (loginQuery);
	    if (rs.next()) {
		//login successful, direct to the club profile
		System.out.println ("A club has logged in!");
		type="club";
		
		// store the id of the club for future queries
		int id = rs.getInt("id");
		session.setAttribute("accountid", id);
		// call the club login handler
		clubLogin cL = new clubLogin ();
		cL.clubLoginHandler(id,request,rs,stmt);
		
		return clubProfileJsp;
	    }

	    //Check if the user is a valid coach
	    loginQuery= "select * from coach where username = '" + username + "' and password = '" + password + "'";
	    System.out.println(loginQuery);
	    rs = stmt.executeQuery (loginQuery);
	    if (rs.next()) {
		//login successful, direct to the coach profile
		System.out.println ("A coach has logged in!");
		type="coach";
		
		// store the id of the coach for future queries
		int id = rs.getInt("id");
		session.setAttribute("accountid", id);
		// call the coach login handler
		coachLogin cL = new coachLogin ();
		cL.coachLoginHandler(id,request,rs,stmt,conn);
				
		return coachProfileJsp;
	    }

	    //Check if the user is a valid umpire
	    loginQuery= "select * from umpire where username = '" + username + "' and password = '" + password + "'";
	    System.out.println(loginQuery);
	    rs = stmt.executeQuery (loginQuery);
	    if (rs.next()) {
		//login successful, direct to the umpire profile
		System.out.println ("A umpire has logged in!");
		type="umpire";

		// store the id of the umpire for future queries
		int id = rs.getInt("id");
		session.setAttribute("accountid", id);
		// call the umpire login handler
		umpireLogin uL=new umpireLogin();
                uL.umpireLoginHandler(id,request,rs,stmt,conn);
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
		session.setAttribute("accountid", id);
		// call the player login handler
		playerLogin pL = new playerLogin ();
		pL.playerLoginHandler (id, request, rs, stmt);
		return playerProfileJsp;
	    }

	    // Invalid login
	    username = "";
	    password = "";
	    return loginNotSuccessful;
	}

	catch (Exception e) {
	    System.out.println(e);
	}
	return "";
    }
}
