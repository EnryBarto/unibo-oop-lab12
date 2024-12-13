package it.unibo.es2;

import java.util.List;

public interface Logics {
    boolean click(final int pos);
	List<Boolean> enablings();
	boolean toQuit();
}
