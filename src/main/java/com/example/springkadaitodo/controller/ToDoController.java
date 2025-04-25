package com.example.springkadaitodo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.service.ToDoService;


@Controller
public class ToDoController {
	private final ToDoService toDoService;
	
	//サービスの依存姓をコントローラへ注入
	public ToDoController(ToDoService toDoService) {
		this.toDoService = toDoService;
	}

	@GetMapping("/todo")
	public String toDo(Model model) {
		
		//サービスから、全てのTodoを取得
		List<ToDo> toDos = toDoService.getAllToDos();
		
		//取得したTodoをViewへ渡すために、モデルへ設定する
		 model.addAttribute("todos",toDos);
		
		return "todoView";
	}
}
