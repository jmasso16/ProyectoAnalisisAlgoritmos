package aplicacion;

import algoritmos.*;

public class Main {
    public static void main(String[] args) {

        /* Creemos el grafo de ejemplo discutido anteriormente */
        //Matriz de adyacencias para el algoritmo de Disjkstra
        int graphDisjktra[][]
                = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};
        ShortestPathDisjkstra dilkstra = new ShortestPathDisjkstra();

        // Llamada a la función
        dilkstra.dijkstra(graphDisjktra, 0);

        //Grafo para Bellman-Ford

        int V = 5; // Número de vértices en el grafo
        int E = 8; // Número de aristas en el grafo

        GraphBellmanFord graphBellmanFord = new GraphBellmanFord(V, E);

        // Agregar arista 0-1
        graphBellmanFord.edge[0].src = 0;
        graphBellmanFord.edge[0].dest = 1;
        graphBellmanFord.edge[0].weight = -1;

        // Agregar arista 0-2
        graphBellmanFord.edge[1].src = 0;
        graphBellmanFord.edge[1].dest = 2;
        graphBellmanFord.edge[1].weight = 4;

        // Agregar arista 1-2
        graphBellmanFord.edge[2].src = 1;
        graphBellmanFord.edge[2].dest = 2;
        graphBellmanFord.edge[2].weight = 3;

        // Agregar arista 1-3
        graphBellmanFord.edge[3].src = 1;
        graphBellmanFord.edge[3].dest = 3;
        graphBellmanFord.edge[3].weight = 2;

        // Agregar arista 1-4
        graphBellmanFord.edge[4].src = 1;
        graphBellmanFord.edge[4].dest = 4;
        graphBellmanFord.edge[4].weight = 2;

        // Agregar arista 3-2
        graphBellmanFord.edge[5].src = 3;
        graphBellmanFord.edge[5].dest = 2;
        graphBellmanFord.edge[5].weight = 5;

        // Agregar arista 3-1
        graphBellmanFord.edge[6].src = 3;
        graphBellmanFord.edge[6].dest = 1;
        graphBellmanFord.edge[6].weight = 1;

        // Agregar arista 4-3
        graphBellmanFord.edge[7].src = 4;
        graphBellmanFord.edge[7].dest = 3;
        graphBellmanFord.edge[7].weight = -3;

        // Llamada a la función
        graphBellmanFord.BellmanFord(graphBellmanFord, 0);


        //Grafo para Floyd-Warshall

        /* Creamos el siguiente grafo ponderado
		10
		(0)------->(3)
		|		 /|\
		5 |		 |
		|		 | 1
		\|/		 |
		(1)------->(2)
		3		 */

        final int INF = 99999; // Valor infinito para representar la ausencia de conexión.

        int grafoFloyd[][] = {{0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}};
        AllPairShortestPathFloyd a = new AllPairShortestPathFloyd();

        // Llamada a la función
        a.floydWarshall(grafoFloyd);

        //Grafo para Johnson

        /* Creamos el siguiente grafo ponderado
				10
		(0)------->(3)
			|		 /|\
		5 |		 |
			|		 | 1
		\|/		 |
		(1)------->(2)
				3		 */
        int[][] grafoJohnson = {{0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}};

        GFGJohnson gfgJohnson = new GFGJohnson();
        // Imprimir la solución
        gfgJohnson.floydWarshall(grafoJohnson);

        //Grafo para acíclico dirigido

        // Crear un grafo . Aquí los números de vértice son 0, 1, 2, 3, 4, 5 con las siguientes asignaciones:
        // 0=r, 1=s, 2=t, 3=x, 4=y, 5=z
        ShortestPathDirectedAcyclic t = new ShortestPathDirectedAcyclic();
        ShortestPathDirectedAcyclic.Graph g = t.newGraph(6);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);

        int s = 1;
        System.out.println("Las distancias más cortas desde la fuente " + s + " son:");
        g.shortestPath(s);

        //Grafo para Dial’s Algorithm

        // Crear el grafo como se muestra en la figura anterior
        int Ve = 9;
        GraphDial graphDial = new GraphDial(Ve);

        // Crear el grafo como se muestra en la figura anterior
        graphDial.AddEdge(0, 1, 4);
        graphDial.AddEdge(0, 7, 8);
        graphDial.AddEdge(1, 2, 8);
        graphDial.AddEdge(1, 7, 11);
        graphDial.AddEdge(2, 3, 7);
        graphDial.AddEdge(2, 8, 2);
        graphDial.AddEdge(2, 5, 4);
        graphDial.AddEdge(3, 4, 9);
        graphDial.AddEdge(3, 5, 14);
        graphDial.AddEdge(4, 5, 10);
        graphDial.AddEdge(5, 6, 2);
        graphDial.AddEdge(6, 7, 1);
        graphDial.AddEdge(6, 8, 6);
        graphDial.AddEdge(7, 8, 7);

        // Peso máximo de la arista: 14
        graphDial.shortestPath(0, 14);


        //Grafo para multistage
        // Grafo almacenado en forma de una Matriz de Adyacencia
        int[][] graphMultistage = new int[][] {
                { INF, 1, 2, 5, INF, INF, INF, INF },
                { INF, INF, INF, INF, 4, 11, INF, INF },
                { INF, INF, INF, INF, 9, 5, 16, INF },
                { INF, INF, INF, INF, INF, INF, 2, INF },
                { INF, INF, INF, INF, INF, INF, INF, 18 },
                { INF, INF, INF, INF, INF, INF, INF, 13 },
                { INF, INF, INF, INF, INF, INF, INF, 2 }
        };

        GFGMultistage gfgMultistage = new GFGMultistage();

        System.out.println(gfgMultistage.shortestDist(graphMultistage));// no esta imprimiendo 9 como dice en la pagina REVISAR
    }
}