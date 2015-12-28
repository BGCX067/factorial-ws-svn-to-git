package by.bsu.factorial.service.handlers;

import by.bsu.factorial.service.FactorialContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author: Sidorovich Vladislav
 * Date: 27.10.11
 */
public class CalculateHandlerTest {
    private CalculateHandler calculateHandler;

    @Mock
    private FactorialContext context;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        calculateHandler = new CalculateHandler();
    }

    @Test
    public void testCalculate() throws Exception {
        setNToContext(3);
        calculateHandler.handle(context);
        verify(context).put(FactorialContext.Param.RESULT, BigInteger.valueOf(6));

        setNToContext(0);
        calculateHandler.handle(context);
        verify(context).put(FactorialContext.Param.RESULT, BigInteger.valueOf(1));

        setNToContext(4);
        calculateHandler.handle(context);
        verify(context).put(FactorialContext.Param.RESULT, BigInteger.valueOf(24));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotValidN() throws Exception {
        setNToContext(-2);
        calculateHandler.handle(context);
    }

    private void setNToContext(int n) {
        when(context.get(FactorialContext.Param.N)).thenReturn(n);
    }
}
