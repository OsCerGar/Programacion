package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class MenuPrincipalVista extends JFrame  {

    private JFrame frame;

    private JButton[] menuButtons = new JButton[3];

    private Integer amplada = 800;
    private Integer alcada = 600;

    
        
    /* 
    Heu d'inicialitzar els atributs d'aquesta classe fent el següent (no afegiu cap listener a cap control):
            
     - Heu d'inicialitzar l'objecte JFrame amb títol "Menú Principal" i layout Grid d'una columna
     - Heu de crear els botons del formulari. Cada botó serà un element de l'array menuBotons amb les següents etiquetes:
                        "0. Sortir"
                        "1. Menú Restaurant"
                        "2. Menú Reserves"
     - Heu d'afegir-ho tot a l'atribut frame
     - Heu de fer visible el frame amb l'amplada i alçada que proposen els atributs amplada i alcada
     - Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
        
    */ 
    
    public MenuPrincipalVista() {
        
        frame = new JFrame("Menú Principal");
        frame.setLayout(new GridLayout(0,1));
        
         //Li assignem mides a la finestra
       
        menuButtons[0] = new JButton("Sortir");
        menuButtons[1] = new JButton("Gestió de restaurants");
        menuButtons[2] = new JButton("Gestió de reserves");
        
        //Creem el gestor de la distribució dels panells de la calculadora
        
        frame.add(menuButtons[0]);
        frame.add(menuButtons[1]);
        frame.add(menuButtons[2]);
        
        frame.setSize(amplada, alcada);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        
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
