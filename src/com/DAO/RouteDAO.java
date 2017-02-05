package com.DAO;


import com.logic.Route;
import com.logic.Driver;
import com.logic.Bus;

import java.util.Collection;
import java.sql.SQLException;

public interface RouteDAO {
  public void addRoute(Route Route) throws SQLException;
  public void updateRoute(Long Route_id, Route Route) throws SQLException;
  public Route getRouteById(Long Route_id) throws SQLException;
  public Collection getAllRoutes() throws SQLException;
  public void deleteRoute(Route Route) throws SQLException;
  public Collection getRoutesByDriver(Driver driver) throws SQLException;
  public Collection getRoutesByBus(Bus bus) throws SQLException;

}