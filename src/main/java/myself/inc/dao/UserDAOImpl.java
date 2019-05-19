/*
 * Developed by Sumin Pavel on 4/28/19 2:30 PM.
 * Last modified 4/28/19 2:30 PM
 */

package myself.inc.dao;
import myself.inc.dataSets.UsersDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class UserDAOImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertUser(String login, String password, String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new UsersDataSet(login, password, email));
        transaction.commit();
        session.close();
    }

    @Override
    public UsersDataSet getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> query = criteriaBuilder.createQuery(UsersDataSet.class);
        CriteriaQuery<UsersDataSet> resultQuery =
                query.select(
                        query.from(UsersDataSet.class))
                        .where(criteriaBuilder
                                .equal(query.from(UsersDataSet.class).get("login"), login));
        final UsersDataSet resultData = session.createQuery(resultQuery).getSingleResult();
        transaction.commit();
        session.close();
        return resultData;
    }
}
