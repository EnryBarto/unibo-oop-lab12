package it.unibo.es2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogicsImpl implements Logics {
    private final List<Boolean> values;

    public LogicsImpl(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        this.values = new ArrayList<>(Collections.nCopies(size * size, false));
    }

    @Override
    public boolean click(final int pos) {
        if (pos >= this.values.size()) {
            throw new IllegalArgumentException("Invalid position");
        }
        final boolean old = this.values.get(pos);
        this.values.set(pos, !old);
        return this.values.get(pos);
    }

    @Override
    public List<Boolean> enablings() {
        return Collections.unmodifiableList(this.values);
    }

    @Override
    public boolean toQuit() {
        boolean allTrue;
        final int length = (int)Math.sqrt(this.values.size());

        for (int pos = 0; pos < length; pos++) {
            // Check row
            allTrue = true;
            for (int i = pos * length; i < (pos + 1) * length; i++) {
                if (!this.values.get(i)) {
                    allTrue = false;
                }
            }
            if (allTrue) {
                return true;
            }

            // Check column
            allTrue = true;
            for (int i = pos; i < length * length; i += length) {
                if (!this.values.get(i)) {
                    allTrue = false;
                }
            }
            if (allTrue) {
                return true;
            }
        }
        return false;
    }
}
