import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Bingo {

  public static void main(String[] args) {
    int[][] carton = new int[3][9];
    ArrayList<Integer> numerosGenerados = new ArrayList<>();

    int n;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++) {
        do {
          switch (j) {
            case 0: // Primera columna
              n = aleatorio(1, 9);
              break;

            case 8: // Última columna
              n = aleatorio(80, 90);
              break;

            default: // Columnas intermedias
              n = aleatorio(10 * j, (10 * j) + 9);
              break;
          }
        } while (numerosGenerados.contains(n));

        numerosGenerados.add(n);
        carton[i][j] = n;
      }
    }

    // Ordena las columnas
    for (int j = 0; j < 9; j++) {
      ArrayList<Integer> columna = sacaColumna(j, carton);
      Collections.sort(columna);
      meteColumna(j, columna, carton);
    }
    
    meteHuecos(carton);
    pintaCarton(carton);
  }

  public static int aleatorio(int min, int max) {
    return (int) (Math.random() * (max - min + 1) + min);
  }

  public static void pintaCarton(int[][] carton) {
    for (int[] fila : carton) {
      for (int numero : fila) {
        if (numero == 0) {
          System.out.print("   ");
        } else {
          System.out.printf("%2d ", numero);
        }
      }
      System.out.println();
    }
  }
  
  public static ArrayList<Integer> sacaColumna(int c, int[][] carton) {
    ArrayList<Integer> aux = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      aux.add(carton[i][c]);
    }
    return aux;
  }
  
  public static void meteColumna(int c, ArrayList<Integer> columna, int[][] carton) {
    for (int i = 0; i < 3; i++) {
      carton[i][c] = columna.get(i);
    }
  }
  
  public static void meteHuecos(int[][] carton) {
    for (int i = 0; i < 3; i++) {
      int huecos = 0;
      do {
        int posicion = aleatorio(0, 8);
        if (carton[i][posicion] != 0) {
          carton[i][posicion] = 0;
          huecos++;
        }
      } while (huecos < 4);
    } 
  }
}
