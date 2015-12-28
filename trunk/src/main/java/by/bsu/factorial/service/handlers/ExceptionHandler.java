package by.bsu.factorial.service.handlers;

import by.bsu.factorial.service.FactorialContext;
import by.bsu.factorial.service.HandlerException;
import by.bsu.factorial.service.Handler;

/**
 * @author: Sidorovich Vladislav
 * Date: 31.10.11
 */
public class ExceptionHandler implements Handler{
    private Handler tryHandler;

    public void handle(FactorialContext context) throws HandlerException {
        try{
            tryHandler.handle(context);
        } catch (Exception e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }

    public void setTryHandler(Handler tryHandler) {
        this.tryHandler = tryHandler;
    }
}
