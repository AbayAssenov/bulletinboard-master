package kz.epam.training.bulletin_board.database;

import kz.epam.training.bulletin_board.constant.LoggerConstant;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Abay Assenov
 */

public class ConnectionPool {

    private final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;
    private String DRIVER_NAME;
    private List<Connection> freeConnections = new ArrayList<Connection>();
    private String URL;
    private String USER;
    private String PASSWORD;
    private int MAX_CONECTIONS;

    private static final String PROP_DRIVER_NAME = "DRIVER_NAME";
    private static final String PROP_URL = "URL";
    private static final String PROP_USER = "USER";
    private static final String PROP_PASSWORD = "PASSWORD";
    private static final String PROP_MAX_CONECTIONS = "MAX_CONECTIONS";
    private static final int PROP_DEFAULT_POOL_SIZE = 10;
    private static final String PROP_PROPERTIES_FILE = "database";

    private static void init() {

        if (instance == null) {

            ResourceBundle resourceDatabese = ResourceBundle.getBundle(PROP_PROPERTIES_FILE);

            String driver = resourceDatabese.getString(PROP_DRIVER_NAME);
            String url = resourceDatabese.getString(PROP_URL);
            String user = resourceDatabese.getString(PROP_USER);
            String password = resourceDatabese.getString(PROP_PASSWORD);
            String poolSizeStr = resourceDatabese.getString(PROP_MAX_CONECTIONS);

            int poolSize = (poolSizeStr != null) ?
                    Integer.parseInt(poolSizeStr) : PROP_DEFAULT_POOL_SIZE;

            instance = new ConnectionPool(driver, url, user, password, poolSize);
        }
    }

    private ConnectionPool(String DRIVER_NAME, String URL,
                           String user, String password, int maxConn) {

        this.DRIVER_NAME = DRIVER_NAME;
        this.URL = URL;
        this.USER = user;
        this.PASSWORD = password;
        this.MAX_CONECTIONS = maxConn;
        loadDrivers();
    }

    private void loadDrivers() {

        try {

            Driver driver = (Driver) Class.forName(DRIVER_NAME).newInstance();
            DriverManager.registerDriver(driver);

        } catch (Exception e) {
            //Can't register JDBC driver
            LOGGER.error(LoggerConstant.ERROR_CONNECTION_POOL, e);
        }
    }

    static synchronized public ConnectionPool getInstance() {
        init();
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection con ;
        if (!freeConnections.isEmpty()) {

            con = freeConnections.get(freeConnections.size() - 1);
            freeConnections.remove(con);

            try {

                if (con.isClosed()) {
                    con = getConnection();
                }
            } catch (SQLException e) {

                con = getConnection();
                LOGGER.error(LoggerConstant.ERROR_CONNECTION_POOL, e);
            }
        } else {

            con = newConnection();
        }
        return con;
    }

    private Connection newConnection() {

        Connection con ;
        try {

            if (USER == null) {
                con = DriverManager.getConnection(URL);
            } else {
                con = DriverManager.getConnection(URL,
                        USER, PASSWORD);
            }
            // "Created a new connection in pool „
        } catch (SQLException e) {
            // "Can't create a new connection for " + URL
            LOGGER.error(LoggerConstant.ERROR_CONNECTION_POOL, e);
            return null;
        }
        return con;
    }

    public synchronized void freeConnection(Connection con) {
        // Put the connection at the end of the List
        if ((con != null) && (freeConnections.size() <= MAX_CONECTIONS)) {
            freeConnections.add(con);
        }
    }

    public synchronized void release() {
        Iterator allConnections = freeConnections.iterator();
        while (allConnections.hasNext()) {

            Connection con = (Connection) allConnections.next();
            try {

                con.close();
                // "Closed connection for pool „
            } catch (SQLException e) {
                // "Can't close connection for pool „
                LOGGER.error(LoggerConstant.ERROR_CONNECTION_POOL, e);
            }
        }
        freeConnections.clear();
    }

}
