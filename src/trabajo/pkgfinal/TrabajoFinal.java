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
        int opcion; //Opcion elegida por el usuario
        int numeroAlumnos = 0; //Numero de alumnos ingresados por el usuario
        String nombreArchivo = ""; //Nombre del archivo
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
                numeroAlumnos = pedirAlumnos(numeroAlumnos); //Ingreso del numero de alumnos a registrar
                String[][] registro = new String [numeroAlumnos][2]; //Creador del arreglo
                registro = llenarRegistro(registro); //Llenador del arreglo
                nombreArchivo = pedirNombre(nombreArchivo); //Peticion para asignarle un nombre al archhivo
                crearArchivo(nombreArchivo, registro); //Creacion del archivo y llenado del arreglo
                continuar();
                break;
            case 2:
                nombreArchivo = pedirNombre(nombreArchivo); //Peticion del nombre al archivo
                leerArchivo(nombreArchivo);  //Impresion del arreglo al usuario
                continuar();
                break;
            case 3:
                mensajeFinalizar(); //Impresion de que el programa ha finalizado
                break;
            default:
                mensajeFinalizar(); //Impresion de que el programa ha finalizado
                break;
        }
    }
    
    public static int pedirAlumnos(int alumnos){ //Metodo para que el usuario ingrese el numero de Alumnos que desea registrar
        Scanner entradaAlumnos  =new Scanner(System.in);
        System.out.println("Ingrese el numero de alumnos que desea registrar:");
        alumnos = entradaAlumnos.nextInt();
        return alumnos;
    }
    
    public static String pedirNombre(String nombreArchivo){ //Metodo que recibe un String(Nombre) y devuelve un string
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
                    System.out.println("Ingrese el nombre del alumno que desea registrar:"); //Peticion del nombre del alumno que se desea registrar
                    nombreAlumno = entradaNMalumno.nextLine();
                    for(int x=0; x<i; x++){
                        while(a[x][0].contains(nombreAlumno)){
                            System.out.println("El nombre del alumno que introdujo ya ha sido ingresado. Por favor ingrese uno diferente:"); //Impresion del mensaje de Error
                            nombreAlumno = entradaNMalumno.nextLine();
                        }
                    }
                    
                    a[i][j] = nombreAlumno; //Registra el nombre del alumno en el Arreglo
                }
                
                if(j==1){
                    System.out.println("Introduce la matricula del alumno que deseas registrar:"); //Peticion de la matricula del alumno que se desea registrar
                    matricula = entradaNMalumno.nextLine();
                    for(int x=0;x<i; x++){
                        while(a[x][1].contains(matricula)){
                            System.out.println("Esta matricula ya ha sido ingresada. Por favor introduzca una nueva matricula:"); //Impresion del mensaje de Error
                            matricula = entradaNMalumno.nextLine();
                        }
                    }
                    
                    a[i][j] = matricula; //Registra la matricula del alumno en el Arreglo
                }
            }
        }
        
        return a; //Regresa los valores registrados en el arreglo
    }
    
    public static void crearArchivo(String nombreArchivo, String[][] arr){ //Metodo para crear el Archivo donde se registran las Datos
        //Paso 1.- Instanciamos un objeto de la clase File 
        //Al instanciar escribimos como parámetro 
        //El nombre del archivo para manipularlo
        File archivo = new File(nombreArchivo + "Registro de Alumnos.txt");
        //Paso 2.- Si no existe el archivo
        if(!archivo.exists()){
            try{
            //try nos sirve para manejar excepciones. En caso de que algo
            //pueda salir mal.
            //Creamos un archivo nuevo.
                archivo.createNewFile();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }else{
            System.out.println("Este archivo ya existe. Por favor introduzca un nombre diferente para poder guardar el archivo:"); //Impresion del mensaje de Error
            nombreArchivo = pedirNombre(nombreArchivo);
            crearArchivo(nombreArchivo, arr);
        }
            //Paso 3.- Escritura en el archivo
        try{
            //Instanciamos un objeto de la clase PrintWriter
            //como parámetros enviamos el la instancia de File y el formato de
            //archivo de texto
            PrintWriter escribir = new PrintWriter (nombreArchivo, "utf-8");
            //Escribimos el contenido del archivo.
            escribir.println("Nombre\t|Matricula");
            for(int i=0; i<arr.length; i++){
                for(int j=0; j<arr[0].length; j++){
                    escribir.print(arr[i][j]);
                    if(j==0)escribir.print("\t| ");
                    //Si es el primero en la linea lista agrega una separacion en el texto
                }
                escribir.println();
            }
            //Cerramos el archivo.
            escribir.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void leerArchivo (String nombreArchivo){
        // Paso 1.- Instanciamos un objeto de la clase File y una variable cadena
        File archivo = new File(nombreArchivo + "Registro de Alumnos.txt");
        String cadena = "";
        try {
            FileReader lectura = new FileReader(nombreArchivo);
           BufferedReader bufferL = new BufferedReader(lectura);
           //Paso 2.- Recorremos el archivo.
           while (cadena!=null){ //Mientras la cadena no sea nula
               cadena = bufferL.readLine(); //Leemos líena por línea el archivo.
               if(cadena!=null) { //Si no encontramos un nulo dentro del archivo
                   System.out.println(cadena); //Imprime la cadena en la pantalla
        }
    }
           
    //Paso 3.- Cerramos las instancias de BufferedReader y FileReader.
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
    
    public static void continuar(){
        int opcion;
        Scanner entradaOpcion = new Scanner(System.in);
        System.out.println("¿Desea seguir ejecutando el programa?");
        System.out.println("1.- Si");
        System.out.println("0.- No");
        opcion = entradaOpcion.nextInt();
        if(opcion < 0 || opcion >1){
            continuar();
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
