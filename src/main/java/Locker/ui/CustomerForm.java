/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Add Search by Celll And Search By OtherPhone
//Add Cell And Other POhone To Row1
//add Notes to row1 
//Add Notes Functionality'
//Work On LAyokur=t
//Set Font Sizes


package Locker.ui;

import LockerData.entity.Customers;
import LockerData.util.HibernateUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;

import java.util.List;
//import java.util.Vector;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;



/**
 *
 * @author genebuchite
 */
public class CustomerForm extends JFrame implements ActionListener  {
    
    
   // setup row0
    
    JPanel row0 = new JPanel();
       
     JComboBox FUName = new JComboBox();
     JButton btnGo = new JButton("ShowSelected Customer");
     JTextField txtPhoneLookup = new JTextField("5017");
     JButton btnGoPhoneLookup= new JButton("Look-Up PhoneNumber");
     
    // setup row1
    
    JPanel row1 = new JPanel();
    JLabel Fname = new JLabel("First Name: ", JLabel.LEFT);
    JTextField tFirstName = new JTextField("First Name");
    JLabel Lname = new JLabel("Last Name: ", JLabel.LEFT);
    JTextField tLastName = new JTextField("Last Name");
    JLabel address = new JLabel("Address: ", JLabel.LEFT);
    JTextField tAddress = new JTextField("Address");
    JLabel state = new JLabel("State: ", JLabel.LEFT);
    JTextField tState = new JTextField("State");
    JLabel zip = new JLabel("ZIP:");
    JTextField tZip = new JTextField("Zip");
    JLabel ph = new JLabel("Phone:");
    JTextField tPhone = new JTextField("Phone Number");
     JComboBox cCity = new JComboBox();
 
   //setup row2
     JPanel row2 = new JPanel();
     JComboBox cPhone = new JComboBox();
     JButton btnLook = new JButton("Search By Phone Number");
     
     
    
     
    
