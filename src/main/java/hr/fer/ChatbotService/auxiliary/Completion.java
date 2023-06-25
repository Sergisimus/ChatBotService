package hr.fer.ChatbotService.auxiliary;

public record Completion(String model, String prompt, double temperature, int maxTokens) {
    public static Completion defaultWith(String prompt){
        return new Completion("text-davinci-003",  prompt, 0.4, 150);
    }
}
