import dbProject.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class tennis extends HttpServlet{
    // connectionFailed will be set to true in case there is any error in connecting the database
    boolean connectionFailed = false;
    String username = "";
    String password = "";
    String type = null;
    Connection conn = null;
    

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	System.out.println("doGet");
        HttpSession session = request.getSession();
        String query="";
        int flag=0;
        
        //Login
        if (request.getParameter("LOGIN") != null) {
            if (request.getParameter("LOGIN").toString().equals("Login")) {
                System.out.println("Matched");
                Login L = new Login();
                System.out.println(username);
                System.out.println(password);
                String redirectJsp = L.handleLogin(request,session,username,password,conn,type);
                System.out.println(username);
                System.out.println(password);
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
        
        //searchresultplayer.jsp
        else if (request.getParameter("SEARCHRESULTPLAYER") != null) {
            session.setAttribute("searchotherid",request.getParameter("SEARCHRESULTPLAYER").toString());
            System.out.println("SEARCHRESULTPLAYER");
            searchresultplayer L = new searchresultplayer();
            String redirectJsp="";
            try
		{
		    redirectJsp = L.searchresultplayerHandler(request,conn, session);
		}
            catch(Exception regexcp)
		{
		    System.out.println(regexcp);
		}
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
            dispatcher.forward(request, response);
        }
        
        
        //searchresultcoach.jsp
        else if (request.getParameter("SEARCHRESULTCOACH") != null) {
            session.setAttribute("searchotherid",request.getParameter("SEARCHRESULTCOACH").toString());
            System.out.println("SEARCHRESULTCOACH");
            searchresultcoach L = new searchresultcoach();
            String redirectJsp="";
            try
		{
		    redirectJsp = L.searchresultcoachHandler(request,conn, session);
		}
            catch(Exception regexcp)
		{
		    System.out.println(regexcp);
		}
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
            dispatcher.forward(request, response);
        }
        
        
        //searchresultumpire.jsp
        else if (request.getParameter("SEARCHRESULTUMPIRE") != null) {
            session.setAttribute("searchotherid",request.getParameter("SEARCHRESULTUMPIRE").toString());
            System.out.println("SEARCHRESULTUMPIRE");
            searchresultumpire L = new searchresultumpire();
            String redirectJsp="";
            try
		{
		    redirectJsp = L.searchresultumpireHandler(request,conn, session);
		}
            catch(Exception regexcp)
		{
		    System.out.println(regexcp);
		}
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
            dispatcher.forward(request, response);
        }
        
        
        //searchresultclub.jsp
        else if (request.getParameter("SEARCHRESULTCLUB") != null) {
            session.setAttribute("searchotherid",request.getParameter("SEARCHRESULTCLUB").toString());
            System.out.println("SEARCHRESULTCLUB");
            searchresultclub L = new searchresultclub();
            String redirectJsp="";
            try
		{
		    redirectJsp = L.searchresultclubHandler(request,conn, session);
		}
            catch(Exception regexcp)
		{
		    System.out.println(regexcp);
		}
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
            dispatcher.forward(request, response);
        }
        
        
        
        //searchresultvendor.jsp
        else if (request.getParameter("SEARCHRESULTVENDOR") != null) {
            session.setAttribute("searchotherid",request.getParameter("SEARCHRESULTVENDOR").toString());
            System.out.println("SEARCHRESULTVENDOR");
            searchresultvendor L = new searchresultvendor();
            String redirectJsp="";
            try
		{
		    redirectJsp = L.searchresultvendorHandler(request,conn, session);
		}
            catch(Exception regexcp)
		{
		    System.out.println(regexcp);
		}
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
            dispatcher.forward(request, response);
        }
        
        
        //Register
        else if (request.getParameter("REGISTER") != null) {
            if(request.getParameter("REGISTER").toString().equals("Proceed")) {
                String redirectJsp="";
                register reg=new register();
                try
		    {
			redirectJsp = reg.registerHandler(request, session);
		    }
                catch(Exception regexcp)
		    {
			System.out.println(regexcp);
		    }
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
	//Adding news by the club
	
	else if (request.getParameter("ADDNEWS") != null) {
            if (request.getParameter("ADDNEWS").equals("Add News")) {
                System.out.println("come from searchclub.jsp into tennis.java, call searchClub in search.java");
                String redirectJsp = "/Functionality/newsAdded.jsp";
                addNews ANH = new addNews();
                try{
                    redirectJsp = ANH.addNewsHandler(request, conn, session);
                }
                catch(Exception e) {
		    System.out.println(e);
		}
                
                
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        } 
	//Addition of an Item by a vendor
 		
	else if (request.getParameter("ADDITEM") != null) {
            if (request.getParameter("ADDITEM").equals("Add")) {
                System.out.println("come from searchclub.jsp into tennis.java, call searchClub in search.java");
                String redirectJsp = "./itemadded.jsp";
                addItem IAH = new addItem();
                try{
                    redirectJsp = IAH.itemAdditionHandler(request, conn, session);
                }
                catch(Exception e) {
		    System.out.println(e);
		}
                
                
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
        
        //Player Becoming a member of a club
        else if (request.getParameter("CLUBPROFILEOTHERS") != null) {
            System.out.println("CLUBPROFILEOTHERS");
            String redirectJsp="";
            addclub ac=new addclub();
            try{
                redirectJsp=ac.addclubHandler(request,conn,session);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
            dispatcher.forward(request, response);
            
        }
        
        //Player adding private records
        else if (request.getParameter("ADDPRIVATERECORD") != null) {
            System.out.println("ADDPRIVATERECORD");
            String redirectJsp="";
            addprivaterecord pv=new addprivaterecord();
            try{
                redirectJsp=pv.addprivaterecordHandler(request,session,conn);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
            dispatcher.forward(request, response);
        }
        
        //Challenging a player by a player
        else if (request.getParameter("CHALLENGEPLAYER") != null) {
            System.out.println("CHALLENGEPLAYER");
            String redirectJsp="/Functionality/Challenge.jsp";
            challengehandler ch=new challengehandler();
            try{
                ch.goToChallengePage(conn,request,session);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
            dispatcher.forward(request, response);
        }
        
        //personsignup.jsp
        else if (request.getParameter("PERSONSIGNUP") != null) {
            if(request.getParameter("PERSONSIGNUP").toString().equals("Proceed"))
		{
		    String redirectJsp="";
		    personsignup su=new personsignup();
		    try
			{
			    redirectJsp = su.personsignupHandler(request, session, conn);
			}
		    catch(Exception regexcp)
			{
			    System.out.println(regexcp);
			}
		    ServletContext context = getServletContext();
		    RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
		    dispatcher.forward(request, response);
		}
        }
        
        //vendorsignup.jsp
        else if (request.getParameter("VENDORSIGNUP") != null) {
            if(request.getParameter("VENDORSIGNUP").toString().equals("Proceed"))
		{
		    String redirectJsp="";
		    vendorsignup su=new vendorsignup();
		    try
			{
			    redirectJsp = su.vendorsignupHandler(request, session, conn);
			}
		    catch(Exception regexcp)
			{
			    System.out.println(regexcp);
			}
		    ServletContext context = getServletContext();
		    RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
		    dispatcher.forward(request, response);
		}
        }
        
        //clubsignup.jsp
        else if (request.getParameter("CLUBSIGNUP") != null) {
            if(request.getParameter("CLUBSIGNUP").toString().equals("Proceed")){
                String redirectJsp="";
                clubsignup su=new clubsignup();
                try
		    {
			redirectJsp = su.clubsignupHandler(request, session, conn);
		    }
                catch(Exception regexcp)
		    {
			System.out.println(regexcp);
		    }
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
        
        //playersignup.jsp: need to change age and experience
        else if (request.getParameter("PLAYERSIGNUP") != null) {
            if(request.getParameter("PLAYERSIGNUP").toString().equals("Sign Up")){
                
                String redirectJsp="";
                playersignup su=new playersignup();
                try
		    {
			redirectJsp = su.playersignupHandler(request, session, conn);
		    }
                catch(Exception regexcp)
		    {
			System.out.println(regexcp);
		    }
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
        
        
        
        //coachsignup.jsp: need to change age and experience
	else if (request.getParameter("COACHSIGNUP") != null) {
            if(request.getParameter("COACHSIGNUP").toString().equals("Sign Up")){
                String redirectJsp="";
                coachsignup su=new coachsignup();
                try
		    {
			redirectJsp = su.coachsignupHandler(request, session, conn);
		    }
                catch(Exception regexcp)
		    {
			System.out.println(regexcp);
		    }
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
        
        
        
        //umpiresignup.jsp: Need to change age and experience
        else if (request.getParameter("UMPIRESIGNUP") != null) {
            if(request.getParameter("UMPIRESIGNUP").toString().equals("Sign Up")){
                String redirectJsp="";
                umpiresignup su=new umpiresignup();
                try
		    {
			redirectJsp = su.umpiresignupHandler(request, session, conn);
		    }
                catch(Exception regexcp)
		    {
			System.out.println(regexcp);
		    }
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }

        //clubsignup2.java
        else if (request.getParameter("CLUBSIGNUP2") != null) {
            if(request.getParameter("CLUBSIGNUP2").toString().equals("Sign Up")){
                String redirectJsp="";
                clubsignup2 su=new clubsignup2();
                try
		    {
			redirectJsp = su.clubsignup2Handler(request, session, conn);
		    }
                catch(Exception regexcp)
		    {
			System.out.println(regexcp);
		    }
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
        
        
        //vendorsignup2.java
        else if (request.getParameter("VENDORSIGNUP2") != null) {
            if(request.getParameter("VENDORSIGNUP2").toString().equals("Sign Up")){
                String redirectJsp="";
                vendorsignup2 su=new vendorsignup2();
                try
		    {
			redirectJsp = su.vendorsignup2Handler(request, session, conn);
		    }
                catch(Exception regexcp)
		    {
			System.out.println(regexcp);
		    }
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
      //Changing the price by the vendor
      
      else if (request.getParameter("CHANGEPRICE") != null) {
            if (request.getParameter("CHANGEPRICE").equals("Change")) {
                System.out.println("come from searchclub.jsp into tennis.java, call searchClub in search.java");
                String redirectJsp = "/Functionality/priceChanged.jsp";
                changePrice CPH = new changePrice();
                try{
                    redirectJsp = CPH.changePriceHandler(request, conn, session);
                }
                catch(Exception e) {
		    System.out.println(e);
		}
                
                
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }  
        
	else if (request.getParameter("VIEWTOURNAMENTSPAGE") != null) {
	    if (request.getParameter("VIEWTOURNAMENTSPAGE").equals("View Tournaments Page")) {
		System.out.println("redirected from clubprofiletoitself");
		tournamentViewHandler TVH = new tournamentViewHandler ();
		try {
		    TVH.handleView (conn,request);
		}
		catch (Exception e) {
		    System.out.println(e);
		}

		String redirectJsp = "/tournament/tournament.jsp";
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
	    }
	}

	else if (request.getParameter("TOURNAMENTVIEW") != null) {
	    showTournamentPageHandler STPH = new showTournamentPageHandler ();
	    try {
		STPH.handleShow(conn,request);
	    }
	    catch (Exception e) {
		System.out.println(e);
	    }
	    String redirectJsp = "/tournament/clubtournament.jsp";
	    ServletContext context = getServletContext();
	    RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
	    dispatcher.forward(request, response);
	}
        
        else if (request.getParameter("SEARCHPLAYER") != null) {
            if (request.getParameter("SEARCHPLAYER").equals("Search")) {
                System.out.println("come from searchplayer.jsp into tennis.java, call searchPlayer in search.java");
                search S = new search();
                try{
                    S.searchPlayer(request, conn);
                }
                catch(Exception e) {
		    System.out.println(e);
		}
                System.out.println("searched the player");
                String redirectJsp = "/search/searchresultplayer.jsp";
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
        
        else if (request.getParameter("SEARCHCOACH") != null) {
            if (request.getParameter("SEARCHCOACH").equals("Search")) {
                System.out.println("come from searchcoach.jsp into tennis.java, call searchCoach in search.java");
                search S = new search();
                try{
                    S.searchCoach(request, conn);
                }
                catch(Exception e) {
		    System.out.println(e);
		}
                
                String redirectJsp = "/search/searchresultcoach.jsp";
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
        
        else if (request.getParameter("SEARCHVENDOR") != null) {
            if (request.getParameter("SEARCHVENDOR").equals("Search")) {
                System.out.println("come from searchvendor.jsp into tennis.java, call searchVendor in search.java");
                search S = new search();
                try{
                    S.searchVendor(request, conn);
                }
                catch(Exception e) {
		    System.out.println(e);
		}
                
                String redirectJsp = "/search/searchresultvendor.jsp";
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
        
        else if (request.getParameter("SEARCHCLUB") != null) {
            if (request.getParameter("SEARCHCLUB").equals("Search")) {
                System.out.println("come from searchclub.jsp into tennis.java, call searchClub in search.java");
                search S = new search();
                try{
                    S.searchClub(request, conn);
                }
                catch(Exception e) {
		    System.out.println(e);
		}
                
                String redirectJsp = "/search/searchresultclub.jsp";
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }

        else if (request.getParameter("AccreditUmpire") != null) {	
	    if (request.getParameter("AccreditUmpire").equals("Matches to be Accredited")) {
		try{
		    Accredit A = new Accredit();
		    A.handleAccredition(Integer.parseInt(session.getAttribute("accountid").toString()),request,conn);
                }
                catch(Exception e) {
		    System.out.println(e); 
		}
                
                String redirectJsp = "/schedule/umpireAck.jsp";
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
	    
	    else if (request.getParameter("AccreditUmpire").equals("Accredit")) {
		try{
		    Accredit A = new Accredit();
		    A.handleAccreditionOfMatches(Integer.parseInt(session.getAttribute("accountid").toString()),request,conn);
		}
		catch(Exception e) {
		    System.out.println(e);
		}
		String redirectJsp = "/signupAndLogin/operationsuccessful.jsp";
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
	    }
	}
        
        else if (request.getParameter("CHALLENGE") != null) {
            if (request.getParameter("CHALLENGE").equals("Challenge")) {
                try{
                   challengehandler ch = new  challengehandler();
                   ch.challengeDetailsFilled(conn,request,session);
                }
                catch(Exception e) {
		    System.out.println(e); 
		}
                String redirectJsp = "/Functionality/challengeStatus.jsp";// put correct jsp
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }

	else if (request.getParameter("SEEPENDINGCHALLENGES") != null) {
	    if (request.getParameter("SEEPENDINGCHALLENGES").equals("See Pending Challenges")) {
		try {
		    challengehandler ch = new challengehandler();
		    ch.acceptRejectChallenge(conn,request,session);
		}

		catch (Exception e) {
		    System.out.println(e);    
		}
		
		String redirectJsp = "/schedule/pendingChallengesPlayer.jsp";
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
	    }
	}
        
        //Add coaching slot for coach
         else if(request.getParameter("addcoachingslot") != null){
            if (request.getParameter("addcoachingslot").equals("Add Coaching Slot")) {
                System.out.println("Add coaching slot in coach");
                String redirectJsp="";
                try{
                    addcoachingslotcoach ac = new addcoachingslotcoach();
                    redirectJsp=ac.addcoachslot(request,conn,session);
                }
                catch(Exception e) {
		    System.out.println(e); 
		}
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
         
         //Add coach for player
         else if(request.getParameter("PLAYERADDCOACHINGSLOT") != null){
            if (request.getParameter("PLAYERADDCOACHINGSLOT").equals("Add Coaching Slot")) {
                System.out.println("Add coaching slot in player");
                String redirectJsp = "";
                try{
                    addcoachingslotplayer ac = new addcoachingslotplayer();
                    redirectJsp=ac.addcoachingslotplayerHandler(request,session,conn);
                }
                catch(Exception e) {
		    System.out.println(e); 
		}
                System.out.println(redirectJsp);
		ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
        }
         
         
         //Logout: Session Invalidate
        else if (request.getParameter("LOGOUT") != null) {
            session.invalidate();
            String redirectJsp = "/login.jsp";// put correct jsp
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
            dispatcher.forward(request, response);
        }
	
	else {
	    // check if some user is logged in
	    if (session.getAttribute("username") != null) {
		if (!session.getAttribute("username").equals("")) {
		    Login L = new Login();
		    String redirectJsp = L.handleLogin(request,session,username,password,conn,type);
		    ServletContext context = getServletContext();
		    RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
		    dispatcher.forward(request, response);
		}
		else {
		    String redirectJsp = "/login.jsp";
		    ServletContext context = getServletContext();
		    RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
		    dispatcher.forward(request, response);
		}
	    }
	    else {
		String redirectJsp = "/login.jsp";
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
		dispatcher.forward(request, response);
	    }
        }
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)	throws ServletException, IOException{
        System.out.println ("doPost");
        doGet(request, response);
    }

    public void init () {
        System.out.println("hello");
        // Load the PostgreSQL JDBC driver class
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException cnfe){
            System.out.println("Could not find the JDBC driver!");
            connectionFailed = true;
        }

        // Enter the connection details
        String hostname = "10.105.15.169"; // IP address of the machine running the PostgreSQL
        String username = "postgres"; // PostgreSQL username
        String password = "dbproject"; // PostgreSQL password
        String dbName = "postgres"; // Name of the database
        String connectionUrl = "jdbc:postgresql://" + hostname +  "/" + dbName;

        // Connect to the database
        try {
            conn = DriverManager.getConnection(connectionUrl,username, password);
            System.out.println("Connected successfullly");
        } catch (SQLException sqle) {
            System.out.println("Connection failed");
            System.out.println(sqle);
            connectionFailed = true;;
        }
    }
}
