/*
 * Copyright 2019 VMware, Inc.
 * SPDX-License-Identifier: EPL-2.0
 */
package com.vmware.vip.core.validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerMapping;

import com.vmware.vip.api.rest.APIParamName;
import com.vmware.vip.common.constants.ConstantsKeys;
import com.vmware.vip.common.utils.RegExpValidatorUtils;

public class ParameterValidation implements IVlidation {
	HttpServletRequest request = null;

	public ParameterValidation(HttpServletRequest request) {
		this.request = request;
	}

	public void validate() throws ValidationException {
		if (this.request == null) {
			return;
		}
		validateProductname(request);
		validateVersion(request);
		validateComponent(request);
		validateKey(request);
		validateLocale(request);
		validateSourceformat(request);
		validateCollectsource(request);
		validatePseudo(request);
		validatePattern(request);
		validateNumber(request);
		validateScale(request);
	}

	@SuppressWarnings("unchecked")
	private void validateProductname(HttpServletRequest request)
			throws ValidationException {
		Map<String, String> pathVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String productName = pathVariables.get(APIParamName.PRODUCT_NAME) == null ? request
				.getParameter(APIParamName.PRODUCT_NAME) : pathVariables
				.get(APIParamName.PRODUCT_NAME);
		if (StringUtils.isEmpty(productName)) {
			return;
		}
		if (!RegExpValidatorUtils.IsLetterOrNumber(productName)) {
			throw new ValidationException(ValidationMsg.PRODUCTNAME_NOT_VALIDE);
		}
	}

	@SuppressWarnings("unchecked")
	private void validateVersion(HttpServletRequest request)
			throws ValidationException {
		Map<String, String> pathVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String version = pathVariables.get(APIParamName.VERSION) == null ? request
				.getParameter(APIParamName.VERSION) : pathVariables
				.get(APIParamName.VERSION);
		if (StringUtils.isEmpty(version)) {
			return;
		}
		if (!RegExpValidatorUtils.IsNumberAndDot(version)) {
			throw new ValidationException(ValidationMsg.VERSION_NOT_VALIDE);
		}
	}

	@SuppressWarnings("unchecked")
	private void validateComponent(HttpServletRequest request)
			throws ValidationException {
		Map<String, String> pathVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String component = pathVariables.get(APIParamName.COMPONENT) == null ? request
				.getParameter(APIParamName.COMPONENT) : pathVariables
				.get(APIParamName.COMPONENT);
		if (StringUtils.isEmpty(component)) {
			return;
		}
		if (!RegExpValidatorUtils.IsLetterAndNumberAndValidchar(component)) {
			throw new ValidationException(ValidationMsg.COMPONENT_NOT_VALIDE);
		}
	}

	@SuppressWarnings("unchecked")
	private void validateKey(HttpServletRequest request)
			throws ValidationException {
		Map<String, String> pathVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String key = pathVariables.get(APIParamName.KEY) == null ? request
				.getParameter(APIParamName.KEY) : pathVariables
				.get(APIParamName.KEY);
		if (StringUtils.isEmpty(key)) {
			return;
		}
		if (!RegExpValidatorUtils.IsLetterAndNumberAndValidchar(key)) {
			throw new ValidationException(ValidationMsg.KEY_NOT_VALIDE);
		}
	}

	@SuppressWarnings("unchecked")
	private void validateLocale(HttpServletRequest request)
			throws ValidationException {
		Map<String, String> pathVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String locale = pathVariables.get(APIParamName.LOCALE) == null ? request
				.getParameter(APIParamName.LOCALE) : pathVariables
				.get(APIParamName.LOCALE);
		if (StringUtils.isEmpty(locale)) {
			return;
		}
		if (!RegExpValidatorUtils.IsLetterAndNumberAndValidchar(locale)) {
			throw new ValidationException(ValidationMsg.LOCALE_NOT_VALIDE);
		}
	}

	@SuppressWarnings("unchecked")
	private void validateNumber(HttpServletRequest request)
			throws ValidationException {
		Map<String, String> pathVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String number = pathVariables.get(APIParamName.NUMBER) == null ? request
				.getParameter(APIParamName.NUMBER) : pathVariables
				.get(APIParamName.NUMBER);
		if (StringUtils.isEmpty(number)) {
			return;
		}
		if (!RegExpValidatorUtils.isNumeric(number)) {
			throw new ValidationException(ValidationMsg.NUMBER_NOT_VALIDE);
		}
	}

	private void validateScale(HttpServletRequest request)
			throws ValidationException {
		String scale = request.getParameter(APIParamName.SCALE) == null ? ConstantsKeys.EMPTY_STRING
				: request.getParameter(APIParamName.SCALE);
		try {
			if (!StringUtils.isEmpty(scale)
					&& new Integer(scale).intValue() < 0) {
				throw new ValidationException(ValidationMsg.SCALE_NOT_VALIDE);
			}
		} catch (NumberFormatException e) {
			throw new ValidationException("NumberFormatException: " + e.getMessage());
		}
	}

	private void validateSourceformat(HttpServletRequest request)
			throws ValidationException {
		String sourceformat = request.getParameter(APIParamName.SOURCE_FORMAT) == null ? ConstantsKeys.EMPTY_STRING
				: request.getParameter(APIParamName.SOURCE_FORMAT);
		if (StringUtils.isEmpty(sourceformat)) {
			return;
		}
		if (!RegExpValidatorUtils.IsLetterOrNumber(sourceformat)) {
			throw new ValidationException(ValidationMsg.SOURCEFORMAT_NOT_VALIDE);
		}
	}

	private void validateCollectsource(HttpServletRequest request)
			throws ValidationException {
		String collectsource = request
				.getParameter(APIParamName.COLLECT_SOURCE) == null ? ConstantsKeys.EMPTY_STRING
				: request.getParameter(APIParamName.COLLECT_SOURCE);
		if (StringUtils.isEmpty(collectsource)) {
			return;
		}
		if (!RegExpValidatorUtils.IsTrueOrFalse(collectsource)) {
			throw new ValidationException(
					ValidationMsg.COLLECTSOURCE_NOT_VALIDE);
		}
	}

	private void validatePseudo(HttpServletRequest request)
			throws ValidationException {
		String pseudo = request.getParameter(APIParamName.PSEUDO) == null ? ConstantsKeys.EMPTY_STRING
				: request.getParameter(APIParamName.PSEUDO);
		if (StringUtils.isEmpty(pseudo)) {
			return;
		}
		if (!RegExpValidatorUtils.IsTrueOrFalse(pseudo)) {
			throw new ValidationException(ValidationMsg.PSEUDO_NOT_VALIDE);
		}
	}

	@SuppressWarnings("unchecked")
	private void validatePattern(HttpServletRequest request)
			throws ValidationException {
		Map<String, String> pathVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String pattern = pathVariables.get(APIParamName.PATTERN) == null ? request
				.getParameter(APIParamName.PATTERN) : pathVariables
				.get(APIParamName.PATTERN);
		if (StringUtils.isEmpty(pattern)) {
			return;
		}
		if (!RegExpValidatorUtils.IsLetter(pattern)) {
			//throw new ValidationException(ValidationMsg.PATTERN_NOT_VALIDE);
		}
	}
}
