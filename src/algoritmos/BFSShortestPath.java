package algoritmos;
import java.util.ArrayList;
import java.util.LinkedList;
public class BFSShortestPath {


        // Función para formar una arista entre dos vértices fuente y destino
        public static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
            adj.get(i).add(j);
            adj.get(j).add(i);
        }

        // Función para imprimir la distancia más corta y el camino entre el vértice fuente y el vértice destino
        public static void printShortestDistance(ArrayList<ArrayList<Integer>> adj, int s, int dest, int v) {
            // El arreglo predecessor[i] almacena el predecesor de i y el arreglo distance almacena la distancia de i desde s
            int pred[] = new int[v];
            int dist[] = new int[v];

            if (BFS(adj, s, dest, v, pred, dist) == false) {
                System.out.println("La fuente y el destino dados no están conectados");
                return;
            }

            // LinkedList para almacenar el camino
            LinkedList<Integer> path = new LinkedList<Integer>();
            int crawl = dest;
            path.add(crawl);
            while (pred[crawl] != -1) {
                path.add(pred[crawl]);
                crawl = pred[crawl];
            }

            // Imprimir la distancia
            System.out.println("La longitud del camino más corto es: " + dist[dest]);

            // Imprimir el camino
            System.out.println("El camino es ::");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + " ");
            }
        }

        // Una versión modificada de BFS que almacena el predecesor de cada vértice en el arreglo pred y su distancia desde la fuente en el arreglo dist
        private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src, int dest, int v, int pred[], int dist[]) {
            // Una cola para mantener la cola de vértices cuya lista de adyacencia debe ser escaneada según el algoritmo BFS normal usando LinkedList de tipo Integer
            LinkedList<Integer> queue = new LinkedList<Integer>();

            // Un arreglo booleano visited[] que almacena la información sobre si el i-ésimo vértice se ha alcanzado al menos una vez en la búsqueda en anchura
            boolean visited[] = new boolean[v];

            // Inicialmente, todos los vértices son no visitados, por lo que v[i] para todo i es false y como aún no se ha construido un camino, dist[i] para todo i se establece en infinito
            for (int i = 0; i < v; i++) {
                visited[i] = false;
                dist[i] = Integer.MAX_VALUE;
                pred[i] = -1;
            }

            // Ahora, la fuente es la primera en ser visitada y la distancia desde la fuente hasta ella misma debe ser 0
            visited[src] = true;
            dist[src] = 0;
            queue.add(src);

            // Algoritmo BFS
            while (!queue.isEmpty()) {
                int u = queue.remove();
                for (int i = 0; i < adj.get(u).size(); i++) {
                    if (visited[adj.get(u).get(i)] == false) {
                        visited[adj.get(u).get(i)] = true;
                        dist[adj.get(u).get(i)] = dist[u] + 1;
                        pred[adj.get(u).get(i)] = u;
                        queue.add(adj.get(u).get(i));

                        // Condición de parada (cuando encontramos nuestro destino)
                        if (adj.get(u).get(i) == dest)
                            return true;
                    }
                }
            }
            return false;
        }

// Este código ha sido contribuido por Sahil Vaid
}
