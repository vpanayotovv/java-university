package oldPackeges.Encapsulation;

public class PersonParser{

    public static Person parsonFrom(String line){
        String[] input = line.split("\\s+");
        String firstName = input[0];
        String secondName = input[1];
        int age = Integer.parseInt(input[2]);
        double salary = Double.parseDouble(input[3]);

        return new Person(firstName,secondName,age,salary);
    }
}
