package kz.fintech.helpers;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.pool.impl.GenericObjectPool;

import java.util.function.BiFunction;
import java.util.function.Function;

public class SoapUtils {

    @SneakyThrows
    public static <TResult, TSoap> TResult invokeSoapService(GenericObjectPool<TSoap> soap,
                                                             Function<TSoap, TResult> function) {
        val port = soap.borrowObject();
        try {
            val result = function.apply(port);
            soap.returnObject(port);
            return result;
        } catch (Throwable e) {
            soap.invalidateObject(port);
            throw e;
        }
    }

    @SneakyThrows
    public static <TResult, TRequest, TSoap> TResult invokeSoapService(GenericObjectPool<TSoap> soap,
                                                                       BiFunction<TSoap, TRequest, TResult> function,
                                                                       TRequest request) {
        val port = soap.borrowObject();
        try {
            val result = function.apply(port, request);
            soap.returnObject(port);
            return result;
        } catch (Throwable e) {
            soap.invalidateObject(port);
            throw e;
        }
    }
}
