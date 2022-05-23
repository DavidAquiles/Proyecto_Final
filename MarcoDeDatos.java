import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.Scanner;

/**
 * Esta clase contiene todos los metodos necesarios para analizar el DataFrame
 */
public class MarcoDeDatos
{
    
   /**
    * Método que se encarga de leer el archivo de datos a trabajar
    * Recorre las filas del archivo, las limpia y las convierte en un arreglo estático
    * que luego es enviado cómo parámetro al constructor de la clase Dato para crear un objeto
    * y almacenarlo en el arreglo dinámico "stations "
    * @param file: Recibe el nombre con la extensión del archivo que se desea cargar Ej:(Medellin.csv)
    */ 
   public static void readFile(String file)
   {
      Scanner teclado = new Scanner(System.in);
      BufferedReader br = null;
      
      try {
         
         br = new BufferedReader(new FileReader(file));
         String line = br.readLine();
         line = br.readLine();         
         //int x = 2;
         while (null!=line) {
             
            String [] station = line.split(",");            
            station = cleanLine(station);            
            Dato stt = new Dato (station);
            Dato.saveStation(stt);
            //x += 1;
            line = br.readLine();
         }
         System.out.println("*** Archivo cargado correctamente ***\n" );
         
      } catch (Exception e) {
         System.out.println("No fue posible leer el archivo. Por favor verifique la ruta y/o el nombre del mismo" );
         System.out.print("Ingrese cualquier caracter para continuar...");
         teclado.next();
         System.out.println("\f");
         Principal.main();
      }
   }
   
   /**
    * Método invocado por el readFile() para limpiar las filas del DataFrame
    * @param f: Arreglo estático de 8 posiciones
    * @return result: Devuelve  un arreglo estático libre de comillas y espacios innecesarios.
    */ 
    private static String[] cleanLine(String[] f) 
   {

      String result[] = new String[f.length];

      for (int i=0;i<8;i++){
        if (f[i].length()<=2){ f[i]= "0";} 
         result[i] = f[i].replaceAll("" + (char)34, "").trim();
    }      
      return result;
   }
   
