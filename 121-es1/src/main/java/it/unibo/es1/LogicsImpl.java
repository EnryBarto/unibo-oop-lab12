package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {
	private final List<Integer> numbers;

	public LogicsImpl(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size must be positive");
		}
		this.numbers = new ArrayList<>(Collections.nCopies(size, 0));
	}

	@Override
	public int size() {
		return this.numbers.size();
	}

	@Override
	public List<Integer> values() {
		return Collections.unmodifiableList(numbers);
	}

	@Override
	public List<Boolean> enablings() {
		return numbers.stream().map(n -> n < this.size()).toList();
	}

	@Override
	public int hit(int elem) {
		this.numbers.set(elem, this.numbers.get(elem) + 1);
		return this.numbers.get(elem);
	}

	@Override
	public String result() {
		return numbers.stream()
			.map(n -> n.toString())
			.collect(Collectors.joining("|","<<", ">>"));
	}

	@Override
	public boolean toQuit() {
		return this.numbers.stream()
			.allMatch(n -> n == this.numbers.get(0));
	}
}
