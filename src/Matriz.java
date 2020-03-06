import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Matriz {
    private int[][] M;
    private int orden;

    public Matriz(int orden){
        //Constructor basico de la matriz
        M = new int[orden+1][orden+1];
        this.orden = orden;
    }

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

    public void imprimirMatriz(){
        for(int i=1; i<=orden; i++){
            for(int j=1; j<=orden; j++){
                System.out.print(M[i][j]+"\t");
            }
            System.out.println("");
        }
    }

    public void sumaDatos(){
        long suma = 0L;
        for(int i=1; i<=orden; i++){
            for(int j=1; j<=orden; j++){
                suma = suma + M[i][j];
            }
        }
        System.out.println("La suma de los datos de la matriz es: " + suma);
    }

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

    public void mayorDatoFila(){
        System.out.println("Los mayores datos de cada columna: ");
        for(int i=1; i<=orden; i++){
            int mayor = M[i][1];
            for(int j=1; j<=orden; j++){
                if(M[i][j] > mayor){
                    mayor = M[i][j];
                }
            }
            System.out.println(i + ": " + mayor);
        }
    }

    public void menorDatocolumna(){
        System.out.println("Los menores datos por columna:");
        for(int j=1; j<=orden; j++){
            int menor = M[1][j];
            for(int i=1; i<=orden; i++){
                if(M[i][j] < menor){
                    menor = M[i][j];
                }
            }
            System.out.println(j + ": " + menor);
        }
    }

    public void mayorSumaDigitosFila(){
        System.out.println("El numero con mayor suma de digitos de cada fila es: ");
        for(int i=1; i<=orden; i++){
            int columna = 1;
            int mayor = sumaDigitos(M[1][1]);
            for(int j=1; j<=orden; j++){
                if(sumaDigitos(M[i][j]) > mayor) {
                    mayor = sumaDigitos(M[i][j]);
                    columna = j;
                }
            }
            System.out.println("Fila " + i + ": " + mayor + ". Columna: " + columna);
        }
    }

    public int sumaDigitos(int n){
        int suma = 0;
        while(n != 0){
            suma = suma + n%10;
            n = n/10;
        }
        return suma;
    }

    public int cuantosDigitos(int n){
        int i=1;
        while(n/10 != 0){
            n = n/10;
            i++;
        }
        return i;
    }

    public void frecuenciaDato(){
        Matriz F = new Matriz(orden);
        for(int i = 1; i <= orden; i++){
            for(int j = 1; j <= orden; j++){
                F.setDato(cuantoSeRepite(M[i][j]),i,j);
            }
        }
        for(int i = 1; i <= orden; i++){
            for(int j = 1; j <= orden; j++){
                System.out.print(M[i][j] + "=>" + F.getDato(i,j) +"\t");
            }
            System.out.println("");
        }
    }

    public int cuantoSeRepite(int d){
        int contador = 0;
        for(int i = 1; i<= orden; i++){
            for (int j = 1; j <= orden; j++){
                if(M[i][j] == d) contador++;
            }
        }
        return contador;
    }

    public void promedioTridiagonal(){
        long suma = 0L;
        for(int i = 0; i < orden; i++){
            suma += M[i][i] + M[i][i+1] + M[i+1][i];
        }
        suma += M[orden][orden];
        float promedio = (float)suma / (3*orden -2);
        System.out.println("La suma de los valores de la tridiagonal es: " + suma +
                " y su promedio es: " + promedio);
    }

    public void intercambiaFilaConColumna(int f, int c){
        for(int i = 1; i<=orden; i++){
            intercambiarDatos(f,i,i,c);
        }
    }

    public void ordenarAscendentePorFilas(){
        for(int i = 1; i <= orden*orden; i++){
            boolean cambios = false;
            for(int j = 0; j < orden*orden - i; j++){
                int ji = j/orden + 1;
                int jj = j%orden + 1;
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

    public void ordenarDescendentePorFilaUno(){
        for(int i = 1; i < orden; i++){
            for(int j = i+1; j <= orden; j++){
                if(M[1][i] < M[1][j]){
                    intercambiarColumna(i,j);
                }
            }
        }
    }

    public void ordenarAscendenteDigitosTridiagonalSegundaria(){
        for(int i = 1; i < orden; i++){
            ordenarDigitosAscendente(M[i][orden-i+1]);
            ordenarDigitosAscendente(M[i][orden-i]);
            ordenarDigitosAscendente(M[i+1][orden-i+1]);
        }
        ordenarDigitosAscendente(M[orden][orden]);
    }

    public void intercambiarDatos(int i, int j, int k, int l){
        int aux = M[i][j];
        M[i][j] = M[k][l];
        M[k][l] = aux;
    }

    public void intercambiarColumna(int i, int j){
        for(int k = 1; k < orden; k++){
            intercambiarDatos(k,i,k,j);
        }
    }

    public void setDato(int d, int i, int j){
        M[i][j] = d;
    }

    public int getDato(int i, int j){
        return M[i][j];
    }

    public int getOrden(){
        return orden;
    }

}
