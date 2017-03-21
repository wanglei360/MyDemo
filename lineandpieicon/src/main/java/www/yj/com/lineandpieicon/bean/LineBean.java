package www.yj.com.lineandpieicon.bean;
import java.io.Serializable;
import java.util.List;

public class LineBean  implements Serializable {

    public List<Month> month;
    public List<Week> week;
    public List<Day> day;

    public class Month implements Serializable {
        public String x;
        public float y;
    }

    public class Week implements Serializable {
        public String x;
        public String y;
    }

    public class Day implements Serializable {
        public String x;
        public String y;
    }

}
