package com.mst.prim;

import java.util.List;

public class PrimsAlgorithm {

    private final boolean[] unsettled;
    private final boolean[] settled;
    private final int key[];
    private final int numberOfNodes;
    private final int infinite = 999;
    private final int[] source;
    private AdjacencyMatrix matrix;

    public PrimsAlgorithm(List<Node> node, AdjacencyMatrix matrix) {
        this.matrix = matrix;
        numberOfNodes = node.size();
        unsettled = new boolean[numberOfNodes];
        settled = new boolean[numberOfNodes];
        key = new int[numberOfNodes];
        source = new int[numberOfNodes];
        algorithm();
    }

    private int getUnsettledCount(boolean unsettled[]) {
        int counter = 0;
        for (boolean Unsettled : unsettled) {
            if (Unsettled) {
                counter++;
            }
        }

        return counter;
    }

    private void algorithm() {
        int currentNode;

        for (int index = 0; index < numberOfNodes; index++) {
            key[index] = infinite;
        }

        key[0] = 0;
        unsettled[0] = true;
        source[0] = 0;

        while (getUnsettledCount(unsettled) != 0) {
            currentNode = getClosestNode();
            unsettled[currentNode] = false;
            settled[currentNode] = true;
            checkNeighbors(currentNode);
        }
    }

    private int getClosestNode() {
        int min = Integer.MAX_VALUE;
        int node = 0;
        for (int i = 0; i < numberOfNodes; i++) {
            if (unsettled[i] && key[i] < min) {
                node = i;
                min = key[i];
            }
        }
        return node;
    }

    private void checkNeighbors(int node) {
        for (int i = 0; i < numberOfNodes; i++) {
            if (!settled[i]) {
                if (matrix.getMatrix()[node][i] != infinite) {
                    if (matrix.getMatrix()[node][i] < key[i]) {
                        key[i] = matrix.getMatrix()[node][i];
                        source[i] = node;
                    }
                    unsettled[i] = true;
                }
            }
        }
    }

    public final int[] getSource() {
        return source;
    }

    public final void minimumSpanningTree(List<Node> node) {
        System.out.println(" Source -> Destination : Weight");
        for (int i = 1; i < numberOfNodes; i++) {
            System.out.println(node.get(source[i]).getId() + "\t:\t" + node.get(i).getId() + "\t=\t" + matrix.getMatrix()[source[i]][i]);
        }
    }
}