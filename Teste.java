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
        driver = (WebDriver) new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.ut.edu/");
    }

    @Test
    public void testaTitulo() {
        String titulo = driver.getTitle();
        assertEquals("Home | University of Tampa", titulo);
    }

    @Test
    public void testaLink() {
        WebElement link = driver.findElement(By.linkText("About UT"));
        link.click();
        String titulo = driver.getTitle();
        assertEquals("About UT | University of Tampa", titulo);
    }

    @Test
    public void testaBusca() {
        WebElement busca = driver.findElement(By.className("search"));
        busca.sendKeys("Computer Science");
        busca.submit();
        String titulo = driver.getTitle();
        assertEquals("Search Results | University of Tampa", titulo);
    }

    public void testaTelefone() {
        WebElement telefone = driver.findElement(By.className("phone"));
        assertEquals("(813) 253-3333", telefone.getText());
    }

    public void testaSecaoPrincipal() {
        WebElement secaoPrincipal = driver.findElement(By.id("content"));
        //verifica se a seção principal contem a div com a classe img-grid
        Assertions.assertTrue(secaoPrincipal.findElements(By.className("img-grid")).size() > 0);
        //verifica se a seção img-grid contem 3 divs filhas
        Assertions.assertEquals(3, secaoPrincipal.findElement(By.className("img-grid")).findElements(By.tagName("div")).size());
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
    }
    

    public void testaPlayButton(){
        //Verifica se o botão com a classe play-button cria um iframe com a classe mfp-iframe
        WebElement playButton = driver.findElement(By.className("play-button"));
        playButton.click();
        WebElement iframe = driver.findElement(By.className("mfp-iframe"));
        Assertions.assertTrue(iframe.findElements(By.className("mfp-iframe")).size() > 0);
    }

    public void fechaBrowser() {
        driver.quit();
    }

    public static void main(String[] args) {
        Teste teste = new Teste();
        teste.testaTitulo();
        teste.testaLink();
        teste.testaBusca();
        teste.fechaBrowser();
    }
}