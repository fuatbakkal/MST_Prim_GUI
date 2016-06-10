package com.mst.prim;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class MainWindow {
    private int rows, columns;
    private List<Node> node = new ArrayList<>();
    private JFrame frame = new JFrame("Show Minimum Spanning Tree using Prim's Algorithm");

    public MainWindow(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void show() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addElements(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private final ActionListener nodeButtonEvent = (ActionEvent e) -> {
        JButton button = (JButton) e.getSource();
        int nodeId = Integer.parseInt(button.getText());

        // If a new node is selected add to the list
        if (!button.isSelected()) {
            button.setSelected(true);
            button.setBackground(Color.GREEN);
            node.add(new Node(nodeId, columns));
        }
        // If node is already selected then remove from the list
        else {
            button.setSelected(false);
            button.setBackground(Color.LIGHT_GRAY);
            node.remove(new Node(nodeId, columns));
        }
    };

    private final ActionListener showMstEvent = (ActionEvent e) -> {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(node);
        System.out.println(adjacencyMatrix.toString());

        PrimsAlgorithm prim = new PrimsAlgorithm(node, adjacencyMatrix);
        prim.minimumSpanningTree(node);

        JFrame mstWindow = new JFrame("Minimum Spanning Tree");
        mstWindow.add(new MinimumSpanningTree(node, prim.getSource(), adjacencyMatrix));
        mstWindow.setSize(1295, 730);
        mstWindow.setVisible(true);
    };

    private void addElements(Container panel) {
        JButton[] nodeBtn = new JButton[rows * columns];
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);
        topBottomColumns(gbc, panel);
        leftRightRows(gbc, panel);
        nodeButtons(gbc, nodeBtn, panel);
        showMstButton(gbc, panel);
    }

    private void showMstButton(GridBagConstraints gbc, Container panel) {
        JButton mstButton = new JButton("Show Minimum Spanning Tree");
        gbc.weightx = 0.0;
        gbc.gridwidth = columns;
        gbc.gridx = 1;
        gbc.gridy = rows + 3;
        panel.add(mstButton, gbc);
        mstButton.addActionListener(showMstEvent);
    }

    private void nodeButtons(GridBagConstraints gbc, JButton[] nodeBtn, Container panel) {
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gbc.gridx = j + 1;
                gbc.gridy = i + 1;
                nodeBtn[k] = new JButton(Integer.toString(k));
                nodeBtn[k].setBackground(Color.LIGHT_GRAY);
                nodeBtn[k].addActionListener(nodeButtonEvent);
                panel.add(nodeBtn[k++], gbc);
            }
        }
    }

    private void leftRightRows(GridBagConstraints gbc, Container panel) {
        JLabel index;
        for (int i = 0; i < rows; i++) {
            // Left
            index = new JLabel("   " + Integer.toString(i) + " ");
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            panel.add(index, gbc);

            // Right
            index = new JLabel(" " + Integer.toString(i) + "   ");
            gbc.gridx = columns + 1;
            gbc.gridy = i + 1;
            panel.add(index, gbc);
        }
    }

    private void topBottomColumns(GridBagConstraints gbc, Container panel) {
        JLabel index;
        for (int i = 0; i < columns; i++) {
            // Top
            index = new JLabel(Integer.toString(i));
            index.setHorizontalAlignment(JLabel.CENTER);
            gbc.gridx = i + 1;
            gbc.gridy = 0;

            // Bottom
            panel.add(index, gbc);
            index = new JLabel(Integer.toString(i));
            index.setHorizontalAlignment(JLabel.CENTER);
            gbc.gridx = i + 1;
            gbc.gridy = rows + 2;
            panel.add(index, gbc);
        }
    }
}