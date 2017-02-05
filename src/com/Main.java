package com;

import java.sql.SQLException;
import java.util.*;



import com.DAO.Factory;
import com.logic.Bus;
import com.logic.Route;
import com.logic.Driver;

public class Main {

	public static void main(String[] args) throws SQLException {
	    
	    Collection routes = Factory.getInstance().getRouteDAO().getAllRoutes();
	    Iterator iterator = routes.iterator();
	    System.out.println("========��� ��������=========");
	    while (iterator.hasNext()) {
	      Route route = (Route) iterator.next();
	      System.out.println("������� : " + route.getName() + "  ����� �������� : " + route.getNumber());
	      Collection busses = Factory.getInstance().getBusDAO().getBussesByRoute(route);
	      Iterator iterator2 = busses.iterator();
	      while (iterator2.hasNext()) {
	        Bus bus = (Bus) iterator2.next();
	        System.out.println("������� � " + bus.getNumber());

	      }
	    }

	    Collection busses = Factory.getInstance().getBusDAO().getAllBusses();
	    iterator = busses.iterator();
	    System.out.println("========��� ��������=========");
	    while (iterator.hasNext()) {
	      Bus bus = (Bus) iterator.next();
	      Collection drivers = Factory.getInstance().getDriverDAO().getDriversByBus(bus);
	      Iterator iterator2 = drivers.iterator();
	      System.out.println("������� � " + bus.getNumber());
	      while (iterator2.hasNext()) {
	        Driver driver = (Driver) iterator2.next();
	        System.out.println("��� : " + driver.getName() + "   �������: " + driver.getSurname());

	      }
	    }

	  }
	}
	
	

