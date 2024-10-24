package com.d3d.model.Number;

import java.util.regex.Pattern;

public class TenDigitsNumber extends Number {
    public TenDigitsNumber(String num) throws NumberFormatException{
        this.number = parseToInt(num);
        pat = Pattern.compile("[a-zA-Z]+");
    }
    @Override
    public String getString(){
        return Integer.toString(number);
    }
    @Override
    protected int parseToInt(String num) throws NumberFormatException{
        return Integer.parseInt(num, 10);
    }
}
