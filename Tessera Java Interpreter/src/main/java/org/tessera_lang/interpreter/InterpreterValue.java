package org.tessera_lang.interpreter;

public class InterpreterValue {



    public static InterpreterValue parse(String rawValue) {
        // TODO: fix this, terrible code

        try {
            int intVal = Integer.parseInt(rawValue);
            return new InterpreterValueInt(intVal);
        } catch (NumberFormatException e) {
            // Do Nothing
        }

        try {
            float floatVal = Float.parseFloat(rawValue);
            return new InterpreterValueFloat(floatVal);
        } catch (NumberFormatException e) {
            // Do Nothing
        }

        return null;
    }

    // Base Class
    public String toString() {
        return null;
    }
}
