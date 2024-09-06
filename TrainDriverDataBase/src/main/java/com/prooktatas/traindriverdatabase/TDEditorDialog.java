package com.prooktatas.traindriverdatabase;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author KomlosyT
 */
public class TDEditorDialog extends JDialog {

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
    
    public TDEditorDialog(JFrame parentFrame, DB db, String lastName, String firstName, int age, String category, String type, String line)
    {
        super(parentFrame, "MV adatok szerkesztése", true);
        setSize(360, 400);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        lastNameField = new JTextField(lastName);
        firstNameField = new JTextField(firstName);
       
        ageComboBox = new JComboBox<>();
        for (int i = 21; i <= 65; i++)ageComboBox.addItem(i);
        ageComboBox.setSelectedItem(age);
        
        JLabel lastNameLabel = new JLabel("Vezetéknév:");
        JLabel firstNameLabel = new JLabel("Keresztnév:");
        JLabel ageLabel = new JLabel("Kor:");
        JLabel typeLabel = new JLabel("Típusok:");
        JLabel lineLabel = new JLabel("Vonalak:");
        JLabel categoryLabel = new JLabel("Kategória:");
        
        String[] categoryArray = category.split(", ");
        electricityCheckBox = new JCheckBox(
                "Villany", categoryArray.length > 0 && categoryArray[0].equals("Villany"));
        dieselCheckBox = new JCheckBox(
                "Dízel", categoryArray.length > 1 && categoryArray[1].equals("Dízel"));


        bzTipCheckBox = new JCheckBox("117 típus", type.contains("117"));
        sziliTipCheckBox = new JCheckBox("431 típus", type.contains("431"));
        taurusTipCheckBox = new JCheckBox("470 típus", type.contains("470"));
        traxxTipCheckBox = new JCheckBox("480 típus", type.contains("480"));
        szergejipCheckBox = new JCheckBox("628 típus", type.contains("628"));
        gigantTipCheckBox = new JCheckBox("630 típus", type.contains("630"));

        firstLineCheckBox = new JCheckBox("1., vonal", line.contains("1es"));
        secondLineCheckBox = new JCheckBox("2., vonal", line.contains("2es"));
        thirdLineCheckBox = new JCheckBox("3., vonal", line.contains("3as"));
        fourthLineCheckBox = new JCheckBox("4., vonal", line.contains("4es"));
        fifthLineCheckBox = new JCheckBox("5., vonal", line.contains("5os"));
        sixthLineCheckBox = new JCheckBox("6., vonal", line.contains("6os"));
        
        
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
        
        JPanel panel = new JPanel();
        add(lastNameLabel);
        add(lastNameField);
        add(firstNameLabel);
        add(firstNameField);
        add(ageLabel);
        add(ageComboBox);
        add(typeLabel);
        add(electricityCheckBox);
        add(dieselCheckBox);
        add(lineLabel);
        add(bzTipCheckBox);
        add(sziliTipCheckBox);
        add(taurusTipCheckBox);
        add(traxxTipCheckBox);
        add(szergejipCheckBox);
        add(gigantTipCheckBox);
        add(categoryLabel);
        add(firstLineCheckBox);
        add(secondLineCheckBox);
        add(thirdLineCheckBox);
        add(fourthLineCheckBox);
        add(fifthLineCheckBox);
        add(sixthLineCheckBox);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String newLastName = lastNameField.getText();
                String newFirstName = firstNameField.getText();
                
                if (!isNameValid(newLastName)) 
                {
                JOptionPane.showMessageDialog(
                        null, 
                        "A vezetéknév csak betűket, szóközt és kötőjelet tartalmazhat.", 
                        "Hiba", 
                        JOptionPane.ERROR_MESSAGE);
                return;
                }
                
                if (!isNameValid(newFirstName)) 
                {
                JOptionPane.showMessageDialog(
                        null, 
                        "A keresztnév csak betűket, szóközt és kötőjelet tartalmazhat.", 
                        "Hiba", 
                        JOptionPane.ERROR_MESSAGE);
                return;
                }
                
                if (newFirstName.length() > 20) 
                {
                    JOptionPane.showMessageDialog(
                            null, 
                            "A keresztnév nem lehet hosszabb 20 karakternél.", 
                            "Hiba", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (newLastName.length() > 20) 
                {
                    JOptionPane.showMessageDialog(
                            null, 
                            "A vezetéknév nem lehet hosszabb 20 karakternél.", 
                            "Hiba", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int typesCount = 0;
                if (bzTipCheckBox.isSelected()) typesCount++;
                if (sziliTipCheckBox.isSelected()) typesCount++;
                if (taurusTipCheckBox.isSelected()) typesCount++;
                if (traxxTipCheckBox.isSelected()) typesCount++;
                if (szergejipCheckBox.isSelected()) typesCount++;
                if (gigantTipCheckBox.isSelected()) typesCount++;

                int lineCount = 0;
                if (firstLineCheckBox.isSelected()) lineCount++;
                if (secondLineCheckBox.isSelected()) lineCount++;
                if (thirdLineCheckBox.isSelected()) lineCount++;
                if (fourthLineCheckBox.isSelected()) lineCount++;
                if (fifthLineCheckBox.isSelected()) lineCount++;
                if (sixthLineCheckBox.isSelected()) lineCount++;

                if (typesCount > 5) 
                {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Legfeljebb 5 típus választható ki.", 
                            "Hiba", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (lineCount > 5) 
                {
                    JOptionPane.showMessageDialog(
                            null, 
                            "Legfeljebb 5 vonal választható ki.", 
                            "Hiba", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                StringBuilder newCategory = new StringBuilder();
                if (electricityCheckBox.isSelected()) newCategory.append("Villany, ");
                if (dieselCheckBox.isSelected()) newCategory.append("Dízel, ");
                if (newCategory.length() > 0) newCategory.setLength(newCategory.length() - 2);

                StringBuilder newTypes = new StringBuilder();
                if (bzTipCheckBox.isSelected()) newTypes.append("117,");
                if (sziliTipCheckBox.isSelected()) newTypes.append("431,");
                if (taurusTipCheckBox.isSelected()) newTypes.append("470,");
                if (traxxTipCheckBox.isSelected()) newTypes.append("480,");
                if (szergejipCheckBox.isSelected()) newTypes.append("628,");
                if (gigantTipCheckBox.isSelected()) newTypes.append("630,");
                if (newTypes.length() > 0) newTypes.setLength(newTypes.length() - 1);

                StringBuilder newLines = new StringBuilder();
                if (firstLineCheckBox.isSelected()) newLines.append("1es,");
                if (secondLineCheckBox.isSelected()) newLines.append("2es,");
                if (thirdLineCheckBox.isSelected()) newLines.append("3as,");
                if (fourthLineCheckBox.isSelected()) newLines.append("4es,");
                if (fifthLineCheckBox.isSelected()) newLines.append("5os,");
                if (sixthLineCheckBox.isSelected()) newLines.append("6os,");
                if (newLines.length() > 0) newLines.setLength(newLines.length() - 1);

                TD mv = new TD(
                        newLastName, 
                        newFirstName, 
                        (int) ageComboBox.getSelectedItem(),
                        newCategory.toString(), 
                        newTypes.toString(), 
                        newLines.toString());
                db.updateTD(mv, lastName);

                ((Run) parentFrame).loadData();
                dispose();
            }
        });
        
        okButton.setBounds(25, 315, 310, 30);
        add(panel);
        add(okButton);
        
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }
    
    private boolean isNameValid(String name) 
    {
        return name.matches("[a-zA-ZáéíóöőúüűÁÉÍÓÖŐÚÜŰ\\-\\s]+");
    }
}
