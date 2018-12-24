package dao;

import java.util.List;
import model.Calendar;

public interface CalendarDao {

    // Listar actEspecs
    public List<Calendar> calendarQry();

    // Insertar nuevos actEspecs
    public String calendarIns(Calendar calendar);

}
