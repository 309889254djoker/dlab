package dlab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import redis.clients.jedis.*;

public class LoginServlet extends HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	System.out.println(req.getParameter("username"));
    	System.out.println(req.getParameter("password"));
    	System.out.println("password" + req.getParameter("password"));
    	System.out.println(req.getRemoteAddr());
    	System.out.println(req.getRemoteHost());
    	RedisHelp redis = RedisHelp.getInstance();
    	redis.set(req.getParameter("username"), req.getParameter("password"));
    	
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	System.out.println(req.getParameter("username"));
    	System.out.println(req.getParameter("password"));
    	System.out.println(req.getRemoteAddr());
    	System.out.println(req.getRemoteHost());
    	System.out.println(req.getMethod());
    	Jedis redis = new Jedis("192.168.89.111", 6379);
    	//redis.set(req.getParameter("username"), req.getParameter("password"));
    	
    	System.out.println("ERROR");
    }
}
