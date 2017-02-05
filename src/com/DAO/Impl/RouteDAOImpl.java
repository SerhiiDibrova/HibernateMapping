package com.DAO.Impl;

import com.DAO.RouteDAO;
import com.logic.Route;
import com.logic.Driver;
import com.logic.Bus;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.util.HibernateUtil;
import javax.swing.*;
import org.hibernate.Session;
import org.hibernate.Query;

public class RouteDAOImpl implements RouteDAO {

  public void addRoute(Route route) throws SQLException {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(route);
      session.getTransaction().commit();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(),"Ошибка при вставке", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {

        session.close();
      }
    }
  }

  public void updateRoute(Long route_id, Route route) throws SQLException {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.update(route);
      session.getTransaction().commit();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  public Route getRouteById(Long route_id) throws SQLException {
    Session session = null;
    Route route = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      route = (Route) session.load(Route.class, route_id);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return route;
  }

  public Collection getAllRoutes() throws SQLException {
    Session session = null;
    List routeses = new ArrayList<Route>();
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      routeses = session.createCriteria(Route.class).list();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return routeses;
  }

  public void deleteRoute(Route route) throws SQLException {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.delete(route);
      session.getTransaction().commit();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  public Collection getRoutesByDriver(Driver driver) throws SQLException {
    Session session = null;
    List routeses = new ArrayList<Route>();
    try {
      session = HibernateUtil.getSessionFactory().getCurrentSession();
      session.beginTransaction();
      Long driver_id = driver.getId();
      Query query = session.createQuery(
          " select b "
              + " from Route b INNER JOIN b.drivers driver"
              + " where driver.id = :driverId "
      )
          .setLong("driverId", driver_id);
      routeses = (List<Route>) query.list();
      session.getTransaction().commit();

    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return routeses;
  }

  public Collection getRoutesByBus(Bus bus){
    Session session = null;
    List routes = new ArrayList<Route>();
    try {
      session = HibernateUtil.getSessionFactory().getCurrentSession();
      session.beginTransaction();
      Long bus_id = bus.getId();
      Query query = session.createQuery("from Route where route_id = :routeId" ).setLong("busId", bus_id);
      routes = (List<Route>) query.list();
      session.getTransaction().commit();

    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return routes;
  }

}