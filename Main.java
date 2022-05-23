import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
/**
 * @author  
 * */
 
class Main
{
    public static void main()
    {
        //String [] station = {"PRUEBA","OLAYA PRUEBA","CO","2000-07-01","3.1","25.6","0.5","19.5"};
        //Dato dat = new Dato(station);
        //System.out.println(dat.getName());
        //System.out.println(dat.getName());
        //Dato.saveStation(dat);
        //MarcoDeDatos.saveRecord(dat, "Medellin.csv");*/
        
        MarcoDeDatos.readFile("Medellin.csv");
        
        /*String linea;
        for (Dato s: Dato.stations)
        {
            linea = MarcoDeDatos.convertToString(s);
            System.out.println(linea);
        }*/
        
      prueba();
    }
    
    public static void prueba()
    {
        String campo = "tavg";        
        double valor = 20;
        System.out.println("  STATION   |        NAME         |    DATE    | PRCP | TAVG | TMAX | TMIN");
        MarcoDeDatos.mayorIgualQue(campo, valor);
    }
} 