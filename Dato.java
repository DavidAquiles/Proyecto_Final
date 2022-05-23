import java.util.ArrayList;
import java.util.Arrays;

public class Dato {
private String station;
private String name;
private String country;
private String date;
private double prcp;
private double tavg;
private double tmax;
private double tmin;
public static ArrayList<Dato> stations = new ArrayList();

/**
 * Método constructor de la Clase
 * @param s[]: Arreglo tipo String de 8 posiciones
 */
public Dato(String s[]) {
    this.station = s[0];
    this.name = s[1];
    this.country = s[2];
    this.date = s[3];
    this.prcp = Double.parseDouble(s[4]);
    this.tavg = Double.parseDouble(s[5]);
    this.tmax = Double.parseDouble(s[6]);
    this.tmin = Double.parseDouble(s[7]);
}

/**
 * Método usado para agregar un objeto tipo Dato al arrelo dinámico stations
 * @param s: Objeto del tipo Dato que se desea agregar al arreglo
 */
public static void saveStation (Dato s)
{
    stations.add(s);
}

// *** Métodos Get ***
/**
 * Recupera el código de la estación y lo retorna
 */
public String getStation() {
    return this.station;
}

/**
 * Recupera el nombre de la estación y lo retorna
 */
public String getName() {
    return this.name;
}

/**
 * Recupera el país de la estación y lo retorna
 */
public String getCountry() {
    return this.country;
}

/**
 * Recupera la fecha del registro y lo retorna
 */
public String getDate() {
    return this.date;
}

/**
 * Recupera el valor de la precipitación y lo retorna
 */
public double getPrcp() {
    return this.prcp;
}

/**
 * Recupera el valor de la temperatura promedio y lo retorna
 */
public double getTavg() {
    return this.tavg;
}

/**
 * Recupera el valor de la temperatura máxima y lo retorna
 */
public double getTmax() {
    return this.tmax;
}

/**
 * Recupera el valor de la temperatura mínima y lo retorna
 */
public double getTmin() {
    return this.tmin;
}

// *** Métodos Set ***
/**
 * Asigna un nuevo valor al código de la estación
 * @param station: String con el valor a asignar
 */
public void setStation(String station) {
    this.station = station;
}

/**
 * Asigna un nuevo valor al nombre de la estación
 * @param name: String con el valor a asignar
 */
public void setName(String name) {
    this.name = name;
}

/**
 * Asigna un nuevo valor a la fecha del registro
 * @param date: String con el valor a asignar
 */
public void setDate(String date) {
    this.date = date;
}

/**
 * Asigna un nuevo valor al campo precipitación del registro
 * @param prcp: double con el valor a asignar
 */
public void setPrcp(double prcp) {
    this.prcp = prcp;
}

/**
 * Asigna un nuevo valor al campo temperatura promedio del registro
 * @param tavg: double con el valor a asignar
 */
public void setTavg(double tavg) {
    this.tavg = tavg;
}

/**
 * Asigna un nuevo valor al campo temperatura máxima del registro
 * @param tmax: double con el valor a asignar
 */
public void setTmax(double tmax) {
    this.tmax = tmax;
}

/**
 * Asigna un nuevo valor al campo temperatura mínima del registro
 * @param tmin: double con el valor a asignar
 */
public void setTmin(double tmin) {
    this.tmin = tmin;
}
}
