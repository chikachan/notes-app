package tabitabi.picco.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.joda.time.DateTime;

@ManagedBean
@SessionScoped
public class CalendarTest implements Serializable {

  private DateTime date = new DateTime();

  public DateTime getDate() {
    return date;
  }

  public void setDate(DateTime date) {
    this.date = date;
  }
  
  
}
