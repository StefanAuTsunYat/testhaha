package utilities;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String getFormattedDate(Date targetDate, String formatPattern){
        SimpleDateFormat dateFormat= new SimpleDateFormat(formatPattern);
        return dateFormat.format(targetDate);
    }

    public static Date getTodayDate(){
        return Calendar.getInstance().getTime();
    }

    public static Date getCalculatedDate(Date startDate, int calValue) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, calValue);

        return calendar.getTime();
    }

    public static String getTransDate(String dob){
        StringBuffer newBob = new StringBuffer();
        String[] transDob = dob.split("-");
        String tmp = transDob[2].trim();
        newBob.append(tmp+"-");
        switch(transDob[1].trim()){
            case "01":
                newBob.append("Jan" + "-" + transDob[0].trim());
                break;
            case "02":
                newBob.append("Feb" + "-" + transDob[0].trim());
                break;
            case "03":
                newBob.append("Mar" + "-" + transDob[0].trim());
                break;
            case "04":
                newBob.append("Apr" + "-" + transDob[0].trim());
                break;
            case "05":
                newBob.append("May" + "-" + transDob[0].trim());
                break;
            case "06":
                newBob.append("Jun" + "-" + transDob[0].trim());
                break;
            case "07":
                newBob.append("Jul" + "-" + transDob[0].trim());
                break;
            case "08":
                newBob.append("Aug" + "-" + transDob[0].trim());
                break;
            case "09":
                newBob.append("Sep" + "-" + transDob[0].trim());
                break;
            case "10":
                newBob.append("Oct" + "-" + transDob[0].trim());
                break;
            case "11":
                newBob.append("Nov" + "-" + transDob[0].trim());
                break;
            case "12":
                newBob.append("Dec" + "-" + transDob[0].trim());
                break;
        }
        return (newBob.toString());
    }
}
