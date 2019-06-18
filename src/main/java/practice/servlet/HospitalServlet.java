package practice.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 30.05.2019.
 */
public class HospitalServlet extends HttpServlet {

    private static final String insert_or_edit = "resources/hospital/hospital-details.jsp";

    private static final String list_all_hospitals = "resources/hospital/hospitals.jsp";

    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        String forward = "";

        if(request.getParameter("action").equalsIgnoreCase("edit")){

            forward = insert_or_edit;
            Integer id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id", id);

        } else if (request.getParameter("action").equalsIgnoreCase("insert")){

            forward =  insert_or_edit;
        } else if(request.getParameter("action").equalsIgnoreCase("list")){

            forward =  list_all_hospitals;
        }


        request.getRequestDispatcher(forward).forward(request, response);
    }

}
