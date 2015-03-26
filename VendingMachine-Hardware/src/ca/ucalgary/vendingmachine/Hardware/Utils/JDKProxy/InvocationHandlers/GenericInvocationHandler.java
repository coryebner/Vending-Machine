package ca.ucalgary.vendingmachine.Hardware.Utils.JDKProxy.InvocationHandlers;

/**
 * GenericInvocationHandler
 * 
 * @synopsis
 * 		JDK related invocation handler that deals forwarding all method calls to original 
 * 		instance 'methods'.
 * 
 * @author 			wwright@ucalgary.ca
 * 
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * GenericInvocationHandler<T>.
 *
 * @param 			<T> 		generic object
 * @see 						getGenericLoggingProxy
 * 
 * @author			wwright@ucalgary.ca
 */
public class GenericInvocationHandler<T> implements InvocationHandler {
	private final T underlying;

	/**
	 * GenericInvocationHandler(final T underlying)
	 * 
	 * @synopsis
	 * 		<Constructor> that saves the underlying object to which the proxy corresponds; 
	 * 		"instatiates" new generic invocation handler.
	 * 
	 * @param 		underlying 			generic underlying object to be proxied
	 * 
	 * @author		wwright@ucalgary.ca
	 */
	public GenericInvocationHandler(final T underlying) {
		this.underlying = underlying;
	}

	/**
	 * invoke
	 * 
	 * @synopsis
	 * 		"Proxified" method interception that specifies the proxy object, requested/invoked
	 * 		method and related arguments.
	 *
	 * @param 		proxy 				the proxy
	 * @param 		method 				the method
	 * @param 		args 				the args
	 * @return 							the object
	 * @throws 		Throwable 			the throwable
	 * @see 		java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 * 
	 * @author 		wwright@ucalgary.ca
	 */
			
	public Object invoke(Object proxy, Method method, 
			Object[] args) throws Throwable {
		Object ret = method.invoke(underlying, args);
		return ret;
	}
}