package by.bsu.factorial.service.handlers;

import by.bsu.factorial.service.FactorialContext;
import by.bsu.factorial.service.HandlerException;
import by.bsu.factorial.service.Handler;

import java.util.Set;

/**
 * @author: Sidorovich Vladislav
 * Date: 27.10.11
 */
public class SecurityHandler implements Handler{
    private static final String SECURITY_EXCEPTION = "%s ip address is not available.";

    private Set<String> availableIps;

    public void handle(FactorialContext context) throws HandlerException {
        String currentIp = context.get(FactorialContext.Param.IP).toString();
        if (!availableIps.contains(currentIp)) {
            throw new HandlerException(String.format(SECURITY_EXCEPTION, currentIp));
        }
    }

    public void setAvailableIps(Set<String> availableIps) {
        this.availableIps = availableIps;
    }
}
