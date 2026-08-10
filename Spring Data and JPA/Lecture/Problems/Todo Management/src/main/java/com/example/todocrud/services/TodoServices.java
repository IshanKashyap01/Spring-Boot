package com.example.todocrud.services;

import com.example.todocrud.entity.Todo;
import com.example.todocrud.entity.Users;
import com.example.todocrud.repository.ToDoRepository;
import com.example.todocrud.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServices {

    @Autowired
    UserServices userServices;
    
    @Autowired
    ToDoRepository toDoRepository;
    
    @Autowired
    UserRepository userRepository;

    public Todo getTodoById(Long todoId)
    {
        return toDoRepository.findById(todoId).get();
    }

    @Transactional
    public void addTodo(Long userId, Todo todo)
    {
        Users user = userServices.getUserById(userId);
        user.getTodoList().add(todo);
    }

    public void toggleTodoCompleted(Long todoId){
        Todo todo = this.getTodoById(todoId);
        todo.setCompleted(!todo.getCompleted());
        toDoRepository.save(todo);
    }

    public void updateTodo(Todo todo)
    {
        toDoRepository.save(todo);
    }

    @Transactional
    public void deleteTodo(Long userId,Long todoId)
    {
        Users user = userServices.getUserById(userId);
        Todo toBeDeleted = null;
        for(Todo todo : user.getTodoList())
        {
            if(todo.getId() == todoId)
            {
                toBeDeleted = todo;
                toDoRepository.deleteById(todoId);
            }
        }
        user.getTodoList().remove(toBeDeleted);
    }
}
