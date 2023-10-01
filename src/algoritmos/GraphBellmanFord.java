package algoritmos;

// Un programa Java para el algoritmo de la ruta más corta de Bellman-Ford
// que encuentra la ruta más corta desde un único origen a todos los demás nodos,
// y detecta ciclos de peso negativo en un grafo dirigido y ponderado.

import java.lang.*;

// Una clase para representar un grafo conectado, dirigido y ponderado
public class GraphBellmanFord {

    // Una clase para representar una arista ponderada en el grafo
    public class Edge {
        public int src, dest, weight;

        public Edge() {
            src = dest = weight = 0;
        }
    }

    ;

    int V, E;
    public Edge edge[];

    // Crea un grafo con V vértices y E aristas
    public GraphBellmanFord(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    // La función principal que encuentra las distancias más cortas desde
    // src a todos los demás vértices utilizando el algoritmo Bellman-Ford
    // y detecta ciclos de peso negativo
    public void BellmanFord(GraphBellmanFord graphBellmanFord, int src) {
        int V = graphBellmanFord.V, E = graphBellmanFord.E;
        int dist[] = new int[V];

        // Paso 1: Inicializar las distancias desde src a todos los
        // otros vértices como INFINITO
        for (int i = 0; i < V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;

        // Paso 2: Relajar todas las aristas |V| - 1 veces. Un camino simple
        // más corto desde src a cualquier otro vértice puede tener como máximo |V| - 1 aristas
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = graphBellmanFord.edge[j].src;
                int v = graphBellmanFord.edge[j].dest;
                int weight = graphBellmanFord.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE
                        && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }

        // Paso 3: comprobar ciclos de peso negativo. El paso anterior garantiza
        // distancias más cortas si el grafo no contiene ciclos de peso negativo. Si obtenemos
        // un camino más corto, entonces hay un ciclo.
        for (int j = 0; j < E; ++j) {
            int u = graphBellmanFord.edge[j].src;
            int v = graphBellmanFord.edge[j].dest;
            int weight = graphBellmanFord.edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE
                    && dist[u] + weight < dist[v]) {
                System.out.println("El grafo contiene un ciclo de peso negativo");
                return;
            }
        }
        printArr(dist, V);
    }

    // Una función de utilidad utilizada para imprimir la solución
    void printArr(int dist[], int V) {
        System.out.println("Vértice Distancia desde la Fuente");
        for (int i = 0; i < V; ++i)
            System.out.println(i + "\t\t" + dist[i]);
    }
}
// Contribución de Aakash Hasija
