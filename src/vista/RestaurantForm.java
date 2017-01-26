package vista;

import javax.swing.*;

public class RestaurantForm {

    private JFrame frame;
    private Integer amplada = 300;
    private Integer alcada = 200;

    private JLabel lCodi;
    private JTextField tCodi;
    private JLabel lNom;
    private JTextField tNom;
    private JLabel lAdreca;
    private JTextField tAdreca;

    private JButton bDesar;   
    private JButton bSortir;   

    /* 
    Heu d'inicialitzar els atributs d'aquesta classe fent el següent (no afegiu cap listener a cap control):
            
     - Heu d'inicialitzar l'objecte JFrame amb títol "Formulari Restaurant" i layout Grid d'una columna
     - Heu d'inicialitzar els objectes JLabel amb el nom corresponent segons l'atribut de restaurant que representen.
     - Heu d'inicialitzar els objectes JTextField amb una longitud de 20 caràcters.
     - Heu d'inicialitzar els objectes JButton amb els noms "Desar" i "Sortir" respectivament.
     - Heu d'afegir-ho tot a l'atribut frame
     - Heu de fer visible el frame amb l'amplada i alçada que proposen els atributs alcada i amplada
     - Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
        
    */
    public RestaurantForm() {
    
    }
    
    public RestaurantForm(Integer codi, String nom, String adreca){
        this(); //Crida a RestaurantForm()
        tCodi.setText(codi.toString());
        tNom.setText(nom);
        tAdreca.setText(adreca);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getCodi() {
        return tCodi;
    }

    public void setCodi(JTextField tCodi) {
        this.tCodi = tCodi;
    }

    public JTextField getNom() {
        return tNom;
    }

    public void setNom(JTextField tNom) {
        this.tNom = tNom;
    }

    public JTextField getAdreca() {
        return tAdreca;
    }

    public void setAdreca(JTextField tAdreca) {
        this.tAdreca = tAdreca;
    }

    public JButton getDesar() {
        return bDesar;
    }

    public void setDesar(JButton bDesar) {
        this.bDesar = bDesar;
    }

    
    public JButton getSortir() {
        return bSortir;
    }

    public void setSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }

}
