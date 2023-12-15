import javax.swing.*;

public class Jugador {
    //atributos del jugador
    private String simbolo;
    private boolean turnos;
    //constructor del jugador
    public Jugador(String simbolo) {
        this.simbolo = simbolo;
        this.turnos = false;
    }
    //los get and setter del jugador
    public String getSimbolo() {
        return simbolo;
    }

    public boolean turnos() {
        return turnos;
    }

    public void setTurn(boolean turno) {
        turnos = turno;
    }
    //Metodo del jugador para jugar xd
    public void realizarMovimiento(JButton button) {
        button.setText(simbolo);
    }
}
