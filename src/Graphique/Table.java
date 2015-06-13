package graphique;

import music.Music;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by alex on 12/06/2015.
 */
public class Table{
    JTable table;
    JScrollPane jScrollPane;

    public Table( ArrayList<Music> listMusic){
        final ArrayList<Music> list = listMusic;
        final String[] column = new String[]{"Titre","Artiste","Album","durée"};
        AbstractTableModel model = new AbstractTableModel() {
            public int getColumnCount() { return 4 ; }
            public int getRowCount() { return list.size(); }
            public String getColumnName(int col) {
                return column[col];
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                if(columnIndex==0) {
                    return list.get(rowIndex).getTitle();
                }
                if(columnIndex==1) {
                    if(list.get(rowIndex).getArtiste()!=null) {
                        return list.get(rowIndex).getArtiste().getName();
                    }
                }
                if(columnIndex==2) {
                    if(list.get(rowIndex).getAlbum()!=null) {
                        return list.get(rowIndex).getAlbum().getName();
                    }
                }
                if(columnIndex==3) {
                    return list.get(rowIndex).getDuree()/1000;
                }
                return "";
            }
        };
        model.fireTableDataChanged();
        table = new JTable(model);
        jScrollPane = new JScrollPane(table);
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
}
