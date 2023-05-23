package hr.fer.ChatbotService.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.fer.ChatbotService.auxiliary.Completion;
import hr.fer.ChatbotService.auxiliary.CompletionResponse;
import hr.fer.ChatbotService.models.Chat;
import hr.fer.ChatbotService.models.Message;
import hr.fer.ChatbotService.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class MessageController {


    @Value("${openai.api_key}")
    private String openaiApiKey;
    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private ChatRepository chatRepository;

    private static final URI CHATGPT_URI = URI.create("https://api.openai.com/v1/chat/completions");
    private HttpClient client = HttpClient.newHttpClient();


    @GetMapping("/{id}")
    public Optional<Chat> getAllMessagesForId(@PathVariable String id) {
//        ArrayList<String> tmp = new ArrayList<>();
//        tmp.add("hello");
//        tmp.add("user");
//        tmp.add(id);
//        return tmp;

        return chatRepository.findById(id);
    }

    @PostMapping(path = "/")
    public String newChatMessage(@RequestBody Message message) throws Exception {
        String response = "";
        String userId = message.getFrom();
        if (message.getText().equals("close")
                || message.getText().equals("close conversation")
                || message.getText().equals("exit")) {

            response = "Conversation ended";
            chatRepository.deleteAllById(Collections.singleton(userId));
            return response;
        } else {
            response = "Hello to you too!";
            //response = chatWithGpt(message.getText());
        }

        Message messageToSave = new Message();
        messageToSave.setFrom(message.getFrom());
        messageToSave.setText(message.getText());
        messageToSave.setTo(message.getTo());
//        if() {
//            messageToSave.setDisplayName(message.getDisplayName());
//
//        }
        ArrayList<String> messages = new ArrayList<>();
        if (chatRepository.existsById(userId)) {

            Chat chat = chatRepository.findById(userId).get();
            for (String s : chat.getAllMessages()) {
                messages.add(s);
            }
            messages.add("user:\t" + message.getText());
            messages.add("chatbot:\t" + response);
            chat.setAllMessages(messages);
            chatRepository.save(chat);
        } else {
            Chat chat = new Chat();
            chat.setUserId(userId);
            messages.add("user:\t" + message.getText());
            messages.add("chatbot:\t" + response);
            chat.setAllMessages(messages);
            chatRepository.save(chat);
        }
        return response;
    }


    private String chatWithGpt(String message) throws Exception {
        var request = HttpRequest.newBuilder()
                .uri(CHATGPT_URI)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openaiApiKey)
                .POST(postMessage(message))
                .build();
        var responseBody = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        System.out.println(responseBody);
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
        return completionResponse.firstAnswer().orElseThrow();
    }


    private HttpRequest.BodyPublisher postMessage(String message) throws JsonProcessingException {
        var completion = Completion.defaultValue(message);
        return HttpRequest.BodyPublishers.ofString(jsonMapper.writeValueAsString(completion));
    }


}
