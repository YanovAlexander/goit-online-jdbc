package ua.goit.dl;

import java.util.Optional;

public interface DateExampleCustomRepository<T> {

    Optional<T> findById(Integer id);
}
