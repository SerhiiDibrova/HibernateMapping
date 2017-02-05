package com.DAO;


import com.logic.Bus;
import com.logic.Driver;
import com.logic.Route;

import java.util.Collection;
import java.sql.SQLException;

public interface DriverDAO {
  public void addDriver(Driver Driver) throws SQLException;
  public void updateDriver(Long Driver_id, Driver Driver) throws SQLException;
  public Driver getDriverById(Long Driver_id) throws SQLException;
  public Collection getAllDriverses() throws SQLException;
  public void deleteDriver(Driver Driver) throws SQLException;
  public Collection getDriversByBus(Bus bus) throws SQLException;
  public Collection getDriversByRoute(Route route) throws SQLException;

}