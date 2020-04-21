package src;

public class NodeMap {
    private static final int INICIAL_CAPACITY = 4;
    private NodeList[] buckets;

    NodeMap() {
        this.buckets = new NodeList[INICIAL_CAPACITY];
    }

    public void put(String key, String value) {
        System.out.println(key.hashCode());
        int buketIdx = getBucketIndex(key);
        if (this.buckets[buketIdx] == null) {
            this.buckets[buketIdx] = new NodeList();
        }
        this.buckets[buketIdx].add(new Node(key, value));

        System.out.println(buketIdx);
    }

    public String get(String key) {
        int bucketIdx = getBucketIndex(key);
        NodeList list = buckets[bucketIdx];
        if (list == null) {
            return null;
        }
        return key;
    }


    private int getBucketIndex(String key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }
}
