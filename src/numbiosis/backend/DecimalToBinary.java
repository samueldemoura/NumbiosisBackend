package numbiosis.backend;

public class DecimalToBinary {

    private static final int DECIMAL_DIGITS = 32;

    public static String convert(double value) {

        String binary = new String();
        boolean negative = false;

        if (value < 0) {
            value *= -1;
            negative = true;
        }

        int integer_part = (int)value;
        double fractional_part = value - integer_part;

        binary = int_to_binary(integer_part) + "." + fractional_to_binary(fractional_part);

        if (negative) binary = "-" + binary;
        return binary;
    }

    private static String int_to_binary(int value) {

        if (value == 0)
            return "0";

        StringBuilder result = new StringBuilder();

        while (value > 0) {
            result.append(Integer.toString(value % 2));
            value /= 2;
        }

        return result.reverse().toString();
    }

    private static String fractional_to_binary(double value) {

        if (value == 0)
            return "0";

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < DECIMAL_DIGITS; i++) {
            value *= 2;
            if (value < 1) {
                result.append("0");
            } else {
                result.append("1");
                value--;
            }

            if (value == 0)
                break;
        }

        return result.toString();
    }
}
