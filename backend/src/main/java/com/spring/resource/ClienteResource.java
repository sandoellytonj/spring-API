package com.spring.resource;

import com.spring.models.Cliente;
import com.spring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
public class ClienteResource {

    @Autowired
    private ClienteRepository clienteRepository;


    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cliente cliente) {
        Cliente resposta = clienteRepository.save(cliente);
        resposta.setIdade(cliente.calcIdade(cliente.getDataNascimento()));
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> listar(@ModelAttribute ClienteRequest clienteRequest, Pageable pageable) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.getNome());
        cliente.setCpf(clienteRequest.getCpf());

        Page<Cliente> clientes = clienteRepository.findAll(Example.of(cliente),pageable);

        for(Cliente c : clientes) {
            c.setIdade(c.calcIdade(c.getDataNascimento()));
        }

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity alterarPatch(@PathVariable String id, @RequestBody Cliente newCliente) {
        Optional<Cliente> oldCliente = clienteRepository.findById(id);

        if(oldCliente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cliente cliente = oldCliente.get();
        if(newCliente.getNome() != null) {
            cliente.setNome(newCliente.getNome());
        }
        if(newCliente.getCpf() != null) {
            cliente.setCpf(newCliente.getCpf());
        }
        if(newCliente.getDataNascimento() != null) {
            cliente.setDataNascimento(newCliente.getDataNascimento());
        }
        clienteRepository.save(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<?> alterar(@RequestBody Cliente cliente) {
        Cliente resp = clienteRepository.save(cliente);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestBody Cliente cliente) {
        clienteRepository.delete(cliente);
        return new ResponseEntity(HttpStatus.OK);
    }

}
