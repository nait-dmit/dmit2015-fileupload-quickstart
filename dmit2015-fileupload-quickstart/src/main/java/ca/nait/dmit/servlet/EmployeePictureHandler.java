package ca.nait.dmit.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.nait.dmit.domain.Employee;
import ca.nait.dmit.service.EmployeeService;

/**
 * Servlet implementation class EmployeePictureHandler
 */
@WebServlet("/EmployeePictureHandler")
public class EmployeePictureHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private EmployeeService employeeService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeePictureHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		if( idString != null ) {
			Integer id = Integer.valueOf(idString);
			Employee employee = employeeService.findById(id);
			if( employee != null ) {
				response.setContentType("image");
				response.getOutputStream().write( employee.getPicture() );
			}
		}
	}

}
