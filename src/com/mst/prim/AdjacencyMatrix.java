package com.mst.prim;

import java.util.List;

public final class AdjacencyMatrix {

    private int[][] adjacencyMatrix;
    private List<Node> node;

    public AdjacencyMatrix(List<Node> node) {
        this.node = node;
        adjacencyMatrix = new int[node.size()][node.size()];
        for (int i = 0; i < node.size(); i++) {
            for (int j = 1; j < node.size(); j++) {
                adjacencyMatrix[i][j] = Calculate.distance(node.get(i).getX(), node.get(j).getX(), node.get(i).getY(), node.get(j).getY());
            }
        }
    }

    public int[][] getMatrix() {
        return adjacencyMatrix;
    }

    // FIXME: 10.06.2016 : Row numbers are not properly shown
    public String toString() {
        String output = "    ";

        for (Node nodes : node) {
            output += String.format("%3d ", nodes.getId());
        }

        output += "\n";

        for (int i = 0; i < node.size(); i++) {
            System.out.format("%3d ", node.get(i).getId());
            for (int j = 0; j < node.size(); j++) {
                output += String.format("%3d ", adjacencyMatrix[i][j]);
            }
            output += "\n";
        }

        return output;
    }
}