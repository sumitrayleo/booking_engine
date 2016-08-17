package com.ehi.cts.serviceclient;

import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.MDC;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.ehi.cts.common.enumeration.HeaderEnum;

public class BaseClient {

	private static final String ACCEPT = "Accept";
	private static final String ACCEPT_LANGUAGE = "Accept-Language";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String CSMA_REQUEST_ID = "Ehi-CSMA-Request-Id";

	protected RestTemplate restTemplate;
	private final String contentTypeHeaderValue;
	private final String acceptHeaderValue;

	public BaseClient(final RestTemplate restTemplate,
			final String contentTypeHeaderValue, final String acceptHeaderValue) {
		this.restTemplate = restTemplate;
		this.contentTypeHeaderValue = contentTypeHeaderValue;
		this.acceptHeaderValue = acceptHeaderValue;
	}

	public String buildUri(final String baseUri,
			final Map<String, Object> arguments) {
		final UriTemplate intUriTemplate = new UriTemplate(baseUri);
		final URI uri = intUriTemplate.expand(arguments);
		return uri.toString();
	}

	public <T> BaseResponseWrapper<T> sendRequestWrapResponse(
			final Map<String, String> headers, final HttpMethod method,
			final Class<T> responseType, final String url, final Object request) {
		final HttpHeaders httpHeaders = createHeaders(headers);
		return getWrappedResponse(httpHeaders, method, request, responseType,
				url);
	}

	public <T> BaseResponseWrapper<T> sendRequestWrapResponse(
			final Map<String, String> headers, final HttpMethod method,
			final Class<T> responseType, final String url) {
		final HttpHeaders httpHeaders = createHeaders(headers);
		return getWrappedResponse(httpHeaders, method, null, responseType, url);
	}

	public <T> BaseResponseWrapper<T> sendRequestWrapResponse(
			final HttpHeaders headers, final HttpMethod method,
			final Class<T> responseType, final String url, final Object request) {
		addBaseHeaders(headers);
		return getWrappedResponse(headers, method, request, responseType, url);
	}

	public <T> T sendRequest(final HttpMethod method,
			final Class<T> responseType, final String url, final Object request) {
		return getRawResponse(addBaseHeaders(new HttpHeaders()), method,
				request, responseType, url);
	}

	// Helper methods

	private <T> T getRawResponse(final HttpHeaders headers,
			final HttpMethod method, final Object requestObject,
			final Class<T> responseType, final String url) {
		T response = null;
		final ResponseEntity<T> responseEntity = getResponseEntity(headers,
				method, requestObject, responseType, url);
		if (responseEntity != null
				&& responseEntity instanceof ResponseEntity<?>) {
			response = responseEntity.getBody();
		}
		return response;
	}

	private <T> BaseResponseWrapper<T> getWrappedResponse(
			final HttpHeaders headers, final HttpMethod method,
			final Object request, final Class<T> responseType, final String url) {
		BaseResponseWrapper<T> baseResponseWrapper = null;
		final ResponseEntity<T> responseEntity = getResponseEntity(headers,
				method, request, responseType, url);

		if (responseEntity != null
				&& responseEntity instanceof ResponseEntity<?>) {
			baseResponseWrapper = new BaseResponseWrapper<T>(
					responseEntity.getBody(), responseEntity.getStatusCode());
		}
		return baseResponseWrapper;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T> ResponseEntity<T> getResponseEntity(final HttpHeaders headers,
			final HttpMethod method, final Object requestObject,
			final Class<T> responseType, final String url) {
		final HttpEntity<?> requestEntity = new HttpEntity(requestObject,
				headers);
		final ResponseEntity<T> responseEntity = restTemplate.exchange(url,
				method, requestEntity, responseType);
		return responseEntity;
	}

	private HttpHeaders createHeaders(final Map<String, String> headers) {
		final HttpHeaders httpHeaders = new HttpHeaders();

		if (headers != null && !headers.isEmpty()) {
			final Set<Entry<String, String>> entrySet = headers.entrySet();
			for (final Entry<String, String> entry : entrySet) {
				httpHeaders.add(entry.getKey(), entry.getValue());
			}
		}

		addBaseHeaders(httpHeaders);

		return httpHeaders;
	}

	protected HttpHeaders addBaseHeaders(final HttpHeaders httpHeaders) {
		httpHeaders.add(HeaderEnum.CORRELATION_ID.getValue(),
				MDC.get(CSMA_REQUEST_ID));
		httpHeaders.add(CONTENT_TYPE, contentTypeHeaderValue);
		httpHeaders.add(ACCEPT, acceptHeaderValue);
		httpHeaders.add(ACCEPT_LANGUAGE, LocaleContextHolder.getLocale()
				.getLanguage()
				+ "-"
				+ LocaleContextHolder.getLocale().getCountry());

		return httpHeaders;
	}

}