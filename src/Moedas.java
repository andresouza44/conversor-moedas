

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;


public record Moedas(List<List<String>> supported_codes, String result,
                     @SerializedName("error-type") String errorType) {

}
