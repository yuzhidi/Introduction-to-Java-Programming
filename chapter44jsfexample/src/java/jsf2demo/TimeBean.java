package jsf2demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TimeBean {
  public String getTime() {
    return new java.util.Date().toString();
  }
}
