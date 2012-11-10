/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.nicola.jeremy.serialarduinodemo.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author jem
 */
public class SliderFrame extends JFrame{
    
    private JSlider angle;
    
    public SliderFrame(){
        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        JLabel sliderLabel=new JLabel("Angle in deg", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.angle=new JSlider(JSlider.HORIZONTAL, 0, 180, 90);
        angle.setMajorTickSpacing(10);
        angle.setMinorTickSpacing(1);
        angle.setPaintTicks(true);
        angle.setPaintLabels(true);
        angle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        Font font=new Font("Serif", Font.ITALIC, 15);
        angle.setFont(font);
        
        mainPanel.add(sliderLabel);
        mainPanel.add(this.angle);
        
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JFrame frame=new JFrame("RaspberryPi <=> Arduino Servo control Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setPreferredSize(new Dimension(650, 105));
        frame.setResizable(false);
                
        frame.pack();
        frame.setVisible(true);
    }
    
    public JSlider getAngleSlider(){
        return this.angle;
    }
}
