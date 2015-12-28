package by.bsu.factorial.service;

/**
 * @author: Sidorovich Vladislav
 * Date: 26.10.11
 */
public interface Handler {
    void handle(FactorialContext context) throws HandlerException;
}
