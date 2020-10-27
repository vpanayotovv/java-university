package orm;

import orm.annotations.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Arrays;

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

    private Field getIdField(Class entity){
        return Arrays.stream(entity
                .getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));
    }
}
