package UI.Components;

import commands.AddResource;
import entities.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class AddResourcePanel extends JPanel {

    //region Panel Properties
    /**
     * Name of Resource
     */
    JTextField nameTF;

    /**
     * Cost of resource
     */
    JFormattedTextField costTF;

    /**
     * Hourly cost to use resource
     */
    JFormattedTextField useCostTF;

    /**
     * Description of resource
     */
    JTextArea descriptionTA;

    /**
     * add resource to project when button clicked
     */
    JButton addResourceBtn;

    //TODO if time add id textbox that requires unique id from user or autofills unique id by asking model
    /**
     * Unique id for creation of ressource
     */
    static int id = 0;
    //endregion

    //region Initialization
    private void initialize()
    {

        nameTF = new JTextField(10);
        costTF = new JFormattedTextField(NumberFormat.getNumberInstance());
        costTF.setColumns(10);
        useCostTF = new JFormattedTextField(NumberFormat.getNumberInstance());
        useCostTF.setColumns(10);
        descriptionTA = new JTextArea(5, 12);
        addResourceBtn = new JButton("Add Resource to project");



        addResourceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run()
                    {
                        try
                        {
                            //if format is correct clear all textboxes, show success message
                            //Create resource object and save it to project resources
                            if (nameTF.getText().isBlank())
                            {
                                throw new IllegalArgumentException("Name field cannot be blank");
                            }
                            Runnable addResource = new AddResource(
                                    new Resource(
                                            nameTF.getText(),
                                            AddResourcePanel.id,
                                            ((Number)costTF.getValue()).intValue(),
                                            ((Number)useCostTF.getValue()).intValue(),
                                            descriptionTA.getText()
                                    )
                            );
                            AddResourcePanel.id++;
                            addResource.run();
                            clearTextBoxes();
                            JOptionPane.showMessageDialog(null, "Successfully added resource to project");
                        }
                        catch(java.lang.NumberFormatException exception)
                        {
                            JOptionPane.showMessageDialog(null,"Unexpected cost format. Please enter valid cost before adding resource to project.\n"+
                                    exception.getMessage());
                        }
                        catch(java.lang.IllegalArgumentException e)
                        {
                            JOptionPane.showMessageDialog(null,"Resource name cannot be empty.\n"+ e.getMessage());
                        }
                        catch (java.lang.Exception e)
                        {
                            JOptionPane.showMessageDialog(null, "Unexpected Error occured: \n"+e.getMessage());
                        }
                    }
                });

            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Name:"));
        panel.add(nameTF);
        add(panel);
        panel = new JPanel();
        panel.add(new JLabel("Cost:"));
        panel.add(costTF);
        add(panel);
        panel = new JPanel();
        panel.add(new JLabel("Hourly Cost of Use:"));
        panel.add(useCostTF);
        add(panel);
        panel = new JPanel();
        panel.add(new JLabel("Description:"));
        panel.add(descriptionTA);
        add(panel);
        add(addResourceBtn);
    }

    private void clearTextBoxes()
    {
        nameTF.setText("");
        costTF.setText("");
        useCostTF.setText("");
        descriptionTA.setText("");
    }


    //endregion
    public AddResourcePanel()
    {
        setPreferredSize(new Dimension(300, 300));
        initialize();
    }


    /**
     * Create the GUI and show it in a standalone JFrame.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static JFrame createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("BoxLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Set up the content pane.
        frame.add(new AddResourcePanel());
        frame.setTitle("Add Resource");

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        return frame;
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
