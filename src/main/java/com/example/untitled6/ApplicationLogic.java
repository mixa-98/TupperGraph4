package com.example.untitled6;

import javafx.util.Pair;

import java.util.Objects;

public class ApplicationLogic {
    private final boolean[] canvas;

    public ApplicationLogic() {
        this.canvas = new boolean[1802];
        for (boolean b : canvas) {
            b = false;
        }
    }

    protected void paint(int x, int y, boolean value) {
        canvas[x * 17 + (16 - y)] = value;
    }

    protected String reverseString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            stringBuilder.append(str.charAt(i));
        }
        return stringBuilder.toString();
    }

    protected int charPlusCharToInt(char a, char b) {
        return (a - '0') + (b - '0');
    }

    protected String strPlusStr(String a, String b) {
        a = reverseString(a);
        b = reverseString(b);
        int buffer = 0;
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        int app;
        for (; i < Math.min(a.length(), b.length()); i++) {
            app = charPlusCharToInt(a.charAt(i), b.charAt(i)) + buffer;
            buffer = app / 10;
            stringBuilder.append(app % 10);
        }
        a = a.length() > b.length() ? a : b;
        while (buffer > 0 && i < a.length()) {
            app = a.charAt(i) - '0' + buffer;
            buffer = app / 10;
            stringBuilder.append(app % 10);
            i++;
        }
        if (buffer > 0) {
            stringBuilder.append(buffer);
            return reverseString(stringBuilder.toString());
        }
        stringBuilder.append(a.substring(i));
        return reverseString(stringBuilder.toString());
    }

    protected String multiplyStringBy2(String a) {
        a = reverseString(a);
        int buffer = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            int app = (a.charAt(i) - '0') * 2 + buffer;
            buffer = app / 10;
            stringBuilder.append(app % 10);
        }
        if (buffer > 0) stringBuilder.append(buffer);
        return reverseString(stringBuilder.toString());
    }

    protected String multiplyStringByInt(String a) {
        a = reverseString(a);
        int buffer = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            int app = (a.charAt(i) - '0') * 17 + buffer;
            buffer = app / 10;
            stringBuilder.append(app % 10);
        }
        while (buffer > 0) {
            stringBuilder.append(buffer % 10);
            buffer /= 10;
        }
        return reverseString(stringBuilder.toString());
    }

    protected Pair<String, Boolean> divideStringBy2(String a) {
        if(a.length() == 1) return new Pair<>("" + (a.charAt(0) - '0') / 2, (a.charAt(0) - '0') % 2 == 1);
        StringBuilder stringBuilder = new StringBuilder();
        int lastChar = a.charAt(a.length() - 1) - '0';
        Boolean remainder = lastChar % 2 == 1;
        int i = 0;
        int buffer = a.charAt(0) == '1' ? a.charAt(i++) - '0' : 0;
        for (; i < a.length(); i++) {
            buffer = buffer * 10 + (a.charAt(i) - '0');
            stringBuilder.append(buffer / 2);
            buffer %= 2;
        }
        return new Pair<>(stringBuilder.toString(), remainder);
    }

    protected String divideStringBy17(String a) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        int buffer = a.charAt(0) == '1' && a.charAt(1) - '0' < 7? (a.charAt(i++) - '0') * 10 + a.charAt(i++) - '0': a.charAt(i++) - '0';
        for (; i < a.length(); i++) {
            buffer = buffer * 10 + (a.charAt(i) - '0');
            stringBuilder.append(buffer / 17);
            buffer %= 17;
        }
        return stringBuilder.toString();
    }

    public String graphToX() {
        String str = "0";
        String multiplier = "1";
        for (boolean b : canvas) {
            if (b) str = strPlusStr(str, multiplier);
            multiplier = multiplyStringBy2(multiplier);
        }
        return multiplyStringByInt(str);
    }

    public boolean[] xToGraph(String input) {
        input = divideStringBy17(input);
        boolean[] result = new boolean[1802];
        int i = 0;
        for (; !Objects.equals(input, "1"); i++) {
            Pair<String, Boolean> p = divideStringBy2(input);
            result[1801 - i] = p.getValue();
            input = p.getKey();
        }
        result[1801 - i] = true;
        return result;
    }
}
