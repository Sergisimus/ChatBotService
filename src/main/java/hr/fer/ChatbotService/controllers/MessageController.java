package hr.fer.ChatbotService.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class MessageController {



//    @Autowired
//    private ObjectMapper jsonMapper;
//    @Autowired
//    private OpenAIApiClient client;

    @GetMapping("/{id}")
    public List<String> getAllMessagesForId(@PathVariable String id) {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("hello");
        tmp.add("user");
        tmp.add(id);
        return tmp;
       // return chatRepository.findAllById(Collections.singleton(id));
    }

//    @PostMapping("/")
//    public ResponseBody newMessage(@RequestBody Message message) {
//        return null;
//    }



//    private String chatWithGpt3(String message) throws Exception {
//        var completion = CompletionRequest.defaultWith(message);
//        var postBodyJson = jsonMapper.writeValueAsString(completion);
//        var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiService.GPT_3);
//        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
//        return completionResponse.firstAnswer().orElseThrow();
//    }
//
//    @PostMapping(path = "/")
//    public ResponseBody newChatMessage(@RequestBody MessageDTO messageDTO) {
//        try {
//
//
//            model.addAttribute("request", dto.prompt());
//            model.addAttribute("response", chatWithGpt3(dto.prompt()));
//        } catch (Exception e) {
//            model.addAttribute("response", "Error in communication with OpenAI ChatGPT API.");
//        }
//        return null;
//    }
}
