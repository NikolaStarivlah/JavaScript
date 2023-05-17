package controller;

import model.Activity;
import model.ActivityDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("Action is: " + action);
        String page = "/index.jsp";
        try {
            switch (action) {
                case "/insertplan":
                    insertPlan(request, response);
                    break;
                case "/searchid":
                    searchId(request, response);
                    page = "/search.jsp";
                    break;
            }
        } catch (Exception e) {
            log("Exception: " + e);
            request.setAttribute("errormessage", "Exception: " + e);
        }
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }

    private void insertPlan(HttpServletRequest request, HttpServletResponse response) {
        String id1 = request.getParameter("id");
        String activityCategory = request.getParameter("activityCategory");
        String activity = request.getParameter("activity");
        if (id1 == null || id1.isBlank() || activityCategory == null || activityCategory.isBlank() ||
                activity == null || activity.isBlank()) {
            request.setAttribute("errormessage", "== Input data error. ==");
            return;
        }
        int id = Integer.parseInt(id1);
        if (id < 0 || id > 50) {
            request.setAttribute("errormessage", "== Input data error. ==");
            return;
        }
        Activity activity1 = new Activity(id, activityCategory, activity);
        boolean done = ActivityDAO.insertActivity(activity1);
        String insert;
        if (done)
            insert = "== New activity added to plan in DB: <br>" + activity1.toString();
        else
            insert = "== Activity ID " + activity1.getId() + " already exists in DB. ==";
        request.setAttribute("insert", insert);
    }

    private void searchId(HttpServletRequest request, HttpServletResponse response) {
        String activityCategory = request.getParameter("activityCategory");
        List<Activity> list;
        if (activityCategory.equals("All")) {
            list = ActivityDAO.selectAllActivities();
        } else {
            list = ActivityDAO.selectActivityCategory(activityCategory);
        }
        request.setAttribute("books", "== Find " + list.size() + " activities (category = " +
                activityCategory + "): <br>" + list.toString());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
