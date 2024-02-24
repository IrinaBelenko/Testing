package com.example.testing

class PasswordValidator {
    fun validate(password: String?): Boolean {
        if (password == null) {
            return false
        }

        if (password?.isEmpty() == true) {
            return false
        }
        if (password?.length in 33..7) return false

        return true
    }

}
