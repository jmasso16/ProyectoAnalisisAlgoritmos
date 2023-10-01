package algoritmos;

public class GFGMultistage {

    static int N = 8;
    static int INF = Integer.MAX_VALUE;

    // Devuelve la distancia más corta desde 0 hasta N-1.
    public static int shortestDist(int[][] graph) {

        // dist[i] va a almacenar la distancia más corta desde el nodo i hasta el nodo N-1.
        int[] dist = new int[N];

        dist[N - 1] = 0;

        // Calculando el camino más corto para el resto de los nodos
        for (int i = N - 2; i >= 0; i--) {

            // Inicializamos la distancia desde i hasta el destino (N-1)
            dist[i] = INF;

            // Comprobamos todos los nodos de las etapas siguientes
            // para encontrar la distancia más corta desde i hasta N-1.
            for (int j = i; j < N; j++) {
                // Rechazamos si no existe una arista
                if (graph[i][j] == INF) {
                    continue;
                }

                // Aplicamos la ecuación recursiva a la distancia hasta el destino a través de j.
                // y la comparamos con la distancia mínima hasta el momento.
                dist[i] = Math.min(dist[i],
                        graph[i][j] + dist[j]);
            }
        }

        return dist[0];
    }

}
