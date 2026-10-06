
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // Proxies*/
		
		response.setHeader("Cache-Control", "no-cache, revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // Proxies
	    
		try {
			String n = request.getParameter("userName");
		    HttpSession session = request.getSession(false); // No crea sesión automáticamente

		    // Si intenta entrar a Servlet1 sin parámetro y sin sesión activa, mandar a la página inicial
		    if ((n == null || n.isBlank()) && (session == null || session.getAttribute("uname") == null)) {
		        response.sendRedirect("index.html");
		        return;
		    }

		    // Si viene del formulario (trae userName), creamos la sesión
		    if (n != null && !n.isBlank()) {
		        session = request.getSession(true);
		        session.setAttribute("uname", n);
		    } else if (session != null) {
		        // Si no traía parámetro pero tenía sesión, recuperamos el nombre guardado
		        n = (String) session.getAttribute("uname");
		    }

		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();

		    out.print("Welcome " + n);
		    out.print("<br>");
		    out.print("<a href='Servlet2'>visit</a> <a href='ServletLogout'>log out</a>");
		    out.close();
					
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
