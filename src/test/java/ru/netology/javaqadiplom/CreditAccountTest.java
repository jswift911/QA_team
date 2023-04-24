package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    // 1) Тест, на добавление средств при нулевом балансе

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        int expected = 3_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 2) Тест, на добавление средств при не нулевом балансе

    @Test
    public void shouldAddToPositiveBalanceWithInitialBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                15_000,
                15
        );

        account.add(3_000);

        int expected = 4_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 3) Тест на генерацию исключения IllegalArgumentException при отрицательном rate

    @Test
    public void shouldThrowIllegalArgumentExceptionRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    1_000,
                    15_000,
                    -5
            );
        });
    }

    // 4) Тест на генерацию исключения IllegalArgumentException при отрицательном CreditLimit

    @Test
    public void shouldThrowIllegalArgumentExceptionCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    1_000,
                    -15_000,
                    5
            );
        });
    }

    // 5) Тест на генерацию исключения IllegalArgumentException при нескольких отрицательных параметрах

    @Test
    public void shouldThrowIllegalArgumentExceptionAll() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    -1_000,
                    -15_000,
                    -5
            );
        });
    }

    // 6) Тест, на метод pay когда сумма покупки превышает кредитный лимит

    @Test
    public void shouldPayHigherThanCreditLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                15_000,
                15
        );

        boolean expected = false;
        boolean actual = account.pay(30_000);

        Assertions.assertEquals(expected, actual);
    }

    // 7) Тест, на метод pay когда сумма покупки не превышает кредитный лимит

    @Test
    public void shouldPayLowerThanCreditLimit() {
        CreditAccount account = new CreditAccount(
                10_000,
                15_000,
                15
        );

        boolean expected = true;
        boolean actual = account.pay(3_000);

        Assertions.assertEquals(expected, actual);
    }

    // 8) Тест, на метод pay когда сумма покупки равна кредитному лимиту

    @Test
    public void shouldPayLowerEqualsCreditLimit() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        boolean expected = false;
        boolean actual = account.pay(5_000);

        Assertions.assertEquals(expected, actual);
    }

    // 9) Тест, проверку баланса при вызове метода pay когда сумма покупки превышает кредитный лимит

    @Test
    public void shouldPayLowerHigherCreditLimitBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(50_000);

        int expected = 0;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 10) Тест, проверку баланса при вызове метода pay когда сумма покупки не превышает кредитный лимит

    @Test
    public void shouldPayLowerLowerCreditLimitBalance() {
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                15
        );

        account.pay(1_000);

        int expected = 1_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 11) Тест, проверку баланса при вызове метода pay когда сумма покупки равна кредитному лимиту

    @Test
    public void shouldPayLowerEqualsCreditLimitBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(5_000);

        int expected = 0;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 12) Тест, проверку метода yearChange при отрицательном балансе

    @Test
    public void shouldYearChangeNegativeBalance() {
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15
        );

        account.balance = -200;

        int expected = -30;
        int actual = account.yearChange();

        Assertions.assertEquals(expected, actual);
    }

    // 13) Тест, проверку метода yearChange при положительном балансе

    @Test
    public void shouldYearChangePositiveBalance() {
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                30
        );

        int expected = 0;
        int actual = account.yearChange();

        Assertions.assertEquals(expected, actual);
    }

}
