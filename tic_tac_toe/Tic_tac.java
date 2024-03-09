package tic_tac_toe;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tic_tac{

    ArrayList<Integer> j1 = new ArrayList<>();
    ArrayList<Integer> j2 = new ArrayList<>();
    String turn = "X";
    int winner = 0;

    int[][] win = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public Tic_tac(){

        JFrame fenetre = new JFrame("Tic-Tac-Toe");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(460,660);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setLayout(new BorderLayout());

        JPanel body = new JPanel();
        body.setLayout(new GridLayout(3,3));
        body.setSize(new Dimension(450,450));

        fenetre.add(body,BorderLayout.CENTER);

        JPanel header = new JPanel();
        header.setLayout(new FlowLayout());
        header.setPreferredSize(new Dimension(100,75));

        fenetre.add(header,BorderLayout.NORTH);

        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout());
        footer.setPreferredSize(new Dimension(100,75));
        fenetre.add(footer,BorderLayout.SOUTH);

        JLabel turn_sup = new JLabel();
        turn_sup.setFont(new Font("arial",Font.BOLD,50));
        turn_sup.setText(""+turn);

        header.add(turn_sup);

        JButton[] button = new JButton[9];

        JButton reload = new JButton();
        reload.setPreferredSize(new Dimension(50,50));
        footer.add(reload);
        reload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                begin();
                for (int i = 0; i < button.length; i++) {
                    button[i].setText("");
                }
                turn_sup.setText(""+turn);
            }
        });

        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton();
            button[i].setFont(new Font("arial",Font.BOLD,50));
            int x = i;
            button[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!j1.contains(x) && !j2.contains(x) && winner ==0) {
                        System.out.println("it works");
                        button[x].setText(""+turn);
                        if (turn.equals("X")) {
                            j1.add(x);
                            turn = "O";
                        } else {
                            j2.add(x);
                            turn = "X";
                        }
                        
                    }
                    test_winner();
                    turn_sup.removeAll();
                    turn_sup.setText(""+turn);
                    if (winner!=0) {
                        if (turn=="O") {
                            turn_sup.setText("X win");
                        }
                        else {
                            turn_sup.setText("O win");
                        }
                    }
                }
            });
            body.add(button[i]);
        }
    }
    public void test_winner(){
        int p1 = 0;
        int p2 = 0;
        for (int i = 0; i < win.length; i++) {
            p1 = 0;
            p2 = 0;
            for (int j = 0; j < win[i].length; j++) {
                if (j1.contains(win[i][j])) {
                    p1++;
                }
                else if (j2.contains(win[i][j])){
                    p2++;
                }
            }
            if (p1 >= 3 || p2 >= 3) {
                winner=1;
                return;
            }
        }
    }
    public void begin(){
        j1.clear();
        j2.clear();
        winner=0;
    }
}