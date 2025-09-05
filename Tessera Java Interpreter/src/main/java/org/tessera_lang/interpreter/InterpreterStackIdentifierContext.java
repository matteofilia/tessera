package org.tessera_lang.interpreter;

import java.util.HashMap;

public class InterpreterStackIdentifierContext {
    InterpreterStackIdentifierContext above = null;
    InterpreterStackIdentifierContext below = null;

    HashMap identifiers = new HashMap();

    /**
     * Checks ALL context instances at or below this one in the stack to see if identifier exists
     * @param identifier
     * @return
     */
    public boolean has(String identifier) {
        InterpreterStackIdentifierContext context = this;
        while (context != null) {
            if (context.identifiers.containsKey(identifier)) {
                return true;
            }

            context = context.below;
        }

        return false;
    }

    /**
     * Checks ALL context instances at or below this one in the stack to see if identifier exists
     * @param identifier
     * @return
     */
    public InterpreterValue get(String identifier) {
        InterpreterStackIdentifierContext context = this;
        while (context != null) {
            if (context.identifiers.containsKey(identifier)) {
                return (InterpreterValue) context.identifiers.get(identifier);
            }

            context = context.below;
        }

        return null;
    }

    /**
     * Puts a variable in the current context instance
     * @param identifier
     * @param value
     */
    public void put(String identifier, InterpreterValue value) {
        identifiers.put(identifier, value);
    }

    public boolean isRoot() {
        return below == null;
    }

    public InterpreterStackIdentifierContext getAbove() {
        return above;
    }

    public void setAbove(InterpreterStackIdentifierContext above) {
        this.above = above;
    }

    public InterpreterStackIdentifierContext getBelow() {
        return below;
    }

    public void setBelow(InterpreterStackIdentifierContext below) {
        this.below = below;
    }

    /**
     * Adds a context to the context stack
     * @param newContext
     */
    public void addToStack(InterpreterStackIdentifierContext newContext) {
        this.above = newContext;
        newContext.below = this;
    }

    private InterpreterStackIdentifierContext findTopRecursive(InterpreterStackIdentifierContext any) {
        if (any.above != null) {
            return findTopRecursive(any.above);
        } else {
            return this;
        }
    }

    /**
     * Removes a context from the context stack
     */
    public void popFromStack() {
        InterpreterStackIdentifierContext context = findTopRecursive(this);

        // Remove the context
        context.above.below = null;
        context.above = null;
    }

}
