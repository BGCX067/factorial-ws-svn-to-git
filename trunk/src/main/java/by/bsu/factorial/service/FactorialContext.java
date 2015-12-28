package by.bsu.factorial.service;

import java.util.HashMap;

/**
 * @author: Sidorovich Vladislav
 * Date: 26.10.11
 */
public class FactorialContext {
    private static final String CONTAINS_EXCEPTION = "Context already contains param %s.";
    private static final String NOT_CONTAINS_EXCEPTION = "Context not contains param %s.";

    private final HashMap<Param, Object> context = new HashMap<Param, Object>();

    /**
     * Add parma to context
     * @throws <code>IllegalArgumentException</code>if param already exist in context.
     */
    public void put(Param param, Object value) {
        if (context.containsKey(param)) {
            throw new IllegalArgumentException(String.format(CONTAINS_EXCEPTION, param));
        }
        putOrReplace(param, value);
    }

    /**
     * Add or replace param in context
     */
    public void putOrReplace(Param param, Object value) {
        context.put(param, value);
    }

    public boolean containsParam(Param param) {
        return context.containsKey(param);
    }

    public Object get(Param param) {
        if (!context.containsKey(param)) {
            throw new IllegalArgumentException(String.format(NOT_CONTAINS_EXCEPTION, param));
        }
        return context.get(param);
    }

    /**
     * Remove param from context
     */
    public void removeParam(Param param) {
        context.remove(param);
    }

    public static enum Param {
        N, RESULT, IP
    }
}
