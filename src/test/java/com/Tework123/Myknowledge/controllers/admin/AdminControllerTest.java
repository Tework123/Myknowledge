package com.Tework123.Myknowledge.controllers.admin;


import com.Tework123.Myknowledge.entities.User;
import com.Tework123.Myknowledge.exceptions.customException.CustomException;
import com.Tework123.Myknowledge.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/applicationTest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Value("${jwtTokenUser1}")
    private String jwtTokenUser1;

    @Value("${jwtTokenUser2}")
    private String jwtTokenUser2;

    @Value("${jwtTokenAdmin1}")
    private String jwtTokenAdmin1;

    @Test
    @Order(1)
    public void getUsers401Test() throws Exception {
        mockMvc.perform(get("/admin/users"))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(2)
    public void getUsers403RoleAdminTest() throws Exception {

        mockMvc.perform(get("/admin/users")
                        .header("Authorization", jwtTokenUser1))
                .andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @Order(3)
    public void getUsers200Test() throws Exception {
        mockMvc.perform(get("/admin/users")
                        .header("Authorization", jwtTokenAdmin1))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void banUser401Test() throws Exception {
        mockMvc.perform(patch("/admin/users/" + getUser().getId() + "/ban"))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @Order(5)
    public void banUser403RoleAdminTest() throws Exception {
        mockMvc.perform(patch("/admin/users/" + getUser().getId() + "/ban")
                        .header("Authorization", jwtTokenUser1))
                .andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @Order(6)
    public void banUser200Test() throws Exception {
        mockMvc.perform(patch("/admin/users/" + getUser().getId() + "/ban")
                        .header("Authorization", jwtTokenAdmin1))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("user1 active is false")));
        ;
    }

    @Test
    @Order(7)
    public void banUserCheckTest() {
        Assertions.assertFalse(getUser().isEnabled());
    }

    @Test
    @Order(8)
    public void getBookCheckBan403Test() throws Exception {
        try {
            mockMvc.perform(get("/book")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", jwtTokenUser1))
                    .andExpect(status().isForbidden())
                    .andExpect(result -> assertTrue
                            (result.getResolvedException() instanceof Exception));

        } catch (CustomException _) {
        }
    }

    @Test
    @Order(9)
    public void banUserCheckUnban200Test() throws Exception {
        mockMvc.perform(patch("/admin/users/" + getUser().getId() + "/ban")
                .header("Authorization", jwtTokenAdmin1));

        assertTrue(getUser().isEnabled());
    }

    @Test
    @Order(10)
    public void getBookCheckUnBan200Test() throws Exception {
        mockMvc.perform(get("/book")
                        .header("Authorization", jwtTokenUser1))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    @Order(11)
    public void deleteApplicationTestProperties() throws Exception {
        File file = new File("src/test/resources/applicationTest.properties");
        File temp = File.createTempFile("copy", ".properties", file.getParentFile());
        String charset = "UTF-8";
        String delete = "jwtTokenUser1";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));


        Pattern pattern = Pattern.compile("jwt");

        for (String line; (line = reader.readLine()) != null; ) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                line.replace(delete, "");
            } else {
                writer.println(line);
            }
        }
        reader.close();
        writer.close();
        file.delete();
        temp.renameTo(file);


    }


//    @Test
//    @Order(1)
//    public void changeRoleUser401Test() throws Exception {
//        mockMvc.perform(post("/admin/users/{id}/change_role")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(BookDtoTest.bookCreateDto200()))
//                .andDo(print()).andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    @Order(1)
//    public void changeRoleUser403RoleAdminTest() throws Exception {
//        mockMvc.perform(post("/admin/{id}/change_role")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(BookDtoTest.bookCreateDto200()))
//                .andDo(print()).andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    @Order(1)
//    public void changeRoleUser200Test() throws Exception {
//        mockMvc.perform(post("/admin/{id}/change_role")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(BookDtoTest.bookCreateDto200()))
//                .andDo(print()).andExpect(status().isUnauthorized());
//    }


    public User getUser() {
        return userRepository.findByUsername("user1");
    }
}
