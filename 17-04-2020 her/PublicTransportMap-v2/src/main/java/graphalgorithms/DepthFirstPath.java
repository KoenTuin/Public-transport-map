package graphalgorithms;

import model.TransportGraph;

public class DepthFirstPath extends AbstractPathSearch {
    public DepthFirstPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
    }

    @Override
    public void search() {
        search(startIndex);
    }

    private void search(int vertex) {
        marked[vertex] = true;
        nodesVisited.add(graph.getStation(vertex));

        for (int w : graph.getAdjacentVertices(vertex)) {
            if (!marked[w]) {
                edgeTo[w] = vertex;
                search(w);
            }

            if (w == endIndex) {
                pathTo(w);
                return;
            }
        }
    }
}
