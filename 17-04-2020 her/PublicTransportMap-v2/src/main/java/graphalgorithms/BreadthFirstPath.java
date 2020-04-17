package graphalgorithms;

import model.TransportGraph;

import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class BreadthFirstPath extends AbstractPathSearch {

    public BreadthFirstPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
    }

    @Override
    public void search() {
        Queue<Integer> queue = new ArrayDeque<>();      // Sedgewick uses queue, we chose to use an arrayDeque
        marked[startIndex] = true;                      // Mark the source
        queue.add(startIndex);                          //   and put it on the queue.
        nodesVisited.add(graph.getStation(startIndex)); // First vertex visited is startIndex.

        while (!queue.isEmpty()) {
            int v = queue.poll();                       // Remove next vertex from the queue.

            for (Integer w : graph.getAdjacentVertices(v)) {

                if (!marked[w]) {                       // For every unmarked adjacent vertex,
                    edgeTo[w] = v;                      //   save last edge on a shortest path,
                    marked[w] = true;                   //   mark it because path is known,
                    queue.add(w);                       //   and add it to the queue.
                    nodesVisited.add(graph.getStation(w));
                }

                if (w == endIndex) {
                    pathTo(w);
                    return;
                }
            }
        }
    }
}
