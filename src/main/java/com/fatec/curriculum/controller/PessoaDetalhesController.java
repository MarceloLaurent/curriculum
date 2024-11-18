        package com.fatec.curriculum.controller;

        import com.fatec.curriculum.model.Pessoa;
        import com.fatec.curriculum.repository.PessoaDetalhesConcluidosDAO;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        @RestController
        @RequestMapping("pessoas")
        public class PessoaDetalhesController {

            @Autowired
            private PessoaDetalhesConcluidosDAO pessoaDetalhesConcluidosDAO;

            @GetMapping("/{pessoaId}/detalhes")
            public Pessoa getPessoaDetalhesConcluidosPorAno(
                    @PathVariable Long pessoaId,
                    @RequestParam(required = false, defaultValue = "2024") int ano) {
                return pessoaDetalhesConcluidosDAO.getPessoaDetalhesConcluidosPorAno(pessoaId, ano);
            }
        }
