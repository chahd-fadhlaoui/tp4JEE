package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IVoyageDao; // Changed import statement
import dao.VoyageDaoImpl; // Changed import statement
import metier.entities.Voyage; // Changed import statement

@WebServlet(name="cs", urlPatterns={"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {

    IVoyageDao metier; // Changed interface type
    @Override
    public void init() throws ServletException {
        metier = new VoyageDaoImpl(); // Changed implementation class
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/index.do")) {
            request.getRequestDispatcher("voyages.jsp").forward(request, response);
        }
        else if (path.equals("/chercher.do")) {
            String motCle = request.getParameter("motCle");
            VoyageModele model = new VoyageModele(); // Changed model type
            model.setMotCle(motCle);
            List<Voyage> voyages = metier.voyagesParMC(motCle); // Changed method name
            model.setVoyages(voyages);
            request.setAttribute("model", model);
            request.getRequestDispatcher("voyages.jsp").forward(request, response);
        }
        else if (path.equals("/saisie.do")) {
            request.getRequestDispatcher("saisieVoyage.jsp").forward(request, response); // Changed JSP file name
        }
        else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            Voyage v = metier.save(new Voyage(nom, prix)); // Changed entity type
            request.setAttribute("voyage", v); // Changed attribute name
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
        }
        else if (path.equals("/supprimer.do")) {
            Long id = Long.parseLong(request.getParameter("id"));
            metier.deleteVoyage(id);
            response.sendRedirect("chercher.do?motCle=");
        }
        else if (path.equals("/editer.do")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Voyage v = metier.getVoyage(id);
            request.setAttribute("voyage", v);
            request.getRequestDispatcher("editerVoyage.jsp").forward(request, response); // Changed JSP file name
        }
        else if (path.equals("/update.do")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            Voyage v = new Voyage();
            v.setIdVoyage(id);
            v.setNomVoyage(nom);
            v.setPrix(prix);
            metier.updateVoyage(v);
            request.setAttribute("voyage", v);
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
        }
        else {
            response.sendError(Response.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
