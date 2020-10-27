package orm;

import java.sql.Connection;

public class EntityManager implements DbContext {

    Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public boolean persist(Object entity) {
        return false;
    }

    public Iterable find(Class table) {
        return null;
    }

    public Iterable find(Class table, String where) {
        return null;
    }

    public Object findFirst(Class table) {
        return null;
    }

    public Object findFirst(Class table, String where) {
        return null;
    }
}
