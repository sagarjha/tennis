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
	//try{
        if (request.getParameter("SUBMIT").toString().equals("Login")) {
	    System.out.println("Matched");
            Login L = new Login();
	    String redirectJsp = L.handleLogin(request,username,password,conn,type);
	    ServletContext context = getServletContext();
	    RequestDispatcher dispatcher = context.getRequestDispatcher(redirectJsp);
	    dispatcher.forward(request, response);
            

	}
        else
            System.out.println("Not Matched");
        //}
        /*catch(Exception e)
        {
            System.out.println(e);
        }*/
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
