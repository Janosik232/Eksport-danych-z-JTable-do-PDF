package org.example;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public MainFrame() {
        setTitle("System Raportowania Magazynowego");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nazwa Produktu");
        tableModel.addColumn("Ilość");
        tableModel.addColumn("Cena");

        tableModel.addRow(new Object[]{1, "Klawiatura", 10, 100.99});
        tableModel.addRow(new Object[]{2, "Mysz", 25, 39.99});
        tableModel.addRow(new Object[]{3, "Monitor", 5, 699.99});
        tableModel.addRow(new Object[]{4, "Laptop", 3, 4299.99});

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton pdfButton = new JButton("Generuj Raport PDF");
        pdfButton.addActionListener(e -> {
            try {
                PdfReportGenerator.generate(table);
                JOptionPane.showMessageDialog(this, "Raport PDF wygenerowany");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Błąd generowania PDF");
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(pdfButton, BorderLayout.SOUTH);
    }
}
