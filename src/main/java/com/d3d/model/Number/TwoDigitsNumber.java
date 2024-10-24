package com.d3d.model.Number;

import java.util.regex.Pattern;

public class TwoDigitsNumber extends Number {
    public TwoDigitsNumber(String num) throws NumberFormatException{
        this.number = parseToInt(num);
        pat = Pattern.compile("[2-9]+||[a-zA-Z]+");
    }
    @Override
    public String getString(){
        return Integer.toBinaryString(number);
    }
    @Override
    protected int parseToInt(String num) throws NumberFormatException{
        return Integer.parseInt(num,2);
    }
}
