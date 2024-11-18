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
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    // Listar tarefas separadas por status
    @GetMapping("/tasks/todo")
    public ResponseEntity<List<Tarefa>> listarTarefasAFazer() {
        return ResponseEntity.ok(taskService.listarTarefasPorStatusOrdenadas(Status.aFazer));
    }

    @GetMapping("/tasks/in-progress")
    public ResponseEntity<List<Tarefa>> listarTarefasEmProgresso() {
        return ResponseEntity.ok(taskService.listarTarefasPorStatusOrdenadas(Status.EmProgresso));
    }

    @GetMapping("/tasks/done")
    public ResponseEntity<List<Tarefa>> listarTarefasConcluidas() {
        return ResponseEntity.ok(taskService.listarTarefasPorStatusOrdenadas(Status.Concluida));
    }

    // Filtrar tarefas por prioridade e/ou data limite
    @GetMapping("/tasks/filter")
    public ResponseEntity<List<Tarefa>> filtrarTarefas(
            @RequestParam(required = false) Prioridade prioridade,
            @RequestParam(required = false) LocalDate dataLimite) {
        return ResponseEntity.ok(taskService.filtrarTarefas(prioridade, dataLimite));
    }

    // Relatório de tarefas atrasadas
    @GetMapping("/tasks/late")
    public ResponseEntity<List<Tarefa>> tarefasAtrasadas() {
        return ResponseEntity.ok(taskService.tarefasAtrasadas());
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


