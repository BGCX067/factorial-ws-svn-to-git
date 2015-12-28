package by.bsu.factorial.service.handlers;

import by.bsu.factorial.service.FactorialWSCache;
import by.bsu.factorial.service.FactorialContext;
import by.bsu.factorial.service.HandlerException;
import by.bsu.factorial.service.Handler;

import java.math.BigInteger;

/**
 * @author: Sidorovich Vladislav
 * Date: 27.10.11
 */
public class CacheHandler implements Handler{
    private Handler nextHandler;

    public void handle(FactorialContext context) throws HandlerException {
        int n = Integer.parseInt(String.valueOf(context.get(FactorialContext.Param.N)));
        String ip = context.get(FactorialContext.Param.IP).toString();

        if (!FactorialWSCache.getInstance().isCached(n, ip)) {
            nextHandler.handle(context);
            if (context.containsParam(FactorialContext.Param.RESULT)) {
                BigInteger result = (BigInteger) context.get(FactorialContext.Param.RESULT);
                FactorialWSCache.getInstance().storedInCache(n, ip, result);
            }
        } else {
            context.put(FactorialContext.Param.RESULT, FactorialWSCache.getInstance().get(n, ip));
        }
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
