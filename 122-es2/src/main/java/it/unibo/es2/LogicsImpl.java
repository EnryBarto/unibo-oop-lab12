package it.unibo.es2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogicsImpl implements Logics {
    private final static String LABEL = "*";
    private final static String NO_LABEL = "";
    private final List<List<String>> values;

    public LogicsImpl(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        this.values = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.values.add(new ArrayList<>(Collections.nCopies(size, NO_LABEL)));
        }
    }

    @Override
    public String click(final Pair<Integer, Integer> pos) {
        if (pos.getX() >= this.values.size() || pos.getY() >= this.values.size()) {
            throw new IllegalArgumentException("Invalid position");
        }
        final String oldVal = this.values.get(pos.getY()).get(pos.getX());
        this.values.get(pos.getY()).set(pos.getX(), oldVal.equals(LABEL) ? NO_LABEL : LABEL);
        return this.values.get(pos.getY()).get(pos.getX());
    }

    @Override
    public boolean toQuit() {
        for (var row: this.values) {
            if (row.stream().filter(s -> s.equals(LABEL)).count() == row.size()) {
                return true;
            }
        }
        List<String> column;
        for (int i = 0; i < this.values.size(); i++) {
            column = new ArrayList<>(this.values.size());
            for (int j = 0; j < this.values.size(); j++) {
                column.add(this.values.get(j).get(i));
            }
            if (column.stream().filter(s -> s.equals(LABEL)).count() == column.size()) {
                return true;
            }
        }
        return false;
    }
}
