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
        if (request.getParameter("LOGIN") != null) {
            if (request.getParameter("LOGIN").toString().equals("Login")) {
                System.out.println("Matched");
                Login L = new Login();
                String redirectJsp = L.handleLogin(request,username,password,conn,type);
                ServletContext context = getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
                dispatcher.forward(request, response);
            }
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
        
        //playersignup.jsp: need to change age and experience. Also redirect
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
        
        
        
        //coachsignup.jsp: need to change age and experience. Also redirect
         else if (request.getParameter("COACHSIGNUP") != null) {
            if(request.getParameter("COACHSIGNUP").toString().equals("Sign Up")){
                int new_id=0;
                String experience=request.getParameter("experience");
                String description=request.getParameter("description");
                try
                {
                    session.setAttribute("experience",experience);
                    session.setAttribute("description",description);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                try{
                    Statement stmt = conn.createStatement();
                    query="Select IDValue from Constant where ConstantName = 'account';";
                    ResultSet rs = stmt.executeQuery (query);
                    if(rs.next())
                    {    
                        new_id=rs.getInt("idvalue");
                        System.out.println(new_id);
                    }
                    conn.setAutoCommit(false);
                    query="Update Constant set IDValue = IDValue+1 where ConstantName = 'account';";
                    stmt.executeUpdate(query);
                    int yearofbirth=0;
                    int coachingstartyear=0;
                    query="Insert into Coach(ID,Name, Username, Password,  Address, Description,PhoneNum,EmailID, YearOfBirth, Gender, coachingstartyear) values ("+new_id+", '"+session.getAttribute("name")+"', '"+session.getAttribute("username")+"', '"+session.getAttribute("password")+"','" +session.getAttribute("address")+"', '"+session.getAttribute("description")+"', '"+session.getAttribute("phoneno")+"', '"+session.getAttribute("emailaddress")+"', "+yearofbirth+", '"+session.getAttribute("gender")+"', "+coachingstartyear+");";
                    stmt.executeUpdate(query);
                    query="Insert into teachesat(coachid, clubid) values ("+new_id+", "+session.getAttribute("clubid")+");";
                    stmt.executeUpdate(query);
                    conn.commit();
                }
                catch(SQLException e)
                {
                    System.out.println(e);
                    if (conn != null) 
                    {
                        try 
                        {
                            System.err.print("Transaction is being rolled back");
                            conn.rollback();
                        } 
                        catch(SQLException excep) 
                        {
                            System.out.println(excep);
                        }
                    }
                }
                finally
                {
                    try
                    {
                        conn.setAutoCommit(true);
                    }
                    catch(SQLException excep2)
                    {
                        System.out.println(excep2);
                    }
                }
            }
        }
        
        
        
        //umpiresignup.jsp: Need to change age and experience. Also redirect
        else if (request.getParameter("UMPIRESIGNUP") != null) {
            if(request.getParameter("UMPIRESIGNUP").toString().equals("Sign Up")){
                int new_id=0;
                String experience=request.getParameter("experience");
                String description=request.getParameter("description");
                try
                {
                    session.setAttribute("experience",experience);
                    session.setAttribute("description",description);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                try{
                    Statement stmt = conn.createStatement();
                    query="Select IDValue from Constant where ConstantName = 'account';";
                    ResultSet rs = stmt.executeQuery (query);
                    if(rs.next())
                    {    
                        new_id=rs.getInt("idvalue");
                        System.out.println(new_id);
                    }
                    conn.setAutoCommit(false);
                    query="Update Constant set IDValue = IDValue+1 where ConstantName = 'account';";
                    stmt.executeUpdate(query);
                    int yearofbirth=0;
                    int umpiringstartyear=0;
                    query="Insert into umpire(ID,Name, Username, Password,  Address, Description,PhoneNum,EmailID, YearOfBirth, Gender, umpiringstartyear, clubid) values ("+new_id+", '"+session.getAttribute("name")+"', '"+session.getAttribute("username")+"', '"+session.getAttribute("password")+"','" +session.getAttribute("address")+"', '"+session.getAttribute("description")+"', '"+session.getAttribute("phoneno")+"', '"+session.getAttribute("emailaddress")+"', "+yearofbirth+", '"+session.getAttribute("gender")+"', "+umpiringstartyear+","+session.getAttribute("clubid")+");";
                    stmt.executeUpdate(query);
                    query="Insert into teachesat(coachid, clubid) values ("+new_id+", "+session.getAttribute("clubid")+");";
                    stmt.executeUpdate(query);
                    conn.commit();
                }
                catch(SQLException e)
                {
                    System.out.println(e);
                    if (conn != null) 
                    {
                        try 
                        {
                            System.err.print("Transaction is being rolled back");
                            conn.rollback();
                        } 
                        catch(SQLException excep) 
                        {
                            System.out.println(excep);
                        }
                    }
                }
                finally
                {
                    try
                    {
                        conn.setAutoCommit(true);
                    }
                    catch(SQLException excep2)
                    {
                        System.out.println(excep2);
                    }
                }
            }
        }

	else if (request.getParameter("CLUBPROFILETOITSELF") != null) {
	    if (request.getParameter("CLUBPROFILETOITSELF").equals("View Tournaments Page")) {
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
        String hostname = "10.105.14.237";	// IP address of the machine running the PostgreSQL
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
