package mvasem8.data;

import java.util.Date;

public class ConverterDate {
    public static java.util.Date convertToJava(java.sql.Date sqlDate){
        java.util.Date javadate = null;
        if(sqlDate != null ){
            javadate = new Date(sqlDate.getTime());
        }
        return javadate;
    }
    public static java.sql.Date convertToSql(java.util.Date javadate){
        java.sql.Date sqldate = null;
        if(javadate != null){
            sqldate = new java.sql.Date(javadate.getTime());
        }
        return sqldate;
    }
}
