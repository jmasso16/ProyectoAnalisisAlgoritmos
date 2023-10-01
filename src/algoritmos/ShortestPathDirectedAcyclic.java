package algoritmos;

// Programa Java para encontrar rutas más cortas desde una fuente única en Grafos Acíclicos Dirigidos

import java.util.*;

public class ShortestPathDirectedAcyclic {
    static final int INF = Integer.MAX_VALUE;

    class AdjListNode {
        private int v;
        private int weight;

        AdjListNode(int _v, int _w) { v = _v; weight = _w; }

        int getV() { return v; }
        int getWeight() { return weight; }
    }

    // Clase para representar el grafo como una lista de
    // nodos del tipo AdjListNode
   public class Graph {
        private int V;
        private LinkedList<AdjListNode> adj[];

        Graph(int v) {
            V = v;
            adj = new LinkedList[V];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList<AdjListNode>();
        }

        public void addEdge(int u, int v, int weight) {
            AdjListNode node = new AdjListNode(v, weight);
            adj[u].add(node); // Agregar v a la lista de u
        }

        // Una función recursiva utilizada por shortestPath.
        // Consulta el siguiente enlace para obtener detalles
        void topologicalSortUtil(int v, Boolean visited[], Stack stack) {
            // Marcar el nodo actual como visitado.
            visited[v] = true;
            Integer i;

            // Recorrer todos los vértices adyacentes a este vértice
            Iterator<AdjListNode> it = adj[v].iterator();
            while (it.hasNext()) {
                AdjListNode node = it.next();
                if (!visited[node.getV()])
                    topologicalSortUtil(node.getV(), visited, stack);
            }
            // Agregar el vértice actual a la pila que almacena el resultado
            stack.push(new Integer(v));
        }

        // La función para encontrar las rutas más cortas desde el vértice dado.
        // Utiliza topologicalSortUtil() de forma recursiva para obtener el orden topológico del grafo.
        public void shortestPath(int s) {
            Stack stack = new Stack();
            int dist[] = new int[V];

            // Marcar todos los vértices como no visitados
            Boolean visited[] = new Boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            // Llamar a la función auxiliar recursiva para almacenar el orden topológico
            for (int i = 0; i < V; i++)
                if (visited[i] == false)
                    topologicalSortUtil(i, visited, stack);

            // Inicializar las distancias a todos los vértices como infinito y la distancia a la fuente como 0
            for (int i = 0; i < V; i++)
                dist[i] = INF;
            dist[s] = 0;

            // Procesar los vértices en orden topológico
            while (stack.empty() == false) {
                // Obtener el próximo vértice del orden topológico
                int u = (int) stack.pop();

                // Actualizar las distancias de todos los vértices adyacentes
                Iterator<AdjListNode> it;
                if (dist[u] != INF) {
                    it = adj[u].iterator();
                    while (it.hasNext()) {
                        AdjListNode i = it.next();
                        if (dist[i.getV()] > dist[u] + i.getWeight())
                            dist[i.getV()] = dist[u] + i.getWeight();
                    }
                }
            }

            // Imprimir las distancias más cortas calculadas
            for (int i = 0; i < V; i++) {
                if (dist[i] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i] + " ");
            }
        }
    }

    // Método para crear una nueva instancia de grafo a través de un objeto
    // de la clase ShortestPath.
    public Graph newGraph(int number) {
        return new Graph(number);
    }

}
