package com.prooktatas.traindriverdatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author KomlosyT
 * This class contains buttons for adding a new train driver, 
 * editing existing ones, deleting, and a refresh button. 
 */
public class ButtonPanel extends javax.swing.JPanel 
{

    public ButtonPanel(Run parentFrame, DB db) 
    {
        
        setLayout(null);
        //add new train drive button
        JButton newTD = new JButton("Új mozdonyvezető hozzáadása");
        newTD.setBounds(60, 20, 200, 30);
        newTD.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                new NewTD(db);
            }
        });
        add(newTD);
        
        //add train driver editor button
        JButton tDEditor = new JButton("Szerkesztés");
        tDEditor.setBounds(280, 20, 100, 30);
        tDEditor.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int selectedRow = parentFrame.getTable().getSelectedRow();

                if (selectedRow != -1) 
                {
                    String lastName = (String) parentFrame.getTableModel().getValueAt(selectedRow, 0);
                    String firstName = (String) parentFrame.getTableModel().getValueAt(selectedRow, 1);
                    int age = (int) parentFrame.getTableModel().getValueAt(selectedRow, 2);
                    String category = (String) parentFrame.getTableModel().getValueAt(selectedRow, 3);
                    String type = (String) parentFrame.getTableModel().getValueAt(selectedRow, 4);
                    String line = (String) parentFrame.getTableModel().getValueAt(selectedRow, 5);

                    new TDEditorDialog(parentFrame, db, lastName, firstName, age, category, type, line);
                } 
                else 
                {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Kérjük, válasszon ki egy mozdonyvezetőt a szerkesztéshez.", 
                            "Figyelmeztetés", 
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(tDEditor);

        //add refresh button
        JButton upDate = new JButton("Frissítés");
        upDate.setBounds(400, 20, 100, 30);
        upDate.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                parentFrame.loadData();
            }
        });
        add(upDate);
        
        //add delete button
        JButton delete = new JButton("Kijelölt mozdonyvezető törlése");
        delete.setBounds(520, 20, 200, 30);
        delete.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int selectedRow = parentFrame.getTable().getSelectedRow();

                if (selectedRow != -1) 
                {
                    String lastName = (String) parentFrame.getTableModel().getValueAt(selectedRow, 0);
                    String firstName = (String) parentFrame.getTableModel().getValueAt(selectedRow, 1);

                    int confirm = JOptionPane.showConfirmDialog(
                            null, 
                            "Biztosan törölni szeretnéd " + lastName + " " + firstName + " nevű mozdonyvezetőt?", 
                            "Törlés megerősítése", 
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) 
                    {
                        boolean success = db.deleteTD(lastName, firstName);
                        if (success) 
                        {
                            parentFrame.getTableModel().removeRow(selectedRow);
                            JOptionPane.showMessageDialog(
                                    null, 
                                    "Mozdonyvezető sikeresen törölve.");
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog(
                                    null, 
                                    "Hiba történt a törlés során.", 
                                    "Hiba", 
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Kérlek válassz ki egy mozdonyvezetőt a törléshez.", 
                            "Nincs kiválasztás", 
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(delete);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
