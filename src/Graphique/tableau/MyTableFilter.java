package graphique.tableau;

import graphique.MyWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by alex on 09/07/2015.
 *
 * permet d'effectuer un filtre sur le tableau par la recherche ou le jtree
 */
public class MyTableFilter extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        //on definit une regex
        String regex=MyWindow.txtRechercher.getText();
        //on applique cette regex au tableau et aux colonnes choisi
        MyWindow.sorter.setRowFilter(RowFilter.regexFilter("(?i)"+regex, 0, 1, 2));
    }
}
