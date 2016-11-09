/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajo.pkgfinal;

import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
//Libraría para el manejo de excepciones.
import java.io.IOException;
/**
 *
 * @author Samsung
 */
public class TrabajoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // MENU DE REGISTRO
        mostrarMenu();
    }
    
    public static void mostrarMenu(){
        int opcion;
        int numeroAlumnos = 0;
        String nombreArchivo = "";
        Scanner entradaOpcion = new Scanner(System.in);
        System.out.println(" *** MENU DE OPCIONES *** ");
        System.out.println("1.- Registrar Alumnos");
        System.out.println("2.- Mostrar registro");
        System.out.println("3.- Salir");
        opcion = entradaOpcion.nextInt();
        while (opcion < 1 || opcion > 3){
            System.out.println("Opcion incorrecta, introduzca una opcion valida:");
            opcion = entradaOpcion.nextInt();}
        switch(opcion){
            case 1:
                numeroAlumnos = pedirAlumnos(numeroAlumnos);
                String[][] registro = new String [numeroAlumnos][2];
                registro = llenarRegistro(registro);
                nombreArchivo = pedirNombre(nombreArchivo);
                crearArchivo(nombreArchivo, registro);
                contador();
                break;
            case 2:
                nombreArchivo = pedirNombre(nombreArchivo);
                leerArchivo(nombreArchivo);
                contador();
                break;
            case 3:
                mensajeFinalizar();
                break;
            default:
                mensajeFinalizar();
                break;
        }
    }
    
    public static int pedirAlumnos(int alumnos){
        Scanner entradaAlumnos  =new Scanner(System.in);
        System.out.println("Ingrese el numero de alumnos que desea registrar:");
        alumnos = entradaAlumnos.nextInt();
        return alumnos;
    }
    
    public static String pedirNombre(String nombreArchivo){
        Scanner entradaNombre = new Scanner(System.in);
        System.out.println("Ingrese el nombre con el cual desea guardar o buscar el Archivo:");
        nombreArchivo = entradaNombre.nextLine();
        return nombreArchivo;
    }
    
    public static String [][] llenarRegistro(String [][] a){
        String nombreAlumno, matricula;
        Scanner entradaNMalumno = new Scanner(System.in);
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[0].length; j++){
                if(j==0){
                    System.out.println("Ingrese el nombre del alumno que desea registrar:");
                    nombreAlumno = entradaNMalumno.nextLine();
                    for(int x=0; x<i; x++){
                        while(a[x][0].contains(nombreAlumno)){
                            System.out.println("El nombre del alumno que introdujo ya ha sido ingresado. Por favor ingrese uno diferente:");
                            nombreAlumno = entradaNMalumno.nextLine();
                        }
                    }
                    
                    a[i][j] = nombreAlumno;
                }
                
                if(j==1){
                    System.out.println("Introduce la matricula del alumno que deseas registrar:");
                    matricula = entradaNMalumno.nextLine();
                    for(int x=0;x<i; x++){
                        while(a[x][1].contains(matricula)){
                            System.out.println("Esta matricula ya ha sido ingresada. Por favor introduzca una nueva matricula:");
                            matricula = entradaNMalumno.nextLine();
                        }
                    }
                    
                    a[i][j] = matricula;
                }
            }
        }
        
        return a;
    }
    
    public static void crearArchivo(String nombreArchivo, String[][] arr){
        File archivo = new File(nombreArchivo + "Registro de Alumnos.txt");
        if(!archivo.exists()){
            try{
                archivo.createNewFile();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }else{
            System.out.println("Este archivo ya existe. Por favor introduzca un nombre diferente para poder guardar el archivo:");
            nombreArchivo = pedirNombre(nombreArchivo);
            crearArchivo(nombreArchivo, arr);
        }
        try{
            PrintWriter escribir = new PrintWriter (nombreArchivo, "utf-8");
            escribir.println("Nombre\t|Matricula");
            for(int i=0; i<arr.length; i++){
                for(int j=0; j<arr[0].length; j++){
                    escribir.print(arr[i][j]);
                    if(j==0)escribir.print("\t| ");
                }
                escribir.println();
            }
            escribir.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void leerArchivo (String nombreArchivo){
        File archivo = new File(nombreArchivo + "Registro de Alumnos.txt");
        String cadena = "";
        try {
            FileReader lectura = new FileReader(nombreArchivo);
           BufferedReader bufferL = new BufferedReader(lectura);
           //Paso 2. Recorremos el archivo.
           while (cadena!=null){ //Mientras la cadena no sea nula
               cadena = bufferL.readLine(); //Leemos líena por línea el archivo.
               if(cadena!=null) { //Si no encontramos null dentro del archivo
                   System.out.println(cadena); //Lo muestra en pantalla.
        }
    }
     bufferL.close();
           lectura.close();
        } catch (Exception e) {
            System.out.println(" *** ESTE ARCHIVO NO EXISTE *** ");
            
        }
    }
    
    public static void mensajeFinalizar(){
        System.out.println(" *** FIN DEL PROGRAMA *** ");
        System.exit(0);
    }
    
    public static void contador(){
        int opcion;
        Scanner entradaOpcion = new Scanner(System.in);
        System.out.println("¿Desea seguir ejecutando el programa?");
        System.out.println("1.- Si");
        System.out.println("0.- No");
        opcion = entradaOpcion.nextInt();
        if(opcion < 0 || opcion >1){
            contador();
        }else{
            switch(opcion){
                case 1:
                    main(null);
                    break;
                default: 
                    mensajeFinalizar();
                    break;
            }
        }
    }
}
