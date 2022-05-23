import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Arrays;
 
/**
  *@author Danna Mendoza, Evelyn Zapata, David Blandon, John Montoya
  *@version Versión 1.0
  */

public class Principal {
    
    static String file;
    /**
     * Este es el método principal del proyecto
     * en este se carga el archivo a trabajar e invoca el menú principal
     */
    public static void main() {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("***** Cargar Archivo  *****\n"); 
        System.out.println("Por favor ingrese el nombre del archivo a trabajar:");
        file = teclado.next();        
        Dato.stations.clear();        
        MarcoDeDatos.readFile(file);
        
        Principal.menu();        
    }
    
    /**
     * Método que despliega el menú principal
     * En este se capturan las opciones que el usuario desea ejecuta
     * y hace el llamado a invocarMetodo()
     */
    public static void menu(){
        
        //MarcoDeDatos.readFile("Medellin.csv");
        Scanner teclado = new Scanner(System.in);
        int x=0;
        int y=0;
        int z=0;
        double valor = -1;
        //System.out.println("\f");
        System.out.println("***** Menú Principal *****\n");        
        System.out.println("Elija una de las siguientes opciones:\n");
        System.out.println("Presione 0 para finalizar el programa");
        System.out.println("Presione 1 analizar el Data Frame");
        System.out.println("Presione 2 para añadir un nuevo registro al Data Frame");
        System.out.println("Presione 3 para cargar un Data Frame nuevo");
        
        do{
            x = teclado.nextInt();
            
            if(x==0) {
                System.out.println("***** Programa Finalizado *****");
                x = 0;
                break;
            }
            else if(x==1)
            {
                String campo = "";
                System.out.println("***** Analizar Data Frame *****\n");
                
                
                System.out.println("Presione 0 para ir volver al menú principal");
                System.out.println("Presione 1 si quiere utilizar el fltro menor igual que");
                System.out.println("Presione 2 si quiere utilizar el filtro mayor igual que");
                System.out.println("Presione 3 si quiere utilizar el filtro igual que");
                System.out.println("Presione 4 si quiere contar los registros del Data Frame");
                System.out.println("Presione 5 si quiere encontrar el valor máximo de un campo");
                System.out.println("Presione 6 si quiere encontrar el valor mínimo de un campo\n");
                y = teclado.nextInt();
                
                if (y==0)
                {
                    Principal.menu();
                    break;
                }
                else if (y == 4) 
                {
                    campo = "";
                }
                else if (y > 6)
                {
                    System.out.println("Opción inválida!");
                    System.out.println("Presione una tecla para continuar...");
                    teclado.next();
                    Principal.menu();
                }
                else
                {
                    System.out.println("***** Elija el campo con el cual desea trabjar *****\n");
                    System.out.println("1:Precipitaciones | 2:Temp. Promedio | 3:Temp. Máxima | 4: Temp. Mínima");
                    z = teclado.nextInt();
                    
                    if(z==1){
                        campo = "prcp";
                    }
                    else if(z==2){
                        campo = "tavg";
                    }
                    else if(z==3){
                        campo = "tmax";
                    }
                    else if(z==4){
                        campo = "tmin";
                    }
                    else{
                        System.out.println("Opción inválida!");
                        System.out.println("Presione una tecla para continuar...");
                        teclado.next();
                        Principal.menu();
                    }
                }
                if (y<4)
                {
                    System.out.println("Ingrese el número con el que desea filtrar");
                    valor = teclado.nextDouble();
                    
                }
                Principal.invocarMetodo(y, campo, valor);                
                
            }
            else if (x==2)
            {
                String [] station = new String [8];
                System.out.println("Por favor ingrese el código de la estación:");
                station[0] = teclado.next().toUpperCase();
                teclado.nextLine();
                System.out.println("Por favor ingrese el nombre de la estación:");
                station[1] = teclado.nextLine().toUpperCase();
                System.out.println("Por favor ingrese el país de la estación:");
                station[2] = teclado.next().toUpperCase();
                System.out.println("Por favor ingrese la fecha del reporte formato (DD-MM-AAAA)");
                station[3] = teclado.next();
                System.out.println("Por favor ingrese la precipitación:");
                station[4] = teclado.next();
                System.out.println("Por favor ingrese la temperatura promedio:");
                station[5] = teclado.next();
                System.out.println("Por favor ingrese la temperatura máxima:");
                station[6] = teclado.next();
                System.out.println("Por favor ingrese la temperatura mínima:");
                station[7] = teclado.next();                
                
                Dato dat = new Dato(station);
                MarcoDeDatos.saveRecord(dat, file);
            }
            else if(x==3)
            {
                System.out.println("\f");
                Principal.main();
            }
            else 
            {
                System.out.println("Opción inválida!");
            }
            
            System.out.print("\nPresione una tecla para continuar...");
            teclado.next();
            System.out.print("\f");
            x=0;
            Principal.menu();            
        }while(x != 0);
    }
    
    /**
     * Este médoto se encarga de llamar a los métodos de la clase MarcoDeDatos
     * según los parámetros ingresados por el usuario
     * @param opcion: Recibe el tipo de operación que el usuario desea ejecutar
     * @param campo: Recibe el campo por el cual se desea operar
     * @param val: Recibe el valor por el cual se desea filtrar
     */
    public static void invocarMetodo(int opcion, String campo, double val)
    {
        boolean flag;
        switch (opcion)
        {
            case 1:
                MarcoDeDatos.printHeaders();
                flag = MarcoDeDatos.menorIgualQue(campo, val);
                if (flag == false)
                {
                    System.out.println("No existen registros que cumplan esta condición");
                }
                break;
            case 2:
                MarcoDeDatos.printHeaders();
                flag = MarcoDeDatos.mayorIgualQue(campo, val);
                if (flag == false)
                {
                    System.out.println("No existen registros que cumplan esta condición");
                }
                break;
            case 3:
                MarcoDeDatos.printHeaders();
                flag = MarcoDeDatos.igualQue(campo, val);
                if (flag == false)
                {
                    System.out.println("No existen registros que cumplan esta condición");
                }
                break;
            case 4:
                MarcoDeDatos.contar();
                break;
            case 5:
                MarcoDeDatos.maximo(campo);
                break;
            case 6:
                MarcoDeDatos.minimo(campo);
                break;
        }
    } 
}