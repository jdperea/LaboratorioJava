/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package micalculadora;

import javax.swing.*;
import java.awt.event.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException; 
import java.beans.*;
/**
 *
 * @author Carlos Tovar
 */
public class CalculadoraProfe {
    public static void main(String[] args) {

    JFrame f = new JFrame();

    int origen_x = 100;
    int origen_y = 100;
    int dimension_boton = 50;


    // Pantalla
    JTextField pantalla = new JTextField(20);
    int dimension_pantalla_x = dimension_boton * 4;
    int dimension_pantalla_y = dimension_boton;
    pantalla.setBounds(100, 20, dimension_pantalla_x, dimension_pantalla_y);
    f.add(pantalla);


    // Mensaje de error
    JLabel mensajeError = new JLabel("expresión malformada");
    mensajeError.setBounds(100, 75, 200, 22);
    mensajeError.setVisible(false);
    f.add(mensajeError);

    // Agregar listener a la pantalla
    pantalla.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent keyEvent) {
        mensajeError.setVisible(false);
      }
      public void keyPressed(KeyEvent keyEvent) {
        //System.out.println("Pressed");
      }

      public void keyReleased(KeyEvent keyEvent) {
        //System.out.println("Released");
      }

    });

    ActionListener procesar_clicks = new ActionListener(){  
      public void actionPerformed(ActionEvent e){  
        JButton boton_clickeado = (JButton) e.getSource();

        // Procesar accion
        String tecla = boton_clickeado.getText();
        if(tecla.equals("C")) {
          pantalla.setText("");
          mensajeError.setVisible(false);
        }else if(tecla.equals("=")){

          try{
            String expresion = pantalla.getText();
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            String result = String.valueOf(engine.eval(expresion));
            pantalla.setText(result);
          }catch(ScriptException ex){
            mensajeError.setVisible(true);
          }
          
        }
        else{
          pantalla.setText(pantalla.getText() + tecla); 
          mensajeError.setVisible(false);
        }
        
      }  
    };

    // Teclado
    String [][] matrizCaracteres = {{"7", "8", "9", "+"}, 
                                  {"4", "5", "6", "-"},
                                  {"1", "2", "3", "*"},
                                  {"0", "C", "=", "/"}};
    for(int i=0; i<4; i++) {
      for(int j=0; j<4; j++) {
        JButton b = new JButton();
        b.setText(String.valueOf(matrizCaracteres[j][i]));
        b.setBounds(i*dimension_boton + origen_x, j*dimension_boton + origen_y, dimension_boton, dimension_boton);

        b.addActionListener(procesar_clicks);  

        f.add(b);
      }
    }

    f.setSize(400, 400);
    f.setLayout(null);
    f.setVisible(true);
  }
}
