package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CountServlet", value = "/count")
public class CountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie cookie = getCookieByName(req.getCookies(), "count");

        Integer val = 0;

        if(cookie == null){
            resp.addCookie(new Cookie("count", val.toString()));
        } else {
            val = Integer.parseInt(cookie.getValue()) + 1;
            cookie.setValue(val.toString());
            resp.addCookie(cookie);
        }

        PrintWriter pw = resp.getWriter();
        pw.println(val);
        pw.flush();
    }


    private Cookie getCookieByName(Cookie[] cookies, String name){

        if (cookies == null){
            return null;
        } else {
            for (Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    return cookie;
                }
            }
        }
        return null;

    }
}