   /**
    * Método invocado para almacenar un nuevo registor en el Data Frame
    * Invoca al método convertToSave() para convertir el Dato stt en un String tipo csv
    * @param stt: Objeto del tipo Dato el cual se desea almacenar
    * @param file: String con el nombre del archivo en el cual se desea almacena el Dato stt
    */
   public static void saveRecord(Dato stt, String file)
   {
        String newStt = convertToSave(stt);
        File f = new File(file);
        FileWriter writer = null;
        PrintWriter pw = null;
        //ACA CREO EL ARCHIVO SINO EXISTE
        if (!f.exists()) {
            try{
                f.createNewFile();
            }catch(IOException exception){
                System.err.println("Error creating the file");
            }
        }
        
        try{
            writer = new FileWriter(file,true);
            pw = new PrintWriter(writer);
            pw.println(newStt);
            Dato.saveStation(stt);
            System.out.println("Registro ingresado correctamente!");
        }catch(IOException exception){
                System.err.println("Error opening the file");
        }
        
        finally {
           try {
               //ACA CIERRO EL ARCHIVO
           if (null != writer)
              writer.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
   }
   
   /**
    * Método invocado para convertir un objeto tipo Dato en un String para imprimirlo por consola
    * @param stt: Objeto del tipo Dato el cual se desea convertir
    */
   public static String convertToString(Dato stt)
   {
       String line = (stt.getStation() +" | "+ (char)34 + stt.getName() + ", "+stt.getCountry() 
       + (char)34 + " | "+stt.getDate() +" | "+stt.getPrcp() +" | "+stt.getTavg() +" | "+stt.getTmax() +" | "+stt.getTmin());
       return line;
   } 
   
   /**
    * Método invocado para convertir un objeto tipo Dato en un String para guardarlo en formato csv
    * @param stt: Objeto del tipo Dato el cual se desea convertir
    */
    public static String convertToSave(Dato stt)
   {
       String line = (stt.getStation() +","+ (char)34 + stt.getName() + ", "+stt.getCountry() 
       + (char)34 + ","+stt.getDate() +","+stt.getPrcp() +","+stt.getTavg() +","+stt.getTmax() +","+stt.getTmin());
       return line;
   }
    
   /**
    * Método invocado para convertir imprimir por consola los encabezados del Data Frame
    */
    public static void printHeaders()
    {
        System.out.println("\n  STATION   |        NAME         |    DATE    | PRCP | TAVG | TMAX | TMIN");
        System.out.println("--------------------------------------------------------------------------");
    }
   
    ///// Métodos de Filtro /////
   /**
    * Método que se invoca para buscar los valores menores o iguales al valor buscado 
    * @param campo: Campo por el cual se desea filtrar
    * @param num: Valor por el cual se aplica el filtro
    * @return sw: Retorna false cuando no se encuentran valores que cumplan con las condiciones dadas
    */
    public static boolean menorIgualQue(String campo, double num){
           String linea;
           double m = -1;
           boolean sw = false;
        for(Dato s: Dato.stations)
        {
            if (campo.equals("prcp")){ m=s.getPrcp();}
            else if (campo.equals("tavg")){ m=s.getTavg();}
            else if (campo.equals("tmax")){ m=s.getTmax();}
            else if (campo.equals("tmin")){ m=s.getTmin();}
            
            if (m<=num){
            sw = true;
            linea = convertToString(s);
            System.out.println(linea);
            }
        } 
        return sw;
    }
    
    /**
    * Método que se invoca para buscar los valores mayores o iguales al valor buscado 
    * @param campo: Campo por el cual se desea filtrar
    * @param num: Valor por el cual se aplica el filtro
    * @return sw: Retorna false cuando no se encuentran valores que cumplan con las condiciones dadas
    */
    public static boolean mayorIgualQue(String campo, double num){
        String linea;
        double m = -1;
        boolean sw = false;
        for(Dato s: Dato.stations) {
            if (campo.equals("prcp")){ m=s.getPrcp();}
            else if (campo.equals("tavg")){ m=s.getTavg();}
            else if (campo.equals("tmax")){ m=s.getTmax();}
            else if (campo.equals("tmin")){ m=s.getTmin();}
    
            if (m>=num){
            sw = true;
            linea = convertToString(s);
            System.out.println(linea);
            }
        }
        return sw;
    }
    
    /**
    * Método que se invoca para buscar los valores iguales al valor buscado 
    * @param campo: Campo por el cual se desea filtrar
    * @param num: Valor por el cual se aplica el filtro
    * @return sw: Retorna false cuando no se encuentran valores que cumplan con las condiciones dadas
    */
    public static boolean igualQue(String campo, double num){
        String linea;
        double m = -1;
        boolean sw = false;
        for(Dato s: Dato.stations) {
            if (campo.equals("prcp")){ m=s.getPrcp();}
            else if (campo.equals("tavg")){ m=s.getTavg();}
            else if (campo.equals("tmax")){ m=s.getTmax();}
            else if (campo.equals("tmin")){ m=s.getTmin();}
    
            if (m==num){
                sw = true;
                linea = convertToString(s);
                System.out.println(linea);
            }
        }
        return sw;
    }
        
    ///// Métodos Estadiśticos /////
    
    /**
    * Método que se invoca para buscar contar la cantidad de datos del Data Frame
    */
    public static void contar()
    {
        Scanner teclado = new Scanner(System.in);
        String line;
        System.out.println("El numero total de registros es de: " + Dato.stations.size());
        System.out.println("Desea imprimir todos los registros del archivo? 1:Si , 2:No");
        int j = teclado.nextInt();
        if (j==1)
        {
            printHeaders();
            for(Dato d: Dato.stations)
            {
                line = convertToString(d);
                System.out.println(line);
            }
        }
        else 
        {
            System.out.println("\f");
            Principal.menu();
        }
    }
    
    /**
    * Método que se invoca para encontrar el máximo valor del campo buscado 
    * @param campo: Campo por el cual se desea buscar
    */
    public static void maximo(String campo) 
    {
        if(campo.equals("tavg"))
        {
            Dato max = null;
            double maxQuant = -1;
            for(Dato station : Dato.stations) {
              if(station.getTavg() > maxQuant) {
                maxQuant=station.getTavg();
                max=station;
              }
            }
            System.out.println("La temperatura promedio más alta es: " + maxQuant);
          }
          else if(campo.equals("prcp"))
          {
            Dato max = null;
            double maxQuant = -1;
            for(Dato station : Dato.stations) {
              if(station.getPrcp() > maxQuant) {
                maxQuant=station.getPrcp();
                max=station;
              }
            }
            System.out.println("La precipitación máxima es: " + maxQuant);
          }
          else if(campo.equals("tmax"))
          {
            Dato max = null;
            double maxQuant = -1;
            for(Dato station : Dato.stations) {
              if(station.getTmax() > maxQuant) {
                maxQuant=station.getTmax();
                max=station;
              }
            }
            System.out.println("La temperatura máxima más alta es: " + maxQuant);
          }
          else if(campo.equals("tmin"))
          {
            Dato max = null;
            double maxQuant = -1;
            for(Dato station : Dato.stations) {
              if(station.getTmin() > maxQuant) {
                maxQuant=station.getTmin();
                max=station;
              }
            }
            System.out.println("La temperatura minima más alta es: " + maxQuant);
          }
          else
          {
              System.out.println("El campo ingresado no es válido");

          }     
    }
    
    /**
    * Método que se invoca para encontrar el mínimo valor del campo buscado 
    * @param campo: Campo por el cual se desea buscar
    */
    public static void minimo(String campo) 
    {
        if(campo.equals("tavg"))
        {
            Dato min = null;
            double minQuant = Integer.MAX_VALUE;
            for(Dato station : Dato.stations) {
              if(station.getTavg() < minQuant) {
                minQuant=station.getTavg();
                min=station;
              }
            }
            System.out.println("La temperatura promedio más baja es: " + minQuant);
          }
          else if(campo.equals("prcp"))
          {
            Dato min = null;
            double minQuant = Integer.MAX_VALUE;
            for(Dato station : Dato.stations) {
              if(station.getPrcp() < minQuant) {
                minQuant=station.getPrcp();
                min=station;
              }
            }
            System.out.println("La precipitación mínima es: " + minQuant);
          }
          else if(campo.equals("tmax"))
          {
            Dato min = null;
            double minQuant = Integer.MAX_VALUE;
            for(Dato station : Dato.stations) {
              if(station.getTmax() < minQuant) {
                minQuant=station.getTmax();
                min=station;
              }
            }
            System.out.println("La temperatura máxima más baja es: " + minQuant);
          }
          else if(campo.equals("tmin"))
          {
            Dato min = null;
            double minQuant = Integer.MAX_VALUE;
            for(Dato station : Dato.stations) {
              if(station.getTmin() < minQuant) {
                minQuant=station.getTmin();
                min=station;
              }
            }
            System.out.println("La temperatura mínima más baja es: " + minQuant);
          }
          else
          {
              System.out.println("El campo ingresado no es válido");

          }
    }     
}
