import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Log {

    private double valor;
    private Cotacao cotacao;
    private Instant moment;


    public  Log(){
    }

    public Log(double valor, Cotacao cotacao) {
        this.valor = valor;
        this.cotacao = cotacao;
        this.moment = Instant.now();
    }

    public double getValor() {
        return valor;
    }

    public Cotacao getCotacao() {
        return cotacao;
    }

    @Override
    public String toString() {
        return
                "valor= " + valor +
                " cotação = " + cotacao +
                " moment= " + moment +
                '}';
    }
}
