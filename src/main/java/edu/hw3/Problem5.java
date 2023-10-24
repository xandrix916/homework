package edu.hw3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem5 {
    public static final String ASCENDING_WAY = "ASC";
    public static final String DESCENDING_WAY = "DESC";

    private static final String EXCEPTION_HANDLING = "Given string %s doesn't fit to \"name surname\" template";

    public ArrayList<String> parseContacts(ArrayList<String> contactList, String sortWay) {
        try {
            contactList.sort(new SurnameComparator(sortWay));
            return contactList;
        }  catch (IllegalArgumentException illegalArgumentException) {
            return new ArrayList<>(List.of(illegalArgumentException.getCause().getMessage()));
        } catch (NullPointerException nullPointerException) {
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("all")
    private boolean fitsToTemplate(String checkedData) {
        if (checkedData.length() - checkedData.replace(" ","").length() != 1)
            return false;
        for (var c: checkedData.toCharArray()) {
            if (!(Character.isLetter(c) || c == ' ')) {
                return false;
            }
        }
        return true;
    }

    private class SurnameComparator implements Comparator<String> {
        private final int order;

        SurnameComparator(String sortWay) {
            order = (sortWay.equalsIgnoreCase(DESCENDING_WAY) ? -1 : 1);
        }

        @Override
        public int compare(String o1, String o2) throws IllegalArgumentException {
            if (!fitsToTemplate(o1)) {
                throw new IllegalArgumentException(new Throwable(EXCEPTION_HANDLING.formatted(o1)));
            }
            if (!fitsToTemplate(o2)) {
                throw new IllegalArgumentException(new Throwable(EXCEPTION_HANDLING.formatted(o2)));
            }
            String firstSurname = o1.substring(o1.indexOf(' ') + 1);
            String secondSurname = o2.substring(o2.indexOf(' ') + 1);
            return order * ((int) Character.toUpperCase(firstSurname.charAt(0))
                - (int) Character.toUpperCase(secondSurname.charAt(0)));
        }
    }

}
