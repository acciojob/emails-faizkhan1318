package com.driver;

public class Email {

    private final String emailId;
    private final String password;
    private String currentPassword;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }
    public String getCurrentPassword(){
        return currentPassword;
    }
    private void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        String currentPassword = getCurrentPassword();

        // check if old password matches the current password
        if (oldPassword.equals(currentPassword)) {
            System.out.println("Incorrect old password");
            return;
        }

        // check if the new password meets all the requirements
        if (newPassword.length() < 8) {
            System.out.println("Password should be at least 8 characters long");
            return;
        }

        boolean containsUpperCase = false;
        boolean containsLowerCase = false;
        boolean containsDigit = false;
        boolean containsSpecialChar = false;
        for (char ch : newPassword.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                containsUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                containsLowerCase = true;
            } else if (Character.isDigit(ch)) {
                containsDigit = true;
            } else {
                containsSpecialChar = true;
            }
        }

        if (!containsUpperCase) {
            System.out.println("Password should contain at least one uppercase letter");
            return;
        }

        if (!containsLowerCase) {
            System.out.println("Password should contain at least one lowercase letter");
            return;
        }

        if (!containsDigit) {
            System.out.println("Password should contain at least one digit");
            return;
        }

        if (!containsSpecialChar) {
            System.out.println("Password should contain at least one special character");
            return;
        }

        // if all requirements are met, change the password
        setCurrentPassword(newPassword);
        System.out.println("Password changed successfully");
    }
}
