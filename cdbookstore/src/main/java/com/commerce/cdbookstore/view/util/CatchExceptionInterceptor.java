package com.commerce.cdbookstore.view.util;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@CatchException
@Interceptor
public class CatchExceptionInterceptor {

	// ======================================
	// = Injection Points =
	// ======================================
//
//    @Inject
//    private Logger logger;

	@Inject
	private FacesContext facesContext;

	// ======================================
	// = Business methods =
	// ======================================

	@AroundInvoke
	private Object intercept(InvocationContext ic) throws Exception {
		try {
			return ic.proceed();
		} catch (Throwable e) {
			facesContext.addMessage(null, new FacesMessage(SEVERITY_ERROR,
							e.getMessage(), null));
			return null;
		}
	}
}