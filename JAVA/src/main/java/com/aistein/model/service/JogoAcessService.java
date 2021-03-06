/*
 * Projeto de Integracao - A.I.Stein
 * CEFET-MG 
 * INF-3A 2019
 * Arthut Marcolino, Gabriel Cruz, Heitor Santos, Italo Nascimento
 */

package com.aistein.model.service;

import com.aistein.model.table.Jogo;
import com.aistein.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe Conexão com Jogos e o BD.
 * Tem como responsabilidade única servir como conexão entre a classe modelo 
 * Jogos com o Banco de Dados.
 *
 * @author Arthur
 * @version 1.0
 */

public class JogoAcessService {

    //Objetos de manipulação interna.
    private static ResultSet result;
    
    //Constantes que representam os nomes das colunas no DB SQL.
    private static final String CODJOGO;
    private static final String NOMEJOGO;
    private static final String URLJOGO;
    private static final String CREDJOGO;
    private static final String IMGJOGO;
    private static final String MATJOGO;


    //Inicialização das constantes
    static{
        CODJOGO = "codigo_jogo";
        NOMEJOGO = "nome_jogo";
        URLJOGO = "url_jogo";
        CREDJOGO = "creditos_jogo";
        IMGJOGO = "codigo_imagem";
        MATJOGO = "codigo_materia";
        
    }
    
    
    public static ArrayList<Jogo> getAllJogo(){
  
        ArrayList<Jogo> jogos = get("");
        if (jogos == null) {
            System.out.println("NENHUM JOGO FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }
        
        return jogos;
        
    }
    
    public static ArrayList<Jogo> getJogoByMateria(int materia){
        System.out.println("MATERIA" + materia);
  
        ArrayList<Jogo> jogos = get("WHERE " + MATJOGO + "=" + materia);
        if (jogos == null) {
            System.out.println("NENHUM JOGO FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }
        
        return jogos;
        
    }
    
    
    
    public static ArrayList<Jogo> get(String condicao) {
        ArrayList<Jogo> jogos = new ArrayList<>();

        try {
            
            result = SQL.query("SELECT * FROM jogo " + condicao);

            if (result.next()) {
                do {
                    Jogo jogo = new Jogo();
                    jogo.setCodJogo(Integer.parseInt(
                            result.getString(CODJOGO)));
                    jogo.setNomeJogo(
                            result.getString(NOMEJOGO));
                    jogo.setUrlJogo(
                            result.getString(URLJOGO));
                    jogo.setCreditosJogo(
                            result.getString(CREDJOGO));
                    jogo.setCodImgJogo(Integer.parseInt(
                            result.getString(IMGJOGO)));
                    jogo.setCodMatJogo(Integer.parseInt(
                            result.getString(MATJOGO)));
                    jogos.add(jogo);

                } while (result.next());
            } else {
                System.out.println("NENHUM JOGO COM CONDIÇÃO: "
                        + condicao + " FOI ENCONTRADO NO BANCO DE DADOS");
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        }

        return jogos;
    }
    
}
