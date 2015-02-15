package ua.artcode.week1.day1;

/**
 * Created by serhii on 20.01.15.
 */
public class TestState {

    public static void main(String[] args) {

        for(State s : State.values()){
            System.out.println(s);
        }

        System.out.println(State.getByCode(100));

    }



}
