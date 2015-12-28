package by.bsu.factorial.service.handlers;

import by.bsu.factorial.service.FactorialContext;
import by.bsu.factorial.service.HandlerException;
import by.bsu.factorial.service.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Sidorovich Vladislav
 * Date: 26.10.11
 */
public class GroupHandler implements Handler {
    private List<Handler> handlers;

    public void handle(FactorialContext context) throws HandlerException {
        for (Handler h : handlers) {
            h.handle(context);
        }
    }

    public void setHandlers(List<Handler> handlers) {
        this.handlers = handlers;
    }

    public void addHandlers(Handler... handlers) {
        if (this.handlers == null) {
            this.handlers = new ArrayList<Handler>(handlers.length);
        }
        this.handlers.addAll(Arrays.asList(handlers));
    }
}
