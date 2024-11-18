package com.example.AulaJwt.service;

import com.example.AulaJwt.repository.TarefaRepository;
import com.example.AulaJwt.model.Prioridade;
import com.example.AulaJwt.model.Status;
import com.example.AulaJwt.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTarefa() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return tarefas.stream()
                .sorted((t1, t2) -> t1.getPrioridade().compareTo(t2.getPrioridade()))
                .collect(Collectors.toList());
    }
        public List<Tarefa> listarTarefasPorStatusOrdenadas(Status status) {
            return tarefaRepository.findByStatusOrdered(status);
    }

    public Tarefa inserirTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa findById(int id) {
        return tarefaRepository.findById(id).orElse(null);
    }

    public Tarefa save(Tarefa product) {
        return tarefaRepository.save(product);
    }

    public void deletarTarefa(int id) {
        tarefaRepository.deleteById(id);
    }

    public Tarefa buscarTarefaPorId(int id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa com ID " + id + " não encontrada."));
    }

    public Tarefa atualizarTarefa(int id, Tarefa tarefaAtualizada) {
        Tarefa tarefa = buscarTarefaPorId(id);  // Busca a tarefa pelo ID
        if (tarefa == null) {
            throw new NoSuchElementException("Tarefa não encontrada com o ID: " + id);
        }
        tarefa.setTitulo(tarefaAtualizada.getTitulo());
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        tarefa.setDataLimite(tarefaAtualizada.getDataLimite());
        return tarefaRepository.save(tarefa);
    }

    // Filtrar tarefas por prioridade e data limite
    public List<Tarefa> filtrarTarefas(Prioridade prioridade, LocalDate dataLimite) {
        return tarefaRepository.findAll().stream()
                .filter(t -> (prioridade == null || t.getPrioridade() == prioridade) &&
                        (dataLimite == null || t.getDataLimite() != null && t.getDataLimite().isEqual(dataLimite)))
                .collect(Collectors.toList());
    }

    // Relatório de tarefas atrasadas
    public List<Tarefa> tarefasAtrasadas() {
        return tarefaRepository.findAll().stream()
                .filter(t -> t.getStatus() != Status.Concluida &&
                        t.getDataLimite() != null &&
                        t.getDataLimite().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }


    public Tarefa moverTarefa(int id) {
        Tarefa tarefa = buscarTarefaPorId(id);
        if (tarefa == null) {
            throw new NoSuchElementException("Tarefa não encontrada , ID: " + id);
        }

        switch (tarefa.getStatus()) {
            case aFazer:
                tarefa.setStatus(Status.EmProgresso);
                break;
            case EmProgresso:
                tarefa.setStatus(Status.Concluida);
                break;
            case Concluida:
                throw new IllegalStateException("Tarefa já está Feita.");
        }

        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarPrioridade(int id, Prioridade prioridade) {
        Tarefa tarefa = buscarTarefaPorId(id);
        if (tarefa == null) {
            throw new NoSuchElementException("Tarefa não encontrada com o ID: " + id);
        }
        tarefa.setPrioridade(prioridade);
        return tarefaRepository.save(tarefa);
    }

}
