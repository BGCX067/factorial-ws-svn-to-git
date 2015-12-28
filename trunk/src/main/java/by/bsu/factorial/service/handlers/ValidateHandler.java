package by.bsu.factorial.service.handlers;

import by.bsu.factorial.service.FactorialContext;
import by.bsu.factorial.service.HandlerException;
import by.bsu.factorial.service.Handler;

/**
 * @author: Sidorovich Vladislav
 * Date: 26.10.11
 */
public class ValidateHandler implements Handler{
    private static final String VALIDATION_EXCEPTION = "N equals %s is not valid.";

    public void handle(FactorialContext context) throws HandlerException {
        int n = Integer.parseInt(String.valueOf(context.get(FactorialContext.Param.N)));
        if (!isValid(n)) {
            throw new HandlerException(String.format(VALIDATION_EXCEPTION, n));
        }
    }

    private boolean isValid(int n) {
        return n >= 0;   //some other rule
    }
}
