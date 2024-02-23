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

}