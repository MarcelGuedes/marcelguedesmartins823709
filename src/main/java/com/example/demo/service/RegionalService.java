package com.example.demo.service;

import com.example.demo.entity.Regional;
import com.example.demo.repository.RegionalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RegionalService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "https://integrador-argus-api.geia.vip/v1/regionais";

    private final RegionalRepository regionalRepository;

    public RegionalService(RegionalRepository regionalRepository) {
        this.regionalRepository = regionalRepository;
    }

    // Importa regionais do endpoint externo e salva no banco (sem apagar os antigos)
    public List<Regional> importarRegionais() {
        Regional[] response = restTemplate.getForObject(url, Regional[].class);
        List<Regional> regionais = Arrays.asList(response);

        // Garante que todas tenham ativo = true se vier nulo
        regionais.forEach(r -> {
            if (r.getAtivo() == null) {
                r.setAtivo(true);
            }
        });

        regionalRepository.saveAll(regionais);
        return regionais;
    }

    // Lista regionais já salvas no banco interno
    public List<Regional> listarRegionaisInternas() {
        return regionalRepository.findAll();
    }

    // Sincroniza: limpa a tabela e salva tudo de novo a partir do endpoint externo
    public List<Regional> sincronizarRegionais() {
        Regional[] response = restTemplate.getForObject(url, Regional[].class);
        List<Regional> regionais = Arrays.asList(response);

        // Remove todos os registros internos
        regionalRepository.deleteAll();

        // Garante que todas tenham ativo = true se vier nulo
        regionais.forEach(r -> {
            if (r.getAtivo() == null) {
                r.setAtivo(true);
            }
        });

        regionalRepository.saveAll(regionais);
        return regionais;
    }

    // Insere manualmente uma nova regional (sempre ativa por padrão)
    public Regional inserirRegional(Regional regional) {
        regional.setAtivo(true);
        return regionalRepository.save(regional);
    }

    // Inativa uma regional existente
    public Regional inativarRegional(Long id) {
        Regional regional = regionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Regional não encontrada com id: " + id));

        regional.setAtivo(false);
        return regionalRepository.save(regional);
    }

    // Altera uma regional: inativa a antiga e cria uma nova ativa
    public Regional alterarRegional(Long id, Regional novaRegional) {
        Regional regionalAntiga = regionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Regional não encontrada com id: " + id));

        // Inativa a antiga
        regionalAntiga.setAtivo(false);
        regionalRepository.save(regionalAntiga);

        // Cria nova com os dados atualizados
        Regional regionalNova = new Regional();
        regionalNova.setNome(novaRegional.getNome());
        regionalNova.setAtivo(true);

        return regionalRepository.save(regionalNova);
    }
}
