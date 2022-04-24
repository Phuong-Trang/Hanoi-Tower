package hw4_20001983_NgoPhuongTrang.bai6_tower;

import hw4_20001983_NgoPhuongTrang.bai2.LinkedListStack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class TowerView extends JFrame {

    LinkedListStack<Disk> initTow = new LinkedListStack<>();
    LinkedListStack<Disk> helpTow = new LinkedListStack<>();
    LinkedListStack<Disk> goalTow = new LinkedListStack<>();
    int diskCount = 5;
    int stepCount = 0;

    public TowerView() {
        setTitle("Tower of Hanoi game Solver");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = diskCount; i > 0; i--)
            initTow.push(new Disk(40 * i));

        TextField tf = new TextField("STEP COUNT: " + stepCount);
        tf.setBounds(400, 75, 120, 20);
        add(tf);

        Button b = new Button("NEXT STEP");
        b.setBounds(200, 70, 80, 30);
        add(b);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText("STEP COUNT: " + (++stepCount));
                towerSolver();
                repaint();

                if (goalTow.size() == diskCount) {
                    tf.setBounds(290, 75, 160, 30);
                    tf.setText("YOU WIN WITH " + stepCount + " STEPS");
                    remove(b);
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Towers
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(125, 200, 20, 250);
        g.fillRect(355, 200, 20, 250);
        g.fillRect(585, 200, 20, 250);

        // Disks
        drawDisk(g, initTow, 125);
        drawDisk(g, helpTow, 355);
        drawDisk(g, goalTow, 585);
    }

    public void drawDisk(Graphics g, LinkedListStack<Disk> tower, int xPos) {
        Iterator<Disk> iterator = tower.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Disk currentDisk = iterator.next();
            int x = xPos + (20 - currentDisk.length) / 2;
            int y = 450 - 40 * (tower.size() - i++);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, currentDisk.length, 40);
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, currentDisk.length, 40);
        }
    }

    public void towerSolver() {
        if (diskCount % 2 == 0) {
            if (stepCount % 3 == 1)
                moveDisk(initTow, helpTow);
            else if (stepCount % 3 == 2)
                moveDisk(initTow, goalTow);
            else
                moveDisk(helpTow, goalTow);
        } else {
            if (stepCount % 3 == 1)
                moveDisk(initTow, goalTow);
            else if (stepCount % 3 == 2)
                moveDisk(initTow, helpTow);
            else
                moveDisk(goalTow, helpTow);
        }
    }

    public void moveDisk(LinkedListStack<Disk> tow1, LinkedListStack<Disk> tow2) {
        if (tow1.isEmpty())
            tow1.push(tow2.pop());
        else if (tow2.isEmpty())
            tow2.push(tow1.pop());
        else {
            if (tow1.top().length > tow2.top().length)
                tow1.push(tow2.pop());
            else
                tow2.push(tow1.pop());
        }
    }
}
