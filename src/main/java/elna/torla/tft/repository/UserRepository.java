package elna.torla.tft.repository;

import elna.torla.tft.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByNickname(String nickname);
    List<User> findByNicknameContaining(String nickname);
}
