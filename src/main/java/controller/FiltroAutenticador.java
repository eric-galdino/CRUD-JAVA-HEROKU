package controller;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroAutenticador
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/usucontroller.do" })
public class FiltroAutenticador implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroAutenticador() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		
		//Fazendo um casting para poder acessar a sessao
		HttpServletRequest httpResquest =  (HttpServletRequest) request; 
		HttpSession sessao = httpResquest.getSession(false);
		
		//Verificar se existe a sessao e se o atributo correspondente ao usuario
		//est� na sessao
		if(sessao!=null  && sessao.getAttribute("usuAutenticado") !=null ){
			chain.doFilter(request, response);
		}else{
			//fazendo casting para poder acessar o metodo sendRedirect
			HttpServletResponse httpResponse =  (HttpServletResponse) response;
			httpResponse.sendRedirect("login.html");
			
		}
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
