package algoritmos;

import java.util.*;

public class GraphDial {
    static final int INF = Integer.MAX_VALUE;
    private int V; // Número de vértices
    // En un grafo ponderado, necesitamos almacenar el par de vértice y peso para cada arista
    private ArrayList<ArrayList<Tuple>> adj;

    public GraphDial(int v) // Constructor
    {
        this.V = v;
        this.adj = new ArrayList<ArrayList<Tuple>>();
        for (int i = 0; i < v; i++)
            this.adj.add(new ArrayList<Tuple>());
    }

    // Función para agregar una arista al grafo
    // Agrega una arista entre u y v con peso w
    public void AddEdge(int u, int v, int w) {
        adj.get(u).add(new Tuple(v, w));
        adj.get(v).add(new Tuple(u, w));
    }

    // Imprime las rutas más cortas desde src a todos los demás vértices.
    // W es el peso máximo de una arista
    public void shortestPath(int src, int W) {
		/* Con cada distancia, se almacena el iterador al vértice en su cubo para que el vértice se pueda eliminar en O(1) al actualizarlo. Por lo tanto,
		dist[i].first = distancia del i-ésimo vértice desde el vértice fuente dist[i].second = iterador al vértice i en el número del cubo */
        int[] dist = new int[V];

        // Inicializar todas las distancias como infinito (INF)
        Arrays.fill(dist, INF);

        // Crear cubos B[]
        // B[i] guarda el vértice de etiqueta de distancia i
        ArrayList<Integer>[] B = new ArrayList[W * V + 1];
        for (int i = 0; i < W * V + 1; i++)
            B[i] = new ArrayList<Integer>();

        B[0].add(src);
        dist[src] = 0;

        int idx = 0;
        while (true) {
            // Avanzar secuencialmente a través de los cubos hasta encontrar uno no vacío
            while (B[idx].size() == 0 && idx < W * V)
                idx++;

            // Si todos los cubos están vacíos, hemos terminado.
            if (idx == W * V)
                break;

            // Tomar el vértice superior del cubo y eliminarlo
            int u = B[idx].get(0);
            B[idx].remove(0);

            // Procesar todos los adyacentes del vértice extraído 'u'
            // y actualizar sus distancias si es necesario.
            for (Tuple i : adj.get(u)) {
                int v = i.v;
                int weight = i.w;

                int du = dist[u];
                int dv = dist[v];

                // Si hay un camino más corto a v a través de u.
                if (dv > du + weight) {
                    // Actualizar la distancia
                    dist[v] = du + weight;
                    dv = dist[v];

                    // Agregar el vértice v al cubo de distancia actualizado
                    B[dv].add(0, v);
                }
            }
        }

        // Imprimir las distancias más cortas almacenadas en dist[]
        System.out.println("Vértice Distancia desde la Fuente");
        for (int i = 0; i < V; ++i)
            System.out.println(i + "\t\t" + dist[i]);
    }

    static class Tuple {
        int v, w;

        Tuple(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

}
