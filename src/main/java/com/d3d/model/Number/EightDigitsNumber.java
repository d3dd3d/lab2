package com.d3d.model.Number;

import java.util.regex.Pattern;

public class EightDigitsNumber extends Number {
    public EightDigitsNumber(String num) throws NumberFormatException{
        this.number = parseToInt(num);
        pat = Pattern.compile("[8-9]+||[a-zA-Z]+");
    }
    @Override
    public String getString(){
        return Integer.toOctalString(number);
    }
    @Override
    protected int parseToInt(String num) throws NumberFormatException{
        return Integer.parseInt(num,8);
    }
}
