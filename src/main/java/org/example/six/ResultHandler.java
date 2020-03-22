package org.example.six;

import java.util.List;

public class ResultHandler {

    public void printResult(List list) {
        for(Object q : list) {
            System.out.println(q.toString());
        }
    }
}
