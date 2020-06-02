package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	
	
	private Task todo = null;
	
	@InjectMocks
	private TaskController controller = null;
	
	@Mock
	private TaskRepo todoRepo;

	@Before
	public void setup() {
		todo = new Task();
		controller = new TaskController();
		MockitoAnnotations.initMocks(this);
	}

	@Test

	public void naoDeveSalvarTarefaSemDescricao() {
		todo.setDueDate(LocalDate.now());

		try {
			controller.save(todo);
		} catch (ValidationException e) {
			assertEquals("Fill the task description", e.getMessage());
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData() {
		todo.setTask("string");
		todo.setDueDate(null);

		try {
			controller.save(todo);
		} catch (ValidationException e) {
			assertEquals("Fill the due date", e.getMessage());
		}

	}

	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		LocalDate date = LocalDate.of(2000, 01, 01);
		todo.setTask("test");
		todo.setDueDate(date);
		try {
			controller.save(todo);
		} catch (ValidationException e) {
			assertEquals("!Due date must not be in past", e.getMessage());
		}
	}

	@Test
	public void deveSalvarTarefComSucesso() throws ValidationException {
		LocalDate date = LocalDate.now();
		todo.setDueDate(date);
		todo.setTask("test");
		controller.save(todo);
		
		Mockito.verify(todoRepo).save(todo);
	}

}
