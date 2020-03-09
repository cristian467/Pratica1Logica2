import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/** Tema 3
 * Cristian Herrera
 * Roy Maestre
 */

public class Matriz {
    private int[][] M;
    private int orden;

    /**
     * Constructor de matriz basico
     * @param orden: recibe el mataño de la matriz cuadrada
     */
    public Matriz(int orden){
        M = new int[orden+1][orden+1];
        this.orden = orden;
    }

    /**Ejercicio 1: Se lee la matriz de un archivo
     * Constructor de matriz que la lee de un archivo
     * @throws IOException: arroja excepcion generada por la lectura del archivo
     */
    public Matriz() throws IOException {
        //Constructor de matriz que lee los datos desde un archivo de texto

        File archivo = new File(System.getProperty("user.dir"));
        String ruta = archivo.getAbsolutePath() + "\\Matriz.txt";

        BufferedReader leerArchivo = null;

        try{
            leerArchivo = new BufferedReader(new FileReader(ruta));
            String linea;
            int numeroLinea = 0;
            int orden = 0;
            /**
             * Se lee cada linea del archivo, la primera contiene el orden de la matriz.
             * Luego se dividen las lineas en un vector de string que contiene cada celda de la matriz
             */
            while((linea = leerArchivo.readLine()) != null){
                if(numeroLinea == 0){
                    orden = Integer.parseInt(linea);
                    M = new int[orden+1][orden+1];
                    this.orden = orden;
                    numeroLinea++;
                    continue;
                }
                String[] celdas = linea.split(" ");
                for (int j = 0; j < orden; j++){
                    M[numeroLinea][j+1] = Integer.parseInt(celdas[j]);
                }
                numeroLinea++;
            }
        }finally {
            if(leerArchivo == null){
                leerArchivo.close();
            }
        }
    }

    /**Ejercicio 2: Imprime la matriz
     * Impresión basica de la matriz
     */
    public void imprimirMatriz(){
        for(int i=1; i<=orden; i++){
            for(int j=1; j<=orden; j++){
                System.out.print(M[i][j]+"\t");
            }
            System.out.println("");
        }
    }

    /**Ejercicio 3: Imprime la suma de los datos de la matriz
     */
    public void sumaDatos(){
        long suma = 0L;
        for(int i=1; i<=orden; i++){
            for(int j=1; j<=orden; j++){
                suma = suma + M[i][j];
            }
        }
        System.out.println("La suma de los datos de la matriz es: " + suma);
    }

    /**Ejercicio 4: hallar mayor dato
     * Lo imprime acompañado de su posicion
     */
    public void mayorDato(){
        int mayor = M[1][1];
        int columnaMayor = 1, filaMayor = 1;
        for(int i=1; i<=orden; i++){
            for(int j=1; j<=orden; j++){
                if(M[i][j] > mayor){
                    mayor = M[i][j];
                    filaMayor = i;
                    columnaMayor = j;
                }
            }
        }
        System.out.println("El dato mayor de la matriz es: " + mayor +
                " ubicado en la fila: "+ filaMayor + " y en la columa: " + columnaMayor);
    }

    /** Ejercicio 5: halla el menor dato
     * Lo imprime acompañado de su posicion
     */
    public void menorDato(){
        int menor = M[1][1];
        int columnaMenor = 1, filaMenor = 1;
        for(int i=1; i<=orden; i++){
            for(int j=1; j<=orden; j++){
                if(M[i][j] < menor){
                    menor = M[i][j];
                    filaMenor = i;
                    columnaMenor = j;
                }
            }
        }
        System.out.println("El dato menor de la matriz es: " + menor +
                " ubicado en la fila: "+ filaMenor + " y en la columa: " + columnaMenor);
    }

    /**Ejercicio 6: Vector con mayor dato con fila
     * Imprime el mayor dato de cada fila
     */
    public void mayorDatoFila(){
        System.out.println("Los mayores datos de cada columna: ");
        for(int i=1; i<=orden; i++){
            int mayor = M[i][1];
            for(int j=1; j<=orden; j++){
                if(M[i][j] >= mayor){
                    mayor = M[i][j];
                }
            }
            System.out.println(i + ": " + mayor);
        }
    }

