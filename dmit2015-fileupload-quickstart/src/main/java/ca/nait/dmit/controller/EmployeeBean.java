package ca.nait.dmit.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import util.JSFUtil;
import ca.nait.dmit.domain.Employee;
import ca.nait.dmit.service.EmployeeService;

@Named
@SessionScoped
public class EmployeeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(EmployeeBean.class.getName());
	
	@Inject
	private EmployeeService employeeService;
	
	private Employee currentEmployee = null;
	private List<Employee> employees = null;
	
	private UploadedFile uploadedFile;
	
	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public void setCurrentEmployee(Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	@PostConstruct
	public void init() {
		currentEmployee = new Employee();
		try {
			employees = employeeService.findAllEmployee();
		} catch(Exception ex) {
			JSFUtil.addErrorMessage("init() failed with exception: " + ex.getMessage()); 
		}
	}
	
	public void addEmployee() {
		try {
			logger.info("file name: " + uploadedFile.getFileName() );
			logger.info("file size: " + uploadedFile.getSize() );
			
			// use uploadedFile.getContents() with commons FileUpload engine.
//			logger.info("contents: " + uploadedFile.getContents() );
//			currentEmployee.setPicture( uploadedFile.getContents() );
			
			// use uploadedFile.getInputstream() with servlet 3.0 FileUpload engine.
			logger.info("inputstream: " + uploadedFile.getInputstream() );
			currentEmployee.setPicture( IOUtils.toByteArray( uploadedFile.getInputstream() ) );
			
			employeeService.createEmployee(currentEmployee);
			currentEmployee = new Employee();
			employees = employeeService.findAllEmployee();
			JSFUtil.addInfoMessage("Add employee was successful.");
		} catch( Exception ex ) {
			JSFUtil.addErrorMessage("Add employee was not successful with exception: " + ex.getMessage() );
		}
	}
	
	public StreamedContent getPictureImage() {
		FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String idString = context.getExternalContext().getRequestParameterMap().get("id");
            Integer id = Integer.valueOf(idString);
            Employee employee = employeeService.findById( id );
            return new DefaultStreamedContent(new ByteArrayInputStream( employee.getPicture() ) );
        }
	}
}
