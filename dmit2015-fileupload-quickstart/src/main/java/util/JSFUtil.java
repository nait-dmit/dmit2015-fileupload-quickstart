package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil
{
  public static void addInfoMessage(String message)
  {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	FacesMessage facesMessage = new FacesMessage(message);
	facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	facesContext.addMessage(null, facesMessage);
  }

  public static void addErrorMessage(String message)
  {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	FacesMessage facesMessage = new FacesMessage(message);
	facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
	facesContext.addMessage(null, facesMessage);
  }
  
  public static void addWarningMessage(String message)
  {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	FacesMessage facesMessage = new FacesMessage(message);
	facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
	facesContext.addMessage(null, facesMessage);
  }
  
  public static void addFatalMessage(String message)
  {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	FacesMessage facesMessage = new FacesMessage(message);
	facesMessage.setSeverity(FacesMessage.SEVERITY_FATAL);
	facesContext.addMessage(null, facesMessage);
  } 
  
  public static String getInitParameter(String paramName)
  {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	return facesContext.getExternalContext().getInitParameter(paramName);
  }
}