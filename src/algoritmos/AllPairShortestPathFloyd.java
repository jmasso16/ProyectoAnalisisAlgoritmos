package algoritmos;

import java.io.*;
import java.lang.*;
import java.util.*;
// Programa Java para el algoritmo de Floyd-Warshall para encontrar
// todas las rutas más cortas entre todos los pares de vértices.

public class AllPairShortestPathFloyd {


    final static int INF = 99999; // Valor infinito para representar la ausencia de conexión.
    final static int V = 4; // Número de vértices en el grafo.

    public void floydWarshall(int dist[][]) {
        int i, j, k;

		/* Agregamos todos los vértices uno por uno
		al conjunto de vértices intermedios.
		---> Antes de comenzar una iteración,
			tenemos las distancias más cortas entre todos los pares
			de vértices de modo que
			las distancias más cortas consideran
			solo los vértices en
			el conjunto {0, 1, 2, .. k-1} como
			vértices intermedios.
		----> Al final de una iteración,
				el vértice no. k se agrega
				al conjunto de vértices intermedios y el conjunto
				se convierte en {0, 1, 2, .. k}. */
        for (k = 0; k < V; k++) {
            // Elegimos todos los vértices como fuente uno por uno
            for (i = 0; i < V; i++) {
                // Elegimos todos los vértices como destino para el
                // origen seleccionado anteriormente
                for (j = 0; j < V; j++) {
                    // Si el vértice k está en el camino más corto
                    // desde i hasta j, entonces actualizamos el valor de
                    // dist[i][j]
                    if (dist[i][k] + dist[k][j]
                            < dist[i][j])
                        dist[i][j]
                                = dist[i][k] + dist[k][j];
                }
            }
        }

        // Imprimimos la matriz de distancias más cortas
        imprimirSolucion(dist);
    }

    void imprimirSolucion(int dist[][]) {
        System.out.println(
                "La siguiente matriz muestra las distancias más cortas "
                        + "entre todos los pares de vértices");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

// Contribución de Aakash Hasija

}
