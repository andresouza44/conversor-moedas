import java.time.Instant;

public class LogEntity {

    private double valor;
    private Cotacao cotacao;
    private Instant moment;

    public LogEntity() {
    }

    public LogEntity(double valor, Cotacao cotacao) {
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

    public Instant getMoment() {
        return moment;
    }

    @Override
    public String toString() {
        String baseCode = cotacao.base_code();
        String targetCode = cotacao.target_code();
        Double taxaConversao = cotacao.conversion_rate();
        Double valor = this.valor;

        return String.format("Cotação: 1[%s] = %.4f [%s] - Valor de %.2f %s = %.2f %s\n",
                baseCode, taxaConversao,
                targetCode, valor / taxaConversao,
                baseCode, valor, targetCode);


    }
}
