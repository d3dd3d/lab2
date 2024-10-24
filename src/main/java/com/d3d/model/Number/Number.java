package com.d3d.model.Number;

import java.util.regex.Pattern;

public abstract class Number {
    protected int number = 0;
    protected Pattern pat;
    public abstract String getString();
    protected abstract int parseToInt(String num);
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
