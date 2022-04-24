package hw4_20001983_NgoPhuongTrang.bai6_tower;

import javax.swing.*;

public class Play {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TowerView towerView = new TowerView();
            towerView.setLayout(null);
            towerView.setVisible(true);
        });
    }
}
