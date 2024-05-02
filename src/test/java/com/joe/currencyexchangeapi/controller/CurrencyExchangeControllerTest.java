package com.joe.currencyexchangeapi.controller;

import com.joe.currencyexchangeapi.enums.CurrencyEnum;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void testCurrencyExchangeUseThousandsShouldReturnMsgIsSuccess() {
        //使用含千分位的資料進行測試
        String amount = "1,145.14";
        MvcResult asyncResult = mockMvc
                .perform(
                        get("/currency_exchange")
                                .param("source", CurrencyEnum.TWD.getCode())
                                .param("target", CurrencyEnum.TWD.getCode())
                                .param("amount", amount)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Map<String, String> object = (Map<String, String>) asyncResult.getAsyncResult();
        assertEquals("success", object.get("msg"));
        assertEquals(amount, object.get("amount"));
    }

    @SneakyThrows
    @Test
    void testCurrencyExchangeWithoutThousandsShouldReturnMsgIsSuccess() {
        //使用不含千分位的資料進行測試
        String amount = "1145.14";
        MvcResult asyncResult = mockMvc
                .perform(
                        get("/currency_exchange")
                                .param("source", CurrencyEnum.TWD.getCode())
                                .param("target", CurrencyEnum.TWD.getCode())
                                .param("amount", amount)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Map<String, String> object = (Map<String, String>) asyncResult.getAsyncResult();
        assertEquals("success", object.get("msg"));
        assertEquals("1,145.14", object.get("amount"));
    }

    @SneakyThrows
    @Test
    void testCurrencyExchangeWithUnsupportedCurrency() {
        //測試輸入的 source 或 target api並不支援
        String amount = "1,145.14";
        MvcResult asyncResult = mockMvc
                .perform(
                        get("/currency_exchange")
                                .param("source", "Unsupported")
                                .param("target", CurrencyEnum.TWD.getCode())
                                .param("amount", amount)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Map<String, String> object = (Map<String, String>) asyncResult.getAsyncResult();
        assertTrue(object.get("msg").contains("failed, Unsupported currency conversion."));
    }

    @SneakyThrows
    @Test
    void testCurrencyExchangeWithInvalidAmount() {
        //測試輸入的金額為非數字或無法辨識
        String amount = "hahaha";
        MvcResult asyncResult = mockMvc
                .perform(
                        get("/currency_exchange")
                                .param("source", CurrencyEnum.USD.getCode())
                                .param("target", CurrencyEnum.TWD.getCode())
                                .param("amount", amount)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Map<String, String> object = (Map<String, String>) asyncResult.getAsyncResult();
        assertTrue(object.get("msg").contains("failed, Unsupported amount conversion"));
    }

    @SneakyThrows
    @Test
    void testCurrencyExchangeWithIntegerAmount() {
        //測試金額為整數
        String amount = "2";
        String expectedAmount = "60.89";
        MvcResult asyncResult = mockMvc
                .perform(
                        get("/currency_exchange")
                                .param("source", CurrencyEnum.USD.getCode())
                                .param("target", CurrencyEnum.TWD.getCode())
                                .param("amount", amount)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Map<String, String> object = (Map<String, String>) asyncResult.getAsyncResult();
        assertEquals("success", object.get("msg"));
        assertEquals(expectedAmount, object.get("amount"));
    }

    @SneakyThrows
    @Test
    void testCurrencyExchangeWithDecimalOnePlace() {
        //測試金額為小數，且小數點後一位
        String amount = "2.2";
        String expectedAmount = "66.98";
        MvcResult asyncResult = mockMvc
                .perform(
                        get("/currency_exchange")
                                .param("source", CurrencyEnum.USD.getCode())
                                .param("target", CurrencyEnum.TWD.getCode())
                                .param("amount", amount)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Map<String, String> object = (Map<String, String>) asyncResult.getAsyncResult();
        assertEquals("success", object.get("msg"));
        assertEquals(expectedAmount, object.get("amount"));
    }

    @SneakyThrows
    @Test
    void testCurrencyExchangeWithDecimalTwoPlace() {
        //測試金額為小數，且小數點後兩位
        String amount = "2.22";
        String expectedAmount = "67.59";
        MvcResult asyncResult = mockMvc
                .perform(
                        get("/currency_exchange")
                                .param("source", CurrencyEnum.USD.getCode())
                                .param("target", CurrencyEnum.TWD.getCode())
                                .param("amount", amount)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Map<String, String> object = (Map<String, String>) asyncResult.getAsyncResult();
        assertEquals("success", object.get("msg"));
        assertEquals(expectedAmount, object.get("amount"));
    }
}
