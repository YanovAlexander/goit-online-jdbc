package ua.goit.dl;

public interface Repository<T> {

    T findById (String id);

    void save(T t);

    void remove(T t);

    T update(T t);
}
