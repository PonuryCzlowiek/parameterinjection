package org.ametyst.inject;

import org.ametyst.Customer;
import org.ametyst.CustomerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class PathCustomerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private CustomerProvider customerProvider;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(PathCustomer.class)
               && Customer.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Customer resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String paramName = methodParameter.getParameterAnnotation(PathCustomer.class).value();
        String paramValue = getParameterValue(nativeWebRequest, paramName);
        Integer id = Integer.valueOf(paramValue);

        if (id <= 0) {
            throw new IllegalStateException(String.format("ID cannot be negative! Given %s", id));
        }

        return customerProvider.getCustomerById(id);
    }

    @SuppressWarnings("unchecked")
    private String getParameterValue(NativeWebRequest webRequest, String primaryKeyName) {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Map<String, String> pathVariables = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        String primaryKeyValue = pathVariables.get(primaryKeyName);
        if (StringUtils.isEmpty(primaryKeyValue)) {
            throw new IllegalStateException(String.format("The path variable %s cannot be resolved.", primaryKeyName));
        }

        return primaryKeyValue;
    }
}
