package connectionPool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static final int POOL_SIZE = 20;
    private static ConnectionPool connectionPool;
    private BlockingQueue<Connection> availableConnection = new ArrayBlockingQueue<>(POOL_SIZE);

    private ConnectionPool() {
        initializeData();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        createConnections();
    }
    public static synchronized ConnectionPool getInstance()  {
        if (connectionPool==null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
            try {
                connection = availableConnection.take();

            } catch (InterruptedException e) {
                logger.error(e.fillInStackTrace());
            }
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        if(connection!=null) {
            try {
                availableConnection.put(connection);
            } catch (InterruptedException e) {
                logger.error(e.fillInStackTrace());
            }
        }
        else {
            logger.error("This connection is empty!");
        }
    }
    public int getSizeAvailable() {
        return availableConnection.size();
    }
    private void initializeData(){
        ResourceManager resourceManager = new ResourceManager();
        driver = resourceManager.getValue(ParameterConstants.DRIVER);
        url = resourceManager.getValue(ParameterConstants.URL) ;
        user = resourceManager.getValue(ParameterConstants.USER_NAME)  ;
        password = resourceManager.getValue(ParameterConstants.PASSWORD) ;
    }
    private  BlockingQueue<Connection> createConnections (){
        Connection connection;
        while (availableConnection.size()<POOL_SIZE){
            try {
                connection = DriverManager.getConnection(url,user,password);
                availableConnection.put(connection);
            } catch (SQLException | InterruptedException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }
        return availableConnection;
    }
}
