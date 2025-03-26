import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ComponentesSelenium {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Aumenta o tamanho da tela
        driver.get("https://demo.automationtesting.in/Register.html"); // Informa o site que será testado
    }

    // TESTE BOTÃO SUBMIT
    @Test
    public void submitButton() {
        WebElement submitButton = driver.findElement(By.id("submitbtn"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        Assert.assertEquals("Submit", submitButton.getText());
        submitButton.click();
    }

    // TESTE LOCALIZAR FULL NAME
    @Test
    public void findElementByOtherElement() {
        WebElement formElement = driver.findElement(By.id("basicBootstrapForm"));
        WebElement divElement = formElement.findElement(By.className("form-group"));
        WebElement labelElement = divElement.findElement(By.tagName("label"));
        Assert.assertEquals("Full Name*", labelElement.getText());
    }

    // TESTE NOME COMPLETO
    @Test
    public void fillName() {
        WebElement firstName = driver.findElement(By.cssSelector("input[ng-model='FirstName']"));
        firstName.sendKeys("Mateus");
        WebElement lastName = driver.findElement(By.cssSelector("input[ng-model='LastName']"));
        lastName.sendKeys("Euzebio");
    }

    // TESTE ENDEREÇO
    @Test
    public void fillAddress() {
        WebElement addressField = driver.findElement(By.cssSelector("textarea[ng-model='Adress']"));
        addressField.sendKeys("Avenida JK, 1626 - Quebec\nLondrina - PR\nCEP: 86010-000");
    }

    // TESTE EMAIL
    @Test
    public void fillEmail() {
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("mateuseuzebio@edu.unifil.br");
    }

    // TESTE TELEFONE
    @Test
    public void fillPhone() {
        WebElement phoneField = driver.findElement(By.cssSelector("input[ng-model='Phone']"));
        phoneField.sendKeys("43999999999");
    }

    // TESTE GÊNERO
    @Test
    public void usingRadioButton() {
        List<WebElement> radioElements = driver.findElements(By.name("radiooptions"));
        Assert.assertEquals(2, radioElements.size());

        WebElement radioElement = radioElements.get(1);
        Assert.assertEquals("FeMale", radioElement.getAttribute("value"));

        radioElement.click();
    }

    // TESTE HOBBIES
    @Test
    public void selectHobbies() {
        WebElement cricketCheckbox = driver.findElement(By.id("checkbox1"));
        WebElement moviesCheckbox = driver.findElement(By.id("checkbox2"));
        WebElement hockeyCheckbox = driver.findElement(By.id("checkbox3"));

        moviesCheckbox.click();

        Assert.assertFalse("Cricket não deve estar selecionado", cricketCheckbox.isSelected());
        Assert.assertTrue("Movies deve estar selecionado", moviesCheckbox.isSelected());
        Assert.assertFalse("Hockey não deve estar selecionado", hockeyCheckbox.isSelected());
    }

    // TESTE IDIOMAS
    @Test
    public void selectLanguages() {
        WebElement languageElement = driver.findElement(By.id("msdd"));
        languageElement.click();

        WebElement englishOption = driver.findElement(
                By.xpath("//a[contains(.,'English')]"));
        englishOption.click();
        languageElement.click();
    }

    // TESTE SKILLS
    @Test
    public void selectOption() {
        WebElement element = driver.findElement(By.id("Skills"));
        Select selectionOption = new Select(element);

        selectionOption.selectByValue("Analytics");
        Assert.assertEquals("Analytics", selectionOption.getFirstSelectedOption().getText());

        selectionOption.selectByIndex(2);
        Assert.assertEquals("Adobe Photoshop", selectionOption.getFirstSelectedOption().getText());

        selectionOption.selectByVisibleText("C");
        Assert.assertEquals("C", selectionOption.getFirstSelectedOption().getText());
    }

    // TESTE PAÍS
    @Test
    public void selectCountry() {
        WebElement countryDropdown = driver.findElement(
                By.xpath("//span[@class='select2-selection select2-selection--single']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", countryDropdown);
        countryDropdown.click();

        WebElement searchField = driver.findElement(By.className("select2-search__field"));
        searchField.sendKeys("Netherlands");
        searchField.sendKeys(Keys.ENTER);

        WebElement selectedCountry = driver.findElement(By.id("select2-country-container"));
        Assert.assertTrue(selectedCountry.getText().contains("Netherlands"));
    }

    // TESTE DATA DE NASCIMENTO
    @Test
    public void selectElement() {
        WebElement yearElement = driver.findElement(By.id("yearbox"));
        WebElement dayElement = driver.findElement(By. id("daybox"));
        WebElement monthElement = driver.findElement(By.xpath("//select[@placeholder='Month']")); // Outra opção para achar o mês
        // WebElement monthElement = driver.findElement(By. id("monthbox"));

        Select yearSelect = new Select(yearElement);
        yearSelect.selectByValue("1992");

        Select daySelect = new Select(dayElement);
        daySelect.selectByIndex(22);

        Select monthSelect = new Select(monthElement);
        monthSelect.selectByVisibleText("April");
    }

    // TESTE SENHA
    @Test
    public void fillPassword() {
        WebElement passwordField = driver.findElement(By.id("firstpassword"));
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                passwordField
        );

        passwordField.sendKeys("Senha123@");
    }

    // TESTE CONFIRMAR SENHA
    @Test
    public void fillConfirmPassword() {
        WebElement confirmField = driver.findElement(By.id("secondpassword"));
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                confirmField
        );
        confirmField.sendKeys("Senha123@");
    }

    @Test
    public void refreshButton() {
        WebElement refreshButton = driver.findElement(By.id("Button1"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", refreshButton);
        Assert.assertEquals("Refresh", refreshButton.getText());
        refreshButton.click();
    }
}
