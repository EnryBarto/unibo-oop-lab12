package it.unibo.es3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class LogicImpl implements Logic {
    private final static int INITIAL_NUM = 3;
    private Map<Pair<Integer, Integer>, Status> matrix;

    public LogicImpl(final int size) {
        this.matrix = new HashMap<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.matrix.put(new Pair<>(j, i), Status.NONE);
            }
        }
        this.generateInitialPositions(size);
    }

    @Override
    public List<Pair<Integer, Integer>> getInitialPositions() {
        return this.matrix.entrySet()
            .stream()
            .filter(e -> e.getValue().equals(Status.INITIAL))
            .map(Entry::getKey)
            .toList();
    }

    @Override
    public List<Pair<Integer, Integer>> advance() {
        this.matrix.entrySet()
            .stream()
            .filter(e -> e.getValue().equals(Status.NONE))
            .map(Entry::getKey)
            .filter(this::hasActiveNear)
            .toList()
            .forEach(k -> this.matrix.replace(k, Status.COVERED));
        
        return this.matrix.keySet()
            .stream()
            .filter(k -> this.matrix.get(k).equals(Status.COVERED))
            .toList();
    }

    @Override
    public boolean toQuit() {
        return this.matrix.values()
            .stream()
            .filter(s -> s.equals(Status.NONE))
            .count() == 0;
    }

    private void generateInitialPositions(final int size) {
        int initial = 0;
        final Random random = new Random();
        while (initial < INITIAL_NUM) {
            final Pair<Integer, Integer> initPos = new Pair<>(random.nextInt(size), random.nextInt(size));
            if (this.matrix.get(initPos).equals(Status.NONE)) {
                this.matrix.replace(initPos, Status.INITIAL);
                initial++;
            }
        }
    }

    private boolean hasActiveNear(final Pair<Integer, Integer> pos) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    if (!this.matrix
                            .getOrDefault(new Pair<>(pos.getX() + i,pos.getY() + j), Status.NONE)
                            .equals(Status.NONE)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
