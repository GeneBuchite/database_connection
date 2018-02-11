package jCombo;

import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.synth.SynthLookAndFeel;



/**
 *
 * @author genebuchite
 */
public class Customers extends JFrame {
    
    //AutoCompletion.enable(yourComboBox);

    public Customers() {
      super("Testing Customers AutoComplete ");
      setLookAndFeel();
      setSize(400,400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JComboBox prof = new JComboBox();
      FlowLayout flo = new FlowLayout();
      prof.addItem("Buchite,Gene");
      prof.addItem("Buchite,Cindy");
      prof.addItem("Buchanan,Troy");
      prof.addItem("Buchanan,Neew One");
      prof.addItem("Smithy,Neew One");
      prof.setFont(new java.awt.Font("Lucida Grande", 0, 24));
      AutoCompletion.enable(prof);
      setLayout(flo);
      JTextField play = new JTextField("Name");
      JTextField stop = new JTextField("Address");
      JTextField pause = new JTextField("City");
      JTextField met = new JTextField("ok Tjhis ISi asA Big Deal");
      
      
      
      add(prof);
      add(play);
      add(stop);
      add(pause);
      add(met);
      
      setVisible(true);
      
      
    }
    private void setLookAndFeel () {
         try {
            //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
              SynthLookAndFeel laf = new SynthLookAndFeel();
  laf.load(Customers.class.getResourceAsStream("laf.xml"), Customers.class);
  UIManager.setLookAndFeel(laf);
         }catch (Exception exc) {
             //System.out.println(exc.toString());
         }
        
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Customers frame = new Customers();
        
        // TODO code application logic here
    }
    
}
