package junit;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successFillTest() {
        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        $("#firstName").setValue("Test");
        $("#lastName").setValue("Testovy");
        $("#userEmail").setValue("pochta@tail.ru");
        $(byText("Male")).click();
        $("#userNumber").setValue("0123456789");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day.react-datepicker__day--015").click();

        $("#subjectsInput").setValue("Maths").pressEnter();
        $(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("picture.jpg");
        $("#currentAddress").setValue("Minsk");

        $("#state").click();
        $(byText("NCR")).click();


        $("#city").click();
        $(byText("Delhi")).click();

        $("#submit").click();

        $(".table-responsive").shouldHave(
                text("Test Testovy"),
                text("pochta@tail.ru"),
                text("Male"),
                text("0123456789"),
                text("15 June,1999"),
                text("Maths"),
                text("Reading"),
                text("picture.jpg"),
                text("Minsk"),
                text("NCR Delhi")
        );

    }


}