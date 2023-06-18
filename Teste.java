//classe para testar elementos do site da University of Tampa usando Selenium e JUnit
//https://www.ut.edu/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;

public class Teste {
    private static WebDriver driver = null;

    public Teste() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.ut.edu/");
        driver.manage().window().maximize();
    }

    @Test
    public void testaTitulo() {
        String titulo = driver.getTitle();
        assertEquals("Home | University of Tampa", titulo);
        System.out.println("Titulo da pagina: " + titulo + " .Teste passou");
    }

    @Test
    public void testaLink() {
        WebElement link = driver.findElement(By.linkText("About UT"));
        link.click();
        String titulo = driver.getTitle();
        assertEquals("About UT | University of Tampa", titulo);
        System.out.println("Titulo da pagina: " + titulo + " .Teste passou");
    }

    @Test
    public void testaBusca() {
        WebElement busca = driver.findElement(By.className("search"));
        busca.click();
        busca = driver.findElement(By.id("searchInput"));
        busca.click();
        busca.sendKeys("Computer Science");
        busca.submit();
        String titulo = driver.getTitle();
        assertEquals("Search Results | University of Tampa", titulo);
        System.out.println("Titulo da pagina: " + titulo + " .Teste passou");
        driver.get("https://www.ut.edu/");
    }

    public void testaTelefone() {
        WebElement telefone = driver.findElement(By.className("phone"));
        assertEquals("(813) 253-3333", telefone.getText());
        System.out.println("Telefone: " + telefone.getText() + " .Teste passou");
    }

    public void testaSecaoPrincipal() {
        WebElement secaoPrincipal = driver.findElement(By.id("content"));
        //verifica se a seção principal contem a div com a classe img-grid
        Assertions.assertTrue(secaoPrincipal.findElements(By.className("img-grid")).size() > 0);
        //verifica se a seção img-grid contem 3 divs filhas
        Assertions.assertEquals(3, secaoPrincipal.findElement(By.className("img-grid")).findElements(By.tagName("div")).size());
        System.out.println("Teste passou");
    }

    public void testaDadosDaTelaPrincipal() {
        WebElement secaoPrincipal = driver.findElement(By.className("facts"));
        //Verifica se a seção principal contem a div com a classe facts
        Assertions.assertTrue(secaoPrincipal.findElements(By.className("facts")).size() > 0);
        //Verifica se a seção facts contem 4 divs filhas
        Assertions.assertEquals(4, secaoPrincipal.findElement(By.className("facts")).findElements(By.tagName("div")).size());
        //Busca todos os filhos do elemento com a classe facts
        WebElement[] filhos = secaoPrincipal.findElement(By.className("facts")).findElements(By.tagName("div")).toArray(new WebElement[0]);
        //verifica se o primeiro filho no caminho filho->div->a->span[0] contem o texto 10,500
        Assertions.assertEquals("10,500", filhos[0].findElement(By.tagName("div")).findElement(By.tagName("a")).findElement(By.tagName("span")).getText());
        //verifica se o segundo filho no caminho filho->div->a->span[0] contem o texto 200
        Assertions.assertEquals("200", filhos[1].findElement(By.tagName("div")).findElement(By.tagName("a")).findElement(By.tagName("span")).getText());
        //verifica se o terceiro filho no caminho filho->div->a->span[0] contem o texto 17:1
        Assertions.assertEquals("17:1", filhos[2].findElement(By.tagName("div")).findElement(By.tagName("a")).findElement(By.tagName("span")).getText());
        //verifica se o quarto filho no caminho filho->div->a->span[0] contem o texto 92%
        Assertions.assertEquals("92%", filhos[3].findElement(By.tagName("div")).findElement(By.tagName("a")).findElement(By.tagName("span")).getText());
        System.out.println("Teste passou");
    }
    

    public void testaPlayButton(){
        //Verifica se o segundo botão com a classe play-button cria um iframe com a classe mfp-iframe
        WebElement playButton = driver.findElements(By.className("play-button")).get(1);
        playButton.click();
        WebElement iframe = driver.findElement(By.className("mfp-iframe"));
        Assertions.assertTrue(iframe.isDisplayed());
        System.out.println("Teste passou");
        iframe = driver.findElement(By.className("mfp-close"));
        iframe.click();
    }

    public void testeCaixaSocial(){
        WebElement caixaSocial = driver.findElement(By.className("social"));
        //Verifica se a caixa social contem 7 divs filhas
        Assertions.assertEquals(7, caixaSocial.findElements(By.tagName("li")).size());
        System.out.println("Teste passou");
    }

    public void testeSeBotaoToolsAbre(){
        WebElement botaoTools = driver.findElement(By.className("tools"));
        //Verifica se o botão com a classe tools coloca a classe 'opened' no elemento 'a' filho
        botaoTools.click();
        Assertions.assertTrue(botaoTools.findElement(By.tagName("a")).getAttribute("class").contains("opened"));
        System.out.println("Teste passou");
        botaoTools.click();
    }

    public void testeSeOElementoQueMostraTemperaturaExiste(){
        WebElement elementoTemperatura = driver.findElement(By.className("weather"));
        //Verifica se o elemento com a classe weather contem um elemento com a classe js--wheather-temperature que contenha algum texto
        Assertions.assertTrue(elementoTemperatura.findElement(By.className("js--weather-temperature")).getText().length() > 0);
        System.out.println("Teste passou");
    }

    public void testeLogoDoSite(){
        WebElement logo = driver.findElement(By.className("logo"));
        //Verifica se o elemento com a classe logo contem um elemento com a tag img que contenha um atributo src com o valor /Images/logo%403x.png
        Assertions.assertEquals("/Images/logo%403x.png", logo.findElement(By.tagName("img")).getAttribute("src"));
        System.out.println("Teste passou");
    }

    public void fechaBrowser() {
        driver.quit();
    }

    public static void main(String[] args) {
        Teste teste = new Teste();
        teste.testaTitulo();
        teste.testaBusca();
        teste.testaTelefone();
        //teste.testaSecaoPrincipal();
        //teste.testaDadosDaTelaPrincipal();
        teste.testaPlayButton();
        teste.testeCaixaSocial();
        teste.testeSeBotaoToolsAbre();
        teste.testeSeOElementoQueMostraTemperaturaExiste();
        teste.testaLink();
        teste.testeLogoDoSite();


        teste.fechaBrowser();
    }
}