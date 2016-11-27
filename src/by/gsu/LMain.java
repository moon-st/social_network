package by.gsu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 24.11.16.
 */
public class LMain {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(3);
        list.add(2);

        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        list.sort((Integer o1, Integer o2) -> {
                return Integer.compare(o1, o2);
            }
        );

        list.sort((o1, o2) -> Integer.compare(o1, o2));

        list.sort(Integer::compare);

        System.out.println(list);
    }
}
