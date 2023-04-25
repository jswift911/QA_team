package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    // 1) Тест, на добавление средств при нулевом начальном балансе не превышающем максимальный баланс

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                0,
                0,
                10_000,
                5
        );

        account.add(3_000);

        int expected = 3_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 2) Тест, на добавление средств при не нулевом начальном балансе не превышающем максимальный баланс

    @Test
    public void shouldAddLessThanMaxBalancePositive() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        int expected = 5_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 3) Тест, на добавление средств при превышении максимального баланса

    @Test
    public void shouldAddHigherThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(30_000);

        int expected = 2_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 4) Тест, на добавление средств которое будет равно максимальному балансу

    @Test
    public void shouldAddEqualsMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        int expected = 10_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 5) Тест, проверяющий метод pay() не превышающий размер начального баланса

    @Test
    public void shouldPay() {
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );

        account.pay(3_000);

        int expected = 5_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 6) Тест, проверяющий метод pay() превышающий размер минимального баланса

    @Test
    public void shouldPayHigherThanBalance() {
        SavingAccount account = new SavingAccount(
                10_000,
                5_000,
                30_000,
                5
        );

        account.pay(8_000);

        int expected = 10_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 7) Тест, когда результат метода pay() равен размеру минимального баланса

    @Test
    public void shouldPayEqualsBalance() {
        SavingAccount account = new SavingAccount(
                10_000,
                5_000,
                30_000,
                5
        );

        account.pay(5_000);

        int expected = 5_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // 8) Тест, когда результат метода yearChange() при положительном балансе

    @Test
    public void shouldYearChangePositiveBalance() {
        SavingAccount account = new SavingAccount(
                200,
                0,
                30_000,
                15
        );

        int expected = 30;
        int actual = account.yearChange();

        Assertions.assertEquals(expected, actual);
    }

    // 9) Тест, на результат метода yearChange() при нулевом балансе

    @Test
    public void shouldYearChangeEmptyBalance() {
        SavingAccount account = new SavingAccount(
                0,
                0,
                30_000,
                15
        );


        int expected = 0;
        int actual = account.yearChange();

        Assertions.assertEquals(expected, actual);
    }

    // 10) Тест, на выброс исключения при некорректном значении rate

    @Test
    public void shouldThrowIllegalArgumentExceptionRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    0,
                    0,
                    30_000,
                    -5
            );
        });
    }

    // 11) Тест, на выброс исключения когда минимальный баланс больше максимального

    @Test
    public void shouldThrowIllegalArgumentExceptionMinHigherMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    0,
                    20_000,
                    10_000,
                    15
            );
        });
    }

    // 12) Тест, на выброс исключения когда начальный баланс больше максимального

    @Test
    public void shouldThrowIllegalArgumentExceptionInitialNotMaxAndMin() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    50_000,
                    20_000,
                    10_000,
                    15
            );
        });
    }

    // 13) Тест проверяет, что минимальный баланс не отрицательный

    @Test
    public void shouldThrowIllegalArgumentExceptionNegativeMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    5_000,
                    -5_000,
                    10_000,
                    15
            );
        });
    }
}
