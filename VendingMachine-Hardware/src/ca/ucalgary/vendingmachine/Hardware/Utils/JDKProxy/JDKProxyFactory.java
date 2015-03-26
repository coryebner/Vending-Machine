package ca.ucalgary.vendingmachine.Hardware.Utils.JDKProxy;

/**
 * 
 * JDKProxyFactory
 * 
 * Abstract generic wrapper class that allows instantiation of a dynamic proxy through 
 * native JDK.  Extendible invocation handlers are contained here-in.
 *
 *
 *	Invocation handlers are custom tailored callback subroutines.
 *
 *@author wwright@ucalgary.ca
 *
 */

import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import ca.ucalgary.vendingmachine.Hardware.Utils.JDKProxy.InvocationHandlers.GenericInvocationHandler;
import ca.ucalgary.vendingmachine.Hardware.Utils.JDKProxy.InvocationHandlers.GenericLoggingInvocationHandler;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class JDKProxyFactory {

	/**
	 * main
	 *  
	 * @synopsis
	 * 	Included strictly for demonostration purposes
	 *
	 * @param 		args 		the arguments
	 * 
	 * @author 		wwright@ucalgary.ca
	 */
	
	public static void main(String[] args) {    
		System.out.println("\n\nmain demo: Set proxy with Standard Out logging\n");
		Set s = getGenericLoggingProxy(Set.class, new HashSet<Object>());
		s.add("uno");
		if (!s.contains("four"))
			s.add("four");
		System.out.println(s);

	}

	/**
	 * getGenericLoggingProxy
	 * 
	 * Example usage:
	 * 	<Interface> s = getGenericLoggingProxy(<Interface>.class, Object (implements <Interface>);
	 *
	 * @param 		<T> 		generic type
	 * @param		class1		"class" to proxy (interface)
	 * @param		obj		 	object instance which implements interface (object being proxied)
	 * @return 		proxy 		object of same type (class1 = return.class)
	 * 
	 * @author 		wwright@ucalgary.ca
	 */
	
	public static<T> T getGenericLoggingProxy(Class<T> class1, final T obj) {
		return (T) 
				Proxy.newProxyInstance(obj.getClass().getClassLoader(),
						new Class[] { class1 },
						new GenericLoggingInvocationHandler<T>(obj));
	}

	/**
	 * getGenericProxy
	 * 
	 * @param 				class1  .class 
	 * @param 				obj		instance of class1
	 * @return				T		a proxy to the incoming generic typed object with the invocation handler
	 * 								set to filter through GenericInvocationHandler.
	 */
	public static<T> T getGenericProxy(Class<T> class1, final T obj) {
		return (T) 
				Proxy.newProxyInstance(obj.getClass().getClassLoader(),
						new Class[] { class1 },
						new GenericInvocationHandler<T>(obj));
	}

}
