package service;

import java.util.List;
import java.util.Scanner;

public interface ICrudManager<E> {
    E create(Scanner scanner);

    void save(E e);

    void update(Scanner scanner);

    void deleteById(Scanner scanner);

    E findById(Scanner scanner);

    void displayAll(List<E> elements);
}
