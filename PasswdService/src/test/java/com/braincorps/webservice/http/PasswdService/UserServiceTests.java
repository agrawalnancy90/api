package com.braincorps.webservice.http.PasswdService;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.braincorps.passwdservice.models.User;
import com.braincorps.passwdservice.repository.UserFileRepository;
import com.braincorps.passwdservice.services.UserService;

@RunWith(SpringRunner.class)
public class UserServiceTests {
 
    @TestConfiguration
    static class UserServiceIntegrationTestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserService();
        }
    }
 
    @Autowired
    private UserService userService;
 
    @MockBean
    private UserFileRepository userFileRepository;
 
    @Before
    public void setUp() {
        // Arrange
        User user1 = new User(10, 100, "name1", "comment1", "home1", "/user");
        User user2 = new User(20, 200, "name2", "comment2", "home2", "/user/var");
        User user3 = new User(30, 300, "name3", "comment3", "home3", "/user");
        User user4 = new User(40, 400, "name4", "comment4", "home4", "/user/var");
        User user5 = new User(50, 500, "name5", "comment5", "home5", "/user/var/test");
        
        User[] allUsers = { user1, user2, user3, user4, user5 };
     
        Mockito.when(userFileRepository.getUser(10))
          .thenReturn(user1);
        
        Mockito.when(userFileRepository.getAllUsers())
        .thenReturn(Arrays.asList(allUsers));
    }
    
    @Test
    public void whenValidId_thenUserShouldBeFound() {
        // Arrange.
        int id = 10;
        String expectedName = "name1";
        
        // Act.
        User found = userService.getUserById((long) id);
      
        // Assert.
        assertTrue(found.getName().equals(expectedName));
     }
    
    @Test
    public void whenInvalidId_thenUserShouldBeNull() {
        // Arrange.
        int id = 20;
        
        // Act.
        User found = userService.getUserById((long) id);
      
        // Assert.
        assertTrue(found == null);
     }
    
    @Test
    public void whenAllUsersQueried_thenCountShouldBeFive() {
        // Arrange.
        int count = 5;
        
        // Act.
        List<User> found = userService.getAllUsers();
      
        // Assert.
        assertTrue(found.size() == count);
     }
    
}