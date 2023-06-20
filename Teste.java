//classe para testar elementos do site da University of Tampa usando Selenium e JUnit
//https://www.ut.edu/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.junit.jupiter.api.Assertions;

public class Teste {
    private WebDriver driver = null;

    public Teste(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://www.ut.edu/");
        this.driver.manage().window().maximize();
    }

    @Test
    public void testaTitulo() {
        String titulo = driver.getTitle();
        try {
            assertEquals("Home | University of Tampa", titulo);
            System.out.println("Titulo da pagina: " + titulo + " .Teste passou");
        } catch (AssertionError e) {
            System.out.println("Titulo da pagina: " + titulo + " .Teste falhou");
        }
    }

    @Test
    public void testaLink() {
        WebElement link = driver.findElement(By.linkText("About UT"));
        link.click();
        String titulo = driver.getTitle();
        try {
            assertEquals("About UT | University of Tampa", titulo);
            System.out.println("Titulo da pagina: " + titulo + " .Teste passou");
        } catch (AssertionError e) {
            System.out.println("Titulo da pagina: " + titulo + " .Teste falhou");
        }
        driver.get("https://www.ut.edu/");
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
        try {
            assertEquals("Search Results | University of Tampa", titulo);
            System.out.println("Titulo da pagina: " + titulo + " .Teste passou");
        } catch (AssertionError e) {
            System.out.println("Titulo da pagina: " + titulo + " .Teste falhou");
        }
        driver.get("https://www.ut.edu/");
    }

    public void testaTelefone() {
        WebElement telefone = driver.findElement(By.className("phone"));
        try {
            assertEquals("(813) 253-3333", telefone.getText());
            System.out.println("Telefone: " + telefone.getText() + " .Teste passou");
        } catch (AssertionError e) {
            System.out.println("Telefone: " + telefone.getText() + " .Teste falhou");
        }
    }

    public void testaSecaoPrincipal() {
        WebElement secaoPrincipal = driver.findElement(By.id("content"));
        // verifica se a seção principal contem a div com a classe img-grid
        try {
            Assertions.assertTrue(secaoPrincipal.findElements(By.className("img-grid")).size() > 0);
            // verifica se a seção img-grid contem 3 divs filhas
            Assertions.assertEquals(3,
                    secaoPrincipal.findElement(By.className("img-grid")).findElements(By.tagName("div")).size());
            System.out.println("Teste passou");
        } catch (AssertionError e) {
            System.out.println("Teste falhou");
        }
    }

    public void testaDadosDaTelaPrincipal() {
        WebElement secaoPrincipal = driver.findElement(By.className("facts"));
        // Verifica se a seção principal contem a div com a classe facts
        try {
            Assertions.assertTrue(secaoPrincipal.findElements(By.className("facts")).size() > 0);
            // Verifica se a seção facts contem 4 divs filhas
            Assertions.assertEquals(4,
                    secaoPrincipal.findElement(By.className("facts")).findElements(By.tagName("div")).size());
            // Busca todos os filhos do elemento com a classe facts
            WebElement[] filhos = secaoPrincipal.findElement(By.className("facts")).findElements(By.tagName("div"))
                    .toArray(new WebElement[0]);
            // verifica se o primeiro filho no caminho filho->div->a->span[0] contem o texto
            // 10,500
            Assertions.assertEquals("10,500", filhos[0].findElement(By.tagName("div")).findElement(By.tagName("a"))
                    .findElement(By.tagName("span")).getText());
            // verifica se o segundo filho no caminho filho->div->a->span[0] contem o texto
            // 200
            Assertions.assertEquals("200", filhos[1].findElement(By.tagName("div")).findElement(By.tagName("a"))
                    .findElement(By.tagName("span")).getText());
            // verifica se o terceiro filho no caminho filho->div->a->span[0] contem o texto
            // 17:1
            Assertions.assertEquals("17:1", filhos[2].findElement(By.tagName("div")).findElement(By.tagName("a"))
                    .findElement(By.tagName("span")).getText());
            // verifica se o quarto filho no caminho filho->div->a->span[0] contem o texto
            // 92%
            Assertions.assertEquals("92%", filhos[3].findElement(By.tagName("div")).findElement(By.tagName("a"))
                    .findElement(By.tagName("span")).getText());
            System.out.println("Teste passou");
        } catch (AssertionError e) {
            System.out.println("Teste falhou");
        }
    }

    public void testaPlayButton() {
        // Verifica se o segundo botão com a classe play-button cria um iframe com a
        // classe mfp-iframe
        WebElement playButton = driver.findElements(By.className("play-button")).get(1);
        playButton.click();
        WebElement iframe = driver.findElement(By.className("mfp-iframe"));
        try {
            Assertions.assertTrue(iframe.isDisplayed());
            System.out.println("Teste passou");
            iframe = driver.findElement(By.className("mfp-close"));
            iframe.click();
        } catch (AssertionError e) {
            System.out.println("Teste falhou");
        }
    }

    public void testeCaixaSocial() {
        WebElement caixaSocial = driver.findElement(By.className("social"));
        // Verifica se a caixa social contem 7 divs filhas
        try {
            Assertions.assertEquals(7, caixaSocial.findElements(By.tagName("li")).size());
            System.out.println("Teste passou");
        } catch (AssertionError e) {
            System.out.println("Teste falhou");
        }
    }

