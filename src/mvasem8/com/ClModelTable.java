package mvasem8.com;

import mvasem8.data.Client;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ClModelTable extends AbstractTableModel {
    private ArrayList<Client> listClient;

    public ClModelTable (ArrayList<Client> listClient) {
        this.listClient = listClient;
    }

    @Override
    public int getRowCount () {
        return (listClient == null ? 0 : listClient.size());
    }

    @Override
    public int getColumnCount () {
        return 4;
    }

    @Override
    public Object getValueAt (int rowIndex, int columnIndex) {
        Client cl = listClient.get(rowIndex);
        switch (columnIndex){
            case 0:
                return rowIndex +1;
            case 1:
                return cl.getKod();
            case 2:
                return cl.getName();
            case 3:
               return cl.getPassport();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName (int column) {
        switch (column){
            case 0:
                return "N";
            case 1:
                return "Код";
            case 2:
                return "ФИО";
            case 3:
                return "Паспорт";
            default:
                return null;
        }
    }
    public void addRow(Client cl){
        int len = listClient.size();
        listClient.add(cl);
        fireTableRowsInserted(len, len);
    }
    public void updateRow(int index){
        fireTableCellUpdated(index, index);
    }
    public void deleteRow(int index){
        if(index != listClient.size() -1)
            fireTableCellUpdated(index + 1, listClient.size()-1);
        listClient.remove(index);
        fireTableRowsDeleted(index, index);
    }
}
