package com.prooktatas.traindriverdatabase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
/**
 *
 * @author KomlosyT
 */
public class NewTD extends javax.swing.JFrame implements ActionListener
{

    private JTextField lastNameField;
    private JTextField firstNameField;
    private JComboBox<Integer> ageComboBox;
    private JCheckBox bzTipCheckBox;
    private JCheckBox szergejipCheckBox;
    private JCheckBox sziliTipCheckBox;
    private JCheckBox traxxTipCheckBox;
    private JCheckBox gigantTipCheckBox;
    private JCheckBox taurusTipCheckBox;
    private JCheckBox firstLineCheckBox;
    private JCheckBox secondLineCheckBox;
    private JCheckBox thirdLineCheckBox;
    private JCheckBox fourthLineCheckBox;
    private JCheckBox fifthLineCheckBox;
    private JCheckBox sixthLineCheckBox;
    private JCheckBox electricityCheckBox;
    private JCheckBox dieselCheckBox;
    private JButton addButton;
    private DB db;
    
    public NewTD(DB db) 
    {
        this.db = db;
        
        setTitle("Új mozdonyvezető hozzáadása");
        setSize(360, 400);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        
        Integer[] ageOption = new Integer[44];
        for (int i = 0; i < ageOption.length; i++)ageOption[i] = 21 + i;
        
        JLabel lastNameLabel = new JLabel("Vezetéknév:");
        JLabel firstNameLabel = new JLabel("Keresztnév:");
        JLabel ageLabel = new JLabel("Kor:");
        JLabel typeLabel = new JLabel("Típusok:");
        JLabel lineLabel = new JLabel("Vonalak:");
        JLabel categoryLabel = new JLabel("Kategória:");
        
        lastNameField = new JTextField();
        firstNameField = new JTextField();
        ageComboBox = new JComboBox<>(ageOption);
        bzTipCheckBox = new JCheckBox("117 típus");
        sziliTipCheckBox = new JCheckBox("431 típus");
        taurusTipCheckBox = new JCheckBox("470 típus");
        traxxTipCheckBox = new JCheckBox("480 típus");
        szergejipCheckBox = new JCheckBox("628 típus");
        gigantTipCheckBox = new JCheckBox("630 típus");
        firstLineCheckBox = new JCheckBox("1., vonal");
        secondLineCheckBox = new JCheckBox("2., vonal");
        thirdLineCheckBox = new JCheckBox("3., vonal");
        fourthLineCheckBox = new JCheckBox("4., vonal");
        fifthLineCheckBox = new JCheckBox("5., vonal");
        sixthLineCheckBox = new JCheckBox("6., vonal");
        electricityCheckBox = new JCheckBox("Villany");
        dieselCheckBox = new JCheckBox("Dízel");
        addButton = new JButton("Hozzáadás");
        
        lastNameLabel.setBounds(120, 10, 100, 25);
        lastNameField.setBounds(25, 30, 310, 30);
        firstNameLabel.setBounds(120, 55, 100, 25);
        firstNameField.setBounds(25, 75, 310, 30);
        ageLabel.setBounds(120, 100, 100, 25);
        ageComboBox.setBounds(25, 120, 310, 25);
        categoryLabel.setBounds(150, 140, 100, 25);
        electricityCheckBox.setBounds(100, 160, 80, 25);
        dieselCheckBox.setBounds(190, 160, 80, 25);
        typeLabel.setBounds(150, 180, 100, 25);
        bzTipCheckBox.setBounds(50, 200, 80, 25);
        sziliTipCheckBox.setBounds(130, 200, 80, 25);
        taurusTipCheckBox.setBounds(210, 200, 80, 25);
        traxxTipCheckBox.setBounds(50, 220, 80, 25);
        szergejipCheckBox.setBounds(130, 220, 80, 25);
        gigantTipCheckBox.setBounds(210, 220, 80, 25);
        lineLabel.setBounds(150, 240, 100, 25);
        firstLineCheckBox.setBounds(50, 260, 80, 25);
        secondLineCheckBox.setBounds(130, 260, 80, 25);
        thirdLineCheckBox.setBounds(210, 260, 80, 25);
        fourthLineCheckBox.setBounds(50, 280, 80, 25);
        fifthLineCheckBox.setBounds(130, 280, 80, 25);
        sixthLineCheckBox.setBounds(210, 280,80, 25);
        
        add(lastNameLabel);        
        add(lastNameField);        
        add(firstNameLabel);        
        add(firstNameField);        
        add(ageLabel);
        add(ageComboBox);
        add(typeLabel);
        add(bzTipCheckBox);
        add(sziliTipCheckBox);
        add(taurusTipCheckBox);
        add(traxxTipCheckBox);
        add(szergejipCheckBox);
        add(gigantTipCheckBox);
        add(lineLabel);
        add(firstLineCheckBox);
        add(secondLineCheckBox);
        add(thirdLineCheckBox);
        add(fourthLineCheckBox);
        add(fifthLineCheckBox);
        add(sixthLineCheckBox);
        add(categoryLabel);
        add(electricityCheckBox);
        add(dieselCheckBox);
        add(addButton);

        addButton.setBounds(25, 315, 310, 30);
        addButton.addActionListener(this);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        centerWindow(this);
        setVisible(true);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        if (e.getSource() == addButton)
        {
            try 
            {
                String lastName = lastNameField.getText().trim();
                String firstName = firstNameField.getText().trim();
                
                if (!isNameValid(lastName)) 
                {
                JOptionPane.showMessageDialog(
                        null, 
                        "A vezetéknév csak betűket, szóközt és kötőjelet tartalmazhat.", 
                        "Hiba", 
                        JOptionPane.ERROR_MESSAGE);
                return;
                }
                
                if (!isNameValid(firstName)) 
                {
                JOptionPane.showMessageDialog(
                        null, 
                        "A keresztnév csak betűket, szóközt és kötőjelet tartalmazhat.", 
                        "Hiba", 
                        JOptionPane.ERROR_MESSAGE);
                return;
                }
                               
                int age = (Integer) ageComboBox.getSelectedItem();
                
                StringBuilder category = new StringBuilder();
                if (electricityCheckBox.isSelected())category.append("Villany, ");
                if (dieselCheckBox.isSelected())category.append("Dízel, ");
                if (category.length() > 0)category.setLength(category.length() - 2);
                
                StringBuilder types = new StringBuilder();
                if (bzTipCheckBox.isSelected())types.append("117,");
                if (sziliTipCheckBox.isSelected())types.append("431,");
                if (taurusTipCheckBox.isSelected())types.append("470,");
                if (traxxTipCheckBox.isSelected())types.append("480,");
                if (szergejipCheckBox.isSelected())types.append("628,");
                if (gigantTipCheckBox.isSelected())types.append("630,");
                
                if (types.length() > 0)types.setLength(types.length() - 1);
                
                int typesCount = 0;
                if (bzTipCheckBox.isSelected()) typesCount++;
                if (sziliTipCheckBox.isSelected()) typesCount++;
                if (taurusTipCheckBox.isSelected()) typesCount++;
                if (traxxTipCheckBox.isSelected()) typesCount++;
                if (szergejipCheckBox.isSelected()) typesCount++;
                if (gigantTipCheckBox.isSelected()) typesCount++;
                
                if (typesCount > 5)
                {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Legfeljebb 5 típus választható ki.", 
                            "Hiba", 
                            JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                
                StringBuilder lines = new StringBuilder();
                if (firstLineCheckBox.isSelected())lines.append("1es,");
                if (secondLineCheckBox.isSelected())lines.append("2es,");
                if (thirdLineCheckBox.isSelected())lines.append("3as,");
                if (fourthLineCheckBox.isSelected())lines.append("4es,");
                if (fifthLineCheckBox.isSelected())lines.append("5os,");
                if (sixthLineCheckBox.isSelected())lines.append("6os, ");
                
                if (lines.length() > 0)lines.setLength(lines.length() - 1);
                
                int lineCount = 0;
                if (firstLineCheckBox.isSelected()) lineCount++;
                if (secondLineCheckBox.isSelected()) lineCount++;
                if (thirdLineCheckBox.isSelected()) lineCount++;
                if (fourthLineCheckBox.isSelected()) lineCount++;
                if (fifthLineCheckBox.isSelected()) lineCount++;
                if (sixthLineCheckBox.isSelected()) lineCount++;
                
                if (lineCount > 5) 
                {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Legfeljebb 5 vonal választható ki.", 
                            "Hiba", 
                            JOptionPane.ERROR_MESSAGE);
                    return; 
                }

                

                

                if (lastName.isEmpty() || firstName.isEmpty() || category.isEmpty() || types.isEmpty() || lines.isEmpty()) 
                {
                    JOptionPane.showMessageDialog(
                            this, 
                            "Kérlek, töltsd ki az összes mezőt és válasz a kategóriából típusból és vonalból legalább egyet!", 
                            "Hiba", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TD newTD = new TD(
                        lastName, 
                        firstName, 
                        age, 
                        category.toString(), 
                        types.toString(), 
                        lines.toString());
                        
                
                db.addTD(newTD);

                JOptionPane.showMessageDialog(
                        this, 
                        "Mozdonyvezető hozzáadva az adatbázishoz:\n" + newTD.toString());
                dispose();

            } 
            catch (NumberFormatException ex) 
            {
                JOptionPane.showMessageDialog(
                        this, 
                        "Hibás kor adat! Kérlek, adj meg egy érvényes számot.", 
                        "Hiba", 
                        JOptionPane.ERROR_MESSAGE);
            } 
            catch (Exception ex) 
            {
                JOptionPane.showMessageDialog(
                        this, 
                        "Hiba történt: " + ex.getMessage(), 
                        "Hiba", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean isNameValid(String name) 
    {
        return name.matches("[a-zA-ZáéíóöőúüűÁÉÍÓÖŐÚÜŰ\\-\\s]+");
    }
  
    public static void centerWindow(JFrame frame) 
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
