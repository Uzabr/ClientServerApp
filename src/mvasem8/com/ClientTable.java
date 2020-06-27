package mvasem8.com;

import mvasem8.data.Client;
import mvasem8.data.EnumD;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class ClientTable extends JDialog {
    private DbConnection manager;
    private JTable clTbale;
    private JButton buttonClose;
    private ClModelTable model;
    private TableRowSorter<ClModelTable> tableRowSorter;
    private ArrayList<Client> listCl;
    private JButton buttonUpdate;
    private JButton buttonInsert;
    private JButton buttonDelete;

    public ClientTable (DbConnection manager) {
        super();
        this.manager = manager;
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        loadData();
        createGui();
        bindListener();
        pack();
        setTitle("Список клиентов ");
        setLocationRelativeTo(this);

    }

    private void bindListener () {
        buttonClose.addActionListener(ActionEvent -> dispose());
        buttonUpdate.addActionListener(ActionEvent -> editClient());
        buttonInsert.addActionListener(ActionEvent -> addClient());
        buttonDelete.addActionListener(ActionEvent -> deleteClinet());
    }

    private JToolBar getToolBar(){
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        buttonInsert = new JButton(new ImageIcon("src/mvasem8/images/addUser.png"));
        buttonInsert.setFocusable(false);
        buttonInsert.setToolTipText("Добавить новый клиенты");
        buttonUpdate = new JButton(new ImageIcon("src/mvasem8/images/editUser.png"));
        buttonUpdate.setFocusable(false);
        buttonUpdate.setToolTipText("Изменить");
        buttonDelete = new JButton(new ImageIcon("src/mvasem8/images/deleteUser.png"));
        buttonDelete.setFocusable(false);
        buttonDelete.setToolTipText("Удалить");
        toolBar.add(buttonInsert);
        toolBar.add(buttonUpdate);
        toolBar.add(buttonDelete);
        return toolBar;

    }

    private void deleteClinet () {
        int index = clTbale.getSelectedRow();
        if(index == -1)
            return;
        if (JOptionPane.showConfirmDialog(this,
                "Удалить клиента ?", "Подтверждение",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) !=
                JOptionPane.YES_OPTION)
        return;
        int modelRow = clTbale.convertRowIndexToModel(index);
        Client cl =listCl.get(modelRow);
        try {
            Integer key = cl.getKod();
            if (manager.deleteclient(key)){
                model.deleteRow(modelRow);
                System.out.println("Delete client OK");
            }
            else
                JOptionPane.showMessageDialog(this,
                        "Ошибка удаления строки", "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,
                    e.getMessage(), "Ошибка удаления",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void addClient () {
        EditWindow ed = new EditWindow(this, manager, null);
        if (ed.showDialog() == EnumD.OK){
            Client cl = ed.getClient();
            model.addRow(cl);
        }
    }

    private void editClient () {
        int index = clTbale.getSelectedRow();
        if (index == -1)
            return;
        int modelT = clTbale.convertRowIndexToModel(index);
        Client client = listCl.get(modelT);
        Integer key = client.getKod();
        EditWindow ed = new EditWindow(this, manager, client);
        if (ed.showDialog() == EnumD.OK){
            model.updateRow(modelT);
            System.out.println("Update client OK");
        }

    }

    private void createGui () {
        JPanel pnl = new JPanel(new MigLayout("insets 3, gapy 4",
                "[grow, fill]", "[]5[grow, fill]10[]"));

        clTbale = new JTable();
        clTbale.setModel(model = new ClModelTable(listCl));

        RowSorter<ClModelTable> sorter = new TableRowSorter<>(model);
        clTbale.setRowSorter(sorter);
        clTbale.setRowSelectionAllowed(true);
        clTbale.setIntercellSpacing(new Dimension(0,1 ));
        clTbale.setGridColor(Color.black);
        clTbale.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        clTbale.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(clTbale);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(3,0,3,0), scrollPane.getBorder()));
        pnl.add(getToolBar(), "growx, wrap");
        buttonClose = new JButton("Закрыть");
        pnl.add(scrollPane, "grow, span");
        pnl.add(buttonClose, "growx 0, right");

        getContentPane().setLayout(
                new MigLayout("insets 0 2 0 2, gapy 0", "[grow, fill]",
                        "[grow, fill]"));
        getContentPane().add(pnl, "grow");


    }

    private void loadData () {
        listCl = manager.loadClient();
    }


}
