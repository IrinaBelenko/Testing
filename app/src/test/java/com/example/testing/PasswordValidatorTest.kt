package com.example.testing

import org.junit.Assert
import org.junit.Test

class PasswordValidatorTest {
    @Test
    fun if_password_is_null_return_false(){
        val validator = PasswordValidator()
        Assert.assertEquals(validator.validate(null), false)
    }

    @Test
    fun if_password_is_empty_return_false() {
        val validator = PasswordValidator()
        Assert.assertEquals(validator.validate(""), false)
    }

    @Test
    fun if_password_more_than_7_and_less_than_32_symbols_return_true() {
        val validator = PasswordValidator()
        Assert.assertEquals(validator.validate("12345678"), true)
    }

}