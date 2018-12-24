package bean;

import dao.CalendarDao;
import dao.impl.CalendarDaoImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Calendar;
import util.Mensajes;

@ManagedBean
@ViewScoped
public class CalendarBean implements Serializable {

    private Calendar calendar;
    List<Calendar> list;
    private Mensajes mensajes;

    public CalendarBean() {
        calendar = new Calendar();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<Calendar> getList() {
        CalendarDao calendarDao = new CalendarDaoImpl();
        list = calendarDao.calendarQry();
        return list;
    }

    public void setList(List<Calendar> list) {
        this.list = list;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public void preparar() {
        calendar = new Calendar();
    }

    public void agregar() {
        CalendarDao calendarDao = new CalendarDaoImpl();

        String message = calendarDao.calendarIns(calendar);
        if (message == null) {
           
            preparar();
        } else {
          
        }

    }

}
