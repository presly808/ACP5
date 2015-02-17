package ua.artcode.week1.day1;

/**
 * Created by serhii on 19.01.15.
 */
public interface ITree<E> {


    boolean add(E el);

    boolean remove(E el);

    boolean contains(Object el);

    void printAsc();

    void printDesc();


}
