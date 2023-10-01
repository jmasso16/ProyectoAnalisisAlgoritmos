package algoritmos;

public class GFGJohnson {
    // Número de vértices en el grafo
    static final int V = 4;
    static final int INF = 99999;

    // Una función de utilidad para encontrar el vértice con el valor de distancia mínimo
    // del conjunto de vértices que aún no se han incluido en el árbol de rutas más cortas.
    static int minDistance(int[] dist, boolean[] sptSet) {
        // Inicializamos el valor mínimo
        int min = INF, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }

    // Una función de utilidad para imprimir la matriz de distancias construida
    static void printSolution(int[][] dist) {
        System.out.println(
                "La siguiente matriz muestra las distancias más cortas "
                        + "entre todos los pares de vértices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF)
                    System.out.printf("%7s", "INF");
                else
                    System.out.printf("%7d", dist[i][j]);
            }
            System.out.println();
        }
    }

    // Resuelve el problema de todas las rutas más cortas entre todos los pares
    // de vértices utilizando el algoritmo Floyd-Warshall.
   public static void floydWarshall(int[][] graph) {
        int[][] dist = new int[V][V];
        int i, j, k;

		/* Inicializamos la matriz de solución igual que la matriz de entrada
		del grafo. O podemos decir que los valores iniciales de las
		distancias más cortas se basan en las rutas más cortas
		considerando que no hay vértices intermedios. */
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist[i][j] = graph[i][j];

		/* Agregamos todos los vértices uno por uno al conjunto de
		vértices intermedios.
		---> Antes de comenzar una iteración, tenemos las distancias
		más cortas entre todos los pares de vértices de modo que
		las distancias más cortas consideran solo los vértices
		en el conjunto {0, 1, 2, .. k-1} como vértices intermedios.
		----> Al final de una iteración, el vértice no. k se agrega
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
        printSolution(dist);
    }

}

