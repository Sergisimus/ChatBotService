package hr.fer.ChatbotService.repositories;

import hr.fer.ChatbotService.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ChatRepository extends JpaRepository<Chat, String> {

}
