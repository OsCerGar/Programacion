package vista;

import java.awt.GridLayout;
import javax.swing.*;

public class MenuRestaurantVista extends JFrame{

    private JFrame frame;

    private JButton[] menuButtons = new JButton[7];

    private Integer amplada = 800;
    private Integer alcada = 600;

    /*  
    Heu d'inicialitzar els atributs d'aquesta classe fent el següent (no afegiu cap listener a cap control):
            
     - Heu d'inicialitzar l'objecte JFrame amb títol "Menú RESTAURANT" i layout Grid d'una columna
     - Heu de crear els botons del formulari. Cada botó serà un element de l'array menuBotons amb les següents etiquetes:
                        "0. Sortir"
                        "1. Alta"
                        "2. Seleccionar restaurant"
                        "3. Modificar restaurant"
                        "4. Llistar restaurants"
                        "5. Carregar restaurant"
                        "6. Desar restaurant"
      - Heu d'afegir-ho tot a l'atribut frame
      - Heu de fer visible el frame amb l'amplada i alçada que proposen els atributs amplada i alcada
      - Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
        
    */
    public MenuRestaurantVista() {
        
        frame = new JFrame("Menú RESTAURANT");
        frame.setLayout(new GridLayout(0,1));
       
        menuButtons[0] = new JButton("Sortir");
        menuButtons[1] = new JButton("Alta");
        menuButtons[2] = new JButton("Seleccionar restaurant");

        menuButtons[3] = new JButton("Modificar restaurant");
        menuButtons[4] = new JButton("Llistar restaurants");
        menuButtons[5] = new JButton("Carregar restaurant");
        menuButtons[6] = new JButton("Desar restaurant");

        frame.add(menuButtons[0]);
        frame.add(menuButtons[1]);
        frame.add(menuButtons[2]);
        frame.add(menuButtons[3]);
        frame.add(menuButtons[4]);
        frame.add(menuButtons[5]);
        frame.add(menuButtons[6]);

        setSize(amplada, alcada); //Li assignem mides a la finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton[] getMenuButtons() {
        return menuButtons;
    }

    public void setMenuButtons(JButton[] menuButtons) {
        this.menuButtons = menuButtons;
    }



}
