package mvasem8.com;

import mvasem8.data.Client;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowInTextArea extends JRDialog {
    DbConnection manager;
    JPanel panel;
    JTextArea textArea;

    public ShowInTextArea (DbConnection manager) {
        super();
        this.manager = manager;
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createGui();
        loadData();
        pack();
        setTitle("Окно с результатом запроса");
        setLocationRelativeTo(this);
    }

    private void loadData () {
        Connection con = manager.getConn();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT code_client," +
                    "name_client, passport FROM client ");
            while (res.next()){
                Client cl = new Client();
                cl.setKod(res.getInt(1));
                cl.setName(res.getString(2));
                cl.setPassport(res.getString(3));
                textArea.append(cl.toString() + "\n");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(),"Ошибка получения данных",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createGui () {
        panel = new JPanel(new FlowLayout());
        textArea = new JTextArea(10, 20);
        panel.add(textArea);
        getContentPane().add(panel);
    }
}
