package graphalgorithms;

import model.Connection;
import model.Line;
import model.Station;
import model.TransportGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Abstract class that contains methods and attributes shared by the DepthFirstPath en BreadthFirstPath classes
 */
public abstract class AbstractPathSearch {

    protected boolean[] marked;
    protected int[] edgeTo;
    protected int transfers = 0;
    protected List<Station> nodesVisited;
    protected List<Station> nodesInPath;
    protected LinkedList<Integer> verticesInPath;
    protected TransportGraph graph;
    protected final int startIndex;
    protected final int endIndex;

    public List<Station> getNodesInPath() {
        return nodesInPath;
    }

    public LinkedList<Integer> getVerticesInPath() {
        return verticesInPath;
    }

    public AbstractPathSearch(TransportGraph graph, String start, String end) {
        startIndex = graph.getIndexOfStationByName(start);
        endIndex = graph.getIndexOfStationByName(end);
        this.graph = graph;
        nodesVisited = new ArrayList<>();
        marked = new boolean[graph.getNumberOfStations()];
        edgeTo = new int[graph.getNumberOfStations()];
    }

    public abstract void search();

    /**
     * @param vertex Determines whether a path exists to the station as an index from the start station
     * @return
     */
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }


    /**
     * Method to build the path to the vertex, index of a Station.
     * First the LinkedList verticesInPath, containing the indexes of the stations, should be build, used as a stack
     * Then the list nodesInPath containing the actual stations is build.
     * Also the number of transfers is counted.
     *
     * @param vertex The station (vertex) as an index
     */
    public void pathTo(int vertex) {
        if (!hasPathTo(vertex)) {
            return;
        }
        verticesInPath = new LinkedList<>();

        for (int x = vertex; x != startIndex; x = edgeTo[x]) {
            verticesInPath.push(x);
        }

        verticesInPath.push(startIndex); // path starts from startIndex

        nodesInPath = new LinkedList<>();
        for (Integer stationIndex : verticesInPath) {
            nodesInPath.add(graph.getStation(stationIndex));
        }

        countTransfers();
    }

    /**
     * Method to count the number of transfers in a path of vertices.
     * Uses the line information of the connections between stations.
     * If two consecutive connections are on different lines there was a transfer.
     */
    public void countTransfers() {

        Line currentLine = null;

        for (int i = 0; i < verticesInPath.size() - 1; i++) {
            Connection connection = graph.getConnection(verticesInPath.get(i), verticesInPath.get(i + 1));

            if (currentLine == null) {
                currentLine = connection.getLine();
            } else if (!connection.getLine().equals(currentLine)) {
                transfers++;
            }
            currentLine = connection.getLine();
        }
    }


    /**
     * Method to print all the nodes that are visited by the search algorithm implemented in one of the subclasses.
     */
    public void printNodesInVisitedOrder() {
        System.out.print("Nodes in visited order: ");
        for (Station vertex : nodesVisited) {
            System.out.print(vertex.getStationName() + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder(String.format("Path from %s to %s: ", graph.getStation(startIndex), graph.getStation(endIndex)));
        resultString.append(nodesInPath).append(" with " + transfers).append(" transfers");
        return resultString.toString();
    }

}
