import com.google.gson.annotations.SerializedName;

public record Cotacao(String base_code, String target_code, Double conversion_rate, String result,
                      @SerializedName("error-type") String errorType) {
}
