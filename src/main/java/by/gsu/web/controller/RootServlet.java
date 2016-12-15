package by.gsu.web.controller;

import by.gsu.model.City;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vasiliy.pispanen.
 */
public class RootServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<City> cites = new ArrayList<>();
        cites.add(new City(1L, "Gomel"));
        cites.add(new City(2L, "Minsk"));
        req.setAttribute("cities", cites);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        rd.forward(req, resp);
    }
}
