/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jCombo;

import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.synth.SynthLookAndFeel;



/**
 *
 * @author genebuchite
 */
public class MyAutoCompleteTest extends JFrame {
    
    //AutoCompletion.enable(yourComboBox);

    public MyAutoCompleteTest() {
      super("Testing ComboBox AutoComplete ");
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
      prof.setFont(new java.awt.Font("Lucida Grande", 0, 18));
      AutoCompletion.enable(prof);
      setLayout(flo);
      JButton play = new JButton("Play");
      JButton stop = new JButton("Stop");
      JButton pause = new JButton("Pause");
      
      add(prof);
      add(play);
      add(stop);
      add(pause);
      setVisible(true);
      
      
    }
    private void setLookAndFeel () {
         try {
            //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
              SynthLookAndFeel laf = new SynthLookAndFeel();
  laf.load(MyAutoCompleteTest.class.getResourceAsStream("laf.xml"), MyAutoCompleteTest.class);
  UIManager.setLookAndFeel(laf);
         }catch (Exception exc) {
             //System.out.println(exc.toString());
         }
        
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyAutoCompleteTest frame = new MyAutoCompleteTest();
        
        // TODO code application logic here
    }
    
}
