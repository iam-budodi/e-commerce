package com.commerce.cdbookstore.util;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Logger;

@Loggable
@Interceptor
public class LoggingInterceptor implements Serializable {

	// ======================================
	// = Injection Points =
	// ======================================

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Logger logger;

	// ======================================
	// = Business methods =
	// ======================================

	@AroundInvoke
	private Object intercept(InvocationContext ic) throws Exception {
		logger.info(ic.getTarget().getClass().getName() + "."
						+ ic.getMethod().getName());
		try {
			return ic.proceed();
		} finally {
			logger.fine(ic.getTarget().getClass().getName() + "."
							+ ic.getMethod().getName());
		}
	}
}