    /**Ejercicio 7: Vector con menor dato por columna
     * Imprime el menor dato de cada columna
     */
    public void menorDatocolumna(){
        System.out.println("Los menores datos por columna:");
        for(int j=1; j<=orden; j++){
            int menor = M[1][j];
            for(int i=1; i<=orden; i++){
                if(M[i][j] <= menor){
                    menor = M[i][j];
                }
            }
            System.out.println(j + ": " + menor);
        }
    }

    /** Ejercicio 8: muestra dato con mayor suma de digitos por fila
     *  Imprime el dato cuya suma de digitos sea la mayor de la fila, ademas dice en que columna esta.
     */
    public void mayorSumaDigitosFila(){
        System.out.println("El numero(s) con mayor suma de digitos de cada fila es: ");
        for(int i=1; i<=orden; i++){
            int columna = 1;
            //sumaDigitos, retorna la suma de los digitos del numero, ver codigo al final.
            int mayor = sumaDigitos(M[i][1]);
            for(int j=1; j<=orden; j++){
                if(sumaDigitos(M[i][j]) > mayor) {
                    mayor = sumaDigitos(M[i][j]);
                    columna = j;
                }
                for(int a=1; a<= orden; i++){
                    for(int b=1; b<orden; j++){
                        if(sumaDigitos(M[i][j]) == mayor){
                            System.out.println("El dato para el cual la suma de digitos es igual es:" +
                                    M[i][j]);
                        }
                    }
                }
            }
            System.out.print("Fila " + i + " : " + M[i][columna] + " en la columna: " + columna);
            //Se hace otra pasada por la fila para imprimir los que empatan en primer lugar
            for(int j = 1; j <= orden; j++){
                if(j != columna && sumaDigitos(M[i][j]) == mayor){
                    System.out.print(" y " + M[i][j] + " en la columna: " + j);
                }
            }
            System.out.println(".");
        }
    }

    /**Ejercicio 9: Ordena ascendeneteme la matriz, por filas
     * Usa el metodo burbuja mejorado para ordenar toda la matriz.
     */
    public void ordenarAscendentePorFilas(){
        /*Se hace un ciclo igual a el numero total de datos de la matriz, para facilitar el uso del metodo de
         ordenamiento y que cada recorrido sea menor al anterior*/
        for(int i = 1; i <= orden*orden; i++){
            boolean cambios = false;
            for(int j = 0; j < orden*orden - i; j++){
                /** Convierte el indice j que va de uno a orden^2, en los dos subindices de la matriz
                 * usando division entera y modulo.
                 */
                int ji = j/orden + 1;
                int jj = j%orden + 1;
                //Controla el caso de cuando se esta en la ultima casilla de la fila
                if(jj == orden){
                    if(M[ji][jj] > M[ji+1][1]){
                        intercambiarDatos(ji,jj,ji+1,1);
                        cambios = true;
                    }
                }
                else{
                    if(M[ji][jj] > M[ji][jj+1]){
                        intercambiarDatos(ji,jj,ji,jj+1);
                        cambios = true;
                    }
                }
            }
            if(!cambios) break;
        }
    }

    /**Ejercicio 10: Ordena cada fila descendenemente
     * Aplica metodo de insercion en cada fila.
     */
    public void ordenarCadaFilaDescendente(){
        for(int i=1; i<=orden; i++){
            for(int j=1; j<=orden; j++){
                for(int k=2; k<=orden; k++){
                    int d = M[i][k];
                    int f = k-1;
                    while((f>0) && (d>M[i][f])){
                        M[i][f+1] = M[i][f];
                        f = f-1;
                    }
                    M[i][f+1] = d;
                }
            }
        }
    }

    /** Ejercicio 11: Numero de veces que se repite cada dato
     * Imprime la matriz junto al numero de veces que se repite cada valor.
     */
    public void frecuenciaDato(){
        //Crea otra matriz para almacenar la frecuencia de repeticion de cada dato
        Matriz F = new Matriz(orden);
        for(int i = 1; i <= orden; i++){
            for(int j = 1; j <= orden; j++){
                //En cada posicion de la matriz llama el metodo
                //de cuanto se repite que devuelve la frecuencia del dato
                F.setDato(cuantoSeRepite(M[i][j]),i,j);
            }
        }
        //Imprime ambas matrices
        for(int i = 1; i <= orden; i++){
            for(int j = 1; j <= orden; j++){
                System.out.print(M[i][j] + "=>" + F.getDato(i,j) +"\t");
            }
            System.out.println("");
        }
    }

