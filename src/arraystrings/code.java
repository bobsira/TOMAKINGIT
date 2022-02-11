package arraystrings;

public class code {

    static String uncompress(String s){
        int i = 0, j = 0;
        StringBuilder result = new StringBuilder();
        while (j < s.length()) {
            if (Character.isDigit(s.charAt(j))) {
                j++;
            } else {
                int number = Integer.parseInt(s.substring(i, j));
                for (int index = 0; index < number; index++){
                    result.append(s.charAt(j));
                }
                j++;
                i = j;
            }
        }
        return result.toString();
    }

    static String compress(String s){
        int i = 0, j = 0;
        StringBuilder result = new StringBuilder();
        while (j < s.length()){
            if (s.charAt(i) == s.charAt(j)){
                j++;
            } else {
                int number = j - i;
                if (number == 1) result.append(s.charAt(i));
                else result.append(number).append(s.charAt(i));
                i = j;
            }
        }
        return result.toString();
    }
}
