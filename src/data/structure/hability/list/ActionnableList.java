package data.structure.hability.list;

import java.util.Iterator;

public interface ActionnableList<T> {
    void add(T elementToAdd);
    T get(int index);
    int size();
    Iterator<T> iterator();
}
