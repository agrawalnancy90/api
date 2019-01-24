package com.braincorps.webservice.http.PasswdService;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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

import com.braincorps.passwdservice.models.Group;
import com.braincorps.passwdservice.repository.GroupFileRepository;
import com.braincorps.passwdservice.services.GroupService;

@RunWith(SpringRunner.class)
public class GroupServiceTests {

	@TestConfiguration
    static class GroupServiceIntegrationTestContextConfiguration {
  
        @Bean
        public GroupService groupService() {
            return new GroupService();
        }
    }
	
	@Autowired
    private GroupService groupService;
	
	@MockBean
    private GroupFileRepository groupFileRepository;
	
	@Before
    public void setUp() {
        // Arrange		
        Group group1 = new Group("groupname1", 200, new ArrayList<>(Arrays.asList("member1", "member2")));
        Group group2 = new Group( "groupname2", 400, new ArrayList<>(Arrays.asList("member3", "member1")));
        Group group3 = new Group("groupname3", 300 , new ArrayList<>(Arrays.asList("member3")));
        
        Group[] allGroups = { group1, group2, group3 };
     
        Mockito.when(groupFileRepository.getGroup(200))
          .thenReturn(group1);
        
        Mockito.when(groupFileRepository.getAllGroups())
        .thenReturn(Arrays.asList(allGroups));
    }
	
	@Test
    public void whenValidGid_thenGroupShouldBeFound() {
        // Arrange.
        long gid = 200;
        String expectedName = "groupname1";
        
        // Act.
        Group found = groupService.getGroupById(gid);
      
        // Assert.
        assertTrue(found.getName().equals(expectedName));
     }
    
    @Test
    public void whenInvalidGid_thenGroupShouldBeNull() {
        // Arrange.
        long gid = 20;
        
        // Act.
        Group found = groupService.getGroupById(gid);
      
        // Assert.
        assertTrue(found == null);
     }
    
    @Test
    public void whenAllGroupsQueried_thenCountShouldBeThree() {
        // Arrange.
        int count = 3;
        
        // Act.
        List<Group> found = groupService.getAllGroups();
      
        // Assert.
        assertTrue(found.size() == count);
     }
    
}
