package practice.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 27.05.2019.
 */
public class DoctorServlet extends HttpServlet {

    private static final String INSERT_OR_EDIT = "resources/doctor/doctor-details.jsp";

    private static final String LIST_ALL_DOCTORS = "resources/doctor/doctors.jsp";

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

            forward =  LIST_ALL_DOCTORS;
        }


        request.getRequestDispatcher(forward).forward(request, response);
    }




}
