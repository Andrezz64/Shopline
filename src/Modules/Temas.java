/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modules;

import Viwes.Main;
import com.formdev.flatlaf.FlatDarculaLaf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author andre
 */
public  class Temas {
    public static void Dark()
    {
          try {
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    catch( Exception ex ) {
        System.err.println( "Failed to initialize LaF" );
    }
    }
    
}
