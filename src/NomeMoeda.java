public enum NomeMoeda {
    USD("USD", "United States Dollar"),
    EUR("EUR", "Euro"),
    COP("COP", "Colombian Peso"),
    BOB("BOB", "Bolivian Boliviano"),
    BRL("BRL", "Brazilian Real"),
    ARS("ARS", "Argentine Peso");


    private final String codigo;
    private final String nome;

    NomeMoeda(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

}