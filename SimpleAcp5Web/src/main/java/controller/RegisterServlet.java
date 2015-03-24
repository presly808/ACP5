package controller;

import model.User;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = "/user/register")
public class RegisterServlet extends HttpServlet {

    private Map<Integer,User> users;

    @Override
    public void init() throws ServletException {
        super.init();
        users = (Map<Integer, User>) getServletContext().getAttribute("users");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginParam = req.getParameter("login");
        String passParam = req.getParameter("pass");
        String ageParam = req.getParameter("age");

        // do validation

        int id = users.size() + 1;
        User user = new User(id, loginParam, passParam, Integer.parseInt(ageParam.trim()));

        users.put(id, user);

        resp.sendRedirect("/acp5/user/show?id=" + id);
    }
}
