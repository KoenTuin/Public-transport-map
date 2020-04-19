package graphalgorithms;

import model.Connection;
import model.IndexMinPQ;
import model.Station;
import model.TransportGraph;

import java.util.List;
import java.util.PriorityQueue;

public class DijkstraShortestPath extends AbstractPathSearch {

    private double[] distTo;
    private IndexMinPQ<Double> priorityQueue;

    public DijkstraShortestPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
        int numberOfStations = graph.getNumberOfStations();
//        edgeTo = new int[numberOfStations];
        distTo = new double[numberOfStations];
        priorityQueue = new IndexMinPQ<>(numberOfStations);

        for (int v = 0; v < numberOfStations; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[startIndex] = 0.0;

    }
    @Override
    public void search() {
        // relax vertices in order of distance from s
        priorityQueue = new IndexMinPQ<>(graph.getNumberOfStations());
        priorityQueue.insert(startIndex, distTo[startIndex]);
        while (!priorityQueue.isEmpty()) {
            int v = priorityQueue.delMin();
            nodesVisited.add(graph.getStation(v));
//            nodesVisitedAmount++;
            for (Integer vertex : graph.getAdjacentVertices(v)) {
                relax(graph.getConnection(v, vertex));
            }

        }

//        pathWeight = distTo[endIndex];
        pathTo(endIndex);
    }
    private void relax(Connection connection) {
        int v = graph.getIndexOfStationByName(connection.getFrom().getStationName()), w = graph.
                getIndexOfStationByName(connection.getTo().getStationName());
        System.out.println(v+" from = " + v + " to: " + w);
        if (distTo[w] > distTo[v] + connection.getWeight()) {
            distTo[w] = distTo[v] + connection.getWeight();
            edgeTo[w] = v;
            if (priorityQueue.contains(w)) {
                priorityQueue.decreaseKey(w, distTo[w]);
            } else {
                priorityQueue.insert(w, distTo[w]);
            }
        }
    }
//Own Code_________________________
//    @Override
//    public void search() {
//        priorityQueue.insert(startIndex, distTo[startIndex]);
//        while (!priorityQueue.isEmpty()){
//            relax(priorityQueue.delMin());
//        }
//        pathTo(startIndex);

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
//    }
//own code __________________________
//    private void relax(int v) {
//        nodesVisited.add(graph.getStation(v));
//        for (int e : graph.getAdjacentVertices(v)) {
//            Connection connection = graph.getConnection(v, e);
//
//            System.out.println("Aanliggende Station: " + connection.getTo());
//            int vertex = graph.getIndexOfStationByName(connection.getFrom().getStationName());
//            int w = graph.getIndexOfStationByName(connection.getTo().getStationName());
//            System.out.println(v + " from = " + vertex + " to: " + w);
//            if (distTo[w] > distTo[v] + connection.getWeight()) {
//                distTo[w] = distTo[v] + connection.getWeight();
//                edgeTo[w] = e;
//                if (priorityQueue.contains(w)) priorityQueue.changeKey(w, distTo[w]);
//                else priorityQueue.insert(w, distTo[w]);
//            }
//        }
//    }


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

        for (int i = 0; i < nodesInPath.size(); i++) {
            if (i++ > nodesInPath.size()) {
                break;
            }
            Station to = nodesInPath.get(i);
            Station from = nodesInPath.get(i++);
            Connection c = new Connection(to, from);
            totalWeight += c.getWeight();
        }



//        for (double distTo : distTo) {
//            if (distTo != Double.POSITIVE_INFINITY) {
//                totalWeight += distTo;
//            }
//        }

        return totalWeight;
    }
}
