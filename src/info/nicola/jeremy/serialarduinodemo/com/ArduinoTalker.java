/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.nicola.jeremy.serialarduinodemo.com;

import gnu.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author jem
 */
public class ArduinoTalker implements ChangeListener {

    public static final String PORT_NAME = "/dev/ttyACM0";
    private boolean busy;
    private CommPortIdentifier portIdentifier;
    private CommPort commPort;
    private OutputStream out;
    private InputStream in;

    public ArduinoTalker() {
        System.err.println("Using port name: " + PORT_NAME);
        this.busy = false;
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(PORT_NAME);

            if (portIdentifier.isCurrentlyOwned()) {
                System.err.println("Port currently in use");
            } else {
                commPort = portIdentifier.open(this.getClass().getName(), 2000);

                if (commPort instanceof SerialPort) {
                    SerialPort serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                    this.out = serialPort.getOutputStream();
                    this.in = serialPort.getInputStream();
                }
            }
        } catch (IOException ex) {
            System.err.println("IOException");
        } catch (UnsupportedCommOperationException ex) {
            System.err.println("UnsupportedCommOperationException");
        } catch (PortInUseException ex) {
            System.err.println("PortInUseException");
        } catch (NoSuchPortException ex) {
            System.err.println("No such port");
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        int val = source.getValue();
        if (!busy) {
            sendToArduino(val);
        }
    }

    public void kill() {
        commPort.close();
    }

    private void sendToArduino(final int command) {
        new Thread(new Runnable() {

            @Override
            public void run() {

                busy = true;
                try {
                    if (out != null) {
                        out.write(command);
                    }
                    if (in != null) {
                        System.out.println("Ardnuino answered back: " + in.read());
                        busy = false;
                    }
                } catch (IOException ex) {
                    System.err.println("Problem with sendToArduino() method");
                }
            }
        }).start();
    }
}
