package com.DAO.Impl;

import com.DAO.DriverDAO;
import com.logic.Driver;
import com.logic.Bus;
import com.logic.Route;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.util.HibernateUtil;
import javax.swing.*;
import org.hibernate.Session;
import org.hibernate.Query;

public class DriverDAOImpl implements DriverDAO {

  public void addDriver(Driver Driver) throws SQLException {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(Driver);
      session.getTransaction().commit();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(),"Ошибка при вставке", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {

        session.close();
      }
    }
  }

  public void updateDriver(Long Driver_id, Driver Driver) throws SQLException {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.update(Driver);
      session.getTransaction().commit();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  public Driver getDriverById(Long Driver_id) throws SQLException {
    Session session = null;
    Driver Driver = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      Driver = (Driver) session.load(Driver.class, Driver_id);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return Driver;
  }

  public Collection getAllDriverses() throws SQLException {
    Session session = null;
    List Driverses = new ArrayList<Driver>();
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      Driverses = session.createCriteria(Driver.class).list();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return Driverses;
  }

  public void deleteDriver(Driver Driver) throws SQLException {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.delete(Driver);
      session.getTransaction().commit();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  public Collection getDriversByBus(Bus bus) throws SQLException {
    Session session = null;
    List Drivers = new ArrayList<Driver>();
    try {
      session = HibernateUtil.getSessionFactory().getCurrentSession();
      session.beginTransaction();
      Long bus_id = bus.getId();
      Query query = session.createQuery(
          " select b "
              + " from Driver b INNER JOIN b.drivers driver"
              + " where driver.id = :driverId "
      )
          .setLong("busId", bus_id);
      Drivers = (List<Driver>) query.list();
      session.getTransaction().commit();

    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return Drivers;
  }

  public Collection getDriversByRoute(Route route){
    Session session = null;
    List Drivers = new ArrayList<Driver>();
    try {
      session = HibernateUtil.getSessionFactory().getCurrentSession();
      session.beginTransaction();
      Long route_id = route.getId();
      Query query = session.createQuery("from Driver where route_id = :routeId" ).setLong("routeId", route_id);
      Drivers = (List<Driver>) query.list();
      session.getTransaction().commit();

    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
    return Drivers;
  }

}