    public void testeSeBotaoToolsAbre() {
        WebElement botaoTools = driver.findElement(By.className("tools"));
        // Verifica se o botão com a classe tools coloca a classe 'opened' no elemento
        // 'a' filho
        botaoTools.click();
        try {
            Assertions.assertTrue(botaoTools.findElement(By.tagName("a")).getAttribute("class").contains("opened"));
            System.out.println("Teste passou");
        } catch (AssertionError e) {
            System.out.println("Teste falhou");
        }
        botaoTools.click();
    }

    public void testeSeOElementoQueMostraTemperaturaExiste() {
        WebElement elementoTemperatura = driver.findElement(By.className("weather"));
        // Verifica se o elemento com a classe weather contem um elemento com a classe
        // js--wheather-temperature que contenha algum texto
        try {
            Assertions.assertTrue(
                    elementoTemperatura.findElement(By.className("js--weather-temperature")).getText().length() > 0);
            System.out.println("Teste passou");
        } catch (AssertionError e) {
            System.out.println("Teste falhou");
        }
    }

    public void testeLogoDoSite() {
        WebElement logo = driver.findElement(By.className("logo"));
        // Verifica se o elemento com a classe logo contem um elemento com a tag img que
        // contenha um atributo src com o valor /Images/logo%403x.png
        try {
            Assertions.assertEquals("/Images/logo%403x.png", logo.findElement(By.tagName("img")).getAttribute("src"));
            System.out.println("Teste passou");
        } catch (AssertionError e) {
            System.out.println("Teste falhou");
        }
        driver.get("https://www.ut.edu/");
    }

    public void testaFormsDeComentario() {
        WebElement comments = driver.findElement(By.xpath("//div[@title='Comments']"));
        comments.click();
        WebElement departamentoASerComentado = driver.findElement(By.id("field80623991"));
        departamentoASerComentado.click();
        departamentoASerComentado.sendKeys("Computer Science");
        WebElement nome = driver.findElement(By.id("field80623992-first"));
        nome.click();
        nome.sendKeys("Teste nome");
        WebElement sobrenome = driver.findElement(By.id("field80623992-last"));
        sobrenome.click();
        sobrenome.sendKeys("Teste sobrenome");
        WebElement radioButton = driver.findElement(By.id("field80623997_1"));
        radioButton.click();
        WebElement courtesyElement = driver.findElement(By.id("fsPipButton-80624687-3"));
        courtesyElement.click();
        WebElement eficienciaElement = driver.findElement(By.id("fsPipButton-80624753-2"));
        eficienciaElement.click();
        WebElement thoroughnessElement = driver.findElement(By.id("fsPipButton-80624749-1"));
        thoroughnessElement.click();
        WebElement timelinessElement = driver.findElement(By.id("fsPipButton-80624751-4"));
        timelinessElement.click();

        WebElement buttonToSend = driver.findElement(By.id("fsSubmitButton3535495"));
        buttonToSend.click();

        WebElement mensagemDeSucesso = driver.findElement(By.className("fsSectionText")).findElement(By.tagName("p"));
        try {
            Assertions.assertEquals(
                    "Thank you for providing feedback about your service experience. The information that you provided will be shared with the appropriate department (s). If you have requested a response, it will be sent shortly.",
                    mensagemDeSucesso.getText());
        } catch (AssertionError e) {
            System.out.println("Teste falhou");
        }
    }

    public void testeMapaDoCampus(){
        WebElement mapa = driver.findElement(By.xpath("//a[@title='Campus']"));
        mapa.click();
        WebElement mapaDoCampus = driver.findElement(By.xpath("//a[@title='UT Campus Map']"));
        try {
            Assertions.assertEquals("/uploadedFiles/About/UTCampusMap-a_924.pdf", mapaDoCampus.getAttribute("href"));
            System.out.println("Teste passou");
        } catch (AssertionError e) {
            System.out.println("Teste falhou");
        }
    }

    public void testeVisitCampus(){
        WebElement visitCampus = driver.findElement(By.xpath("//a[@title='Visit Campus']"));
        visitCampus.click();
        try {
            Assertions.assertTrue(driver.getCurrentUrl().contains("visit"));
            System.out.println("Teste passou");
        } catch (AssertionError e) {
            System.out.println("Teste falhou");
        }
        driver.get("https://www.ut.edu/");
    }




    public void fechaBrowser() {
        driver.quit();
    }

    public static void main(String[] args) {
        WebDriver driver = new EdgeDriver(); // Mudar o driver para o do seu navegador
        Teste teste = new Teste(driver);
        teste.testaTitulo();
        /*
         * teste.testaLink();
         * teste.testaBusca();
         * teste.testaTelefone();
         * teste.testaSecaoPrincipal();
         * teste.testaDadosDaTelaPrincipal();
         * teste.testaPlayButton();
         * teste.testeCaixaSocial();
         * teste.testeSeBotaoToolsAbre();
         * teste.testeSeOElementoQueMostraTemperaturaExiste();
         * teste.testaLink();
         * teste.testeLogoDoSite();
         */

        teste.fechaBrowser();
    }
}