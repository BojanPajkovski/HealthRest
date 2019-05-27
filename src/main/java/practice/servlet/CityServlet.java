package practice.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 10.04.2019.
 */
public class CityServlet extends HttpServlet {

    private static final String INSERT_OR_EDIT = "resources/city/city-details.jsp";

    private static final String LIST_ALL_CITIES = "resources/faculty/views/faculties.jsp";

    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        String forward = "";

        if(request.getParameter("action").equalsIgnoreCase("edit")){

            forward = INSERT_OR_EDIT;
            Integer id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id", id);

        } else if (request.getParameter("action").equalsIgnoreCase("insert")){

            forward =  INSERT_OR_EDIT;
        } else if(request.getParameter("action").equalsIgnoreCase("list")){

            forward =  LIST_ALL_CITIES;
        }


        request.getRequestDispatcher(forward).forward(request, response);
    }


}
