package com.prooktatas.traindriverdatabase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KomlosyT
 * This class runs the program.
 */
public class Run extends javax.swing.JFrame 
{

    private DB db;
    private JTable table;
    private DefaultTableModel tableModel;
    //main window
    public Run() 
    {
        db = new DB();
        setTitle("Mozdonyvezető adatbázis");
        setSize(800, 650);
        setLayout(null);
        setResizable(false);
                
        ButtonPanel buttonPanel = new ButtonPanel(this, db);
        buttonPanel.setBounds(0, 0, 800, 60);
        buttonPanel.setBackground(Color.GRAY);
        add(buttonPanel);

        String[] columnNames = {"Vezetéknév", "Keresztnév", "Kor", "Kategória", "Típusok", "Vonalak"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 60, 760, 400);
        add(scrollPane);
        
        SearchPanel searchPanel = new SearchPanel(db, table, tableModel);
        searchPanel.setBackground(Color.GRAY);
        searchPanel.setBounds(0, 0, 800, 550);
        add(searchPanel);

        loadData();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        centerWindow(this);
        setVisible(true);
    }
    
    public JTable getTable() 
    {
        return table;
    }
    
    public DefaultTableModel getTableModel() 
    {
        return tableModel;
    }
    
    public void loadData() 
    {
        List<TD> tDList = db.getAllTD();
        updateTable(tDList);
    }
    
    public void updateTable(List<TD> mvList) 
    {
        tableModel.setRowCount(0);
        for (TD mv : mvList) 
        {
            Object[] row = 
            {
                mv.getLastName(),
                mv.getFirstName(),
                mv.getAge(),
                mv.getCategory(),
                mv.getType(),
                mv.getLine()
            };
            tableModel.addRow(row);
        }
    }
    
    public static void centerWindow(JFrame frame) 
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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

    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Run.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Run.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Run.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Run.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new Run();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