    /**Ejercicio 12: Muestra promedio datos tridiagonal
     * Recorre la tridiagonal, suma sus datos, saca el promedio y lo imprime.
     */
    public void promedioTridiagonal(){
        long suma = 0L;
        for(int i = 0; i < orden; i++){
            //La tridiagonal esta compuesta por cada dato de la
            // diagonal principal, el dato a su derecha y el de abajo
            suma += M[i][i] + M[i][i+1] + M[i+1][i];
        }
        //El ultimo dato de la diagonal no puede estar en el ciclo ya que no tiene dato
        // a la derecha ni abajo.
        suma += M[orden][orden];
        float promedio = (float)suma / (3*orden -2);
        System.out.println("La suma de los valores de la tridiagonal es: " + suma +
                " y su promedio es: " + promedio);
    }

    /**Ejercicio 13: Ordena descendentemente por fila 1
     * Ordena la fila 1 de la matriz, pero mantiene las columnas, moviendolas junto a cada valor de la
     * primera fila.
     *
     * Ejemplo:
     * 5 3 4        3 4 5
     * 1 2 6   =>   2 6 1
     * 6 8 1        8 1 6
     */
    public void ordenarDescendentePorFilaUno(){
        for(int i = 1; i < orden; i++){
            for(int j = i+1; j <= orden; j++){
                if(M[1][i] < M[1][j]){
                    intercambiarColumna(i,j);
                }
            }
        }
    }

    /**Ejercicio 14: Intercambia fila con columna
     * Recibe el indice de una fila y una columna y los intercambia
     * @param f Entero que almacena el indice de la fila a cambiar
     * @param c Entero que almacena el indice de la columna a cambiar
     */
    public void intercambiaFilaConColumna(int f, int c){
        for(int i = 1; i<=orden; i++){
            //Intercambia el primer dato de la fila con el primero de la columna, asi sucesivamente
            intercambiarDatos(f,i,i,c);
        }
    }

    /** Ejercicio 15: Ordena ascendentemente los digitos de cada dato en la tridiagonal segundaria
     * Recorre la tridiagonal segundaria y ordena sus digitos
     * Si el dato contiene un cero (0) se ubica en la primera posicion, por lo que no se muestra
     */
    public void ordenarAscendenteDigitosTridiagonalSegundaria(){
        {
            //Impresion inicial
            System.out.println("Los datos que cambiaran son: ");
            System.out.println(M[1][orden - 1] + " en la fila 1 y columna " + (orden - 1));
            System.out.println(M[1][orden] + " en la fila 1 y columna " + orden);
            for (int i = 2; i < orden; i++) {
                System.out.println(M[i][orden - i] + " en la fila " + i + " y columna " + (orden - i));
                System.out.println(M[i][orden - i + 1] + " en la fila " + i + " y columna " + (orden - i + 1));
                System.out.println(M[i][orden - i + 2] + " en la fila " + i + " y columna " + (orden - i + 2));
            }
            System.out.println(M[orden][1] + " en la fila " + orden + " y columna 1");
            System.out.println(M[orden][2] + " en la fila " + orden + " y columna 2");
        }
        //Se guarda una copia de la matriz
        Matriz copia = new Matriz(orden);
        copiarMatriz(copia);

        //Ordenamiento
        for(int i = 1; i < orden; i++){
            M[i][orden-i+1] = ordenarDigitosAscendente(M[i][orden-i+1]);
            M[i][orden-i] = ordenarDigitosAscendente(M[i][orden-i]);
            M[i+1][orden-i+1] = ordenarDigitosAscendente(M[i+1][orden-i+1]);
        }
        M[orden][1]= ordenarDigitosAscendente(M[orden][1]);

        {
            //Impresion
            System.out.println("Los cambios realizados fueron: ");
            System.out.println(copia.getDato(1,orden - 1) + "\t => " + M[1][orden - 1] + "\t en la fila 1 y columna " + (orden - 1));
            System.out.println(copia.getDato(1, orden) + "\t => " + M[1][orden] + "\t en la fila 1 y columna " + orden);
            for (int i = 2; i < orden; i++) {
                System.out.println(copia.getDato(i,orden - i) + "\t => "+ M[i][orden-i] + "\t en la fila " + i + " y columna " + (orden - i));
                System.out.println(copia.getDato(i,orden - i + 1) + "\t => " + M[i][orden -i + 1] + "\t en la fila " + i + " y columna " + (orden - i + 1));
                System.out.println(copia.getDato(i,orden - i + 2) + "\t => " + M[i][orden -i + 2] + "\t en la fila " + i + " y columna " + (orden - i + 2));
            }
            System.out.println(copia.getDato(orden,1) + "\t => " + M[orden][1] + "\t en la fila " + orden + " y columna 1");
            System.out.println(copia.getDato(orden,2) + "\t => " + M[orden][2] + "\t en la fila " + orden + " y columna 2");
        }

    }

