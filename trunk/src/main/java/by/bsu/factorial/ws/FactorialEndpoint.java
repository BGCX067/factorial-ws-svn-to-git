package by.bsu.factorial.ws;

import by.bsu.factorial.service.FactorialContext;
import by.bsu.factorial.service.HandlerException;
import by.bsu.factorial.service.Handler;
import org.jdom.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.*;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

/**
 * @author: Sidorovich Vladislav
 * Date: 26.10.11
 */
@Endpoint
public class FactorialEndpoint {
    private static final String NAMESPACE_URI = "http://factorialWs.bsu.by/schema";
    private static final String NAMESPACE = "fc";

    private final ResponseGenerator responseGenerator;

    @Autowired
    @Qualifier(value = "exceptionHandler")
    private Handler startHandler;

    @Autowired
    public FactorialEndpoint(ResponseGenerator responseGenerator) {
        this.responseGenerator = responseGenerator;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "FactorialRequest")
    @ResponsePayload
    @Namespace(prefix = NAMESPACE, uri = NAMESPACE_URI)
    public Element handleFactorialRequest(@XPathParam("//fc:N") int value) throws Exception {
        FactorialContext context = new FactorialContext();

        context.put(FactorialContext.Param.N, value);
        context.put(FactorialContext.Param.IP, getRequestIp());

        try{
            startHandler.handle(context);
        } catch (HandlerException e) {
            return responseGenerator.generateFailureResponse(e.getMessage());
        }

        if (context.containsParam(FactorialContext.Param.RESULT)) {
            return responseGenerator.generateSuccessResponse((BigInteger) context.get(FactorialContext.Param.RESULT));
        } else {
            return responseGenerator.generateFailureResponse();
        }
    }

    private String getRequestIp() {
        TransportContext context = TransportContextHolder.getTransportContext();
        HttpServletConnection connection = (HttpServletConnection) context.getConnection();
        HttpServletRequest request = connection.getHttpServletRequest();

        return request.getRemoteAddr();
    }

}
