package src;

public class Node {
    final String key;
    final String value;
    Node next = null;

    public Node (String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "k: " + key + " v: " +value;
    }
}
