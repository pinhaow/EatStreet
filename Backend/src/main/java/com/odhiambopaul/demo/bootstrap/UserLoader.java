package com.odhiambopaul.demo.bootstrap;

import com.odhiambopaul.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserLoader implements CommandLineRunner {
    public final UserRepository userRepository;

    public UserLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUsers();
    }

    private void loadUsers() {
        if (userRepository.count() == 0) {
            /*
            todoRepository.save(
                    Todo.builder()
                            .title("Go to market")
                            .description("Buy eggs from market")
                            .todoStatus(TodoStatus.NOT_COMPLETED)
                            .build()
            );
            todoRepository.save(
                    Todo.builder()
                            .title("Go to school")
                            .description("Complete assignments")
                            .todoStatus(TodoStatus.NOT_COMPLETED)
                            .build()
            );
            */
            System.out.println("Sample Users Loaded");
        }
    }
}
