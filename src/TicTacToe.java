import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// El acctionlistener viene de la clase awt y sirve para implementar botones
//Pero para utilizar esto se necesita actionevent que viene de complemento
public class TicTacToe implements ActionListener {

    //Declara una ventana emergente que es el JFrame
    private JFrame frame = new JFrame();
    //Implementa los 9 botones
    private JButton[] botones = new JButton[9];
    private Jugador jugadorX = new Jugador("X");
    private Jugador jugadorO = new Jugador("O");

    //Creacion de la ventan emergente
    public TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));


        for (int i = 0; i < 9; i++) {
            botones[i] = new JButton();
            botones[i].setFont(new Font("Impact", Font.PLAIN, 40));
            botones[i].setFocusable(false);
            botones[i].addActionListener(this);
            frame.add(botones[i]);
        }

    }

    public void iniciarJuego() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonaplastado = (JButton) e.getSource();

        if (botonaplastado.getText().equals("")) {
            if (jugadorX.turnos()) {
                jugadorX.realizarMovimiento(botonaplastado);
            } else {
                jugadorO.realizarMovimiento(botonaplastado);
            }

            if (ganar()) {
                JOptionPane.showMessageDialog(frame, (jugadorX.turnos() ? jugadorX.getSimbolo() : jugadorO.getSimbolo()) + " gana");
                reiniciar();
            } else if (empate()) {
                JOptionPane.showMessageDialog(frame, "Empate");
                reiniciar();
            } else {
                jugadorX.setTurn(!jugadorX.turnos());
                jugadorO.setTurn(!jugadorO.turnos());
            }
        }
    }
    //Revisa cuando haya un ganador mediante matrices ya sea horizontal, vertical o diagonal
    private boolean ganar() {
        return verificarlinea(0, 1, 2) || verificarlinea(3, 4, 5) || verificarlinea(6, 7, 8) ||
                verificarlinea(0, 3, 6) || verificarlinea(1, 4, 7) || verificarlinea(2, 5, 8) ||
                verificarlinea(0, 4, 8) || verificarlinea(2, 4, 6);
    }

    private boolean verificarlinea(int a, int b, int c) {
        return botones[a].getText().equals(botones[b].getText()) &&
                botones[b].getText().equals(botones[c].getText()) &&
                !botones[a].getText().equals("");
    }
    //Verifica que todas las celdas esten ocupadas para comprobar el empate
    private boolean empate() {
        for (JButton button : botones) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }
    //Reinicia el juego y lo posiciona al jugador x para comenzar nuevamente
    private void reiniciar() {
        for (JButton button : botones) {
            button.setText("");
        }
        jugadorX.setTurn(true);
    }

}
