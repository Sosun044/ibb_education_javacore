package com.MuhammedSosun._2_week;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTime {

    public static void dateMethod(){
        Date now =new Date();
        System.out.println("Şu andaki zaman: " + now);
        System.out.println("1 ocak 1970 yılından şimdi ki zamana kadar geçen sürenin milisaniye cinsinden:" + now.getTime());
        System.out.println("Date:" + now.getDate());

        System.out.println("#######################");
        System.out.println("Day:" + now.getDay());
        System.out.println("Month:" + now.getMonth()); // Saymaya Sıfırdan başlar 0=Ocak 1=Şubat
        System.out.println("Year:" + (1900 + now.getYear()));  // 1900(Ekle veya Çıkar)
        System.out.println("Date yıl:" + (2025 - now.getYear()));
        System.out.println("Hours:" + now.getHours());
        System.out.println("Minutes:" + now.getMinutes());
        System.out.println("Seconds:" + now.getSeconds());

    }
    public static String nowFormat2() throws NullPointerException{
        //%s String yapısı
        //%d decimal yapısı
        //%f float yapısı
        Date now = new Date();
        return String.format("Şimdiki Zaman: %02d:%02d:%02d", now.getHours(), now.getMinutes(), now.getSeconds());

    }
    public static String nowFormat3() throws NullPointerException {
        Date now = new Date();
        Locale locale = new Locale("tr", "TR");

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",locale);
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss",locale);
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss",locale);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy hh:mm:ss", locale);
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss zzzz",locale);

        String formatedDate = String.format("Şimdiki Zaman: %s", sdf.format(now));
        return new Date().toString() + " - " + formatedDate;
    }

    public static void main(String[] args) {
        dateMethod();
        String nowDate2 = nowFormat2();
        System.out.println(nowDate2);

        String nowDate3 = nowFormat3();
        System.out.println(nowDate3);
    }
}
