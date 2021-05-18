import com.google.common.annotations.VisibleForTesting;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.print.Printable;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EjerciciosSelenium1 {

    public WebDriver getDriver(String URL){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }

    @Test
    public void ejercicio1() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com");

        System.out.println("El titulo de la pagina es: " + driver.getTitle());
        System.out.println("La URL del sitio es: " + driver.getCurrentUrl());
    }

    @Test
    public void bbcMundTest() {
        String URL_BBC_MUNDO = "https://www.bbc.com/mundo";
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(URL_BBC_MUNDO);

        List<WebElement> listaH1 = driver.findElements(By.tagName("h1"));
        System.out.println("la cantidad de h1 es: " + listaH1.size());

        List<WebElement> listaH2 = driver.findElements(By.tagName("h2"));
        System.out.println("la cantidad de h2 es: " + listaH2.size());

        System.out.println("Elementos h1:");
        for(WebElement elementoH1: listaH1){
            System.out.println(elementoH1.getText());
        }

        System.out.println("Elementos h2:");
        for (WebElement elementoH2: listaH2){
            System.out.println(elementoH2.getText());
        }
    }

    @Test
    public void bbcMundoLinks() {
        String URL_BBC_MUNDO = "https://www.bbc.com/mundo";
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(URL_BBC_MUNDO);

        List<WebElement> listaLinks = driver.findElements(By.tagName("a"));
        System.out.println(listaLinks.size());
        for (WebElement elementoLinks: listaLinks){
            System.out.println(elementoLinks.getText());
        }
    }

    @Test
    public void bbcMundoListas() {
        String URL_BBC_MUNDO = "https://www.bbc.com/mundo";
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(URL_BBC_MUNDO);

        List<WebElement> listaList = driver.findElements(By.tagName("li"));
        System.out.println(listaList.size());

        for (WebElement elementoListas : listaList) {
            System.out.println(elementoListas.getText());
        }

    }

    @Test
    public void spotifyTitleTest() {
        String URL_SPOTIFY = "https://www.spotify.com";
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(URL_SPOTIFY);

        //List<WebElement> listaH1 = driver.findElements(By.tagName("h1"));
        //System.out.println(listaH1.size());

        String title = driver.getTitle();
        if (title.equals("Escuchar es todo - Spotify")) {
            System.out.println("Test Passed!!");
        } else {
            System.out.println(title);
            System.out.println("Test Failed!!");
        }
    }

    @Test
    public void getWindowsSizeTest() {
        String URL_GOOGLE = "https://www.google.com";
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(URL_GOOGLE);

        int heigth = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();

        System.out.println(heigth);
        System.out.println(width);

        //driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        Dimension dimension = new Dimension(1024, 768);
        driver.manage().window().setSize(dimension);
        System.out.println("Actualizando el largo y ancho.....");

        heigth = driver.manage().window().getSize().getHeight();
        width = driver.manage().window().getSize().getWidth();

        System.out.println("El largo actual es: " + heigth);
        System.out.println("El ancho actual es: " + width);
    }

    @Test
    public void searchInGoogle() {

        WebDriver driver = getDriver("https://www.google.com");

        System.out.println("el titulo de la pagina es: " + driver.getTitle());

        driver.findElement(By.name("q")).sendKeys("WebElement" + Keys.ENTER);
        //Thread.sleep(3000);
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();
    }

    @Test
    public void facebookPageTest() {

        WebDriver driver = getDriver("https://www.facebook.com");

        List<WebElement> listaDivs = driver.findElements(By.tagName("div"));
        List<WebElement> listalinksFacebook = driver.findElements(By.tagName("a"));
        List<WebElement> listaBotonesFacebook = driver.findElements(By.tagName("button"));

        System.out.println("listado de divs de Facebook: " + listaDivs.size());
        System.out.println("listado de botones de Facebook: " + listaBotonesFacebook.size());

        System.out.println("*** links ***");
        for (WebElement elementolinks: listalinksFacebook) {
            System.out.println(elementolinks.getText());
        }

        System.out.println("*** Buttons ***");
        for (WebElement elementoButton: listaBotonesFacebook) {
            System.out.println(elementoButton.getText());
        }
    }

    @Test
    public void sendKeysToFacebook() {

        WebDriver driver = getDriver("https://www.facebook.com");

        driver.findElement(By.name("email")).sendKeys("test@test.com");
        driver.findElement(By.name("pass")).sendKeys("HolaMundo");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void forgotAccountTest() {

        WebDriver driver = getDriver("https://www.facebook.com");
        driver.manage().window().maximize();

        String titulo = driver.getTitle();
        Assert.assertEquals("*** titulo no coincide ***", "Facebook - Inicia sesión o regístrate", titulo);

        driver.findElement(By.linkText("¿Olvidaste tu contraseña?")).click();
        String titulo2 = driver.getTitle();
        Assert.assertEquals("*** el titulo no es correcto", "¿Olvidaste tu contraseña? | No puedo iniciar sesión | Facebook",titulo2);
        Assert.assertNotEquals("*** Se encuentra en la URL incorrecta ***", driver.getCurrentUrl(), "https://www.facebook.com/");

        driver.close();
    }

    @Test
    public void forgotAccountPartialLinkTest() {
        WebDriver driver = getDriver("https://www.facebook.com");
        driver.manage().window().maximize();

        driver.findElement(By.partialLinkText("¿Olvidaste")).click();
        driver.close();
    }

    @Test
    public void checkBoxAndComboboxTest() throws InterruptedException {
        WebDriver driver = getDriver("https://www.facebook.com");
        driver.manage().window().maximize();

        driver.findElement(By.linkText("Crear cuenta nueva")).click();
        Thread.sleep(5000);
        driver.findElement(By.name("firstname")).sendKeys("Alan");
        driver.findElement(By.name("lastname")).sendKeys("Smith");
        driver.findElement(By.xpath("//label[contains(text(), 'Personalizado')]")).click();

        WebElement dias = driver.findElement(By.id("day"));
        Select comboboxDias = new Select(dias);
        comboboxDias.selectByValue("4");

        WebElement meses = driver.findElement(By.id("month"));
        Select comboboxMeses = new Select(meses);
        comboboxMeses.selectByVisibleText("abr");

        WebElement años = driver.findElement(By.id("year"));
        Select comboboxAños = new Select(años);
        comboboxAños.selectByIndex(9);

        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void comboboxTest() throws InterruptedException {
        WebDriver driver = getDriver("https://www.facebook.com");
        driver.manage().window().maximize();

        Thread.sleep(3000);
        driver.findElement(By.linkText("Crear cuenta nueva")).click();
        Thread.sleep(5000);

        WebElement meses = driver.findElement(By.id("month"));
        Select combo = new Select(meses);

        List<WebElement> options = combo.getOptions();
        Assert.assertNotEquals(0, options.size());

        boolean encontrar = false;

        for(WebElement opt: options){
            System.out.println(opt.getText());
            if (opt.getText().contentEquals("jun")) {
                encontrar = true;
                break;
            }
        }
    }

    @Test
    public void completeRegistration() throws InterruptedException {
        WebDriver driver = getDriver("https://www.facebook.com");
        driver.manage().window().maximize();

        Thread.sleep(3000);
        driver.findElement(By.linkText("Crear cuenta nueva")).click();
        Thread.sleep(5000);

        driver.findElement(By.name("firstname")).sendKeys("The");
        driver.findElement(By.name("lastname")).sendKeys("Smith");
        driver.findElement(By.name("reg_email__")).sendKeys("12341234");
        driver.findElement(By.name("reg_passwd__")).sendKeys("12341234");

        setBirthdate(driver, "22", "jun", "1990");

    }

    private void setBirthdate(WebDriver driver, String day, String month, String year) {

        WebElement dias = driver.findElement(By.name("birthday_day"));
        Select comboDias = new Select(dias);
        comboDias.selectByVisibleText(day);

        WebElement meses = driver.findElement(By.name("birthday_month"));
        Select comboMeses = new Select(meses);
        comboMeses.selectByVisibleText(month);

        WebElement año = driver.findElement(By.name("birthday_year"));
        Select comboAño = new Select(año);
        comboAño.selectByVisibleText(year);

    }

    

}




