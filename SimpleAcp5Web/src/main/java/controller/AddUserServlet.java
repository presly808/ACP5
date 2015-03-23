package controller;

import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddUserServlet", value = "/user/add")
public class AddUserServlet extends HttpServlet {

    private Map<Integer,User> users;

    @Override
    public void init() throws ServletException {
        super.init();
        users = (Map<Integer, User>) getServletContext().getAttribute("users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String age = req.getParameter("age");
        String name = req.getParameter("name");

        int id1 = Integer.parseInt(id);
        User user = new User(id1, name, Integer.parseInt(age));

        users.put(id1, user);

        resp.sendRedirect("/html/ok.html");

    }
}
