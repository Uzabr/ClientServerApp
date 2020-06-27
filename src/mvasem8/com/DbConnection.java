package mvasem8.com;

import mvasem8.data.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbConnection {
    private Connection conn = null;

    public DbConnection () {

    }
    public void RollBack(){
        try{
            conn = this.getConn();
            conn.rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setPath(){
        Statement smt;
        try {
            smt = conn.createStatement();
            smt.executeUpdate("SET SEARCH_PATH= kursValyut");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConn () {
        return conn;
    }

    public void setConn (Connection conn) {
        this.conn = conn;
    }
    public String getVersion(){
        String ver =null;
        Statement stm = null;
        ResultSet rst = null;
        try{
            stm = conn.createStatement();
            rst = stm.executeQuery("SELECT VERSION()");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ver;
    }

    public ArrayList<Client> loadClient(){
        ArrayList<Client> listclient = new ArrayList<>();
        Connection con = this.getConn();
        try{
            Statement stm = con.createStatement();
            ResultSet rst = stm.executeQuery("SELECT code_client, name_client, passport FROM client ");
            while (rst.next()){
                Client cl = new Client();
                cl.setKod(rst.getInt(1));
                cl.setName(rst.getString(2));
                cl.setPassport(rst.getString(3));
                listclient.add(cl);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка получения данных", JOptionPane.ERROR_MESSAGE);
        }
        return listclient;
    }

    public boolean upateClinet(Client cl, Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "UPDATE client SET  name_client = ?, passport = ? WHERE code_client = ?";
        try{
            pst = con.prepareStatement(skript);
            pst.setString(1, cl.getName());
            pst.setString(2, cl.getPassport());
            pst.setInt(3, key);
            pst.executeUpdate();
            con.commit();
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка изменения данных",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean insertClient(Client cl){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "INSERT INTO client(code_client, name_client, passport) VALUES (?, ?, ?)";

        try{
            pst = con.prepareStatement(skript);
            pst.setInt(1, cl.getKod());
            pst.setString(2, cl.getName());
            pst.setString(3, cl.getPassport());
            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка добавления данных",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }
    public boolean deleteclient(Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "DELETE FROM client WHERE code_client = ?";
        try {
            pst = con.prepareStatement(skript);
            pst.setInt(1, key);
            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean upateContract(Contract cr, Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "UPDATE contract SET  conclusion_date = ?, code_staff = ?, code_client = ?  WHERE id_contract = ?";
        try{
            pst = con.prepareStatement(skript);
            pst.setDate(1, ConverterDate.convertToSql(cr.getDataZakl()));
            pst.setInt(2, cr.getKodSotrudnik());
            pst.setInt(3, key);
            pst.executeUpdate();
            con.commit();
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка изменения данных",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean insertContract(Contract cr){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "INSERT INTO client(id_contract, conclusion_date, code_staff, code_client) VALUES (?, ?, ?, ?)";

        try{
            pst = con.prepareStatement(skript);
            pst.setInt(1, cr.getKodClient());
            pst.setDate(2, ConverterDate.convertToSql(cr.getDataZakl()));
            pst.setInt(3, cr.getKodSotrudnik());
            pst.setInt(4, cr.getKodClient());
            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка добавления данных",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }
    public boolean deleteContract(Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "DELETE FROM contract WHERE id_contract = ?";
        try {
            pst = con.prepareStatement(skript);
            pst.setInt(1, key);
            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean upateSotr(Sotrudnik st, Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "UPDATE staff SET  name_staff = ?, post = ?  WHERE id_contract = ?";
        try{
            pst = con.prepareStatement(skript);
            pst.setString(1, st.getName());
            pst.setString(2, st.getDoljnost());
            pst.setInt(3, key);
            pst.executeUpdate();
            con.commit();
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка изменения данных",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean insertSotr(Sotrudnik st){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "INSERT INTO staff(code_staff, name_staff, post) VALUES (?, ?, ?)";

        try{
            pst = con.prepareStatement(skript);
            pst.setInt(1, st.getKod());
            pst.setString(2, st.getName());
            pst.setString(3, st.getDoljnost());
            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка добавления данных",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }
    public boolean deleteSotr(Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "DELETE FROM staff WHERE code_staff = ?";
        try {
            pst = con.prepareStatement(skript);
            pst.setInt(1, key);
            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean upateConvert(Convertatsiya cn , Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "UPDATE conversion SET  transfer_amount = ?, conversion_date = ?, " +
                "id_contract = ?, code_staff = ?, code_client = ?  WHERE id_conversion = ?";
        try{
            pst = con.prepareStatement(skript);
            pst.setDouble(1, cn.getSumPerevoda());
            pst.setDate(2, ConverterDate.convertToSql(cn.getDataConvertatsi()));
            pst.setInt(3, cn.getKodContarct());
            pst.setInt(4, cn.getKodValyut());
            pst.setInt(5, cn.getKodObmen());
            pst.setInt(6, key);
            pst.executeUpdate();
            con.commit();
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка изменения данных",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean insertConvert(Convertatsiya cn){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "INSERT INTO conversion(id_conversion, transfer_amount, conversion_date, id_contract, code_currency, code_exchange) VALUES (?, ?, ?, ?, ?, ?)";

        try{
            pst = con.prepareStatement(skript);
            pst.setInt(1, cn.getKod());
            pst.setDouble(2, cn.getSumPerevoda());
            pst.setDate(3,ConverterDate.convertToSql(cn.getDataConvertatsi()));
            pst.setInt(4, cn.getKodContarct());
            pst.setInt(5, cn.getKodValyut());
            pst.setInt(6, cn.getKodObmen());

            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка добавления данных",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }
    public boolean deleteConvert(Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "DELETE FROM conversion WHERE id_conversion = ?";
        try {
            pst = con.prepareStatement(skript);
            pst.setInt(1, key);
            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean upateObmen(Obmen ob , Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "UPDATE exchange SET  currency_value = ? WHERE code_exchange = ?";
        try{
            pst = con.prepareStatement(skript);
            pst.setDouble(1, ob.getKursValyut());
            pst.setInt(2, key);
            pst.executeUpdate();
            con.commit();
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка изменения данных",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean insertObmen(Obmen ob){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "INSERT INTO exchange(code_exchange, currency_value) VALUES(?, ?) ";

        try{
            pst = con.prepareStatement(skript);
            pst.setInt(1, ob.getKod());
            pst.setDouble(2, ob.getKursValyut());
            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка добавления данных",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }
    public boolean deleteObmen(Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "DELETE FROM exchange WHERE code_exchange = ?";
        try {
            pst = con.prepareStatement(skript);
            pst.setInt(1, key);
            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean upateValyut(Valyuta vl , Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "UPDATE currency SET  name_currency = ?, code_exchange = ? WHERE code_currency = ?";
        try{
            pst = con.prepareStatement(skript);
            pst.setString(1, vl.getNameVlyuta());
            pst.setInt(2, vl.getKodObmena());
            pst.setInt(3, key);
            pst.executeUpdate();
            con.commit();
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка изменения данных",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public boolean insertValyut(Valyuta vl){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "INSERT INTO currency (code_currency, name_currency, code_exchange) VALUES(?, ?, ?) ";

        try{
            pst = con.prepareStatement(skript);
            pst.setInt(1, vl.getKod());
            pst.setString(2, vl.getNameVlyuta());
            pst.setInt(3, vl.getKodObmena());
            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            RollBack();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Ошибка добавления данных",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }
    public boolean deleteValyut(Integer key){
        PreparedStatement pst = null;
        Connection con = this.getConn();
        String skript = "DELETE FROM currency WHERE code_currency = ?";
        try {
            pst = con.prepareStatement(skript);
            pst.setInt(1, key);
            pst.executeUpdate();
            con.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }




}
