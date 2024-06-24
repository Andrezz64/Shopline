package Modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Andre.infra
 */
public class Time {

    public String GetData() {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
         DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.forLanguageTag("pt-BR"));
         String dataHoraFormatada = dataHoraAtual.format(formatoBrasileiro);
         return dataHoraFormatada;
    }

}
