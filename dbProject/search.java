package dbProject;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class search extends HttpServlet{
    public void searchPlayer(HttpServletRequest request, Connection conn){
        String searchString = request.getParameter("searchString");
        String basis = request.getParameter("basis");
        String order = request.getParameter("order");
        List <Integer> playerids = new ArrayList<Integer>();
        List <String> playernames = new ArrayList<String>();
        try{
            String query = "";
            Statement stmt = conn.createStatement();
            ResultSet rs;

            if(basis.equals("1")){
                //search by name
                if(order.equals("2")){
                    //order by rating
                    query = "select name, id from Player where lower(name) like lower('%" + searchString + 
                            "%') order by rating;";
                }
                else if(order.equals("3")){
                    //order by age
                    query = "select name, id from Player where lower(name) like lower('%" + searchString + 
                            "%') order by yearofbirth;";
                }
                else {
                    // order by name by default
                    query = "select name, id from Player where lower(name) like lower('%" + searchString + 
                            "%') order by name;";
                }
            }
            
            else if(basis.equals("2")){
                // search by club name
                if(order.equals("2")){
                    // order by rating
                    query = "select p.name as name,p.id as id from player as p, member as m, club as c where m.playerid = p.id"+
                            "and m.clubid = c.id and lower(c.name) like lower('%" + searchString +
                            "%') order by p.rating;";
                }
                else if(order.equals("3")){
                    // order by age
                    query = "select p.name as name,p.id as id from player as p, member as m, club as c where m.playerid = p.id"+
                            "and m.clubid = c.id and lower(c.name) like lower('%" + searchString +
                            "%') order by p.yearofbirth;";
                }
                else{
                    // order by name default
                    query = "select p.name as name,p.id as id from player as p, member as m, club as c where m.playerid = p.id"+
                            "and m.clubid = c.id and lower(c.name) like lower('%" + searchString +
                            "%') order by p.name;";
                } 
            }
            
            else if(basis.equals("3")){
                    // search by coached by
                if(order.equals("2")){
                    // order by rating
                    query = "select p.name as name , p.id as id from player p, coach c, training t" +
                            "where p.id = t.playerid and c.id = t.coachid and lower(c.name) like lower('%"
                            + searchString +"%') order by p.rating;";
                }
                else if(order.equals("3")){
                    // order by age
                    query = "select p.name as name , p.id as id from player p, coach c, training t" +
                            "where p.id = t.playerid and c.id = t.coachid and lower(c.name) like lower('%"
                            + searchString +"%') order by p.yearofbirth;";
                }
                else{
                    // order by name default
                    query = "select p.name as name , p.id as id from player p, coach c, training t" +
                            "where p.id = t.playerid and c.id = t.coachid and lower(c.name) like lower('%"
                            + searchString +"%') order by p.name;";
                }
            }
            
            else if(basis.equals("4")){
                    // search by gender
                if(order.equals("2")){
                    // order by rating
                    query = "select name, id from Player where lower(gender) like lower('%" + searchString +
                            "%') order by rating;";
                }    
                else if(order.equals("3")){
                    // order by age
                    query = "select name, id from Player where lower(gender) like lower('%" + searchString +
                            "%') order by yearofbirth;";
                }  
                else{
                    // order by name default
                    query = "select name, id from Player where lower(gender) like lower('%" + searchString +
                            "%') order by name;";
                }  
            }
            else if(basis.equals("5")){
                    // search by age
                int age = 0;
                String query1 = "Select extract(year from (select datevalue from constant where constantname = 'time')) as year;";
                rs=stmt.executeQuery(query1);
                if(rs.next()){
                    age = rs.getInt("year")- Integer.parseInt(searchString);
                }
                
                else if(order.equals("2")){
                    // order by rating
                    query = "select name, id from Player where yearofbirth = " + age + " order by rating;";
                } 
                else{
                    // order by name default
                    
                    query = "select name, id from Player where yearofbirth = " + age + " order by name;";
                }
            }
            else{
                    // search by locality
                if(order.equals("2")){
                    // order by rating
                    query = "select name, id from Player where lower(address) like lower('%" + searchString +
                            "%') order by rating;";
                }    
                else if(order.equals("3")){
                    // order by age
                    query = "select name, id from Player where lower(address) like lower('%" + searchString +
                            "%') order by yearofbirth;";
                }  
                else{
                    // order by name default
                    query = "select name, id from Player where lower(address) like lower('%" + searchString +
                            "%') order by name;";
                }      
            }
            System.out.println(query);
            rs = stmt.executeQuery(query);
            System.out.println("done");
            while(rs.next()){
                playerids.add(rs.getInt("id"));
                playernames.add(rs.getString("name"));
            }
            request.setAttribute("playerids", playerids);
            request.setAttribute("playernames", playernames);
        }
        catch (Exception e) {
	    System.out.println(e);
	}
    }
    
    
    public void searchCoach(HttpServletRequest request, Connection conn){
        String searchString = request.getParameter("searchString");
        String basis = request.getParameter("basis");
        String order = request.getParameter("order");
        List <Integer> coachids = new ArrayList<Integer>();
        List <String> coachnames = new ArrayList<String>();
        try{
            String query = "";
            Statement stmt = conn.createStatement();
            ResultSet rs;
            
            if(basis.equals("2")){
                // search by name of club
                if(order.equals("2")){
                    //rating
                    query = "select c.name as name, c.id as id from coach c, club clb, teachesat t"+ 
                            " where t.clubid = clb.id and t.coachid = c.id and lower(clb.name) like lower('%"
                            + searchString +"%') order by c.rating;";
                }
                else if(order.equals("3")){
                    //experience
                    query = "select c.name as name, c.id as id from coach c, club clb, teachesat t"+ 
                            " where t.clubid = clb.id and t.coachid = c.id and lower(clb.name) like lower('%"
                            + searchString +"%') order by c.coachingstartyear;";
                }
                else if(order.equals("4")){
                    // age
                    query = "select c.name as name, c.id as id from coach c, club clb, teachesat t"+ 
                            " where t.clubid = clb.id and t.coachid = c.id and lower(clb.name) like lower('%"
                            + searchString +"%') order by c.yearofbirth;";
                }
                else{
                    //name
                    query = "select c.name as name, c.id as id from coach c, club clb, teachesat t"+ 
                            " where t.clubid = clb.id and t.coachid = c.id and lower(clb.name) like lower('%"
                            + searchString +"%') order by c.name;";
                }
                
            }
            
            else {
                // search by name of coach
                if(order.equals("2")){
                    //rating
                    query = "select name, id from coach where lower(name) like lower('%" + searchString + 
                            "%') order by rating;";
                }
                else if(order.equals("3")){
                    //experience
                    query = "select name, id from coach where lower(name) like lower('%" + searchString + 
                            "%') order by coachingstartyear;";
                }
                else if(order.equals("4")){
                    // age
                    query = "select name, id from coach where lower(name) like lower('%" + searchString + 
                            "%') order by yearofbirth;";
                }
                else{
                    //name
                    query = "select name, id from coach where lower(name) like lower('%" + searchString + 
                            "%') order by name;";
                }
            }
            
            rs = stmt.executeQuery(query);
            while(rs.next()){
                coachids.add(rs.getInt("id"));
                coachnames.add(rs.getString("name"));
            }
            request.setAttribute("coachids", coachids);
            request.setAttribute("coachnames", coachnames);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
    public void searchVendor(HttpServletRequest request, Connection conn){
        String searchString = request.getParameter("searchString");
        String basis = request.getParameter("basis");
        List <Integer> vendorids = new ArrayList<Integer>();
        List <String> vendornames = new ArrayList<String>();
        try{
            String query = "";
            Statement stmt = conn.createStatement();
            ResultSet rs;
            
            if(basis.equals("2")){
                //by location
                query = "select name, id from vendor where lower(address) like lower('%" + searchString +
                            "%') order by name;";
            }
            if(basis.equals("3")){
                //by brand
                query = "select distinct v.name as name, v.id as id from vendor v, item i, sells s" + 
                        "where s.itemid = i.id and s.vendorid = v.id and lower(i.brand) like lower('%" +
                        searchString + "%') order by v.name;";
            }
            if(basis.equals("4")){
                // by product
                query = "select distinct v.name as name, v.id as id from vendor v, item i, sells s" + 
                        "where s.itemid = i.id and s.vendorid = v.id and lower(i.type) like lower('%" +
                        searchString + "%') order by v.name;";
            }
            else{
                // by name
                query = "select name, id from vendor where lower(name) like lower('%" + searchString + 
                            "%') order by name;";
            }
            
            rs = stmt.executeQuery(query);
            while(rs.next()){
                vendorids.add(rs.getInt("id"));
                vendornames.add(rs.getString("name"));
            }
            request.setAttribute("vendorids", vendorids);
            request.setAttribute("vendornames", vendornames);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
    public void searchClub(HttpServletRequest request, Connection conn){
        String searchString = request.getParameter("searchString");
        String basis = request.getParameter("basis");
        List <Integer> clubids = new ArrayList<Integer>();
        List <String> clubnames = new ArrayList<String>();
        try{
            String query = "";
            Statement stmt = conn.createStatement();
            ResultSet rs;
            
            if(basis.equals("2")){
                // by coach
                query = "select clb.name as name, clb.id as id from club clb, teachesat t, coach c"+
                        "where c.id = t.coachid and clb.id = t.clubid and lower(c.name) like lower('%" + searchString + 
                            "%') order by clb.name;";
                
            }
            if(basis.equals("3")){
                // by location
                query = "select name, id from club where lower(address) like lower('%" + searchString +
                            "%') order by name;";
            }
            else{
                // by name
                query = "select name, id from club where lower(name) like lower('%" + searchString + 
                            "%') order by name;";
            }
            
            rs = stmt.executeQuery(query);
            while(rs.next()){
                clubids.add(rs.getInt("id"));
                clubnames.add(rs.getString("name"));
            }
            request.setAttribute("clubids", clubids);
            request.setAttribute("clubnames", clubnames);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }    
}