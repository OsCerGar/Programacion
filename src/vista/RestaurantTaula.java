package vista;

import model.Restaurant;
import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;

public class RestaurantTaula extends AbstractTableModel {
    
    //Taula de 3 files * 3 columnes

    private final String[] nomsColumnes = {"Codi", "Nom", "Adreça"};

    String[][] restaurants = new String[ControladorPrincipal.getMAXRESTAURANTS()][3];

    public RestaurantTaula() {
        int i = 0;
        for (Restaurant restaurant : ControladorPrincipal.getRestaurants()) {
            if (restaurant != null) {
                restaurants[i][0] = String.valueOf(restaurant.getCodi());
                restaurants[i][1] = restaurant.getNom();
                restaurants[i][2] = restaurant.getAdreca();
                i++;
            }
        }
    }


    public int getRowCount() {
        return restaurants.length;
    }


    public int getColumnCount() {
        return restaurants[0].length;
    }


    public String getNomsColumnes(int col) {
        return nomsColumnes[col];
    }

   
    public Object getValueAt(int row, int column) {
        return restaurants[row][column];
    }

}
