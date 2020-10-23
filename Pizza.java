package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Pizza extends JFrame {

    public static final String TITEL = "J-Pizza";
    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;
    public String[] groesseListe = {"klein", "mittel", "groß"};
    public String[] variationListe = {"Salami", "Schinken", "Peperoni", "Pilze", "Oliven", "extra Käse"};

    private JLabel preisLabel;
    private Panel checkboxPanel;
    private Panel variationPanel;
    private CheckboxGroup checkboxGroup;

    Pizza() {
        setTitle(TITEL);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(true);
        setLocationRelativeTo(null);
        createComponents();
        addComponents();
        adjustPrice();
        setVisible(true);
    }

    private void createComponents() {
        checkboxPanel = new Panel();
        variationPanel = new Panel(new GridLayout(2, 3));
        checkboxGroup = new CheckboxGroup();
        CheckboxListener checkboxListener = new CheckboxListener();
        for (String groesse : groesseListe) {
            boolean state = false;
            if (groesse.equals("mittel")) {
                state = true;
            }
            Checkbox radioButton = new Checkbox(groesse, state, checkboxGroup);
            radioButton.addItemListener(checkboxListener);
            checkboxPanel.add(radioButton);
        }
        for (String variation : variationListe) {
            Checkbox checkbox = new Checkbox(variation);
            checkbox.addItemListener(checkboxListener);
            variationPanel.add(checkbox);
        }
        preisLabel = new JLabel();
    }

    private void addComponents() {
        add(checkboxPanel, BorderLayout.NORTH);
        add(variationPanel);
        add(preisLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new Pizza();
    }

    private void adjustPrice() {
        double basisPreis = 0;
        double belagPreis = 0;
        switch (checkboxGroup.getSelectedCheckbox().getLabel()) {
            case "klein":
                basisPreis = 4;
                belagPreis = 0.5;
                break;
            case "mittel":
                basisPreis = 4.5;
                belagPreis = 0.75;
                break;
            case "groß":
                basisPreis = 5;
                belagPreis = 1;
                break;
        } // TODO: Datenstruktur mit Pizzagröße und Preis implementieren
        for (Component component : variationPanel.getComponents()) {
            if (!(component instanceof Checkbox)) {
                continue;
            }
            Checkbox checkbox = (Checkbox) component;
            if (checkbox.getState()) {
                basisPreis += belagPreis;
            }
        }
        preisLabel.setText("Preis: " + basisPreis + "€");
        preisLabel.repaint();
    }

    class CheckboxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            adjustPrice();
        }
    }
}
