/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.nicola.jeremy.serialarduinodemo;

import gnu.io.CommPortIdentifier;
import info.nicola.jeremy.serialarduinodemo.view.SliderFrame;
import info.nicola.jeremy.serialarduinodemo.com.ArduinoTalker;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author jem
 */
public class StartSerialArduinoDemo {

    static private SliderFrame frame=null;
    static private ArduinoTalker talker;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            
            SwingUtilities.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    frame=new SliderFrame();
                }
            });
            talker=new ArduinoTalker();
            frame.getAngleSlider().addChangeListener(talker);
            frame.addWindowListener(new WindowAdapter(){
            
                @Override
                public void windowClosed(WindowEvent e){
                talker.kill();
            }});
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(StartSerialArduinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
