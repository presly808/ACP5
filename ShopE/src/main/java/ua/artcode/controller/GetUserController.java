package ua.artcode.controller;

import org.springframework.context.ApplicationContext;
import ua.artcode.exception.NoUserFoundException;
import ua.artcode.manager.ClientManager;
import ua.artcode.model.Client;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUserController extends HttpServlet {

    private ClientManager clientManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext app = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
        clientManager = app.getBean(ClientManager.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        try {
            String res = clientManager.signIn(login, pass);
            System.out.println(res);
        } catch (NoUserFoundException e) {
            // forward to error page
            e.printStackTrace();
        }
    }
}
