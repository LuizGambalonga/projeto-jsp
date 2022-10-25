package servelts;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoginModel;

import java.io.IOException;

import dao.loginDaoRepository;

@WebServlet(urlPatterns = { "/ServletLogin", "/principal/ServletLogin" })
public class ServelLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private loginDaoRepository daoLoginRepository = new loginDaoRepository();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServelLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		try {
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				LoginModel loginModel = new LoginModel();
				

				loginModel.setLogin(login);
				loginModel.setSenha(senha);

				if (daoLoginRepository.validarAutenticacao(loginModel)) {
					request.getSession().setAttribute("usuario", loginModel.getLogin());
					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);

				} else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Informe o Login e senha corretamente!");
					redirecionar.forward(request, response);
				}
			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe o Login e senha corretamente!");
				redirecionar.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			

		}

	}

}
