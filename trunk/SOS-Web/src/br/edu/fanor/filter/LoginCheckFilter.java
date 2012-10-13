package br.edu.fanor.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.fanor.entity.Usuario;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
public class LoginCheckFilter extends AbstractFilter implements Filter {
	private static List<String> allowedURIs;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		if(allowedURIs == null){
			allowedURIs = new ArrayList<String>();
			allowedURIs.add(fConfig.getInitParameter("loginActionURI"));
			
//			adicione aki as paginas que sao independentes de usuario ex:
//			allowedURIs.add("/SOS-Web/resources/css/estilo.css");
//			allowedURIs.add("/SOS-Web/javax.faces.resource/primefaces.css.jsf");
//			allowedURIs.add("/SOS-Web/javax.faces.resource/jquery/jquery.js.jsf");
//			allowedURIs.add("/SOS-Web/javax.faces.resource/theme.css.jsf");
//			allowedURIs.add("/SOS-Web/javax.faces.resource/primefaces.js.jsf");
//			allowedURIs.add("");
//			allowedURIs.add("");
//			allowedURIs.add("");
//			allowedURIs.add("");
			
		}
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		if (session.isNew()) {
			doLogin(request, response, req);
			return;
		}

		Usuario user = (Usuario) session.getAttribute("usuario");

		if (user == null && !allowedURIs.contains(req.getRequestURI())) {
			doLogin(request, response, req);
			return;
		}

		chain.doFilter(request, response);
	}
}