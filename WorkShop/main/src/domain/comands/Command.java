package domain.comands;

public interface Command <T extends EmptyInput> {
    void execute(T input);
}
