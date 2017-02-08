package vista;

import java.awt.GridLayout;
import javax.swing.*;

public class RestaurantLlista extends JFrame{

    private JFrame frame;
    private Integer amplada = 600;
    private Integer alcada = 200;
    
    private JTable taulaRestaurant;

    private JButton bSortir;   
    
/*
  Heu d'inicialitzar els atributs d'aquesta classe fent el següent (no afegiu cap listener a cap control):
            
    - Heu d'inicialitzar l'objecte JFrame amb títol "Llistat de biblioteques" i layout Grid d'una columna
    - Heu d'inicialitzar l'objecte Jtable amb un nou objecte de RestaurantTaula
    - Heu d'inicialitzar l'objecte JButton amb nom "Sortir".
    - Heu d'afegir-ho tot a l'atribut frame
    - Heu de fer visible el frame amb l'amplada i alçada que proposen els atributs alcada i amplada
    - Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
       
    */
    public RestaurantLlista() {
        
        RestaurantTaula Restaurantt = new RestaurantTaula();
        
        JPanel Llistatdebiblioteques = new JPanel();

        frame = new JFrame("Llistatdebiblioteques");
        frame.setLayout(new GridLayout(0,1));

        taulaRestaurant = new JTable(Restaurantt);

        bSortir = new JButton("Sortir");
        
        bSortir.add(taulaRestaurant);
        taulaRestaurant.add(frame);

        setSize(amplada, alcada); 
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(Llistatdebiblioteques);

        setVisible(true);
    }


    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTable getTaulaRestaurant() {
        return taulaRestaurant;
    }

    public void setTaulaRestaurant(JTable pTaulaRestaurant) {
        taulaRestaurant = pTaulaRestaurant;
    }    
    
    public JButton getSortir() {
        return bSortir;
    }

    public void setSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }

}
