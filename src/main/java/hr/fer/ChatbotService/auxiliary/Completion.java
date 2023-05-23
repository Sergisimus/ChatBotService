package hr.fer.ChatbotService.auxiliary;

public record Completion(String model, String prompt, double temperature, int maxTokens) {
    public static Completion defaultValue(String prompt){
        return new Completion("text-davinci-003",  prompt, 0.4, 150);
    }

    //gpt-3.5-turbo
}
