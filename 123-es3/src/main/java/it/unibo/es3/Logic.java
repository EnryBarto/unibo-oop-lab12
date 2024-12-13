package it.unibo.es3;

import java.util.List;

public interface Logic {

    List<Pair<Integer, Integer>> getInitialPositions();

    List<Pair<Integer, Integer>> advance();

    boolean toQuit();

}
