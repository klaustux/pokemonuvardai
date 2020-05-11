package lt.pogo.vardai;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CsvTest {

    private Set<Set<String>> raides = new HashSet<>();

    @BeforeEach
    public void before() throws IOException {
        final File dic = FileUtils.getFile("src","test", "resources", "vardai.csv");
        final String utf8 = "UTF-8";


        List<String> eilutes = FileUtils.readLines(dic,  utf8);
        for (String eilute : eilutes) {
            String[] demenys = StringUtils.split(eilute, ",");
            String vardas = demenys[1].toLowerCase();
            Set<String> vienoZodzioRaides = zodisISeta(vardas);
            raides.add(vienoZodzioRaides);
        }
    }


    @Test
    public void skaitomCsv() throws IOException {
        before();
        spausdinti("gyti");
        spausdinti("giedre");
        spausdinti("aistis");
        spausdinti("eimis");
        spausdinti("sandra");

    }

    public void spausdinti(String vardas){
        System.out.println("Vardas - " + vardas);
        if (aibeTuriVisasRaides(raides, vardas)){
            System.out.println("Radom, pokemono  raidės:");

        }
    }


    private Set<String> zodisISeta(String vardas) {
        String[] zodzioRaides = vardas.split("");
        return new HashSet<>(Arrays.asList(zodzioRaides));
    }

    private boolean zodisTuriVisasRaides(Set<String> raides, String zodis){
        String[] zodzioRaides = zodis.split("");
        for (String zodzioRaide : zodzioRaides) {
            if(!raides.contains(zodzioRaide)){
                return false;
            }
        }
        return true;
    }

    private boolean aibeTuriVisasRaides(Set<Set<String>> aibe, String zodis) {
        for (Set<String> strings : aibe) {
            if (zodisTuriVisasRaides(strings, zodis)) {
                System.out.println(rusiuota(strings));
                return true;
            }
        }
        return false;
    }

    private String rusiuota(Set<String> s) {
        Set<String> rusiuota = new TreeSet<>(s);
        return rusiuota.toString();
    }

    @Test
    public void turiRaidesTest(){
        Set<String> krokodilas = zodisISeta("krokodilas");
        assertTrue(zodisTuriVisasRaides(krokodilas, "kroko"));
        assertFalse(zodisTuriVisasRaides(krokodilas, "krokoz"));
    }

    @Test
    public void turiZodzius(){
        Set<String> krokodilas = zodisISeta("krokodilas");
        Set<String> begemotas = zodisISeta("begemotas");
        Set<Set<String>> zoo = new HashSet<>();
        zoo.add(krokodilas);
        zoo.add(begemotas);
        assertTrue(aibeTuriVisasRaides(zoo, "kroko"));
        assertFalse(aibeTuriVisasRaides(zoo, "krokoz"));
        assertTrue(aibeTuriVisasRaides(zoo, "beg"));
    }


}
