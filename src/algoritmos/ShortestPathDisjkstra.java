package algoritmos;

// Un programa Java para el algoritmo de la ruta más corta de Dijkstra
// El programa es para la representación de la matriz de adyacencia del grafo

public class ShortestPathDisjkstra {
    // Una función de utilidad para encontrar el vértice con el valor de distancia mínimo,
    // del conjunto de vértices que aún no están incluidos en el árbol de ruta más corta
    static final int V = 9;
  public  int minDistance(int dist[], Boolean sptSet[]) {
        // Inicializar el valor mínimo
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    // Una función de utilidad para imprimir la matriz de distancias construida
    void printSolution(int dist[]) {
        System.out.println("Vértice \t\t Distancia desde la fuente");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }

    // Función que implementa el algoritmo de la ruta más corta de Dijkstra para un grafo
    // representado utilizando una matriz de adyacencia
    public void dijkstra(int graph[][], int src) {
        int dist[] = new int[V]; // La matriz de salida.
        // dist[i] contendrá
        // la distancia más corta desde src hasta i

        // sptSet[i] será verdadero si el vértice i está incluido en
        // el árbol de ruta más corta o si la distancia más corta desde src
        // hasta i está finalizada
        Boolean sptSet[] = new Boolean[V];

        // Inicializar todas las distancias como INFINITO y stpSet[]
        // como falso
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // La distancia desde el vértice fuente hasta sí mismo siempre es 0
        dist[src] = 0;

        // Encontrar la ruta más corta para todos los vértices
        for (int count = 0; count < V - 1; count++) {
            // Elegir el vértice con distancia mínima del conjunto
            // de vértices que aún no han sido procesados. u siempre es
            // igual a src en la primera iteración.
            int u = minDistance(dist, sptSet);

            // Marcar el vértice seleccionado como procesado
            sptSet[u] = true;

            // Actualizar el valor dist de los vértices adyacentes al
            // vértice seleccionado.
            for (int v = 0; v < V; v++)

                // Actualizar dist[v] solo si no está en sptSet,
                // hay una arista de u a v, y el peso total
                // del camino desde src hasta v a través de u es
                // menor que el valor actual de dist[v]
                if (!sptSet[v] && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // Imprimir la matriz de distancias construida
        printSolution(dist);
    }

    // Código del controlador
}
// Este código es contribución de Aakash Hasija
