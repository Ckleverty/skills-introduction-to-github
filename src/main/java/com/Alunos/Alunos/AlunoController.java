package com.Alunos.Alunos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/Alunos")




public class AlunoController {
    /*
    * 1- vamos mapear
    * 2-arraylist
    * 3-metodo listar
    * 4-metodo adicionar
    * 5-metodo apagar
    * 6metodo atualizar
    * */
private Map<String,Aluno> baseDados= new HashMap<>();

@PostMapping
private ResponseEntity<String>adicionar(@RequestBody Aluno aluno){
    baseDados.put(aluno.getCodigo(), aluno);
    return ResponseEntity.ok("Aluno adicionado com sucesso");
}
// agrora vamos listar os alunos

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos(){
        return ResponseEntity.ok(new ArrayList<>(baseDados.values()));
    }

    // metodo que busca pelo codigo
    @GetMapping("/{codigo}")
    public ResponseEntity<Aluno> buscarPorCodigo(@PathVariable String codigo){
        Aluno aluno=baseDados.get(codigo);
        if (aluno == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<String> atualizar(@PathVariable String codigo, @RequestBody Aluno novo) {
        baseDados.put(codigo, novo);
        return ResponseEntity.ok("Mais uma boca na familia!");
    }

    //metodo para apagar
    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> apagar(@PathVariable String codigo) {
        baseDados.remove(codigo);
        return ResponseEntity.ok("R.I.P");
    }

    //metodo para mapear, listar apenas estudantes de acordo com
//a sua morada.
    @GetMapping("/morada/{morada}")
    public ResponseEntity<List<Aluno>> filtrarPorMorada(@PathVariable String morada) {
        List<Aluno> filtrados = baseDados.values().stream()
                .filter(s -> s.getMorada().equalsIgnoreCase(morada))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filtrados);
    }
    //listar apenas de acordo com a operadora.
    @GetMapping("/operadora/{operadora}")
    public ResponseEntity<List<Aluno>> filtrarPorOperadora(@PathVariable String operadora) {
        List<Aluno> filtrados = baseDados.values().stream()
                .filter(s -> s.getOperadora().equalsIgnoreCase(operadora))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filtrados);
    }

}
