package com.mst.prim;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComponent;

public class MinimumSpanningTree extends JComponent {

    private final boolean[] cizildiMi;
    private final List<Node> node;
    private final int[] kaynak;
    private final AdjacencyMatrix kM;

    public MinimumSpanningTree(List<Node> node, int kaynak[], AdjacencyMatrix kM) {
        this.node = node;
        this.kaynak = kaynak;
        this.kM = kM;
        this.cizildiMi = new boolean[node.size()];
        Arrays.fill(cizildiMi, false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Daha kaliteli çizimler için
        // https://stackoverflow.com/questions/19483834/improving-java-graphics2d-quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

        // Düğüm numarasının yazı genişliği
        FontMetrics fm = g2d.getFontMetrics();
        double yaziGenisligi = fm.getStringBounds("000", g2d).getWidth();

        int cap = 30; //Düğümlerin çapı
        int k = 1;
        int kx = 56;
        int ky = 34;

        for (int i = 1; i < node.size(); i++) {
            int x1 = (k + node.get(kaynak[i]).getX()) * kx - 30;
            int y1 = (k + node.get(kaynak[i]).getY()) * ky - 20;
            int x2 = (k + node.get(i).getX()) * kx - 30;
            int y2 = (k + node.get(i).getY()) * ky - 20;

            // Yolları çiz
            g2d.setColor(new Color(0, 128, 0));
            g2d.setStroke(new BasicStroke(3)); //Yol çizgilerinin kalınlığı
            g2d.drawLine(x1 + cap / 2, y1 + cap / 2, x2 + cap / 2, y2 + cap / 2);

            g2d.setFont(new Font("default", Font.BOLD, 13));

            // Düğümleri çiz(Zaten çizilmişse tekrar çizme)
            if (!cizildiMi[kaynak[i]]) {
                cizildiMi[kaynak[i]] = true;
                g2d.setColor(new Color(0, 128, 0));
                g2d.fillOval(x1, y1, cap, cap);
                g2d.setColor(Color.GREEN);
                g2d.drawString(Integer.toString(node.get(kaynak[i]).getId()), x1 + cap / 2 - (int) yaziGenisligi / 2, y1 + cap / 2 + 4);
            }

            if (!cizildiMi[i]) {
                cizildiMi[i] = true;
                g2d.setColor(new Color(0, 128, 0));
                g2d.fillOval(x2, y2, cap, cap);
                g2d.setColor(Color.GREEN);
                g2d.drawString(Integer.toString(node.get(i).getId()), x2 + cap / 2 - (int) yaziGenisligi / 2, y2 + cap / 2 + 4);
            }

            g2d.setColor(Color.GREEN);
            g2d.drawString(Integer.toString(node.get(kaynak[i]).getId()), x1 + cap / 2 - (int) yaziGenisligi / 2, y1 + cap / 2 + 4);
            g2d.drawString(Integer.toString(node.get(i).getId()), x2 + cap / 2 - (int) yaziGenisligi / 2, y2 + cap / 2 + 4);

            // Yolların uzunluğunu toString
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("default", Font.BOLD, 15));

            if(kM.getMatrix()[kaynak[i]][i] != 0)
                g2d.drawString(Integer.toString(kM.getMatrix()[kaynak[i]][i]),
                        x1 > x2 ? x2 + (x1 - x2) / 2 + 20 : x1 + (x2 - x1) / 2 + 17,
                        y1 > y2 ? y2 + (y1 - y2) / 2 : y1 + (y2 - y1) / 2 + 20);
        }
    }
}