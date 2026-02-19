package org.tessera_lang.interpreter;

public class InterpreterValueInt extends InterpreterValue {
    private int value;

    public InterpreterValueInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static InterpreterValueInt add(InterpreterValueInt a, InterpreterValueInt b){
        return new InterpreterValueInt(a.value + b.value);
    }

    public static InterpreterValueInt subtract(InterpreterValueInt a, InterpreterValueInt b){
        return new InterpreterValueInt(a.value - b.value);
    }

    public static InterpreterValueInt multiply(InterpreterValueInt a, InterpreterValueInt b){
        return new InterpreterValueInt(a.value * b.value);
    }

    public static InterpreterValueInt divide(InterpreterValueInt a, InterpreterValueInt b){
        return new InterpreterValueInt(a.value / b.value);
    }

    public static InterpreterValueInt modulus(InterpreterValueInt a, InterpreterValueInt b){
        return new InterpreterValueInt(a.value % b.value);
    }

    public static InterpreterValueInt power(InterpreterValueInt a, InterpreterValueInt b){
        return new InterpreterValueInt((int) Math.pow(a.value, b.value));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
