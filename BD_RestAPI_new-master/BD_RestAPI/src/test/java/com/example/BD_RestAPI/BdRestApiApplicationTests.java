package com.example.BD_RestAPI;

import com.example.BD_RestAPI.controller.UserController;
import com.example.BD_RestAPI.model.UserEntity;
import com.example.BD_RestAPI.service.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {com.example.BD_RestAPI.application.RestApiAplication.class})
class BdRestApiApplicationTests {

	@Mock
	private UserService userService;
	@InjectMocks
	private UserController userController;

	@Test
	void testObterTodos() {
		List<UserEntity> userList = Arrays.asList(
				new UserEntity("1","User1","user1@example.com"),
				new UserEntity("2","User2","user2@example.com")
		);

		when(userService.obterTodos()).thenReturn(userList);

		List<UserEntity> result = userController.obterTodos();

	}

	@Test
	void testObterPorId() {
		UserEntity user = new UserEntity("1","User1","user1@example.com");

		when(userService.obterPorId("1")).thenReturn(user);

		UserEntity result = userController.obterPorId("1");

		assertEquals(user,result);

	}

	@Test
	void testInserir() {
		UserEntity newUser = new UserEntity("1","User1","user1@example.com");

		when(userService.inserir(newUser)).thenReturn(newUser);

		UserEntity result = userController.inserir(newUser);

		assertEquals(newUser,result);

	}

	@Test
	void testAtualizar() {
		UserEntity updateUser = new UserEntity("1","User1","user1@example.com");

		when(userService.atualizar("1",updateUser)).thenReturn(updateUser);

		UserEntity result = userController.atualizar("1",updateUser);

		assertEquals(updateUser,result);

	}

	@Test
	void testExcluir() {

		doNothing().when(userService).excluir("1");

		userController.excluir("1");

		verify(userService,times(1)).excluir("1");

	}

}
