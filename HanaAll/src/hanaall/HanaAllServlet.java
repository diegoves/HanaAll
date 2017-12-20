package hanaall;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class HanaAllServlet
 */
public class HanaAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(HanaAllServlet.class);
	
	private MessageDAO mexDAO;
	
	 /** {@inheritDoc} */
    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
            mexDAO = new MessageDAO(ds);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            List<Message> resultList = mexDAO.selectAllMessageFromDevice();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
            String json = gson.toJson(resultList);  
            response.getWriter().println("{\"messages\":" + json + "}");
        } catch (Exception e) {
            response.getWriter().println("Persistence operation failed with reason: " + e.getMessage());
            LOGGER.error("Persistence operation failed", e);
        }
	}

}
