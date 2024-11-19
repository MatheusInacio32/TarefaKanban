package com.example.AulaJwt.controller;


import com.example.AulaJwt.model.Prioridade;
import com.example.AulaJwt.model.Status;
import com.example.AulaJwt.model.Tarefa;
import com.example.AulaJwt.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;


    // Lista as tarefas por status
    @GetMapping("/status/{status}")
    public List<Tarefa> listarTarefasPorStatus(@PathVariable Status status, HttpServletRequest request) {
        // Recupera o userId do token JWT
        String userId = (String) request.getAttribute("userId");
        System.out.println("UserId: " + userId); // Apenas para verificar se o id foi extraído corretamente
        return taskService.listarTarefasPorStatusOrdenadas(status);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas(HttpServletRequest request) {
        try {
            return ResponseEntity.ok(taskService.listarTarefa());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> incluirTarefa(@Valid @RequestBody Tarefa tarefa, HttpServletRequest request) {
        try {
            tarefa.setDataCriacao(LocalDate.now());
            Tarefa novaTarefa = taskService.inserirTarefa(tarefa);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable int id) {
        try {
            taskService.deletarTarefa(id);
            return ResponseEntity.ok().body("Tarefa deletada com sucesso.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarTarefa(@PathVariable int id, @Valid @RequestBody Tarefa tarefaAtualizada) {
        try {
            Tarefa tarefa = taskService.atualizarTarefa(id, tarefaAtualizada);
            return ResponseEntity.ok(tarefa);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
        }
    }
}


