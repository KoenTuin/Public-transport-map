package controller;

import graphalgorithms.AbstractPathSearch;
import graphalgorithms.BreadthFirstPath;
import graphalgorithms.DepthFirstPath;
import graphalgorithms.DijkstraShortestPath;
import model.Station;
import model.TransportGraph;

import java.util.List;

public class TransportGraphLauncher {

    public static void main(String[] args) {


//        assignmentA();
        assignmentB();

    }

    private static void assignmentA() {
        String[] redLine = {"red", "metro", "A", "B", "C", "D"};
        String[] blueLine = {"blue", "metro", "E", "B", "F", "G"};
        String[] greenLine = {"green", "metro", "H", "I", "C", "G", "J"};
        String[] yellowLine = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};

        TransportGraph transportGraph = new TransportGraph.Builder()
                .addLine(redLine)
                .addLine(blueLine)
                .addLine(greenLine)
                .addLine(yellowLine)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();

//        Uncomment to test the builder:
        System.out.println(transportGraph);

////        Uncommented to test the DepthFirstPath algorithm
        DepthFirstPath dfpTest = new DepthFirstPath(transportGraph, "E", "J");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();
        System.out.println();

//        Uncommented to test the BreadthFirstPath algorithm
        BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, "E", "J");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();

//        for (Station from : transportGraph.getStationList()) {
//            for (Station to : transportGraph.getStationList()) {
//                if (from.equals(to)) {
//                    continue;
//                }
//
//                DepthFirstPath dfpTest = new DepthFirstPath(transportGraph, from.getStationName(), to.getStationName());
//                dfpTest.search();
//
//                BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, from.getStationName(), to.getStationName());
//                bfsTest.search();
//
//                int bfsLength = bfsTest.getVerticesInPath().size();
//                int dfpLength = dfpTest.getVerticesInPath().size();
//
//                AbstractPathSearch fastest;
//
//                if (bfsLength > dfpLength) {
//                    fastest = dfpTest;
//                } else {
//                    fastest = bfsTest;
//                }
//
//                System.out.print(from.getStationName() + "-" + to.getStationName() + ": ");
//                List<Station> nodesInPath = fastest.getNodesInPath();
//                for (int i = 0; i < nodesInPath.size(); i++) {
//                    if (i > 0) {
//                        System.out.print("-");
//                    }
//                    System.out.print(nodesInPath.get(i));
//                }
//                System.out.println();
//            }
//
//        }
    }

    private static void assignmentB() {

        String[] redLine = {"red", "metro", "Haven", "Marken", "Steigerplein", "Centrum", "Meridiaan", "Dukdalf", "Oostvaarders"};
        double[] redLineWeights = {4.5, 4.7, 6.1, 3.5, 5.4, 5.6};
        int[] redLineLocation = {14, 1, 12, 3, 10, 5, 8, 8, 6, 9, 3, 10, 0, 11};
        String[] blueLine = {"blue", "metro", "Trojelaan", "Coltrane Cirkel", "Meridiaan", "Robijnpark", "Violetplantsoen"};
        double[] blueLineWeights = {6.0, 5.3, 5.1, 3.3};
        int[] blueLineLocation = {9, 3, 7, 6, 6, 9, 6, 12, 5, 14};
        String[] purpleLine = {"purple", "metro", "Grote Sluis", "Grootzeil", "Coltrane Cirkel", "Centrum", "Swingstraat"};
        double[] purpleLineWeights = {6.2, 5.2, 3.8, 3.6};
        int[] purpleLineLocation = {2, 3, 4, 6, 7, 6, 8, 8, 10, 9};
        String[] greenLine = {"green", "metro", "Ymeerdijk", "Trojelaan", "Steigerplein", "Swingstraat", "Bachgracht", "Nobelplein"};
        double[] greenLineWeights = {5.0, 3.7, 6.9, 3.9, 3.4};
        int[] greenLineLocation = {9, 0, 9, 3, 10, 5, 10, 9, 11, 11, 12, 13};
        String[] yellowLine = {"yellow", "bus", "Grote Sluis", "Ymeerdijk", "Haven", "Nobelplein", "Violetplantsoen", "Oostvaarders", "Grote Sluis"};
        double[] yellowLineWeights = {26, 19, 37, 25, 22, 28};
        int[] yellowLineLocation = {2, 3, 9, 0, 14, 1, 12, 13, 5, 14, 0, 11, 2, 3};

//        double sum = 0;
//        for (double weight : redLineWeights) {
//            sum += weight;
//        }for (double weight : blueLineWeights) {
//            sum += weight;
//        }for (double weight : purpleLineWeights) {
//            sum += weight;
//        }for (double weight : greenLineWeights) {
//            sum += weight;
//        }for (double weight : yellowLineWeights) {
//            sum += weight;
//        }
//        System.out.println("total weights: " + sum);

        TransportGraph transportGraph = new TransportGraph.Builder()
                .addLine(redLine)
                .addLine(blueLine)
                .addLine(purpleLine)
                .addLine(greenLine)
                .addLine(yellowLine)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();

        transportGraph.addWeights(redLine, redLineWeights);
        transportGraph.addWeights(blueLine, blueLineWeights);
        transportGraph.addWeights(purpleLine, purpleLineWeights);
        transportGraph.addWeights(greenLine, greenLineWeights);
        transportGraph.addWeights(yellowLine, yellowLineWeights);

        transportGraph.addLocations(redLine, redLineLocation);
        transportGraph.addLocations(blueLine, blueLineLocation);
        transportGraph.addLocations(purpleLine, purpleLineLocation);
        transportGraph.addLocations(greenLine, greenLineLocation);
        transportGraph.addLocations(yellowLine, yellowLineLocation);

//        Uncomment to test the builder:
//        System.out.println(transportGraph);
        String from = "Dukdalf";
        String to = "Grote Sluis";

//        Uncommented to test the DepthFirstPath algorithm
//        DepthFirstPath dfpTest = new DepthFirstPath(transportGraph, from, to);
//        dfpTest.search();
//        System.out.println(dfpTest);
//        dfpTest.printNodesInVisitedOrder();
//        System.out.println();
//
////        Uncommented to test the BreadthFirstPath algorithm
//        BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, from, to);
//        bfsTest.search();
//        System.out.println(bfsTest);
//        bfsTest.printNodesInVisitedOrder();

        DijkstraShortestPath dijkstra = new DijkstraShortestPath(transportGraph, from, to);
        dijkstra.search();
        System.out.println(dijkstra);
        dijkstra.printNodesInVisitedOrder();
        System.out.println("dijkstra.getTotalWeight() = " + dijkstra.getTotalWeight());
    }
}
