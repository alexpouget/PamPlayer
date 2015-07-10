package graphique.tableau;

import graphique.MyWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by alex on 09/07/2015.
 */
public class MyTableFilter extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        String regex=MyWindow.txtRechercher.getText();

        MyWindow.sorter.setRowFilter(RowFilter.regexFilter(regex, 0, 1));
    }
}
