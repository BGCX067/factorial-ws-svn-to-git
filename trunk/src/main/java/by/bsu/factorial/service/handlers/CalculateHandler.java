package by.bsu.factorial.service.handlers;

import by.bsu.factorial.service.FactorialContext;
import by.bsu.factorial.service.HandlerException;
import by.bsu.factorial.service.Handler;
import org.springframework.util.Assert;

import java.math.BigInteger;

/**
 * @author: Sidorovich Vladislav
 * Date: 27.10.11
 */
public class CalculateHandler implements Handler{
    public void handle(FactorialContext context) throws HandlerException {
        int n = Integer.parseInt(String.valueOf(context.get(FactorialContext.Param.N)));
        context.put(FactorialContext.Param.RESULT, calculateFactorial(n));
    }

    private BigInteger calculateFactorial(int n) {
        Assert.isTrue(n >= 0, "Factorial can be calculated only for non-negative integer.");
        BigInteger result = BigInteger.ONE;
        for (int i=1; i<=n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
