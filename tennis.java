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
	
        if (request.getParameter("LOGIN").toString().equals("Login")) {
	    System.out.println("Matched");
            Login L = new Login();
	    String redirectJsp = L.handleLogin(request,username,password,conn,type);
	    ServletContext context = getServletContext();
	    RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
	    dispatcher.forward(request, response);
            

	}
        
        //Register
        else if(request.getParameter("REGISTER").toString().equals("Proceed")) {
            String role=request.getParameter("role");
            session.setAttribute("type",role);
            ServletContext context = getServletContext();
            if((session.getAttribute("type").equals("1"))|
                    (session.getAttribute("type").equals("2"))|
                    (session.getAttribute("type").equals("3")))
            {   RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/personsignup.jsp");
                dispatcher.forward(request, response);
            }
            else if(session.getAttribute("type").equals("4"))
            {
                RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/clubsignup.jsp");
                dispatcher.forward(request, response);
            }
            else if(session.getAttribute("type").equals("5"))
            {
                RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/vendorsignup.jsp");
                dispatcher.forward(request, response);
            }
            
        }
        
        //personsignup.jsp
        else if(request.getParameter("PERSONSIGNUP").toString().equals("Proceed"))
        {
            String name=request.getParameter("name");
            String age=request.getParameter("age");
            String gender=request.getParameter("gender");
            String address=request.getParameter("address");
            String emailaddress=request.getParameter("emailaddress");
            String phoneno=request.getParameter("phoneno");
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            String clubid=request.getParameter("clubid");
            
            session.setAttribute("name",name);
            session.setAttribute("age",age);
            session.setAttribute("gender",gender);
            session.setAttribute("address",address);
            session.setAttribute("emailaddress",emailaddress);
            session.setAttribute("phoneno",phoneno);
            //phoneno can be empty string so we need to check for both empty and null when converting into int
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            session.setAttribute("clubid",clubid);
            
            ServletContext context = getServletContext();
            if(session.getAttribute("type").equals("1"))
            {
                RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/playersignup.jsp");
                dispatcher.forward(request, response);
            }
            else if(session.getAttribute("type").equals("2"))
            {
                RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/coachsignup.jsp");
                dispatcher.forward(request, response);
            }
            else if(session.getAttribute("type").equals("3"))
            {
                RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/umpiresignup.jsp");
                dispatcher.forward(request, response);
            }
        }
        
        //vendorsignup.jsp
        else if(request.getParameter("VENDORSIGNUP").toString().equals("Proceed"))
        {
            String name=request.getParameter("name");
            String address=request.getParameter("address");
            String emailaddress=request.getParameter("emailaddress");
            String phoneno=request.getParameter("phoneno");
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            
            session.setAttribute("name",name);
            session.setAttribute("address",address);
            session.setAttribute("emailaddress",emailaddress);
            session.setAttribute("phoneno",phoneno);
            //phoneno can be empty string so we need to check for both empty and null when converting into int
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/vendorsignup2.jsp");
            dispatcher.forward(request, response);
        }
        
        
        //clubsignup.jsp
        else if(request.getParameter("CLUBSIGNUP").toString().equals("Proceed")){
            String name=request.getParameter("name");
            String address=request.getParameter("address");
            String emailaddress=request.getParameter("emailaddress");
            String phoneno=request.getParameter("phoneno");
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            String courtnum=request.getParameter();
        }
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)	throws ServletException, IOException{
	System.out.println ("doPost");
	doGet(request, response);
    }

    public void init () {
	System.out.println("hello");
	//Load the PostgreSQL JDBC driver class
	try{
	    Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException cnfe){
	    System.out.println("Could not find the JDBC driver!");
	    connectionFailed = true;
	}
	        
	//Enter the connection details
	String hostname = "10.105.14.237";	// IP address of the machine running the PostgreSQL
	String username = "postgres"; // PostgreSQL username
	String password = "dbproject"; // PostgreSQL password
	String dbName = "postgres"; // Name of the database
	String connectionUrl = "jdbc:postgresql://" + hostname +  "/" + dbName;
	
	//Connect to the database
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
