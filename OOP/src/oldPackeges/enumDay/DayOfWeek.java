package oldPackeges.enumDay;

public enum DayOfWeek {
    MONDAY(true),
    TUESDAY(true),
    WEDNESDAY(true),
    THURSDAY(true),
    FRIDAY(true),
    SATURDAY(false),
    SUNDAY(false);

    private boolean isWorkDay;

    DayOfWeek(boolean isWorkDay){
        this.isWorkDay = isWorkDay;
    }
    public boolean isWorkDay() {
        return isWorkDay;
    }
}
