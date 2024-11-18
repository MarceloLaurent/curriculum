package com.fatec.curriculum.repository;

import com.fatec.curriculum.model.Curso;
import com.fatec.curriculum.model.Experiencia;
import com.fatec.curriculum.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class PessoaDetalhesConcluidosDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Pessoa getPessoaDetalhesConcluidosPorAno(Long pessoaId, int ano) {
        Pessoa pessoaDetalhes = new Pessoa();
        pessoaDetalhes.setId(pessoaId);
        pessoaDetalhes.setCursos(new ArrayList<>());
        pessoaDetalhes.setExperiencias(new ArrayList<>());

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             var callableStatement = connection.prepareCall("{CALL GetPessoaDetalhesConcluidosAno(?, ?)}")) {

            callableStatement.setLong(1, pessoaId);
            callableStatement.setInt(2, ano);
            boolean hasResults = callableStatement.execute();

            // Processa o primeiro conjunto de resultados (Cursos)
            if (hasResults) {
                try (ResultSet cursosResultSet = callableStatement.getResultSet()) {
                    while (cursosResultSet.next()) {
                        Curso curso = new Curso();
                        curso.setId(cursosResultSet.getLong("curso_id"));
                        curso.setDescricao(cursosResultSet.getString("curso_descricao"));
                        curso.setInstituicao(cursosResultSet.getString("curso_instituicao"));
                        curso.setDataInicio(cursosResultSet.getDate("curso_dataInicio").toLocalDate());
                        curso.setDataConclusao(cursosResultSet.getDate("curso_dataConclusao").toLocalDate());
                        pessoaDetalhes.getCursos().add(curso);
                    }
                }
            }

            // Move para o próximo conjunto de resultados (Experiências)
            if (callableStatement.getMoreResults()) {
                try (ResultSet experienciasResultSet = callableStatement.getResultSet()) {
                    while (experienciasResultSet.next()) {
                        Experiencia experiencia = new Experiencia();
                        experiencia.setId(experienciasResultSet.getLong("experiencia_id"));
                        experiencia.setCargo(experienciasResultSet.getString("experiencia_cargo"));
                        experiencia.setEmpresa(experienciasResultSet.getString("experiencia_empresa"));
                        experiencia.setDataInicio(experienciasResultSet.getDate("experiencia_dataInicio").toLocalDate());
                        experiencia.setDataTermino(experienciasResultSet.getDate("experiencia_dataTermino").toLocalDate());
                        pessoaDetalhes.getExperiencias().add(experiencia);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao executar a stored procedure: " + e.getMessage(), e);
        }

        return pessoaDetalhes;
    }
}
