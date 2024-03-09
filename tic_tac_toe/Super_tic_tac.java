package tic_tac_toe;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Super_tic_tac{

    ArrayList<Integer> j1 = new ArrayList<>();
    ArrayList<Integer> j2 = new ArrayList<>();
    ArrayList<Integer> super_j1 = new ArrayList<>();
    ArrayList<Integer> super_j2 = new ArrayList<>();
    String turn = "X";
    int winner = 0;
    int active = -1;
    ArrayList<Integer> no_active = new ArrayList<>();
    int game = 0;

    int[][] win = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[][] win_super = duplicate(win, 9, 8);
    int[][] box_1 = {{0,9,18,27,36,45,54,63,72}};
    int[][] box_part = duplicate(box_1, 1, 8);
    int[][] part = {{0,1,2,3,4,5,6,7,8}};
    int[][] part_all = duplicate(part, 9, 8);

    public static int[][] push_it(int[] to_push, int[][] tabl){
        int[][] result = new int[tabl.length+1][];
        System.arraycopy(tabl, 0, result, 0, tabl.length);
        result[tabl.length] = to_push;
        return result;
    }

    public static int[][] duplicate(int[][] tabl,int ajout,int nb){
        int l_origin = tabl.length;
        int l_origin_i = tabl[0].length;
        int i_reserv = 0;
        int i_last = 0;
        for (int i = 0; i < nb; i++) {
            int[][] reserve = new int[l_origin][l_origin_i];
            i_last = tabl.length-1;
            i_reserv = 0;
            for (int j = 0; j < l_origin; j++) {
                for (int k = 0; k < l_origin_i; k++) {
                    reserve[i_reserv][k] = tabl[i_last][k]+ajout;
                }
                i_reserv++;
                i_last = i_last-1;
            }
            for (int l = 0; l < l_origin; l++) {
                tabl = push_it(reserve[l] , tabl);
            }
        }
        return tabl;
    }

    public Super_tic_tac(){

        JFrame fenetre = new JFrame("Super-Tic-Tac-Toe");
        fenetre.setLayout(new BorderLayout());

        JPanel body = new JPanel();
        body.setLayout(new GridLayout(3,3,10,10));
        body.setSize(new Dimension(730,730));

        fenetre.add(body,BorderLayout.CENTER);

        JPanel header = new JPanel();
        header.setLayout(new FlowLayout());
        header.setPreferredSize(new Dimension(100,50));

        fenetre.add(header,BorderLayout.NORTH);

        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout());
        footer.setPreferredSize(new Dimension(100,60));
        fenetre.add(footer,BorderLayout.SOUTH);

        JLabel turn_sup = new JLabel();
        turn_sup.setFont(new Font("arial",Font.BOLD,25));
        turn_sup.setText(""+turn);

        header.add(turn_sup);

        JPanel[] box = new JPanel[9];
        Border border_active = BorderFactory.createLineBorder(Color.blue,3);
        Border border_no_active = BorderFactory.createLineBorder(Color.red,3);

        JButton[] button = new JButton[81];
        int i_button = 0;

        JButton reload = new JButton();
        reload.setPreferredSize(new Dimension(50,50));
        reload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                begin();
                for (int i = 0; i < 81; i++) {
                    button[i].setText("");
                }
                turn_sup.setText(""+turn);
                for (int i = 0; i < 9; i++) {
                    box[i].setBorder(null);
                }
            }
        });

        footer.add(reload);
        
        for (int j = 0; j < 9; j++) {
            box[j] = new JPanel();
            box[j].setLayout(new GridLayout(3,3));
            for (int i = 0; i < 9; i++) {
                button[i_button] = new JButton();
                button[i_button].setFont(new Font("arial",Font.BOLD,20));
                int x = i_button;
                button[i_button].addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        int permission = 1;
                        for (int k = 0; k < no_active.size(); k++) {
                            if (verif_place(x)==no_active.get(k)) {
                                permission = 0;
                            }
                        }
                            if (action_verif(x) == 1 && !j1.contains(x) && !j2.contains(x) && game == 0 && permission==1) {
                                    // System.out.println("it works");
                                    button[x].setText(""+turn);
                                    if (turn.equals("X")) {
                                        j1.add(x);
                                        turn = "O";
                                    } else {
                                        j2.add(x);
                                        turn = "X";
                                    }
                                    
                                test_winner();
                                turn_sup.removeAll();
                                turn_sup.setText(""+turn);
                                if (game != 0) {
                                    if (turn=="O") {
                                        turn_sup.setText("X win");
                                    }
                                    else {
                                        turn_sup.setText("O win");
                                    }
                                }
                                active_control(x);
                                for(int i = 0; i<9; i++){
                                    if (i != active) {
                                        box[i].setBorder(null);
                                    }
                                }
                                for (int i = 0; i < no_active.size(); i++) {
                                    box[no_active.get(i)].setBorder(border_no_active);
                                }
                                if(active!=-1){
                                    box[active].setBorder(border_active);
                                }
                            }
                            System.out.println(game);
                        }
                });

                box[j].add(button[i_button]);
                i_button++;
            }
            body.add(box[j]);

            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setSize(600,700);
            fenetre.setLocationRelativeTo(null);
            fenetre.setVisible(true);
        }
    }
    public void test_winner(){
        int p1 = 0;
        int p2 = 0;
        for (int i = 0; i < win_super.length; i++) {
            p1 = 0;
            p2 = 0;
            for (int j = 0; j < win_super[i].length; j++) {
                if (j1.contains(win_super[i][j])) {
                    p1++;
                }
                if (j2.contains(win_super[i][j])){
                    p2++;
                }
            }
            if (p1 >= 3) {
                super_j1.add(verif_place(win_super[i][0]));
                no_active.add(verif_place(win_super[i][0]));
            }
            if (p2 >= 3) {
                super_j2.add(verif_place(win_super[i][0]));
                no_active.add(verif_place(win_super[i][0]));
            }

            
        }
        for (int i = 0; i < win.length; i++) {
            p1 = 0;
            p2 = 0;
            for (int j = 0; j < 3; j++) {
                if (super_j1.contains(win[i][j])) {
                    p1++;
                }
                if (super_j2.contains(win[i][j])){
                    p2++;
                }
            }
            if (p1 >= 3 || p2 >= 3) {
                game = 1;
                System.out.println("end");
            }
        }
    }
    public int verif_place(int a){
        int x = 0;
            for (int i = 0; i < part_all.length; i++) {
                for (int j = 0; j < 9; j++) {
                    if (part_all[i][j]==a) {
                        return i;
                    }
                }
            }
        return x;
    }
    public void begin(){
        j1.clear();
        j2.clear();
        super_j1.clear();
        super_j2.clear();
        no_active.clear();
        active = -1;
        // winner=0;
        game = 0;
    }
    public void active_control(int control){
        for (int i = 0; i < box_part.length; i++) {
            for (int j = 0; j < box_part[i].length; j++) {
                if (box_part[i][j] == control) {
                    active = i;
                }
            }
        }   
        for (int i = 0; i < no_active.size(); i++) {
            if (active==no_active.get(i)) {
                active = -1;
            }
        }
    }
    public int action_verif(int action){
        int result = 0;
        if (active==-1) {
            return 1;
        }
        for (int i = 0; i < part_all[active].length; i++) {
            if (action==part_all[active][i]) {
                result = 1;
            }
        }
        return result;
    }
}