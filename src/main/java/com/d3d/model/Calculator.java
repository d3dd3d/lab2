package com.d3d.model;

import java.security.InvalidParameterException;

import com.d3d.model.Number.EightDigitsNumber;
import com.d3d.model.Number.Number;
import com.d3d.model.Number.SixteenDigitsNumber;
import com.d3d.model.Number.TenDigitsNumber;
import com.d3d.model.Number.TwoDigitsNumber;

public class Calculator {
    public static int add(Number firstOperand, Number secondOperand) {
        return firstOperand.getNumber() + secondOperand.getNumber();
    }

    public static int diff(Number firstOperand, Number secondOperand) {
        return firstOperand.getNumber() - secondOperand.getNumber();
    }

    public static int multiply(Number firstOperand, Number secondOperand) {
        return firstOperand.getNumber() * secondOperand.getNumber();
    }

    public static int divide(Number firstOperand, Number secondOperand) throws InvalidParameterException {
        if (secondOperand.getNumber() == 0)
            throw new InvalidParameterException("Деление на ноль!");

        return firstOperand.getNumber() / secondOperand.getNumber();
    }
    public static Number create(String operand, String digit) throws Exception{
        int digitInt = Integer.parseInt(digit);
        switch (digitInt) {
            case 2:{
                return new TwoDigitsNumber(operand);
            }
            case 8:{
                return new EightDigitsNumber(operand);
            }
            case 10:{
                return new TenDigitsNumber(operand);
            }
            case 16:{
                return new SixteenDigitsNumber(operand);
            }   
            default:
                throw new Exception();
        }

    }
}
