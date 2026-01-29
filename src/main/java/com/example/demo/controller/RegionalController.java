package com.example.demo.controller;

import com.example.demo.entity.Regional;
import com.example.demo.service.RegionalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/regionais")
public class RegionalController {

    private final RegionalService regionalService;

    public RegionalController(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    // Lista regionais já salvas no banco interno
    @GetMapping
    public List<Regional> listarRegionaisInternas() {
        return regionalService.listarRegionaisInternas();
    }

    // Importa regionais do endpoint externo (sem apagar os antigos)
    @PostMapping("/importar")
    public List<Regional> importarRegionais() {
        return regionalService.importarRegionais();
    }

    // Sincroniza regionais com o endpoint externo (apaga e recria todos)
    @PostMapping("/sincronizar")
    public List<Regional> sincronizarRegionais() {
        return regionalService.sincronizarRegionais();
    }

    // Insere manualmente uma nova regional
    @PostMapping
    public Regional inserirRegional(@RequestBody Regional regional) {
        return regionalService.inserirRegional(regional);
    }

    // Inativa uma regional existente
    @PutMapping("/{id}/inativar")
    public Regional inativarRegional(@PathVariable Long id) {
        return regionalService.inativarRegional(id);
    }

    // Altera uma regional (inativa a antiga e cria nova)
    @PutMapping("/{id}")
    public Regional alterarRegional(@PathVariable Long id, @RequestBody Regional novaRegional) {
        return regionalService.alterarRegional(id, novaRegional);
    }
}
