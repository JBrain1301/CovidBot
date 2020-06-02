package org.covid.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
public class Result {
    @JsonProperty(value = "country")
    private String country;
    @JsonProperty(value = "confirmed")
    private String confirmed;
    @JsonProperty(value = "recovered")
    private String recovered;
    @JsonProperty(value = "critical")
    private String critical;
    @JsonProperty(value = "deaths")
    private String deaths;
    @JsonProperty(value = "latitude")
    private String latitude;
    @JsonProperty(value = "longitude")
    private String longitude;

    public Result() {
    }

    @Override
    public String toString() {
        return "Страна: " + country + "\n" +
                "Подтвержденно: " + confirmed + "\n" +
                "Выздоровевших: " + recovered + "\n" +
                "Критическое состояние: " + critical+ "\n"  +
                "Смертей: " + deaths + "\n";
    }
}
