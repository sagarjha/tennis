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
            String role=request.getParameter("role");
            
            session.setAttribute("type",role);
            session.setAttribute("usernamealreadyexists",0);
            
            ServletContext context = getServletContext();
            if((session.getAttribute("type").equals("1"))|
               (session.getAttribute("type").equals("2"))|
               (session.getAttribute("type").equals("3")))
                {   String redirect="/signupAndLogin/personsignup.jsp";
                    RequestDispatcher dispatcher = context.getRequestDispatcher(redirect);
                    System.out.println(redirect);
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
        }

        //personsignup.jsp
        else if (request.getParameter("PERSONSIGNUP") != null) {
            if(request.getParameter("PERSONSIGNUP").toString().equals("Proceed"))
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
                
                try{
                    Statement stmt = conn.createStatement();
                    
                    if((session.getAttribute("type").equals("1")))
                        query="select username from player where username='"+username+"';";
                    else if((session.getAttribute("type").equals("2")))
                        query="select username from coach where username='"+username+"';";
                    else if((session.getAttribute("type").equals("3")))
                        query="select username from Umpire where username='"+username+"';";
                    ResultSet rs = stmt.executeQuery (query);
                    
                    flag=0;
                    if(rs.next())
                    {
                        flag=1;
                    }
                    if(flag==0)
                    {
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
                    else
                    {
                        
                        session.setAttribute("usernamealreadyexists",1);
                        ServletContext context = getServletContext();
                        
                        RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/personsignup.jsp");
                        dispatcher.forward(request, response);
                        
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                
            }
        }
        
        //vendorsignup.jsp
        else if (request.getParameter("VENDORSIGNUP") != null) {
            if(request.getParameter("VENDORSIGNUP").toString().equals("Proceed"))
            {
                String name=request.getParameter("name");
                String address=request.getParameter("address");
                String emailaddress=request.getParameter("emailaddress");
                String phoneno=request.getParameter("phoneno");
                String username=request.getParameter("username");
                String password=request.getParameter("password");
                //**********************************************
                
                try{
                    Statement stmt = conn.createStatement();
                    query="select username from vendor where username='"+username+"';";
                    ResultSet rs = stmt.executeQuery (query);
                    
                    flag=0;
                    if(rs.next())
                    {
                        flag=1;
                    }
                    if(flag==0)
                    {
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
                    else
                    {
                        
                        session.setAttribute("usernamealreadyexists",1);
                        ServletContext context = getServletContext();
                        
                        RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/vendorsignup.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                
                
                
                //**********************************************
                
            }
        }
        
        //clubsignup.jsp
        else if (request.getParameter("CLUBSIGNUP") != null) {
            if(request.getParameter("CLUBSIGNUP").toString().equals("Proceed")){
                String name=request.getParameter("name");
                String address=request.getParameter("address");
                String emailaddress=request.getParameter("emailaddress");
                String phoneno=request.getParameter("phoneno");
                String username=request.getParameter("username");
                String password=request.getParameter("password");
                int courtnum=Integer.parseInt(request.getParameter("courtnum"));
                int coachingslot=Integer.parseInt(request.getParameter("coachingslot"));

                //**********************************************
                
                try{
                    Statement stmt = conn.createStatement();
                    query="select username from club where username='"+username+"';";
                    ResultSet rs = stmt.executeQuery (query);
                    
                    flag=0;
                    if(rs.next())
                    {
                        flag=1;
                    }
                    if(flag==0)
                    {
                        session.setAttribute("name",name);
                        session.setAttribute("address",address);
                        session.setAttribute("emailaddress",emailaddress);
                        session.setAttribute("phoneno",phoneno);
                        session.setAttribute("username",username);
                        session.setAttribute("password",password);
                        session.setAttribute("courtnum",courtnum);
                        session.setAttribute("coachingslot",coachingslot);



                        ServletContext context = getServletContext();
                        RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/clubsignup2.jsp");
                        dispatcher.forward(request, response);
                    }
                    else
                    {
                        
                        session.setAttribute("usernamealreadyexists",1);
                        ServletContext context = getServletContext();
                        
                        RequestDispatcher dispatcher = context.getRequestDispatcher("/signupAndLogin/clubsignup.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                
                //**********************************************
                
                
                
            }
        }
        
        //playersignup.jsp
        else if (request.getParameter("PLAYERSIGNUP") != null) {
            if(request.getParameter("PLAYERSIGNUP").toString().equals("Sign Up")){
                int new_id=0;
                try{
                    Statement stmt = conn.createStatement();
                    query="Select IDValue from Constant where ConstantName = ‘account’;";
                    ResultSet rs = stmt.executeQuery (query);
                    if(rs.next())
                        new_id=rs.getInt("idvalue");
                    conn.setAutoCommit(false);
                    query="Update Constant set IDValue = IDValue+1 where ConstantName = ‘account’;";
                    
                    conn.setAutoCommit(true);
                }
                catch(Exception e)
                {
                    System.out.println(e);
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