    /**
     * Aqui en adelante se encuentran metodos extra que se usan en los metodos del ejercicio.
     */

    /**Usado en el ejercicio 8.
     * Recibe un entero y devuelve la suma de sus digitos
     * @param n Entero a analizar
     * @return Suma de los digitos de n
     */
    private int sumaDigitos(int n){
        int suma = 0;
        while(n != 0){
            suma = suma + n%10;
            n = n/10;
        }
        return suma;
    }

    /**Usado en el ejercicio 11
     *  Metodo que dice cuantas veces se repite un dato en la matriz
     * @param d Entero a analizar
     * @return Devuelve el numero de veces que esta d en la matriz.
     */
    private int cuantoSeRepite(int d){
        int contador = 0;
        for(int i = 1; i<= orden; i++){
            for (int j = 1; j <= orden; j++){
                if(M[i][j] == d) contador++;
            }
        }
        return contador;
    }

    /** Usado en los ejercicios 9, 13 y 14
     * Intercambia dos datos en la matriz
     * @param i Indice de la fila del primer dato
     * @param j Indice de la columna del primer dato
     * @param k Indice de la fila del segundo dato
     * @param l Indice de la columna del segundo dato
     */
    private void intercambiarDatos(int i, int j, int k, int l){
        int aux = M[i][j];
        M[i][j] = M[k][l];
        M[k][l] = aux;
    }

    /** Usado en el ejercicio 13
     *  Intercambia dos columnas
     * @param i Indice de una columna
     * @param j Indice de la otra columna
     */
    private void intercambiarColumna(int i, int j){
        for(int k = 1; k < orden; k++){
            intercambiarDatos(k,i,k,j);
        }
    }

    /**Usado en el ejercicio 15
     * Cuenta cuantos digitos tiene un numero
     * @param n entero a analizar
     * @return Numero de digitos de n.
     */
    private int cuantosDigitos(int n){
        int i=1;
        while(n/10 != 0){
            n = n/10;
            i++;
        }
        return i;
    }

    /** Usado en el ejercicio 15
     *  Ordena los digitos de un numero de manera ascendente
     *  Si el numero contiene un cero, se pondra de primero y no se mostrara.
     * @param d Entero a analizar
     * @return Devuelve d pero con los digitos ordenados ascedentemente.
     */
    private int ordenarDigitosAscendente(int d){
        //Cuenta cuantos digitos tiene d y crea un vector de ese tamaño
        int numeroDigitos = cuantosDigitos(d);
        int[] digitos = new int[numeroDigitos];
        //Rellena el vector con los digitos del numero
        for(int i = 0; i < numeroDigitos; i++){
            digitos[i] = d%10;
            d = d/10;
        }
        // Ordenamiento por Seleccion para los digitos de d
        for(int i = 0; i < digitos.length - 1; i++){
            for(int j = 1; j < digitos.length; j++){
                if(digitos[i] > digitos[j]){
                    int aux = digitos[i];
                    digitos[i] = digitos[j];
                    digitos[j] = aux;
                }
            }
        }
        //Agrega cada digito del numero al nuevo numero ordenado.
        int ordenado = 0;
        for(int i = 0; i < digitos.length; i++){
            ordenado = ordenado*10 + digitos[i];
        }
        return ordenado;
    }

    /** Usado en ejercicio 15
     * Copia los datos de una matriz a otra
     * @param copia Matriz ya instanciada, donde se almacenara la copia de esta matriz.
     */
    private void copiarMatriz(Matriz copia){
        for(int i = 1; i <= orden; i++){
            for(int j = 1; j <= orden; j++){
                copia.setDato(M[i][j], i, j);
            }
        }
    }

    /** Setters y Getters
     */
    private void setDato(int d, int i, int j){
        M[i][j] = d;
    }

    private int getDato(int i, int j){
        return M[i][j];
    }

    public int getOrden(){
        return orden;
    }

}
