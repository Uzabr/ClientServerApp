package mvasem8.com;

import mvasem8.data.Client;
import mvasem8.data.EnumD;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class EditWindow  extends JRDialog {

    private DbConnection manager;
    private Integer oldkey;
    private final static String titelAdd = "Добавление нового клиента ";
    private final static String titleEd = "Редактирование ";

    private Client  client = null;
    private boolean isNewRow = false;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private JTextField kodText;
    private JTextField nameText;
    private JTextField pasportText;
    private JButton buttonSave;
    private JButton buttonCancel;

    public EditWindow (Window parent, DbConnection manager, Client client) {
        this.manager = manager;
        isNewRow = client == null ? true : false;
        setTitle(isNewRow ? titelAdd : titleEd);
        if (!isNewRow){
            this.client = client;
            oldkey = client.getKod();
        }
        else
            this.client = new Client();
        createGui();
        bindListenner();
        loadData();
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);

    }

    private void loadData () {
        if (!isNewRow){
            kodText.setText(String.valueOf(client.getKod()).toString());
            nameText.setText(client.getName());
            pasportText.setText(client.getPassport());
        }
    }

    private void bindListenner () {
        buttonCancel.addActionListener(ActionEvent -> {
            setDialog(EnumD.Cancel);
            close();
        });

        buttonSave.addActionListener(ActionEvent -> {
            if (!constructorClient())
                return;
            if (isNewRow){
                if(manager.insertClient(client)){
                    setDialog(EnumD.OK);
                    close();
                }
            }else
                if (manager.upateClinet(client, oldkey)){
                    setDialog(EnumD.OK);
                    close();
                }
        });
    }

    private boolean constructorClient () {
        try{
            client.setKod(Integer.valueOf(kodText.getText()));
            client.setName(nameText.getText());
            client.setPassport(pasportText.getText());
            return true;

        }catch (Exception e){
            JOptionPane.showMessageDialog(this,
                    e.getMessage(), "Ошибка данных",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void createGui () {
        JPanel pnl = new JPanel(new MigLayout("insets 5", "[][]","[]5[]10[]"));
        kodText = new JTextField(10);
        nameText = new JTextField(50);
        pasportText = new JTextField(20);
        buttonSave = new JButton("Сохранить");
        buttonCancel = new JButton("Отмена");

        // Добавление элементов на панель
        pnl.add(new JLabel("Код"));
        pnl.add(kodText, "span");
        pnl.add(new JLabel("ФИО"));
        pnl.add(nameText, "span");
        pnl.add(new JLabel("Серия паспорта"));
        pnl.add(pasportText, "span");
        pnl.add(buttonSave, "growx 0, right, sg 1");
        pnl.add(buttonCancel, "sg 1");

        //  Добавление панели в окно фрейма
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pnl, BorderLayout.CENTER);
    }

    public Client getClient(){
        return client;
    }
}
