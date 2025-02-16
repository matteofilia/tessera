package org.tessera_lang.interpreter;

public class InterpreterValueFloat extends InterpreterValue {
    private float value;

    public InterpreterValueFloat(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
