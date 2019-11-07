package it.distributedsystems.web;

import it.distributedsystems.util.HibernateUtil;
import it.distributedsystems.model.ejb.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "HibernateServlet", urlPatterns = {"hibernate"}, loadOnStartup = 1) // TODO: demo/hibernate???
public class HibernateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        initSessionFactory(response);
    }

    private void initSessionFactory(HttpServletResponse response) throws IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Department department = new Department("java");
        session.save(department);

        session.save(new Employee("Jakab Gipsz",department));
        session.save(new Employee("Captain Nemo",department));

        session.getTransaction().commit();

        Query q = session.createQuery("From Employee ");

        PrintWriter out = response.getWriter();
        List<Employee> resultList = q.list();
        System.out.println("num of employess:" + resultList.size());
        out.println("num of employees:" + resultList.size());
        for (Employee next : resultList) {
            System.out.println("next employee: " + next);
            out.println(next.toString());
        }

        /*
        session.disconnect();
        session.close();
        HibernateUtil.shutdown();
        */
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        initSessionFactory(response);
    }






}
