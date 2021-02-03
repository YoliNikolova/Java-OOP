package shopping;

public class Validator {

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isNonNegativeNum(double value){
        return value>=0;
    }
}
