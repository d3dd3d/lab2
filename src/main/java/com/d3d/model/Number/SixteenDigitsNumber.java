package com.d3d.model.Number;

import java.util.regex.Pattern;

public class SixteenDigitsNumber extends Number {
    public SixteenDigitsNumber(String num) throws NumberFormatException{
        this.number = parseToInt(num);
        pat = Pattern.compile("[g-zG-Z]+");
    }
    @Override
    public String getString(){
        return Integer.toHexString(number);
    }
    @Override
    protected int parseToInt(String num) throws NumberFormatException{
        return Integer.parseInt(num,16);
    }
}
