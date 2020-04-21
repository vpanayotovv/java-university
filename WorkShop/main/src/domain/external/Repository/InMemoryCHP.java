package domain.external.Repository;

import domain.entities.Money;
import domain.repository.ConversionHistoryRepository;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryCHP implements ConversionHistoryRepository {
    private final Deque<String> history;

    public InMemoryCHP() {
        this.history = new ArrayDeque<>();
    }

    @Override
    public List<String> getLast(int n) {
        return this.history.stream().limit(n).collect(Collectors.toList());
    }

    @Override
    public void save(Money from, Money to) {
        String format = String.format("from: %s to %s", from, to);
        this.history.push(format);
    }
}
