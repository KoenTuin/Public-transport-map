package graphalgorithms;

import model.Connection;
import model.IndexMinPQ;
import model.TransportGraph;

import java.util.PriorityQueue;

public class DijkstraShortestPath extends AbstractPathSearch {

    private double[] distTo;
    private IndexMinPQ<Double> priorityQueue;

    public DijkstraShortestPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);

        int numberOfStations = graph.getNumberOfStations();
        edgeTo = new int[numberOfStations];
        distTo = new double[numberOfStations];
        priorityQueue = new IndexMinPQ<>(numberOfStations);

        for (int v = 0; v < numberOfStations; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[startIndex] = 0.0;

    }

    @Override
    public void search() {
        priorityQueue.insert(startIndex, 0.0);

        while (!priorityQueue.isEmpty()){
            System.out.println("priorityQueue = " + priorityQueue.size());
            relax(priorityQueue.delMin());
        }
        pathTo(endIndex);
//        while (!priorityQueue.isEmpty()) {
//
//            int vertex = priorityQueue.delMin();
//            nodesVisited.add(graph.getStation(vertex));
//            System.out.println(nodesVisited);
//
////            if (vertex == endIndex){
//////                pathTo(vertex);
////                return;
////            }
//            for (double pq: priorityQueue) {
//                relax(priorityQueue.delMin());
//            }
//
//        }
//        pathTo(endIndex);
    }

    private void relax(int v) {
        for (int e : graph.getAdjacentVertices(v)) {
            Connection to = graph.getConnection(v, e);
            System.out.println("Aanliggende Station: " + to.getTo());
            int vertex = graph.getIndexOfStationByName(to.getFrom().getStationName());
            int w = graph.getIndexOfStationByName(to.getTo().getStationName());
            System.out.println(v+" from = " + vertex + " to: " + w);
            if (distTo[w] > distTo[v] + to.getWeight()) {
                distTo[w] = distTo[v] + to.getWeight();
                edgeTo[w] = e;
                if (priorityQueue.contains(w)) priorityQueue.changeKey(w, distTo[w]);
                else priorityQueue.insert(w, distTo[w]);
            }
        }
    }


//    @Override
//    public void search() {
//        priorityQueue.insert(startIndex, 0.0);
//
//        while (!priorityQueue.isEmpty()) {
//            int vertex = priorityQueue.delMin();
////            if (vertex == endIndex) {
////                pathTo(vertex);
////                break;
////            }
//            relax(vertex);
//        }
//    }


//    private void relax(int fromVertexIndex) {
//        for (int toStationIndex : graph.getAdjacentVertices(fromVertexIndex)) {
//            nodesVisited.add(graph.getStation(toStationIndex)); //may add same station multiple times
//            Connection w = graph.getConnection(fromVertexIndex, toStationIndex);
//
//            if (w.getWeight() == 0) {
//                System.out.println();
//            }
//
//            if (distTo[toStationIndex] > distTo[fromVertexIndex] + w.getWeight()) {
//                distTo[toStationIndex] = distTo[fromVertexIndex] + w.getWeight();
//                edgeTo[toStationIndex] = toStationIndex;
//
//                if (priorityQueue.contains(toStationIndex)) {
//                    priorityQueue.changeKey(toStationIndex, distTo[toStationIndex]);
//                } else {
//                    priorityQueue.insert(toStationIndex, distTo[toStationIndex]);
//                }
//            }
//            if (toStationIndex == endIndex) {
//                System.out.println(distTo[endIndex] + w.getWeight());
//                break;
//            }
//
//        }
//    }

    @Override
    public boolean hasPathTo(int vertex) {
        return distTo[vertex] < Double.POSITIVE_INFINITY;
    }

    public double getTotalWeight() {
        double totalWeight = 0;

        for (double distTo : distTo) {
            if (distTo != Double.POSITIVE_INFINITY) {
                totalWeight += distTo;
            }
        }

        return totalWeight;
    }
}
