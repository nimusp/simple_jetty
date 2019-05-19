/*
 * Developed by Sumin Pavel on 4/27/19 11:09 PM.
 * Last modified 4/27/19 11:09 PM
 */

package myself.inc.dbService;

import myself.inc.accounts.UserProfile;
import myself.inc.dao.UserDAOImpl;
import myself.inc.dao.UserDao;
import myself.inc.dataSets.UsersDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class DbServiceImpl implements DbService {

    private final SessionFactory sessionFactory;
    private final UserDao userDao;

    public DbServiceImpl() {
        Configuration cfg = getMySQLConfiguration();
        sessionFactory = getSessionFactory(cfg);
        userDao = new UserDAOImpl(sessionFactory);
    }

    public void addUser(UserProfile profile) {
        String login = profile.getLogin();
        String password = profile.getPassword();
        String email = profile.getEmail();
        userDao.insertUser(login, password, email);
    }

    public UserProfile getUserByLogin(String login) {
        UsersDataSet dbResult = userDao.getUserByLogin(login);
        return new UserProfile(dbResult.getLogin(), dbResult.getPassword(), dbResult.getEmail());
    }

    public void printConnectInfo() {
        try (Connection connection = sessionFactory
                .getSessionFactoryOptions().getServiceRegistry()
                .getService(ConnectionProvider.class).getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("DB name: " + metaData.getDatabaseProductName());
            System.out.println("DB version: " + metaData.getDatabaseProductVersion());
            System.out.println("Driver: " + metaData.getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Configuration getMySQLConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/my_database" +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "password");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    private SessionFactory getSessionFactory(Configuration configuration) {
        return configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build()
        );
    }
}
