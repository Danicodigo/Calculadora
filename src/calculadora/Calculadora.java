package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class Calculadora extends JFrame {

    JTextField panelTexto;
    double resultado;
    String operacion;
    JPanel panelNumeros, panelOperaciones, panel;
    boolean nuevaOperacion = true;

    public Calculadora() {
        super();
        setSize(300, 400);
        setTitle("Calculadora Simple");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Vamos a dibujar sobre el panel
        panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());

        panelTexto = new JTextField("0", 20);
        //Coloca el texto a la derecha
        panelTexto.setHorizontalAlignment(JTextField.RIGHT);
        panelTexto.setFont(new Font("Calibri", 1, 20));
        panelTexto.setEditable(false);
        //Posiciona el panel arriba
        panel.add("North", panelTexto);

        panelNumeros = new JPanel();
        panelNumeros.setLayout(new GridLayout(4, 3));
        //Cambiar el tamaño de los bordes
        panelNumeros.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//private void botonPulsado(java.awt.ActionEvent evt){
//    getCampo().setText("");
//}
        for (int i = 9; i >= 0; i--) {
            JButton btn = new JButton();
            btn.setText(i + "");
            btn.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent evt) {
                    JButton btn = (JButton) evt.getSource();
                    numeroPulsado(btn.getText());
                }
            });

            panelNumeros.add(btn);
        }
        JButton btn = new JButton();
        btn.setText(".");
        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                numeroPulsado(btn.getText());
            }
        });

        panelNumeros.add(btn);

        panel.add("Center", panelNumeros);

        panelOperaciones = new JPanel();
        panelOperaciones.setLayout(new GridLayout(6, 1));
        panelOperaciones.setBorder(new EmptyBorder(5, 5, 5, 5));

        nuevaOperacion("+");
        nuevaOperacion("-");
        nuevaOperacion("X");
        nuevaOperacion("/");
        nuevaOperacion("=");
        nuevaOperacion("CE");

        panel.add("East", panelOperaciones);

        validate();
    }

    private void nuevaOperacion(String operacion) {
        JButton btn = new JButton(operacion);

        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                operacionPulsado(btn.getText());
            }
        });

        panelOperaciones.add(btn);
    }

    private void numeroPulsado(String digito) {
        if (panelTexto.getText().equals("0") || nuevaOperacion) {
            panelTexto.setText(digito);
        } else {
            panelTexto.setText(panelTexto.getText() + digito);
        }
        nuevaOperacion = false;
    }

    private void operacionPulsado(String tecla) {
        if (tecla.equals("=")) {
            calcularResultado();
        } else if (tecla.equals("CE")) {
            resultado = 0;
            panelTexto.setText("");
            nuevaOperacion = true;
        } else {
            operacion = tecla;
            if ((resultado > 0) && !nuevaOperacion) {
                calcularResultado();
            } else {
                resultado = new Double(panelTexto.getText());
            }
        }

        nuevaOperacion = true;
    }

    private void calcularResultado() {
        if (operacion.equals("+")) {
            resultado += new Double(panelTexto.getText());
        } else if (operacion.equals("-")) {
            resultado -= new Double(panelTexto.getText());
        } else if (operacion.equals("/")) {
            resultado /= new Double(panelTexto.getText());
        } else if (operacion.equals("X")) {
            resultado *= new Double(panelTexto.getText());
        }

        panelTexto.setText("" + resultado);
        operacion = "";
    }
}
