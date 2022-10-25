package filtro;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import conexao.SingleConnection;

@WebFilter(urlPatterns = { "/principal/*" }) // intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao extends HttpFilter implements Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Connection connection;

	public FilterAutenticacao() {
		super();

	}

	// encerra os processos quando o servidor é parado
	// exemplo mataria os processos de conexão com o banco e sessão
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// intercepta todas requisições do projeto
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");

			String urlParaAutenticar = req.getServletPath();

			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por Favor Realize o Login!");
				redirecionar.forward(request, response);
				return;
			} else {
				chain.doFilter(request, response);
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	// e executado quando inicia o sistema
	// iniciar a conexão com banco (exemplo)
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnection.getConnection();
	}

}
