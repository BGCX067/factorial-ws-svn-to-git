package by.bsu.factorial.ws;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jdom.Namespace;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

 /**
  * @author: Sidorovich Vladislav
  * Date: 27.10.11
  */
@Component
public class ResponseGenerator {

    public Element generateFailureResponse() {
        return generateFailureResponse(ResponseStatus.FAILURE.getMessage());
    }

    public Element generateFailureResponse(String errorMessage) {
        return generateResponse(ResponseStatus.FAILURE, errorMessage, null);
    }

    public Element generateSuccessResponse(BigInteger resultValue) {
        return generateResponse(ResponseStatus.SUCCESS, null, resultValue);
    }

    private Element generateResponse(ResponseStatus status, String customMessage, BigInteger resultValue) {
        Namespace fcNamespace = Namespace.getNamespace("fc", "http://factorialWs.bsu.by/schema");
        Element responseRoot = new Element("FactorialResponse", fcNamespace);

        buildResultValue(responseRoot, fcNamespace, resultValue);
        buildStatusGroup(responseRoot, fcNamespace, status, customMessage);

        return responseRoot;
    }

    private void buildResultValue(Element parent, Namespace namespace, BigInteger resultValue) {
        if (resultValue != null) {
            Element resultValueElement = new Element("ResultValue", namespace);
            resultValueElement.setText(resultValue.toString());

            parent.addContent(resultValueElement);
        }
    }

    private void buildStatusGroup(Element parent, Namespace namespace, ResponseStatus responseStatus, String customMessage) {
        Element statusElement = new Element("Status", namespace);
        statusElement.setText(responseStatus.toString());

        Element statusCodeElement = new Element("StatusCode", namespace);
        statusCodeElement.setText(responseStatus.getCode());

        Element messageElement = new Element("Message", namespace);
        messageElement.setText(StringUtils.isEmpty(customMessage) ? responseStatus.getMessage() : customMessage);

        Element statusGroupElement = new Element("StatusGroup", namespace);
        statusGroupElement.addContent(statusElement);
        statusGroupElement.addContent(statusCodeElement);
        statusGroupElement.addContent(messageElement);

        parent.addContent(statusGroupElement);
    }

    private enum ResponseStatus {
        SUCCESS("Factorial calculation is successful.", "001"),
        FAILURE("Can't calculate factorial.", "002");

        private String message;
        private String code;

        private ResponseStatus(String message, String code) {
            this.message = message;
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }
    }
 }
