package api.mapper;

import com.example.demo.DemoApplication;
import com.example.demo.api.entity.User;
import com.example.demo.api.entity.UserLoveMusicDTO;
import com.example.demo.api.mapper.UserMapper;
import com.example.demo.api.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
class MusicMapperTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;  // 注入 UserService

    @Test
    void getMusicByUser() {
        // 创建 User 对象并设置用户名
        User user = new User();
        user.setUserName("kxq");

        // 调用 UserService 层的方法来获取用户的喜欢的音乐
        List<UserLoveMusicDTO> musicList = userService.getMusicByUser(user);
        // 打印用户喜欢的音乐列表
        System.out.println("用户 'kxq' 喜欢的音乐: " + musicList);
    }
}
