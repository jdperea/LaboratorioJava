package micalculadora;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author jdperea
 */
public class MiCalculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
        Scanner scanner = new Scanner(System.in);
        int numero = scanner.nextInt();
        //int numero = 5;
        double numbouble = 5.5;
        Integer num3 = 56;
        
        System.out.println("Hola Mundo "+numero);
                */
        JFrame f = new JFrame();
        // Parametrización botons
        int v,h,tboton,dimension;
        v=10;
        h=150;
        tboton = 55;
        dimension = 50;
        // Pantalla
        JTextField pantalla = new JTextField(20);
        int dimension_pantalla_x = (tboton*3)+dimension;
        int dimension_pantalla_y = dimension;
        pantalla.setBounds(v,h-tboton,dimension_pantalla_x,dimension_pantalla_y); 
        f.add(pantalla);
        
        // Mensaje de Error
        JLabel mensajeError = new JLabel("Expresión mal Formada");
        mensajeError.setBounds(v,(h-tboton)-40,dimension_pantalla_x,dimension_pantalla_y); 
        f.add(mensajeError);
        mensajeError.setVisible(false);
        
        //listener pantalla}
        /*
        pantalla.addPropertyChangeListener("text", new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                mensajeError.setVisible(false);
            }
            
        });*/

        // creacion teclado
        String [][] matrizCaracteres = {{"7", "8", "9", "+"}, 
                                        {"4", "5", "6", "-"},
                                        {"1", "2", "3", "*"},
                                        {"0", "C", "=", "/"}};
        /*String [][] matrizCaracteres = new String[4][4];
        matrizCaracteres[0][0] = "7";
        matrizCaracteres[0][1] = "8";
        matrizCaracteres[0][2] = "9";
        matrizCaracteres[0][3] = "+";
        matrizCaracteres[1][0] = "4";
        matrizCaracteres[1][1] = "5";
        matrizCaracteres[1][2] = "6";
        matrizCaracteres[1][3] = "-";
        matrizCaracteres[2][0] = "1";
        matrizCaracteres[2][1] = "2";
        matrizCaracteres[2][2] = "3";
        matrizCaracteres[2][3] = "*";
        matrizCaracteres[3][0] = "0";
        matrizCaracteres[3][1] = "C";
        matrizCaracteres[3][2] = "=";
        matrizCaracteres[3][3] = "/";*/
        
        for (int i = 0; i<4; i++) {
              
            for (int j = 0; j<4; j++) {
                JButton b = new JButton("_");
                b.setText( matrizCaracteres[j][i] ); 
                b.setBounds((i*tboton)+v, (j*tboton)+h, dimension, dimension);
                f.add(b);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton tecla = (JButton) e.getSource();
                        System.out.println(""+tecla.getText());
                        if(tecla.getText().equals("C")){
                            pantalla.setText("");
                            mensajeError.setVisible(false);
                        } else if (tecla.getText().equals("=")){
                            try {
                                String expresion = pantalla.getText();
                            ScriptEngineManager manager = new ScriptEngineManager();
                            ScriptEngine engine = manager.getEngineByName("js");
                            String result = String.valueOf(engine.eval(expresion));
                            pantalla.setText(result);
                            } catch (Exception ex) {
                                System.out.println(""+ex.getMessage());
                                System.out.println(""+ex.toString());
                                mensajeError.setVisible(true);
                            }
                        } else {
                            mensajeError.setVisible(false);
                            pantalla.setText(pantalla.getText() + tecla.getText());   
                        }
                    }
                });
            }
        }
        
        
        // Cargar vista
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }
    
}
