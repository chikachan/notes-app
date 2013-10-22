package tabitabi.picco.web.util;

import javax.faces.context.FacesContext;

import tabitabi.picco.util.UTF8ResourceBundle;

public class Messages extends UTF8ResourceBundle {

	private static final long serialVersionUID = 162203315208987730L;
	
	private static final String BUNDLE_NAME = "messages";

	
	public Messages(){
		super(BUNDLE_NAME, FacesContext.getCurrentInstance().getViewRoot().getLocale());
	}

}
