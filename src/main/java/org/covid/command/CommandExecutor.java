package org.covid.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.covid.command.client.RestClient;
import org.covid.domain.Result;

public class CommandExecutor {
    private RestClient restClient = new RestClient();
    private ObjectMapper mapper = new ObjectMapper();

    public String infoByName(String name) {
        String answer = restClient.requestCountryByName(name);
        if (answer != null) {
            try {
                String normalizeAnswer = answer.replaceAll("[\\[\\]]", "");
                Result result = mapper.readValue(normalizeAnswer,Result.class);
                return result.toString();
            } catch (JsonProcessingException e) {
                return "Не правильное название страны";
            }
        }
        return "Ошибка";
    }
}
