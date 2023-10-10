package algoritmos;
import java.util.*;
// Clase que representa un grafo no dirigido utilizando una lista de adyacencia
public class BFSBidirectionalShortestPath {



        private int V; // Número de nodos en el grafo
        private LinkedList<Integer>[] adj; // Lista de adyacencia para representar las conexiones entre nodos

        // Constructor
        @SuppressWarnings("unchecked")
        public BFSBidirectionalShortestPath(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++)
                adj[i] = new LinkedList<Integer>();
        }

        // Método para agregar una arista no dirigida
        public void addEdge(int u, int v) {
            adj[u].add(v);
            adj[v].add(u); // Ya que es un grafo no dirigido, la arista también debe ir en la dirección opuesta
        }

        // Método para realizar una búsqueda en amplitud (BFS)
        public void bfs(Queue<Integer> queue, Boolean[] visited, int[] parent) {
            int current = queue.poll();
            for (int i : adj[current]) {
                // Si el vértice adyacente no ha sido visitado previamente
                if (!visited[i]) {
                    parent[i] = current; // Establecer el vértice actual como padre del vértice adyacente
                    visited[i] = true; // Marcar el vértice adyacente como visitado
                    queue.add(i); // Agregar el vértice adyacente al final de la cola
                }
            }
        }

        // Método para verificar si existe un vértice de intersección entre dos búsquedas BFS
        public int isIntersecting(Boolean[] s_visited, Boolean[] t_visited) {
            for (int i = 0; i < V; i++) {
                // Si un vértice es visitado tanto por la búsqueda frontal (s) como por la búsqueda trasera (t)
                if (s_visited[i] && t_visited[i])
                    return i; // Devolver ese vértice como vértice de intersección
            }
            return -1; // Si no se encuentra ninguna intersección, devolver -1
        }

        // Método para imprimir el camino desde el origen hasta el destino
        public void printPath(int[] s_parent, int[] t_parent, int s, int t, int intersectNode) {
            LinkedList<Integer> path = new LinkedList<Integer>();
            path.add(intersectNode);
            int i = intersectNode;
            while (i != s) {
                path.add(s_parent[i]);
                i = s_parent[i];
            }
            Collections.reverse(path);
            i = intersectNode;
            while (i != t) {
                path.add(t_parent[i]);
                i = t_parent[i];
            }

            System.out.println("*****Camino*****");
            for (int it : path)
                System.out.print(it + " ");
            System.out.println();
        }

        // Método para realizar una búsqueda bidireccional
        public int biDirSearch(int s, int t) {
            // Arreglos booleanos para marcar nodos visitados por las búsquedas frontal (s) y trasera (t)
            Boolean[] s_visited = new Boolean[V];
            Boolean[] t_visited = new Boolean[V];

            // Arreglos para llevar un registro de los padres de los nodos para las búsquedas frontal (s) y trasera (t)
            int[] s_parent = new int[V];
            int[] t_parent = new int[V];

            // Colas para las búsquedas frontal (s) y trasera (t)
            Queue<Integer> s_queue = new LinkedList<Integer>();
            Queue<Integer> t_queue = new LinkedList<Integer>();

            int intersectNode = -1;

            // Inicialización necesaria
            for (int i = 0; i < V; i++) {
                s_visited[i] = false;
                t_visited[i] = false;
            }

            s_queue.add(s);
            s_visited[s] = true;

            // El padre del origen se establece en -1
            s_parent[s] = -1;

            t_queue.add(t);
            t_visited[t] = true;

            // El padre del destino se establece en -1
            t_parent[t] = -1;

            while (!s_queue.isEmpty() && !t_queue.isEmpty()) {
                // Realizar BFS desde los vértices de origen y destino
                bfs(s_queue, s_visited, s_parent);
                bfs(t_queue, t_visited, t_parent);

                // Comprobar si existe un vértice de intersección
                intersectNode = isIntersecting(s_visited, t_visited);

                // Si se encuentra un vértice de intersección, significa que existe un camino
                if (intersectNode != -1) {
                    System.out.printf("\nExiste un camino entre %d y %d\n", s, t);
                    System.out.printf("Intersección en: %d\n", intersectNode);

                    // Imprimir el camino y salir del programa
                    printPath(s_parent, t_parent, s, t, intersectNode);
                    System.exit(0);
                }
            }
            return -1;
        }

// Este código fue proporcionado por cavi4762.

}
