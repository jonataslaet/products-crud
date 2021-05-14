package br.com.jonataslaet.productscrudapi.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class HeaderFilter implements Filter {

	private String originPermitida = "http://localhost:4200";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Methods","PUT, DELETE");
		res.setHeader("Access-Control-Allow-Origin", originPermitida);
		res.setHeader("Access-Control-Allow-Headers", "Content-Type");
		chain.doFilter(request, response);
	}
}
