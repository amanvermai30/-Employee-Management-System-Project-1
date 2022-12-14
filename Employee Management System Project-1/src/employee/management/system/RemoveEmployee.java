package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice empIdCh;
    JButton delete, back;
    RemoveEmployee(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel empId = new JLabel("Employee Id");
        empId.setBounds(50,50,100,30);
        add(empId);

        empIdCh = new Choice();
        empIdCh.setBounds(170,50,150,30);
        add(empIdCh);


        try {

            MysqlConnectivity con = new MysqlConnectivity();
            String query = "select * from employee";
            ResultSet resultSet = con.s.executeQuery(query);
            while (resultSet.next()){
                empIdCh.add(resultSet.getString("empId"));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        JLabel name = new JLabel("Name");
        name.setBounds(50,100,100,30);
        add(name);

        JLabel labelName = new JLabel();
        labelName.setBounds(200,100,100,30);
        add(labelName);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(50,150,100,30);
        add(phone);

        JLabel labelPhone = new JLabel();
        labelPhone.setBounds(200,150,100,30);
        add(labelPhone);

        JLabel email = new JLabel("Email");
        email.setBounds(50,200,100,30);
        add(email);

        JLabel labelEmail = new JLabel();
        labelEmail.setBounds(200,200,200,30);
        add(labelEmail);


        try {

            MysqlConnectivity con = new MysqlConnectivity();
            String query = "select * from employee where empId = '"+empIdCh.getSelectedItem()+"' ";
            ResultSet resultSet = con.s.executeQuery(query);
            while (resultSet.next()){
                labelName.setText(resultSet.getString("name"));
                labelPhone.setText(resultSet.getString("phone"));
                labelEmail.setText(resultSet.getString("email"));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        empIdCh.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                try {

                    MysqlConnectivity con = new MysqlConnectivity();
                    String query = "select * from employee where empId = '"+empIdCh.getSelectedItem()+"' ";
                    ResultSet resultSet = con.s.executeQuery(query);
                    while (resultSet.next()){
                        labelName.setText(resultSet.getString("name"));
                        labelPhone.setText(resultSet.getString("phone"));
                        labelEmail.setText(resultSet.getString("email"));
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        delete = new JButton("Delete");
        delete.setBounds(100,250,100,30);
        delete.addActionListener(this);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        add(delete);

        back = new JButton("Back");
        back.setBounds(250,250,100,30);
        back.setForeground(Color.white);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        add(back);




        setSize(1000,400);
        setLocation(150,150);
        setVisible(true);


    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == delete ){

            try {

                MysqlConnectivity con = new MysqlConnectivity();
                String query = "delete from employee where empId = '"+empIdCh.getSelectedItem()+"' ";
                con.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Employee Details Deleted Successfully");
                setVisible(false);
                new HomePage();

            }catch (Exception ex){
                ex.printStackTrace();
            }

        }else {
            setVisible(false);
            new HomePage();
        }
    }
}
