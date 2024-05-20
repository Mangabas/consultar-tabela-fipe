package com.example.consultandotabelafipe.main;

import com.example.consultandotabelafipe.model.Marcas;
import com.example.consultandotabelafipe.model.Modelos;
import com.example.consultandotabelafipe.model.Veiculo;
import com.example.consultandotabelafipe.services.ConsumoApi;
import com.example.consultandotabelafipe.services.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    Scanner leitura = new Scanner(System.in);
    ConsumoApi endereco = new ConsumoApi();
    ConverteDados conversor = new ConverteDados();
    String json;

    public void menu() throws JsonProcessingException {
        String header = """
                ***************************************************
                Digite o tipo de veículo que deseja consultar
                
                Carros
                Motos
                Caminhões
                
                (Sem acento)
                """;
        System.out.println(header);
        String modelo = leitura.nextLine().toLowerCase();
        modelo = modelo + "/marcas";
        json = endereco.requestApi(modelo);

        List<Marcas> marcas = conversor.lerJsonLista(json, Marcas.class);

        marcas.stream()
                .sorted(Comparator.comparing(Marcas::codigo))
                .forEach(System.out::println);
        System.out.println("Digite o código da marca para consulta");
        String codigoMarca = leitura.nextLine();
        codigoMarca = modelo + "/" + codigoMarca + "/modelos";
        json = endereco.requestApi(codigoMarca);
        Modelos modelos = conversor.lerJson(json, Modelos.class);
        modelos.modelos().stream()
                .sorted(Comparator.comparing(Marcas::codigo))
                .forEach(System.out::println);
        System.out.println("Digite um trecho do carro que está procurando");
        String trecho = leitura.nextLine();
        List<Marcas> modelosFiltrados = modelos.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(trecho.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\nModelos encontrados:");
        modelosFiltrados.forEach(System.out::println);
        System.out.println("Digite o codigo do carro que está procurando");
        String codigoCarro = leitura.nextLine();
        codigoCarro =  codigoMarca + "/" + codigoCarro + "/anos";
        json = endereco.requestApi(codigoCarro);
        List<Marcas> anos = conversor.lerJsonLista(json, Marcas.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            String enderecoAnos = codigoCarro + "/" + anos.get(i).codigo();
            json = endereco.requestApi(enderecoAnos);
            Veiculo veiculo = conversor.lerJson(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        System.out.println("Todos os veículos filtrados por ano");
        veiculos.forEach(System.out::println);
    }
}