    /**
     * @param args the command line arguments
     */
    // jFrame Code
    public CustomerForm() {
        super("Customer");
        
     runQueryAllFullNames();
     fillcCity();
     
        
        setLookAndFeel();
        setSize(1250,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout flo =new FlowLayout();
    
         JLabel city = new JLabel("City:",JLabel.LEFT);
       
    
       
       
        FUName.setEditable(true);
        AutoCompletion.enable(FUName);
                FUName.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
               FUNameFocusLost(evt);
            }
        });
        
        cCity.setEditable(true);
        AutoCompletion.enable(cCity);
         //prof.setFont(new java.awt.Font("Lucida Grande", 0, 24));
        
        FUName.setFont(new java.awt.Font("Lucida Grande", 0, 24));
       
        
           GridLayout Layout1 = new GridLayout(7,2,10,10);
        //row0.setLayout(Layout1);
         row0.add(FUName);
         row0.add(btnGo);
         btnGo.addActionListener(this);
         row0.add(btnGo);
         row0.add(txtPhoneLookup);
         row0.add(btnGoPhoneLookup);
         btnGoPhoneLookup.addActionListener(this);
         
         add(row0);
        
        
        row1.setLayout(Layout1);
        row1.add(Fname);
        row1.add(tFirstName);
        row1.add(Lname);
        row1.add(tLastName);
        row1.add(address);
        row1.add(tAddress);
        row1.add(city);
        row1.add(cCity);
        row1.add(state);
        row1.add(tState);
        row1.add(zip);
        row1.add(tZip);
        row1.add(ph);
        row1.add(tPhone);
       
        
        
        
        add(row1);
        
       // System.out.println(FUName.getUI());
        row2.setLayout(flo);
        row2.add(cPhone);
        row2.add(btnLook);
        btnLook.addActionListener(this);
        row2.setVisible(false);
        add(row2);
        
       setLayout(flo);
       
        // LOad Combo FUNAme
       
    

        
        setVisible(true);
       
        Object which = FUName.getItemAt(FUName.getSelectedIndex());
        String answer = which.toString();
        System.out.println(answer + " Is the currently Selected NAme!Q");
    
    }    
    
       private static final String QUERY_BASED_ON_CITY="from Customers a where a.city like '";
    
    private void runQueryBasedOnCity() {
       executeHQLQuery(QUERY_BASED_ON_CITY + "Pi%' " + "ORDER BY a.lname, a.fname"  );
    
    }
    private void runQueryAllFullNames() {
    executeHQLQuery("from Customers a ORDER BY a.fullName ");
    }
    
    
    private void runQueryPhone() {
        try{
        Session session = HibernateUtil.getSessionFactory().openSession();
          
          session.beginTransaction();
          Query q = session.createQuery("from Customers c WHERE c.phone Like '%"  + txtPhoneLookup.getText() + "'  ");
          List rL = q.list();
            for(Object o : rL ){
        
                 Customers cus = (Customers)o;
                 System.out.println(cus.getFullName() + cus.getPhone());
                 cPhone.addItem(cus.getFullName() + ":    " + cus.getPhone());
                 row2.setBackground(new java.awt.Color(255, 153, 255));
                 row2.setVisible(true);
            }   
          session.getTransaction().commit();
          session.close();
        }catch (HibernateException he) {
        he.printStackTrace();
        }
        
       
    
    }
    private void listPhoneNumbers(){
        
    System.out.println("Make MessageBox If MOre Than One entry, LIst Entries, Show CustomerINfo Based On Selection");
    }
       private void runQuerySelectedPhone() {
       try{
           Object mycusName = cPhone.getSelectedItem();
           String mCN = mycusName.toString();
           
           
           System.out.println(mycusName);
           int pos = mCN.indexOf(":");
           mCN = mCN.length() > 10 ? mCN.substring(0, pos) : mCN;
           System.out.println(mCN);
           
          Session session = HibernateUtil.getSessionFactory().openSession();
          
          session.beginTransaction();
          Query q = session.createQuery("from Customers c Where c.fullName = '" + mCN + "' ");
          List rL = q.list();
          displayR(rL);
          session.getTransaction().commit();
          session.close();
         } catch (HibernateException he) {
            he.printStackTrace();
         }
       
       
    }
    private void runQuerySelected() {
       try{
          Session session = HibernateUtil.getSessionFactory().openSession();
          
          session.beginTransaction();
          Query q = session.createQuery("from Customers c Where c.fullName = '" + FUName.getSelectedItem() + "' ");
          List rL = q.list();
          displayR(rL);
          session.getTransaction().commit();
          session.close();
         } catch (HibernateException he) {
            he.printStackTrace();
         }
       
       
    }
        public void displayR(java.util.List rL) {
    System.out.println("okay This (S The Customer INfo");
      
       
       for(Object o : rL ){
        
          Customers cus = (Customers)o;
    
         tFirstName.setText(cus.getFname());
         tLastName.setText(cus.getLname());
         tAddress.setText(cus.getAddress());
         tState.setText(cus.getState());
         tZip.setText(cus.getZip());
         cCity.setSelectedItem(cus.getCity());
         tPhone.setText(cus.getPhone());
        
   
                  
       }
      
    }
        //Fill cCity ComboBox
         private void fillcCity() {
       try{
          Session session = HibernateUtil.getSessionFactory().openSession();
          
          session.beginTransaction();
          Query q = session.createQuery("Select DISTINCT c.city from Customers c Order By c.city");
          List<String> ctys = (List<String>)q.list();
          
          for(String ct : ctys) {
            cCity.addItem(ct);
          }
          
       } catch (HibernateException he) {
            he.printStackTrace();
         } 
    }
    
     private void executeHQLQuery(String hql){
       try{
          Session session = HibernateUtil.getSessionFactory().openSession();
          
          session.beginTransaction();
          Query q = session.createQuery(hql);
          List resultList = q.list();
          displayResult(resultList);
          session.getTransaction().commit();
          session.close();
         } catch (HibernateException he) {
            he.printStackTrace();
         }
    }
        public void displayResult(java.util.List resultList) {
    System.out.println("okayHere");
      
       
       for(Object o : resultList){
          //Customers cus = (Customers)o;
         
          Customers cus = (Customers)o;
          
         // Vector<Object> oneRow = new Vector<Object>();
         // oneRow.add(cus.getId());
      // FUName.AddItem(cus.getFullName());
         FUName.addItem(cus.getFullName());
         // oneRow.add(cus.getFname());
          //oneRow.add(cus.getLname());
          //oneRow.add(cus.getCity());
          //System.out.println(cus.get());
          //tableData.add(oneRow);
                  
       }
      // resultTable.setModel(new DefaultTableModel (tableData, tableHeaders));
     
        }
        
    // Action Listeners
   //@Override
  
     private void FUNameFocusLost(java.awt.event.FocusEvent evt) {                             
       System.out.println(FUName.getSelectedItem());
    }           
        
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        System.out.println(command);
        if (command.equals("ShowSelected Customer")){
          System.out.println("Clickded");
           runQuerySelected();
        }
         if (command.equals("Look-Up PhoneNumber")){
          System.out.println("Searching");
           runQueryPhone();
           row2.setVisible(true);
        }
        if (command.equals("Search By Phone Number")){
          System.out.println("Searching Work Here");
        runQuerySelectedPhone();
          row2.setVisible(false);
        }
        
       
       
    }
    private void setLookAndFeel() {
        try {
             UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Lucida Grande", 0, 24));
          // UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
         
        } catch (Exception exc) {
            System.out.println("Error");

           
           
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    
        

            CustomerForm frame = new CustomerForm();
    }
    
}
