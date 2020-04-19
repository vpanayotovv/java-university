package oldPackeges.enumDay;

public class Main {
    public static void main(String[] args) {
        System.out.println(DayOfWeek.MONDAY.isWorkDay());
        System.out.println(DayOfWeek.FRIDAY.isWorkDay());
        System.out.println(DayOfWeek.THURSDAY.isWorkDay());
        System.out.println(DayOfWeek.FRIDAY.isWorkDay());
        System.out.println(DayOfWeek.SATURDAY.isWorkDay());
        System.out.println(DayOfWeek.SUNDAY.isWorkDay());
    }
}
