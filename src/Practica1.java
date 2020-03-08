import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Practica1 {

    Scanner leer = new Scanner(System.in);

    public void menu() throws IOException {
        Matriz matriz = new Matriz();
        int opcion = 0;
        do {
            System.out.println("Bienvenido al gestor de matrices.");
            System.out.println("Seleccione una opcion ingresando su indice.");
            System.out.println("1. Mostrar Matriz");
            System.out.println("2. Mostrar operaciones sobre toda la matriz");
            System.out.println("3. Mostrar operaciones por filas o columnas");
            System.out.println("4. Mostrar operaciones de ordenamiento");
            System.out.println("0. Salir");
            opcion = leerInt(0, 4);
            //Los numerales de la practica se dividieron en tres categorias para facilitar la visualizacion.
            switch (opcion) {
                case 1:
                    matriz.imprimirMatriz();
                    break;
                case 2:
                    menuOperacionesMatriz(matriz);
                    break;
                case 3:
                    menuOperacionesFilasColumnas(matriz);
                    break;
                case 4:
                    menuOperacionesOrdenamiento(matriz);
                    break;
                case 0:
                    System.out.println("Adios!");
                    break;

            }
        }while(opcion != 0);

    }

    /**
     * Ejercicios 3, 4, 5, 11 y 12
     * @param matriz Recibe la matriz a operar
     */
    public void menuOperacionesMatriz(Matriz matriz){
        int opcion = 0;
        do {
            System.out.println("Seleccione la operacion que desea realizar.");
            System.out.println("1. Hallar la suma de los terminos de la matriz");
            System.out.println("2. Hallar el mayor valor en la matriz");
            System.out.println("3. Hallar el menor valor en la matriz");
            System.out.println("4. Mostrar el numero de veces que se repite cada dato");
            System.out.println("5. Mostrar el promedio de los datos de la tridiagonal principal");
            System.out.println("0. Volver al menu principal");
            opcion = leerInt(0,5);
            switch (opcion){
                case 1:
                    matriz.sumaDatos();
                    break;
                case 2:
                    matriz.mayorDato();
                    break;
                case 3:
                    matriz.menorDato();
                    break;
                case 4:
                    matriz.frecuenciaDato();
                    break;
                case 5:
                    matriz.promedioTridiagonal();
                    break;
            }
        }while(opcion != 0);
    }

    /**
     * Ejercicios 6, 7, 8, 14
     * @param matriz Recibe la matriz a operar
     */
    public void menuOperacionesFilasColumnas(Matriz matriz){
        int opcion = 0;
        do {
            System.out.println("Seleccione la operacion que desea realizar.");
            System.out.println("1. Mostrar el mayor dato de cada fila");
            System.out.println("2. Mostrar el menor dato de cada columna");
            System.out.println("3. Mostrar el dato con la mayor suma de digitos en cada fila");
            System.out.println("4. Intercambiar los datos de una fila con los de una columna");
            System.out.println("0. Volver al menu principal");
            opcion = leerInt(0,4);
            switch (opcion){
                case 1:
                    matriz.mayorDatoFila();
                    break;
                case 2:
                    matriz.menorDatocolumna();
                    break;
                case 3:
                    matriz.mayorSumaDigitosFila();
                    break;
                case 4:
                    System.out.println("Ingrese indice de la fila a intercambiar: ");
                    int f = leerInt(1, matriz.getOrden());
                    System.out.println("Ingrese el indice de la columna a intercambiar: ");
                    int c =  leerInt(1, matriz.getOrden());
                    matriz.intercambiaFilaConColumna(f,c);
                    break;
            }
        }while(opcion != 0);
    }

    /**
     * Ejercicios 9, 10, 13 y 15
     * @param matriz Recibe la matriz a operar
     */
    public void menuOperacionesOrdenamiento(Matriz matriz){
        int opcion = 0;
        do {
            System.out.println("Seleccione la operacion que desea realizar.");
            System.out.println("1. Ordenar la matriz ascendentemente por filas");
            System.out.println("2. Ordenar cada fila descendentemente");
            System.out.println("3. Ordenar descendentemente por fila 1");
            System.out.println("4. Ordenar ascendentemente los digitos de cada dato de la tridiagonal segundaria");
            System.out.println("0. Volver al menu principal");
            opcion = leerInt(0,4);
            switch (opcion){
                case 1:
                    matriz.ordenarAscendentePorFilas();
                    break;
                case 2:
                    matriz.ordenarCadaFilaDescendente();
                    break;
                case 3:
                    matriz.ordenarDescendentePorFilaUno();
                    break;
                case 4:
                    matriz.ordenarAscendenteDigitosTridiagonalSegundaria();
                    break;
            }
        }while(opcion != 0);
    }

    /**
     * Metodo para leer enteros y manejar sus excepciones
     * @param min El menor valor que puede tomar dicho entero
     * @param max El mayor valor que puede tomar dicho entero
     * @return Un entero valido ingresado por el usuario
     */
    public int leerInt(int min, int max){
        int n = min-1;
        while(true){
            try{
                n = Integer.parseInt(leer.nextLine());
            }catch (InputMismatchException ex){
                System.out.println("El numero debe estar entre "+min+" y "+ max);
            }
            if(n<= max && n>= min) break;
            System.out.println("El numero debe estar entre "+min+" y "+max);
        }
        return n;
    }

    public static void main(String[] args) throws IOException {
        Practica1 start = new Practica1();
        start.menu();
    }
}

