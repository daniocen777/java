package web.bean;

import dto.MyUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class AppBean {

    public AppBean() {
    }
    public String getBaseUrl() {
        return MyUtil.baseurl();
    }
}
