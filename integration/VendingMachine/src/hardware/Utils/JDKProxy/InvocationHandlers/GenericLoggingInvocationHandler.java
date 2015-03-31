package hardware.Utils.JDKProxy.InvocationHandlers;

/**
 * GenericLoggingInvocationHandler
 * 
 * @synopsis
 * 		JDK related invocation handler that deals with logging specified method calls
 * 
 * @author 			wwright@ucalgary.ca
 * 
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Timestamp;

/**
 * GenericLoggingInvocationHandler<T>.
 *
 * @param 			<T> 		generic object
 * @see 						getGenericLoggingProxy
 * 
 * @author			wwright@ucalgary.ca
 */
public class GenericLoggingInvocationHandler<T> implements InvocationHandler {
	private final T underlying;

	/**
	 * GenericLoggingInvocationHandler(final T underlying)
	 * 
	 * @synopsis
	 * 		<Constructor> that saves the underlying object to which the proxy corresponds; 
	 * 		"instatiates" new logging invocation handler.
	 * 
	 * @param 		underlying 			generic underlying object to be proxied
	 * 
	 * @author		wwright@ucalgary.ca
	 */
	public GenericLoggingInvocationHandler(final T underlying) {
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
		 java.util.Date date= new java.util.Date();
		// TODO: LogManager integration
		// TODO: Implement more elegant approach to log creation/formatting
		StringBuffer sb = new StringBuffer();
		sb.append("["); sb.append(new Timestamp(date.getTime())); sb.append("] " + method.getName());  sb.append("(");
		for (int i=0; args != null && i<args.length; i++) {
			if (i != 0)
				sb.append(", ");
			sb.append(args[i]);
		}
		sb.append(")");
		Object ret = method.invoke(underlying, args);
		if (ret != null) {
			sb.append(" -> "); sb.append(ret);
		}
		System.out.println(sb);
		return ret;
	}
}