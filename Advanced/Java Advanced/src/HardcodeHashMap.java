public class HardcodeHashMap {
    public static void main(String[] args) {

        NodeList list = new NodeList();
        Node alice = new Node("Alice", "Sofia");
        Node zoe = new Node("Zoe", "");
        Node charlie = new Node("Charlie", "London");

        NodeMap map = new NodeMap();

        map.put("Alice" , "Sofia");
        map.put("Zoe" , "Brussels");
        map.put("Charlie" , "London");

        System.out.println(map.get("Alice"));
    }
}
