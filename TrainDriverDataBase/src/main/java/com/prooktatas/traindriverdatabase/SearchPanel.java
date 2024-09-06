package com.prooktatas.traindriverdatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KomlosyT
 */
public class SearchPanel extends JPanel 
{
    private DB db;
    private JTable table;
    private DefaultTableModel tableModel;

    public SearchPanel(DB db, JTable table, DefaultTableModel tableModel) 
    {
        this.db = db;
        this.table = table;
        this.tableModel = tableModel;
        setLayout(null);
        
        
        JButton lastNameButton = new JButton("Vezetéknév");
        lastNameButton.setBounds(40, 470, 100, 30);
        lastNameButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String SearchedLastName = JOptionPane.showInputDialog("Adja meg a keresett vezetéknevet:");
                
                if (SearchedLastName != null && !SearchedLastName.isEmpty()) 
                {
                    List<TD> szurtMV = db.searchTDByLastName(SearchedLastName);
                    updateTable(szurtMV);
                }
            }
        });
        add(lastNameButton);
        

        JButton firstNameButton = new JButton("Keresztnév");
        firstNameButton.setBounds(160, 470, 100, 30);
        firstNameButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String SearchedFirstNameButton = JOptionPane.showInputDialog("Adja meg a keresett keresztnevet:");
                
                if (SearchedFirstNameButton != null && !SearchedFirstNameButton.isEmpty()) 
                {
                    List<TD> szurtMV = db.searchTDByFirstName(SearchedFirstNameButton);
                    updateTable(szurtMV);
                }
            }
        });
        add(firstNameButton);
        

        JButton ageButton = new JButton("Kor");
        ageButton.setBounds(280, 470, 100, 30);
        
        ageButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                Integer[] ageOption = new Integer[44];
                
                for (int i = 0; i < ageOption.length; i++)ageOption[i] = 21 + i;
                
                JComboBox<Integer> korComboBox = new JComboBox<>(ageOption);

                JPanel panel = new JPanel();
                panel.add(new JLabel("Válassza ki a kort:"));
                panel.add(korComboBox);


                int result = JOptionPane.showConfirmDialog
                    (
                    null, 
                    panel, 
                    "Kor kiválasztása", 
                    JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.PLAIN_MESSAGE
                    );

                if (result == JOptionPane.OK_OPTION) 
                {
                    int searchedAge = (int) korComboBox.getSelectedItem();
                    List<TD> filteredTD = db.searchTDByAge(searchedAge);
                    updateTable(filteredTD);
                }
            }
        });
        add(ageButton);
        

        JButton categoryButton = new JButton("Kategoria");
        categoryButton.setBounds(400, 470, 100, 30);
        categoryButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                boolean validInput = false;
                
                while (!validInput) 
                {
                    JCheckBox electricityCheckBox = new JCheckBox("Villany");
                    JCheckBox dieselCheckBox = new JCheckBox("Dízel");

                    JPanel panel = new JPanel();
                    panel.add(new JLabel("Válassza ki a kategóriát:"));
                    panel.add(electricityCheckBox);
                    panel.add(dieselCheckBox);

                    int result = JOptionPane.showConfirmDialog
                        (
                        null, 
                        panel, 
                        "Kategória kiválasztása", 
                        JOptionPane.OK_CANCEL_OPTION, 
                        JOptionPane.PLAIN_MESSAGE
                        );

                    if (result == JOptionPane.OK_OPTION) 
                    {
                        int selectedCount = 0;
                        StringBuilder category = new StringBuilder();

                        if (electricityCheckBox.isSelected()) 
                        {
                            category.append("Villany");
                            selectedCount++;
                        }
                        
                        if (dieselCheckBox.isSelected()) 
                        {
                            category.append("Dízel");
                            selectedCount++;  
                        }
                        
                        if (selectedCount > 1) 
                        {
                            JOptionPane.showMessageDialog(
                                null, 
                                "Hiba: Kérjük, válasszon csak egy kategóriát egyszerre.", 
                                "Hiba", 
                                JOptionPane.ERROR_MESSAGE);
                        } 
                        else if (selectedCount == 1) 
                        {
                            List<TD> filteredTD = db.searchTDByCategory(category.toString());
                            updateTable(filteredTD);
                            validInput = true;
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog(
                                null, 
                                "Kérjük, válasszon legalább egy típust.", 
                                "Figyelmeztetés", 
                                JOptionPane.WARNING_MESSAGE);
                        }
                    } 
                    else 
                    {
                        validInput = true;
                    }
                }
            }
        });
        add(categoryButton);
        

        JButton typeButton = new JButton("Típus");
        typeButton.setBounds(520, 470, 100, 30);
        typeButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                boolean validInput = false;

                while (!validInput) 
                {
                    JCheckBox bzTipCheckBox = new JCheckBox("117");
                    JCheckBox sziliTipCheckBox = new JCheckBox("431");
                    JCheckBox taurusTipCheckBox = new JCheckBox("470");
                    JCheckBox traxxTipCheckBox = new JCheckBox("480");
                    JCheckBox szergejipCheckBox = new JCheckBox("628");
                    JCheckBox gigantTipCheckBox = new JCheckBox("630");
                    
                    JPanel panel = new JPanel();
                    panel.add(new JLabel("Válasszon ki egy tipust:"));

                    panel.add(bzTipCheckBox);
                    panel.add(sziliTipCheckBox);
                    panel.add(taurusTipCheckBox);
                    panel.add(traxxTipCheckBox);
                    panel.add(szergejipCheckBox);
                    panel.add(gigantTipCheckBox);
                    
                    int result = JOptionPane.showConfirmDialog(
                        null, 
                        panel, 
                        "Típus kiválasztása", 
                        JOptionPane.OK_CANCEL_OPTION, 
                        JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) 
                    {
                        int selectedCount = 0;
                        StringBuilder type = new StringBuilder();
                        
                        if (bzTipCheckBox.isSelected()) 
                        {
                            type.append("117,");
                            selectedCount++;
                        }
                        
                        if (sziliTipCheckBox.isSelected()) 
                        {
                            type.append("431,");
                            selectedCount++;
                        }
                        
                        if (taurusTipCheckBox.isSelected()) 
                        {
                            type.append("470,");
                            selectedCount++;
                        }
                        
                        if (traxxTipCheckBox.isSelected()) 
                        {
                            type.append("480,");
                            selectedCount++;
                        }
                        
                        if (szergejipCheckBox.isSelected()) 
                        {
                            type.append("628,");
                            selectedCount++;
                        }
                        
                        if (gigantTipCheckBox.isSelected()) 
                        {
                            type.append("630,");
                            selectedCount++;
                        }

                        if (type.length() > 0)type.setLength(type.length() - 1);

                        if (selectedCount > 1) 
                        {
                            JOptionPane.showMessageDialog(
                                null, 
                                "Hiba: Kérjük, válasszon csak egy típust egyszerre.", 
                                "Hiba", 
                                JOptionPane.ERROR_MESSAGE);
                        } 
                        else if (selectedCount == 1) 
                        {
                            List<TD> filteredTD = db.searchTDByType(type.toString());
                            updateTable(filteredTD);
                            validInput = true;
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog(
                                null, 
                                "Kérjük, válasszon legalább egy típust.", 
                                "Figyelmeztetés", 
                                JOptionPane.WARNING_MESSAGE);
                        }
                    } 
                    else 
                    {
                        validInput = true;
                    }
                }
            }
        });
        add(typeButton);
        
        JButton lineButton = new JButton("Vonal");
        lineButton.setBounds(640, 470, 100, 30);
        lineButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                boolean validInput = false;

                while (!validInput) 
                {
                    JCheckBox firstLineCheckBox = new JCheckBox("1 vonal ");
                    JCheckBox secondLineCheckBox = new JCheckBox("2 vonal ");
                    JCheckBox thirdLineCheckBox = new JCheckBox("3 vonal");
                    JCheckBox fourthLineCheckBox = new JCheckBox("4 vonal");
                    JCheckBox fifthLineCheckBox = new JCheckBox("5 vonal");
                    JCheckBox sixthLineCheckBox = new JCheckBox("6 vonal");

                    JPanel panel = new JPanel();
                    panel.add(new JLabel("Válasszon ki egy vonalat:"));

                    panel.add(firstLineCheckBox);
                    panel.add(secondLineCheckBox);
                    panel.add(thirdLineCheckBox);
                    panel.add(fourthLineCheckBox);
                    panel.add(fifthLineCheckBox);
                    panel.add(sixthLineCheckBox);

                    int result = JOptionPane.showConfirmDialog
                        (
                        null, 
                        panel, "Vonal kiválasztása", 
                        JOptionPane.OK_CANCEL_OPTION, 
                        JOptionPane.PLAIN_MESSAGE
                        );

                    if (result == JOptionPane.OK_OPTION) 
                    {
                        int selectedCount = 0;
                        StringBuilder line = new StringBuilder();
                        
                        if (firstLineCheckBox.isSelected()) 
                        {
                            line.append("1es,");
                            selectedCount++;
                        }
                        
                        if (secondLineCheckBox.isSelected()) 
                        {
                            line.append("2es,");
                            selectedCount++;
                        }
                        
                        if (thirdLineCheckBox.isSelected()) 
                        {
                            line.append("3as,");
                            selectedCount++;
                        }
                        
                        if (fourthLineCheckBox.isSelected()) 
                        {
                            line.append("4es,");
                            selectedCount++;
                        }
                        
                        if (fifthLineCheckBox.isSelected()) 
                        {
                            line.append("5os,");
                            selectedCount++;
                        }
                        
                        if (sixthLineCheckBox.isSelected()) 
                        {
                            line.append("6os,");
                            selectedCount++;
                        }

                        if (line.length() > 0)line.setLength(line.length() - 1);
                        
                        if (selectedCount > 1) 
                        {
                            JOptionPane.showMessageDialog
                                (
                                null, 
                                "Hiba: Kérjük, válasszon csak egy vonalat egyszerre.", 
                                "Hiba", 
                                JOptionPane.ERROR_MESSAGE
                                );
                        } 
                        else if (selectedCount == 1) 
                        {
                            List<TD> filteredTD = db.searchTDByLine(line.toString());
                            updateTable(filteredTD);
                            validInput = true; 
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog
                                (
                                null, 
                                "Kérjük, válasszon legalább egy vonalat.", 
                                "Figyelmeztetés", 
                                JOptionPane.WARNING_MESSAGE
                                );
                        }
                    } 
                    else 
                    {
                        validInput = true;
                    }
                }
            }
        });
        add(lineButton);
    }
    

    private void updateTable(List<TD> filteredTD) 
    {
        tableModel.setRowCount(0);
        for (TD td : filteredTD) 
        {
            tableModel.addRow(new Object[]
            {
                td.getLastName(), 
                td.getFirstName(), 
                td.getAge(), 
                td.getCategory(), 
                td.getType(), 
                td.getLine()
            });
        }
    }
}
