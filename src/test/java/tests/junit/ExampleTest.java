package tests.junit;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.junit.jupiter.CitrusExtension;
import dto.Data;
import dto.Support;
import dto.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;


@ExtendWith({CitrusExtension.class})
@Tag("@example")
@DisplayName("Набор тестов со страницей преподавателя")
public class ExampleTest {

    @Test
    @Tag("Тест№1")
    @CitrusTest
    public void test(@CitrusResource TestCaseRunner test) {
        test.variable("variable", "superValue");
        test.$(echo("Property \"value\" = ${variable}"));
        test.$(echo("We have userId = ${userId}"));
        test.variable("now", "citrus:currentDate()");

        test.run(http()
                .client("restClientReqres")
                .send()
                .get("user/${userId}"));
    }

//    @Test
////    @Test(description = "Получение информации о пользователе 1, валидация на строку")
//    @CitrusTest
//    public void getTestActions1() {
//        this.context = citrus.getCitrusContext().createTestContext();
//
//        // Отправка
//        run(http()
//                .client("restClientReqres")
//                .send()
//                .get("users/" + context.getVariable("userId")));
//        //
//        run(http()
//                .client("restClientReqres")
//                .receive()
//                .response(HttpStatus.OK)
//                .message()
//                .type(MessageType.JSON)
//                .body(
//                        "{\n" +
//                                "    \"data\": {\n" +
//                                "        \"id\": 5,\n" +
//                                "        \"email\": \"charles.morris@reqres.in\",\n" +
//                                "        \"first_name\": \"Charles\",\n" +
//                                "        \"last_name\": \"Morris\",\n" +
//                                "        \"avatar\": \"https://reqres.in/img/faces/5-image.jpg\"\n" +
//                                "    },\n" +
//                                "    \"support\": {\n" +
//                                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
//                                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
//                                "    }\n" +
//                                "}"
//                )
//        );
//    }
//
//    @Test
////    @Test(description = "Получение информации о пользователе 2, валидация на DTO объект")
//    @CitrusTest
//    public void getTestActions2() {
//        this.context = citrus.getCitrusContext().createTestContext();
//
//        // Отправка
//        run(http()
//                .client("restClientReqres")
//                .send()
//                .get("users/" + context.getVariable("userId")));
//        //
//        run(http()
//                .client("restClientReqres")
//                .receive()
//                .response(HttpStatus.OK)
//                .message()
//                .type(MessageType.JSON)
//                .body(new ObjectMappingPayloadBuilder(getUserDTO(), "objectMapper"))
//        );
//    }
//
//    @Test
////    @Test(description = "Получение информации о пользователе 2, валидация JSON файл")
//    @CitrusTest
//    public void getTestActions3() {
//        this.context = citrus.getCitrusContext().createTestContext();
//
//        // Отправка
//        run(http()
//                .client("restClientReqres")
//                .send()
//                .get("users/" + context.getVariable("userId")));
//
//        run(http()
//                .client("restClientReqres")
//                .receive()
//                .response(HttpStatus.OK)
//                .message()
//                .type(MessageType.JSON)
//                .body(new ClassPathResource("json/user5.json"))
//        );
//    }

    public UserDTO getUserDTO() {
        return UserDTO.builder()
                .data(new Data(
                        "https://reqres.in/img/faces/5-image.jpg",
                        "charles.morris@reqres.in",
                        5L,
                        "Charles",
                        "Morris"))
                .support(new Support(
                        "To keep ReqRes free, contributions towards server costs are appreciated!",
                        "https://reqres.in/#support-heading"))
                .build();
    }
}
