package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "ShowUserServlet", value = "/user/show")
public class ShowUserServlet extends HttpServlet {

    public static final String PATH = "/WEB-INF/pages/user-info.jsp";
    private Map<Integer,User> users;

    @Override
    public void init() throws ServletException {
        super.init();
        users = (Map<Integer, User>) getServletContext().getAttribute("users");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        String idParam = req.getParameter("id");
        int id = Integer.parseInt(idParam);

        User user = users.get(id);




        if(user == null){
            printWriter.println("User not found");
            printWriter.flush();
        } else {
            req.getServletContext().setAttribute("userA", user);
            req.getRequestDispatcher(PATH).forward(req,resp);
        }

    }
}
