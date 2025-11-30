package esmt.der.chapitre7demo.controller;

import esmt.der.chapitre7demo.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class) // Charge uniquement la couche Web
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simule le navigateur/Postman

    @MockitoBean
    private CalculatorService calculatorService; // Simule le Service

    @Test
    void testAdditionEndpoint() throws Exception {
        // 1. GIVEN : On dit au Mock quoi répondre si on l'appelle
        when(calculatorService.add(2, 3)).thenReturn(5);

        // 2. WHEN & 3. THEN : On tire la requête et on vérifie
        mockMvc.perform(get("/add?a=2&b=3"))
                .andExpect(status().isOk())        // Vérifie HTTP 200
                .andExpect(content().string("5")); // Vérifie la réponse
    }
}
